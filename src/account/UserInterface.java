package account;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;


class UserInterface extends JPanel implements ActionListener {

    private JLabel picture;
    private JPasswordField pw;
    private int counter=0;
    private UserInterface() {
        super(new BorderLayout(1,1));

        JPanel topPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        topPanel.setBackground(Color.ORANGE);
        bottomPanel.setBackground(Color.ORANGE);
        topPanel.setLayout(new GridBagLayout());


        final String[] str = { "Admin Login","Customer Login" };

        //Create the combo box, select the item at index 1.
        JComboBox<java.lang.String> list = new JComboBox<>(str);
        list.setSelectedIndex(1);
        list.setBorder(BorderFactory.createTitledBorder(new TitledBorder("Select Role:")));
        list.setBackground(Color.ORANGE);

        list.setActionCommand("LIST");

        list.addActionListener(this);

        //Set up the picture.
        picture = new JLabel();
        picture.setFont(picture.getFont().deriveFont(Font.ITALIC));
        picture.setHorizontalAlignment(JLabel.RIGHT);
        updateLabel(str[list.getSelectedIndex()]);
        picture.setBorder(BorderFactory.createEmptyBorder(10,20,0,20));
        picture.setPreferredSize(new Dimension(177, 122+10));

        //Set up the password field
        pw = new JPasswordField(10);
        pw.setHorizontalAlignment(SwingConstants.LEFT);
        pw.setMinimumSize(new Dimension(400, 20));
        pw.setText("******");
        pw.setActionCommand("OK");
        pw.addActionListener(this);

        //Add Elements to the page
        topPanel.add(list);
        topPanel.add(pw);
        bottomPanel.add(picture, LEFT_ALIGNMENT);

        this.add(topPanel, BorderLayout.WEST);
        this.add(bottomPanel, BorderLayout.EAST);
        setBorder(BorderFactory.createEmptyBorder(20,40,20,20));
    }

    /** Listens to the combo box. */
    @SuppressWarnings("unchecked")
    public void actionPerformed(ActionEvent e) {

        JComboBox<String> cb;

        // Event 1 ---> User Clicks on the Selection Box
        if("LIST".equals(e.getActionCommand())) {
            cb = (JComboBox<String>) e.getSource();

            if (e.getSource() instanceof JComboBox) {
                String st = (String) cb.getSelectedItem();
                updateLabel(st);
            }
        }

        // Event 2 ---> User enters a Password and presses on 'Enter'
        if("OK".equals(e.getActionCommand())) {
            char[] input = pw.getPassword();
            if(counter!=3) {
                if (isPasswordCorrect(input)) {
                    JOptionPane.showMessageDialog(this,
                            "Login Successful!");
                    counter = 0;
                } else {
                    counter++;
                    JOptionPane.showMessageDialog(this,
                            "Invalid password. Try again.(" + (3 - counter) + " Remaining)",
                            "Error Message",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
            else {
                JOptionPane.showMessageDialog(this,
                        "Please Contact the System Administrator");
                System.exit(-1);
            }
        }
    }

    /**
     * Method to check the entered password
     * @param input of type char[]
     * @return isCorrect
     */

   private static boolean isPasswordCorrect(char[] input) {
        boolean isCorrect;
        char[] correctPassword = {'b', 'e', 'r', 'k', '1', '2', '3'};

        isCorrect = input.length == correctPassword.length &&
                Arrays.equals(input, correctPassword);

        //Zero out the password.
        Arrays.fill(correctPassword, '0');

        return isCorrect;
    }


    private void updateLabel(String name) {
        ImageIcon icon = createImageIcon("./images/" + name + ".png");
        picture.setIcon(icon);
        picture.setToolTipText("A drawing of a " + name.toLowerCase());
        if (icon != null) {
            picture.setText(null);
        } else {
            picture.setText("Image not found");
        }
    }

    /** Returns an ImageIcon, or null if the path was invalid. */
     static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = UserInterface.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame;
        frame = new JFrame("Welcome to BerkBank Login!");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);

        //Create and set up the content pane.
        JComponent newContentPane = new UserInterface();
        newContentPane.setBackground(Color.ORANGE);
        newContentPane.setPreferredSize(new Dimension(500,200));
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        /*
        Schedule a job for the event-dispatching thread:
        creating and showing this application's GUI.
        */
        javax.swing.SwingUtilities.invokeLater(UserInterface::createAndShowGUI);
    }
}