package metrobank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Signup2 extends JFrame implements ActionListener{

    JLabel l1,l2,l3,l4,l5,l6,l7,l10,l11;
    JButton b;
    JRadioButton r3,r4;
    JTextField t1,t2;
    JComboBox c1,c2,c3,c4,c5;
    private String username;

    private void appendData(String[] additionalData) {
        String filePath = "data/users.csv"; // Path to your CSV file
        List<String> fileContent = new ArrayList<>();

        try {
            // Read the file and store its content
            fileContent = new ArrayList<>(Files.readAllLines(Paths.get(filePath)));

            // Find the line with the matching username and update it
            for (int i = 0; i < fileContent.size(); i++) {
                String[] values = fileContent.get(i).split(",");
                if (values.length > 0 && values[0].equals(this.username)) {
                    String updatedLine = fileContent.get(i) + String.join(",", additionalData);
                    fileContent.set(i, updatedLine);
                    break;
                }
            }

            // Write everything back to the file
            Files.write(Paths.get(filePath), fileContent);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception, possibly with a user notification
        }
    }


    private void saveUserDataToCSV(String[] userData) {
        String filePath = "data/users.csv"; //file path
        try (FileWriter fw = new FileWriter(filePath, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            for(String data : userData) {
                out.print(data + ",");
            }
            out.println();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "An error occurred while saving user data.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    Signup2(String username){
        this.username = username;
        setTitle("MetroBank Application Form - Part 2");

        l1 = new JLabel("Page 2: Additonal Details");
        l1.setFont(new Font("Arial", Font.BOLD, 22));

        l2 = new JLabel("Account Type:");
        l2.setFont(new Font("Arial", Font.BOLD, 18));

        l3 = new JLabel("Category:");
        l3.setFont(new Font("Arial", Font.BOLD, 18));

        l4 = new JLabel("Income:");
        l4.setFont(new Font("Arial", Font.BOLD, 18));

        l5 = new JLabel("Educational");
        l5.setFont(new Font("Arial", Font.BOLD, 18));

        l11 = new JLabel("Qualification:");
        l11.setFont(new Font("Arial", Font.BOLD, 18));

        l6 = new JLabel("Occupation:");
        l6.setFont(new Font("Arial", Font.BOLD, 18));

        l7 = new JLabel("SSN:");
        l7.setFont(new Font("Arial", Font.BOLD, 18));

        l10 = new JLabel("Existing Account:");
        l10.setFont(new Font("Arial", Font.BOLD, 18));

        b = new JButton("Next");
        b.setFont(new Font("Arial", Font.BOLD, 14));
        b.setBackground(Color.BLACK);
        b.setForeground(Color.WHITE);

        t1 = new JTextField();
        t1.setFont(new Font("Arial", Font.BOLD, 14));

        r3 = new JRadioButton("Yes");
        r3.setFont(new Font("Arial", Font.BOLD, 14));
        r3.setBackground(Color.WHITE);

        r4 = new JRadioButton("No");
        r4.setFont(new Font("Arial", Font.BOLD, 14));
        r4.setBackground(Color.WHITE);


        String type[] = {"Checkings","Savings","Credit","Investment"};
        c1 = new JComboBox(type);
        c1.setBackground(Color.WHITE);
        c1.setFont(new Font("Arial", Font.BOLD, 14));

        String category[] = {"General","OBC","SC","ST","Other"};
        c2 = new JComboBox(category);
        c2.setBackground(Color.WHITE);
        c2.setFont(new Font("Arial", Font.BOLD, 14));

        String income[] = {"Rather not disclose","<1,50,000","<250,000","<500,000","Upto 1,000,000","Above 1,000,000"};
        c3 = new JComboBox(income);
        c3.setBackground(Color.WHITE);
        c3.setFont(new Font("Arial", Font.BOLD, 14));

        String education[] = {"Non-Graduate","Graduate","Post-Graduate","Doctrate","Others"};
        c4 = new JComboBox(education);
        c4.setBackground(Color.WHITE);
        c4.setFont(new Font("Arial", Font.BOLD, 14));

        String occupation[] = {"Salaried","Self-Employmed","Business","Student","Retired","Others"};
        c5 = new JComboBox(occupation);
        c5.setBackground(Color.WHITE);
        c5.setFont(new Font("Arial", Font.BOLD, 14));


        setLayout(null);

        l1.setBounds(280,30,600,40);
        add(l1);

        l2.setBounds(100,120,100,30);
        add(l2);

        c1.setBounds(350,120,320,30);
        add(c1);

        l3.setBounds(100,170,100,30);
        add(l3);

        c2.setBounds(350,170,320,30);
        add(c2);

        l4.setBounds(100,220,100,30);
        add(l4);

        c3.setBounds(350,220,320,30);
        add(c3);

        l5.setBounds(100,270,150,30);
        add(l5);

        c4.setBounds(350,270,320,30);
        add(c4);

        l11.setBounds(100,290,150,30);
        add(l11);

        l6.setBounds(100,340,150,30);
        add(l6);

        c5.setBounds(350,340,320,30);
        add(c5);

        l7.setBounds(100,390,150,30);
        add(l7);

        t1.setBounds(350,390,320,30);
        add(t1);

        //l8.setBounds(100,440,180,30);
        //add(l8);


        //l9.setBounds(100,490,150,30);
        //add(l9);

        l10.setBounds(100,440,180,30);
        add(l10);

        r3.setBounds(350,440,100,30);
        add(r3);

        r4.setBounds(460,440,100,30);
        add(r4);

        b.setBounds(570,640,100,30);
        add(b);

        b.addActionListener(this);

        getContentPane().setBackground(Color.WHITE);

        setSize(850,750);
        setLocation(500,120);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if (ae.getSource() == b) {
            // Collect additional data from the form fields
            String accountType = c1.getSelectedItem().toString();
            String category = c2.getSelectedItem().toString();
            String income = c3.getSelectedItem().toString();
            String education = c4.getSelectedItem().toString();
            String occupation = c5.getSelectedItem().toString();
            String existingAccount = r3.isSelected() ? "Yes" : (r4.isSelected() ? "No" : "Not Specified");

            String[] additionalData = {
                    accountType,
                    category,
                    income,
                    education,
                    occupation,
                    existingAccount
            };

            // Append the additional data for the specific user
            appendData(additionalData);

            // Provide feedback to the user
            JOptionPane.showMessageDialog(this, "Details saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            this.dispose(); // Close the Signup2 window
            String placeholderFirstName = "New User";
            new Transactions(null, username).setVisible(true);
        }
    }




    public static void main(String[] args){
        String dummyUsername = "testUser"; // Dummy username for testing
        new Signup2(dummyUsername).setVisible(true);
    }
}