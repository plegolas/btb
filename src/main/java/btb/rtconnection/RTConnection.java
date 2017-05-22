package btb.rtconnection;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import btb.rtconnection.bean.SubscriptionBody;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.neovisionaries.ws.client.WebSocket;
import com.neovisionaries.ws.client.WebSocketAdapter;
import com.neovisionaries.ws.client.WebSocketException;
import com.neovisionaries.ws.client.WebSocketFactory;

/**
 * Created by Marcelo Giesel on 14-5-17.
 */
public class RTConnection
{
	public RTConnection() throws IOException
	{
		_mapper = new ObjectMapper();
		
		_webSocket = new WebSocketFactory().createSocket("https://rtf.beta.getbux.com/subscriptions/me");
		_webSocket.addHeader( "Accept-Language", "nl-NL,en;q=0.8" );
		_webSocket.addHeader( "Authorization", "Bearer eyJhbGciOiJIUzI1NiJ9.eyJyZWZyZXNoYWJsZSI6ZmFsc2UsInN1YiI6IjJlMDcxNjllLTNlMTMtNGFjOS04M2JkLTFiN2I5ZTYwZGM2OSIsImF1ZCI6ImJldGEuZ2V0YnV4LmNvbSIsInNjcCI6WyJhcHA6bG9naW4iLCJydGY6bG9naW4iXSwibmJmIjoxNDczOTQyMzk0LCJleHAiOjE1MDU0NzgzOTQsImlhdCI6MTQ3Mzk0MjM5NCwianRpIjoiMzgyNTVlNmMtMTU0MC00OGJkLWIxMjUtZWVkMmYyZjRjZDdlIiwiY2lkIjoiODQ3MzYyMjkzNCJ9.1r55eMkL7s2kBMio9KqHqS7NYHdBMT7LAJOFc2U08Lg" );
		_webSocket.addListener( new WebSocketAdapter()
			{
				public void onTextMessage( WebSocket websocket, String message )
				{
					notifyListener( message );
				}
			});
		
		_listeners = new LinkedList<>();
	}
	
	public void addListener( MessageUpdateListener listener )
	{
		_listeners.add( listener );
	}
	
	public void removeListener( MessageUpdateListener listener )
	{
		_listeners.remove( listener );
	}
	
	private void notifyListener( String message )
	{
		_listeners.forEach( l -> l.onMessageUpdate( message ) );
	}
	
	public void connect() throws WebSocketException
	{
		_webSocket.connect();
	}
	
	public void disconnect()
	{
		_webSocket.disconnect();
	}
	
	public void subscribe( String productId ) throws JsonProcessingException
	{
		SubscriptionBody bodyMessage = new SubscriptionBody();
		List<String> subscribeTo = new LinkedList<>();
		subscribeTo.add( productId );
		bodyMessage.setSubscribeTo( subscribeTo );
		
		String jsonMessage = _mapper.writeValueAsString( bodyMessage );
		
		_webSocket.sendText( jsonMessage );
	}
	
	public void unsubscribe( String productId ) throws JsonProcessingException
	{
		SubscriptionBody bodyMessage = new SubscriptionBody();
		List<String> unsubscribeFrom = new LinkedList<>();
		unsubscribeFrom.add( productId );
		bodyMessage.setUnsubscribeFrom( unsubscribeFrom );
		
		String jsonMessage = _mapper.writeValueAsString( bodyMessage );
		
		_webSocket.sendText( jsonMessage );
	}
	
	private final ObjectMapper _mapper;
	private final WebSocket _webSocket;
	private List<MessageUpdateListener> _listeners;
}
