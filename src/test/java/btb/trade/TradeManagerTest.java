package btb.trade;

import java.io.IOException;

import btb.order.OrderExecutor;
import btb.trade.rest.bean.RestOrderResponse;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;

/**
 * Created by Marcelo Giesel on 19-5-17.
 */
public class TradeManagerTest
{
	
	@Test
	public void parseTest() throws IOException
	{
		ObjectMapper mapper = new ObjectMapper(  );
		JsonNode jsonMessage = mapper.readTree( _openPriceMessage );
		JsonNode messageType = jsonMessage.get( "t" );
		
		assertTrue( "Parsing message type",
				messageType != null && messageType.isTextual()
				&& messageType.textValue().equalsIgnoreCase( "trading.quote" ) );
	
		String messageBody = jsonMessage.get( "body" ).toString();
		TradeQuote quote = mapper.readValue( messageBody, TradeQuote.class );
		assertEquals(  "Product ID mismatch", _productId, quote.getSecurityId() );
		assertEquals( "Price mismatch", (Float) _openPrice, quote.getCurrentPrice() );
	}
	
	@Test
	public void closeOnLowPrice() throws IOException
	{
		Trade trade = getTestTrade();
		
		TradeManager tradeManager = new TradeManager( trade );
		assertEquals( "Wrong trade status", Trade.TradeStatus.READY, tradeManager.getTradeStatus() );
		
		tradeManager.onMessageUpdate( _closeHighPriceMessage );
		assertEquals( "Wrong trade status", Trade.TradeStatus.READY, tradeManager.getTradeStatus() );
		
		tradeManager.onMessageUpdate( _openPriceMessage );
		assertEquals( "Wrong trade status", Trade.TradeStatus.OPEN, tradeManager.getTradeStatus() );
		
		assertEquals( "Wrong position id", _positionId, tradeManager.getTrade().getPositionId() );
		
		tradeManager.onMessageUpdate( _closeLowPriceMessage );
		assertEquals( "Wrong trade status", Trade.TradeStatus.CLOSED, tradeManager.getTradeStatus() );
	}
	
	@Test
	public void closeOnHighPrice() throws IOException
	{
		Trade trade = getTestTrade();
		
		TradeManager tradeManager = new TradeManager( trade );
		assertEquals( "Wrong trade status", Trade.TradeStatus.READY, tradeManager.getTradeStatus() );
		
		tradeManager.onMessageUpdate( _openPriceMessage );
		assertEquals( "Wrong trade status", Trade.TradeStatus.OPEN, tradeManager.getTradeStatus() );
		
		tradeManager.onMessageUpdate( _closeHighPriceMessage );
		assertEquals( "Wrong trade status", Trade.TradeStatus.CLOSED, tradeManager.getTradeStatus() );
	}
	
	@Test
	public void openWithPriceLowerThanOpenPrice() throws IOException
	{
		Trade trade = getTestTrade();
		
		TradeManager tradeManager = new TradeManager( trade );
		assertEquals( "Wrong trade status", Trade.TradeStatus.READY, tradeManager.getTradeStatus() );
		
		tradeManager.onMessageUpdate( _closeLowPriceMessage );
		assertEquals( "Wrong trade status", Trade.TradeStatus.OPEN, tradeManager.getTradeStatus() );
	}
	
	@Test
	public void otherProductMessages() throws IOException
	{
		Trade trade = getTestTrade();
		
		TradeManager tradeManager = new TradeManager( trade );
		assertEquals( "Wrong trade status", Trade.TradeStatus.READY, tradeManager.getTradeStatus() );
		
		tradeManager.onMessageUpdate( _otherProductCloseLowPriceMessage );
		assertEquals( "Wrong trade status", Trade.TradeStatus.READY, tradeManager.getTradeStatus() );
		
		tradeManager.onMessageUpdate( _openPriceMessage );
		assertEquals( "Wrong trade status", Trade.TradeStatus.OPEN, tradeManager.getTradeStatus() );
		
		tradeManager.onMessageUpdate( _otherProductCloseLowPriceMessage );
		assertEquals( "Wrong trade status", Trade.TradeStatus.OPEN, tradeManager.getTradeStatus() );
		
		tradeManager.onMessageUpdate( _closeHighPriceMessage );
		assertEquals( "Wrong trade status", Trade.TradeStatus.CLOSED, tradeManager.getTradeStatus() );
		
		tradeManager.onMessageUpdate( _otherProductCloseLowPriceMessage );
		assertEquals( "Wrong trade status", Trade.TradeStatus.CLOSED, tradeManager.getTradeStatus() );
	}
	
	private Trade getTestTrade() throws IOException
	{
		RestOrderResponse mockOrderResponse = new RestOrderResponse();
		mockOrderResponse.setPositionId( _positionId );
		
		OrderExecutor mockOrderExecutor = Mockito.mock( OrderExecutor.class );
		Mockito.when( mockOrderExecutor.openLong( Matchers.anyString() ) ).thenReturn( mockOrderResponse );
		Mockito.when( mockOrderExecutor.close( Matchers.anyString() ) ).thenReturn( mockOrderResponse );
		
		TradeStrategy tradeStrategy = new TradeStrategy( _productId, _openPrice, _closePriceHigh, _closePriceLow );
		return new LongTrade( tradeStrategy, mockOrderExecutor );
	}
	
	private static final String _productId = "sd99999";
	private static final float _openPrice = 10;
	private static final float _closePriceHigh = 12;
	private static final float _closePriceLow = 8;
	private static final String _positionId = "position-id";
	
	private static final String _openPriceMessage = "{\"t\":\"trading.quote\",\"body\":{\"securityId\":\"sd99999\",\"currentPrice\":\"10.0\"}}";
	private static final String _closeHighPriceMessage = "{\"t\":\"trading.quote\",\"body\":{\"securityId\":\"sd99999\",\"currentPrice\":\"12.0\"}}";
	private static final String _closeLowPriceMessage = "{\"t\":\"trading.quote\",\"body\":{\"securityId\":\"sd99999\",\"currentPrice\":\"7.0\"}}";
	private static final String _otherProductCloseLowPriceMessage = "{\"t\":\"trading.quote\",\"body\":{\"securityId\":\"sd88888\",\"currentPrice\":\"7.0\"}}";
	
}