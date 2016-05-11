package account;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;


class Admin extends JFrame implements ActionListener {

    private static final Color backgroundColor = Color.WHITE;
    private static final Color foregroundColor = Color.BLACK;
    private static final Color successColor = Color.GREEN;
    private static final Color failureColor = Color.RED;
    private static final Color regularColor = Color.BLUE;

    private static final Font font = new Font("SansSerif", Font.BOLD, 16);
    private static final Font font1 = new Font("SansSerif", Font.BOLD, 14);
    private static final Font font2 = new Font("SansSerif", Font.BOLD, 22);
    private static final Font font3 = new Font("SansSerif",  Font.ITALIC, 14);
    private String select=null;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JButton button;
    private JLabel last;
    private int newID;
    private ChequingAccount user;
    private ArrayList<String> myList;


    Admin() {
        super("**** Admin Console  ****");

        initialize();

        // create an ID for the new user
        newID = createID(myList);

        user = new ChequingAccount(newID);


    }

    /**
     * Retrieves the user information
     */
    private void getUserData(int ID){

        user.setID(ID);
        textField1.setText(user.getName());
        textField2.setText(user.getLastName());
        textField3.setText(Integer.toString(user.getSIN()));
        textField4.setText(user.getBirthDate());
        textField5.setText(Integer.toString(user.getBalLeft()));
        textField6.setText(Integer.toString(user.getBalRight()));
        textField7.setText(user.getCurrency());
        last.setText(user.getLastActivity());

    }

    @SuppressWarnings("unchecked")
    /**
     * Initializes and creates all the visual elements required for this Frame
     */
    private void initialize() {
        setSize(500, 600);
        setResizable(false);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JPanel infoPanel = new JPanel();
        JPanel balancePanel = new JPanel();
        infoPanel.setLayout(new GridLayout(3, 2,10,10));
        balancePanel.setLayout(new GridLayout(1, 3,10,10));

        // Top image
        JPanel topPanel = new JPanel();
        JLabel logo = new JLabel();
        logo.setHorizontalAlignment(JLabel.LEFT);
        ImageIcon icon = UserInterface.createImageIcon("./images/topAdmin.png");
        logo.setIcon(icon);

        logo.setBorder(BorderFactory.createEmptyBorder(10,20,0,20));
        topPanel.add(logo);
        topPanel.setBackground(backgroundColor);
        topPanel.setForeground(foregroundColor);

        // Bottom image
        JPanel bottomPanel = new JPanel();
        JLabel copyright = new JLabel();
        copyright.setHorizontalAlignment(JLabel.LEFT);
        ImageIcon icon2 = UserInterface.createImageIcon("./images/bottom.png");
        copyright.setIcon(icon2);

        copyright.setBorder(BorderFactory.createEmptyBorder(0,0,0,0));
        bottomPanel.add(copyright);

        // Logout Button
        JButton logout = new JButton();
        ImageIcon l1 = UserInterface.createImageIcon("./images/logout.png");
        ImageIcon l2 = UserInterface.createImageIcon("./images/logout2.png");
        logout.setIcon(l1);
        logout.setPressedIcon(l2);
        logout.setBackground(backgroundColor);
        logout.setBorderPainted(false);
        logout.setContentAreaFilled(false);
        logout.setFocusPainted(false);
        logout.setSize(new Dimension(10, 10));
        logout.setActionCommand("LOGOUT");
        logout.addActionListener(this);

        // List Selection of Accounts in the database
        JPanel p = new JPanel();

        myList= MySQLConnect.getCustomers();

        JComboBox list = new JComboBox(myList.toArray());
        list.addItem("-- Create New --");

        list.setBorder(BorderFactory.createTitledBorder(new TitledBorder("Please Select " +
                "a Customer to Edit")));
        list.setPreferredSize(new Dimension(300,60));
        list.setSelectedIndex(myList.size());
        list.setActionCommand("LIST");
        list.addActionListener(this);
        select = (String) list.getSelectedItem();

        p.add(list);
        p.add(logout);
        //Line2 ( name )
        JPanel panel1 = new JPanel();
        textField1 = new JTextField();
        textField1.setPreferredSize(new Dimension(200,40));
        textField1.setFont(font);
        textField1.setBorder(BorderFactory.createTitledBorder(new TitledBorder("Name:")));
        textField1.setForeground(foregroundColor);
        //Line3 ( last name )
        textField2 = new JTextField();
        textField2.setPreferredSize(new Dimension(200,40));
        textField2.setFont(font);
        textField2.setBorder(BorderFactory.createTitledBorder(new TitledBorder("Last Name:")));
        textField2.setForeground(foregroundColor);
        panel1.add(textField1);
        panel1.add(textField2);

        //Line4 ( Social Insurance Number )
        JPanel panel2 = new JPanel();
        textField3 = new JTextField();
        textField3.setPreferredSize(new Dimension(200,40));
        textField3.setFont(font);
        textField3.setBorder(BorderFactory.createTitledBorder(new TitledBorder("SIN:")));
        textField3.setForeground(foregroundColor);
        //Line5 ( BirthDate )
        textField4 = new JTextField();
        textField4.setPreferredSize(new Dimension(200,40));
        textField4.setFont(font);
        textField4.setBorder(BorderFactory.createTitledBorder(new TitledBorder("Birth Date:")));
        textField4.setForeground(foregroundColor);
        panel2.add(textField3);
        panel2.add(textField4);


        //Balance Field
        JPanel panel3 = new JPanel();
        textField5 = new JTextField();
        textField5.setPreferredSize(new Dimension(150,40));
        textField5.setFont(font);
        textField5.setHorizontalAlignment(SwingConstants.RIGHT);
        textField5.setBorder(BorderFactory.createTitledBorder(new TitledBorder("Balance:")));
        textField5.setForeground(foregroundColor);
        JLabel dot = new JLabel(".");
        dot.setFont(font2);
        textField6 = new JTextField();
        textField6.setPreferredSize(new Dimension(100,40));
        textField6.setFont(font);
        textField6.setBorder(BorderFactory.createTitledBorder(new TitledBorder("Balance:")));
        textField6.setForeground(foregroundColor);
        textField7 = new JTextField();
        textField7.setPreferredSize(new Dimension(90,40));
        textField7.setFont(font);
        textField7.setBorder(BorderFactory.createTitledBorder(new TitledBorder("Currency:")));
        textField7.setForeground(foregroundColor);

        panel3.add(textField5);
        panel3.add(dot);
        panel3.add(textField6);
        panel3.add(textField7);


        //SAVE Button
        JPanel panel4 = new JPanel();
        button = new JButton("SAVE");
        button.setPreferredSize(new Dimension(140,40));
        button.setFont(font1);
        button.setBackground(Color.BLUE);
        button.setForeground(backgroundColor);
        button.setActionCommand("SAVE");
        button.addActionListener(this);
        panel4.add(button);

        //Last Activity Line
        JPanel lastActivity = new JPanel();
        JLabel la = new JLabel("Last Activity: ");
        la.setFont(font3);
        last = new JLabel("N/A");
        last.setFont(font3);
        lastActivity.add(la);
        lastActivity.add(last);


        JPanel midPanel = new JPanel();
        JPanel bothPanel = new JPanel();
        bothPanel.setLayout(new GridLayout(2,1));

        infoPanel.add(p);
        infoPanel.add(panel1);
        infoPanel.add(panel2);

        balancePanel.add(panel3);

        bothPanel.add(panel4);
        bothPanel.add(lastActivity);

        midPanel.add(infoPanel);
        midPanel.add(balancePanel);
        midPanel.add(bothPanel);


        this.add(topPanel,BorderLayout.PAGE_START);
        this.add(midPanel,BorderLayout.CENTER);
        this.add(bottomPanel,BorderLayout.PAGE_END);
    }

    /**
     * Creates a new ID for the new user
     * @param list of type of ArrayList<String>
     * @return ID of type int
     */
    private static int createID(ArrayList<String> list){

        int newID;
        Random random = new Random();
        newID = random.nextInt(1000000);

        ArrayList<Integer> newList = new ArrayList<>();

        for(String s : list) {
            try {
                newList.add(Integer.parseInt(s));
            } catch(NumberFormatException nfe) {
                System.out.println("Error -> Could not parse " + nfe);
            }
        }
            while (newList.contains(newID)) {
                newID = random.nextInt(1000000);
            }

       return newID;
    }

    /**
     * Updates the current user's info to be displayed
     * @param user of type ChequingAccount
     * @throws Exception
     */
    private void updateInfo(ChequingAccount user) throws Exception{
        Boolean[] isSuccess = new Boolean[7];

        isSuccess[0] = user.setName(textField1.getText());
        isSuccess[1] = user.setLastName(textField2.getText());

        try {
            isSuccess[2] = user.setSIN(Integer.parseInt(textField3.getText()));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error --> Please Enter a valid SIN");
        }

        isSuccess[3] = user.setBirthDate(textField4.getText());

        try {
            isSuccess[4] = user.setBalLeft(Integer.parseInt(textField5.getText()));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Error --> Please Enter an Integer");
        }

        try {
            isSuccess[5] = user.setBalRight(Integer.parseInt(textField6.getText()));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Error --> Please Enter an Integer");
        }

        isSuccess[6] = user.setCurrency(textField7.getText());


        for (Boolean element : isSuccess) {
            if (!element) {
                throw new Exception();
            }
        }
        button.setBackground(successColor);



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        eventHandler(e);
    }

    @SuppressWarnings("unchecked")
    /**
     * Handles the events depending on their status code
     * @param e of type ActionEvent
     */
    private void eventHandler(ActionEvent e){

        JComboBox<String> cb;

        // Event 1 ---> User Clicks on the Selection Box
        if ("LIST".equals(e.getActionCommand())) {
            cb = (JComboBox<String>) e.getSource();
            button.setBackground(regularColor);

            if (e.getSource() instanceof JComboBox) {
                select = (String) cb.getSelectedItem();
                //System.out.println(Integer.parseInt(select));
                try{
                    getUserData(Integer.parseInt(select));
                }catch(Exception ex){
                    textField1.setText("");
                    textField2.setText("");
                    textField3.setText("");
                    textField4.setText("");
                    textField5.setText("");
                    textField6.setText("");
                    textField7.setText("");
                    last.setText("N/A");
                }
            }
        }

        // Event 2 - Admin clicks on SAVE button
        try {
            if ("SAVE".equals(e.getActionCommand())) {

                if(select.equals("-- Create New --")){


                    ChequingAccount newUser = new ChequingAccount(newID);
                    newUser.setID(newID);
                    updateInfo(newUser);
                    newUser.createAccount(newID);

                }
                else {
                    updateInfo(user);
                }
            }
        } catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error --> Cannot Write to the Database");

            button.setBackground(failureColor);

        }

        //Event 3 - User presses on Logout button
        if("LOGOUT".equals(e.getActionCommand())){
            JOptionPane.showMessageDialog(null,"Logged out successfully!");
            System.exit(0);
        }
    }

    /*public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(() -> new Admin().setVisible(true));
    }*/
}
