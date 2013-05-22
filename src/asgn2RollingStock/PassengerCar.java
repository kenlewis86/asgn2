package asgn2RollingStock;
import asgn2Exceptions.TrainException;

public class PassengerCar extends RollingStock {

	private int numberOfSeats;
	private int onBoard = 0;
	
	public PassengerCar(int grossWeight, int numberOfSeats)
		throws TrainException{
		
		super(grossWeight);
		this.numberOfSeats = numberOfSeats;
	}
	
	public int board(int newPassengers)
		throws TrainException{
		
		int board = numberOfSeats - newPassengers;
		
		if(newPassengers < 0)
			throw new TrainException("Number of new passengers is negative");
		else {
			onBoard = onBoard + newPassengers;
			return board;
		}
	}
	
	public void alight(int departingPassengers)
		throws TrainException{
		
		if(departingPassengers < 0)
			throw new TrainException("Departing Passengers is less than 0!\n");
		else if(departingPassengers > numberOnBoard())
			throw new TrainException("There are more passengers departing then on the car\n");
		else
			onBoard = onBoard - departingPassengers;
		
	}
	
	public int numberOnBoard(){
		
		return onBoard;
	}
	
	public int numberOfSeats(){
		
		return this.numberOfSeats;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "PassengerCar - Weight: "+ this.getGrossWeight()+ ", Seats: " + numberOfSeats + "\n";
	}

}
