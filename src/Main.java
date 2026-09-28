import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * @author cai filiault
 * @version 1.0
 *
 */
public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random r = new Random();
        char[] brokenDownWord, hiddenWord;
        char guess = ' ';
        int numGuesses = 0;
        boolean matchFound = false;
        //Welcome the user to the software
        //Prompt them to select a category
        do {
            System.out.println("Welcome to the Guess a word game\n" +
                    "Please select a topic. You can have two 1x use perks, a hint and a letter reveal \n" +
                    "1) General\n" +
                    "2) Lord of the Rings\n" +
                    "3) StarTrek Voyager");
//        String name = "Cai";
//        System.out.println(name);
            String[] general = {"enigma", "tranquil", "vanguard", "flummoxed",
                    "secret", "practice"};
            String[] lotr = {"frodo", "samwise", "gondor", "balrog", "mithrandir",
                    "mithril"};
            String[] trek = {"janeway", "chakotay", "doctor", "torres", "paris",
                    "neelix"};
            byte choice = input.nextByte();
            input.nextLine();

            String[] words = switch (choice) {
                case 1 -> general;
                case 2 -> lotr;
                default -> trek;
            };
//        String[] words;
//        switch (choice){
//            case 1:
//                words = general;
//            break;
//            case 2:
//                words = lotr;
//            break;
//            default:
//                words = trek;
//        }
            //Select a random word from that category
            brokenDownWord = words[r.nextInt(words.length)].toCharArray();
            //r.nextInt(words.length)
            //words.length = 7
            //r.nextInt(7)-> 0-6
            //words[0] -> "frodo"
            //"frodo".toCharArray() -> ['f']['r']['o']['d']['o']
            //brokenDownWord =  ['f']['r']['o']['d']['o']
            //Break that word down into a character array
            //Build a hidden word array that matches the character array
            hiddenWord = new char[brokenDownWord.length]; // ['']['']['']['']['']
            Arrays.fill(hiddenWord, '*'); //['*']['*']['*']['*']['*']

            boolean hintActive, revealActive = false;



            do {

                System.out.println("Enter a letter in the word. "
                        + new String(hiddenWord) + ">");
//
                guess = input.nextLine().trim().toLowerCase().charAt(0);
                // "                    BoBy              "
                // "BoBy"
                // "BoBy" -> "boby"
                // "boby" -> 'b'
                for (int i = 0; i < brokenDownWord.length; i++) {
                    if (brokenDownWord[i] == guess) {
                        hiddenWord[i] = guess;
                        matchFound = true;
                    }
                }
                if (!matchFound) numGuesses++;
                System.out.println(guess + " was "
                        + (matchFound ? "found" : "not found")
                        + " in the word");
                // condition ? true : false
                // 5 > 4 ?
                matchFound = false;
            } while (!Arrays.equals(brokenDownWord, hiddenWord));
            System.out.println("The word is " + new String(hiddenWord) + "." +
                    "You missed " + numGuesses + " time(s)");
            System.out.println("Would you like to play the game again? Enter y or n");
            guess = input.nextLine().trim().toLowerCase().charAt(0);
            numGuesses = 0;
            //System.out.println(5>3 ? "Five" : "three");

        }while (guess == 'y');


        //play the game
        input.close();
    }
}