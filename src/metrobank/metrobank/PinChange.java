package metrobank;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

public class PinChange extends JFrame implements ActionListener{

    JTextField t1,t2,t3;
    JButton b1,b2;
    JLabel l1,l22,l3,l4;
    String username;

    PinChange(String username){
        this.username = username;

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("img/logo.png"));
        Image i2 = i1.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l2 = new JLabel(i3);
        l2.setBounds(80, 85, 80, 80);
        add(l2);

        setTitle("MetroBank");
        l1 = new JLabel("CHANGE PIN");
        l1.setForeground(Color.BLACK);
        l1.setFont(new Font("System", Font.BOLD, 48));

        l22 = new JLabel("Current PIN");
        l22.setForeground(Color.BLACK);
        l22.setFont(new Font("Arial", Font.BOLD, 16));

        l3 = new JLabel("New PIN");
        l3.setForeground(Color.BLACK);
        l3.setFont(new Font("Arial", Font.BOLD, 16));

        l4 = new JLabel("Confirm new PIN");
        l4.setForeground(Color.BLACK);
        l4.setFont(new Font("Arial", Font.BOLD, 16));

        t1 = new JTextField();
        t1.setFont(new Font("Calibri", Font.BOLD, 22));

        t2 = new JTextField();
        t2.setFont(new Font("Calibri", Font.BOLD, 22));

        t3 = new JTextField();
        t3.setFont(new Font("Calibri", Font.BOLD, 22));

        b1 = new JButton("Confirm"); // regulloje dhe te deposit dhe withdraw
        b1.setBackground(Color.lightGray);
        b2 = new JButton("Back");
        b2.setBackground(Color.lightGray);

        setLayout(null);

        l1.setBounds(245,100,480,55);
        add(l1);

        l22.setBounds(225,330,480,55);
        add(l22);

        l3.setBounds(225,370,480,55);
        add(l3);

        l4.setBounds(225,410,480,55);
        add(l4);

        t1.setBounds(355,347,250,25);
        add(t1);

        t2.setBounds(355, 387, 250, 25);
        add(t2);

        t3.setBounds(355, 427, 250, 25);
        add(t3);

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
        if(ae.getSource()==b1){
            if (t1.getText().isEmpty() || t2.getText().isEmpty() || t3.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // PIN matching
            if (!t2.getText().equals(t3.getText())) {
                JOptionPane.showMessageDialog(this, "New PIN and Confirm new PIN do not match.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                String filePath = "data/users.csv";
                List<String> fileContent = Files.readAllLines(Paths.get(filePath));
                boolean pinUpdated = false;

                for (int i = 0; i < fileContent.size(); i++) {
                    String[] data = fileContent.get(i).split(",");
                    if (data[0].equals(username) && data[4].equals(t1.getText())) {
                        data[4] = t2.getText();
                        fileContent.set(i, String.join(",", data));
                        pinUpdated = true;
                        break;
                    }
                }

                if (pinUpdated) {
                    Files.write(Paths.get(filePath), fileContent);
                    JOptionPane.showMessageDialog(this, "PIN Updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Current PIN is incorrect.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error updating PIN.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (ae.getSource() == b2) {
            this.dispose();
            new Transactions(username).setVisible(true);
        }
    }



    public static void main(String[] args){
        new PinChange("").setVisible(true);
    }
}