package exams.Project5;

/**
 * Αυτή η εφαρμογή υλοποιεί την διαχείριση ενός θεάτρου με 30 σειρές και 12 στήλες.
 * Κάθε θέση στο θέατρο αναπαρίσταται με έναν χαρακτήρα για τη στήλη και έναν αριθμό για τη σειρά.
 * Για παράδειγμα, η θέση "C2" αναφέρεται στην 2η σειρά και στην 3η στήλη του θεάτρου.
 *
 * Η εφαρμογή περιλαμβάνει τις παρακάτω μεθόδους:
 *
 * - **book(char column, int row)**: Κρατά μια θέση στο θέατρο, αν δεν είναι ήδη κρατημένη.
 *
 * - **cancel(char column, int row)**: Ακυρώνει μια κράτηση σε μια θέση, αν είναι ήδη κρατημένη.
 *
 * Το θέατρο έχει συνολικά 30 σειρές και 12 στήλες, δηλαδή 360 θέσεις συνολικά. Κάθε θέση μπορεί να
 * είναι είτε διαθέσιμη είτε κρατημένη.
 *
 * Σκοπός της εφαρμογής είναι η διαχείριση των κρατήσεων και ακυρώσεων για τις θέσεις του θεάτρου.
 */
public class Project5 {

    public boolean[][] seats = new boolean[30][12];


    public void book(char column, int row) {

        int colIndex = column - 'A';
        int rowIndex = row - 1;


        if (seats[rowIndex][colIndex]) {
            System.out.println("Η θέση " + column + row + " είναι ήδη κρατημένη.");
        } else {
            seats[rowIndex][colIndex] = true;
            System.out.println("Η θέση " + column + row + " έχει κρατηθεί.");
        }
    }


    public void cancel(char column, int row) {

        int colIndex = column - 'A';
        int rowIndex = row - 1;


        if (!seats[rowIndex][colIndex]) {
            System.out.println("Η θέση " + column + row + " δεν έχει κρατηθεί.");
        } else {
            seats[rowIndex][colIndex] = false;
            System.out.println("Η κράτηση για τη θέση " + column + row + " ακυρώθηκε.");
        }
    }


    public void displaySeats() {
        System.out.println("Θέατρο: ");
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 12; j++) {

                if (seats[i][j]) {
                    System.out.print("X ");  // Kρατημένη θέση
                } else {
                    System.out.print("O ");  // Διαθέσιμη θέση
                }
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        Project5 theatre = new Project5();


        theatre.book('A', 1);  // Κράτηση της θέσης A1
        theatre.book('A', 1);  // Δοκιμή να κρατήσουμε ξανά την A1
        theatre.book('B', 10);  // Κράτηση της θέσης B2
        theatre.cancel('A', 8); // Ακύρωση της θέσης A1
        theatre.cancel('C', 3); // Δοκιμή να ακυρώσουμε τη θέση C3 που δεν έχει κρατηθεί
        theatre.book('C',10);


        theatre.displaySeats();
    }
}
