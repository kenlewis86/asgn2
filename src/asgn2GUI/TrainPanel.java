package asgn2GUI;

import asgn2Train.DepartingTrain;
import asgn2RollingStock.RollingStock;
import asgn2RollingStock.Locomotive;
import asgn2RollingStock.PassengerCar;
import asgn2RollingStock.FreightCar;
import asgn2Exceptions.TrainException;

import java.io.*;
import javax.swing.*;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;



public class TrainPanel extends JPanel implements ActionListener {
	
	// GUI Components General (TOP)
	private JPanel componentsTop;
	private JTextField passengers;
	private JLabel passengersBoarding;
	private JLabel freight, goods, refrigerated, danger;
	private JTextField transFreightG, transFreightR, transFreightD;
	
	// GUI Components General (BOTTOM)
	private JButton reset;
	private JButton refresh;
	private JButton board;
	private JPanel bottomComponents;
	
	// GUI Components Driver
	private JButton addLocomotive, addPassenger, addFreight, remove;
	private JTextField locoClass, locoWeight;
	private JLabel locoClassLabel,locoWeightLabel, passWeightLabel, passSeatLabel,
					freightWeightLabel,driverErrors, driverInfo, pullingPwr,
					removeCarriage;
	private JTextField passengerWeight, passengerSeats;
	private JTextField freightWeight;
	private JCheckBox freightG, freightR, freightD;
	private JTextArea trainErrors, currentLoads, pwrLevel,toRemove;
	private JPanel driverComponents;
	
	// GUI Components Conductor
	private JPanel conductorComponents;
	private JLabel availSeating, reqSeating, messages, carriageList;
	private JTextArea seatingCapacity;
	private JTextArea seatingRequired;
	private JTextArea seatingMessage;
	private JTextArea passengerCarriageList;

	// GUI Misc Lables
	private JLabel reqOnTrain, trainDriver, conductor;
	
	private DepartingTrain departingTrain;
	
	private int totalTrainWeight = 0;
	
	//Create main components, Initialise frame
	public TrainPanel(){
		
		departingTrain = new DepartingTrain();
		initializeComponents();

	}
	
	//Builds main components of the frame
	//Displays them 
	private void initializeComponents() {
		
		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);

		//Set grid which all other grids will be placed on
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.anchor = GridBagConstraints.CENTER;
		constraints.weightx = 100;
		constraints.weighty = 100;
		constraints.fill = GridBagConstraints.HORIZONTAL;
		
		reqOnTrain = new JLabel ("What is required to go on this Train below");
		addToPanel(reqOnTrain, constraints, 1,0,1,1);
		
		
		// General Components (TOP)
		passengers = new JTextField();
		transFreightG = new JTextField();
		transFreightR = new JTextField();
		transFreightD = new JTextField();
		passengersBoarding = new JLabel("Passengers");
		freight = new JLabel("Freight requiring transportaion");
		goods = new JLabel("Goods");
		refrigerated = new JLabel ("Refrigerated");
		danger = new JLabel ("Dangerous");
		
		componentsTop = new JPanel (new GridBagLayout());
		componentsTop.setBorder(BorderFactory.createEtchedBorder());
		
		
		addToPanel(componentsTop,passengersBoarding, constraints, 0,0,1,1);
		addToPanel(componentsTop,passengers, constraints, 1,0,1,1);
		
		addToPanel(componentsTop,freight, constraints, 0,3,3,1);
		addToPanel(componentsTop,goods, constraints, 0,4,1,1);
		addToPanel(componentsTop,refrigerated, constraints, 1,4,1,1);
		addToPanel(componentsTop,danger, constraints, 2,4,1,1);
		
		
		addToPanel(componentsTop,transFreightG, constraints, 0,4,1,1);
		addToPanel(componentsTop,transFreightR, constraints, 1,4,1,1);
		addToPanel(componentsTop,transFreightD, constraints, 2,4,1,1);
		
		addToPanel(componentsTop, constraints, 0, 1, 4, 1);
		//-----------------------------------------------------------------\\
		
		// Driver Components
		trainDriver = new JLabel ("Train Driver Information Below");
		addToPanel(trainDriver, constraints, 1,2,1,1);
		
		
		driverComponents = new JPanel(new GridBagLayout());
		driverComponents.setBorder(BorderFactory.createEtchedBorder());
		
		addLocomotive = new JButton ("Add Locomotive");
		addLocomotive.addActionListener(this);
		locoClassLabel = new JLabel ("Locomotive Class");
		locoClass = new JTextField();
		locoWeightLabel = new JLabel("Locomotive Weight");
		locoWeight = new JTextField();
		
		
		addToPanel(driverComponents,locoClassLabel, constraints, 0,0,1,1);
		addToPanel(driverComponents,locoWeightLabel, constraints, 0,1,1,1);
		addToPanel(driverComponents,locoClass, constraints, 1,0,1,1);
		addToPanel(driverComponents,locoWeight, constraints, 1,1,1,1);
		addToPanel(driverComponents,addLocomotive, constraints, 0,2,3,1);
		

		passengerWeight = new JTextField();
		passWeightLabel = new JLabel("PassengerCar Weight");
		passengerSeats = new JTextField();
		passSeatLabel = new JLabel ("PassengerCar Seats");
		addPassenger = new JButton("Add PassengerCar");
		addPassenger.addActionListener(this);
		
		addToPanel(driverComponents, passWeightLabel, constraints, 0,3,0,1);
		addToPanel(driverComponents, passSeatLabel, constraints, 0,4,0,1);
		addToPanel(driverComponents, passengerWeight, constraints, 1,3,0,1);		
		addToPanel(driverComponents, passengerSeats, constraints, 1,4,0,1);
		addToPanel(driverComponents,addPassenger, constraints, 0,5,0,1);
		
		
		addFreight = new JButton ("Add FreightCar");
		addFreight.addActionListener(this);
		freightWeightLabel = new JLabel("Freight Weight");
		freightWeight = new JTextField();
		freightG = new JCheckBox();
		freightR = new JCheckBox();
		freightD = new JCheckBox();
		
		addToPanel(driverComponents,freightWeightLabel, constraints, 0,7,0,1);
		addToPanel(driverComponents,goods,constraints, 0,8,0,1);
		addToPanel(driverComponents,refrigerated,constraints, 0,9,0,1);
		addToPanel(driverComponents,danger,constraints, 0,10,0,1);
		addToPanel(driverComponents,freightWeight, constraints, 1,7,0,1 );
		addToPanel(driverComponents,freightG, constraints, 1,8,0,1 );
		addToPanel(driverComponents,freightR, constraints, 1,9,0,1 );
		addToPanel(driverComponents,freightD, constraints, 1,10,0,1 );
		addToPanel(driverComponents,addFreight, constraints, 0,11,0,1);
		
		trainErrors = new JTextArea();
		trainErrors.setEditable(false);
		trainErrors.setLineWrap(true);
		trainErrors.setBorder(BorderFactory.createEtchedBorder());
		driverErrors = new JLabel("Current Load Errors");
		currentLoads = new JTextArea();
		currentLoads.setEditable(false);
		currentLoads.setLineWrap(false);
		currentLoads.setBorder(BorderFactory.createEtchedBorder());
		driverInfo = new JLabel("Train Information");
		
		addToPanel(driverComponents, driverErrors, constraints, 0,13,0,1);
		addToPanel(driverComponents, trainErrors, constraints, 0,14,0,1);
		addToPanel(driverComponents, driverInfo, constraints, 0,15,0,1);
		addToPanel(driverComponents, currentLoads, constraints, 0,16,0,1);
		
		pullingPwr = new JLabel("Pulling Power Details");
		pwrLevel = new JTextArea();
		
		addToPanel(driverComponents, pullingPwr, constraints, 0,17,1,1);
		addToPanel(driverComponents, pwrLevel, constraints, 0,18,1,1);
		
		removeCarriage = new JLabel("Remove Last Carriage");
		remove = new JButton("Remove");
		remove.addActionListener(this);
		toRemove = new JTextArea();
		toRemove.setEditable(false);
		
		addToPanel(driverComponents,removeCarriage, constraints,0,19,1,1);
		addToPanel(driverComponents,toRemove,constraints,0,20,1,1);
		addToPanel(driverComponents, remove, constraints, 0,21,1,1);
		
		
		addToPanel(driverComponents, constraints, 0, 4, 5,1);
		//-----------------------------------------------------------------\\		
		
		
		//Conductor Components
		trainDriver = new JLabel ("Conductor Information Below");
		addToPanel(trainDriver, constraints, 0,5,1,1);
		
		
		availSeating = new JLabel ("Available Seating"); 
		reqSeating = new JLabel ("Required Seating");
		messages = new JLabel ("Conductor Messages"); 
		carriageList = new JLabel ("Current Carrige List");
		seatingCapacity = new JTextArea();
		seatingCapacity.setEditable(false);
		seatingRequired = new JTextArea();
		seatingRequired.setEditable(false);
		seatingMessage = new JTextArea();
		seatingMessage.setEditable(false);
		passengerCarriageList = new JTextArea();
				
		conductorComponents = new JPanel(new GridBagLayout());
		conductorComponents.setBorder(BorderFactory.createEtchedBorder());
		conductorComponents.add(seatingCapacity);
		conductorComponents.add(seatingRequired);
		conductorComponents.add(seatingMessage);		
		conductorComponents.add(passengerCarriageList);
		
		addToPanel(conductorComponents,availSeating, constraints, 0,0,1,1);
		addToPanel(conductorComponents,seatingCapacity, constraints, 0,1,1,1);
		addToPanel(conductorComponents,reqSeating, constraints, 0,2,1,1);
		addToPanel(conductorComponents,seatingRequired, constraints, 0,3,1,1);
		addToPanel(conductorComponents,messages, constraints, 0,4,1,1);
		addToPanel(conductorComponents,seatingMessage, constraints, 0,5,1,1);
		addToPanel(conductorComponents,carriageList, constraints, 0,6,1,1);
		addToPanel(conductorComponents,passengerCarriageList, constraints, 0,7,1,1);
		
		
		addToPanel(conductorComponents, constraints, 0, 6, 1, 1);
		//-----------------------------------------------------------------\\
		
		//General Components (BOTTOM)
		reset = new JButton("Reset");
		reset.addActionListener(this);
		refresh = new JButton("Depart");
		refresh.addActionListener(this);
		board = new JButton("Board Passengers");
		board.addActionListener(this);
		bottomComponents = new JPanel(new GridBagLayout());
		addToPanel(bottomComponents, reset,constraints, 0,0,1,1);
		addToPanel(bottomComponents, refresh,constraints, 1,0,1,1);
		addToPanel(bottomComponents, board, constraints, 2,0,1,1);
		
		addToPanel(bottomComponents, constraints, 0,10,10,1);
		

		repaint();
	}
	//Method taken from assignment 1 for placing items on GridBagLayout
	private void addToPanel(Component c, GridBagConstraints constraints, int x,
			int y, int w, int h) {
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.gridwidth = w;
		constraints.gridheight = h;
		add(c, constraints);
	}
	//As Above
	private void addToPanel(JPanel p,Component c, GridBagConstraints constraints, int x,
			int y, int w, int h) {
		constraints.gridx = x;
		constraints.gridy = y;
		constraints.gridwidth = w;
		constraints.gridheight = h;
		p.add(c, constraints);
	}
	
	//Used to handel button presses! Note: Not working yet!!
	public void actionPerformed(ActionEvent evt) {
		// Get event source
		Object src = evt.getSource();

		// Consider the alternatives - not all active at once.
		if (src == addLocomotive) {
			addCarriage("Locomotive");
		} else if (src == addPassenger) {
			addCarriage("Passenger");
		} else if (src == addFreight) {
			addCarriage("Freight");
		}
		else if (src == reset) {
			//handleRejectButton();
		}
		else if (src == refresh) {
			//handleRejectButton();
		}
	}
	
	//When button pressed to add rolling stock to train
	//This method add carriages to the train and updates display
	public void addCarriage(String rollingStock){
		try {
			if(rollingStock.equals("Locomotive")){
				int weight = Integer.parseInt(locoWeight.getText());
				String locoInfo = locoClass.getText();
				totalTrainWeight += weight;
				departingTrain.addCarriage(new Locomotive(weight,locoInfo));
				updateDriver();
				currentLoads.setText(departingTrain.firstCarriage().toString());
				clearFields();
				
			}
			else if(rollingStock.equals("Passenger")){
				int weight = Integer.parseInt(passengerWeight.getText());
				int seatInfo = Integer.parseInt(passengerSeats.getText());
				totalTrainWeight += weight;
				if (updateDriver() == false){
					throw new TrainException("No Locomotive Present!");
				}
				departingTrain.addCarriage(new PassengerCar(weight,seatInfo));
				updateDriver();
				String carriageInfo = departingTrain.nextCarriage().toString();
				currentLoads.setText(currentLoads.getText()+ carriageInfo);
				updateConductor(carriageInfo);
				clearFields();
				
			}
			else if(rollingStock.equals("Freight")){
				int weight = Integer.parseInt(freightWeight.getText());
				String freightType = "";
				if(freightD.isSelected() == true){
					freightType = "Dangerous";
					clearFields();
				}
				else if (freightR.isSelected() == true){
					freightType = "Refrigerated";
					clearFields();
				}
				else if (freightG.isSelected() == true){
					freightType = "General Goods";
					clearFields();
				}
				clearFields();
				
				totalTrainWeight += weight;
				if (updateDriver() == false){
					throw new TrainException("No Locomotive Present!");
				}
				departingTrain.addCarriage(new FreightCar(weight,freightType));
				updateDriver();
				String carriageInfo = departingTrain.nextCarriage().toString();
				currentLoads.setText(currentLoads.getText() + carriageInfo);
				
			}
		}catch(TrainException e){
			trainErrors.setText(e.getLocalizedMessage());
		}
	}
	
	public void updateConductor(String carriageInfo){
		
			seatingCapacity.setText(""+departingTrain.numberOfSeats());
			passengerCarriageList.setText(passengerCarriageList.getText()+
					carriageInfo);
			if(passengers.getText().equals("") == false){
				int passengersForTrain = Integer.parseInt(passengers.getText());
				seatingRequired.setText(""+(departingTrain.numberOfSeats()- passengersForTrain));
			}
		
	}
	
	public Boolean updateDriver(){
		
		try{
			RollingStock l1 = departingTrain.firstCarriage();
			int pwr = ((Locomotive) l1).power();
			trainErrors.setText("");
			pwrLevel.setText("Total Train Weight: " + totalTrainWeight + "\n"
							+ "Pulling Power: " + pwr + "\n" + "Power Available: "
							+ (pwr - totalTrainWeight));
			if(departingTrain.trainCanMove() == false)
				trainErrors.setText("Locomotive cannot pull that weight!");
			
		}catch (Exception e){
			trainErrors.setText("No Locomotive Present!");
			return false;
		}
		return true;
	}
	
	public void clearFields(){
		
		locoWeight.setText("");
		locoClass.setText("");
		passengerWeight.setText("");
		passengerSeats.setText("");
		freightD.setSelected(false);
		freightG.setSelected(false);
		freightR.setSelected(false);
		freightWeight.setText("");
	}
}
