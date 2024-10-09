import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

// Class representing a user (with login and profile update functionality)
class User {
    private final String username;
    private String password;
    private String fullName;
    
    public User(String username, String password, String fullName) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
    }

    // Validates user login credentials
    public boolean login(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }

    // Updates the user's full name and password
    public void updateProfile(String fullName, String password) {
        this.fullName = fullName;
        this.password = password;
    }

    // Getters
    public String getFullName() {
        return fullName;
    }
    
    public String getPassword() {
        return password;
    }
}

// Class managing the exam, MCQs, timer, and auto-submit
class Exam {
    private HashMap<String, String> questions;
    private final HashMap<String, String> answers;
    private boolean isSubmitted;
    private final int timeLimit; // Time limit in seconds

    public Exam() {
        questions = new HashMap<>();
        answers = new HashMap<>();
        isSubmitted = false;
        timeLimit = 30; // 30 seconds for the exam

        // Sample questions
        questions.put("1. What is the capital of France?", "a) Paris\nb) London\nc) Berlin\nd) Rome");
        questions.put("2. Which of the following is a Java keyword?", "a) goto\nb) if\nc) this\nd) catch");
    }

    // Starts the exam and handles the timer
    public void startExam() {
        Scanner sc = new Scanner(System.in);

        System.out.println("You have " + timeLimit + " seconds to complete the exam.");
        System.out.println("Please enter the option (a, b, c, d) for each question.");
        startTimer();
        
        for (String question : questions.keySet()) {
            System.out.println(question);
            System.out.println(questions.get(question));
            System.out.print("Your answer: ");
            String answer = sc.nextLine();
            answers.put(question, answer);

            if (isSubmitted) {
                break;
            }
        }
        if (!isSubmitted) {
            submitExam();
        }
    }

    // Starts the exam timer and auto-submits when time is up
    private void startTimer() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (!isSubmitted) {
                    System.out.println("\nTime is up! Auto-submitting your exam...");
                    submitExam();
                }
                timer.cancel();
            }
        };
        timer.schedule(task, timeLimit * 1000);
    }

    // Submits the exam manually or automatically
    public void submitExam() {
        isSubmitted = true;
        System.out.println("Exam submitted successfully!");
        // Logic to evaluate answers can be added here
    }

    public HashMap<String, String> getQuestions() {
        return questions;
    }

    public void setQuestions(HashMap<String, String> questions) {
        this.questions = questions;
    }
}

// Main class handling login, profile update, and exam session flow
public class OnlineExamination {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            User user = new User("testUser", "1234", "Prachi");
            boolean loggedIn = false;
            
            // Login logic
            while (!loggedIn) {
                System.out.print("Enter username: ");
                String username = sc.nextLine();
                System.out.print("Enter password: ");
                String password = sc.nextLine();
                
                if (user.login(username, password)) {
                    loggedIn = true;
                    System.out.println("Login successful! Welcome, " + user.getFullName());
                } else {
                    System.out.println("Invalid credentials, please try again.");
                }
            }
            
            // Menu after login
            boolean sessionActive = true;
            while (sessionActive) {
                System.out.println("\n--- Menu ---");
                System.out.println("1. Update Profile and Password");
                System.out.println("2. Start Exam");
                System.out.println("3. Logout");
                System.out.print("Choose an option: ");
                
                try {
                    int choice = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    
                    switch (choice) {
                        case 1 -> {
                            // Update profile
                            System.out.print("Enter new full name: ");
                            String fullName = sc.nextLine();
                            System.out.print("Enter new password: ");
                            String newPassword = sc.nextLine();
                            user.updateProfile(fullName, newPassword);
                            System.out.println("Profile updated successfully.");
                        }
                        case 2 -> {
                            // Start exam
                            Exam exam = new Exam();
                            exam.startExam();
                        }
                        case 3 -> {
                            // Logout
                            sessionActive = false;
                            System.out.println("Logged out successfully. Goodbye!");
                        }
                        default -> System.out.println("Invalid choice. Please try again.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a valid number for your choice.");
                    sc.next(); // Clear the invalid input
                }
            }
        }
    }
}
