import java.util.ArrayList;
import java.util.Scanner;

public class FlowStateConsole {

    // ===== DATA =====
    static ArrayList<Task> tasks = new ArrayList<>();
    static ArrayList<Course> courses = new ArrayList<>();

    static Scanner input = new Scanner(System.in);

    // ===== MAIN =====
    public static void main(String[] args) {

        while (true) {
            System.out.println("\n=== FLOW STATE DASHBOARD ===");
            System.out.println("1. GPA Calculator");
            System.out.println("2. To-Do List");
            System.out.println("3. Pomodoro Timer");
            System.out.println("4. Assignment Reminder");
            System.out.println("5. Exit");

            System.out.print("Choose: ");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1 -> gpaMenu();
                case 2 -> todoMenu();
                case 3 -> pomodoro();
                case 4 -> reminders();
                case 5 -> System.exit(0);
            }
        }
    }

    // ===== GPA =====
    static void gpaMenu() {
        System.out.print("Course name: ");
        String name = input.nextLine();

        System.out.print("Grade (0.0 - 4.0): ");
        double grade = input.nextDouble();

        System.out.print("Credits: ");
        double credits = input.nextDouble();
        input.nextLine();

        courses.add(new Course(name, grade, credits));

        double totalPoints = 0;
        double totalCredits = 0;

        for (Course c : courses) {
            totalPoints += c.grade * c.credits;
            totalCredits += c.credits;
        }

        double gpa = totalPoints / totalCredits;

        System.out.println("Current GPA: " + gpa);
    }

    // ===== TODO =====
    static void todoMenu() {
        System.out.println("1. Add Task");
        System.out.println("2. View Tasks");
        System.out.println("3. Complete Task");

        int choice = input.nextInt();
        input.nextLine();

        if (choice == 1) {
            System.out.print("Task: ");
            tasks.add(new Task(input.nextLine()));
        }

        if (choice == 2) {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println(i + ": " + tasks.get(i));
            }
        }

        if (choice == 3) {
            System.out.print("Task number: ");
            int i = input.nextInt();
            tasks.get(i).done = true;
        }
    }

    // ===== POMODORO (SIMPLIFIED) =====
    static void pomodoro() {
        System.out.println("Pomodoro started (25 seconds demo)");

        for (int i = 25; i >= 0; i--) {
            System.out.print("\rTime left: " + i + " sec");
            try { Thread.sleep(1000); } catch (Exception e) {}
        }

        System.out.println("\nSession complete!");
    }

    // ===== REMINDERS =====
    static void reminders() {
        System.out.print("Enter reminder (e.g. Test Friday): ");
        String reminder = input.nextLine();

        System.out.println("Saved reminder: " + reminder);
    }
}

// ===== CLASSES (OOP) =====
class Task {
    String name;
    boolean done;

    Task(String name) {
        this.name = name;
        this.done = false;
    }

    public String toString() {
        return (done ? "[DONE] " : "[ ] ") + name;
    }
}

class Course {
    String name;
    double grade;
    double credits;

    Course(String n, double g, double c) {
        name = n;
        grade = g;
        credits = c;
    }
}