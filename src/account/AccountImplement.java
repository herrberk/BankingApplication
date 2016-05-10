package account;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

class AccountImplement implements Account{

	private int ID;
	private int balLeft, balRight, SIN;
	private String lastActivity;
	private String name, lastName, birthDate, currency;
	private boolean accountInUse;
	private Connection con=null;
	private Statement statement;

	/**
	 * Constructor Method - connects to the database and loads the user data
	 */
	AccountImplement() {

		//Create a connection to the Database
		con = MySQLConnect.ConnectDB();

		//load user data
		loadUserData(1);

	}

	private void loadUserData(int receivedID){

		try {
			statement = con.createStatement();
			String sql = "SELECT accountID, name, lastname, SIN, birthdate,balleft,balright,currency," +
					"lastactivity FROM bankdb WHERE accountID = " + receivedID;
			ResultSet rs = statement.executeQuery(sql);

			while(rs.next()){
				//Retrieve by column name
				ID = rs.getInt("accountID");
				name  = rs.getString("name");
				lastName = rs.getString("lastname");
				SIN = rs.getInt("SIN");
				birthDate = rs.getString("birthdate");
				balLeft = rs.getInt("balleft");
				balRight = rs.getInt("balright");
				currency = rs.getString("currency");
				lastActivity = rs.getString("lastactivity");

			}
			rs.close();

		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public boolean setName(String name) {
		 this.name=name;
		 boolean isSuccess;

		// Name must be alphabetical
		 try {
			 if(!name.matches("[A-Za-z]+")){
				 throw new Exception();
			 }
			 else {

				 statement = con.createStatement();
				 String sql = "UPDATE bankdb " +
						 "SET name='" + name + "' WHERE accountID = " + ID;
				 statement.executeUpdate(sql);
				 isSuccess = true;
			 }
		 } catch (Exception e) {
			 JOptionPane.showMessageDialog(null,"Error --> Cannot Update Name");

			 isSuccess = false;
		 }

		return isSuccess;
	 }

	@Override
	public boolean setLastName(String lastName) {
		 this.lastName=lastName;
		 boolean isSuccess;

		 try {
			 if(!lastName.matches("[A-Za-z]+")){
				 throw new Exception();
			 }
			 else {
				 statement = con.createStatement();
				 String sql = "UPDATE bankdb " +
						 "SET lastname='" + lastName + "' WHERE accountID = " + ID;
				 statement.executeUpdate(sql);
				 isSuccess = true;
			 }

		 } catch (Exception e) {
			 JOptionPane.showMessageDialog(null,"Error --> Cannot Update Last Name");
			 isSuccess = false;
		 }

		 return isSuccess;
	 }

	@Override
	public boolean setBirthDate(String birthDate) {
		 this.birthDate = birthDate;
		 boolean isSuccess;

		 try {
			 if(!birthDate.matches("\\d{2}-\\d{2}-\\d{4}")){
				 throw new Exception();
			 }
			 else {

				 statement = con.createStatement();
				 String sql = "UPDATE bankdb " +
						 "SET birthdate='" + birthDate + "' WHERE accountID = " + ID;
				 statement.executeUpdate(sql);
				 isSuccess = true;
			 }

		 } catch (Exception e) {
			 JOptionPane.showMessageDialog(null,"Error --> Cannot Update Birth Date");

			 isSuccess = false;
		 }

		 return isSuccess;
	 }

	@Override
	public boolean setSIN(int SIN) {
		 this.SIN = SIN;
		 boolean isSuccess;

		 try {
			 if (String.valueOf(SIN).length() != 7){
				 throw new Exception();
			 }
			 else {
				 statement = con.createStatement();
				 String sql = "UPDATE bankdb " +
						 "SET SIN=" + SIN + " WHERE accountID = " + ID;
				 statement.executeUpdate(sql);
				 isSuccess = true;
			 }

		 } catch (Exception e) {
			 JOptionPane.showMessageDialog(null,"Error --> Cannot Update SIN");

			 isSuccess = false;
		 }

		 return isSuccess;
	 }

	@Override
	public boolean setID(int ID) {
 		 this.ID = ID;

		// Incomplete

		 return false;
	 }

	@Override
	public boolean setBalLeft(int balLeft) {
		 this.balLeft = balLeft;
		 boolean isSuccess;

		 try {
			 statement = con.createStatement();
			 String sql = "UPDATE bankdb " +
					 "SET balleft=" + balLeft +" WHERE accountID = " + ID;
			 statement.executeUpdate(sql);
			 isSuccess = true;

		 } catch (Exception e) {
			 JOptionPane.showMessageDialog(null,"Error --> Cannot Update Balance");

			 isSuccess = false;
		 }

		 return isSuccess;
	 }

	@Override
	public boolean setBalRight(int balRight) {
		 this.balRight = balRight;
		 boolean isSuccess;

		 try {
			 statement = con.createStatement();
			 String sql = "UPDATE bankdb " +
					 "SET balright=" + balRight +" WHERE accountID = " + ID;
			 statement.executeUpdate(sql);
			 isSuccess = true;

		 } catch (Exception e) {
			 JOptionPane.showMessageDialog(null,"Error --> Cannot Update Balance");

			 isSuccess = false;
		 }

		 return isSuccess;
	 }

	@Override
	public boolean setCurrency(String currency) {
		 this.currency = currency;
		 boolean isSuccess;

		 try {
			 statement = con.createStatement();
			 String sql = "UPDATE bankdb " +
					 "SET currency='" + currency +"' WHERE accountID = " + ID;
			 statement.executeUpdate(sql);
			 isSuccess = true;

		 } catch (Exception e) {
			 JOptionPane.showMessageDialog(null,"Error --> Cannot Update Currency");

			 isSuccess = false;
		 }

		 return isSuccess;
	 }

	@Override
	public boolean setLastActivity(String lastActivity) {
		 this.lastActivity = lastActivity;
		 boolean isSuccess;

		 try {
			 statement = con.createStatement();
			 String sql = "UPDATE bankdb " +
					 "SET lastactivity='" + lastActivity +"' WHERE accountID = " + ID;
			 statement.executeUpdate(sql);
			 isSuccess = true;

		 } catch (Exception e) {
			 JOptionPane.showMessageDialog(null,"Error --> Cannot Update Last Activity");

			 isSuccess = false;
		 }

		 return isSuccess;
	 }

	@Override
	public String getName() {
		 return name;
	 }

	@Override
	public String getLastName() {
		 return lastName;
	 }

	@Override
	public String getBirthDate() {
		 return birthDate;
	 }

	@Override
	public int getSIN() {
		 return SIN;
	 }

	@Override
	public int getID() {
		 return ID;
	 }

	@Override
	public int getBalLeft() {
		 return balLeft;
	 }

	@Override
	public int getBalRight() {
		 return balRight;
	 }

	@Override
	public String getCurrency() {
		return currency;
	}

	@Override
	public String getLastActivity() {
		 return lastActivity;
	 }

	/**
	 * Withdraw method is used to withdraw multiples of 20 from the balance
	 */
	public boolean withdraw(int amount) {
		if(balLeft<amount){
			JOptionPane.showMessageDialog(null,"Error --> Amount exceeds the balance!  ");
			return false;
		}
		if(amount%20 == 0){
			balLeft -= amount;
			setBalLeft(balLeft);

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

			int temp = balLeft;
			balLeft +=amount;
			setBalLeft(balLeft);

		return balLeft > temp;

	}

	/**
	 * Creates an account with the given information
	 */
	public boolean createAccount(String name, String lastName, String birthDate, int SIN, int ID, String currency) {

		if(!accountInUse){
			this.SIN = SIN;
			this.ID = ID;
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
		if ( this.ID == accountID ){
			name = lastName = birthDate = null;
			lastActivity=null;
			this.ID = 0;
			balLeft=balRight=SIN=0;
			accountInUse=false;
			return true;
		}

		else return false;
	}

}
