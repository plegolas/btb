package btb.order;

import btb.trade.rest.bean.RestOrderResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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
		_logger.debug( "Received open response with body:\n{}", new ObjectMapper().writeValueAsString( response ) );
		
		String positionId = response.getPositionId();
		assertNotNull( "Received null positionId from open trade request.", positionId );
		
		_logger.info( "Open trade with position id {}. Make sure it is closed if this test fails after this message!", positionId );
		
		response = orderExecutor.close( positionId );
		_logger.debug( "Received close response with body:\n{}", new ObjectMapper().writeValueAsString( response ) );
		
		assertEquals( "productId received from close trade request differs from expected.",
				productId, response.getProduct().getSecurityId() );
		assertEquals( "Received response is not of CLOSE type.", response.getType(), "CLOSE" );
		
		_logger.info( "Trade with position id {} is closed.", positionId );
	}
	
	private static final Logger _logger = LogManager.getLogger();
}