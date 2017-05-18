package btb.trade;

import java.io.IOException;

import btb.order.OrderExecutor;

/**
 * Created by Marcelo Giesel on 14-5-17.
 */
public abstract class Trade
{
	public Trade( TradeStrategy tradeStrategy )
	{
		_tradeStrategy = tradeStrategy;
		_status = TradeStatus.READY;
		_orderExecutor = new OrderExecutor();
		_positionId = "";
	}
	
	protected abstract boolean isOpenPrice( float price );
	
	protected abstract boolean isClosePrice( float price );
	
	public void open() throws IOException
	{
		_positionId = openTrade( _tradeStrategy.getProductId() );
		_status = TradeStatus.OPEN;
	}
	
	protected abstract String openTrade( String productId ) throws IOException;
	
	public void close() throws IOException
	{
		_orderExecutor.close( _positionId );
		_status = TradeStatus.CLOSED;
	}
	
	
	public TradeStatus getStatus()
	{
		return _status;
	}
	
	public enum TradeStatus
	{
		READY, OPEN, CLOSED;
	}
	
	private String _positionId;
	protected TradeStatus _status;
	protected final TradeStrategy _tradeStrategy;
	protected final OrderExecutor _orderExecutor;
}
 