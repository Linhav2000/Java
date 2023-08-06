package bai_tap_ngan_hang;

import java.util.ArrayList;
import java.util.Scanner;

public class CurrentAccount extends Account {
	private final double MINIMUM_BALANCE= 0 ; // SỐ DƯ TỐI THIỂU  
	private final double Fee = 1000; 
	private ArrayList<Transaction> transaction ;
	private ArrayList<SavingAccount> savingAccount ;
	private ArrayList<LoanAccounts> loanAccount  ;
	private double balance ;
	private double annualIntestRate ;
	
	
	public CurrentAccount(String name, String id) {
		  super(name, id);
		  this.balance = MINIMUM_BALANCE;
		  this.transaction = new ArrayList<>();
	      this.savingAccount = new ArrayList<>();
	      this.loanAccount = new ArrayList<>();   
	 }    
	
	 public static CurrentAccount CreateAccount() {
	        Scanner input = new Scanner(System.in);
	        System.out.print("Họ và Tên: ");
	        String name = input.nextLine();
	        System.out.print("Nhập vào số CMND: ");
	        String id = input.nextLine();
	        return new CurrentAccount(name, id);
	    }
	  public SavingAccount createSavingAccount() {
	        Scanner input = new Scanner(System.in);
	        System.out.print("Số tiền muốn gửi tiết kiệm: ");
	        double startBalance = input.nextDouble();
	        System.out.print("Chọn kỳ hạn gửi (3, 6, 9, 12 tháng) | Gửi không kỳ hạn (nhập vào 0): ");
	        int savingTerm = input.nextInt();
	        double annualInterestRate = SavingAccount.getSavingInterestRate(savingTerm);
	        System.out.print("Số tiền gửi vào định kỳ hàng tháng (từ tháng thứ 2): ");
	        double regularSavingAmount = input.nextDouble();
	        return new SavingAccount(startBalance, savingTerm, regularSavingAmount, annualInterestRate);
	    }
	  
	  public LoanAccounts createLoanAccount() {
	        LoanAccounts loanAccount;
	        Scanner input = new Scanner(System.in);
	        System.out.printf("Số tiền tối đa có thể vay: %10.2f%4s\n", getMaxLoanAmount());
	        System.out.print("Số tiền muốn vay: ");
	        double loanAmount = input.nextDouble();
	        System.out.print("Chọn kỳ hạn vay (3, 6, 9, 12 tháng): ");
	        int loanTerm = input.nextInt();
	        double loanInterestRate = LoanAccounts.getLoanInterestRate(loanTerm);
	        return new LoanAccounts(loanAmount, loanTerm, loanInterestRate);
	    }

	public double getMaxLoanAmount() {
		return balance*2;
	}

	
	  public double getFee() {
	        return Fee;
	    }

	 // hàm kiemr tra list  có rỗng không (thông tin tài khoản)
	    public String isActiveLA() {
	        if (loanAccount.isEmpty()) {
	            return "Không";
	        } else {
	            return "Có";
	        }
	    }
	    
	    
	    public String isActiveSA() {
	        if (savingAccount.isEmpty()) {
	            return "Không";
	        } else {
	            return "Có";
	        }
	    }
	    
	    
	    public double getBalance() {
			return balance;
		}

		public void setBalance(double balance) {
			this.balance = balance;
		}

		public void printCard() {
	        System.out.println("---------------------------------------");
	        System.out.println("          Thông Tin Tài Khoản          ");
	        System.out.println("---------------------------------------");
	        System.out.printf("%-2s%-15s%20s%3s", "|", "Tên:", getCustomName(), "|\n");
	        System.out.printf("%-2s%-15s%20s%3s", "|", "Số tài khoản:", getAccountNumber(), "|\n");
	        System.out.printf("%-2s%-15s%20s%3s", "|", "Ngày tạo:", BankMethods.convertDate(getDateCreated()), "|\n");
	        System.out.printf("%-2s%-15s%20.2f%3s", "|", "Số dư:", getBalance(), "|\n");
	        System.out.printf("%-2s%-15s%20s%3s", "|", "Tiền gửi:", isActiveSA(), "|\n");
	        System.out.printf("%-2s%-15s%20s%3s", "|", "Khoản nợ:", isActiveLA(), "|\n");
	        System.out.print("---------------------------------------\n");
	    }

		public ArrayList<Transaction> getTransaction() {
			return transaction;
		}

		public void setTransaction(ArrayList<Transaction> transaction) {
			this.transaction = transaction;
		}

		public ArrayList<SavingAccount> getSavingAccount() {
			return savingAccount;
		}

		public void setSavingAccount(ArrayList<SavingAccount> savingAccount) {
			this.savingAccount = savingAccount;
		}

		public ArrayList<LoanAccounts> getLoanAccount() {
			return loanAccount;
		}

		public void setLoanAccount(ArrayList<LoanAccounts> loanAccount) {
			this.loanAccount = loanAccount;
		}

		public double getAnnualIntestRate() {
			return annualIntestRate;
		}

		public void setAnnualIntestRate(double annualIntestRate) {
			this.annualIntestRate = annualIntestRate;
		}
		
		 public double getMINIMUM_BALANCE() {
		        return MINIMUM_BALANCE;
		}
		 // hàm nạp tiền
		 
		 public void deposit() {
		        Scanner input = new Scanner(System.in);
		        System.out.print("Nhập vào số tiền cần nạp: ");
		        double amount = input.nextDouble();
		        setBalance(getBalance() + amount);
		        transaction.add(new Transaction("Nạp tiền", amount, getBalance(),
		                "Nạp tiền vào tài khoản"));
		    }
		 
		  public void deposit(double amount) {
		        setBalance(getBalance() + amount);
		        transaction.add(new Transaction("Tất toán", amount, getBalance(),
		                "Tất toán tài khoản tiết kiệm"));
		    }
		  // hàm rút tiền
		   public void withdraw(double amount) {
		        if (getBalance() - amount - Fee > MINIMUM_BALANCE) {
		            setBalance(getBalance() - amount - Fee);
		            transaction.add(new Transaction("Tất toán", amount, getBalance(),
		                    "Tất toán tài khoản vay"));
		        } else {
		        	try {
		        		throw new IllegalArgumentException("Không đủ tiền!");
		        	}catch(IllegalArgumentException e){
		        		 System.out.println(e);
		        	}
		        }
		    }
		    
		 
		    public void withdraw() {
		        Scanner input = new Scanner(System.in);
		        System.out.print("Nhập vào số tiền cần rút: ");
		        double amount = input.nextDouble();
		        if (getBalance() - amount - Fee > MINIMUM_BALANCE) {
		            setBalance(getBalance() - amount - Fee);
		            transaction.add(new Transaction("Rút tiền", amount, getBalance(),
		                    "Rút tiền từ tài khoản"));
		        } else {
		        	try {
		        		throw new IllegalArgumentException("Không đủ tiền!");
		        	}catch(IllegalArgumentException e){
		        		 System.out.println(e);
		        	}
		            	
		        }
		    }

			
		 
		 
	    
}
