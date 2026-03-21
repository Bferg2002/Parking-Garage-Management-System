package Finance;

import java.util.ArrayList;
import java.util.List;

public class CashRegister {
        private double currentBalance;
        private double totalSales;
        private final List<String> transactionLog;

        public CashRegister() {
            this.currentBalance = 0.0;
            this.totalSales = 0.0;
            this.transactionLog = new ArrayList<>();
        }

        // Add a charge (e.g., parking fee)
        public void addCharge(double amount) {
            if (amount < 0) {
                throw new IllegalArgumentException("Charge amount cannot be negative.");
            }
            currentBalance += amount;
            transactionLog.add("Charge added: $" + String.format("%.2f", amount));
        }

        // Accept payment from customer
        public void acceptPayment(double amount) {
            if (amount <= 0) {
                throw new IllegalArgumentException("Payment must be greater than zero.");
            }
            currentBalance -= amount;
            totalSales += amount;
            transactionLog.add("Payment received: $" + String.format("%.2f", amount));
        }

        // Calculate change owed to customer
        public double giveChange() {
            if (currentBalance >= 0) {
                return 0.0; // no change owed
            }
            double change = Math.abs(currentBalance);
            transactionLog.add("Change given: $" + String.format("%.2f", change));
            currentBalance = 0.0;
            return change;
        }

        // Get current balance (positive = customer owes, negative = change owed)
        public double getCurrentBalance() {
            return currentBalance;
        }

        // Get total sales for reporting
        public double getTotalSales() {
            return totalSales;
        }

        // Reset register (e.g., new customer)
        public void clear() {
            currentBalance = 0.0;
            transactionLog.add("Register cleared.");
        }

        // View transaction history
        public List<String> getTransactionLog() {
            return transactionLog;
        }

        // Print receipt
        public void printReceipt() {
            System.out.println("----- Receipt -----");
            for (String entry : transactionLog) {
                System.out.println(entry);
            }
            System.out.println("-------------------");
        }
    }

