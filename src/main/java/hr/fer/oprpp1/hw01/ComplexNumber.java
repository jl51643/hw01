package hr.fer.oprpp1.hw01;


import java.util.regex.*;
import static java.lang.Math.*;

/**
 * Model of complex number
 */
public class ComplexNumber {


    /**
     * Real part of complex number.
     */
    private double real;

    /**
     * Imaginary part of complex number.
     */
    private double imaginary;

    /**
     * Constructing new complex number with real and imaginary part.
     *
     * @param real real part of complex number.
     * @param imaginary imaginary part of complex number.
     */
    public ComplexNumber(double real, double imaginary){
        this.real = real;
        this.imaginary = imaginary;
    }

    /**
     * Creates new complex number laying on real axis.
     *
     * @param real real part of complex number.
     * @return returns new complex number laying on real axis.
     */
    public static ComplexNumber fromReal(double real) {
        return new ComplexNumber(real, 0);
    }

    /**
     * Creates new complex number laying on imaginary axis.
     *
     * @param imaginary imaginary part of number.
     * @return returns new complex number laying on imaginary axis.
     */
    public static ComplexNumber fromImaginary(double imaginary) {
        return new ComplexNumber(0, imaginary);
    }

    /**
     * Creates new complex number from given magnitude and angle.
     *
     * @param magnitude distance from the origin in the complex plane.
     * @param angle angle between real axis and this complex number.
     * @return returns
     */
    public static ComplexNumber fromMagnitudeAndAngle(double magnitude, double angle) {
        /*angle is in range from 0 to 2 PI*/
        angle %= (2 * PI);

        double imaginary, real;
        if (abs(angle - PI) <= 1E-10) {
            real = cos(angle) * magnitude;
            imaginary = 0;
        } else if (abs(angle - (PI / 2)) <= 1E-10 || abs(angle - (3 * PI / 2)) <= 1E-10) {
            real = 0;
            imaginary = sin(angle) * magnitude;
        } else {
            real = cos(angle) * magnitude;
            imaginary = sin(angle) * magnitude;
        }
        return new ComplexNumber(real, imaginary);
    }

    /**
     * Parses given string to complex number
     *
     * @param s string to parse into complex number.
     * @return returns new complex number from given string.
     * @throws IllegalArgumentException if given string can not be parsed into complex number.
     */
    public static ComplexNumber parse(String s) {

        String[] number = s.split("");
        String real = "", imaginary = "";
        int i = number.length-1;
        /*start reading number from behind
        * and if it ends with "i" that is imaginary part*/
        if (number[i].equals("i")) {
            /*skip "i"*/
            i--;
            /*read numbers into imaginary part of complex number*/
            imaginary = determineNumber(number, i);
            /*moves i for each digit added into imaginary od plus or minus sign*/
            i -= imaginary.length();
            /*read numbers into real part of complex number*/
            real = determineNumber(number, i);
            i -= real.length();

            if (real.equals(""))
                real = "0.0";

            if (imaginary.equals("") || imaginary.equals("+"))
                imaginary = "1.0";

            if (imaginary.equals("-"))
                imaginary = "-1.0";

            if (i >= 0)
                throw new IllegalArgumentException("Can not turn string " + s + " into complex number");

            return new ComplexNumber(Double.parseDouble(real), Double.parseDouble(imaginary));
        } else {

            real = determineNumber(number, i);
            i -= real.length();

            if (i >= 0)
                throw new IllegalArgumentException("Can not turn string " + s + " into complex number");

            return new ComplexNumber(Double.parseDouble(real), 0.0);
        }
    }

    /**
     * Returns longest substring of consecutive digits in given array starting from given index towards beginning of array
     *
     * @param string array to find substring of consecutive digits
     * @param index index to start searching substring
     * @return returns longest substring of consecutive digits in given array starting from given index towards beginning of array
     */
    private static String readNumbers(String[] string, int index) {
        String number = "";
        while (index >= 0 && isNumber(string[index])) {
            number = string[index--] + number;
        }
        return number;
    }

    /**
     * Returns string representation of last number in given array starting from given index towards beginning of array
     *
     * @param string array to find number
     * @param index index to start searching number
     * @return returns string representation of last number in given array starting from given index towards beginning of array
     */
    private static String determineNumber(String[] string, int index) {
        String number = "";
        String tmp = readNumbers(string, index);
        number = tmp + number;
        index -= tmp.length();
        /*if there is more elements in array and current element is "." and i we already have any digit saved into number and if next element is digit*/
        if (index > 0 && string[index].equals(".") && !number.equals("") && isNumber(string[index-1])) {
            number = string[index--] + number;
        }
        tmp = readNumbers(string, index);
        number = tmp + number;
        index -= tmp.length();
        if (index >= 0 && (string[index].equals("-") || string[index].equals("+"))) {
            number = string[index--] + number;
        }
        return number;
    }

    /**
     * Returns true only if given string can be parsed into integer.
     *
     * @param s string to check if can be parsed into number.
     * @return returns true only if given string can be parsed into integer.
     */
    private static boolean isNumber(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }


    /**
     * Return real part of complex number.
     *
     * @return returns real part of complex number.
     */
    public double getReal() {
        return this.real;
    }

    /**
     * Returns imaginary part of complex number
     *
     * @return returns imaginary part of complex number
     */
    public double getImaginary() {
        return this.imaginary;
    }

    /**
     * Returns magnitude of complex number.
     *
     * @return returns magnitude of complex number.
     */
    public double getMagnitude() {
        return sqrt(real*real + imaginary*imaginary);
    }

    /**
     * Returns angle of complex number.
     *
     * @return returns angle of complex number.
     */
    public double getAngle() {
        double angle = atan(this.imaginary/ this.real);
        if (angle < 0)
            angle += (2 * PI);
        return angle;
    }

    /**
     * Returns new complex number computed by adding two complex numbers.
     *
     * @param c second summand in addition.
     * @return new complex number computed by adding two complex numbers.
     */
    public ComplexNumber add(ComplexNumber c) {
        return new ComplexNumber(this.real + c.real, this.imaginary + c.imaginary);
    }

    /**
     * Returns new complex number computed by subtraction two complex numbers.
     *
     * @param c subtrahend in subtraction.
     * @return returns new complex number computed by subtraction two complex numbers.
     */
    public ComplexNumber sub(ComplexNumber c) {
        return new ComplexNumber(this.real - c.real, this.imaginary - c.imaginary);
    }

    /**
     * Returns new complex number computed by multiplying two complex numbers.
     *
     * @param c second factor in multiplication.
     * @return returns new complex number computed by multiplying two complex numbers.
     */
    public ComplexNumber mul(ComplexNumber c) {
        return new ComplexNumber(this.real * c.real - (this.imaginary * c.imaginary),
                        this.real * c.imaginary + this.imaginary * c.real);
    }

    /**
     * Returns new complex number computed by dividing two complex numbers.
     *
     * @param c divisor in division.
     * @return returns new complex number computed by multiplying two complex numbers.
     */
    public ComplexNumber div(ComplexNumber c) {
        ComplexNumber conjugateOfC = new ComplexNumber(c.real, -c.imaginary);

        ComplexNumber dividend = this.mul(conjugateOfC);
        ComplexNumber divisor = c.mul(conjugateOfC);

        return new ComplexNumber(dividend.real / divisor.real, dividend.imaginary / divisor.real);
    }

    /**
     * Returns new complex number computed as n-th power of this complex number.
     *
     * @param n exponent
     * @return returns new complex number computed as n-th power of this complex number.
     */
    public ComplexNumber power(int n) {
        if (n < 0)
            throw new IllegalArgumentException("Can not calculate power of negative number!");
        if (n == 0)
            return fromReal(1);
        else
            return fromMagnitudeAndAngle( Math.pow(this.getMagnitude(), n), this.getAngle() * n);
    }

    /** Returns array of n-th roots of given complex number.
     *
     * @param n n-th root
     * @return returns array of n-th roots of given complex number.
     */
    public ComplexNumber[] root(int n) {
        if (n <= 0)
            throw new IllegalArgumentException("Can not calculate root of not natural number!");
        ComplexNumber[] roots = new ComplexNumber[n];
        for (int i = 0; i < n; i++) {
            roots[i] = fromMagnitudeAndAngle(Math.pow(this.getMagnitude(), 1.0/n), (this.getAngle() + 2 * i * PI) / n);
        }

        return roots;
    }

    /**
     * Returns string representation of given complex number.
     *
     * @return returns string representation of given complex number.
     */
    public String toString(){
        if (real != 0.0 && imaginary != 0.0) {
            if (this.imaginary > 0)
                return Double.toString(this.real) + "+" + Double.toString(this.imaginary) + "i";
            else {
                return Double.toString(this.real) + Double.toString(this.imaginary) + "i";
            }
        }
        if (abs(imaginary - 0.0) <= 1E-10)
            return Double.toString(this.real);
        if (abs(real - 0.0) <= 1E-10)
            return this.imaginary + "i";
        return null;
    }
}
