package btb.trade;

import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Created by Marcelo Giesel on 19-5-17.
 */
public class TradeManagerTest
{
	
	@Test
	public void parseTes() throws IOException
	{
		String message = "{\n" +
				"\"t\": \"trading.quote\",\n" +
				"\"body\": {\n" +
				"\"securityId\": \"sd999999\",\n" +
				"\"currentPrice\": \"10692.3\"\n" +
				"}\n" +
				"}";
		
		ObjectMapper mapper = new ObjectMapper(  );
		JsonNode jsonMessage = mapper.readTree( message );
		JsonNode messageType = jsonMessage.get( "t" );
		
		assertTrue( "Parsing message type",
				messageType != null && messageType.isTextual()
				&& messageType.textValue().equalsIgnoreCase( "trading.quote" ) );
	
		String messageBody = jsonMessage.get( "body" ).toString();
		TradeQuote quote = mapper.readValue( messageBody, TradeQuote.class );
		assertEquals(  "Product ID mismatch", "sd999999", quote.getSecurityId() );
		assertEquals( "Price mismatch", new Float(10692.3), quote.getCurrentPrice() );
	}
	
}