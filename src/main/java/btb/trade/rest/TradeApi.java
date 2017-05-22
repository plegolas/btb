package btb.trade.rest;

import java.io.IOException;

import btb.trade.rest.bean.OrderBody;
import btb.trade.rest.bean.RestOrderResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Marcelo Giesel on 14-5-17.
 */
public interface TradeApi
{
	@Headers({
			"Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJyZWZyZXNoYWJsZSI6ZmFsc2UsInN1YiI6IjJlMDcxNjllLTNlMTMtNGFjOS04M2JkLTFiN2I5ZTYwZGM2OSIsImF1ZCI6ImJldGEuZ2V0YnV4LmNvbSIsInNjcCI6WyJhcHA6bG9naW4iLCJydGY6bG9naW4iXSwibmJmIjoxNDczOTQyMzk0LCJleHAiOjE1MDU0NzgzOTQsImlhdCI6MTQ3Mzk0MjM5NCwianRpIjoiMzgyNTVlNmMtMTU0MC00OGJkLWIxMjUtZWVkMmYyZjRjZDdlIiwiY2lkIjoiODQ3MzYyMjkzNCJ9.1r55eMkL7s2kBMio9KqHqS7NYHdBMT7LAJOFc2U08Lg",
			"Accept-Language: nl-NL,en;q=0.8",
			"Content-Type: application/json",
			"Accept: application/json"
	})
	@POST( "core/16/users/me/trades" )
	Call<RestOrderResponse> openLong( @Body OrderBody order ) throws IOException;
	
	
	@Headers({
			"Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJyZWZyZXNoYWJsZSI6ZmFsc2UsInN1YiI6IjJlMDcxNjllLTNlMTMtNGFjOS04M2JkLTFiN2I5ZTYwZGM2OSIsImF1ZCI6ImJldGEuZ2V0YnV4LmNvbSIsInNjcCI6WyJhcHA6bG9naW4iLCJydGY6bG9naW4iXSwibmJmIjoxNDczOTQyMzk0LCJleHAiOjE1MDU0NzgzOTQsImlhdCI6MTQ3Mzk0MjM5NCwianRpIjoiMzgyNTVlNmMtMTU0MC00OGJkLWIxMjUtZWVkMmYyZjRjZDdlIiwiY2lkIjoiODQ3MzYyMjkzNCJ9.1r55eMkL7s2kBMio9KqHqS7NYHdBMT7LAJOFc2U08Lg",
			"Accept-Language: nl-NL,en;q=0.8",
			"Content-Type: application/json",
			"Accept: application/json"
	})
	@DELETE( "core/16/users/me/portfolio/positions/{positionId}" )
	Call<RestOrderResponse> closeLong( @Path( "positionId" ) String positionId ) throws IOException;
}
