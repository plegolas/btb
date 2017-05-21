package btb.trade.rest.bean;

import java.io.IOException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Created by Marcelo Giesel on 21-5-17.
 */
public class RestOrderResponseTest
{

	@Test
	public void parseOpenPosition() throws IOException
	{
		String openResponse = "{\n" +
				"\"id\": \"98922f1a-4c10-4635-a9e6-ae19ddcd12b4\",\n" +
				"\"positionId\": \"4c58a0b2-ea78-46a0-ac21-5a8c22d527dc\",\n" +
				"\"product\": {\n" +
				"\"securityId\": \"sd99999\",\n" +
				"\"symbol\": \"someSymbol\",\n" +
				"\"displayName\": \"someName\"\n" +
				"},\n" +
				"\"investingAmount\": {\n" +
				"\"currency\": \"BUX\",\n" +
				"\"decimals\": 2,\n" +
				"\"amount\": \"200.00\"\n" +
				"},\n" +
				"\"price\": {\n" +
				"\"currency\": \"EUR\",\n" +
				"\"decimals\": 3,\n" +
				"\"amount\": \"0.567\"\n" +
				"},\n" +
				"\"leverage\": 1,\n" +
				"\"direction\": \"BUY\",\n" +
				"\"type\": \"OPEN\",\n" +
				"\"dateCreated\": 1405515165705\n" +
				"}";
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode exceptedJsonResponse = mapper.readTree( openResponse );
		
		RestOrderResponse orderResponse = mapper.readValue( openResponse, RestOrderResponse.class );
		JsonNode jsonResponse = mapper.valueToTree( orderResponse );
		assertEquals( "Wrong parsing", exceptedJsonResponse, jsonResponse );
		System.out.println( jsonResponse );
		
	}
	
	@Test
	public void parseClosePosition() throws IOException
	{
		String closeResponse = "{\n" +
				"\"id\": \"ebd37d2b-8489-4419-bd78-8df7dc6a4823\",\n" +
				"\"positionId\": \"423ac625-bd9a-41eb-8531-d5ea25352020\",\n" +
				"\"profitAndLoss\": {\n" +
				"\"currency\": \"BUX\",\n" +
				"\"decimals\": 2,\n" +
				"\"amount\": \"-0.61\"\n" +
				"},\n" +
				"\"product\": {\n" +
				"\"securityId\": \"sb26493\",\n" +
				"\"symbol\": \"GERMANY30\",\n" +
				"\"displayName\": \"Germany 30\"\n" +
				"},\n" +
				"\"investingAmount\": {\n" +
				"\"currency\": \"BUX\",\n" +
				"\"decimals\": 2,\n" +
				"\"amount\": \"200.00\"\n" +
				"},\n" +
				"\"price\": {\n" +
				"\"currency\": \"EUR\",\n" +
				"\"decimals\": 1,\n" +
				"\"amount\": \"10342.5\"\n" +
				"},\n" +
				"\"leverage\": 2,\n" +
				"\"direction\": \"SELL\",\n" +
				"\"type\": \"CLOSE\",\n" +
				"\"dateCreated\": 1473946766125\n" +
				"}";
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode exceptedJsonResponse = mapper.readTree( closeResponse );
		
		RestOrderResponse orderResponse = mapper.readValue( closeResponse, RestOrderResponse.class );
		JsonNode jsonResponse = mapper.valueToTree( orderResponse );
		assertEquals( "Wrong parsing", exceptedJsonResponse, jsonResponse );
		System.out.println( jsonResponse );
	}
}