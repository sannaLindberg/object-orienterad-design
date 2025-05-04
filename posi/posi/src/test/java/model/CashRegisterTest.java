package model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.posi.model.Amount;
import se.kth.iv1350.posi.model.CashRegister;
public class CashRegisterTest {

    @Test
    public void testAddAmountIncreasesBalance() {
        Amount initialAmount = new Amount(100.0);
        CashRegister instance = new CashRegister(initialAmount);

        Amount payment = new Amount(50.0);
        instance.addAmount(payment);

        Amount expected = initialAmount.add(payment);
        Amount actual = instance.getBalance();

        assertEquals(expected.getValue(), actual.getValue(), "Saldo efter addAmount blev fel.");
    }
}
