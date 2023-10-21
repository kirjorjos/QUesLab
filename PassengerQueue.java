package QueuesLab;

import java.util.LinkedList;
import java.util.Queue;

/** 
 * Class to simulate a queue of passengers. 
 */
public class PassengerQueue {
	// Data Fields
	
	/** 
	 * The queue of passengers. 
	 */
	private Queue<Passenger> theQueue;
	/** 
	 * The number of passengers served. 
	 */
	private int numServed;
	/** 
	 * The total time passengers were waiting. 
	 */
	private int totalWait;
	/** 
	 * The name of this queue. 
	 */
	private String queueName;
	/** 
	 * The average arrival rate. 
	 */
	private double arrivalRate;
	
	// Constructor
	/** 
	 * Construct a PassengerQueue with the given name.
	 * @param queueName The name of this queue
	 */
	public PassengerQueue(String queueName) {
		setNumServed(0);
		setTotalWait(0);
		this.queueName = queueName;
		theQueue = new LinkedList<Passenger>();
	}
	
	/** Check if a new arrival has occurred.
	@param clock The current simulated time
	@param showAll Flag to indicate that detailed data should be output
	*/
	public void checkNewArrival(int clock, boolean showAll) {
		if (Math.random() < arrivalRate) {
			theQueue.add(new Passenger(clock));
			if (showAll) {
				System.out.println("Time is " + clock + ": " + queueName + " arrival, new queue size is " + theQueue.size());
			}
		}
	}
	
	/** Update statistics.
	 * pre: The queue is not empty.
	@param clock The current simulated time
	@param showAll Flag to indicate whether to show detail
	@return Time passenger is done being served
	*/
	public int update(int clock, boolean showAll) {
		Passenger nextPassenger = theQueue.remove();
		int timeStamp = nextPassenger.getArrivalTime();
		int wait = clock - timeStamp;
		setTotalWait(getTotalWait() + wait);
		setNumServed(getNumServed() + 1);
		if (showAll) {
			System.out.println("Time is " + clock + ": Serving " + queueName + " with time stamp " + timeStamp);
		}
		return clock + nextPassenger.getProcessingTime();
	}
	
	/**
	 * @return If the queue is empty or not
	 */
	public boolean isEmpty() {
		return theQueue.isEmpty();
	}

	/**
	 * @return the numServed
	 */
	public int getNumServed() {
		return numServed;
	}

	/**
	 * @param numServed the numServed to set
	 */
	public void setNumServed(int numServed) {
		this.numServed = numServed;
	}

	/**
	 * @return the totalWait
	 */
	public int getTotalWait() {
		return totalWait;
	}

	/**
	 * @param totalWait the totalWait to set
	 */
	public void setTotalWait(int totalWait) {
		this.totalWait = totalWait;
	}
	
	/**
	 * @return The size or the queue
	 */
	public int size() {
		return theQueue.size();
	}
	
	public void setArrivalRate(double arrivalRate) {
		this.arrivalRate = arrivalRate;
	}
}