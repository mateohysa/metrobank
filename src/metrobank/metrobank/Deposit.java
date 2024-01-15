package metrobank;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

public class Deposit extends JFrame implements ActionListener{

    JTextField t1,t2;
    JButton b1,b2,b3;
    JLabel l1,l2,l3;
    String username;
    Deposit(String username){
        this.username = username;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("img/logo.png"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l2 = new JLabel(i3);
        l2.setBounds(80, 85, 80, 80);
        add(l2);

        setTitle("MetroBank");
        l1 = new JLabel("ENTER AMOUNT YOU WANT TO DEPOSIT");
        l1.setForeground(Color.BLACK);
        l1.setFont(new Font("System", Font.BOLD, 22));

        t1 = new JTextField();
        t1.setFont(new Font("Calibri", Font.BOLD, 22));

        b1 = new JButton("DEPOSIT");
        b1.setBackground(Color.lightGray);
        b2 = new JButton("BACK");
        b2.setBackground(Color.lightGray);

        setLayout(null);

        l1.setBounds(245,100,480,55);
        add(l1);

        t1.setBounds(280,347,400,25);
        add(t1);

        b1.setBounds(405,488,150,35);
        add(b1);

        b2.setBounds(405,533,150,35);
        add(b2);

        b1.addActionListener(this);
        b2.addActionListener(this);

        setSize(960,720);
        getContentPane().setBackground(Color.WHITE);
        setUndecorated(false);
        setVisible(true);
        setResizable(false);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) { // Deposit button
            String filePath = "data/users.csv";
            List<String> fileContent = new ArrayList<>();
            try {
                fileContent = Files.readAllLines(Paths.get(filePath));
                for (int i = 0; i < fileContent.size(); i++) {
                    String[] data = fileContent.get(i).split(",");



                    if (data[0].equals(username)) {
                        int currentBalanceIndex = data.length - 1; // balanca esht n fun t rreshtit te csv
                        int currentBalance = Integer.parseInt(data[currentBalanceIndex]);


                        int depositAmount = Integer.parseInt(t1.getText());
                        int newBalance = currentBalance + depositAmount;
                        data[currentBalanceIndex] = String.valueOf(newBalance);
                        fileContent.set(i, String.join(",", data));

                        break;
                    }
                }
                Files.write(Paths.get(filePath), fileContent);
                JOptionPane.showMessageDialog(this, "Deposit Successful", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException | NumberFormatException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error processing deposit.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (ae.getSource() == b2) {
            this.dispose();
            new Transactions(username).setVisible(true);
        }
    }


    public static void main(String[] args){
        new Deposit("").setVisible(true);
    }
}