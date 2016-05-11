package account;

interface Account {

	//Setters
	boolean setName(String name);
    boolean setLastName(String lastName);
    boolean setBirthDate(String birthDate);
    boolean setSIN(int SIN);
    boolean setID(int ID);//admin
    boolean setBalLeft(int balLeft);
    boolean setBalRight(int balRight);//admin
    boolean setCurrency(String currency); //admin
    boolean setLastActivity(String lastActivity);//auto

	// Getters
	String getName();
	String getLastName();
	String getBirthDate();
	int getSIN();
	int getID();
	int getBalLeft();
	int getBalRight();
    String getCurrency();
    String getLastActivity();

	boolean withdraw(int amount);
	
	boolean deposit(int amount);
	
	boolean createAccount(int ID);
	
	boolean deleteAccount(int accountID);




	
}
