package bai_tap_ngan_hang;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Scanner;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class BankMethods {
	
  // tra ve ngay va thang da qua
  public static int[] getTimeElapsed (Date dataCreated, Date now) {
	  int [] elapsedTimes = new int[2] ;
	  
	  long timeElapsed = now.getTime() - dataCreated.getTime() ;
	  long hourElapsed = timeElapsed / 3600000 ; //60*60*1000
	  
	  int  monthElapsed = (int) (hourElapsed /(24*30)) ;
	  int  dayElapsed = (int) (hourElapsed - (monthElapsed * 30 * 24)) / 24 ;
	  
	  elapsedTimes[0] = dayElapsed ;
	  elapsedTimes[1] = monthElapsed ;
	  
	  return elapsedTimes;
  }
  
  // ham random so acc 
  
  public static String generateAccNumber () {
	  StringBuilder strAcc = new StringBuilder("1234256");
	  for(int i=0 ;i<=5 ;i++) {
		  char strRandom = (char) ('0' + Math.random()*('9' +'0' + 1)) ;
		  strAcc.append(strRandom) ;  
	  }	
	  return strAcc.toString() ;   // chuyen doi sang string vif nos strAcc dang o builderString
  }
  
  public static void printMainMenu () {
	  System.out.println("                                      Main Menu                               ");
	  System.out.println("----------------------------------------------------------------------------------------------");
	  System.out.printf("%26s%35s%33s","Tài khoan giao dịch","Tài khoản tiết kiệm", " Tài khoản vay\n");
	  System.out.println("----------------------------------------------------------------------------------------------");
	  System.out.printf("%-20s%-23s%-25s%25s","11:Tạo tài khoản","14: Xem thông tin","21:Gửi tiết kiệm","31 :Vay tiền\n");
	  System.out.printf("%-20s%-23s%-25s%28s","12:Rút tiền", "15:Lịch sử giao dịch","22: Tính lãi xuất","32:Xem thông tin\n");
	  System.out.printf("%-20s%-23s%-25s%28s","13:Nạp tiền","", "23:Xem thông tin","33:Tất toán khoản vay\n");
	  System.out.printf("%-20s%-23s%-25s%28s","0:Thoát","","24:Tất toán tiền gửi","34:Tính lãi vay\n");
	  System.out.printf("%-20s","Chọn Chức năng : ");
	  
  }
  
  
  public static void printTransactionActivityHead() {
      System.out.printf("%65s", "-------------------------------\n");
      System.out.printf("%58s", "Lịch sử giao dịch\n");
      System.out.printf("%65s", "-------------------------------\n");
      System.out.printf("%20s%15s%15s%15s%35s\n", "Ngày        ", "Loại  ", "Biến động ", "Số dư    ", "Nội dung           ");
  }
  public static void printSavingAccount() {
	        System.out.println("-------------------------------------------");
	        System.out.println("        Thông Tin Tài Khoản Tiết Kiệm      ");
	        System.out.println("-------------------------------------------");
	    
  }
  // trả về dinh dang "dd/MM/yyyy" trả về String
  public static String convertDate(Date date) {
	  SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
      String strDate = formatter.format(date);
      return strDate;
  }
  
  public static String convertDateDetail(Date date) {
	  SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss dd-MM-yyyy");
      String strDateDetail = formatter.format(date);
      return strDateDetail;
  
  }
  // lấy một chuỗi để chuyển đổi thanh định dạng Date
  public static Date parseDate(String date) {
      try {
          return new SimpleDateFormat("HH:mm:ss dd-MM-yyyy").parse(date);
      } catch (ParseException e) {
          return null;
      }
  }
  
 
  
  public static void pressEnter() {
      System.out.print("Press \"ENTER\" to continue...");
      Scanner input = new Scanner(System.in);
      input.nextLine();
  }
  
}
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
