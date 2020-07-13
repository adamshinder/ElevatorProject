import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;

public class ElevatorCanvas extends Canvas
{
	private int numFloors = 10, numBasements = 3, currFloor = 0;
	
	public ElevatorCanvas(int n, int b)  // the two elevator columns
	{
		numFloors = n-1;  // numbers the floors
	}
	
	public void paint(Graphics g)
	{
		g.setColor(Color.GREEN); // makes the color of the elevator shaft
		g.fillRect(0, 0, getWidth(), getHeight()); // makes the squares
		
		int rectHeight = getHeight() / (numFloors+numBasements); 
		
		
		int yPos = (currFloor - numFloors)*( (getHeight() - rectHeight)/(-numBasements - numFloors) ) + 0;
//		System.out.println("Calculated yPos "+yPos+" for floor "+currFloor);
		
		
		g.setColor(Color.BLUE); // makes the color of the elevators
		g.fillRect(0, yPos, getWidth(), rectHeight);
	}
	
	public void setFloor(int c)
	{
		currFloor = c;// where to draw the elevator
	}
}