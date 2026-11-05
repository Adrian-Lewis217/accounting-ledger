package com.pluralsight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/*
 * Capstone skeleton – personal finance tracker.
 * ------------------------------------------------
 * File format  (pipe-delimited)
 *     yyyy-MM-dd|HH:mm:ss|description|vendor|amount
 * A deposit has a positive amount; a payment is stored
 * as a negative amount.
 *
 * TODO: create the Transaction class in this package first.
 *       One object = one line of the file. Decide its fields from
 *       the file format above, add a constructor and getters.
 *       This class does not compile until Transaction exists.
 */
public class FinancialTracker {

    /* ------------------------------------------------------------------
       Shared data and formatters
       ------------------------------------------------------------------ */
    private static final ArrayList<Transaction> transactions = new ArrayList<>();
    private static final String FILE_NAME = "transactions.csv";

    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private static final String TIME_PATTERN = "HH:mm:ss";
    private static final String DATETIME_PATTERN = DATE_PATTERN + " " + TIME_PATTERN;

    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern(DATE_PATTERN);
    private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern(TIME_PATTERN);
    private static final DateTimeFormatter DATETIME_FMT = DateTimeFormatter.ofPattern(DATETIME_PATTERN);

    /* ------------------------------------------------------------------
       Main menu
       ------------------------------------------------------------------ */
    public static void main(String[] args) {
        loadTransactions(FILE_NAME);

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Welcome to TransactionApp");
            System.out.println("Choose an option:");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment (Debit)");
            System.out.println("L) Ledger");
            System.out.println("X) Exit");

            String input = scanner.nextLine().trim();

            switch (input.toUpperCase()) {
                case "D" -> addDeposit(scanner);
                case "P" -> addPayment(scanner);
                case "L" -> ledgerMenu(scanner);
                case "X" -> running = false;
                default -> System.out.println("Invalid option");
            }
        }
        scanner.close();
    }

    /* ------------------------------------------------------------------
       File I/O
       ------------------------------------------------------------------ */

    /**
     * Load transactions from FILE_NAME.
     * • If the file doesn’t exist, create an empty one so that future writes succeed.
     * • Each line looks like: date|time|description|vendor|amount
     */
    public static void loadTransactions(String fileName) {
        // TODO: create file if it does not exist, then read each line,
        //       parse the five fields, build a Transaction object,
        //       and add it to the transactions list.

        // Reading the file line by line and splitting
        try {

            // if the file doesn't exist
            File file = new File(FILE_NAME);
            if (!file.exists()) {
                System.out.println("File does not exist");
                file.createNewFile();
            }

            String line;
            BufferedReader br = new BufferedReader(new FileReader(FILE_NAME));
            while ((line = br.readLine()) != null){
                String[] sections = line.split("\\|");
                LocalDate date = LocalDate.parse(sections[0]);
                LocalTime time = LocalTime.parse(sections[1]);
                String description = sections[2];
                String vendor = sections[3];
                double amount = Double.parseDouble(sections[4]);
                transactions.add(new Transaction(date, time, description, vendor, amount));
            }
            br.close();
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    /* ------------------------------------------------------------------
       Add new transactions
       ------------------------------------------------------------------ */

    /**
     * Prompt for ONE date+time string in the format
     * "yyyy-MM-dd HH:mm:ss", plus description, vendor, amount.
     * Validate that the amount entered is positive.
     * Store the amount as-is (positive) and append to the file.
     */
    private static void addDeposit(Scanner scanner) {
        // TODO
        // prompt the user for all fields
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE_NAME, true));

            System.out.print("Enter date (MM/d/yyyy): ");
            String dateInput = scanner.nextLine().trim();
            System.out.print("Enter time (h:mm AM/PM): ");
            String timeInput = scanner.nextLine().trim().toUpperCase();
            System.out.print("Enter description: ");
            String description = scanner.nextLine().trim();
            System.out.print("Enter vendor: ");
            String vendor = scanner.nextLine().trim();
            System.out.print("Enter amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();

            // Change format of date and time
            DateTimeFormatter userDateFmt = DateTimeFormatter.ofPattern("MM/d/yyyy");
            DateTimeFormatter userTimeFmt = DateTimeFormatter.ofPattern("h:mm a");

            LocalDate date = LocalDate.parse(dateInput, userDateFmt);
            LocalTime time = LocalTime.parse(timeInput, userTimeFmt);

            String formattedDate = date.format(DATE_FMT);
            String formattedTime = time.format(TIME_FMT);

            // combine date and time
            String dateTime = formattedDate + "|" + formattedTime;

            // if statement for when they enter a negative number
            if (amount <= 0) {
                System.out.println("Amount must be greater than 0.");
                return;
            }

            transactions.add(new Transaction(date, time, description, vendor, amount));
            bufferedWriter.write(dateTime + "|" + description + "|" + vendor + "|" + amount);
            bufferedWriter.newLine();

            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /**
     * Same prompts as addDeposit.
     * Amount must be entered as a positive number,
     * then converted to a negative amount before storing.
     */
    private static void addPayment(Scanner scanner) {
        // TODO
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(FILE_NAME, true));

            System.out.print("Enter date (MM/d/yyyy): ");
            String dateInput = scanner.nextLine().trim();
            System.out.print("Enter time (h:mm AM/PM): ");
            String timeInput = scanner.nextLine().trim().toUpperCase();
            System.out.print("Enter description: ");
            String description = scanner.nextLine().trim();
            System.out.print("Enter vendor: ");
            String vendor = scanner.nextLine().trim();
            System.out.print("Enter amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();

            // if statement for when they enter a positive number
            if (amount >= 0) {
                amount = -Math.abs(amount);
            }

            // Change format of date and time
            DateTimeFormatter userDateFmt = DateTimeFormatter.ofPattern("MM/d/yyyy");
            DateTimeFormatter userTimeFmt = DateTimeFormatter.ofPattern("h:mm a");

            LocalDate date = LocalDate.parse(dateInput, userDateFmt);
            LocalTime time = LocalTime.parse(timeInput, userTimeFmt);

            String formattedDate = date.format(DATE_FMT);
            String formattedTime = time.format(TIME_FMT);

            // combine date and time
            String dateTime = formattedDate + "|" + formattedTime;


            transactions.add(new Transaction(date, time, description, vendor, amount));
            bufferedWriter.write(dateTime + "|" + description + "|" + vendor + "|" + amount);
            bufferedWriter.newLine();

            bufferedWriter.close();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /* ------------------------------------------------------------------
       Ledger menu
       ------------------------------------------------------------------ */
    private static void ledgerMenu(Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("Ledger");
            System.out.println("Choose an option:");
            System.out.println("A) All");
            System.out.println("D) Deposits");
            System.out.println("P) Payments");
            System.out.println("R) Reports");
            System.out.println("H) Home");

            String input = scanner.nextLine().trim();

            switch (input.toUpperCase()) {
                case "A" -> displayLedger();
                case "D" -> displayDeposits();
                case "P" -> displayPayments();
                case "R" -> reportsMenu(scanner);
                case "H" -> running = false;
                default -> System.out.println("Invalid option");
            }
        }
    }

    /* ------------------------------------------------------------------
       Display helpers: show data in neat columns
       ------------------------------------------------------------------ */
    private static void displayLedger() {
        /* TODO – print all transactions in column format, newest first */

        // using for i for positions in the transactions in the arrayList
        for (int i = transactions.size() - 1; i >= 0; i--) {
            System.out.println(transactions.get(i));
        }
    }

    private static void displayDeposits() {
        /* TODO – only amount > 0               */

        // used for each to
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() > 0){
                System.out.println(transaction);
            }
        }
    }

    private static void displayPayments() {
        /* TODO – only amount < 0               */
        for (Transaction transaction : transactions) {
            if (transaction.getAmount() < 0){
                System.out.println(transaction);
            }
        }
    }

    /* ------------------------------------------------------------------
       Reports menu
       ------------------------------------------------------------------ */
    private static void reportsMenu(Scanner scanner) {
        boolean running = true;
        while (running) {
            System.out.println("Reports");
            System.out.println("Choose an option:");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search by Vendor");
            System.out.println("6) Custom Search");
            System.out.println("0) Back");

            String input = scanner.nextLine().trim();

            switch (input) {
                case "1" -> {/* TODO – month-to-date report */
                    LocalDate start = LocalDate.now().withDayOfMonth(1);
                    LocalDate end = LocalDate.now();

                    filterTransactionsByDate(start, end);
                }
                case "2" -> {/* TODO – previous month report */
                    LocalDate previousMonth = LocalDate.now().minusMonths(1);

                    LocalDate start = previousMonth.withDayOfMonth(1);
                    LocalDate end = previousMonth.withDayOfMonth(previousMonth.lengthOfMonth());

                    filterTransactionsByDate(start, end);
                }
                case "3" -> {/* TODO – year-to-date report   */
                    LocalDate start = LocalDate.now().withDayOfYear(1);
                    LocalDate end = LocalDate.now();

                    filterTransactionsByDate(start, end);
                }
                case "4" -> {/* TODO – previous year report  */
                    LocalDate previousYear = LocalDate.now().minusYears(1);

                    LocalDate start = previousYear.withDayOfMonth(1);
                    LocalDate end = previousYear.withDayOfMonth(previousYear.lengthOfMonth());

                    filterTransactionsByDate(start, end);
                }
                case "5" -> {/* TODO – prompt for vendor then report */
                    System.out.print("Enter Vendor: ");
                    String vendor = scanner.nextLine();

                    filterTransactionsByVendor(vendor);
                }
                case "6" -> customSearch(scanner);
                case "0" -> running = false;
                default -> System.out.println("Invalid option");
            }
        }
    }

    /* ------------------------------------------------------------------
       Reporting helpers
       ------------------------------------------------------------------ */
    private static void filterTransactionsByDate(LocalDate start, LocalDate end) {
        // TODO – iterate transactions, print those within the range

        try {
            for (Transaction transaction : transactions) {
                LocalDate date = transaction.getDate();

                if (!date.isBefore(start) && !date.isAfter(end)){
                    System.out.println(transaction);
                } else {
                    System.out.println("No transactions found");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    private static void filterTransactionsByVendor(String vendor) {
        // TODO – iterate transactions, print those with matching vendor

        try {
            for (Transaction transaction : transactions) {
                // in case user enter the vendor's name in lower case
                if (transaction.getVendor().equalsIgnoreCase(vendor)){
                    System.out.println(transaction);
                } else {
                    System.out.println("Vendor not found");
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void customSearch(Scanner scanner) {
        // TODO – prompt for any combination of date range, description,
        //        vendor, and exact amount, then display matches

        // search through the arraylist for any combination of fields the user enters
        System.out.print("Start date (leave blank if none): ");
        String start = scanner.nextLine();

        System.out.print("End date (leave blank if none): ");
        String end = scanner.nextLine();

        System.out.print("Description (leave blank if none): ");
        String description = scanner.nextLine();
        System.out.print("Vendor (leave blank if none): ");
        String vendor = scanner.nextLine();

        System.out.print("Amount date (leave blank if none): ");
        String amount = scanner.nextLine();

        // no filter
        if (start.isBlank() && end.isBlank() && description.isBlank() && vendor.isBlank() && amount.isBlank()){
            System.out.println("No Filter");
            return;
        }

        try {
            LocalDate startInput = null;
            LocalDate endInput = null;
            Double amountInput = null;

            if (!start.isBlank()){
                startInput = parseDate(start, scanner);
            }

            if (!end.isBlank()){
                endInput = parseDate(end, scanner);
            }

            if (!amount.isBlank()){
                amountInput = parseDouble(amount, scanner);
            }

            // check if transactions were founded
            boolean found = false;

            for (Transaction transaction : transactions) {
            // if matches is false then skip
            boolean matches = true;

            if (!description.isBlank() && !transaction.getDescription().equalsIgnoreCase(description)) {
                matches = false;
            }
            if (!vendor.isBlank() && !transaction.getVendor().equalsIgnoreCase(vendor)) {
                matches = false;
            }
            if (amountInput != null && transaction.getAmount() != amountInput) {
                matches = false;
            }
            if (startInput != null && transaction.getDate().isBefore(startInput)){
                matches = false;
            }
            if (endInput != null && transaction.getDate().isAfter(endInput)){
                matches = false;
            }
            if (matches){
                System.out.println(transaction);
            }
        }
            if (!found){
                System.out.println("No transactions found.");
            }
    } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    /* ------------------------------------------------------------------
       Utility parsers (you can reuse in many places)
       ------------------------------------------------------------------ */
    private static LocalDate parseDate(String s, Scanner scanner) {
        /* TODO – return LocalDate or null */
        try {
            return LocalDate.parse(s);
        } catch (Exception e) {
            System.out.println("Invalid date format");
        }
        return null;
    }

    private static Double parseDouble(String s, Scanner scanner) {
        /* TODO – return Double   or null */
        try {
            return Double.parseDouble(s);
        } catch (Exception e) {
            System.out.println("Invalid double format");
        }
        return null;
    }
}
