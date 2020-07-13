import java.awt.Font;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Main extends JFrame implements ActionListener {
	private final int numElevators = 2/* Makes the number of elevators */, numFloors = 10, numBasements = 3;
	private ArrayList<Elevator> elevators;
	private ArrayList<ElevatorCanvas> canvases;
	private Font bigFont = new Font("DALEK", Font.BOLD, 40); // adjust this size if you want

	// Only very minor changes to the constructor may be necessary.
	public Main() {
		super("Elevator Manager");
		elevators = new ArrayList<>();
		canvases = new ArrayList<>();

		for (int i = 0; i < numElevators; i++) // create the specified # of elevators and GUI objects
		{
			ElevatorCanvas c = new ElevatorCanvas(numFloors, numBasements);
			Elevator e = new Elevator(c);
			elevators.add(e); // adds the elevators
			canvases.add(c); // adds them to the canvas
			// start a thread for each elevator

			Thread t = new Thread(e);
			t.start();
		}
		// adds the buttons
		setSize(1200, 900);
		setLayout(new GridLayout(1, canvases.size() + 1));

		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(numFloors + numBasements, 1));
		for (int i = numFloors - 1; i >= 0; i--)
			buttonPanel.add(new JButton("" + i));
		for (int i = -1; i >= numBasements * -1; i--)
			buttonPanel.add(new JButton("" + i));

		for (int i = 0; i < canvases.size() / 2; i++)
			add(canvases.get(i));

		for (int i = 0; i < buttonPanel.getComponentCount(); i++) {
			buttonPanel.getComponent(i).setFont(bigFont);
			((JButton) (buttonPanel.getComponent(i))).addActionListener(this);
		}

		add(buttonPanel);

		for (int i = canvases.size() / 2; i < canvases.size(); i++)
			add(canvases.get(i));

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
	}

	// The elevator management logic goes here!

	// bestChoice == targetFloor ;

	// This method runs automatically when a button is clicked.
	// The parameter "e" contains information about which button was clicked;
	// the int "targetFloor" reads the text on the button to figure out

	// JButton enters the hold arrayList
	////////// can I make the last int? (first pressed) in the arraylist = the
	// targetFloor? /////
	// make the elevator go to the next floor in the arrayList
	// bestChoice goes to the targetFloor.
	// ^^^ How do I make it go? move?
	// put hold list in elevator, the list is where the elevator should go.
	// assign which elevator bestChoice goes to the floor pressed

	// which floor the user clicked.
	@Override
	public void actionPerformed(ActionEvent e) {
		int targetFloor = Integer.parseInt(((JButton) (e.getSource())).getText());

		// Assign the target floor to an elevator here!

		//bestChoice need to go to the targetFloor.
		
		Elevator bestChoice = elevators.get(0);
		for (int i = 0; i < elevators.size(); i++) {
			Elevator el = elevators.get(i);
			

			if (el.diffrenceFloor == 0 && bestChoice.diffrenceFloor != 0) // not moving
			{
				bestChoice = el;
			//	break;
			}
			else { // ignore the moving elevator

				if (el.Hold.isEmpty() == true && bestChoice.Hold
						.isEmpty() == false) /* if elevator has no value to go to. not if it's moving */
					bestChoice = el;

				else if (Math.abs(targetFloor - el.currentFloor) < Math.abs(targetFloor - bestChoice.currentFloor))
					bestChoice = el; // go to the closest elevator
			}

		}

		bestChoice.Hold.add(targetFloor);
	}

	public static void main(String[] args) {
		new Main();
	}
}
