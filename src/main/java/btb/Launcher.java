package btb;

import btb.order.OrderExecutor;
import btb.rtconnection.RTConnection;
import btb.trade.LongTrade;
import btb.trade.Trade;
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
			_logger.fatal( "Error while starting real time connection.", e );
			System.exit( 1 );
		}
		
		Trade trade = new LongTrade( tradeStrategy, new OrderExecutor() );
		TradeManager tradeManager = new TradeManager( trade );
		BotStarter botStarter = new BotStarter( connection, tradeManager );
		try
		{
			botStarter.start();
		}
		catch( WebSocketException e )
		{
			_logger.fatal( "Exception when starting bot: ", e );
			System.exit( 1 );
		}
	}

	private final static Logger _logger = LogManager.getLogger();
}

