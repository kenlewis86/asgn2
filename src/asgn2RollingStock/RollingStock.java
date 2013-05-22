package asgn2RollingStock;
import asgn2Exceptions.TrainException;

public abstract class RollingStock {

	private int grossWeight;
	
	public RollingStock(int grossWeight) throws TrainException{
		this.grossWeight = grossWeight;
	}
	
	public int getGrossWeight(){
		return this.grossWeight;
	}
	public abstract String toString();
}
