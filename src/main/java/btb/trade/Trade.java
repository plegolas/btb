package btb.trade;

import java.io.IOException;

import btb.order.OrderExecutor;
import btb.trade.bean.TradeStrategy;
import btb.trade.rest.bean.RestOrderResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Marcelo Giesel on 14-5-17.
 */
public abstract class Trade
{
	public Trade( TradeStrategy tradeStrategy, OrderExecutor orderExecutor )
	{
		_tradeStrategy = tradeStrategy;
		_status = TradeStatus.READY;
		_orderExecutor = orderExecutor;
		_positionId = "";
	}
	
	protected abstract boolean isOpenPrice( float price );
	
	protected abstract boolean isClosePrice( float price );
	
	public void open() throws IOException
	{
		_positionId = openTrade();
		_status = TradeStatus.OPEN;
		_logger.info( "Trade open with position id {}.", _positionId );
	}
	
	protected abstract String openTrade() throws IOException;
	
	public RestOrderResponse close() throws IOException
	{
		RestOrderResponse response =  _orderExecutor.close( _positionId );
		_status = TradeStatus.CLOSED;
		return response;
	}
	
	public TradeStrategy getTradeStrategy()
	{
	return _tradeStrategy;
	}
	
	public TradeStatus getStatus()
	{
		return _status;
	}
	
	public String getPositionId()
	{
		return _positionId;
	}
	
	public enum TradeStatus
	{
		READY, OPEN, CLOSED;
	}
	
	private String _positionId;
	protected TradeStatus _status;
	protected final TradeStrategy _tradeStrategy;
	protected final OrderExecutor _orderExecutor;
	private static final Logger _logger = LogManager.getLogger();
}
 