package metrobank;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

public class FastCash extends JFrame implements ActionListener {

    JTextField t1, t2, t3;
    JButton b1, b2, b3, b4, b5, b6, b7;
    JLabel l1, l22, l3, l4;
    String username;

    FastCash(String username) {
        this.username = username;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("img/logo.png"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l2 = new JLabel(i3);
        l2.setBounds(80, 85, 80, 80);
        add(l2);

        setTitle("MetroBank");
        l1 = new JLabel("FAST CASH WITHDRAW");
        l1.setForeground(Color.BLACK);
        l1.setFont(new Font("System", Font.BOLD, 32));

        b1 = new JButton("1000"); // regulloje dhe te deposit dhe withdraw
        b1.setBackground(Color.lightGray);
        b2 = new JButton("10000");
        b2.setBackground(Color.lightGray);

        b3 = new JButton("3000");
        b3.setBackground(Color.lightGray);
        b4 = new JButton("20000");
        b4.setBackground(Color.lightGray);
        b5 = new JButton("5000");
        b5.setBackground(Color.lightGray);
        b6 = new JButton("30000");
        b6.setBackground(Color.lightGray);
        b7 = new JButton("Back");
        b7.setBackground(Color.lightGray);

        setLayout(null);

        l1.setBounds(245, 100, 480, 55);
        add(l1);

        b1.setBounds(300, 285, 150, 35);
        add(b1);

        b2.setBounds(510, 285, 150, 35);
        add(b2);

        b3.setBounds(300, 343, 150, 35);
        add(b3);

        b4.setBounds(510, 343, 150, 35);
        add(b4);

        b5.setBounds(300, 401, 150, 35);
        add(b5);

        b6.setBounds(510, 401, 150, 35);
        add(b6);

        b7.setBounds(405, 533, 150, 35);
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
        JButton clickedButton = (JButton) ae.getSource();
        if (ae.getSource() == b7) {
            this.setVisible(false);
            new Transactions(username).setVisible(true);
        } else {
            int amountToWithdraw = Integer.parseInt(clickedButton.getText());
            withdrawAmount(amountToWithdraw);
        }
    }

    private int getUserBalance(String username) {
        String filePath = "data/users.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values[0].equals(username)) {
                    return Integer.parseInt(values[values.length - 1]);
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private void withdrawAmount(int amount) {
        String filePath = "data/users.csv";
        try {
            List<String> fileContent = new ArrayList<>(Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8));
            boolean transactionSuccess = false;

            for (int i = 0; i < fileContent.size(); i++) {
                String[] userData = fileContent.get(i).split(",");
                if (userData[0].equals(this.username)) {
                    int balanceIndex = userData.length - 1;
                    int currentBalance = Integer.parseInt(userData[balanceIndex]);

                    if (currentBalance >= amount) {
                        currentBalance -= amount;
                        userData[balanceIndex] = String.valueOf(currentBalance);
                        fileContent.set(i, String.join(",", userData));
                        transactionSuccess = true;
                    }
                    break;
                }
            }

            if (transactionSuccess) {
                Files.write(Paths.get(filePath), fileContent, StandardCharsets.UTF_8);
                JOptionPane.showMessageDialog(this, "Amount Withdrawn: $" + amount);
            } else {
                JOptionPane.showMessageDialog(this, "Insufficient Funds", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error processing transaction.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Error reading balance data.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new FastCash("").setVisible(true);
    }
}