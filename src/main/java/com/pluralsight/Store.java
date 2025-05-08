package com.pluralsight;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Store {

    public static void main(String[] args) {
        // Initialize variables
        ArrayList<Product> inventory = new ArrayList<Product>();
        ArrayList<Product> cart = new ArrayList<Product>();
        double totalAmount = 0.0;

        // Load inventory from CSV file
        loadInventory("products.csv", inventory);

        // Create scanner to read user input
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        // Display menu and get user choice until they choose to exit
        while (choice != 3) {
            System.out.println("Welcome to the Online Store!");
            System.out.println("1. Show Products");
            System.out.println("2. Show Cart");
            System.out.println("3. Exit");

            choice = scanner.nextInt();
            scanner.nextLine();

            // Call the appropriate method based on user choice
            switch (choice) {
                case 1:
                    displayProducts(inventory, cart, scanner);
                    break;
                case 2:
                    displayCart(cart, scanner, totalAmount);
                    break;
                case 3:
                    System.out.println("Thank you for shopping with us!");
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
    }

    public static void loadInventory(String fileName, ArrayList<Product> inventory) {
        try (BufferedReader bufReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufReader.readLine()) != null) {

                String[] parts = line.split("\\|");

                if (parts.length >= 3) {
                    String sku = parts[0].trim();
                    String productName = parts[1].trim();
                    double price = Double.parseDouble(parts[2].trim());
                    Product product = new Product(sku, productName, price);
                    inventory.add(product);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading inventory: " + e.getMessage());
        }
    }

    public static void displayProducts(ArrayList<Product> inventory, ArrayList<Product> cart, Scanner scanner) {
        if (inventory.isEmpty()) {
            System.out.println("No products available at the moment.");
            return;
        }
        System.out.println("\n===== Product List =====");

        for (int i = 0; i < inventory.size(); i++) {
            Product product = inventory.get(i);
            System.out.println((i + 1) + ". " + product);
        }

        System.out.print("\nEnter the number of the product you want to add to your cart (or 0 to cancel): ");
        String input = scanner.nextLine();
        int choice;

        try {
            choice = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return;
        }

        if (choice == 0) {
            System.out.println("No product was added to the cart.");
            return;
        }

        if (choice < 1 || choice > inventory.size()) {
            System.out.println("Invalid product number. Please try again.");
            return;
        }

        Product selectedProduct = inventory.get(choice - 1);
        cart.add(selectedProduct);

        System.out.println(selectedProduct.getName() + " has been added to your cart.");

    }

    public static void displayCart(ArrayList<Product> cart, Scanner scanner, double totalAmount) {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty.");
            return;
        }

        System.out.println("\n===== Your Shopping Cart =====");
        for (int i = 0; i < cart.size(); i++) {
            Product product = cart.get(i);
            System.out.println((i + 1) + ". " + product);
        }

        double updatedTotal = 0;
        for (Product p : cart) {
            updatedTotal += p.getPrice();
        }
        System.out.println("Total: $" + updatedTotal);

        System.out.print("\nEnter the number of the product you want to remove from your cart (or 0 to cancel): ");
        String input = scanner.nextLine();

        int choice;
        try {
            choice = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a valid number.");
            return;
        }

        if (choice == 0) {
            System.out.println("No product was removed from your cart.");
            return;
        }

        if (choice < 1 || choice > cart.size()) {
            System.out.println("Invalid product number. Returning to the previous menu.");
            return;
        }

        Product removedProduct = cart.remove(choice - 1);
        System.out.println("Removed " + removedProduct.getName() + " from your cart.");

        double newTotal = 0;
        for (Product p : cart) {
            newTotal += p.getPrice();
        }
        System.out.println("Updated Total: $" + newTotal);
    }

    public static void checkOut(ArrayList<Product> cart, double totalAmount) {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty. Nothing to check out.");
            return;
        }

        double total = 0.0;
        System.out.println("\n===== Purchase Summary =====");
        for (Product p : cart) {
            System.out.println(p.getName() + " - $" + p.getPrice());
            total += p.getPrice();
        }
        System.out.println("Total Amount: $" + total);

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Do you want to proceed with the purchase? (y/n): ");
            String confirm = scanner.nextLine();
            if (!confirm.equalsIgnoreCase("y")) {
                System.out.println("Purchase cancelled.");
                return;
            }

            System.out.print("Enter payment amount: $");
            double payment;
            try {
                payment = Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid payment amount. Transaction cancelled.");
                return;
            }

            if (payment < total) {
                System.out.println("Insufficient payment. Transaction cancelled.");
                return;
            }

            double change = payment - total;
            System.out.println("Payment accepted. Your change is: $" + change);
            System.out.println("Thank you for your purchase!");
            cart.clear();
        }
    }

    public static Product findProductById(String id, ArrayList<Product> inventory) {
        for (Product product : inventory) {
            if (product.getSku().equalsIgnoreCase(id)) {
                return product;
            }
        }
        return null;
    }
}