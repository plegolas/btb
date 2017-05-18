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
		"currency",
		"decimals",
		"amount"
})
public class Value implements Serializable
{
	
	@JsonProperty("currency")
	private String currency;
	@JsonProperty("decimals")
	private Integer decimals;
	@JsonProperty("amount")
	private String amount;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	private final static long serialVersionUID = -3759398923407216074L;
	
	@JsonProperty("currency")
	public String getCurrency() {
		return currency;
	}
	
	@JsonProperty("currency")
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	
	@JsonProperty("decimals")
	public Integer getDecimals() {
		return decimals;
	}
	
	@JsonProperty("decimals")
	public void setDecimals(Integer decimals) {
		this.decimals = decimals;
	}
	
	@JsonProperty("amount")
	public String getAmount() {
		return amount;
	}
	
	@JsonProperty("amount")
	public void setAmount(String amount) {
		this.amount = amount;
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