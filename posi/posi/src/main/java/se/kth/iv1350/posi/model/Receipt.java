package se.kth.iv1350.posi.model;

import java.time.LocalDateTime;
import se.kth.iv1350.posi.integration.Item;
import se.kth.iv1350.posi.integration.ItemDTO;
/**
 * Represents a receipt for A completed sale 
 */
public class Receipt {
    private final Sale sale;
    private final Amount paidAmount;

    /**
     * Creates a new instance of <code>Recepeit</code>
     * 
     * @param sale The sale that this reseipt represents.
     * @param paidAmount The amount of cash paid by the customer.
     */
    public Receipt(Sale sale, Amount paidAmount) {
        this.sale = sale;
        this.paidAmount = paidAmount;
    }
    
    /**
     * Creates a well-formatted string with the entire content of the receipt.
     *
     * @return The well-formatted receipt string.
     */
    public String generateText() {
        StringBuilder sb = new StringBuilder();

        for (Item item : sale.getItems().values()) {
            ItemDTO itemDTO = item.getItemDTO();
            
            sb.append("Add 1 item with item id ").append(itemDTO.getItemID().toString()).append(" :\n");
            sb.append("Item ID: ").append(itemDTO.getItemID().toString()).append("\n");
            sb.append("Item name:" ).append(itemDTO.getName()).append(("\n"));
            sb.append("Cost of Item: ").append(itemDTO.getPrice().toString()).append(" SEK\n");
            sb.append("VAT: ").append(itemDTO.getVAT()).append("%\n");
            sb.append("Item description: ").append(itemDTO.getItemDescription()).append("\n\n");

            Amount runningTotal = sale.calculateRunningTotal(item);
            Amount runningVAT = sale.calculateRunningVAT(item);
            sb.append("Total cost (incl VAT): ").append(runningTotal.toString()).append(" SEK \n");
            sb.append("Total VAT : ").append(runningVAT.toString()).append(" SEK\n\n");

        }

        sb.append("End Sale: \n");
        sb.append("Total cost (incl VAT): " ).append(sale.getTotal().toString()).append(" SEK\n\n");

        
        sb.append("----------------Begin receipt---------------- \n");
        sb.append("Time of Sale: ")
        .append(LocalDateTime.now())
        .append("\n \n");


        for (Item item : sale.getItems().values()) {
            String name = item.getItemDTO().getName();
            Amount unitPrice = item.getItemDTO().getPrice();
            Amount quantity = item.getQuantity().getAmount();
            Amount lineTotal = unitPrice.multiply(quantity);
            sb.append(name)
            .append(" ")
            .append(quantity).append(" x ")
            .append(String.format("%s", unitPrice.getValue()))
            .append(" ")
            .append(String.format("        %s SEK \n", lineTotal.getValue()));
        }

        sb.append("\n");
        sb.append("Total price: ").append(String.format("%s SEK\n", sale.getTotal().getValue()));
        sb.append("Total VAT: ").append(String.format("%s \n",sale.getFinalVAT().getValue()));
        sb.append("\n");
        sb.append("Cash: ").append(String.format("%s SEK \n", paidAmount.getValue()));
        sb.append("Total Change: ").append(paidAmount.subtract(sale.getTotal()).toString()).append(" SEK\n");
        sb.append("----------------End receipt----------------");
        return sb.toString();
    }
}
