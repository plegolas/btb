package btb.trade.rest.bean;

/**
 * Created by Marcelo Giesel on 14-5-17.
 */
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import btb.bean.Value;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"productId",
		"investingAmount",
		"leverage",
		"direction"
})
public class OrderBody implements Serializable
{
	
	@JsonProperty("productId")
	private String productId;
	@JsonProperty("investingAmount")
	private Value investingAmount;
	@JsonProperty("leverage")
	private Integer leverage;
	@JsonProperty("direction")
	private String direction;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	private final static long serialVersionUID = 6300640063483005433L;
	
	@JsonProperty("productId")
	public String getProductId() {
		return productId;
	}
	
	@JsonProperty("productId")
	public void setProductId(String productId) {
		this.productId = productId;
	}
	
	@JsonProperty("investingAmount")
	public Value getInvestingAmount() {
		return investingAmount;
	}
	
	@JsonProperty("investingAmount")
	public void setInvestingAmount(Value investingAmount ) {
		this.investingAmount = investingAmount;
	}
	
	@JsonProperty("leverage")
	public Integer getLeverage() {
		return leverage;
	}
	
	@JsonProperty("leverage")
	public void setLeverage(Integer leverage) {
		this.leverage = leverage;
	}
	
	@JsonProperty("direction")
	public String getDirection() {
		return direction;
	}
	
	@JsonProperty("direction")
	public void setDirection(String direction) {
		this.direction = direction;
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