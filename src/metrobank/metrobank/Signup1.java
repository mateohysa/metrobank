package metrobank;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;

public class Signup1 extends JFrame implements ActionListener{

    JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11,l12,l13,l14,l15;
    JTextField t1,t2,t3,t4,t5,t6,t7;
    JRadioButton r1,r2,r3,r4,r5;
    JButton b;
    JDateChooser dateChooser;

    private void saveUserData() {
        String filePath = "data/users.csv"; // file path
        try (FileWriter csvWriter = new FileWriter(filePath, true)) {
            csvWriter.append(t1.getText())  // Username
                    .append(",")
                    .append(t2.getText())  // Full Name
                    .append(",");

            if (dateChooser.getDate() != null) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                String dob = dateFormat.format(dateChooser.getDate());
                csvWriter.append(dob);  // Date of Birth
            } else {
                csvWriter.append("Not Specified");
            }
            csvWriter.append(",");

            String gender = "Not Specified";
            if (r1.isSelected()) {
                gender = "Male";
            } else if (r2.isSelected()) {
                gender = "Female";
            }
            csvWriter.append(gender).append(",");  // Gender

            csvWriter.append(t6.getText())  // PIN
                    .append(",")
                    .append(t3.getText())  // Email
                    .append(",");

            String maritalStatus = "Not Specified";
            if (r3.isSelected()) {
                maritalStatus = "Married";
            } else if (r4.isSelected()) {
                maritalStatus = "Single";
            }
            csvWriter.append(maritalStatus).append(",")  // Marital Status
                    .append(t4.getText())  // Address
                    .append(",")
                    .append(t5.getText())  // City
                    .append(",")
                    .append(t7.getText())  // Country
                    .append(",");
            csvWriter.append("000000000").append("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean doesUsernameExist(String username) {
        String filePath = "data/users.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                if (values.length > 0 && values[0].equals(username)) {
                    return true; // Username exists
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false; // Username does not exist
    }

    Signup1(){

        setTitle("MetroBank Application Form");

        l1 = new JLabel("Please fill in the details below");
        l1.setFont(new Font("Calibri", Font.BOLD,25));

        l2 = new JLabel("Page 1: Personal Details");
        l2.setFont(new Font("Calibri", Font.BOLD, 20));

        l3 = new JLabel("Username:");
        l3.setFont(new Font("Calibri", Font.BOLD, 20));

        l4 = new JLabel("Full Name:");
        l4.setFont(new Font("Calibri", Font.BOLD, 20));

        l5 = new JLabel("Date of Birth:");
        l5.setFont(new Font("Calibri", Font.BOLD, 20));

        l6 = new JLabel("PIN:");
        l6.setFont(new Font("Calibri", Font.BOLD, 20));

        l7 = new JLabel("Email Address:");
        l7.setFont(new Font("Calibri", Font.BOLD, 20));

        l8 = new JLabel("Marital Status:");
        l8.setFont(new Font("Calibri", Font.BOLD, 20));

        l9 = new JLabel("Address:");
        l9.setFont(new Font("Calibri", Font.BOLD, 20));

        l10 = new JLabel("City:");
        l10.setFont(new Font("Calibri", Font.BOLD, 20));

        l11 = new JLabel("Male/Female:");
        l11.setFont(new Font("Calibri", Font.BOLD, 20));

        l12 = new JLabel("Country:");
        l12.setFont(new Font("Calibri", Font.BOLD, 20));

        l13 = new JLabel("Date");
        l13.setFont(new Font("Calibri", Font.BOLD, 14));

        l14 = new JLabel("Month");
        l14.setFont(new Font("Calibri", Font.BOLD, 14));

        l15 = new JLabel("Year");
        l15.setFont(new Font("Calibri", Font.BOLD, 14));


        t1 = new JTextField();
        t1.setFont(new Font("Calibri", Font.BOLD, 14));

        t2 = new JTextField();
        t2.setFont(new Font("Calibri", Font.BOLD, 14));

        t3 = new JTextField();
        t3.setFont(new Font("Calibri", Font.BOLD, 14));

        t4 = new JTextField();
        t4.setFont(new Font("Calibri", Font.BOLD, 14));

        t5 = new JTextField();
        t5.setFont(new Font("Calibri", Font.BOLD, 14));

        t6 = new JTextField();
        t6.setFont(new Font("Calibri", Font.BOLD, 14));

        t7 = new JTextField();
        t7.setFont(new Font("Calibri", Font.BOLD, 14));



        b = new JButton("Next");
        b.setFont(new Font("Calibri", Font.BOLD, 14));
        b.setBackground(Color.BLACK);
        b.setForeground(Color.WHITE);

        r1 = new JRadioButton("Male");
        r1.setFont(new Font("Calibri", Font.BOLD, 14));
        r1.setBackground(Color.WHITE);

        r2 = new JRadioButton("Female");
        r2.setFont(new Font("Calibri", Font.BOLD, 14));
        r2.setBackground(Color.WHITE);

        ButtonGroup groupgender = new ButtonGroup();
        groupgender.add(r1);
        groupgender.add(r2);

        r3 = new JRadioButton("Married");
        r3.setFont(new Font("Calibri", Font.BOLD, 14));
        r3.setBackground(Color.WHITE);

        r4 = new JRadioButton("Unmarried");
        r4.setFont(new Font("Calibri", Font.BOLD, 14));
        r4.setBackground(Color.WHITE);

        r5 = new JRadioButton("Other");
        r5.setFont(new Font("Calibri", Font.BOLD, 14));
        r5.setBackground(Color.WHITE);

        ButtonGroup groupstatus = new ButtonGroup();
        groupstatus.add(r3);
        groupstatus.add(r4);
        groupstatus.add(r5);

        dateChooser = new JDateChooser();
        //dateChooser.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        dateChooser.setForeground(new Color(105, 105, 105));
        dateChooser.setBounds(137, 337, 200, 29);
        add(dateChooser);

        setLayout(null);
        l1.setBounds(280,20,600,40);
        add(l1);

        l2.setBounds(300,80,600,30);
        add(l2);

        l3.setBounds(100,140,100,30);
        add(l3);

        t1.setBounds(300,140,400,30);
        add(t1);

        l4.setBounds(100,190,200,30);
        add(l4);

        t2.setBounds(300,190,400,30);
        add(t2);

        l5.setBounds(100,240,200,30);
        add(l5);

        dateChooser.setBounds(300, 240, 400, 30);

        l6.setBounds(100,290,200,30);
        add(l6);

        r1.setBounds(300,540,60,30);
        add(r1);

        r2.setBounds(450,540,90,30);
        add(r2);

        l7.setBounds(100,340,200,30);
        add(l7);

        t3.setBounds(300,340,400,30);
        add(t3);

        l8.setBounds(100,390,200,30);
        add(l8);

        r3.setBounds(300,390,100,30);
        add(r3);

        r4.setBounds(450,390,100,30);
        add(r4);

        r5.setBounds(635,390,100,30);
        add(r5);



        l9.setBounds(100,440,200,30);
        add(l9);

        t4.setBounds(300,440,400,30);
        add(t4);

        l10.setBounds(100,490,200,30);
        add(l10);

        t5.setBounds(300,490,400,30);
        add(t5);

        l11.setBounds(100,540,200,30);
        add(l11);

        t6.setBounds(300,290,400,30);
        add(t6);

        l12.setBounds(100,590,200,30);
        add(l12);

        t7.setBounds(300,590,400,30);
        add(t7);

        b.setBounds(620,660,80,30);
        add(b);

        b.addActionListener(this);

        getContentPane().setBackground(Color.WHITE);

        setSize(850,800);
        setLocation(500,120);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b) {
            // Check if any of the required fields are empty
            if (t1.getText().trim().isEmpty() ||
                    t2.getText().trim().isEmpty() ||
                    t3.getText().trim().isEmpty() ||
                    t4.getText().trim().isEmpty() ||
                    t5.getText().trim().isEmpty() ||
                    t6.getText().trim().isEmpty() ||
                    t7.getText().trim().isEmpty() ||
                    dateChooser.getDate() == null) {

                JOptionPane.showMessageDialog(this, "Field is empty. Please fill out all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                return; // Nuk procedon pa te dhena te pa populluara.
            }

            String username = t1.getText();

            if (!doesUsernameExist(username)) {
                saveUserData();
                this.dispose();
                Signup2 signup2Window = new Signup2(username);
                signup2Window.setVisible(true); // Procedon te Signup2 vetem kur Signup1 eshte e populluar.
            } else {
                JOptionPane.showMessageDialog(this, "This username is already taken. Please choose a different one.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }



    public static void main(String[] args){
        new Signup1().setVisible(true);
    }
}