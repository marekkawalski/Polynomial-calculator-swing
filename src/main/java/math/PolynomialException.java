package math;

/**
 * Class which handles all exceptions connected with calculating.
 *
 * @author Marek Kawalski
 * @version 1.2
 */
public class PolynomialException extends Exception {

    /**
     * Exception class constructor
     *
     * @param message display message
     */
    public PolynomialException(String message) {
        super(message);
    }
}
