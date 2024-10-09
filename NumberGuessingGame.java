import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {
    // Function to start a guessing game round
    public static void playRound(int range, int maxAttempts, Scanner sc) {
        Random random = new Random();
        int numberToGuess = random.nextInt(range) + 1;  // Random number between 1 and range
        int attempts = 0;
        int score = 0;
        boolean hasWon = false;

        System.out.println("\nI have generated a number between 1 and " + range + ".");
        System.out.println("Try to guess the number! You have " + maxAttempts + " attempts.");

        // Main guessing loop
        while (attempts < maxAttempts) {
            attempts++;
            System.out.print("Attempt " + attempts + ": Enter your guess: ");
            int userGuess = sc.nextInt();

            if (userGuess == numberToGuess) {
                System.out.println("Congratulations! You guessed the number in " + attempts + " attempts.");
                score = (maxAttempts - attempts + 1) * 10;  // Award points based on attempts left
                hasWon = true;
                break;
            } else if (userGuess < numberToGuess) {
                System.out.println("Too low! Try again.");
            } else {
                System.out.println("Too high! Try again.");
            }
        }

        if (!hasWon) {
            System.out.println("Sorry, you've used all attempts. The correct number was " + numberToGuess + ".");
        }

        System.out.println("Score for this round: " + score + " points.\n");
    }

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            int totalScore = 0;
            int roundsPlayed = 0;
            
            System.out.println("Welcome to the Number Guessing Game!");
            System.out.print("Enter the range of numbers (e.g., enter 100 for range 1 to 100): ");
            int range = sc.nextInt();
            
            System.out.print("Enter the maximum number of attempts per round: ");
            int maxAttempts = sc.nextInt();
            
            System.out.print("How many rounds would you like to play? ");
            int totalRounds = sc.nextInt();
            
            // Play the number of rounds specified
            while (roundsPlayed < totalRounds) {
                roundsPlayed++;
                System.out.println("\n--- Round " + roundsPlayed + " ---");
                playRound(range, maxAttempts, sc);
                
                System.out.print("Would you like to play another round? (y/n): ");
                char playAgain = sc.next().charAt(0);
                
                if (playAgain == 'n' || playAgain == 'N') {
                    break;
                }
            }
            
            System.out.println("\nYou played " + roundsPlayed + " rounds with a total score of " + totalScore + " points.");
            System.out.println("Thank you for playing!");
        }
    }
}
