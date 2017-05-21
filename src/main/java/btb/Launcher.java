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
		String productId;
		float buyPrice, sellPriceHigh, sellPriceLow;
		try
		{
			productId = args[0];
			buyPrice = Float.parseFloat( args[1] );
			sellPriceHigh = Float.parseFloat( args[2] );
			sellPriceLow = Float.parseFloat( args[3] );
		}
		catch( Exception e )
		{
			_logger.fatal( "Error while reading the arguments. Please read the instructions and try again." );
			throw e;
		}
		
		TradeStrategy tradeStrategy = new TradeStrategy( productId, buyPrice, sellPriceHigh, sellPriceLow );
		
		_logger.info( "Starting bot for:" );
		_logger.info( "Product ID: {}", productId );
		_logger.info( "Buy price: {}", buyPrice );
		_logger.info( "Sell price (High): {}", sellPriceHigh );
		_logger.info( "Sell price (Low): {}", sellPriceLow );
		
		RTConnection connection = null;
		try
		{
			connection = new RTConnection();
			ExitControl.setRTConnection( connection );
		}
		catch( Exception e )
		{
			_logger.fatal( "Error while starting real time connection.", e );
			ExitControl.exitOnError();
		}
		
		Trade trade = new LongTrade( tradeStrategy, new OrderExecutor() );
		TradeManager tradeManager = new TradeManager( trade );
		ExitControl.setTradeManager( tradeManager );
		BotStarter botStarter = new BotStarter( connection, tradeManager );
		try
		{
			botStarter.start();
		}
		catch( WebSocketException e )
		{
			_logger.fatal( "Exception when starting bot: ", e );
			ExitControl.exitOnError();
		}
	}

	private final static Logger _logger = LogManager.getLogger();
}

