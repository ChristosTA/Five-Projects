package exams.Project1;
import java.io.*;
import java.util.*;

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

            try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\xrist\\IdeaProjects\\FiveProjects\\src\\FileOut.txt"))) {
                divideIntoGroupsAndApplyFilters(array, index, writer);
                System.out.println("Τα αποτελέσματα των φίλτρων γράφτηκαν στο output.txt");

            } catch (IOException e) {
                System.out.println("Σφάλμα κατά τη γραφή στο αρχείο.");
            }

        } catch (FileNotFoundException e) {
            System.out.println("Το αρχείο δεν βρέθηκε.");
        } catch (InputMismatchException e) {
            System.out.println("Το αρχείο περιέχει μη έγκυρους ακεραίους.");
        }
    }


    public static void divideIntoGroupsAndApplyFilters(int[] array, int size, BufferedWriter writer) throws IOException {
        int groupSize = 6;
        int groupCount = (int) Math.ceil((double) size / groupSize);  // Υπολογισμός του αριθμού των 6άδων

        for (int i = 0; i < groupCount; i++) {
            int start = i * groupSize;
            int end = Math.min((i + 1) * groupSize, size);


            int[] group = Arrays.copyOfRange(array, start, end);


            if (applyFilters(group, group.length)) {
                writer.write("Ομάδα " + (i + 1) + " που πέρασε τα φίλτρα: " + Arrays.toString(group) + "\n");
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

    // 1) Να περιέχει το πολύ 4 άρτιους
    public static boolean checkEvenCount(int[] array, int size) {
        int evenCount = 0;
        for (int i = 0; i < size; i++) {
            if (array[i] % 2 == 0) {
                evenCount++;
            }
        }
        return evenCount <= 4;
    }

    // 2) Να περιέχει το πολύ 4 περιττούς
    public static boolean checkOddCount(int[] array, int size) {
        int oddCount = 0;
        for (int i = 0; i < size; i++) {
            if (array[i] % 2 != 0) {
                oddCount++;
            }
        }
        return oddCount <= 4;
    }

    // 3) Να περιέχει το πολύ 2 συνεχόμενους αριθμούς
    public static boolean checkConsecutive(int[] array, int size) {
        int consecutiveCount = 0;
        for (int i = 1; i < size; i++) {
            if (array[i] == array[i-1] + 1) {
                consecutiveCount++;
                if (consecutiveCount > 2) {
                    return false;
                }
            }
        }
        return true;
    }

    // 4) Να περιέχει το πολύ 3 ίδιους λήγοντες
    public static boolean checkSameLastDigit(int[] array, int size) {
        Map<Integer, Integer> lastDigitCount = new HashMap<>();
        for (int i = 0; i < size; i++) {
            int lastDigit = array[i] % 10;
            lastDigitCount.put(lastDigit, lastDigitCount.getOrDefault(lastDigit, 0) + 1);
        }

        for (int count : lastDigitCount.values()) {
            if (count > 3) {
                return false;
            }
        }
        return true;
    }

    // 5) Να περιέχει το πολύ 3 αριθμούς στην ίδια δεκάδα
    public static boolean checkSameDecade(int[] array, int size) {
        Map<Integer, Integer> decadeCount = new HashMap<>();
        for (int i = 0; i < size; i++) {
            int decade = array[i] / 10;
            decadeCount.put(decade, decadeCount.getOrDefault(decade, 0) + 1);
        }

        for (int count : decadeCount.values()) {
            if (count > 3) {
                return false;
            }
        }
        return true;
    }
}
