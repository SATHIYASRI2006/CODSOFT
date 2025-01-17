import java.util.Scanner;
import java.util.Random;

public class NumberGuessingGame {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        int totalRounds = 0;
        int roundsWon = 0;

        while (true) {
            int numberToGuess = random.nextInt(100) + 1; // Generates a number between 1 and 100
            int maxAttempts = 10; // Maximum number of attempts allowed
            int attemptsLeft = maxAttempts;
            boolean guessedCorrectly = false;

            System.out.println("New round started! Guess the number between 1 and 100.");
            
            while (attemptsLeft > 0) {
                System.out.println("You have " + attemptsLeft + " attempts left.");
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();

                if (userGuess == numberToGuess) {
                    System.out.println("Congratulations! You've guessed the correct number.");
                    guessedCorrectly = true;
                    roundsWon++;
                    break;
                } else if (userGuess < numberToGuess) {
                    System.out.println("Your guess is too low.");
                } else {
                    System.out.println("Your guess is too high.");
                }

                attemptsLeft--;
            }

            if (!guessedCorrectly) {
                System.out.println("Sorry! You've run out of attempts. The correct number was " + numberToGuess + ".");
            }

            totalRounds++;
            System.out.println("Round " + totalRounds + " over. You have won " + roundsWon + " out of " + totalRounds + " rounds.");
            
            System.out.print("Do you want to play again? (yes/no): ");
            String playAgain = scanner.next();

            if (!playAgain.equalsIgnoreCase("yes")) {
                break;
            }
        }

        System.out.println("Game over! You won " + roundsWon + " out of " + totalRounds + " rounds.");
        scanner.close();
    }
}
