package exams.Project4;
import java.util.Scanner;

/**
 * Αυτή η εφαρμογή υλοποιεί το παιχνίδι της τρίλιζας  με έναν αρχικοποιημένο παίκτη
 * και έναν πίνακα 3x3 για το πεδίο παιχνιδιού.
 *
 * Ο πίνακας 3x3 αντιπροσωπεύει το πεδίο, όπου οι παίκτες τοποθετούν τα σύμβολά τους
 * (συνήθως 'X' και 'O'). Το πρόγραμμα ελέγχει τις εξής παραμέτρους:
 * - Αν η θέση είναι ήδη καλυμμένη.
 * - Αν οι αριθμοί που δίνονται για τις θέσεις βρίσκονται εντός των ορίων του πίνακα (1-9).
 *
 * Στο τέλος του παιχνιδιού, το πρόγραμμα εμφανίζει το αποτέλεσμα:
 * - "Νίκη" αν κάποιος παίκτης έχει καταφέρει να ευθυγραμμίσει τρία από τα σύμβολά του σε μία γραμμή,
 *   στήλη ή διαγώνιο.
 * - "Ισοπαλία" αν όλοι οι χώροι του πίνακα είναι γεμάτοι χωρίς νικητή.
 */

public class Project4 {

    static char[][] board = new char[3][3];
    static char currentPlayer = 'X';

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);


        initializeBoard();


        while (true) {
            printBoard();
            System.out.println("Παίκτης " + currentPlayer + ", εισάγετε θέση (π.χ. 1 1 για γραμμή 1, στήλη 1): ");

            try {
                String input = sc.nextLine();
                String[] inputs = input.split(" ");

                if (inputs.length != 2) {
                    throw new IllegalArgumentException("Πρέπει να εισάγονται ακριβώς δύο αριθμοί!");
                }

                int row = Integer.parseInt(inputs[0]) - 1;
                int col = Integer.parseInt(inputs[1]) - 1;


                if (row < 0 || row > 2 || col < 0 || col > 2) {
                    throw new IndexOutOfBoundsException("Οι αριθμοί πρέπει να είναι μεταξύ 1 και 3 για γραμμή και στήλη!");
                }


                if (book(currentPlayer, row, col)) {

                    if (checkWinner()) {
                        printBoard();
                        System.out.println("Παίκτης " + currentPlayer + " κέρδισε!");
                        break;
                    }


                    if (checkDraw()) {
                        printBoard();
                        System.out.println("Ισοπαλία!");
                        break;
                    }


                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                } else {
                    System.out.println("Η θέση είναι ήδη κατειλημμένη. Προσπαθήστε ξανά.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Σφάλμα: Πρέπει να εισάγονται αριθμοί!");
            } catch (IllegalArgumentException e) {
                System.out.println("Σφάλμα: " + e.getMessage());
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Σφάλμα: " + e.getMessage());
            }
        }

        sc.close();
    }


    public static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }


    public static void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }


    public static boolean book(char symbol, int row, int col) {
        if (board[row][col] == '-') {
            board[row][col] = symbol;
            return true;
        }
        return false;
    }


    public static boolean checkWinner() {

        for (int i = 0; i < 3; i++) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                return true;
            }
        }


        for (int i = 0; i < 3; i++) {
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                return true;
            }
        }


        if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) {
            return true;
        }
        if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) {
            return true;
        }

        return false;
    }


    public static boolean checkDraw() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }
}