package asgn2RollingStock;
import asgn2Exceptions.TrainException;



public class Locomotive extends RollingStock {

	private String classification;
	
	public Locomotive(int grossWeight, String classification)
		throws TrainException{
		super(grossWeight);
		this.classification = classification;
	}
	
	public int power(){
		
		String [] parts = classification.split("");
		int power = Integer.parseInt(classification.replaceAll("[\\D]",""));
		
		power = power*100;
		
		return power;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "Lococmotive - Class: "+classification + ", Weight: " + this.getGrossWeight()
				+ ", Power: "+ power()+ "\n";
	}

}
