package math;

import java.util.*;

/**
 * Class which handles all calculations connected with calculating polynomial
 * values.
 *
 * @author Marek Kawalski
 * @version 2.2
 */
public class PolynomialMath {

    /**
     * Specification of the lambda expression with three parameters
     */
    interface DoubleMath {

        double operation(int a, int b, int c);
    }

    /**
     * lambda expression as a parameter
     *
     * @param a first lambda parameter
     * @param b first lambda parameter
     * @param c first lambda parameter
     * @param op lambda expression
     * @return result of operation
     */
    double lambdaOperatation(int a, int b, int c, DoubleMath op) {
        return op.operation(a, b, c);
    }

    /**
     * Method is responsible for checking if arguments are zeros or args is
     * nulls.
     *
     * @param args an array of arguments which are used to calculate output
     * value
     * @throws PolynomialException exception is thrown when all arguments are
     * zeros or when args is null
     */
    private void checkIfCalculationPossible(List<Integer> args) throws PolynomialException {
        if (args == null) {
            throw new PolynomialException("Array is null!\n");
        }
        if (checkIfArgumentsAreZeros(args)) {
            throw new PolynomialException("All arguments are zeros!\n");
        }
    }

    /**
     * Method is responsible for calculating polynomial values- f(x0) and
     * f'(x0). It's invoked in calculatePolynomialFirstDerivativeValue method
     *
     * @param args array which value is to be calculated
     * @return returns calculated value
     * @throws PolynomialException method should throw an exception when array
     * is null or all parameters are zero
     */
    public int calculatePolynomialValue(List<Integer> args) throws PolynomialException {
        checkIfCalculationPossible(args);
        int calculatedValue = 0;
        int length = args.size();
        int degree = length - 2;
        //ax
        if (length == 2) {
            return args.get(0);
        } //ax+b, ax^2+bx+c, ...
        else {
            DoubleMath valueCalculation = (a, b, index) -> (Math.pow(args.get(a - 1), b - index) * args.get(index));
            int i = 0;
            while (i <= degree) {
                calculatedValue += lambdaOperatation(length, degree, i, valueCalculation);
                i++;
            }
        }
        return calculatedValue;
    }

    /**
     * Method calculates polynomial first derivative
     *
     * @param args an array of arguments from which derivative should be
     * calculated
     * @return list of factors (numbers by which variable (x) is multiplied
     * @throws PolynomialException should throw an exception when array is null
     * or all arguments are zeros
     */
    public List<Integer> calculatePolynomialFirstDerivative(List<Integer> args) throws PolynomialException {
        checkIfCalculationPossible(args);
        int length = args.size();
        int degree = length - 2;
        List<Integer> listOfFactors = new ArrayList<>();
        //ax
        if (length == 2) {
            listOfFactors.add(0);
        } //ax+b, ax^2+bx+c, ...
        else {
            int xValue;
            for (int i = 0; i < length - 2; i++) {
                xValue = (degree - i) * args.get(i);
                //x = degree - i - 1;
                listOfFactors.add(xValue);
            }
        }
        listOfFactors.add(args.get(args.size() - 1));
        return listOfFactors;
    }

    /**
     * Method is responsible for calculating polynomial first derivative value-
     * f'(x0)
     *
     * @param listOfFactors list in which there are factors (numbers next to by
     * which variable x is multiplied)
     * @return value of first derivative- f'(x0)
     * @throws PolynomialException exception should be thrown when list is null
     * or amount of factors is less equal than 1.
     */
    public int calculatePolynomialFirstDerivativeValue(List<Integer> listOfFactors) throws PolynomialException {
        if (listOfFactors == null) {
            throw new PolynomialException("Array is null!\n");
        }
        if (listOfFactors.size() < 2) {
            throw new PolynomialException("Number of factors is incorrect!\n");
        }
        return calculatePolynomialValue(listOfFactors);
    }

    /**
     * Method checks whether all programs arguments were zero.
     *
     * @param args an array of programs arguments
     * @return true if all values were zero, false if something else
     */
    private boolean checkIfArgumentsAreZeros(List<Integer> args) {
        return args
                .stream()
                .noneMatch(argument -> (argument != 0));
    }
}
