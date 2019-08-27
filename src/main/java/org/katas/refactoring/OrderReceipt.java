package org.katas.refactoring;

/**
 * OrderReceipt prints the details of order including customer name, address, description, quantity,
 * price and amount. It also calculates the sales tax @ 10% and prints as part
 * of order. It computes the total order amount (amount of individual lineItems +
 * total sales tax) and prints it.
 */
public class OrderReceipt {
    private Order o;

    public OrderReceipt(Order o) {
        this.o = o;
    }
    
    public String printReceipt() {
        StringBuilder output = new StringBuilder();
        
        output.append(printHeaders() +  printCustomerInfo());
        
        for (LineItem lineItem : o.getLineItems()) {
			output.append(printLineItem(lineItem));
		}
        
        output.append(getSalesTaxAndTotal());
        return output.toString();
    }
    
    private String printHeaders() {
		return "======Printing Orders======\n";
	}
    
    private String printCustomerInfo() {
		return o.getCustomerName() + o.getCustomerAddress();
	}
    
    private String printLineItem(LineItem lineItem) {
    	return lineItem.getDescription() + "\t" + lineItem.getPrice() + "\t" +
    		   lineItem.getQuantity() + "\t" + lineItem.totalAmount() + "\n";       
	}
    
    private String printTotal(double totSalesTx, double tot) {
		return "Sales Tax" + "\t" + totSalesTx +
			   "Total Amount" + "\t" + tot;
	}
    
    private String getSalesTaxAndTotal() {
    	double totSalesTx = 0d;
        double tot = 0d;
        double discount = .10;
		for (LineItem lineItem : o.getLineItems()) {
            double salesTax = lineItem.totalAmount() * discount;
            totSalesTx += salesTax;
            tot += lineItem.totalAmount() + salesTax;
		}		
	   return printTotal(totSalesTx,tot);
	}
}