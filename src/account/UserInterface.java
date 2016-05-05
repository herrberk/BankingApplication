package account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class UserInterface extends JPanel implements ActionListener {

    private JLabel picture;

    private UserInterface() {
        super(new BorderLayout());

        String[] str = { "Admin Login","Customer Login" };

        //Create the combo box, select the item at index 1.

        JComboBox<java.lang.String> list = new JComboBox<>(str);
        list.setSelectedIndex(1);
        list.addActionListener(this);

        //Set up the picture.
        picture = new JLabel();
        picture.setFont(picture.getFont().deriveFont(Font.ITALIC));
        picture.setHorizontalAlignment(JLabel.CENTER);
        updateLabel(str[list.getSelectedIndex()]);
        picture.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));


        picture.setPreferredSize(new Dimension(177, 122+10));

        //Lay out the demo.
        add(list, BorderLayout.PAGE_START);
        add(picture, BorderLayout.PAGE_END);
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));
    }

    /** Listens to the combo box. */
    @SuppressWarnings("unchecked")
    public void actionPerformed(ActionEvent e) {
        JComboBox<String> cb;

        cb= (JComboBox<String>)e.getSource();

        if(e.getSource() == null)
            return;
        else if(e.getSource() instanceof JComboBox ) {
            String st = (String) cb.getSelectedItem();
            updateLabel(st);
        }


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

        //Create and set up the content pane.
        JComponent newContentPane = new UserInterface();
        newContentPane.setOpaque(true); //content panes must be opaque
        newContentPane.setPreferredSize(new Dimension(500,500));
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