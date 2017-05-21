package btb.order;

import java.io.IOException;
import java.text.DecimalFormat;

import btb.trade.rest.bean.OrderBody;
import btb.bean.Value;
import btb.trade.rest.bean.RestOrderResponse;
import btb.trade.rest.TradeApi;
import btb.trade.rest.TradeApiRestClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Marcelo Giesel on 14-5-17.
 */
public class OrderExecutor
{
	public OrderExecutor()
	{
		_tradeApi = new TradeApiRestClient().getClient();
	}
	
	public RestOrderResponse openLong( String productId ) throws IOException
	{
		Value value = new Value();
		value.setCurrency( "BUX" );
		value.setDecimals( 2 );
		value.setAmount( "200" );
		
		OrderBody orderBody = new OrderBody();
		orderBody.setProductId( productId );
		orderBody.setLeverage( 2 );
		orderBody.setDirection( "BUY" );
		orderBody.setValue( value );
		
		return execute( _tradeApi.openLong( orderBody ) );
	}
	
	public RestOrderResponse close( String positionId ) throws IOException
	{
		return execute( _tradeApi.closeLong( positionId ) );
	}
	
	private <T> T execute( Call<T> call ) throws IOException
	{
		Response<T> response;
		try
		{
			response = call.execute();
		}
		catch( IOException e )
		{
			_logger.error( "Request {} {} resulted in exception:", call.request().method(),
					call.request().url().toString(), e );
			throw e;
		}
		
		if( response.isSuccessful() )
		{
			return response.body();
		}
		else
		{
			String errorMessage = String.format( "Request %s %s resulted in code %d:\n%s", call.request().method(),
					call.request().url().toString(), response.code(), response.errorBody().string() );
			_logger.error( errorMessage );
			throw new IOException( errorMessage );
		}
	}
	
	private final TradeApi _tradeApi;
	private static final Logger _logger = LogManager.getLogger();
}
