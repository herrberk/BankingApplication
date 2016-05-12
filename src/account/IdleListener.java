package account;

    import javax.swing.*;
    import java.awt.*;
    import java.awt.event.AWTEventListener;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;

    /**
     * This listener reacts to timeouts and forces the user to enter his
     * password before allowing him to continue working.
     *
     * The timer is being reset at every AWT event.
     */
    class IdleListener implements AWTEventListener, ActionListener {

        private Timer timer;
        private int timeoutSeconds;
        private int counter=0;

        /**
         * Creates a new timer and registers itself as action listener.
         */
        IdleListener(int timeoutSeconds) {
            this.timeoutSeconds = timeoutSeconds;
            timer = new Timer(1000, this);
        }

        /**
         * Invoked when an event is dispatched in the AWT. Simply
         * resets the timer.
         */
        public void eventDispatched(AWTEvent event) {
            timer.restart();
            counter=0;
        }

        /**
         * Invoked when an action occurs (i.e. the timer triggers).
         */
        public void actionPerformed(ActionEvent e) {

            stopTimer();
            counter++;
            startTimer();

            if(counter== timeoutSeconds){
                stopTimer();
                JOptionPane.showMessageDialog(null,"Timeout ( Status--> Idle for 3 minutes )");
                System.exit(1);
            }
        }

        /**
         * stops the timer.
         */
        private void stopTimer() {
            timer.removeActionListener(this);
            timer.stop();
        }

        /**
         * starts the timer.
         */
        void startTimer() {
            timer = new Timer(1000, this);
            timer.start();

        }

    }


