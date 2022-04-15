
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import math.PolynomialException;
import math.PolynomialMath;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Class which tests functionalities from PolynomialMath
 *
 * @author Marek Kawalski
 * @version 2.0
 */
public class PolynomialMathTest {

    /**
     * instance of PolynomialMath class
     */
    private PolynomialMath polynomialMath;
    /**
     * An array of programs arguments.
     */
    private List<Integer> args;
    /**
     * An array of factors.
     */
    private List<Integer> listOfFactors;

    /**
     * Method which creates new PolynomialMath object before each test.
     */
    @BeforeEach
    public void setUp() {
        polynomialMath = new PolynomialMath();
    }

    /**
     * Method which tests if results of polynomial value calculations are
     * correct
     *
     * @param list list of arguments which is used to calculations
     * @param expectedOutput expected value of calculation
     */
    @ParameterizedTest
    @MethodSource("integerArrayListProvider")
    public void testCalculatingPolynomialsShouldGenerateExpectedResults(List<Integer> list, int expectedOutput) {
        try {
            int outcome = polynomialMath.calculatePolynomialValue(list);
            assertEquals(outcome, expectedOutput, "Polynomial value wasn't calculated properly.");

        } catch (PolynomialException ex) {
            fail("Polynomial value calculation fails!");
        }
    }

    /**
     * static method which returns stream of arguments- an array of data and
     * expected result
     *
     * @return stream of arguments
     */
    static Stream<Arguments> integerArrayListProvider() {
        return Stream.of(
                arguments(Arrays.asList(1, 2), 1),
                arguments(Arrays.asList(1, 2, 3), 5),
                arguments(Arrays.asList(1, 2, 3, 4), 27),
                arguments(Arrays.asList(-1, 2), -1),
                arguments(Arrays.asList(-1, -2, 3), -5),
                arguments(Arrays.asList(-1, 2, 3, -4), -21)
        );
    }

    /**
     * Method which tests if results of polynomial derivative calculations are
     * correct
     *
     * @param list list of arguments which is used to calculations
     * @param expectedOutputList an array of expected factors
     */
    @ParameterizedTest
    @MethodSource("expectedOutputListProvider")
    public void testCalculatingPolynomialFirstDerivativeShouldGenerateExpectedResults(List<Integer> list, List<Integer> expectedOutputList) {
        try {
            List<Integer> outcome = polynomialMath.calculatePolynomialFirstDerivative(list);
            assertEquals(outcome, expectedOutputList, "Polynomial factors weren't calculated properly.");

        } catch (PolynomialException ex) {
            fail("Polynomial factors calculation fails!");
        }
    }

    /**
     * static method which returns stream of arguments- an array of data and
     * expected result array
     *
     * @return stream of arguments
     */
    static Stream<Arguments> expectedOutputListProvider() {
        return Stream.of(
                arguments(Arrays.asList(1, 2), Arrays.asList(0, 2)),
                arguments(Arrays.asList(1, 2, 3), Arrays.asList(1, 3)),
                arguments(Arrays.asList(1, 2, 3, 4), Arrays.asList(2, 2, 4)),
                arguments(Arrays.asList(-1, -2), Arrays.asList(0, -2)),
                arguments(Arrays.asList(-1, -2, 3), Arrays.asList(-1, 3)),
                arguments(Arrays.asList(-1, -2, -3, 4), Arrays.asList(-2, -2, 4))
        );
    }

    /**
     * Method which tests if results of polynomial first derivative value
     * calculations are correct
     *
     * @param listOfFactors list of arguments which is used to calculations
     * @param expectedOutput expected value of calculation
     */
    @ParameterizedTest
    @MethodSource("integerArrayListProviderForFirstDerivative")
    public void testCalculatingPolynomialFirstDerivativeValueShouldGenerateExpectedResults(List<Integer> listOfFactors, int expectedOutput) {
        try {
            int outcome = polynomialMath.calculatePolynomialFirstDerivativeValue(listOfFactors);
            assertEquals(outcome, expectedOutput, "Polynomial first derivative value wasn't calculated properly.");

        } catch (PolynomialException ex) {
            fail("Polynomial first derivative value calculation fails!");
        }
    }

    /**
     * static method which returns stream of arguments- an array of data and
     * expected result
     *
     * @return stream of arguments
     */
    static Stream<Arguments> integerArrayListProviderForFirstDerivative() {
        return Stream.of(
                arguments(Arrays.asList(1, 4), 1),
                arguments(Arrays.asList(2, 2, 4), 10),
                arguments(Arrays.asList(3, 4, 3, 5), 98),
                arguments(Arrays.asList(-2, -2, 4), -10)
        );
    }

    /**
     * Method which tests if input only consists of zeros. If it does an
     * exception should be thrown
     */
    @Test
    public void allNumbersZerosExceptionWhenAllNumbersAreZeroShouldThrowIncorrectInputException() {
        args = new ArrayList<>();
        args.add(0);
        args.add(0);
        args.add(0);
        PolynomialException exception = assertThrows(
                PolynomialException.class,
                () -> polynomialMath.calculatePolynomialValue(args));
        assertEquals("All arguments are zeros!\n", exception.getMessage(), "Wrong error communicate calculating polynomial value!");

        exception = assertThrows(
                PolynomialException.class,
                () -> polynomialMath.calculatePolynomialFirstDerivative(args));
        assertEquals("All arguments are zeros!\n", exception.getMessage(), "Wrong error communicate when calculating first derivative!");

        exception = assertThrows(
                PolynomialException.class,
                () -> polynomialMath.calculatePolynomialFirstDerivativeValue(args));
        assertEquals("All arguments are zeros!\n", exception.getMessage(), "Wrong error communicate when calculating first derivative value!");
    }

    /**
     * Method which tests if input is null. If it does an exception should be
     * thrown
     */
    @Test
    public void listIsNullExceptionWhenListIsNullShouldThrowThatArrayIsNull() {
        args = null;
        PolynomialException exception = assertThrows(
                PolynomialException.class,
                () -> polynomialMath.calculatePolynomialValue(args));
        assertEquals("Array is null!\n", exception.getMessage(), "Wrong error communicate calculating polynomial value!");

        exception = assertThrows(
                PolynomialException.class,
                () -> polynomialMath.calculatePolynomialFirstDerivative(args));
        assertEquals("Array is null!\n", exception.getMessage(), "Wrong error communicate when calculating first derivative!");

        exception = assertThrows(
                PolynomialException.class,
                () -> polynomialMath.calculatePolynomialFirstDerivativeValue(args));
        assertEquals("Array is null!\n", exception.getMessage(), "Wrong error communicate when calculating first derivative value!");
    }

    /**
     * Method which tests if number of factors in factors array is less equal
     * than 2. If it is an exception should be thrown.
     *
     * @param candidate an integer value which is added to factor array for
     * which the test is performed
     */
    @ParameterizedTest
    @ValueSource(ints = {2, 100, -2, 0})
    public void listOfFactorConsistsOfLessThan2ArgumentShouldThrowExceptionWhenNumberOfFactorIsLessThan2(int candidate) {
        listOfFactors = new ArrayList<>();
        listOfFactors.add(candidate);
        PolynomialException exception = assertThrows(
                PolynomialException.class,
                () -> polynomialMath.calculatePolynomialFirstDerivativeValue(listOfFactors));
        assertEquals("Number of factors is incorrect!\n", exception.getMessage(), "Wrong error communicate calculating polynomial first derivative value!");
    }
}
