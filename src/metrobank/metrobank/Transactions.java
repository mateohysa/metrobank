package metrobank;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Transactions extends JFrame implements ActionListener{

    JLabel l1;
    JButton b1,b2,b3,b4,b5,b6,b7;
    String pin;
    private Login loginWindow;

    public Transactions(String firstName) {
        this(null, firstName); // construktor ekstra me nxjerr emrin atje
    }


    Transactions(Login login, String firstName){
        this.loginWindow = login;
        this.pin = pin;
        setTitle("MetroBank");
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("img/logo.png"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l2 = new JLabel(i3);
        l2.setBounds(30, 15, 80, 80);
        add(l2);

        l1 = new JLabel();
        l1.setText("Welcome to MetroBank, " + firstName); // Set personalized welcome message
        l1.setForeground(Color.BLACK);
        l1.setFont(new Font("Calibri", Font.BOLD, 36));
        l1.setBounds(170, 33, 700, 35);
        add(l1);


        b1 = new JButton("Deposit");
        b2 = new JButton("Cash Withdrawal");
        b3 = new JButton("Fast Cash");
        b4 = new JButton("Mini Statement");
        b5 = new JButton("PIN Change");
        b6 = new JButton("Balance Enquiry");
        b7 = new JButton("Logout");

        setLayout(null);

        l1.setBounds(170,33,700,35);
        add(l1);
        b1.setBounds(276,241,150,35);
        b1.setBackground(Color.lightGray);
        add(b1);
        b2.setBounds(557,241,150,35);
        b2.setBackground(Color.lightGray);
        add(b2);
        b3.setBounds(276,341,150,35);
        b3.setBackground(Color.lightGray);
        add(b3);
        b4.setBounds(557,341,150,35);
        b4.setBackground(Color.lightGray);
        add(b4);
        b5.setBounds(276,441,150,35);
        b5.setBackground(Color.lightGray);
        add(b5);
        b6.setBounds(557,441,150,35);
        b6.setBackground(Color.lightGray);
        add(b6);
        b7.setBounds(839,33,80,28);
        b7.setBackground(Color.lightGray);
        add(b7);


        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);


        setSize(960,720);
        getContentPane().setBackground(Color.WHITE);
        setUndecorated(false);
        setVisible(true);
        setResizable(false);



    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b7) { // If the logout button is pressed
            this.setVisible(false); // Hide or close the Transactions window
            if (loginWindow != null) {
                loginWindow.clearFields(); // Clear the fields in the Login window
                loginWindow.setVisible(true); // Show the existing Login window
            } else {
                // If loginWindow is null, create a new Login instance and clear the fields
                Login newLogin = new Login();
                newLogin.clearFields();
                newLogin.setVisible(true);
            }
        }

        //MOS I PREK KTO!!!!

        //if(ae.getSource()==b1){
        // setVisible(false);
        //  new Deposit(pin).setVisible(true);
        //  }else if(ae.getSource()==b2){
        //    setVisible(false);
        // new Withdrawl(pin).setVisible(true);
        // }else if(ae.getSource()==b3){
        //     setVisible(false);
        //     new FastCash(pin).setVisible(true);
        // }else if(ae.getSource()==b4){
        //    new MiniStatement(pin).setVisible(true);
        // }else if(ae.getSource()==b5){
        //    setVisible(false);
        //    new Pin(pin).setVisible(true);
        // }else if(ae.getSource()==b6){
        //   this.setVisible(false);
        //  new BalanceEnquiry(pin).setVisible(true);
        //  }else if(ae.getSource()==b7){
        //     System.exit(0);
        //  }
    }

    public static void main(String[] args){
        new Transactions("Test User").setVisible(true); // e ka ate test user per me dal emri ke faqja mos e luj
    }
}