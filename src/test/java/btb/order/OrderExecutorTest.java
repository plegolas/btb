package btb.order;

import java.io.IOException;

import btb.trade.rest.bean.RestOrderResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Created by Marcelo Giesel on 22-5-17.
 */
public class OrderExecutorTest
{
	@Test
	public void openAndCloseLong() throws Exception
	{
		String productId = "sb26502";
		OrderExecutor orderExecutor = new OrderExecutor();
		RestOrderResponse response = orderExecutor.openLong( productId );
		_logger.debug( "Received open response with body:\n{}", _mapper.writeValueAsString( response ) );
		
		String positionId = response.getPositionId();
		assertNotNull( "Received null positionId from open trade request.", positionId );
		
		_logger.info( "Open trade with position id {}. Make sure it is closed if this test fails after this message!", positionId );
		
		response = orderExecutor.close( positionId );
		_logger.debug( "Received close response with body:\n{}", _mapper.writeValueAsString( response ) );
		
		assertEquals( "productId received from close trade request differs from expected.",
				productId, response.getProduct().getSecurityId() );
		assertEquals( "Received response is not of CLOSE type.", response.getType(), "CLOSE" );
		
		_logger.info( "Trade with position id {} is closed.", positionId );
	}
	
	@Ignore( "Used to close eventually open positions" )
	@Test
	public void closePosition() throws IOException
	{
		String positionId = "1905d2db-f6bc-46d8-8da5-477278c6362c";
		
		OrderExecutor orderExecutor = new OrderExecutor();
		RestOrderResponse response = orderExecutor.close( positionId );
		_logger.debug( "Received close response with body:\n{}", _mapper.writeValueAsString( response ) );
	}
	
	private static final ObjectMapper _mapper = new ObjectMapper();
	private static final Logger _logger = LogManager.getLogger();
}