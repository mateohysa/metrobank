﻿# MetroBank Application README

## Overview

MetroBank Application is a Java-based desktop application for managing banking transactions. It allows users to perform various operations like depositing money, withdrawing cash, checking balances, changing PINs, and more.

## Features

-   **Login:** Secure access for users with a username and password.
-   **Sign Up:** New users can create an account.
-   **Transactions:** Perform banking transactions after logging in.
-   **Deposit:** Deposit money into an account.
-   **Withdraw:** Withdraw money from the account.
-   **Fast Cash:** Quick withdrawal options with predefined amounts.
-   **Balance Inquiry:** Check the current balance in the account.
-   **PIN Change:** Change the account PIN for security.
-   **Mini Statement:** Feature under development to view transaction history.

## How to Run

1.  Ensure Java is installed on your machine.
2.  Download and extract the application files.
3.  Navigate to the application directory in the command line.
4.  Compile the Java files using `javac *.java`.
5.  Run the application using `java metrobank.Login`.

## Usage Instructions

1.  **Login/Sign Up:**
    
    -   Existing users can log in using their credentials.
    -   New users can click on "Sign Up" to create a new account.
2.  **Performing Transactions:**
    
    -   After logging in, select the desired transaction type.
    -   Follow the on-screen instructions to complete the transaction.
3.  **Checking Balance:**
    
    -   Navigate to the "Balance Inquiry" to view the current account balance.
4.  **Changing PIN:**
    
    -   Select "PIN Change" and follow the instructions to update your PIN.
5.  **Logout:**
    
    -   Click on "Logout" to exit and close your session securely.

## File Structure

-   `Login.java`: Manages user login functionality.
-   `Signup1.java`: Handles the first part of the user registration process.
-   `Signup2.java`: Completes the user registration with additional details.
-   `Transactions.java`: Main interface for various banking transactions.
-   `Deposit.java`: Interface for depositing money.
-   `Withdraw.java`: Interface for withdrawing money.
-   `FastCash.java`: Quick cash withdrawal functionality.
-   `PinChange.java`: Allows users to change their account PIN.

## Data Storage

-   User data and transaction details are stored in a CSV file (`data/users.csv`).

## Security Note

This application is a prototype and should not be used as a real banking application. It lacks encryption and other security measures necessary for handling sensitive financial data.

**

## **Signed, Mateo Hysa & Leandro Leka**

**
