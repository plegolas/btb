package btb.bean;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
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
		System.out.println( mapper.writeValueAsString( p ) );
		
		Product p2 = mapper.readValue( "{\"securityId\":\"id\",\"symbol\":\"sym\",\"displayName\":\"name\"}", Product.class );
		System.out.println( p2.getDisplayName() );
		System.out.println( p2.getSecurityId() );
		System.out.println( p2.getSymbol() );
//		assertEquals( "Not equals", p, p2 );
	}
	
}