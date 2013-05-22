package asgn2Exceptions;

/**
 * A simple class for exceptions thrown by railway shunting and boarding operations
 * 
 * @author Ken Lewis
 * @author n5279615
 * @version 1.0
 */
@SuppressWarnings("serial") // We don't care about binary i/o here
public class TrainException extends Exception {

	/**
	 * Creates a new instance of TrainException.
	 * 
	 * @param msg An informative message about the problem found.
	 */
	public TrainException(String msg) {
		super("Train Exception: " + msg);
	}

}
