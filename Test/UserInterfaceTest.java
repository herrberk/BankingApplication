import static org.junit.Assert.*;
import org.junit.Test;

public class UserInterfaceTest {

    /**
     * Successfull ImageIcon creation
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
}
