package asgn2RollingStock;
import asgn2Exceptions.TrainException;

public class FreightCar extends RollingStock {

	
	private String goodsType;
	
	
	public FreightCar(int grossWeight, String goodsType)
	throws TrainException{
		super(grossWeight);
		this.goodsType = goodsType;
	}
	
	public String goodsType(){
		return goodsType;
	}
	
	public String toString(){
		return "FreightCar - Weight: " + this.getGrossWeight() + 
			", GoodsType: " + goodsType + "\n";
	}
}
