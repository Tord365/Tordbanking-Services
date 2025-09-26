import java.util.Scanner;
// This Project represents a bank account with basic operations
   class Account{
       //Methods for the user's name, account number and balance
       private double balance = 00.00;
       private String accountNumber = "8088484763";
       private String accountName = "Norteh Tordum";
       //Methods for operations: balance recovery, deposit, withdrawal, transfer and interest calculation
         public double getBalance (){return balance ;}
         public void deposit(double amount){
            balance += amount;
        }
         public void withdrawal(double amount){balance -= amount ;}
         public void transfer(double amount, Account targetAccount){
            balance -= amount;
            targetAccount.deposit(amount);
        }
        public void applyInterest(double rate){
            double interest = balance * rate / 100;
            balance += interest;
        }
    }
class Deposit{
       public void depositMoney(Account myaccount, Scanner myscanner){
           System.out.println("Enter amount to deposit: ");
           double amount = myscanner.nextDouble();
           if (amount > 0 ){
               myaccount.deposit(amount);
               System.out.println("Deposit Successful.");
               System.out.println("New Balance: N" + myaccount.getBalance() );
           }else{
               System.out.println("Deposit Failed: Amount must be greater than zero.");
           }
    }
}
class BalanceInquiry {
       public void checkBalance(Account myaccount, Scanner myscanner) {
        System.out.println(" Do you wish to proceed with a balance inquiry? ");
        String usereply = myscanner.next();
        if (usereply.equalsIgnoreCase("yes")) {
            System.out.println(" Balance Verification Complete.\n Current Available Balance: N " + myaccount.getBalance());
        } else {
            System.out.println(" Balance Verification Failed as per your Request. ");
        }
    }
}

class Transfer {// This class handles transfer operations from the user
       public void transferMoney(Account myaccount, Scanner myscanner) {
        System.out.println("Enter Recipient Account Number :"); //Asks for recipient account number
        String targetAccount = myscanner.next();
           Account targetaccount = new Account() ;

        // Validate account format to prevent irregular Account details
if (!targetAccount.matches("\\d{10}")){
    System.out.println(" Invalid Account Number.\n Must be Exactly 10 digits. ");
    return;
        }

      System.out.println("Enter Transfer Amount:\n") ;  //Ask for deposit amount
        double amount = myscanner.nextDouble() ;

        //Validate and Perform deposit
           if (amount > 0 && amount <= myaccount.getBalance()){
    myaccount.transfer(amount, targetaccount);
    System.out.println("Transfer Successful.N "+ amount+ "Sent to " + targetAccount+ ".");
    System.out.println("New Balance: N" + myaccount.getBalance() );
}else{
    System.out.print("Transfer Failed: Insufficient Funds.");
}
    }
}

class Withdrawal{//This class handles withdrawal operation from the user's account
        public void withdrawMoney(Account myaccount, Scanner myscanner){
           System.out.println("Enter Withdrawal Amount: "); //Ask for Withdrawal amount
           double amount  = myscanner.nextDouble() ;

           //Validate and Perform withdrawal
        if (amount <= myaccount.getBalance() && amount > 0) {
            myaccount.withdrawal(amount);
               System.out.println("Withdrawal Successful. ");
               System.out.println("New Balance: N" + myaccount.getBalance() );
           }else{
               System.out.print("Withdrawal Failed: Insufficient Funds.");
           }

       }
   }

class Interest {//This class handles withdrawal operation from the user's account
        public void applyInterest(Account myaccount, Scanner myscanner) {
            System.out.println("Do you wish to Apply Interest to your account? "); //Ask user if they want to apply interest rate
            myscanner.nextLine();
            String usereply = myscanner.nextLine();// Accept user reply

        //Validate user reply and apply interest
        if (usereply.equalsIgnoreCase("yes")) {
System.out.println("Enter Interest Rate: ");
double interestvalue = myscanner.nextDouble() ;

if (interestvalue <= 0){
    System.out.println("Interest Application Failed: Invalid Rate. ");
}else{
    myaccount.applyInterest(interestvalue) ;
    System.out.println("Interest Applied Successfully. ");
    System.out.println("New Balance: N" + myaccount.getBalance() );  //Print user balance after interest application
}

        }else{
            System.out.println("Interest Application Failed As Per Your Request. ");
        }
    }
}

public class Main{
       public static void main (String []args){
           Scanner myscanner = new Scanner(System.in);
           Account myaccount = new Account();

           //Create objects for each transaction class
           Deposit deposit = new Deposit();
           Transfer transfer = new Transfer();
           Withdrawal withdrawal = new Withdrawal();
           Interest interest = new Interest();
           BalanceInquiry inquiry = new BalanceInquiry() ;

           int choice = 0; //Initializing the loop with zero so it runs

           //loop to keep showing menu until the user exits
           while(choice != 6){
               //Show Menu
               System.out.println("\n ===== WELCOME TO TORDBANKING SERVICES ===== ");
               System.out.println("1. Deposit");
               System.out.println("2. Transfer");
               System.out.println("3. Withdraw");
               System.out.println("4. Balance Inquiry");
               System.out.println("5. Interest");
               System.out.println("6. Exit");
               System.out.println(" Enter Your Choice (1-5); ");

              choice = myscanner.nextInt(); //Accepts User's choice from the menu

               // Handles User choice using switch case
               switch (choice){
                   case 1:
                       deposit.depositMoney(myaccount, myscanner);
                       break;
                   case 2:
                       transfer.transferMoney(myaccount, myscanner);
                       break;
                   case 3:
                       withdrawal.withdrawMoney(myaccount, myscanner);
                       break;
                   case 4:
                       inquiry.checkBalance(myaccount, myscanner);
                       break;
                   case 5:
                       interest.applyInterest(myaccount, myscanner);
                       break;
                   case 6:
                       System.out.println(" Thank You for Banking with TORDBANKS! ");
                       break;
                   default:
                       System.out.println("Invalid Choice: Please enter a number between 1 and 6 ");
               }
           }
           myscanner.close(); //Closes Scanner when done
       }
   }