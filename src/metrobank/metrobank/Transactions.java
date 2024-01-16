package metrobank;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import javax.swing.*;
import java.util.List;


public class Transactions extends JFrame implements ActionListener {

    JLabel l1;
    JButton b1, b2, b3, b4, b5, b6, b7;
    String pin;
    private Login loginWindow;
    private String username;

    public Transactions(String username) {
        this(null, username); // construktor ekstra me nxjerr emrin atje
    }


    Transactions(Login login, String username) {
        this.loginWindow = login;
        this.pin = pin;
        this.username = username;
        setTitle("MetroBank");
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("img/logo.png"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l2 = new JLabel(i3);
        l2.setBounds(30, 15, 80, 80);
        add(l2);

        l1 = new JLabel();
        l1.setText("Welcome to MetroBank, " + username); // Set personalized welcome message
        l1.setForeground(Color.BLACK);
        l1.setFont(new Font("Calibri", Font.BOLD, 36));
        l1.setBounds(170, 33, 700, 35);
        add(l1);


        b1 = new JButton("Deposit");
        b2 = new JButton("Withdraw");
        b3 = new JButton("Fast Cash");
        b4 = new JButton("Mini Statement");
        b5 = new JButton("PIN Change");
        b6 = new JButton("Balance Enquiry");
        b7 = new JButton("Logout");

        setLayout(null);

        l1.setBounds(170, 33, 700, 35);
        add(l1);
        b1.setBounds(276, 241, 150, 35);
        b1.setBackground(Color.lightGray);
        add(b1);
        b2.setBounds(557, 241, 150, 35);
        b2.setBackground(Color.lightGray);
        add(b2);
        b3.setBounds(276, 341, 150, 35);
        b3.setBackground(Color.lightGray);
        add(b3);
        b4.setBounds(557, 341, 150, 35);
        b4.setBackground(Color.lightGray);
        add(b4);
        b5.setBounds(276, 441, 150, 35);
        b5.setBackground(Color.lightGray);
        add(b5);
        b6.setBounds(557, 441, 150, 35);
        b6.setBackground(Color.lightGray);
        add(b6);
        b7.setBounds(839, 33, 80, 28);
        b7.setBackground(Color.lightGray);
        add(b7);


        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);


        setSize(960, 720);
        getContentPane().setBackground(Color.WHITE);
        setUndecorated(false);
        setVisible(true);
        setResizable(false);


    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) { // If the deposit button is pressed
            this.setVisible(false);
            new Deposit(username).setVisible(true); // Navigate to the Deposit screen
        } else if (ae.getSource() == b2) {
            this.setVisible(false);
            new Withdraw(username).setVisible(true);

        } else if (ae.getSource() == b6) {
            try {
                String filePath = "data/users.csv";
                List<String> fileContent = Files.readAllLines(Paths.get(filePath));
                for (String line : fileContent) {
                    String[] data = line.split(",");
                    if (data[0].equals(username)) {
                        int balanceIndex = data.length - 1;
                        String balance = data[balanceIndex];
                        JOptionPane.showMessageDialog(this, "Current Balance: " + balance, "Balance Inquiry", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error retrieving balance.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else if (ae.getSource() == b4) {
            JOptionPane.showMessageDialog(this, "This feature is under development", "Mini Statement", JOptionPane.INFORMATION_MESSAGE);
        }else if (ae.getSource() == b3) {
            this.setVisible(false);
            new FastCash(username).setVisible(true);
        } else if (ae.getSource()==b5) {
            this.setVisible(false);
            new PinChange(username).setVisible(true);
        } else if (ae.getSource() == b7) { //logout button
            this.setVisible(false);
            Login loginWindow = Login.getInstance(); // SINGLETON PATTERN QUHET
            loginWindow.clearFields();
            loginWindow.setVisible(true);

        }else{
            Login newLogin = new Login();
            newLogin.clearFields();
            newLogin.setVisible(true);
        }

    }



    public static void main(String[] args){
        new Transactions(null,"user123").setVisible(true); // e ka ate test user per me dal emri ke faqja mos e luj
    }
}