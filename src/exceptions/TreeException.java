package exceptions;

/**
 * ******************************************** **
 * TreeException - exceptions.TreeException
 * Handles exceptions by passing it to it's super class
 * @author Christian Garrovillo
 * Information and Communications Technologies
 * Software Development
 * * ********************************************* **
 */
public class TreeException extends Exception {

    /**
     * Handles exceptions by passing it to it's super class
     * @param message the message to be displayed
     */
    public TreeException(String message) {
        super(message);
    }
}
