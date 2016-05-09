package account;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class Customer extends JFrame implements ActionListener {

    private static final Color backgroundColor = Color.WHITE;
    private static final Color foregroundColor = Color.BLACK;
    private static final Font font = new Font("SansSerif", Font.BOLD, 16);
    private static final Font font1 = new Font("SansSerif", Font.BOLD, 14);
    private static final Font font2 = new Font("SansSerif", Font.BOLD, 22);
    private static final Font font3 = new Font("SansSerif",  Font.ITALIC, 14);

    Customer(String username) {
        super("**** Customer Console : " + username + " ****");
        initialize();
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
        infoPanel.setLayout(new GridLayout(5, 1,10,10));
        balancePanel.setLayout(new GridLayout(4, 1,10,10));


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

        //Line1 (AccountID)
        JPanel p = new JPanel();
        JLabel ID = new JLabel("Account ID:"+"1234567891011");
        ID.setFont(font);
        p.add(ID);

        //Line2 ( name )
        JPanel panel1 = new JPanel();
        JTextField textField1 = new JTextField("Berk");
        textField1.setPreferredSize(new Dimension(200,40));
        textField1.setFont(font);
        textField1.setBorder(BorderFactory.createTitledBorder(new TitledBorder("Name:")));
        textField1.setForeground(foregroundColor);
        JButton button1 = new JButton("Update");
        button1.setPreferredSize(new Dimension(140,40));
        button1.setFont(font1);
        button1.setBackground(foregroundColor);
        button1.setForeground(backgroundColor);
        panel1.add(textField1);
        panel1.add(button1);

        //Line3 ( last name )
        JPanel panel2 = new JPanel();
        JTextField textField2 = new JTextField("Soysal");
        textField2.setPreferredSize(new Dimension(200,40));
        textField2.setFont(font);
        textField2.setBorder(BorderFactory.createTitledBorder(new TitledBorder("Last Name:")));
        textField2.setForeground(foregroundColor);
        JButton button2 = new JButton("Update");
        button2.setPreferredSize(new Dimension(140,40));
        button2.setFont(font1);
        button2.setBackground(foregroundColor);
        button2.setForeground(backgroundColor);
        panel2.add(textField2);
        panel2.add(button2);

        //Line4 ( Social Insurance Number )
        JPanel panel3 = new JPanel();
        JTextField textField3 = new JTextField("67921481");
        textField3.setPreferredSize(new Dimension(200,40));
        textField3.setFont(font);
        textField3.setBorder(BorderFactory.createTitledBorder(new TitledBorder("SIN:")));
        textField3.setForeground(foregroundColor);
        JButton button3 = new JButton("Update");
        button3.setPreferredSize(new Dimension(140,40));
        button3.setFont(font1);
        button3.setBackground(foregroundColor);
        button3.setForeground(backgroundColor);
        panel3.add(textField3);
        panel3.add(button3);

        //Line5 ( BirthDate )
        JPanel panel4 = new JPanel();
        JTextField textField4 = new JTextField("25.05.1976");
        textField4.setPreferredSize(new Dimension(200,40));
        textField4.setFont(font);
        textField4.setBorder(BorderFactory.createTitledBorder(new TitledBorder("Birth Date:")));
        textField4.setForeground(foregroundColor);
        JButton button4 = new JButton("Update");
        button4.setPreferredSize(new Dimension(140,40));
        button4.setFont(font1);
        button4.setBackground(foregroundColor);
        button4.setForeground(backgroundColor);
        panel4.add(textField4);
        panel4.add(button4);


        //Balance Field
        JPanel panel5 = new JPanel();
        JLabel label5 = new JLabel("Balance: " + "3124" + "." + "56" + " " + "CAD");
        label5.setFont(font2);
        label5.setForeground(foregroundColor);
        panel5.add(label5);


        //Deposit Field
        JPanel panel6 = new JPanel();
        JTextField textField6 = new JTextField();
        textField6.setPreferredSize(new Dimension(200,40));
        textField6.setFont(font);
        textField6.setBorder(BorderFactory.createTitledBorder(new TitledBorder("Enter Amount:")));
        textField6.setForeground(foregroundColor);
        JButton button6 = new JButton("Deposit");
        button6.setPreferredSize(new Dimension(140,40));
        button6.setFont(font1);
        button6.setBackground(Color.RED);
        button6.setForeground(backgroundColor);
        panel6.add(button6);
        panel6.add(textField6);

        //Withdraw Field
        JPanel panel7 = new JPanel();
        JTextField textField7 = new JTextField();
        textField7.setPreferredSize(new Dimension(200,40));
        textField7.setFont(font);
        textField7.setBorder(BorderFactory.createTitledBorder(new TitledBorder("Enter Amount:")));
        textField7.setForeground(foregroundColor);
        JButton button7 = new JButton("Withdraw");
        button7.setPreferredSize(new Dimension(140,40));
        button7.setFont(font1);
        button7.setBackground(Color.BLUE);
        button7.setForeground(backgroundColor);
        panel7.add(button7);
        panel7.add(textField7);

        //Last Activity Line
        JPanel lastActivity = new JPanel();
        JLabel last = new JLabel("Last Activity: "+"May 9, 2016 00:28");
        last.setFont(font3);
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

    }


    public static void main(String[] args) {

        java.awt.EventQueue.invokeLater(() -> new Customer("").setVisible(true));
    }
}
