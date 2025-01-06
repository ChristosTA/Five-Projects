package exams.Project1;
import java.io.*;
import java.util.*;

/**
 * Είναι μια εφαρμογή που αρχικοποιεί, διαβάζει ένα αρχείο FileIn.txt με έναν scanner.
 * Έπειτα ελέγχει τις τιμές από το αρχείο αν είναι έγκυρες και τις περνάει σε έναν int πίνακα, όπου γίνεται το sort.
 * Έχουμε μεθόδους που παίρνουν τους αριθμούς και δημιουργούν κάθε πιθανή εξάδα και αναφέρει πόσες εξάδες φτιαχτήκαν, και τα φίλτρα, όπου μόλις φτιαχτεί η εξάδα περνάει από τα φίλτρα.
 * Τα αποτελέσματα που περνάνε ως true γράφονται σε ένα αρχείο fileOut.txt και εμφανιζέται ενα μήνυμα στην οθόνη.
 */

public class Project1 {

    public static void main(String[] args) {
        int[] array = new int[50];
        File fd = new File("C:\\Users\\xrist\\IdeaProjects\\FiveProjects\\src\\FileIn.txt");

        try (Scanner scanner = new Scanner(fd)) {
            int index = 0;

            while (scanner.hasNext()) {
                if (index < array.length) {
                    int value = scanner.nextInt();

                    if (value >= 1 && value <= 49) {
                        array[index] = value;
                        index++;
                    } else {
                        System.out.println("Η τιμή " + value + " δεν είναι έγκυρη.");
                    }
                } else {
                    System.out.println("Ο πίνακας είναι γεμάτος.");
                    break;
                }
            }

            Arrays.sort(array, 0, index);
            System.out.println("Ταξινομημένος πίνακας: " + Arrays.toString(Arrays.copyOf(array, index)));


            List<int[]> combinations = getCombinations(array, index, 6);
            System.out.println("Σύνολο εξάδων: " + combinations.size());


            try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\xrist\\IdeaProjects\\FiveProjects\\src\\FileOut.txt"))) {
                applyFiltersAndWrite(combinations, writer);
                System.out.println("Τα αποτελέσματα των φίλτρων γράφτηκαν στο FileOut.txt");
            } catch (IOException e) {
                System.out.println("Σφάλμα κατά τη γραφή στο αρχείο.");
            }

        } catch (FileNotFoundException e) {
            System.out.println("Το αρχείο δεν βρέθηκε.");
        } catch (InputMismatchException e) {
            System.out.println("Το αρχείο περιέχει μη έγκυρους ακεραίους.");
        }
    }


    public static List<int[]> getCombinations(int[] array, int size, int k) {
        List<int[]> combinations = new ArrayList<>();
        int[] combination = new int[k];
        generateCombinations(array, combinations, combination, 0, size - 1, 0, k);
        return combinations;
    }

    public static void generateCombinations(int[] array, List<int[]> combinations, int[] combination, int start, int end, int index, int k) {
        if (index == k) {
            combinations.add(Arrays.copyOf(combination, k));
            return;
        }

        for (int i = start; i <= end && end - i + 1 >= k - index; i++) {
            combination[index] = array[i];
            generateCombinations(array, combinations, combination, i + 1, end, index + 1, k);
        }
    }


    public static void applyFiltersAndWrite(List<int[]> combinations, BufferedWriter writer) throws IOException {
        for (int[] combination : combinations) {
            if (applyFilters(combination, combination.length)) {
                writer.write("Εξάδα που πέρασε τα φίλτρα: " + Arrays.toString(combination) + "\n");
            }
        }
    }


    public static boolean applyFilters(int[] array, int size) {
        return checkEvenCount(array, size) &&
                checkOddCount(array, size) &&
                checkConsecutive(array, size) &&
                checkSameLastDigit(array, size) &&
                checkSameDecade(array, size);
    }


    public static boolean checkEvenCount(int[] array, int size) {
        int evenCount = 0;
        for (int i = 0; i < size; i++) {
            if (array[i] % 2 == 0) {
                evenCount++;
            }
        }
        return evenCount <= 4;
    }

    public static boolean checkOddCount(int[] array, int size) {
        int oddCount = 0;
        for (int i = 0; i < size; i++) {
            if (array[i] % 2 != 0) {
                oddCount++;
            }
        }
        return oddCount <= 4;
    }

    public static boolean checkConsecutive(int[] array, int size) {
        int consecutiveCount = 0;
        for (int i = 1; i < size; i++) {
            if (array[i] == array[i - 1] + 1) {
                consecutiveCount++;
                if (consecutiveCount >= 2) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean checkSameLastDigit(int[] array, int size) {
        Map<Integer, Integer> lastDigitCount = new HashMap<>();
        for (int i = 0; i < size; i++) {
            int lastDigit = array[i] % 10;
            lastDigitCount.put(lastDigit, lastDigitCount.getOrDefault(lastDigit, 0) + 1);
        }

        for (int count : lastDigitCount.values()) {
            if (count >= 3) {
                return false;
            }
        }
        return true;
    }

    public static boolean checkSameDecade(int[] array, int size) {
        Map<Integer, Integer> decadeCount = new HashMap<>();
        for (int i = 0; i < size; i++) {
            int decade = array[i] / 10;
            decadeCount.put(decade, decadeCount.getOrDefault(decade, 0) + 1);
        }

        for (int count : decadeCount.values()) {
            if (count >= 3) {
                return false;
            }
        }
        return true;
    }
}
