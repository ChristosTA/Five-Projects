package exams.Project3;
import java.io.*;
import java.util.*;

public class Project3 {
    static final int MAX_CHARACTERS = 128;

    public static void main(String[] args) {

        char[][] characterTable = new char[MAX_CHARACTERS][2];
        for (int i = 0; i < MAX_CHARACTERS; i++) {
            characterTable[i][0] = '\0';
            characterTable[i][1] = 0;
        }


        String inputFile = "C:\\Users\\xrist\\IdeaProjects\\FiveProjects\\src\\Project3.txt";


        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            int c;
            while ((c = reader.read()) != -1) {
                char currentChar = (char) c;


                if (Character.isWhitespace(currentChar)) {
                    continue;
                }

                boolean found = false;

                for (int i = 0; i < MAX_CHARACTERS; i++) {
                    if (characterTable[i][0] == currentChar) {
                        characterTable[i][1]++;
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    for (int i = 0; i < MAX_CHARACTERS; i++) {
                        if (characterTable[i][0] == '\0') {
                            characterTable[i][0] = currentChar;
                            characterTable[i][1] = 1;
                            break;
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Σφάλμα κατά το άνοιγμα ή την ανάγνωση του αρχείου.");
            e.printStackTrace();
        }


        List<CharacterFrequency> frequencyList = new ArrayList<>();


        for (int i = 0; i < MAX_CHARACTERS; i++) {
            if (characterTable[i][0] != '\0') {
                frequencyList.add(new CharacterFrequency(characterTable[i][0], characterTable[i][1]));
            }
        }


        Collections.sort(frequencyList, Comparator.comparingInt(CharacterFrequency::getChar));

        System.out.println("Στατιστικά (Ταξινομημένα κατά χαρακτήρα):");
        for (CharacterFrequency cf : frequencyList) {
            System.out.println(cf.getChar() + ": " + cf.getFrequency());
        }


        Collections.sort(frequencyList, Comparator.comparingInt(CharacterFrequency::getFrequency).reversed());

        System.out.println("\nΣτατιστικά (Ταξινομημένα κατά συχνότητα):");
        for (CharacterFrequency cf : frequencyList) {
            System.out.println(cf.getChar() + ": " + cf.getFrequency());
        }
    }


    public static class CharacterFrequency {
        private char character;
        private int frequency;

        public CharacterFrequency(char character, int frequency) {
            this.character = character;
            this.frequency = frequency;
        }

        public char getChar() {
            return character;
        }

        public int getFrequency() {
            return frequency;
        }
    }
}