package account;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * The User Interface of the Application, manages the login process, contains the main method..
 */
class UserInterface extends JPanel implements ActionListener {

    private JLabel picture;
    private JPasswordField pw;
    private JFormattedTextField username;
    private JProgressBar progress;
    private static final Color backgroundColor = Color.WHITE;
    private static final Color foregroundColor = Color.BLACK;
    private int counter=0, i=0;
    private String select=null;
    private static final int maxNumberOfTries = 5;
    private static JFrame frame;
    private JLabel status;
    private ImageIcon online;
    private ImageIcon offline;


    private Connection con=null;

    private UserInterface() {
        super(new BorderLayout(1,1));
        initialize();
    }

    /**
     * Initializes and creates all the visual elements required for this Frame
     */
    private void initialize(){

        final String[] str = { "Admin Login","Customer Login" };

        //Create a connection to the Database
        con = MySQLConnect.ConnectDB();


        //Set up the online/offline status indicator.
        status = new JLabel();
        status.setHorizontalAlignment(JLabel.LEFT);
        online = createImageIcon("./images/online.png");
        offline = createImageIcon("./images/offline.png");
        status.setBorder(BorderFactory.createEmptyBorder(10,20,0,20));

        status();


        // Initialize the 3 Panels (left - auth - right)
        JPanel leftPanel = new JPanel();
        JPanel authPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JPanel bottomPanel = new JPanel();


        // Change their layouts and background color
        leftPanel.setBackground(backgroundColor);
        authPanel.setBackground(backgroundColor);
        rightPanel.setBackground(backgroundColor);
        leftPanel.setLayout(new GridLayout(1,1));
        authPanel.setLayout(new GridLayout(4,4));
        rightPanel.setLayout(new GridLayout(4,4));

        //Create the combo box, select the item at index 1.
        JComboBox<java.lang.String> list = new JComboBox<>(str);
        list.setSelectedIndex(1);
        select = (String)list.getSelectedItem();
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

        //Set up the logo.
        JLabel logo = new JLabel();
        logo.setHorizontalAlignment(JLabel.LEFT);
        ImageIcon icon = createImageIcon("./images/logo.png");
        logo.setIcon(icon);
        logo.setBorder(BorderFactory.createEmptyBorder(10,20,0,20));


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
        username = new JFormattedTextField();
        username.setHorizontalAlignment(SwingConstants.CENTER);
        username.setBorder(BorderFactory.createTitledBorder(new TitledBorder("User Name:")));
        username.setBackground(backgroundColor);
        username.setForeground(foregroundColor);
        username.setSelectedTextColor(backgroundColor);
        username.setSelectionColor(foregroundColor);
        username.setActionCommand("ENTER");
        username.addActionListener(this);

        // Create and set up the login button
        JButton login = new JButton();
        ImageIcon icon2 = createImageIcon("./images/login.png");
        ImageIcon icon3 = createImageIcon("./images/login2.png");
        login.setIcon(icon2);
        login.setPressedIcon(icon3);
        login.setBackground(backgroundColor);
        login.setBorderPainted(false);
        login.setContentAreaFilled(false);
        login.setFocusPainted(false);
        login.setSize(new Dimension(10, 10));
        login.setActionCommand("LOGIN");
        login.addActionListener(this);


        // Progress Bar
        progress = new JProgressBar();
        progress.setValue(0);
        progress.setBackground(backgroundColor);
        progress.setMaximum(100);
        progress.setPreferredSize(new Dimension(600,20));
        progress.setBorderPainted(false);


        //Add Elements to the page
        JLabel pad1 = new JLabel("-----------------------------------------------------");

        leftPanel.add(logo,LEFT_ALIGNMENT);

        authPanel.add(list);
        authPanel.add(pad1);
        authPanel.add(username,BOTTOM_ALIGNMENT);
        authPanel.add(pw,BOTTOM_ALIGNMENT);
        rightPanel.add(status,LEFT_ALIGNMENT);
        rightPanel.add(picture, LEFT_ALIGNMENT);
        rightPanel.add(login,LEFT_ALIGNMENT);
        bottomPanel.add(progress);

        this.add(authPanel,BorderLayout.CENTER);
        this.add(leftPanel, BorderLayout.WEST);
        this.add(rightPanel, BorderLayout.EAST);
        this.add(bottomPanel,BorderLayout.SOUTH);
        this.setBorder(BorderFactory.createEmptyBorder(20,40,20,20));
    }

    /**
     * Closes the Frame after successful login
     */
    private void close(){
        WindowEvent wce = new WindowEvent(frame,WindowEvent.WINDOW_CLOSING);
        Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wce);
    }

    private void status(){
        if(MySQLConnect.status){
            status.setIcon(online);
        } else status.setIcon(offline);
    }

    /**
     * Listens to the events */
    public void actionPerformed(ActionEvent e) {

        eventHandler(e);

    }

    /**
     * Handles the events depending on their status code
     * @param e of type ActionEvent
     */
    @SuppressWarnings("unchecked")
    private void eventHandler(ActionEvent e) {

        status();

        JComboBox<String> cb;

        // Event 1 ---> User Clicks on the Selection Box
        if ("LIST".equals(e.getActionCommand())) {
            cb = (JComboBox<String>) e.getSource();

            if (e.getSource() instanceof JComboBox) {
                select = (String) cb.getSelectedItem();
                updateLabel(select);
            }
        }

        // Event 2 ---> User enters a Username and a Password and presses on 'Enter'
        if ("ENTER".equals(e.getActionCommand()) || "LOGIN".equals(e.getActionCommand())) {


            try {
                 if (isLoginCorrect() && counter <= maxNumberOfTries ) {
                     progressBar(true);
                     // If admin is logging in
                     if (select.equals("Admin Login") && username.getText().equals("admin")) {
                         // JOptionPane.showMessageDialog(null, "Welcome Admin!");

                         // Close the current page and move to the Admin Panel
                         Admin admin = new Admin();
                         admin.setVisible(true);
                         close();
                         con.close();  //close the connection

                     }
                     else if (select.equals("Admin Login") && !username.getText().equals("admin")) {
                         cannotLogin();
                     }
                     // If admin or customers are logging in
                     else if(select.equals("Customer Login")&& !username.getText().equals("admin")){
                         // Close the current page and move to the Admin Panel
                         Customer customer = new Customer(username.getText());
                         customer.setVisible(true);
                         close();
                         con.close(); //close the connection
                    }
                 }
                 else {
                    cannotLogin();
                 }

            } catch(Exception e3){
                 JOptionPane.showMessageDialog(null, "Error --> Login Error..");
                //e3.printStackTrace();

            }
        }
    }

    /**
     * Login Failure
     */
    private void cannotLogin(){
        counter++;

        if(counter==maxNumberOfTries){
            counter=0;
            JOptionPane.showMessageDialog(this,
                    "Access Denied !(" + (counter) + " Remaining)");
            System.exit(-1);
        }
        progressBar(false);
        JOptionPane.showMessageDialog(this,
                "Invalid entry. Try again.(" + (maxNumberOfTries - counter) + " Remaining)",
                "Error Message",
                JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Progress bar implementation (Red(%50) or Green(%100))
     * @param b of type boolean
     */
    private void progressBar(boolean b){
        if(b) {
            progress.setForeground(Color.GREEN);
            while (i < 100) {
                i++;
                progress.setValue(i);
            }
        }
        else{
            progress.setForeground(Color.RED);
            while (i < 100) {
                i++;
                progress.setValue(i);
                }
            }
        }

    /**
     * Method to check the login information
     * @return isCorrect of type boolean
     */
    private boolean isLoginCorrect() {

        try {
            String sql = "SELECT * FROM Account WHERE Username =? and Password =?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, username.getText());
            pst.setString(2, String.valueOf(pw.getPassword()));
            ResultSet rs = pst.executeQuery();

            if (rs.next())
                return true;

            } catch (Exception e2) {
                JOptionPane.showMessageDialog(null, "Error --> System is Offline..");
            }

        return false;
   }

    /**
     * Updates the label of the selected image
     * @param name of type String
     */
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

        frame = new JFrame("Welcome to BotBank Login!");
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setResizable(false);

        //Create and set up the content pane.
        JComponent newContentPane = new UserInterface();
        newContentPane.setBackground(backgroundColor);
        newContentPane.setPreferredSize(new Dimension(700,280));
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