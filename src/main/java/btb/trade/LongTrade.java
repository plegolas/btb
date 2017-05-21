package btb.trade;

import java.io.IOException;

import btb.order.OrderExecutor;

/**
 * Created by Marcelo Giesel on 14-5-17.
 */
public class LongTrade extends Trade
{
	public LongTrade( TradeStrategy tradeStrategy, OrderExecutor orderExecutor )
	{
		super( tradeStrategy, orderExecutor );
	}
	
	protected boolean isOpenPrice( float price )
	{
		if( price <= _tradeStrategy.getOpenPrice() )
		{
			return true;
		}
		return false;
	}
	
	protected boolean isClosePrice( float price )
	{
		if( price >= _tradeStrategy.getClosePriceHigh() || price <= _tradeStrategy.getClosePriceLow() )
		{
			return true;
		}
		return false;
	}
	
	protected String openTrade( String productId ) throws IOException
	{
		return _orderExecutor.openLong( productId ).getPositionId();
	}
}
