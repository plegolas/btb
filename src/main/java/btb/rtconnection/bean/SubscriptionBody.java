package btb.rtconnection.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Created by Marcelo Giesel on 17-5-17.
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"subscribeTo",
		"unsubscribeFrom"
})
public class SubscriptionBody implements Serializable
{
	
	@JsonProperty("subscribeTo")
	private List<String> subscribeTo = null;
	@JsonProperty("unsubscribeFrom")
	private List<String> unsubscribeFrom = null;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	private final static long serialVersionUID = -8773520415992706798L;
	
	@JsonProperty("subscribeTo")
	public List<String> getSubscribeTo() {
		return subscribeTo;
	}
	
	@JsonProperty("subscribeTo")
	public void setSubscribeTo(List<String> subscribeTo) {
		this.subscribeTo = subscribeTo;
	}
	
	@JsonProperty("unsubscribeFrom")
	public List<String> getUnsubscribeFrom() {
		return unsubscribeFrom;
	}
	
	@JsonProperty("unsubscribeFrom")
	public void setUnsubscribeFrom(List<String> unsubscribeFrom) {
		this.unsubscribeFrom = unsubscribeFrom;
	}
	
	@JsonAnyGetter
	public Map<String, Object> getAdditionalProperties() {
		return this.additionalProperties;
	}
	
	@JsonAnySetter
	public void setAdditionalProperty(String name, Object value) {
		this.additionalProperties.put(name, value);
	}
	
}