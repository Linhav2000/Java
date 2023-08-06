package bai_tap_ngan_hang;

import java.util.Date;

public class Transaction {
	  	private Date date;
	    private String type;
	    private double amount;
	    private double balance;
	    private String description;
	    
	    
		public Transaction(String type, double amount, double balance, String description) {
			this.date = new Date() ;
			this.type = type;
			this.amount = amount;
			this.balance = balance;
			this.description = description;
		}
	    
	    public void printTransaction() {
	    	System.out.printf("%20s%15s%15s%15s\n",
	    			BankMethods.convertDateDetail(this.date), 
	    			getType() , getAmount(), getDescription());
	    	
	    }

		public Date getDate() {
			return date;
		}

		public void setDate(Date date) {
			this.date = date;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public double getAmount() {
			return amount;
		}

		public void setAmount(double amount) {
			this.amount = amount;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}
	    
}
