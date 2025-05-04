package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import se.kth.iv1350.posi.integration.Item;
import se.kth.iv1350.posi.integration.ItemDTO;
import se.kth.iv1350.posi.integration.ItemID;
import se.kth.iv1350.posi.model.Sale;
import se.kth.iv1350.posi.model.Amount;

public class SaleTest {

    private Sale sale;  
    private ItemID itemIDJames;
    private ItemDTO itemDTOJames;
    private Item itemJames;
    private Item itemFake;
    
    
    @BeforeEach
    
    void setUp() {
    
    sale = new Sale();
    Amount vAT = new Amount(2.0);
    itemIDJames = new ItemID("007");
    itemDTOJames = new ItemDTO(itemIDJames,  "ActionFigure of James Bond", new Amount(100), vAT, "James Bond");
    itemJames = new Item(itemDTOJames);
    

    Amount priceBeforeVAT = new Amount (100.0);     
    Amount vatRate = new Amount(2);
    ItemDTO itemDTOFake = new ItemDTO(new ItemID("item123"), "fake item", priceBeforeVAT, vatRate, "fakeItemName");
    itemFake = new Item(itemDTOFake);

    }   

    @Test
    
    public void testAddNewItem() {
    Amount expeAmount = new Amount(1.0);
    sale.addItemToList(itemJames);
    
    assertTrue(sale.getItems().containsKey(itemIDJames), "Item be added to sale.");
    
    assertEquals(expeAmount.getValue(), sale.getItems().get(itemIDJames).getQuantity().getAmount().getValue(), "Quantity should be 1");
    
    assertEquals(itemDTOJames.getPriceWithVAT().getValue(), sale.getTotal().getValue(), "Total should be equal to total price of item incl. VAT.");
    }
     
    @Test
    
    void testAddSameItemIncreaseQuantity() {
    sale.addItemToList(itemJames);
    sale.addItemToList(itemJames);
    Amount expeQuantAmount = new Amount(2.0);

    assertEquals(expeQuantAmount.getValue(), sale.getItems().get(itemIDJames).getQuantity().getAmount().getValue(), "Quantity should be 2 since there are two of the same items");
    Amount expectedTotal = itemDTOJames.getPriceWithVAT().multiply(new Amount(2));
    assertEquals(expectedTotal.getValue(), sale.getTotal().getValue(), "Total should be total price times 2");
    } 

    @Test

    public void testCalculateRunningTotal() {
    sale.addItemToList(itemJames);
    Amount result = sale.calculateRunningTotal(itemJames);
    Amount exprunn = new Amount(102);
    assertEquals(exprunn.getValue(), result.getValue());

    sale.addItemToList(itemFake);
    result = sale.calculateRunningTotal(itemFake);
    Amount newExprunn = new Amount(204);
    assertEquals(newExprunn.getValue(), result.getValue());

    }



    @Test

    public void testCalculateRunningVAT() {
    Amount result = sale.calculateRunningVAT(itemJames);
    assertEquals(new Amount(2.0).getValue(),result.getValue());

    result = sale.calculateRunningVAT(itemFake);

    assertEquals(new Amount(4.0).getValue(),result.getValue());

    }


        
        
        
}
