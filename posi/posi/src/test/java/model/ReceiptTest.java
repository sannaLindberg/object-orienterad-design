package model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import se.kth.iv1350.posi.integration.Item;
import se.kth.iv1350.posi.integration.ItemDTO;
import se.kth.iv1350.posi.integration.ItemID;
import se.kth.iv1350.posi.model.Amount;
import se.kth.iv1350.posi.model.Receipt;
import se.kth.iv1350.posi.model.Sale;

public class ReceiptTest {
@Test

void testGenerateText() {

    ItemID itemID = new ItemID("cucumber");
Amount price = new Amount (8.0);
Amount vat = new Amount(12.0);
String itemDesc = "Green cucumber";
String name = "Cucumber";
ItemDTO itemDTO = new ItemDTO(itemID, name, price, vat, itemDesc);
Item item = new Item(itemDTO);
Sale sale = new Sale();

sale.addItemToList(item);
Amount paidAmount = new Amount(500.0);
Receipt receipt = new Receipt(sale, paidAmount);
String testText = receipt.generateText();

assertTrue(testText.contains("cucumber"), "Receipt should contain itemID");

assertTrue(testText.contains("Cucumber"), "Receipt should contain name");

assertTrue(testText.contains("Green cucumber"), "Receipt should contain an item description"); assertTrue(testText.contains("8.0"), "Receipt should contain the price of the item");

assertTrue(testText.contains("12.0%"), "Receipt should contain the VAT of the item");

assertTrue(testText.contains("Cash: 500.0"), "Receipt should mention cash amount");

assertTrue(testText.contains("Total Change:"), "Receipt should mention total change of the sale");

assertTrue(testText.contains("----------------Begin receipt----------------"), "Receipt should contain a start");

}



}

