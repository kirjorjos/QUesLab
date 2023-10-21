package QueuesLab;

public class AirlineCheckinSimTester {
	/** Main method.
	@param args Not used
	*/
	public static void main(String args[]) {
		AirlineCheckinSim sim = new AirlineCheckinSim();
		sim.enterData();
		sim.runSimulation();
		sim.showStats();
		System.exit(0);
	}
}
