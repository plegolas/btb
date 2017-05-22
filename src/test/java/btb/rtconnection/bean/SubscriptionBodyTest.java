package btb.rtconnection.bean;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Created by Marcelo Giesel on 22-5-17.
 */
public class SubscriptionBodyTest
{

	@Test
	public void parseTest() throws IOException
	{
		String expected = "{  \n" +
				"   \"subscribeTo\":[  \n" +
				"      \"trading.product.sb26493\",\n" +
				"      \"trading.product.sb26496\",\n" +
				"      \"trading.product.sb26502\"\n" +
				"   ],\n" +
				"   \"unsubscribeFrom\":[  \n" +
				"      \"trading.product.sb26493\",\n" +
				"      \"trading.product.sb26496\",\n" +
				"      \"trading.product.sb26502\"\n" +
				"   ]\n" +
				"}";
		
		SubscriptionBody body = new SubscriptionBody();
	
		List<String> productIds = new LinkedList<>();
		productIds.add( "sb26493" );
		productIds.add( "sb26496" );
		productIds.add( "sb26502" );
		
		body.setSubscribeTo( productIds );
		body.setUnsubscribeFrom( productIds );
		
		ObjectMapper mapper = new ObjectMapper();
		
		JsonNode expectedJson = mapper.readTree( expected );
		JsonNode parsedJson =  mapper.valueToTree( body );
		assertEquals( "Wrong parsing.", expectedJson, parsedJson  );
	}
}