package account;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;


class Customer extends JFrame implements ActionListener {

    private static final Color backgroundColor = Color.WHITE;
    private static final Color foregroundColor = Color.BLACK;
    private static final Color successColor = Color.GREEN;
    private static final Color failureColor = Color.RED;
    private static final Font font = new Font("SansSerif", Font.BOLD, 16);
    private static final Font font1 = new Font("SansSerif", Font.BOLD, 14);
    private static final Font font2 = new Font("SansSerif", Font.BOLD, 22);
    private static final Font font3 = new Font("SansSerif",  Font.ITALIC, 14);

    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField6;
    private JTextField textField7;
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JLabel ID;
    private JLabel last;
    private JLabel label6;
    private JLabel label8;
    private JLabel label9;
    private ChequingAccount user;
    private IdleListener timer;

    Customer(String username) {
        super("**** Customer Console : " + username + " ****");
        timer = new IdleListener(180);

        initialize();

        timer.startTimer();

        user = new ChequingAccount(username);
        getUserData();

    }

    /**
     * Retrieves the user information
     */
    private void getUserData(){

        textField1.setText(user.getName());
        textField2.setText(user.getLastName());
        textField3.setText(Integer.toString(user.getSIN()));
        textField4.setText(user.getBirthDate());
        label6.setText(Integer.toString(user.getBalLeft()));
        label8.setText(Integer.toString(user.getBalRight()));
        label9.setText(user.getCurrency());
        last.setText(user.getLastActivity());
        ID.setText(Integer.toString(user.getID()));

    }

    /**
     * Initializes and creates all the visual elements required for this Frame
     */
    private void initialize() {
        setSize(500, 700);
        setResizable(false);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        JPanel infoPanel = new JPanel();
        JPanel balancePanel = new JPanel();
        infoPanel.setLayout(new GridLayout(5, 1,2,2));
        balancePanel.setLayout(new GridLayout(4, 1,2,2));


        // Top image
        JPanel topPanel = new JPanel();
        JLabel logo = new JLabel();
        logo.setHorizontalAlignment(JLabel.LEFT);
        ImageIcon icon = UserInterface.createImageIcon("./images/top.png");
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

        //Line1 (AccountID)
        JPanel p = new JPanel();
        JLabel accID = new JLabel("Account ID: ");
        ID = new JLabel("N/A");
        accID.setFont(font);
        ID.setFont(font);
        p.add(accID);
        p.add(ID);
        p.add(logout);

        //Line2 ( name )
        JPanel panel1 = new JPanel();
        textField1 = new JTextField("N/A");
        textField1.setPreferredSize(new Dimension(200,40));
        textField1.setFont(font);
        textField1.setBorder(BorderFactory.createTitledBorder(new TitledBorder("Name:")));
        textField1.setForeground(foregroundColor);
        button1 = new JButton("Update");
        button1.setPreferredSize(new Dimension(140,40));
        button1.setFont(font1);
        button1.setBackground(foregroundColor);
        button1.setForeground(backgroundColor);
        button1.setActionCommand("NAME");
        button1.addActionListener(this);
        panel1.add(textField1);
        panel1.add(button1);

        //Line3 ( last name )
        JPanel panel2 = new JPanel();
        textField2 = new JTextField("N/A");
        textField2.setPreferredSize(new Dimension(200,40));
        textField2.setFont(font);
        textField2.setBorder(BorderFactory.createTitledBorder(new TitledBorder("Last Name:")));
        textField2.setForeground(foregroundColor);
        button2 = new JButton("Update");
        button2.setPreferredSize(new Dimension(140,40));
        button2.setFont(font1);
        button2.setBackground(foregroundColor);
        button2.setForeground(backgroundColor);
        button2.setActionCommand("LASTNAME");
        button2.addActionListener(this);
        panel2.add(textField2);
        panel2.add(button2);

        //Line4 ( Social Insurance Number )
        JPanel panel3 = new JPanel();
        textField3 = new JTextField("N/A");
        textField3.setPreferredSize(new Dimension(200,40));
        textField3.setFont(font);
        textField3.setBorder(BorderFactory.createTitledBorder(new TitledBorder("SIN:")));
        textField3.setForeground(foregroundColor);
        button3 = new JButton("Update");
        button3.setPreferredSize(new Dimension(140,40));
        button3.setFont(font1);
        button3.setBackground(foregroundColor);
        button3.setForeground(backgroundColor);
        button3.setActionCommand("SIN");
        button3.addActionListener(this);
        panel3.add(textField3);
        panel3.add(button3);

        //Line5 ( BirthDate )
        JPanel panel4 = new JPanel();
        textField4 = new JTextField("N/A");
        textField4.setPreferredSize(new Dimension(200,40));
        textField4.setFont(font);
        textField4.setBorder(BorderFactory.createTitledBorder(new TitledBorder("Birth Date:")));
        textField4.setForeground(foregroundColor);
        button4 = new JButton("Update");
        button4.setPreferredSize(new Dimension(140,40));
        button4.setFont(font1);
        button4.setBackground(foregroundColor);
        button4.setForeground(backgroundColor);
        button4.setActionCommand("DATE");
        button4.addActionListener(this);
        panel4.add(textField4);
        panel4.add(button4);


        //Balance Field
        JPanel panel5 = new JPanel();
        JLabel label5 = new JLabel("Balance: ");
        label6 = new JLabel("N/A");//balLeft
        JLabel label7 = new JLabel(".");
        label8 = new JLabel("N/A"); // balRight
        label9 = new JLabel("N/A");//currency

        label5.setFont(font2);
        label6.setFont(font2);
        label7.setFont(font2);
        label8.setFont(font2);
        label9.setFont(font2);
        panel5.setForeground(foregroundColor);
        panel5.add(label5);
        panel5.add(label6);
        panel5.add(label7);
        panel5.add(label8);
        panel5.add(label9);


        //Deposit Field
        JPanel panel6 = new JPanel();
        textField6 = new JTextField();
        textField6.setPreferredSize(new Dimension(200,40));
        textField6.setFont(font);
        textField6.setBorder(BorderFactory.createTitledBorder(new TitledBorder("Enter Amount:")));
        textField6.setForeground(foregroundColor);
        JButton button6 = new JButton("Deposit");
        button6.setPreferredSize(new Dimension(140,40));
        button6.setFont(font1);
        button6.setBackground(Color.RED);
        button6.setForeground(backgroundColor);
        button6.setActionCommand("DEPOSIT");
        button6.addActionListener(this);
        panel6.add(button6);
        panel6.add(textField6);

        //Withdraw Field
        JPanel panel7 = new JPanel();
        textField7 = new JTextField();
        textField7.setPreferredSize(new Dimension(200,40));
        textField7.setFont(font);
        textField7.setBorder(BorderFactory.createTitledBorder(new TitledBorder("Enter Amount:")));
        textField7.setForeground(foregroundColor);
        JButton button7 = new JButton("Withdraw");
        button7.setPreferredSize(new Dimension(140,40));
        button7.setFont(font1);
        button7.setBackground(Color.BLUE);
        button7.setForeground(backgroundColor);
        button7.setActionCommand("WITHDRAW");
        button7.addActionListener(this);
        panel7.add(button7);
        panel7.add(textField7);

        //Last Activity Line
        JPanel lastActivity = new JPanel();
        JLabel la = new JLabel("Last Activity: ");
        la.setFont(font3);
        last = new JLabel("N/A");
        last.setFont(font3);
        lastActivity.add(la);
        lastActivity.add(last);


        JPanel midPanel = new JPanel();
        infoPanel.add(p);
        infoPanel.add(panel1);
        infoPanel.add(panel2);
        infoPanel.add(panel3);
        infoPanel.add(panel4);
        balancePanel.add(panel5);
        balancePanel.add(panel6);
        balancePanel.add(panel7);
        balancePanel.add(lastActivity);

        midPanel.add(infoPanel);
        midPanel.add(balancePanel);


        this.add(topPanel,BorderLayout.PAGE_START);
        this.add(midPanel,BorderLayout.CENTER);
        this.add(bottomPanel,BorderLayout.PAGE_END);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.eventDispatched(e);
        eventHandler(e);
    }

    /**
     * Handles the events depending on their status code
     * @param e of type ActionEvent
     */
    private void eventHandler(ActionEvent e){

        Date date = new Date();
        user.setLastActivity(date.toString());
        last.setText(user.getLastActivity());


        if("LOGOUT".equals(e.getActionCommand())){
            JOptionPane.showMessageDialog(null,"Logged out successfully!");
            System.exit(0);
        }

        if ("NAME".equals(e.getActionCommand())){
            boolean isSuccess = user.setName(textField1.getText());
            if(isSuccess) button1.setBackground(successColor);
            else button1.setBackground(failureColor);
        }

        else if("LASTNAME".equals(e.getActionCommand())){
            boolean isSuccess = user.setLastName(textField2.getText());
            if(isSuccess) button2.setBackground(successColor);
            else button2.setBackground(failureColor);
        }

        else if("SIN".equals(e.getActionCommand()))  {
            try{
                boolean isSuccess = user.setSIN(Integer.parseInt(textField3.getText()));
                if(isSuccess) button3.setBackground(successColor);
                else button3.setBackground(failureColor);
            } catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null,"Error --> Please Enter an Integer");
                button3.setBackground(failureColor);
            }
        }
        else if("DATE".equals(e.getActionCommand())){
            boolean isSuccess = user.setBirthDate(textField4.getText());
            if(isSuccess) button4.setBackground(successColor);
            else button4.setBackground(failureColor);
        }

        else if("DEPOSIT".equals(e.getActionCommand()))  {
            try{
                user.deposit(Integer.parseInt(textField6.getText()));
            } catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null,"Error --> Please Enter an Integer");
            }
            label6.setText(Integer.toString(user.getBalLeft()));
        }

        else if("WITHDRAW".equals(e.getActionCommand())){
            try{
                user.withdraw(Integer.parseInt(textField7.getText()));
            } catch(NumberFormatException ex){
                JOptionPane.showMessageDialog(null,"Error --> Please Enter an Integer( Multiples of 20 )");
            }
            label6.setText(Integer.toString(user.getBalLeft()));
        }
    }

   /* public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(() -> new Customer("").setVisible(true));
    } */
}
