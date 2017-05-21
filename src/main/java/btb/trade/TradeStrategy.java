package btb.trade;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Marcelo Giesel on 14-5-17.
 */
public class TradeStrategy
{
	public TradeStrategy( String productId, float openPrice, float closePriceHigh, float closePriceLow )
	{
		setProductId( productId );
		_openPrice = openPrice;
		_closePriceHigh = closePriceHigh;
		setClosePriceLow( closePriceLow );
	}
	
	private void setProductId( String productId )
	{
		if( ! productId.matches( "[a-z]{2}[0-9]{5}" ) )
		{
			_logger.fatal( "ProductId is in the wrong format." );
			System.exit( 1 );
		}
		_productId = productId;
	}
	
	private void setClosePriceLow( float closePriceLow )
	{
		if( closePriceLow > _closePriceHigh )
		{
			_logger.fatal( "Lower close price is higher than Higher close price." );
			System.exit( 1 );
		}
		_closePriceLow = closePriceLow;
	}
	
	public float getOpenPrice()
	{
		return _openPrice;
	}
	
	public float getClosePriceHigh()
	{
		return _closePriceHigh;
	}
	
	public float getClosePriceLow()
	{
		return _closePriceLow;
	}
	
	public String getProductId()
	{
		return _productId;
	}
	
	private String _productId;
	private float _openPrice;
	private float _closePriceHigh;
	private float _closePriceLow;
	private final static Logger _logger = LogManager.getLogger();
}
