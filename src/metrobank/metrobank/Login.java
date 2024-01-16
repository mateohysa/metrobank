package metrobank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Login extends JFrame implements ActionListener{
    JButton login, signup;
    private static Login instance;
    JTextField accNoField;
    JPasswordField pinField;
    private String verifyCredentials(String username, String pin) {
        String filePath = "data/users.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length >= 5 && values[0].equals(username) && values[4].equals(pin)) {
                    return values[1]; // Return the user's name (assuming it's the second field)
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    Login(){
        setLayout(null);
        setTitle("Metro Bank");
        ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("img/logo.png"));
        JLabel label = new JLabel(img1);
        Image img2 = img1.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT);
        ImageIcon img3 = new ImageIcon(img2);
        JLabel label1 = new JLabel(img3);
        label1.setBounds(50, 10, 40, 40); 
        add(label1);
        
        JLabel text = new JLabel("Welcome to MetroBank");
        text.setFont(new Font("Calibri", Font.BOLD, 35));
        text.setBounds(200, 20, 400, 40);
        add(text); 
        
        JLabel accNo = new JLabel("Username");
        accNo.setFont(new Font("Raleway", Font.BOLD, 20));
        accNo.setBounds(140, 150, 400, 40);
        add(accNo);
        
        JLabel pin = new JLabel("PIN");
        pin.setFont(new Font("Raleway", Font.BOLD, 20));
        pin.setBounds(140, 210, 400, 40);    
        add(pin);
        
        accNoField = new JTextField();
        accNoField.setBounds(340, 160, 250, 30);
        accNoField.setFont(new Font("Arial", Font.BOLD, 18));
        add(accNoField);
        
        pinField = new JPasswordField();
        pinField.setBounds(340, 220, 250, 30);
        pinField.setFont(new Font("Arial", Font.BOLD, 18));
        add(pinField);
        
        login = new JButton("Log In"); 
        login.setBounds(340, 280, 120, 35);
        login.setBackground(Color.lightGray);
        login.setForeground(Color.darkGray);
        login.setFont(new Font("Sans Serif", Font.BOLD, 11));
        login.addActionListener(this);
        add(login);
        
        signup = new JButton("Sign Up"); 
        signup.setBounds(470, 280, 120, 35);
        signup.setBackground(Color.lightGray);
        signup.setForeground(Color.darkGray);
        signup.setFont(new Font("Sans Serif", Font.BOLD, 11));
        signup.addActionListener(this);
        add(signup);
        
        setSize(800, 450);
        setVisible(true);
        setLocation(350,200);
        getContentPane().setBackground(Color.WHITE);
    }
    public static Login getInstance() {
        if (instance == null) {
            instance = new Login();
        }
        return instance;
    }
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == login) {
            String username = accNoField.getText();
            String password = new String(pinField.getPassword());


            if (username.trim().isEmpty() || password.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Username or password cannot be empty.", "Login Error", JOptionPane.ERROR_MESSAGE);
                return; // Exit the method, preventing further processing
            }

            String userName = verifyCredentials(username, password);
            if (userName != null) {
                this.setVisible(false);
                new Transactions(this, username).setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid username or password. Please try again.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                accNoField.setText("");
                pinField.setText("");
            }
        } else if (ae.getSource() == signup) {
            Signup1 signupWindow = new Signup1();
            signupWindow.setVisible(true);
            this.setVisible(false);
        }

    }
    public void clearFields() {
        accNoField.setText("");
        pinField.setText("");
    }

    public static void main(String args[]){
        new Login().setVisible(true);
    }
}
        
