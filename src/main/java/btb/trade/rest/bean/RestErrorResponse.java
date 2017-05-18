package btb.trade.rest.bean;

/**
 * Created by Marcelo Giesel on 15-5-17.
 */
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"message",
		"developerMessage",
		"errorCode"
})
public class RestErrorResponse implements Serializable
{
	
	@JsonProperty("message")
	private String message;
	@JsonProperty("developerMessage")
	private String developerMessage;
	@JsonProperty("errorCode")
	private String errorCode;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	private final static long serialVersionUID = 8834730302623264828L;
	
	@JsonProperty("message")
	public String getMessage() {
		return message;
	}
	
	@JsonProperty("message")
	public void setMessage(String message) {
		this.message = message;
	}
	
	@JsonProperty("developerMessage")
	public String getDeveloperMessage() {
		return developerMessage;
	}
	
	@JsonProperty("developerMessage")
	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}
	
	@JsonProperty("errorCode")
	public String getErrorCode() {
		return errorCode;
	}
	
	@JsonProperty("errorCode")
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
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