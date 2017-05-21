package btb.trade.rest.bean;

/**
 * Created by Marcelo Giesel on 14-5-17.
 */

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import btb.bean.Product;
import btb.bean.Value;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
		"id",
		"positionId",
		"profitAndLoss",
		"product",
		"value",
		"price",
		"leverage",
		"direction",
		"type",
		"dateCreated"
})
public class RestOrderResponse implements Serializable
{
	
	@JsonProperty("id")
	private String id;
	@JsonProperty("positionId")
	private String positionId;
	@JsonProperty("profitAndLoss")
	private Value profitAndLoss;
	@JsonProperty("product")
	private Product product;
	@JsonProperty("value")
	private Value value;
	@JsonProperty("price")
	private Value price;
	@JsonProperty("leverage")
	private Integer leverage;
	@JsonProperty("direction")
	private String direction;
	@JsonProperty("type")
	private String type;
	@JsonProperty("dateCreated")
	private Long dateCreated;
	@JsonIgnore
	private Map<String, Object> additionalProperties = new HashMap<String, Object>();
	private final static long serialVersionUID = 1550716975084603194L;
	
	@JsonProperty("id")
	public String getId() {
		return id;
	}
	
	@JsonProperty("id")
	public void setId(String id) {
		this.id = id;
	}
	
	@JsonProperty("positionId")
	public String getPositionId() {
		return positionId;
	}
	
	@JsonProperty("positionId")
	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}
	
	@JsonProperty("profitAndLoss")
	public Value getProfitAndLoss() {
		return profitAndLoss;
	}
	
	@JsonProperty("profitAndLoss")
	public void setProfitAndLoss(Value profitAndLoss) {
		this.profitAndLoss = profitAndLoss;
	}
	
	@JsonProperty("product")
	public Product getProduct() {
		return product;
	}
	
	@JsonProperty("product")
	public void setProduct(Product product) {
		this.product = product;
	}
	
	@JsonProperty("value")
	public Value getValue() {
		return value;
	}
	
	@JsonProperty("value")
	public void setValue(Value value ) {
		this.value = value;
	}
	
	@JsonProperty("price")
	public Value getPrice() {
		return price;
	}
	
	@JsonProperty("price")
	public void setPrice(Value price) {
		this.price = price;
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
	
	@JsonProperty("type")
	public String getType() {
		return type;
	}
	
	@JsonProperty("type")
	public void setType(String type) {
		this.type = type;
	}
	
	@JsonProperty("dateCreated")
	public Long getDateCreated() {
		return dateCreated;
	}
	
	@JsonProperty("dateCreated")
	public void setDateCreated(Long dateCreated) {
		this.dateCreated = dateCreated;
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