package btb.trade;

import java.io.IOException;

import btb.ExitControl;
import btb.rtconnection.MessageUpdateListener;
import btb.trade.rest.bean.RestOrderResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Marcelo Giesel on 14-5-17.
 */
public class TradeManager implements MessageUpdateListener
{
	public TradeManager( Trade trade )
	{
		_trade = trade;
		_tradeStrategy = trade.getTradeStrategy();
	}
	
	@Override
	public void onMessageUpdate( String message )
	{
		ObjectMapper mapper = new ObjectMapper();
		try
		{
			JsonNode jsonMessage = mapper.readTree( message );
			JsonNode messageType = jsonMessage.get( "t" );
			if( messageType != null
					&& messageType.isTextual()
					&& messageType.textValue().equalsIgnoreCase( "trading.quote" ) )
			{
				String messageBody = jsonMessage.get( "body" ).toString();
				priceUpdate( mapper.readValue( messageBody, TradeQuote.class ) );
			}
			else {
				_logger.debug( "Received not quote message: {}", jsonMessage );
			}
		}
		catch( IOException e )
		{
			_logger.info( "Error while parsing message: ", e );
		}
	}
	
	
	public void priceUpdate( TradeQuote tradeQuote )
	{
		try
		{
			if( isQuoteFromThisTrade( tradeQuote ) )
			{
				float price = tradeQuote.getCurrentPrice();
				_logger.debug( "Received new quote at {}.", price );
				
				if( _trade.getStatus() == Trade.TradeStatus.READY && _trade.isOpenPrice( price ) )
				{
					_logger.info( "Opening trade at {}.", price );
					_trade.open();
				}
				else if( _trade.getStatus() == Trade.TradeStatus.OPEN && _trade.isClosePrice( price ) )
				{
					_logger.info( "Closing trade at {}.", price );
					printCloseResponse( _trade.close() );
					ExitControl.exitOnSuccess();
				}
			}
		}
		catch( IOException e )
		{
			_logger.fatal( "Error on open/close trade:", e );
			ExitControl.exitOnError();
		}
	}
	
	private void printCloseResponse( RestOrderResponse response )
	{
		ObjectMapper mapper = new ObjectMapper();
		try
		{
			_logger.info( "Close response:{}", mapper.writeValueAsString( response ) );
		}
		catch( JsonProcessingException e )
		{
			_logger.error( "Could not parse close response: ", e );
		}
	}
	
	private boolean isQuoteFromThisTrade( TradeQuote tradeQuote )
	{
		return tradeQuote != null && tradeQuote.getSecurityId() != null
				&& tradeQuote.getSecurityId().equals( _tradeStrategy.getProductId() );
	}
	
	public Trade.TradeStatus getTradeStatus()
	{
		return _trade.getStatus();
	}
	
	public Trade getTrade()
	{
		return _trade;
	}
	
	public TradeStrategy getTradeStrategy()
	{
		return _tradeStrategy;
	}
	
	private final Trade _trade;
	private final TradeStrategy _tradeStrategy;
	private static final Logger _logger = LogManager.getLogger();
}
