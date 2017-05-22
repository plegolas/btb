package btb;

import java.io.IOException;

import btb.rtconnection.MessageUpdateListener;
import btb.rtconnection.RTConnection;
import btb.trade.TradeManager;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neovisionaries.ws.client.WebSocketException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Marcelo Giesel on 18-5-17.
 */
public class BotStarter implements MessageUpdateListener
{
	public BotStarter( RTConnection connection, TradeManager tradeManager )
	{
		_connection = connection;
		_tradeManager = tradeManager;
		_mapper = new ObjectMapper();
	}
	
	public void start() throws WebSocketException
	{
		_connection.addListener( this );
		_connection.connect();
	}
	
	@Override
	public void onMessageUpdate( String message )
	{
		if( isConnectedMessage( message ) )
		{
			_logger.info( "\nConnected: {}", message );
			_connection.removeListener( this );
			
			_connection.addListener( _tradeManager );
			
			String productId = _tradeManager.getTradeStrategy().getProductId();
			try
			{
				_connection.subscribe( productId );
			}
			catch( JsonProcessingException e )
			{
				_logger.error( "Error while trying to subscribe to product {}: ", productId, e );
				ExitControl.exitOnError();
			}
		}
		else
		{
			_logger.info( "Received non-connected message: {}", message );
		}
	}
	
	private boolean isConnectedMessage( String message )
	{
		try
		{
			JsonNode jsonMessage = _mapper.readTree( message );
			JsonNode messageType = jsonMessage.get( "t" );
			return messageType != null
					&& messageType.isTextual()
					&& messageType.textValue().equalsIgnoreCase( "connect.connected" );
		}
		catch( IOException e )
		{
			_logger.debug( "Error while parsing message: ", e );
			return false;
		}
	}
	
	private final RTConnection _connection;
	private final TradeManager _tradeManager;
	private final ObjectMapper _mapper;
	private final static Logger _logger = LogManager.getLogger();
	
}
