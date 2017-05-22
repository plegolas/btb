package btb.trade.bean;

/**
 * Created by Marcelo Giesel on 14-5-17.
 */
public class TradeQuote
{
	private String securityId;
	private Float currentPrice;
	
	public String getSecurityId()
	{
		return securityId;
	}
	
	public void setSecurityId( String securityId )
	{
		this.securityId = securityId;
	}
	
	public Float getCurrentPrice()
	{
		return currentPrice;
	}
	
	public void setCurrentPrice( Float currentPrice )
	{
		this.currentPrice = currentPrice;
	}
	
}
