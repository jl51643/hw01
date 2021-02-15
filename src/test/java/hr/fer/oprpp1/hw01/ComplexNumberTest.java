package hr.fer.oprpp1.hw01;

import org.junit.jupiter.api.Test;

import static hr.fer.oprpp1.hw01.ComplexNumber.*;
import static org.junit.jupiter.api.Assertions.*;

public class ComplexNumberTest {

    @Test
    public void testComplexNumberConstructor() {
        ComplexNumber c = new ComplexNumber(2.3, 5.4);
        assertEquals(2.3, c.getReal());
        assertEquals(5.4, c.getImaginary());
    }

    @Test
    public void testFromReal() {
        ComplexNumber c = fromReal(2.3);
        assertEquals(2.3, c.getReal());
        assertEquals(0, c.getImaginary());
    }

    @Test
    public void testFromImaginary() {
        ComplexNumber c = fromImaginary(5);
        assertEquals(5, c.getImaginary());
        assertEquals(0, c.getReal());
    }

    @Test
    public void testFromMagnitudeAndAngle() {
        ComplexNumber c1 = fromMagnitudeAndAngle(1, 0);
        ComplexNumber c2 = fromMagnitudeAndAngle(1, Math.PI);
        ComplexNumber c3 = fromMagnitudeAndAngle(1, Math.PI / 2);
        ComplexNumber c4 = fromMagnitudeAndAngle(1, Math.PI * 3 / 2);
        ComplexNumber c5 = fromMagnitudeAndAngle(5, 5);
        ComplexNumber c6 = fromMagnitudeAndAngle(5, 10);
        assertEquals("1.0", c1.toString());
        assertEquals("-1.0", c2.toString());
        assertEquals("1.0i", c3.toString());
        assertEquals("-1.0i", c4.toString());
        assertEquals("1.4183109273161312-4.794621373315692i", c5.toString());
        assertEquals("-4.195357645382262-2.72010555444685i", c6.toString());

    }

    @Test
    public void testParse() {
        ComplexNumber c1 = ComplexNumber.parse("-2+3i");
        assertEquals("-2.0+3.0i", c1.toString());
        ComplexNumber c2 = ComplexNumber.parse("i");
        assertEquals("1.0i", c2.toString());
        ComplexNumber c3 = ComplexNumber.parse("-i");
        assertEquals("-1.0i", c3.toString());
        ComplexNumber c4 = ComplexNumber.parse("2.5+3i");
        assertEquals("2.5+3.0i", c4.toString());
        ComplexNumber c5 = ComplexNumber.parse("-2");
        assertEquals("-2.0", c5.toString());
        ComplexNumber c6 = ComplexNumber.parse("2.9i");
        assertEquals("2.9i", c6.toString());
        ComplexNumber c7 = ComplexNumber.parse("+9");
        assertEquals("9.0", c7.toString());
        assertThrows(IllegalArgumentException.class, () -> {
            ComplexNumber c8 = ComplexNumber.parse("i2");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            ComplexNumber c9 = ComplexNumber.parse("-+3i");
            System.out.println(c9.toString());
        });
        ComplexNumber c10 = ComplexNumber.parse("2i");
        assertEquals("2.0i", c10.toString());
        assertThrows(IllegalArgumentException.class, () -> {
            ComplexNumber c11 = ComplexNumber.parse("1.1.1i");
            System.out.println(c11.toString());
        });
        assertThrows(IllegalArgumentException.class, () -> {
            ComplexNumber c12 = ComplexNumber.parse("1+.1i");
            System.out.println(c12.toString());
        });
    }

    @Test
    public void testGetReal() {
        ComplexNumber c = new ComplexNumber(2,3);
        assertTrue(2 == c.getReal());
        ComplexNumber c1 = ComplexNumber.parse("3i");
        assertEquals(0, c1.getReal());
    }

    @Test
    public void testGetImaginary() {
        ComplexNumber c = new ComplexNumber(2,3);
        assertTrue(3 == c.getImaginary());
        ComplexNumber c1 = ComplexNumber.parse("3i");
        assertEquals(3, c1.getImaginary());
        ComplexNumber c2 = ComplexNumber.parse("2");
        assertEquals(0, c2.getImaginary());
    }

    @Test
    public void testGetMagnitude() {
        ComplexNumber c1 = ComplexNumber.parse("3+4i");
        assertEquals(5, c1.getMagnitude());
        ComplexNumber c2 = ComplexNumber.parse("1");
        assertEquals(1, c2.getMagnitude());
        ComplexNumber c3 = ComplexNumber.parse("3i");
        assertEquals(3, c3.getMagnitude());
        ComplexNumber c4 = ComplexNumber.parse("5-5i");
        assertEquals(5*Math.sqrt(2), c4.getMagnitude());
    }

    @Test
    public void testGetAngle() {
        ComplexNumber c1 = new ComplexNumber(1,0);
        assertEquals(0, c1.getAngle());
        assertEquals(Math.PI/2, ComplexNumber.parse("i").getAngle());
        assertEquals(Math.PI/4, ComplexNumber.parse("1+i").getAngle());
    }

    @Test
    public void testAdd() {
        assertEquals("2.0+2.0i", ComplexNumber.parse("1+i").add(ComplexNumber.parse("1+i")).toString());
        assertEquals("3.0+2.0i", ComplexNumber.parse("2+i").add(ComplexNumber.parse("1+i")).toString());
        assertEquals("0.0", ComplexNumber.parse("-1-i").add(ComplexNumber.parse("1+i")).toString());
        assertEquals("2.0i", ComplexNumber.parse("1+i").add(ComplexNumber.parse("-1+i")).toString());
        assertEquals("2.0", ComplexNumber.parse("1-i").add(ComplexNumber.parse("1+i")).toString());

    }

    @Test
    public void testSub() {
        assertEquals("0.0", ComplexNumber.parse("1+i").sub(ComplexNumber.parse("1+i")).toString());
        assertEquals("1.0", ComplexNumber.parse("2+i").sub(ComplexNumber.parse("1+i")).toString());
        assertEquals("-2.0-2.0i", ComplexNumber.parse("-1-i").sub(ComplexNumber.parse("1+i")).toString());
        assertEquals("2.0", ComplexNumber.parse("1+i").sub(ComplexNumber.parse("-1+i")).toString());
        assertEquals("-2.0i", ComplexNumber.parse("1-i").sub(ComplexNumber.parse("1+i")).toString());
    }

    @Test
    public void testMul() {
        assertEquals("2.0i", ComplexNumber.parse("1+i").mul(ComplexNumber.parse("1+i")).toString());
        assertEquals("1.0+3.0i", ComplexNumber.parse("2+i").mul(ComplexNumber.parse("1+i")).toString());
        assertEquals("-2.0i", ComplexNumber.parse("-1-i").mul(ComplexNumber.parse("1+i")).toString());
        assertEquals("-2.0", ComplexNumber.parse("1+i").mul(ComplexNumber.parse("-1+i")).toString());
        assertEquals("2.0", ComplexNumber.parse("1-i").mul(ComplexNumber.parse("1+i")).toString());
    }

    @Test
    public void testDiv() {
        assertEquals("1.0", ComplexNumber.parse("1+i").div(ComplexNumber.parse("1+i")).toString());
        assertEquals("1.5-0.5i", ComplexNumber.parse("2+i").div(ComplexNumber.parse("1+i")).toString());
        assertEquals("-1.0", ComplexNumber.parse("-1-i").div(ComplexNumber.parse("1+i")).toString());
        assertEquals("-1.0i", ComplexNumber.parse("1+i").div(ComplexNumber.parse("-1+i")).toString());
        assertEquals("-1.0i", ComplexNumber.parse("1-i").div(ComplexNumber.parse("1+i")).toString());
    }

    @Test
    public void testPower() {
        double real = Math.round(ComplexNumber.parse("2+i").power(2).getReal());
        double imaginary = Math.round(ComplexNumber.parse("2+i").power(2).getImaginary());
        assertEquals(3, real);
        assertEquals(4, imaginary);
        real = Math.round(ComplexNumber.parse("5+8i").power(5).getReal());
        imaginary = Math.round(ComplexNumber.parse("5+8i").power(5).getImaginary());
        assertEquals(25525, real);
        assertEquals(-70232, imaginary);
    }

    @Test
    public void testRoot() {
        ComplexNumber[] roots = ComplexNumber.parse("2+3i").root(4);
        String[] result = new String[4];
        for (int i = 0; i < roots.length; i++) {
            result[i] = roots[i].toString();
        }
        assertArrayEquals(new String[]{ComplexNumber.parse("1.3365960777571289+0.33517136966065714i").toString(),
                                            ComplexNumber.parse("-0.3351713696606569714+1.3365960777571289i").toString(),
                                            ComplexNumber.parse("-1.3365960777571289-0.33517136966065714i").toString(),
                                            ComplexNumber.parse("0.3351713696606571-1.3365960777571289i").toString()},
                                            result);
    }

    @Test
    public void testToString() {
        assertEquals("2.0+2.0i", ComplexNumber.parse("2+2i").toString());
        assertEquals("2.0", ComplexNumber.parse("2").toString());
        assertEquals("2.0i", ComplexNumber.parse("2i").toString());
        assertEquals("2.0-2.0i", ComplexNumber.parse("2-2i").toString());
    }
}