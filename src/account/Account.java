package account;

interface Account {
	
	 boolean withdraw(int amount);
	
	 boolean deposit(int amount);
	
	 boolean createAccount(String name,
								 String lastName,
								 String birthDate,
								 int SIN,
								 long accountID,
								 String currency);
	
	 boolean deleteAccount(long accountID);

	 String balanceQuery();
	
}
