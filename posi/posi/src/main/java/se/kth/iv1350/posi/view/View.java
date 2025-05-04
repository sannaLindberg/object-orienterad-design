package se.kth.iv1350.posi.view;

import se.kth.iv1350.posi.controller.Controller;
import se.kth.iv1350.posi.integration.ItemID;
import se.kth.iv1350.posi.model.Amount;

/**
 * This program has no view, instead, this class is a placeholder for the entire
 * view.
 */
public class View {
    private final Controller contr;

    /**
     * Creates a new instance.
     *
     * @param contr The controller that is used for all operations.
     */

    public View (Controller contr) {
        this.contr = contr;
    }

    /**
     * Simulates a user input that generates calls to all system operations.
     */

    public void sampleExecution() {
       contr.startSale();
       
       contr.addItem(new ItemID("abc123"));
       contr.addItem(new ItemID("def456"));
    
      
       contr.endSale();
       
       contr.enterPayment(new Amount(100.0));
       System.out.println("Tack för ditt köp!");

    }
}