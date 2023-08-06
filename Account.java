package bai_tap_ngan_hang;
import java.time.LocalDate;
import java.util.Date;

public class Account {
	private String id ;
	static final String CURRENCY = "VND" ; // khong thay doi 
	private String customName ;
	private String  accountNumber ;
	private Date dateCreated ;
	
	//Constructors
//	public Account() {
//		this.id = "" ;
//		this.customName =  "" ;
//		this.accountNumber = "" ;
//		this.dateCreated = BankMethods.parseDate("1-8-2023");
//	}
	// Constructors tao tai khoan moi
	public Account(String customerName, String id) {
		this.id = id;
		this.customName = customerName.toUpperCase();  //vieets hoa het
		this.accountNumber = BankMethods.generateAccNumber();          // random  de tao so tk
		this.dateCreated =  BankMethods.parseDate("16:20:15 01-08-2023");
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCustomName() {
		return customName;
	}
	public void setCustomName(String customName) {
		this.customName = customName;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	


	
}
