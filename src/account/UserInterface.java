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
    private JFormattedTextField username;
    private static final Color backgroundColor = Color.WHITE;
    private static final Color foregroundColor = Color.BLACK;
    private int counter=0;

    // Constructor
    private UserInterface() {
        super(new BorderLayout(1,1));

        final String[] str = { "Admin Login","Customer Login" };

        // Initialize the 3 Panels (left - auth - right)
        JPanel leftPanel = new JPanel();
        JPanel authPanel = new JPanel();
        JPanel rightPanel = new JPanel();

        // Change their layouts and background color
        leftPanel.setBackground(backgroundColor);
        authPanel.setBackground(backgroundColor);
        rightPanel.setBackground(backgroundColor);
        leftPanel.setLayout(new GridBagLayout());
        authPanel.setLayout(new GridLayout(4,4));

        //Create the combo box, select the item at index 1.
        JComboBox<java.lang.String> list = new JComboBox<>(str);
        list.setSelectedIndex(1);
        list.setBorder(BorderFactory.createTitledBorder(new TitledBorder("Select Role:")));
        list.setBackground(backgroundColor);
        list.setForeground(foregroundColor);
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
        pw.setHorizontalAlignment(SwingConstants.CENTER);
        pw.setMinimumSize(new Dimension(400, 20));
        pw.setText("**********");
        pw.setBorder(BorderFactory.createTitledBorder(new TitledBorder("Password:")));
        pw.setBackground(backgroundColor);
        pw.setForeground(foregroundColor);
        pw.setSelectedTextColor(backgroundColor);
        pw.setSelectionColor(foregroundColor);
        pw.setActionCommand("ENTER");
        pw.addActionListener(this);

        //Set up the username field
        username = new JFormattedTextField("Enter Username....");
        username.setHorizontalAlignment(SwingConstants.CENTER);
        username.setBorder(BorderFactory.createTitledBorder(new TitledBorder("User Name:")));
        username.setBackground(backgroundColor);
        username.setForeground(foregroundColor);
        username.setSelectedTextColor(backgroundColor);
        username.setSelectionColor(foregroundColor);
        username.setActionCommand("ENTER");
        username.addActionListener(this);

        //Add Elements to the page
        JLabel pad1 = new JLabel();
        leftPanel.add(list);
        authPanel.add(pad1);
        authPanel.add(username,BOTTOM_ALIGNMENT);
        authPanel.add(pw,BOTTOM_ALIGNMENT);
        rightPanel.add(picture, LEFT_ALIGNMENT);
        this.add(authPanel,BorderLayout.CENTER);
        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);
        this.setBorder(BorderFactory.createEmptyBorder(20,40,20,20));
    }

    /** Listens to the events */

    public void actionPerformed(ActionEvent e) {

        eventHandler(e);

    }

    /**
     * Handles the events depending on their status code
     * @param e of type ActionEvent
     */
    @SuppressWarnings("unchecked")
    private void eventHandler(ActionEvent e){

        JComboBox<String> cb;

        // Event 1 ---> User Clicks on the Selection Box
        if ("LIST".equals(e.getActionCommand())) {
            cb = (JComboBox<String>) e.getSource();

            if (e.getSource() instanceof JComboBox) {
                String st = (String) cb.getSelectedItem();
                updateLabel(st);
            }
        }

        // Event 2 ---> User enters a Username then a Password and presses on 'Enter'
        if ("ENTER".equals(e.getActionCommand())) {
            char[] input1 = username.getText().toCharArray();
            char[] input2 = pw.getPassword();

            if (counter != 3) {
                if ((isUsernameCorrect(input1) && isPasswordCorrect(input2))) {
                    JOptionPane.showMessageDialog(this,
                            "Login Successful!");
                    counter = 0;
                } else {
                    counter++;
                    JOptionPane.showMessageDialog(this,
                            "Invalid entry. Try again.(" + (3 - counter) + " Remaining)",
                            "Error Message",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
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

    /**
     * Method to check the entered username
     * @param input of type char[]
     * @return isCorrect
     */
    private static boolean isUsernameCorrect(char[] input) {
        boolean isCorrect;
        char[] correctUsername = "herrberk".toCharArray();

        isCorrect = input.length == correctUsername.length &&
                Arrays.equals(input, correctUsername);

        Arrays.fill(correctUsername, '0');

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

    /**
     * Returns an ImageIcon, or null if the path was invalid.
     */
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
        newContentPane.setBackground(backgroundColor);
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