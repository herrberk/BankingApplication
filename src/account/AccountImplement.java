package account;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

 class AccountImplement implements Account{

	private long accountID;
	private int balLeft, balRight, SIN;
	private String lastActivityDate;
	private String name, lastName, birthDate, currency;
	private boolean accountInUse;
	/**
	 * Constructor Method - initializes all the instance variables
	 */
	AccountImplement() {
		name = lastName = birthDate = null;
		lastActivityDate=null;
		accountID = 0;
		balLeft=balRight=SIN=0;
        accountInUse = false;
	}

	/**
	 * Constructor Method - initializes all the instance variables with an amount
	 */
	AccountImplement(int amount) {
		name = lastName = birthDate = null;
		lastActivityDate=null;
		accountID = 0;
		balLeft=amount;
		balRight=SIN=0;
        accountInUse = false;
	}

	/**
	 * Withdraw method is used to withdraw multiples of 20 from the balance
	 */
	public boolean withdraw(int amount) {
		if(balLeft<amount){
			System.out.println("Error --> Amount exceeds the balance!  ");
			return false;
		}
		if(amount%20 == 0){
			balLeft -= amount;
			System.out.println(this.toSTR());
			return true;
		}
		else {
			System.out.println("Please select multiples of 20 !");
			return false;
		}
	}

	/**
	 * Deposit method used to deposit some amount to the account
	 */
	public boolean deposit(int amount) {
		if(accountInUse){
			int temp = balLeft;
			balLeft +=amount;

			if( balLeft > temp){
				System.out.println(this.toSTR());
				return true;
			}
			else
				return false;
		}
		else
			return false;
	}

	/**
	 * Returns information about the account
	 */
	private String toSTR(){
		StringBuilder sb = new StringBuilder();
		sb.append(System.lineSeparator());sb.append("---------------------------------");
		sb.append(System.lineSeparator());
		sb.append("Name: "); sb.append(name);
		sb.append(System.lineSeparator());
		sb.append("Lastname: "); sb.append(lastName);
		sb.append(System.lineSeparator());
		sb.append("AccountID: "); sb.append(accountID);
		sb.append(System.lineSeparator());
		sb.append("Current Balance: "); sb.append(balLeft); sb.append('.'); sb.append(balRight);
		sb.append(System.lineSeparator());
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		lastActivityDate = dateFormat.format(date);
		sb.append("Last Activity: ");sb.append(lastActivityDate);
		sb.append(System.lineSeparator()); sb.append("---------------------------------");
		sb.append(System.lineSeparator());
		return sb.toString();
	}

	/**
	 * Creates an account with the given information
	 */

	public boolean createAccount(String name, String lastName, String birthDate, int SIN, long accountID, String currency) {

		if(!accountInUse){
			this.SIN = SIN;
			this.accountID = accountID;
			this.birthDate=birthDate;
			this.name = name;
			this.lastName = lastName;
			this.currency=currency;
			accountInUse=true;
			return true;
		}
		else return false;
	}

	/**
	 * Deletes an account
	 */

	public boolean deleteAccount(long accountID) {
		if ( this.accountID == accountID ){
			name = lastName = birthDate = null;
			lastActivityDate=null;
			this.accountID = 0;
			balLeft=balRight=SIN=0;
			accountInUse=false;
			return true;
		}

		else return false;
	}


	/**
	 * Returns the account information
	 * @return toSTR()
     */
	public String balanceQuery(){
		return this.toSTR();
	}
}
