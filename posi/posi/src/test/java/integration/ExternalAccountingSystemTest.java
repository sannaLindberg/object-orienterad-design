package integration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import se.kth.iv1350.posi.integration.ExternalAccountingSystem;
import se.kth.iv1350.posi.integration.Item;
import se.kth.iv1350.posi.integration.ItemDTO;
import se.kth.iv1350.posi.integration.ItemID;
import se.kth.iv1350.posi.model.Amount;
import se.kth.iv1350.posi.model.Sale;


public class ExternalAccountingSystemTest {
    private ByteArrayOutputStream outContent;
    private PrintStream originalSysOut;

    @BeforeEach
    public void setUp() {
        outContent = new ByteArrayOutputStream();
        originalSysOut = System.out;
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void tearDown() { 
        outContent = null;
        System.setOut(originalSysOut);
    }

    @Test
    public void recordSaleTest(){
        Amount price = new Amount(100.0);
        Sale sale = new Sale();
        ItemID itemID = new ItemID("abs");
        ItemDTO itemDTO = new ItemDTO(itemID, "hejehj", price, new Amount(1.0), "kakor");
        Item item = new Item(itemDTO);
        sale.addItemToList(item);
        try{
        ExternalAccountingSystem instance = new ExternalAccountingSystem();
        instance.recordSale(sale);

        String output = outContent.toString().trim();
            String expectedPart = "External accounting updated. New total revenue: " + sale.getTotal().toString();

            assertTrue(output.contains(expectedPart), "Output should contain updated revenue info.");
        
        }

        finally {
            System.setOut(originalSysOut);
        }
        
    }

}