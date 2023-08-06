package bai_tap_ngan_hang;

import java.util.ArrayList;
import java.util.Scanner;

public class Main extends BankMethods {
	

	public static void main(String[] args) {
		
	  int selector ; 
	  ArrayList<CurrentAccount> currentAccount = new ArrayList<CurrentAccount>();
	  do {
		 BankMethods.printMainMenu();
		 Scanner sn = new Scanner(System.in);
		 selector = sn.nextInt() ;
		 switch (selector) {
		case 11: {
			if(currentAccount.isEmpty()) {
				 currentAccount.add(CurrentAccount.CreateAccount());
                 BankMethods.pressEnter();
                 break;
			}else {
				System.out.println("Tai khoan da duoc tao");
				 BankMethods.pressEnter();
                 break;
		}
	  }
		// rút tiền
		case 12 :{
		   if(currentAccount.isEmpty()) {
			   System.out.println("Ban can tao tai khoan truoc");
			   BankMethods.pressEnter();
			   break ;
		   }else {
			   currentAccount.get(0).withdraw();
			   System.out.println("rut tien thanh cong");
			   BankMethods.pressEnter();
               break;
		   }
		// nap tien	   
		   }
		case 13 :{
			if(currentAccount.isEmpty()) {
				   System.out.println("Ban can tao tai khoan truoc");
				   BankMethods.pressEnter();
				   break ;
			}else {
				currentAccount.get(0).deposit();
				System.out.println(" banj nap tien thanh cong");
				BankMethods.pressEnter();
				break ;
			}
		}
		// in thong tin tai khoan
		case 14:{
			if(currentAccount.isEmpty()) {
				   System.out.println("Ban can tao tai khoan truoc");
				   BankMethods.pressEnter();
				   break ;
			}else {
				currentAccount.get(0).printCard();
				BankMethods.pressEnter();
				break ;
			}
		}
		// lich su giao dich
		case 15:{
			if(currentAccount.isEmpty()) {
				   System.out.println("Ban can tao tai khoan truoc");
				   BankMethods.pressEnter();
				   break ;
			}else if (currentAccount.get(0).getTransaction().isEmpty()) {
				System.out.println("Chua co lich su giao dich nao");
				BankMethods.pressEnter();
				break ;
			}else {
				BankMethods.printTransactionActivityHead();
				currentAccount.get(0).getTransaction().forEach((transaction) -> transaction.printTransaction());
				BankMethods.pressEnter();
				break ;
			}
		
		}
		// gui tiet kiem
		case 21:{ 
			if(currentAccount.isEmpty()) {
				   System.out.println("Ban can tao tai khoan truoc");
				   BankMethods.pressEnter();
				   break ;
			}else {
				currentAccount.get(0).getSavingAccount().add(currentAccount.get(0).createSavingAccount());
				 System.out.println("gui tiet kiem thanh cong");
				 BankMethods.pressEnter();
				 break ;
			}
		}
		//Tinh lai xuat
		case 22:{
			if(currentAccount.isEmpty()) {
				   System.out.println("Ban can tao tai khoan truoc");
				   BankMethods.pressEnter();
				   break ;
			}else {
				if(currentAccount.get(0).getSavingAccount().isEmpty()) {
					System.out.println("Chua co ta khaon tiet kiem");
					BankMethods.pressEnter();
					break ;
				}else {
					currentAccount.get(0).getSavingAccount().get(0).SavingCalculator();
					BankMethods.pressEnter();
					break ;
				}
			}
		}
		// in thong tin tai khoanr tiet kiem
		case 23:{
			if(currentAccount.isEmpty()) {
				   System.out.println("Ban can tao tai khoan truoc");
				   BankMethods.pressEnter();
				   break ;
			}else {
				if(currentAccount.get(0).getSavingAccount().isEmpty()) {
					System.out.println("Chua co ta khaon tiet kiem");
					BankMethods.pressEnter();
					break ;
				}else {
					BankMethods.printSavingAccount();
					currentAccount.get(0).getSavingAccount().get(0).printSavingAccount();
					BankMethods.pressEnter();
					break ;
				}
			}
		}
		//Tất toán tiền gửi 
		case 24:{
			if(currentAccount.isEmpty()) {
				   System.out.println("Ban can tao tai khoan truoc");
				   BankMethods.pressEnter();
				   break ;
			}else {
				if(currentAccount.get(0).getSavingAccount().isEmpty()) {
					System.out.println("Chua co ta khaon tiet kiem");
					BankMethods.pressEnter();
					break ;
				}else {
					 currentAccount.get(0).deposit(currentAccount.get(0).getSavingAccount().get(0).finalSettlement());
                     currentAccount.get(0).getSavingAccount().clear();
                     break;
				}
			}
		
		}
		// vay tien
		case 31:{
			 if(currentAccount.isEmpty()) {
				  System.out.println("Ban can tao tai khoan truoc");
				  BankMethods.pressEnter();
				  break ;
			 }else {
				 if(currentAccount.get(0).getLoanAccount().isEmpty()) {
					  System.out.println("Chua co ta khoan vay");
					  BankMethods.pressEnter();
					  break ;
				 }else {
					 currentAccount.get(0).getLoanAccount().add(currentAccount.get(0).createLoanAccount()); 
					  BankMethods.pressEnter();
					  break ;
				 }
			 }
		}
		case 32:{
			 if(currentAccount.isEmpty()) {
				  System.out.println("Ban can tao tai khoan truoc");
				  BankMethods.pressEnter();
				  break ;
			 }else {
				 if(currentAccount.get(0).getLoanAccount().isEmpty()) {
					  System.out.println("Chua co ta khoan vay");
					  BankMethods.pressEnter();
					  break ;
				 }else {
					  currentAccount.get(0).getLoanAccount().get(0).printLoanAccount();
                      BankMethods.pressEnter();
                      break;
				 }
			 }
		}
		// tat toan khoan vay
		case 33 :{
		  if(currentAccount.isEmpty()) {
			  System.out.println("Ban can tao tai khoan truoc");
			  BankMethods.pressEnter();
			  break ;
		  }else {
			  if(currentAccount.get(0).getLoanAccount().isEmpty()) {
				  System.out.println("Chua co ta khoan vay");
				  BankMethods.pressEnter();
				  break ;
			  }else {
				  currentAccount.get(0).withdraw(currentAccount.get(0).getLoanAccount().get(0).finalSettlement());
				  currentAccount.get(0).getLoanAccount().clear();
                  break;
			  }
		  }
		}
		//tinh lai vay
		case 34:{
			if(currentAccount.isEmpty()) {
				  System.out.println("Ban can tao tai khoan truoc");
				  BankMethods.pressEnter();
				  break ;
			}else {
				 if(currentAccount.get(0).getLoanAccount().isEmpty()) {
					  System.out.println("Chua co tai khoan vay");
					  BankMethods.pressEnter();
					  break ;
				  }else {
					  currentAccount.get(0).getLoanAccount().get(0).LoanCalculator();
					  BankMethods.pressEnter();
					  break ;
				  }
			}
		}
		
		
	 }
	  } while(selector != 0);
	  
	  
	}
}

