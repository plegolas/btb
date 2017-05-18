package btb;

import btb.rtconnection.RTConnection;
import btb.trade.TradeManager;
import btb.trade.TradeStrategy;
import com.neovisionaries.ws.client.WebSocketException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Launcher
{
	public static void main( String[] args )
	{
		String productId = args[0];
		float buyPrice = Float.parseFloat( args[1] );
		float sellPriceUp = Float.parseFloat( args[2] );
		float sellPriceLow = Float.parseFloat( args[3] );
		
		TradeStrategy tradeStrategy = new TradeStrategy( productId, buyPrice, sellPriceUp, sellPriceLow );
		
		RTConnection connection = null;
		try
		{
			connection = new RTConnection();
		}
		catch( Exception e )
		{
			_logger.error( "Error while starting real time connection.", e );
			System.exit( 1 );
		}
		
		TradeManager tradeManager = new TradeManager( tradeStrategy );
		BotStarter botStarter = new BotStarter( connection, tradeManager );
		try
		{
			botStarter.start();
		}
		catch( WebSocketException e )
		{
			_logger.error( "Exception when starting bot: ", e );
			System.exit( 1 );
		}
	}

	private final static Logger _logger = LogManager.getLogger();
}

