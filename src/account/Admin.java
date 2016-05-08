package account;

import javax.swing.*;
import java.awt.*;


class Admin extends JFrame {


    Admin(){
        super( "**** Admin Console ****" );
        initComponents();
    }

    private void initComponents(){
        setSize( 500, 500 );
        setDefaultCloseOperation( WindowConstants.DISPOSE_ON_CLOSE );

        setLayout( new BorderLayout() );       // set the layout manager
        JLabel label = new JLabel("Hello Swing!");  // construct a JLabel
        this.add( label );
    }

    public static void main (String[] args){

        java.awt.EventQueue.invokeLater(() -> new Admin().setVisible(true));
    }


}
