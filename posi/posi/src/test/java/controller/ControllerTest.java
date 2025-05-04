package controller;

    import org.junit.jupiter.api.BeforeEach;

    import org.junit.jupiter.api.Test;
    
    import se.kth.iv1350.posi.integration.*;
    
    import se.kth.iv1350.posi.model.Amount;
    import se.kth.iv1350.posi.controller.Controller;
    import java.io.ByteArrayOutputStream;
    import java.io.PrintStream;
    import static org.junit.jupiter.api.Assertions.*;
    
    
    public class ControllerTest {
        private Controller controller;
        private ByteArrayOutputStream outContent;
        private PrintStream originalSysOut;
    
        @BeforeEach
    
        public void setUp() {
    
            originalSysOut = System.out;
            outContent = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outContent));
    
            Printer printer = new Printer(); // Assuming real printer prints to console
            ExternalCreator creator = new ExternalCreator(); // Provides real integrations
            controller = new Controller(printer, creator);
            controller.startSale();
        }
    
    
    
        @Test
    
        public void testStartSaleInitializesSale() {
    
            controller.startSale();
    
            ItemID itemID = new ItemID("def456");
    
            controller.addItem(itemID);
    
            String result = outContent.toString();
    
            assertTrue(result.contains("Item:"), "Sale was not properly started before adding item.");
    
        }
    
    
    
        @Test
    
        public void testAddItemPrintsItemAndRunningTotal() {
    
            ItemID itemID = new ItemID("abc123"); // Assuming this ID exists in ExternalInventorySystem
    
            controller.addItem(itemID);
    
            String result = outContent.toString();
    
            assertTrue(result.contains("Item:"), "Output does not contain item info.");
    
            assertTrue(result.contains("Running Total:"), "Output does not contain running total.");
    
        }
    
    
    
        @Test
    
        public void testEnterPaymentProcessesPaymentCorrectly() {
    
            ItemID itemID = new ItemID("abc123");
    
            controller.addItem(itemID);
    
            Amount paymentAmount = new Amount(1000);
    
            controller.enterPayment(paymentAmount);
    
            String result = outContent.toString();
    
            assertTrue(result.contains("Change to give to the customer: "), "Payment result not printed correctly.");
    
           // assertTrue(result.contains("Receipt"), "Receipt not printed.");
    
        }
    
    }

