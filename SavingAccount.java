package bai_tap_ngan_hang;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Scanner;

public class SavingAccount implements finalSettlement {

	private final double MINIMUM_START_BALANCE = 2_000_000;
	private static final double[] SAVING_INTEREST_RATE = {3.5, 4.8, 5.3, 5.5, 6.4};
	private Date dateCreated;
    private double startBalance;
    private double balance;
    private int savingTerm;
    private double regularSavingAmount;
    private double annualInterestRate;
    private double monthlyInterestRate;
    
	
	public SavingAccount(double startBalance,  int savingTerm, double regularSavingAmount,
			double annualInterestRate) {
		
		this.startBalance = startBalance;
		this.savingTerm = savingTerm;
		this.regularSavingAmount = regularSavingAmount;
		this.annualInterestRate = annualInterestRate;
		this.dateCreated = BankMethods.parseDate("16:08:23 07-09-2023");
	}
	public static double getSavingInterestRate(int month) {
		return SAVING_INTEREST_RATE[month / 3];
	}
	
	public void SavingCalculator() {
        Scanner input = new Scanner(System.in);
        int month;
        System.out.print("Nhập vào số tháng cần tính : ");
        month = input.nextInt();
        System.out.println("                 Bảng lãi thẻ tiết kiệm                ");
        System.out.println("-------------------------------------------------------");
        System.out.printf("%5s%20s%22s", "Thời gian", "Tiền lãi", "Tổng tiền");
        System.out.println();
        for (int i = 1; i <= month; i++) {
            System.out.printf("#%-5d%20.2f%20.2f\n", i, getMonthInterest(i), getTotalDeposit(i));
        }
    }
	public Date getDateCreated() {
	        return dateCreated;
	    }
	public void setDateCreated(Date dateCreated) {
	        this.dateCreated = dateCreated;
	    }
	public double getMINIMUM_START_BALANCE() {
	        return MINIMUM_START_BALANCE;
	    }
	public double getStartBalance() {
	        return startBalance;
	    }

	public void setStartBalance(double startBalance) {
	        this.startBalance = startBalance;
	    }
	public int getSavingTerm() {
	        return savingTerm;
	    }

	public void setSavingTerm(int savingTerm) {
	        this.savingTerm = savingTerm;
	    }
	public double getRegularSavingAmount() {
        return regularSavingAmount;
    }

    public void setRegularSavingAmount(double regularSavingAmount) {
        this.regularSavingAmount = regularSavingAmount;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    public void setAnnualInterestRate(double annualInterestRate) {
        this.annualInterestRate = annualInterestRate;
    }

    public double getMonthlyInterestRate() {
        return monthlyInterestRate;
    }

    public void setMonthlyInterestRate(double annualInterestRate) {
        this.monthlyInterestRate = annualInterestRate / 12;
    }

    public void printSavingAccount() {
    	 System.out.println();
         System.out.printf("%-2s%-15s%20s%6s\n", "|", "Ngày bắt đầu:", BankMethods.convertDate(getDateCreated()), "|");
         System.out.printf("%-2s%-15s%18.2f%6s\n", "|", "Tiền gửi ban đầu:", getStartBalance(), "|");
         System.out.printf("%-2s%-15s%18.2f%2s%6s\n", "|", "Lãi suất:", getAnnualInterestRate(), "%", "|");
         System.out.printf("%-2s%-15s%12d%8s%6s\n", "|", "Kỳ hạn gửi:", getSavingTerm(), "Tháng", "|");
         System.out.println("-------------------------------------------");

    }
    // Tính tiền lãi nhận được tại tháng truyền vào
    public double getMonthInterest(int month) {
        double monthInterest = 0;
        double totalDeposits = 0;
        for (int i = 1; i <= month; i++) {
            if (i == 1) { // Gửi tiết kiệm tích lũy nên tháng thứ 1 chưa có tiền góp vào hàng tháng
                monthInterest = getStartBalance() * getAnnualInterestRate() / 1200;
                totalDeposits = getStartBalance() + monthInterest;
            } else {
                totalDeposits += getRegularSavingAmount(); // Công vào tiền góp mỗi tháng từ tháng thứ 2
                monthInterest = totalDeposits * getAnnualInterestRate() / 1200;
            }
        }
        return monthInterest;
    }
    //Nhan tong tien gui toi thang may
    public double getTotalDeposit(int month) {
        double monthInterest = 0;
        double totalDeposits = 0;
        for (int i = 1; i <= month; i++) {
            if (i == 1) {
                monthInterest = getStartBalance() * getAnnualInterestRate() / 1200;
                totalDeposits = getStartBalance() + monthInterest;
            } else {
                totalDeposits += getRegularSavingAmount();
                monthInterest = totalDeposits * getAnnualInterestRate() / 1200;
                totalDeposits += monthInterest;
            }
        }
        return totalDeposits;
    }
    //tất toán lãi xuaasrt trước kì hạn nhận với lãi xuất không kì bạn
    public double getTotalDeposit(int month , double interestRate) {
    	double monthInterest = 0;
        double totalDeposits = 0;
        for (int i = 1; i <= month; i++) {
            if (i == 1) { // Gửi tiết kiệm tích lũy nên tháng thứ 1 chưa có tiền góp vào hàng tháng
                monthInterest = getStartBalance() * interestRate / 1200;
                totalDeposits = getStartBalance() + monthInterest;
            } else {
                totalDeposits += getRegularSavingAmount(); 
                monthInterest = totalDeposits * interestRate / 1200;
                totalDeposits += monthInterest;
            }
        }
        return totalDeposits;
    	
    }
    // tính tiền theo ngày 
    public double getRemainDeposit(int day) {
        return day * getTotalDeposit(BankMethods.getTimeElapsed(getDateCreated(), new Date())[0] + 1) / 30;
    }
    
    public boolean isMatchSavingTerm() {
        Date now = new Date();
        LocalDate nowTime = now.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate pastTime = getDateCreated().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        boolean isMatchDay = nowTime.getDayOfMonth() == pastTime.getDayOfMonth();
        boolean isMatchMonth = (getSavingTerm() == BankMethods.getTimeElapsed(getDateCreated(), now)[1]);
        return isMatchDay && isMatchMonth;
    }
    
    @Override
    public double finalSettlement() {
        double finalAmount = 0;
        printSavingAccount();
        Date now = new Date();
        if (isMatchSavingTerm()) {
            System.out.printf("Tất toán tiền gửi đúng hạn. Lãi suất áp dụng là: %5.2f%2s\n", getAnnualInterestRate(), "%");
            System.out.println("Tiếp tục tất toán tài khoản tiết kiệm? ");
            BankMethods.pressEnter();
            finalAmount = getTotalDeposit(BankMethods.getTimeElapsed(getDateCreated(), now)[1]);
            System.out.println("Tất toán thành công. Kiểm tra tài khoản giao dịch!");
            BankMethods.pressEnter();
        } else if ((getSavingTerm() != BankMethods.getTimeElapsed(getDateCreated(), now)[1])) {
            double interestRate = SavingAccount.getSavingInterestRate(0);
            System.out.println("Tất toán tiền gửi trước hạn!");
            System.out.printf("Thời gian tính lãi: " +
                    BankMethods.getTimeElapsed(getDateCreated(), now)[1] + " tháng " +
                    BankMethods.getTimeElapsed(getDateCreated(), now)[0] + " ngày.\n");
            System.out.printf("Lãi suất áp dụng là: %5.2f%2s\n", interestRate, "%");
            System.out.println("\nTiếp tục tất toán? ");
            BankMethods.pressEnter();
            if (BankMethods.getTimeElapsed(getDateCreated(), now)[0] != 0) {
                finalAmount = getTotalDeposit(BankMethods.getTimeElapsed(getDateCreated(), now)[1], interestRate);
                finalAmount += getRemainDeposit(BankMethods.getTimeElapsed(getDateCreated(), now)[0]);
                System.out.println("Tất toán thành công. Kiểm tra tài khoản giao dịch!");
                BankMethods.pressEnter();
            } else {
                finalAmount = getTotalDeposit(BankMethods.getTimeElapsed(getDateCreated(), now)[1], interestRate);
                System.out.println("Tất toán thành công. Kiểm tra tài khoản giao dịch!");
                BankMethods.pressEnter();
            }
        }
        return finalAmount;
    }
   
}
