package QueuesLab;

import java.util.Scanner;

/** 
 * Simulate the check-in process of an airline.
 */
public class AirlineCheckinSim {
	// Data Fields
	
	/** 
	 * Queue of frequent flyers.
	 */
	private PassengerQueue frequentFlyerQueue = new PassengerQueue("Frequent Flyer");
	/** 
	 * Queue of regular passengers. 
	 */
	private PassengerQueue regularPassengerQueue = new PassengerQueue("Regular Passenger");
	/** 
	 * Maximum number of frequent flyers to be served before a regular passenger gets served. 
	 */
	private int frequentFlyerMax;
	/** 
	 * Total simulated time. 
	 */
	private int totalTime;
	/** 
	 * If set true, print additional output. 
	 */
	private boolean showAll = false;
	/** 
	 * Simulated clock. 
	 */
	private int clock = 0;
	/** 
	 * Time that the agent will be done with the current passenger.
	 */
	private int timeDone;
	/** 
	 * Number of frequent flyers served since the last regular passenger was served.
	 */
	private int frequentFlyersSinceRP;
	
	Scanner scanner = new Scanner(System.in);
	
	public void runSimulation() {
		for (clock = 0; clock < totalTime; clock++) {
			frequentFlyerQueue.checkNewArrival(clock, showAll);
			regularPassengerQueue.checkNewArrival(clock, showAll);
			if (clock >= timeDone) {
				startServe();
			}
		}
	}
	
	private void startServe() {
		if (!frequentFlyerQueue.isEmpty() && ((frequentFlyersSinceRP <= frequentFlyerMax) || regularPassengerQueue.isEmpty())) {	// Serve the next frequent flyer.
			frequentFlyersSinceRP++;
			timeDone = frequentFlyerQueue.update(clock, showAll);
		} else if (!regularPassengerQueue.isEmpty()) {
			// Serve the next regular passenger.
			frequentFlyersSinceRP = 0;
			timeDone = regularPassengerQueue.update(clock, showAll);
		} else if (showAll) {
			System.out.println("Time is " + clock + " server is idle");
		}
	}
	
	/** 
	 * Method to show the statistics. 
	 */
	public void showStats() {
		System.out.println("\nThe number of regular passengers served was " + regularPassengerQueue.getNumServed());
		double averageWaitingTime = (double) regularPassengerQueue.getTotalWait() / (double) regularPassengerQueue.getNumServed();
		System.out.println(" with an average waiting time of " + averageWaitingTime);
		System.out.println("The number of frequent flyers served was " + frequentFlyerQueue.getNumServed());
        averageWaitingTime = (double) frequentFlyerQueue.getTotalWait() / (double) frequentFlyerQueue.getNumServed();
		System.out.println("Passengers in frequent flyer queue: " + frequentFlyerQueue.size());
		System.out.println("Passengers in regular passenger queue: " + regularPassengerQueue.size());
	}
	
	public void enterData() {
		System.out.println("Frequent flyer arrivals every hour: ");
        frequentFlyerQueue.setArrivalRate(scanner.nextDouble()/60);
        System.out.println("Regular passenger arrivals every hour: ");
        regularPassengerQueue.setArrivalRate(scanner.nextDouble()/60);
        System.out.println("Max number of frequent flyers between regular passengers: ");
        frequentFlyerMax = scanner.nextInt();
        System.out.println("Max service time in minutes: ");
        Passenger.setMaxProcessingTime(scanner.nextInt());
        System.out.println("Simulation time in minutes: ");
        totalTime = scanner.nextInt();
        System.out.println("Display minute-by-minute trace of simulation? (y/n)"); 
        showAll = (scanner.next().equals("y"));
	}
}