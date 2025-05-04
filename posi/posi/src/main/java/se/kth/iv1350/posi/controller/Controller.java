package se.kth.iv1350.posi.controller;

import se.kth.iv1350.posi.integration.ExternalAccountingSystem;
import se.kth.iv1350.posi.integration.ExternalCreator;
import se.kth.iv1350.posi.integration.ExternalInventorySystem;
import se.kth.iv1350.posi.integration.Item;
import se.kth.iv1350.posi.integration.ItemDTO;
import se.kth.iv1350.posi.integration.ItemID;
import se.kth.iv1350.posi.integration.Printer;
import se.kth.iv1350.posi.model.Amount;
import se.kth.iv1350.posi.model.CashPayment;
import se.kth.iv1350.posi.model.CashRegister;
import se.kth.iv1350.posi.model.Receipt;
import se.kth.iv1350.posi.model.Sale;

/**
 * This is the application's only controller class. All calls to the model pass
 * through here.
 */
public class Controller {
    private Sale currentSale;
    private final Printer printer;
    private final CashRegister cashRegister;
    private final ExternalAccountingSystem eas;
    private final ExternalInventorySystem eis;

    /**
     * Creates a new instance of the Controller.
     * 
     * @param printer Interface to printer.
     * @param creator Used to get all classes that handle database calls.
     */
    public Controller(Printer printer, ExternalCreator creator) {
        this.printer = printer;
        this.eas = creator.getEas();
        this.eis = creator.getEis();
        this.cashRegister = new CashRegister(new Amount(1234));
    }

    /**
     * Starts a new sale.
     */
    public void startSale() {
        currentSale = new Sale();
    }

    /**
     * Adds an item to the current sale.
     * 
     * @param itemID The identifier of the item to add.
     */
    public void addItem(ItemID itemID) {
        ItemDTO itemDTO = eis.findItem(itemID);
        currentSale.addItemToList(new Item (itemDTO));
        Amount runningTotal = currentSale.getTotal();
        System.out.println("Item: " + itemDTO.getItemDescription() + " price: " + itemDTO.getPrice().toString() +
          " Running Total: " + runningTotal.toString());
    }

    /**
     * Ends the current sale and shows the total.
     */
    public void endSale() {
        System.out.println("Total incl. VAT: " + currentSale.getTotal().toString());

    }

    /**
     * Processes the customer's payment and finalizes the sale.
     * 
     * @param paidAmount The amount paid by the customer.
     */
    public void enterPayment(Amount paidAmount) {
        CashPayment payment = new CashPayment(paidAmount);
        currentSale.processPayment(payment);
        cashRegister.addAmount(paidAmount);
        eas.recordSale(currentSale);
        eis.updateInventory(currentSale);
        Receipt receipt = new Receipt(currentSale, paidAmount);
        printer.printReceipt(receipt);
        System.out.println("Change to give to the customer: " + payment.getChange(currentSale.getTotal()).toString() + " SEK");
    }

}
