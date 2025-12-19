# 🏦 Vaultify - Smart Banking Management System

**Vaultify** is a robust Java-based desktop application designed to simulate a modern banking environment. It features a fully functional **Swing GUI**, secure user authentication, and advanced financial logic for multiple account types.

---

## 🚀 Specialized Account Features

The system is built on an inheritance-based model to handle different banking products:

### 💰 Savings Account (`SavingAccount.java`)
* **Interest Accumulation:** Features a built-in interest rate logic. 
* **Minimum Balance Requirement:** Automatically ensures that the account maintains a base balance to remain active.
* **Withdrawal Limits:** Includes checks to prevent balance depletion below the mandatory threshold.

### 💳 Current Account (`CurrentAccount.java`)
* **Overdraft Facility:** Allows users to withdraw funds beyond their available balance up to a pre-defined **Overdraft Limit**.
* **Flexible Liquidity:** Designed for high-frequency transactions without minimum balance restrictions.
* **Overdraft Tracking:** Automatically calculates the negative balance (credit) when users utilize the overdraft feature.

---

## 🛠 Core Functionalities

* **Secure Login System:** PIN-protected access for account security.
* **Universal Transactions:** Support for Deposits, Withdrawals, and Fund Transfers between accounts.
* **Real-time History:** Every action generates a `Transaction` object stored in a persistent history log.
* **Data Persistence:** Uses **Java Serialization** to save all accounts and transaction logs to `.ser` files, ensuring data is kept after the application is closed.
* **Input Validation:** Comprehensive error handling for insufficient funds, invalid account numbers, and incorrect PINs.

---

## 🏗 System Architecture (OOP Principles)

This project demonstrates strong software engineering foundations:
* **Inheritance:** The `Account` base class is extended by `SavingAccount` and `CurrentAccount`.
* **Encapsulation:** Sensitive data like PINs and balances are protected via private/protected modifiers and getters/setters.
* **Polymorphism:** Unified methods for transaction handling across different account types.
* **Serialization:** Persistent object storage for a database-less architecture.



---

## 📂 Project Structure
* `BankApp.java`: Entry point with GUI initialization.
* `AccountManager.java`: Handles the business logic for creating and finding accounts.
* `Transaction.java`: Stores details of individual financial events.
* `Persistence.java`: Manages the saving/loading of the `.ser` data files.

---

## 💻 Installation & Usage
1. Clone the repository.
2. Compile the source files: `javac *.java`
3. Launch the application: `java BankApp`
4. Use the default account or register a new one to begin banking!

---
