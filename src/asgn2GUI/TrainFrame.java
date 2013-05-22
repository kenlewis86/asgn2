package asgn2GUI;

import java.awt.Dimension;
import javax.swing.JFrame;

/**
 * This class is the outermost container for the game's GUI components.
 * 
 * @author INB370
 */
@SuppressWarnings("serial") // We don't care about binary i/o here
public class TrainFrame extends JFrame {

	// Display constants
	protected static final int WIDTH = 1080;
	protected static final int HEIGHT = 920;
	protected static final Dimension PREFSIZE = new Dimension(WIDTH, HEIGHT);

	/**
	 * Create the TrainFrame object and fill the frame with the game panel
	 */
	public TrainFrame() {
		// Initialize the Frame and add the GamePanel
		setTitle("Train Departure Application");
		setSize(PREFSIZE);

		this.getContentPane().add(new TrainPanel());
		repaint();
	}
}
