package model;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.posi.model.Amount;

import static org.junit.jupiter.api.Assertions.*;

public class AmountTest {
 
    @Test
    public void subtract() {
        double amountOfOperand1 = 10.0;
        double amountOfOperand2 = 3.0;
        Amount operand1 = new Amount(amountOfOperand1);
        Amount operand2 = new Amount(amountOfOperand2);
        Amount expResult = new Amount(amountOfOperand1 - amountOfOperand2);
        Amount result = operand1.subtract(operand2);
        assertEquals(expResult.getValue(), result.getValue(), "Wrong subtraction result");
    }

    @Test
    public void subtractNegRes() {
        double amountOfOperand1 = 3.0;
        double amountOfOperand2 = 10.0;
        Amount operand1 = new Amount(amountOfOperand1);
        Amount operand2 = new Amount(amountOfOperand2);
        Amount expResult = new Amount(amountOfOperand1 - amountOfOperand2);
        Amount result = operand1.subtract(operand2);
        assertEquals(expResult.getValue(), result.getValue(), "Wrong subtraction result");
    }

    @Test
    public void subtractNegOperand() {
        double amountOfOperand1 = -3.0;
        double amountOfOperand2 = -3.0;
        Amount operand1 = new Amount(amountOfOperand1);
        Amount operand2 = new Amount(amountOfOperand2);
        Amount expResult = new Amount(amountOfOperand1 - amountOfOperand2);
        Amount result = operand1.subtract(operand2);
        assertEquals(expResult.getValue(), result.getValue(), "Wrong subtraction result");
    }


    @Test
    void testAdd(){
        double firstDouble = 2.0;
        double secondDouble = 5.0;
        Amount amountOne = new Amount(firstDouble);
        Amount amountTwo = new Amount(secondDouble);
        Amount expResult = new Amount(firstDouble + secondDouble);
        Amount result = amountOne.add(amountTwo);
        assertEquals(expResult.getValue(), result.getValue(), "Addition fails!");
    }

    @Test
    void testAddNegRes(){
        double firstDouble = 2.0;
        double secondDouble = -5.0;
        Amount amountOne = new Amount(firstDouble);
        Amount amountTwo = new Amount(secondDouble);
        Amount expResult = new Amount(firstDouble + secondDouble);
        Amount result = amountOne.add(amountTwo);
        assertEquals(expResult.getValue(), result.getValue(), "Addition fails!");
    }

    @Test
    void testAddNegZeroRes(){
        double firstDouble = 5.0;
        double secondDouble = -5.0;
        Amount amountOne = new Amount(firstDouble);
        Amount amountTwo = new Amount(secondDouble);
        Amount expResult = new Amount(firstDouble + secondDouble);
        Amount result = amountOne.add(amountTwo);
        assertEquals(expResult.getValue(), result.getValue(), "Addition fails!");
    }

    @Test
    void testMultiply(){
        double firstDouble = 5.0;
        double secondDouble = 3.0;
        Amount amountOne = new Amount(firstDouble);
        Amount amountTwo = new Amount(secondDouble);
        Amount expResult = new Amount(firstDouble * secondDouble);
        Amount result = amountOne.multiply(amountTwo);
        assertEquals(expResult.getValue(), result.getValue(), "Multiply fails!");
    }

    @Test
    void testMultiplyNeg(){
        double firstDouble = -5.0;
        double secondDouble = 3.0;
        Amount amountOne = new Amount(firstDouble);
        Amount amountTwo = new Amount(secondDouble);
        Amount expResult = new Amount(firstDouble * secondDouble);
        Amount result = amountOne.multiply(amountTwo);
        assertEquals(expResult.getValue(), result.getValue(), "Multiply fails!");
    }

    @Test
    void testMultiplyZero(){
        double firstDouble = 0.0;
        double secondDouble = 3.0;
        Amount amountOne = new Amount(firstDouble);
        Amount amountTwo = new Amount(secondDouble);
        Amount expResult = new Amount(firstDouble * secondDouble);
        Amount result = amountOne.multiply(amountTwo);
        assertEquals(expResult.getValue(), result.getValue(), "Multiply fails!");
    }

    @Test
    void testDividePosRes(){
        double firstDouble = 5.0;
        double secondDouble = 3.0;
        Amount amountOne = new Amount(firstDouble);
        Amount amountTwo = new Amount(secondDouble);
        Amount expResult = new Amount(firstDouble / secondDouble);
        Amount result = amountOne.divide(amountTwo);
        assertEquals(expResult.getValue(), result.getValue(), "Multiply fails!");
    }

    @Test
    void testDivideNegRes(){
        double firstDouble = -5.0;
        double secondDouble = 3.0;
        Amount amountOne = new Amount(firstDouble);
        Amount amountTwo = new Amount(secondDouble);
        Amount expResult = new Amount(firstDouble / secondDouble);
        Amount result = amountOne.divide(amountTwo);
        assertEquals(expResult.getValue(), result.getValue(), "Multiply fails!");
    }

    @Test
    void testDivideNegOperandPosRes(){
        double firstDouble = -5.0;
        double secondDouble = -3.0;
        Amount amountOne = new Amount(firstDouble);
        Amount amountTwo = new Amount(secondDouble);
        Amount expResult = new Amount(firstDouble / secondDouble);
        Amount result = amountOne.divide(amountTwo);
        assertEquals(expResult.getValue(), result.getValue(), "Multiply fails!");
    }

    @Test
    public void toStringPosAmt() {
        double representedAmt = 10.0;
        Amount amount = new Amount(representedAmt);
        String expResult = Double.toString(representedAmt);
        String result = amount.toString();
        assertEquals(expResult, result, "Wrong string returned by toString");
    }

    @Test
    public void toStringNegAmt() {
        double representedAmt = -10.0;
        Amount amount = new Amount(representedAmt);
        String expResult = Double.toString(representedAmt);
        String result = amount.toString();
        assertEquals(expResult, result, "Wrong string returned by toString");
    }

    @Test
    public void toStringZeroAmt() {
        double representedAmt = 0.0;
        Amount amount = new Amount(representedAmt);
        String expResult = Double.toString(representedAmt);
        String result = amount.toString();
        assertEquals(expResult, result, "Wrong string returned by toString");
    }
}
