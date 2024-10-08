import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

// Class for INCOMES
class INCOMES implements Serializable {
    private static int idCounter = 0;  // To generate unique IDs
    private int id;
    private int day;
    private int month;
    private int year;
    private int amount;
    private String description;
    private String category;
    private int cash;
    private String name;

    public INCOMES() {
        this.id = ++idCounter;  // Assign a unique ID to each income object
    }

    public int getId() {
        return id;
    }

    public void setdata(int day, int month, int year, String name, int amount, String description, String category, int cash) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.name = name;
        this.amount = amount;
        this.description = description;
        this.category = category;
        this.cash = cash;
    }

    public void display() {
        this.cash += amount;
        System.out.println("ID: " + id);
        System.out.println(day + "/" + month + "/" + year + " Name: " + name);
        System.out.println("BALANCE: " + cash);
        System.out.println("DATE: " + day + "/" + month + "/" + year + " AMOUNT: " + amount + " CATEGORY: " + category + " DESCRIPTION: " + description);
    }
}

// Class for EXPENSES
class EXPENSES implements Serializable {
    private static int idCounter = 0;  // To generate unique IDs
    private int id;
    private int day;
    private int month;
    private int year;
    private int amount;
    private String description;
    private String category;
    private int cash;
    private String name;

    public EXPENSES() {
        this.id = ++idCounter;  // Assign a unique ID to each expense object
    }

    public int getId() {
        return id;
    }

    public void setdata(int day, int month, int year, String name, int amount, String description, String category, int cash) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.name = name;
        this.amount = amount;
        this.description = description;
        this.category = category;
        this.cash = cash;
    }

    public void display() {
        this.cash -= amount;
        System.out.println("ID: " + id);
        System.out.println(day + "/" + month + "/" + year + " Name: " + name);
        System.out.println("BALANCE: " + cash);
        System.out.println("DATE: " + day + "/" + month + "/" + year + " AMOUNT: " + amount + " CATEGORY: " + category + " DESCRIPTION: " + description);
    }
}

// Main class for Expenditure Management System
public class ExpenditureManagement3 {
    private static ArrayList<INCOMES> incomeList = new ArrayList<>();  // Store income objects in memory
    private static ArrayList<EXPENSES> expenseList = new ArrayList<>();  // Store expense objects in memory

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Initial cash
        System.out.println("How much money do you have in your cash wallet?");
        int cash = sc.nextInt();
        sc.nextLine();  // Consume newline

        boolean running = true;
        while (running) {
            System.out.println("\nChoose any one option:");
            System.out.println("1. Add Income");
            System.out.println("2. Add Expenditure");
            System.out.println("3. Delete Income");
            System.out.println("4. Delete Expenditure");
            System.out.println("5. View All Incomes");
            System.out.println("6. View All Expenditures");
            System.out.println("7. Exit");
            int choice = sc.nextInt();
            sc.nextLine();  // Consume newline

            switch (choice) {
                case 1:
                    // Add income
                    INCOMES income = new INCOMES();
                    System.out.println("Enter day/month/year:");
                    int day = sc.nextInt(), month = sc.nextInt(), year = sc.nextInt();
                    sc.nextLine();  // Consume newline
                    System.out.println("Enter your name:");
                    String name = sc.nextLine();
                    System.out.println("Enter amount:");
                    int amount = sc.nextInt();
                    sc.nextLine();  // Consume newline
                    System.out.println("Enter description:");
                    String description = sc.nextLine();
                    System.out.println("Enter category:");
                    String category = sc.nextLine();
                    income.setdata(day, month, year, name, amount, description, category, cash);
                    incomeList.add(income);
                    saveData();  // Save to file
                    break;

                case 2:
                    // Add expense
                    EXPENSES expense = new EXPENSES();
                    System.out.println("Enter day/month/year:");
                    int expDay = sc.nextInt(), expMonth = sc.nextInt(), expYear = sc.nextInt();
                    sc.nextLine();  // Consume newline
                    System.out.println("Enter your name:");
                    String expName = sc.nextLine();
                    System.out.println("Enter amount:");
                    int expAmount = sc.nextInt();
                    sc.nextLine();  // Consume newline
                    System.out.println("Enter description:");
                    String expDescription = sc.nextLine();
                    System.out.println("Enter category:");
                    String expCategory = sc.nextLine();
                    expense.setdata(expDay, expMonth, expYear, expName, expAmount, expDescription, expCategory, cash);
                    expenseList.add(expense);
                    saveData();  // Save to file
                    break;

                case 3:
                    // Delete income
                    System.out.println("Enter the ID of the income to delete:");
                    int delIncomeId = sc.nextInt();
                    deleteIncome(delIncomeId);
                    break;

                case 4:
                    // Delete expense
                    System.out.println("Enter the ID of the expense to delete:");
                    int delExpenseId = sc.nextInt();
                    deleteExpense(delExpenseId);
                    break;

                case 5:
                    // View all incomes
                    for (INCOMES i : incomeList) {
                        i.display();
                    }
                    break;

                case 6:
                    // View all expenditures
                    for (EXPENSES e : expenseList) {
                        e.display();
                    }
                    break;

                case 7:
                    // Exit
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    // Delete income by ID
    private static void deleteIncome(int id) {
        Iterator<INCOMES> iterator = incomeList.iterator();
        while (iterator.hasNext()) {
            INCOMES income = iterator.next();
            if (income.getId() == id) {
                iterator.remove();
                System.out.println("Income with ID " + id + " deleted.");
                saveData();  // Save updated list to file
                return;
            }
        }
        System.out.println("Income with ID " + id + " not found.");
    }

    // Delete expense by ID
    private static void deleteExpense(int id) {
        Iterator<EXPENSES> iterator = expenseList.iterator();
        while (iterator.hasNext()) {
            EXPENSES expense = iterator.next();
            if (expense.getId() == id) {
                iterator.remove();
                System.out.println("Expense with ID " + id + " deleted.");
                saveData();  // Save updated list to file
                return;
            }
        }
        System.out.println("Expense with ID " + id + " not found.");
    }

    // Save all data to file
    private static void saveData() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("IncomeData.txt"));
            oos.writeObject(incomeList);
            oos.close();

            ObjectOutputStream oosExp = new ObjectOutputStream(new FileOutputStream("ExpenseData.txt"));
            oosExp.writeObject(expenseList);
            oosExp.close();
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }
}
