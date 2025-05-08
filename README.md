# 🛍️ Online Store Console Application

Welcome to the **Online Store Console App**, a Java-based program that simulates a basic online shopping experience using a text-based interface. This project allows users to browse a product catalog, add and remove items from a shopping cart, and simulate a checkout experience. It's designed to demonstrate file reading, user input handling, object manipulation, and error checking in Java.

---

## 📌 Project Description

This application is built for educational purposes and demonstrates:

- Reading and parsing data from external files (CSV)
- Storing product objects in a dynamic list
- Navigating a menu-driven interface via user input
- Validating user input (handling errors and edge cases)
- Calculating totals, change, and simulating a payment process

The program structure is modular, with dedicated methods for each operation, promoting code clarity and maintainability.

---

## 🔧 Technologies Used

- Java SE (Standard Edition)
- BufferedReader / FileReader for file I/O
- Scanner for user input
- ArrayList for dynamic storage
- Try-Catch blocks for exception handling

---

## 📦 Features

### ✅ Inventory Management
- Products are loaded from a CSV file (`products.csv`)
- Each product has a SKU, name, and price

### 🛒 Shopping Cart
- Users can add items to a cart
- View the cart with running total
- Remove individual items

### 💳 Checkout Simulation
- Prompt the user to confirm purchase
- Input payment amount
- Display change or cancel if insufficient funds

### 🧠 Input Validation
- Handles non-numeric input gracefully
- Prevents out-of-bound selections
- Ensures correct payment amount during checkout

---

## 🖼️ Screenshots

> _Make sure to place these images in a `screenshots/` folder in your repo._

### 🏠 Home Screen (Main Menu)
![Home Screen] ![Screenshot 2025-05-07 205858.png](../../../../Pictures/Screenshots/Screenshot%202025-05-07%20205858.png)

### 🛍️ Products Display Screen
![Products Display]![Screenshot 2025-05-07 210222.png](../../../../Pictures/Screenshots/Screenshot%202025-05-07%20210222.png)

### ❌ Calculator Page with Error Handling
_This shows a failed attempt to add a product using invalid input (e.g. letters or out-of-range number)._
![Calculator Error]![Screenshot 2025-05-07 210441.png](../../../../Pictures/Screenshots/Screenshot%202025-05-07%20210441.png)

---

