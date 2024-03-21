import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

// Task class representing a task with name, description, and due date
class Task {
    private String name;
    private String description;
    private String dueDate;

    // Constructor to initialize Task object with name, description, and due date
    public Task(String name, String description, String dueDate) {
        this.name = name;
        this.description = description;
        this.dueDate = dueDate;
    }

    // Getter method for task name
    public String getName() {
        return name;
    }

    // Getter method for task description
    public String getDescription() {
        return description;
    }

    // Getter method for task due date
    public String getDueDate() {
        return dueDate;
    }

    // Override toString method to provide string representation of Task object
    @Override
    public String toString() {
        return "Name: " + name + ", Description: " + description + ", Due Date: " + dueDate;
    }
}

// TaskManager class to manage tasks
public class TaskManager {
    // HashMap to store tasks for each user
    private static HashMap<String, ArrayList<Task>> userTasks = new HashMap<>();
    private static Scanner scanner = new Scanner(System.in);

    // Main method to start the program
    public static void main(String[] args) {
        // Main menu loop
        while (true) {
            System.out.println("\nTask Management System");
            System.out.println("1. Login");
            System.out.println("2. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    login(); // Call login method
                    break;
                case 2:
                    System.out.println("Exiting...");
                    System.exit(0); // Exit the program
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 2.");
            }
        }
    }

    // Method for user login
    private static void login() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        // Simulate authentication, you can extend it to real authentication mechanism
        // If user doesn't exist, create a new empty task list for the user
        if (!userTasks.containsKey(username)) {
            userTasks.put(username, new ArrayList<>());
        }
        showMenu(username); // Call showMenu method with the logged in username
    }

    // Method to show menu options for a logged in user
    private static void showMenu(String username) {
        // Menu loop
        while (true) {
            System.out.println("\nTask Management System - " + username);
            System.out.println("1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. List Tasks");
            System.out.println("4. Logout");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    addTask(username); // Call addTask method
                    break;
                case 2:
                    removeTask(username); // Call removeTask method
                    break;
                case 3:
                    listTasks(username); // Call listTasks method
                    break;
                case 4:
                    return; // Logout
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            }
        }
    }

    // Method to add a task for a user
    private static void addTask(String username) {
        System.out.print("Enter task name: ");
        String name = scanner.nextLine();
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        System.out.print("Enter due date: ");
        String dueDate = scanner.nextLine();

        Task task = new Task(name, description, dueDate); // Create a new Task object
        userTasks.get(username).add(task); // Add task to user's task list
        System.out.println("Task added successfully!");
    }

    // Method to remove a task for a user
    private static void removeTask(String username) {
        ArrayList<Task> tasks = userTasks.get(username); // Get user's task list
        if (tasks.isEmpty()) {
            System.out.println("No tasks to remove.");
            return;
        }

        System.out.println("Select the task to remove:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i).getName());
        }

        System.out.print("Enter task number to remove: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        if (index < 1 || index > tasks.size()) {
            System.out.println("Invalid task number.");
            return;
        }

        tasks.remove(index - 1); // Remove task from user's task list
        System.out.println("Task removed successfully!");
    }

    // Method to list tasks for a user
    private static void listTasks(String username) {
        ArrayList<Task> tasks = userTasks.get(username); // Get user's task list
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }

        System.out.println("Tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
}