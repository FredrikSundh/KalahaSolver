import java.util.Arrays;
import java.util.Scanner;

class kalahasolver {

    public static void main(String[] args) {
        playGame();

    }

    static int[] setupBoard() {
        int[] currentBoard = new int[12];
        Arrays.fill(currentBoard, 6);
        return currentBoard;
    }

    static void playGame() {
        Scanner scanner = new Scanner(System.in);
        int[] currentBoard = setupBoard();
        while (true) {
            System.out.print("Which nest did the opponent play ?: ");
            String userInput = scanner.nextLine();
            int opponentPlayedNest = Integer.parseInt(userInput) - 1;
            updateBoardState(currentBoard, opponentPlayedNest, false); // makes the move the opponent made to update the
            // boardState
            System.out.println("State after opponent play" + Arrays.toString(currentBoard));
            int userShouldPlay = findOptimalNest(currentBoard) - 1; // gets which nest the user should play
            updateBoardState(currentBoard, userShouldPlay, true); // updates the board state to the state the player
                                                                  // should
            // make
            System.out.println("State after your play" + Arrays.toString(currentBoard));

        }

    }

    static int findOptimalNest(int[] currentBoard) {
        int currentBestScore = 0;
        int nestToPlay = 0;
        for (int i = 0; i < 12; i++) { // plays all nests and finds the best score
            int scoreForNest = playNest(i, currentBoard);
            currentBestScore = Math.max(currentBestScore, scoreForNest);
            if (currentBestScore == scoreForNest) { // if the max score is updated this nest is the best one to play
                                                    // right now
                nestToPlay = i + 1; // Zeroth index will be nest 1
            }

        }
        System.out.println("The best score obtainabe: " + currentBestScore);
        System.out.println("Play Nest " + nestToPlay);

        return nestToPlay;
    }

    static void updateBoardState(int[] currentBoard, int chosenNest, boolean yourTurn) { // This function should update
                                                                                         // and return the
        // boardstate
        // resulted
        // from a previous move This boardstate should now be set as the
        // new currentboard
        // when finding the next optimal nest

        int ballsinHand = currentBoard[chosenNest]; // puts balls in hand
        currentBoard[chosenNest] = 0; // removes balls from nest
        int iterator = 1;
        int obtainedScore = 0;
        while (ballsinHand > 0) {
            int nextNest = chosenNest + iterator;
            if (nextNest == 12) { // a ball is placed inside your nest giving you a point
                if (yourTurn) {
                    ballsinHand--;
                }
                nextNest = 0;
                chosenNest = 0; // Resets all variables so it will select the first nest on next loop
                iterator = 0;

            }
            if (nextNest == 6 && !yourTurn) { // accounts for opponent placing ball in their nest
                ballsinHand--;
                if (ballsinHand == 0) {
                    return;
                }
            }

            currentBoard[nextNest] += 1;
            ballsinHand--;
            if (ballsinHand == 0 && currentBoard[nextNest] > 1) {
                ballsinHand = currentBoard[nextNest];
                currentBoard[nextNest] = 0;
            }
            iterator++;

        }
    }

    static int playNest(int chosenNest, int[] currentBoard) { // this function will pick a chosen nest and play a move
                                                              // until termination, it will then report the obtained
                                                              // score back
        int[] copiedArray = Arrays.copyOf(currentBoard, 12); // makes deep copy of the current board state
        int ballsinHand = copiedArray[chosenNest]; // puts balls in hand
        copiedArray[chosenNest] = 0; // removes balls from nest
        int iterator = 1;
        int obtainedScore = 0;
        while (ballsinHand > 0) {
            int nextNest = chosenNest + iterator;
            if (nextNest == 12) { // a ball is placed inside your nest giving you a point
                obtainedScore++;
                ballsinHand--;
                nextNest = 0;
                chosenNest = 0; // Resets all variables so it will select the first nest on next loop
                iterator = 0;

            }

            copiedArray[nextNest] += 1;
            ballsinHand--;
            if (ballsinHand == 0 && copiedArray[nextNest] > 1) {
                ballsinHand = copiedArray[nextNest];
                copiedArray[nextNest] = 0;
            }
            iterator++;

        }
        return obtainedScore;
    }

}