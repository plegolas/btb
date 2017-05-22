package btb.rtconnection.bean;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.util.StdConverter;

/**
 * Created by Marcelo Giesel on 22-5-17.
 */
public class TradingProductIdConverter extends StdConverter<List<String>, List<String>>
{
	@Override
	public List<String> convert( List<String> productIds )
	{
		return productIds.stream()
				.map( id -> String.format( "trading.product.%s", id ) )
				.collect( Collectors.toList() );
	}
}
