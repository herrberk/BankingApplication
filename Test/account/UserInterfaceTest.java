package account;

import static org.junit.Assert.*;

import org.junit.Test;

public class UserInterfaceTest {

    /**
     * Successful ImageIcon creation
     */
    @Test
    public void testCreateImageIcon(){
        String path = "./images/Admin Login.png";
        assertNotNull(UserInterface.createImageIcon(path));
    }

    /**
     * Unsuccessful ImageIcon creation
     */
    @Test
    public void testCreateImageIcon2(){
        String path = "./images/unknownimage.png";
        assertNull(UserInterface.createImageIcon(path));
    }
/* Test successful, method made private
    @Test
    public void testIsPassWordCorrect(){
        char[] correct = {'b', 'e', 'r', 'k', '1', '2', '3'};
        char[] wrong = {'w', 'r', 'o', 'n', 'g', 'g', 'g'};
        assertTrue(UserInterface.isPasswordCorrect(correct));
        assertFalse(UserInterface.isPasswordCorrect(wrong));
    }
    */
}
