package exams.Project2;

/**
 * Μια εφαρμόγη που υπολογίζει το μέγιστο άθροισμα συνεχόμενου υποπίνακα απο ενάν δεδομένο πίνακα ακέραιων αριθμών
 */
public class Project2 {



    public static void main(String[] args) {

        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

        int result = findMaximumSumSubarray(arr);
        System.out.println("Το μέγιστο άθροισμα του συνεχόμενου υποπίνακα είναι: " + result);
    }

    public static int findMaximumSumSubarray(int[] arr) {

        if (arr == null || arr.length == 0) {
            return 0;
        }


        int maxSoFar = arr[0];
        int maxEndingHere = arr[0];


        for (int i = 1; i < arr.length; i++) {
            maxEndingHere = Math.max(arr[i], maxEndingHere + arr[i]);

            maxSoFar = Math.max(maxSoFar, maxEndingHere);
        }


        return maxSoFar;
    }


}
