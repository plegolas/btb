package btb.trade.rest;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Marcelo Giesel on 15-5-17.
 */
public class TradeApiRestClient
{
	public TradeApiRestClient()
	{
		OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
		
		_retrofit = new Retrofit.Builder()
				.baseUrl( _BASE_URL )
				.addConverterFactory( JacksonConverterFactory.create() )
				.client( httpClient.build() )
				.build();
	}
	
	public TradeApi getClient()
	{
		if( _client == null )
		{
			_client = _retrofit.create( TradeApi.class );
		}
		return _client;
	}
	
	private final String _BASE_URL = "https://api.beta.getbux.com/";
	private final Retrofit _retrofit;
	private TradeApi _client;
}
