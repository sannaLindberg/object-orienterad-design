package se.kth.iv1350.posi.model;
import java.util.HashMap;
import java.util.Map;
import se.kth.iv1350.posi.integration.Item;
import se.kth.iv1350.posi.integration.ItemID;
/**
 * Represents one particular sale transaction.
 */
public class Sale {
    private Map<ItemID, Item> items = new HashMap<>();
    private Amount total = new Amount(0.0);
    private CashPayment payment;
    private Amount runningVAT = new Amount(0);
    private Amount runningTotal = new Amount(0);

    /**
     * Adds an item to the current sale. if item is already scanned, its quantity is increased.
     * The total sale amount is updated with items price incl VAT.
     * 
     * @param item The item added to the sale.
     */
    public void addItemToList(Item item) {
        ItemID id = item.getItemID();
        if (items.containsKey(id)) {
            items.get(id).increaseQuantity();
        } else {
            items.put(id, item);
        }
        total = total.add(item.getItemDTO().getPriceWithVAT());
    }

    /**
     * Returns the total cost incl VAT of the sale.
     * 
     * @return the total <code>Amount</code> of the sale.
     */
    public Amount getTotal() {
        return total; 
    }

    /**
     * Calculates and updates the running total incl VAT 
     * 
     * @param item The item whose price will be added to the running total.
     * @return The updated running total incl VAT
     */
    public Amount calculateRunningTotal(Item item) {
        Amount itemTotal = item.getItemDTO().getPriceWithVAT();
        runningTotal =  runningTotal.add(itemTotal);
        return runningTotal;
    }
   
    /**
     * Calculates and updates the total VAT amount of the sale.
     * 
     * @param item The item whose VAT will be added to the running VAT.
     * @return The updated total VAT.
     */
    public Amount calculateRunningVAT(Item item) {
        Amount itemVAT = item.getItemDTO().calculateVATAmount().multiply((item.getQuantity()).getAmount());
        runningVAT = runningVAT.add(itemVAT); 
        return runningVAT;
    }

    /**
     * Registers the payment for the sale.
     * 
     * @param payment the <code>CashPayment</code> representing the amount payed by the customer.
     */
    public void processPayment (CashPayment payment) {
        this.payment = payment;
    }

    /**
     * Gets the map of all the current items of the sale.
     * 
     * @return A map of <code>ItemID</code> to <code>Item</code>:
     */
    public Map<ItemID, Item> getItems() {
        return items;
    }

    /**
     * Returns the total VAT amount for the sale.
     * 
     * @return The final VAT amount as an <code>Amount</code>
     */
    public Amount getFinalVAT () {
        Amount finalVAT = runningVAT;
        return finalVAT;
    }


}
