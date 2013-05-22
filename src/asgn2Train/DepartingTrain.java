package asgn2Train;
import asgn2Exceptions.TrainException;
import asgn2RollingStock.RollingStock;
import asgn2RollingStock.Locomotive;
import asgn2RollingStock.PassengerCar;
import asgn2RollingStock.FreightCar;

import java.util.*;
/**
 * A train is a sequence of carriages. This class defines various 
 * operations that can be performed to prepare a long-distance train for 
 * departure.
 * 
 * We assume that a train can be assembled from any available rolling 
 * stock, including locomotives, passenger cars and freight cars. 
 * However, they may be configured in only a certain sequence:
 * 
 * The first carriage must be a locomotive (and there can be only one 
 * locomotive per train).
 * This may be followed by zero or more passenger cars.
 * These may be followed by zero or more freight cars.
 * Any other configurations of rolling stock are disallowed.
 * 
 * The process of preparing the train for departure occurs in two stages:
 * 
 * 1. The train is assembled from individual carriages. New carriages may 
 * be added to the rear of the train only. (Similarly, carriages may be 
 * removed from the rear of the train only.)
 * 2. Passengers board the train. For safety reasons, no carriage shunting 
 * operations may be performed when any passengers are on board the train.
 * @version 1.0
 * @author 
 *
 */

public class DepartingTrain {
	
	List<RollingStock> train = new ArrayList<RollingStock>();
	List<RollingStock> passengerCars = new ArrayList<RollingStock>();
	List<RollingStock> freightCars = new ArrayList<RollingStock>();
	int numberOfSeats =0;
	
	/**
	 * Constructs a (potential) train object containing no carriages (yet).
	 */
	public DepartingTrain(){
	}
	/**
	 * Adds the given number of people to passenger carriages on the 
	 * train. We do not specify where the passengers must sit, so they 
	 * can be allocated to any vacant seat in any passenger car.
	 * @param newPassengers - the number of people wish to board the train
	 * @return the number of people who were unable to board the train 
	 * because they couldn't get a seat
	 * @throws TrainException - if the number of new passengers is negative
	 */
	public int board (int newPassengers)
		throws TrainException{
		
		int availSeats = numberOfSeats();
		
		availSeats = availSeats - newPassengers;
		
		if(newPassengers < 0)
			throw new TrainException("Number of new Passengers is less than 0\n");
		return availSeats;//pplz unable to get a seat
	}
	/**
	 * Returns the first carriage on the train (which must be a 
	 * locomotive). Special value null is returned if there are no 
	 * carriages on the train at all.
	 * 
	 * NB: When combined with method nextCarriage, this method gives 
	 * us a simple ability to iteratively examine each of the train's 
	 * carriages.
	 * @return the first carriage in the train, or null if there are no carriages
	 */
	public RollingStock firstCarriage(){
		
		if(train.get(0) instanceof Locomotive){
			return train.get(0);
		}
		else
			return null;
	}
	/**
	 * Returns the next carriage in the train after the one returned by 
	 * the immediately preceding call to either this method or method 
	 * firstCarriage. Special value null is returned if there is no such 
	 * carriage. If there has been no preceding call to either firstCarriage 
	 * or nextCarriage, this method behaves like firstCarriage, i.e., it 
	 * returns the first carriage in the train, if any.
	 * 
	 * NB: When combined with method firstCarriage, this method gives us 
	 * a simple ability to iteratively examine each of the train's carriages.
	 * @return the train's next carriage after the one returned by the 
	 * immediately preceding call to either firstCarriage or 
	 * nextCarriage, or null if there is no such carriage
	 */
	public RollingStock nextCarriage(){
		
		if(passengerCars.size() != 0){
			RollingStock p1 = passengerCars.get(0);
			passengerCars.remove(0);
			return p1;
		}
		if(freightCars.size() != 0){
			RollingStock f1 = freightCars.get(0);
			freightCars.remove(0);
			return f1;
		}
		return null;
	}
	
	public int numberOfSeats(){
		
		
		for(int i =1; i < train.size(); i++){
			if(train.get(i) instanceof PassengerCar);
				numberOfSeats +=((PassengerCar)train.get(i)).numberOfSeats();
		}
		return numberOfSeats;
	}
	/**
	 * Returns the total number of passengers currently on the train, 
	 * counting all passenger cars.
	 * @return the number of passengers on the train
	 */
	public int numberOnBoard(){
		
		int numberOnBoard = 0;
		
		for (int i =1; i < train.size();i++){
			if(train.get(i) instanceof PassengerCar)
				numberOnBoard += ((PassengerCar)train.get(i)).numberOnBoard();
		}
		
		return numberOnBoard;
	}
	/**
	 * Adds a new carriage to the end of the train. However, a new 
	 * carriage may be added only if the resulting train configuration 
	 * is valid, as per the rules listed above. Furthermore, shunting 
	 * operations may not be performed if there are passengers on 
	 * the train.
	 * 
	 * Hint: You may find Java's in-built instanceof operator useful 
	 * when implementing this method (and others in this class).
	 * @param newCarriage - the new carriage to be added
	 * @throws TrainException - if adding the new carriage would produce 
	 * an invalid train configuration, or if there are passengers on the 
	 * train
	 */
	public void addCarriage(RollingStock newCarriage)
		throws TrainException{
		
		
		if(train == null && (newCarriage instanceof Locomotive) == false)
			throw new TrainException("First Carriage must be a Locomotive\n");
		else if(numberOnBoard() > 0)
			throw new TrainException("Cannot add Carriages when passengers are onboard!\n");
		else
			train.add(newCarriage);
		
		if(newCarriage instanceof PassengerCar)
			passengerCars.add(newCarriage);
		if(newCarriage instanceof FreightCar)
			freightCars.add(newCarriage);
		
	}
	/**
	 * Removes the last carriage from the train. (This may be the 
	 * locomotive if it is the only item of rolling stock on the train.) 
	 * However, shunting operations may not be performed if there are 
	 * passengers on the train.
	 * @throws TrainException - if there is no rolling stock on the 
	 * "train", or if there are passengers on the train.
	 */
	public void removeCarriage()throws TrainException{
		
		if(train.get(0) == null)
			throw new TrainException("There is no rolling stock to remove\n");
		else if (numberOnBoard() > 0)
			throw new TrainException("Cannot remove Carriage while people are onboard!\n");
		else {
			int trainSize = train.size();
			train.remove(train.remove(trainSize));
		}
	}
	/**
	 * Returns whether or not the train is capable of moving. A train 
	 * can move if its locomotive's pulling power equals or exceeds the 
	 * train's total weight (including the locomotive itself).
	 * 
	 * In the degenerate case of a "train" which doesn't have any 
	 * rolling stock at all yet, the method returns true.
	 * @return
	 */
	public boolean trainCanMove(){
		
		int locoPullingPwr = 0;
		int totalWeight =0;
		
		for(int i=0; i < train.size();i++){
			
			if(train.get(i) instanceof Locomotive)
				locoPullingPwr += ((Locomotive)train.get(i)).power();
			
			totalWeight += train.get(i).getGrossWeight();
		}
		
		if(locoPullingPwr > totalWeight)
			return true;
		else
			return false;
	}
	/**
	 * Returns a human-readable description of the entire train. This 
	 * has the form of a hyphen-separated list of carriages, starting 
	 * with the locomotive on the left. The description is thus a string 
	 * "a-b-...-z", where a is the human-readable description of the 
	 * first carriage (the locomotive), b is the description of the 
	 * second carriage, etc, until the description of the last carriage z. 
	 * (Note that there should be no hyphen after the last carriage.) For 
	 * example, a possible train description may be 
	 * "Loco(6D)-Passenger(13/24)-Passenger(16/16)-Freight(G)".
	 * 
	 * In the degenerate case of a "train" with no carriages, the empty 
	 * string is returned.
	 * 
	 * @return a human-readable description of the entire train
	 */
	public String toString(){
		
		return "I like trains\n";
	}
	
	
	
	
	
	
	
	
}
