package btb.bean;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Created by Marcelo Giesel on 14-5-17.
 */
public class ProductTest
{
	@Test
	public void productParseTest() throws IOException
	{
		Product p = new Product();
		p.setDisplayName( "name" );
		p.setSecurityId( "id" );
		p.setSymbol( "sym" );
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode product = mapper.valueToTree( p );
		JsonNode expectedProduct = mapper.readTree( "{\"securityId\":\"id\",\"symbol\":\"sym\",\"displayName\":\"name\"}" );
		assertEquals( "Product values differ", expectedProduct, product );
		System.out.println( product );
	}
	
}