package btb;

import btb.rtconnection.RTConnection;
import btb.trade.Trade;
import btb.trade.TradeManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Marcelo Giesel on 21-5-17.
 */
public class ExitControl
{
	public static void exitOnError()
	{
		exit( releaseResources( _ERROR_CODE ) );
	}
	
	public static void exitOnSuccess()
	{
		exit( releaseResources( _SUCCESS_CODE ) );
	}
	
	private static int releaseResources( int exitCode )
	{
		Trade trade = null;
		String productId = null;
		if( _tradeManager != null )
		{
			trade = _tradeManager.getTrade();
		}
		
		if( trade == null )
		{
			_logger.info( "TradeManager or Trade is null. No trade to close." );
		}
		else
		{
			productId = trade.getTradeStrategy().getProductId();
			
			if( trade.getStatus() == Trade.TradeStatus.OPEN )
			{
				_logger.info( "Closing open trade..." );
				try
				{
					trade.close();
				}
				catch( Exception e )
				{
					_logger.fatal( "Could not close open trade! Trade can still be open!", e );
					exitCode = _ERROR_CODE;
				}
			}
			_logger.info( "Trade status: {}", trade.getStatus().name() );
		}
		
		
		if( _rtConnection == null )
		{
			_logger.info( "Real Time connection is null. No connection to close." );
		}
		else
		{
			if( productId != null )
			{
				try
				{
					_logger.info( "Unsubscribing from {}...", productId );
					_rtConnection.unsubscribe( productId );
				}
				catch( Exception e )
				{
					_logger.error( "Could not unsubscribe from updates of product ID {}!", e );
				}
			}
			_logger.info( "Closing Real Time connection..." );
			_rtConnection.disconnect();
		}
		
		return exitCode;
	}
	
	private static void exit( int exitCode )
	{
		// Commented out to run the tests.
//		System.exit( exitCode );
	}
	
	public static void setRTConnection( RTConnection rtConnection)
	{
		_rtConnection = rtConnection;
	}
	
	public static void setTradeManager( TradeManager tradeManager )
	{
		_tradeManager = tradeManager;
	}
	
	private static final int _ERROR_CODE = 1;
	private static final int _SUCCESS_CODE = 0;
	
	private static RTConnection _rtConnection = null;
	private static TradeManager _tradeManager = null;
	private static final Logger _logger = LogManager.getLogger();
}
