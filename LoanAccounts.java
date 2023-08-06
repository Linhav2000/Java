package bai_tap_ngan_hang;

import java.util.Date;
import java.time.LocalDate;
import java.time.ZoneId;
public class LoanAccounts implements finalSettlement {
	
	private static final double[] LOAN_INTEREST_RATE = {5, 8.4, 6.7, 12}; // lưu lãi xuất theo quý hay theo kì hạn
	private double loanAmount;
    private  int loanTerm;
    private double loanInterestRate;
    private Date dateCreated;
    
    
    
    public LoanAccounts(double loanAmount, int loanTerm, double loanInterestRate) {
		this.loanAmount = loanAmount;
		this.loanTerm = loanTerm;
		this.loanInterestRate = loanInterestRate;
		this.dateCreated = BankMethods.parseDate("16:08:23 07-09-2023");
	}
    
    // tra ve lai xuat theo kì hạn 
    public static double getLoanInterestRate(int month) {
    	if(month >=1 & month <=3) {
    		 return LOAN_INTEREST_RATE[0]; 
    	}else {
    		 return LOAN_INTEREST_RATE[month / 3 - 1];
    	}
    }
    // tiền lãi phải trả theo lãi xuất năm
    public  double getMonthlyPayment() {
    	double monthlyInterestRate = getLoanInterestRate(loanTerm) / 1200;
        double monthlyPayment = loanAmount * monthlyInterestRate / (1 -
                (Math.pow(1 / (1 + monthlyInterestRate), getLoanTerm())));
        return monthlyPayment;
    }
    // tiền lãi phải trả mỗi tháng tại tháng thứ 
    public double getMonthlyPayment(int loanTerm) {
        double monthlyInterestRate = getLoanInterestRate(loanTerm) / 1200;
        double monthlyPayment = loanAmount * monthlyInterestRate / (1 -
                (Math.pow(1 / (1 + monthlyInterestRate), loanTerm)));
        return monthlyPayment;
    }
    // ham tinh tong tien theo theo ki han vay
    public double getTotalPayMent() {
    	double totalPayMent = getMonthlyPayment() * getLoanTerm() ;
    	return totalPayMent ;
    }
    // tin tien can dong tai thang may
    public double getTotalPayMent(int month) {
    	double totalPayMent = getMonthlyPayment() * month ;
    	return totalPayMent ;
    }
    
    // thoi han cho vay phu hop kiem tra nga thang
    public boolean isMathLoanTerm() {
    	Date now = new Date() ;
    	LocalDate nowTime = now.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    	LocalDate pastTime = getDateCreated().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    	boolean isMatchDay = nowTime.getDayOfMonth() == pastTime.getDayOfMonth();
    	boolean isMatchMonth = (getLoanTerm() == BankMethods.getTimeElapsed(getDateCreated(), now)[1]);
    	return isMatchDay && isMatchMonth ;
    }
    
    public void printLoanAccount() {
        System.out.printf("%-2s%-15s%20s%6s\n", "|", "Ngày bắt đầu:", BankMethods.convertDate(getDateCreated()), "|");
        System.out.printf("%-2s%-15s%18.2f%6s\n", "|", "Số tiền vay:", getLoanAmount(), "|");
        System.out.printf("%-2s%-15s%18.2f%2s%6s\n", "|", "Lãi suất:", getLoanInterestRate(getLoanTerm()), "%", "|");
        System.out.printf("%-2s%-15s%12d%8s%6s\n", "|", "Kỳ hạn vay:", getLoanTerm(), "Tháng", "|");
        System.out.println("-------------------------------------------");
    }

    public void LoanCalculator() {
        System.out.println("                 Bảng lãi tiền vay                ");
        System.out.println("-------------------------------------------------------");
        System.out.printf("%5s%20s%16s\n", "Kỳ hạn vay  ", "Tiền trả mỗi tháng", "Tổng tiền phải trả");
        System.out.printf("#%-5d%20.2f%20.2f\n\n", getLoanTerm(), getMonthlyPayment(), getTotalPayMent());
    }
    
	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public int getLoanTerm() {
		return loanTerm;
	}

	public void setLoanTerm(int loanTerm) {
		this.loanTerm = loanTerm;
	}

	@Override
	public double finalSettlement() {
		Date now = new Date();
        double finalAmount = 0;
        if (isMathLoanTerm()) {
            System.out.printf("Tất toán khoản vay đúng hạn. Lãi suất áp dụng là: %5.2f%2s\n",
                    getLoanInterestRate(getLoanTerm()), "%");
            finalAmount = getTotalPayMent();
            System.out.printf("Số tiền cần thanh toán: %16.2f VND\n", finalAmount);
        } else {
            System.out.println("Tất toán khoản vay trước hạn!");
            System.out.printf("Thời gian tính lãi: " +
                    BankMethods.getTimeElapsed(getDateCreated(), now)[1] + " tháng | ");
            System.out.printf("Lãi suất áp dụng là: %5.2f%2s",
                    getLoanInterestRate(getLoanTerm()), "%\n");
            double payedMount = (getLoanAmount() / getLoanTerm()) *
                    BankMethods.getTimeElapsed(getDateCreated(), now)[1] +
                    getLoanAmount() * getLoanInterestRate(getLoanTerm()) / (getLoanTerm() * 100);
            double remainPayment = getLoanAmount() - payedMount;
            finalAmount = payedMount + remainPayment;
            System.out.println("                    Bảng lãi tiền vay                  ");
            System.out.println("-------------------------------------------------------");
            System.out.printf("%8s%20s%12s%12s\n", "Kỳ hạn", "Đã thanh toán", "Còn nợ", "Tổng");
            System.out.printf("%8d%18.2f%16.2f%14.2f\n", getLoanTerm(), payedMount, remainPayment, finalAmount);
        }
        System.out.println("Tiếp tục tất toán tài khoản vay? ");
        BankMethods.pressEnter();
        System.out.println("Tất toán thành công. Kiểm tra tài khoản giao dịch!");
        BankMethods.pressEnter();
        return finalAmount;	
	}       
}
