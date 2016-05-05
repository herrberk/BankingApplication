package account;

import static org.junit.Assert.*;

import account.ChequingAccount;
import org.junit.Test;

public class ChequingTest {

	@Test
	public void testDeposit() {
		ChequingAccount user1 = new ChequingAccount();
		user1.createAccount("Berk", 
							"Soysal",
							"25.05.1992",
							 5675894,
				 			 10021213816L,
							 "CAD");
		assertEquals(true, user1.deposit(100));
		
	}
	
	@Test
	public void testWithdraw() {
		ChequingAccount user1 = new ChequingAccount(60);
		user1.createAccount("Berk",
						  	"Soysal",
							"25.05.1992",
							5675894,
							10021213816L,
							"CAD");
		assertEquals(true,user1.withdraw(20));
	}
	
	@Test
	public void testWithdraw2() {
		ChequingAccount user1 = new ChequingAccount(10);
		user1.createAccount("Berk", 
							"Soysal",
							"25.05.1992",
				  			 5675894,
							 10021213816L,
							 "CAD");
		assertEquals(false,user1.withdraw(50));
	}

	@Test
	public void testBalanceQuery(){
		ChequingAccount user1 = new ChequingAccount(100);
		user1.createAccount("Berk",
							"Soysal",
							"21.05.1988",
							5675894,
							62822752285L,
							"USD");

		assertNotNull(user1.balanceQuery());

	}
}
