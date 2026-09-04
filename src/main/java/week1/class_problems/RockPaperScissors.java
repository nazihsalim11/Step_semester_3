package week1.class_problems;

import java.util.Random;

/**
 * Problem 1 - Rock-Paper-Scissors Game (College Coding Arcade)
 *
 * Plays N rounds between the player and a random computer opponent,
 * prints a round-by-round table and a final scoreboard.
 */
public class RockPaperScissors {

    // Decides the result of ONE round from the player's point of view.
    static String playRound(String playerMove, String computerMove) {
        if (playerMove.equalsIgnoreCase(computerMove)) {
            return "Draw";
        }
        // Every winning combination for the player
        if ((playerMove.equalsIgnoreCase("Rock") && computerMove.equalsIgnoreCase("Scissors"))
                || (playerMove.equalsIgnoreCase("Paper") && computerMove.equalsIgnoreCase("Rock"))
                || (playerMove.equalsIgnoreCase("Scissors") && computerMove.equalsIgnoreCase("Paper"))) {
            return "Player Wins";
        }
        return "Computer Wins";
    }

    public static void main(String[] args) {
        String[] moves = {"Rock", "Paper", "Scissors"};

        // Predefined player moves so the demo runs the same way every time
        String[] playerMoves = {"Rock", "Paper", "Scissors", "Rock", "Paper"};
        int rounds = playerMoves.length;

        String[] computerMoves = new String[rounds];
        String[] results = new String[rounds];

        Random random = new Random();
        int wins = 0, losses = 0, draws = 0;

        for (int i = 0; i < rounds; i++) {
            computerMoves[i] = moves[random.nextInt(3)];   // 0, 1 or 2
            results[i] = playRound(playerMoves[i], computerMoves[i]);

            if (results[i].equals("Player Wins")) {
                wins++;
            } else if (results[i].equals("Computer Wins")) {
                losses++;
            } else {
                draws++;
            }
        }

        System.out.println("Round | Player Move | Computer Move | Result");
        for (int i = 0; i < rounds; i++) {
            System.out.println((i + 1) + "     | " + playerMoves[i]
                    + "        | " + computerMoves[i]
                    + "         | " + results[i]);
        }

        double winPercent = (wins * 100.0) / rounds;   // 100.0 keeps this a double division
        System.out.println("\nWins: " + wins + " | Losses: " + losses
                + " | Draws: " + draws + " | Win % = " + winPercent + "%");
    }
}
