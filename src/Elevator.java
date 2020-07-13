import java.util.ArrayList;

public class Elevator implements Runnable // each elevator knows how to be an elevator.
{
	final int SPEED = 1000; // how quickly should the elevator update its position? - moving toward it's
							// destination one floor at a time
	int currentFloor = 0; // where is the elevator now? --Hint: I'll have to change this
	int diffrenceFloor = 0;
//	boolean IdleElevator  = Hold.get(0) == currentFloor; 
	public ArrayList<Integer> Hold = new ArrayList<>();
	// make a destination and then if it's moving, hold the other destination
	// inputs.
	ElevatorCanvas canvas;

	public Elevator(ElevatorCanvas c) {
		canvas = c;
	}

	@Override
	public void run() {
		// Describe an elevator's behavior here!
		// here, you put the stop/ start info

		// put the hold array in the elevator class

		// is current floor = to destination?
		// if no, move toward the destination. check again - loop
		// if yes, look at the <hold arraylist> and move toward the next hold

		// find elevator best choice - if e (elavtor) isn't == to destination (nothing
		// is pressed)
		// and there is no floor in the hold array. now its the bestChoice which is the
		// targetfloor.
		// looking for the idle elevator

		// update current floor,
		// subtract destination and currentfloor.
		// destination = currentFloor, take destination off of the hold list
		// whatever difference is smallest == bestChoice
		while (true) {
			if (Hold.size() > 0) {
				diffrenceFloor = Hold.get(0) - currentFloor;

				if (diffrenceFloor < 0)
					currentFloor = currentFloor - 1;
				// if Hold.get(0) = currentFloor , remove from the arrayList
				// if differenceFloor
				if (diffrenceFloor > 0)
					currentFloor = currentFloor + 1;
			
				// if one elevator is idle, make it go to hold.get(0).
				// then ignore the - moving elevator - else 
					
				if (currentFloor == Hold.get(0))
				{
					Hold.remove(0);
					diffrenceFloor = 0; 

				}
				System.out.println(Hold);
			
			}
			canvas.setFloor(currentFloor);
			canvas.repaint();

			try {
				Thread.sleep(SPEED); // pause for a bit to simulate movement
			} 
			catch (Exception e) {
			}
		}

	}
}