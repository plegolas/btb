package btb.trade;

import java.io.IOException;

import btb.order.OrderExecutor;
import btb.rtconnection.MessageUpdateListener;
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
					_trade.close();
					System.exit( 0 );
				}
			}
		}
		catch( IOException e )
		{
			_logger.error( "Error on open/close trade:", e );
			System.exit( 1 );
		}
	}
	
	private boolean isQuoteFromThisTrade( TradeQuote tradeQuote )
	{
		if( tradeQuote != null && tradeQuote.getSecurityId() != null
				&& tradeQuote.getSecurityId().equals( _tradeStrategy.getProductId() ) )
		{
			return true;
		}
		return false;
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
