package btb.bean;

/**
 * Created by Marcelo Giesel on 14-5-17.
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
		"securityId",
		"symbol",
		"displayName"
})
public class Product implements Serializable
{
	
	@JsonProperty("securityId")
	private String securityId;
	@JsonProperty("symbol")
	private String symbol;
	@JsonProperty("displayName")
	private String displayName;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	private final static long serialVersionUID = 6162587210073214463L;
	
	@JsonProperty("securityId")
	public String getSecurityId() {
		return securityId;
	}
	
	@JsonProperty("securityId")
	public void setSecurityId(String securityId) {
		this.securityId = securityId;
	}
	
	@JsonProperty("symbol")
	public String getSymbol() {
		return symbol;
	}
	
	@JsonProperty("symbol")
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	
	@JsonProperty("displayName")
	public String getDisplayName() {
		return displayName;
	}
	
	@JsonProperty("displayName")
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
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