package exams.Project5;

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
