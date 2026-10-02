import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * @author cai filiault
 * @version 1.0
 *
 */
public class Main {
//    public static char reveal(char[] hiddenWord){
//        Random r = new Random();
//        char reveal ;
//        for(int i = 0; i< hiddenWord.length; i++) {
//            reveal = hiddenWord[r.nextInt(((hiddenWord.length - 1)) + 1)];
//        }
//
//        return reveal;
//    }
public static boolean containsChar(char[] array, char target) {
    for (char c : array) {
        if (c == target) {
            return true; // Found it!
        }
    }
    return false; // Looked everywhere and didn't find it
}

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Random r = new Random();
        char[] brokenDownWord, hiddenWord;
        String hint;
        char guess = ' ';
        int numGuesses = 0;
        boolean matchFound = false;
        //first round check

        boolean round1 = false;
        //Welcome the user to the software
        //Prompt them to select a category
        do {
            System.out.println("Welcome to the Guess a word game\n" +
                    "Please select a topic. You can have two 1x use perks, a hint and a letter reveal \n" +
                    "1) General\n" +
                    "2) Lord of the Rings\n" +
                    "3) StarTrek Voyager\n" +
                    "4. Avengers\n" +
                    "5. Bible");
//        String name = "Cai";
//        System.out.println(name);
            String[][] general = {
                    {"enigma", "a person, thing, or situation that is mysterious, puzzling, or difficult to understand completely"},
                    {"tranquil", "calm, quiet, and peaceful, free from disturbance or agitation"},
                    {"vanguard", "the group of people or troops at the front of an advancing army, or the leaders of a new movement in ideas, art, or politics"},
                    {"flummoxed", "being so completely confused, puzzled, or bewildered that you do not know what to do or say"},
                    {"secret", "something kept hidden, unknown, or unseen by others"},
                    {"practice", "doing an activity regularly to improve a skill, following a daily habit, or turning an idea into real action"}
            };
            String[][] lotr = {
                    {"frodo", " is the main hero of The Lord of the Rings film trilogy"},
                    {"samwise", "serving as the loyal gardener, steadfast companion to Baggins, and the emotional heart of the entire story"},
                    {"gondor", " great human kingdom of the South"},
                    {"balrog", "a colossal, terrifying demon of the ancient world made of rock, fire, and shadow"},
                    {"mithrandir", "the Elven name for the wise wizard Gandalf"},
                    {"mithril", "precious silver metal from J.R.R. Tolkien's The Lord of the Rings that is as light as a feather yet stronger than steel"}
            };
            String[][] trek = {
                    {"janeway", "makes a brief cameo appearance as a newly promoted Vice Admiral"},
                    {"chakotay", "He serves as the loyal first officer to Captain Kathryn on the USS Voyager after their ship gets stranded in the distant Delta Quadrant."},
                    {"doctor", "the passionate, humanistic chief medical officer aboard the USS Enterprise in the original Star Trek films"},
                    {"torres", "Chief Engineer of the USS Voyager."},
                    {"paris", "a 23rd-century Starfleet officer who commands the massive space station Yorktown"},
                    {"neelix", "a friendly, optimistic Talaxian from the Delta Quadrant who serves as the ship's chef, morale officer, and guide."}
            };

            String[][] avengers = {
                    {"ironman", "a genius billionaire inventor who builds advanced armored suits and becomes one of the founding members of the Avengers."},
                    {"captainamerica", "a super soldier and natural leader who fights for justice with his iconic vibranium shield."},
                    {"thor", "the Asgardian God of Thunder who wields the enchanted hammer Mjolnir and later Stormbreaker."},
                    {"hulk", "the powerful green alter ego of scientist Bruce Banner, known for his immense strength."},
                    {"blackwidow", "a highly skilled spy and combat expert who serves as a key member of the Avengers."},
                    {"hawkeye", "an expert marksman and archer whose precision and loyalty make him a valuable Avenger."}
            };

            String[][] bible = {
                    {"moses", "the prophet chosen by God to lead the Israelites out of slavery in Egypt and receive the Ten Commandments."},
                    {"david", "the shepherd boy who defeated Goliath and later became one of Israel's greatest kings."},
                    {"solomon", "the wise king of Israel who built the First Temple in Jerusalem."},
                    {"abraham", "the patriarch whom God called to leave his homeland and who became the father of many nations."},
                    {"noah", "the righteous man who built the ark and survived the great flood with his family and pairs of animals."},
                    {"paul", "an apostle and missionary who spread Christianity throughout the Roman world and wrote many New Testament letters."}
            };

            System.out.print("\nAnswer: ");
            byte choice;
            do{
                 choice = input.nextByte();
                input.nextLine();
                if(!round1 && choice>3){
                    System.out.println("You cannot guess an Avenger or a Bible character unless you complete the game at least once");
                    System.out.print("\nAnswer: ");

                }
            }while(!round1 && choice>3);



            System.out.println("");
            String[][] words = switch (choice) {
                case 1 -> general;
                case 2 -> lotr;
                case 3 -> trek;
                case 4 -> avengers;
                default -> bible;
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
            int index = r.nextInt(words.length);
            brokenDownWord = words[index][0].toCharArray();
            hint = words[index][1];
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

            //counting the perk use
            int hintCount= 0;
            int revealCount = 0;

            do {


                System.out.println("Enter a letter in the word. "
                        + new String(hiddenWord) + ">");
                //display appropriate perk use message
                System.out.print("1. Reveal a letter " + (revealCount ==0 ? "[1x Use]":"[not available]") +  "\t 2. Get a hint " + (hintCount == 0 ? "[1x Use]": "[not available]")+ "\n");
                System.out.print("Input: ");

                //getting user's input
                guess = input.nextLine().trim().toLowerCase().charAt(0);

                System.out.println();
                //perk logic
                if(guess == '1'){
                    //verifying if reveal perk is still available
                    if(revealCount == 1){
                        System.out.println("You don't have any reveal perk available");
                    }
                    //reveal a letter
                    else {
                        Random rand = new Random();
                        char reveal = brokenDownWord[rand.nextInt(((hiddenWord.length - 1)) + 1)];
                        for(int i = 0; i<hiddenWord.length; i++){
                            if(brokenDownWord[i] == reveal){
                                boolean check = containsChar(hiddenWord, reveal);
                                if(!check){
                                    hiddenWord[i] = reveal;
                                }
                            }
                        }
                    }
                    revealCount++;
                }
                else if(guess == '2'){
                    //verifying if hint perk is still available
                    if(hintCount == 1){
                        System.out.println("You don't have any hint perk available");
                    }
                    //show hint
                    else{
                        System.out.println(hint);
                        hintCount++;
                    }
                }
//
                else{

                    // "                    BoBy              "
                    // "BoBy"
                    // "BoBy" -> "boby"
                    // "boby" -> 'b'

                    //assign guessed letter to hiddenWord array
                        for (int i = 0; i < brokenDownWord.length; i++) {
                            if (brokenDownWord[i] == guess) {
                                hiddenWord[i] = guess;
                                matchFound = true;
                            }
                        }
                        //count attempts
                        if (!matchFound) numGuesses++;

                        //telling the user if they guessed the write word
                        System.out.println(guess + " was "
                                + (matchFound ? "found" : "not found")
                                + " in the word");
                        // condition ? true : false
                        // 5 > 4 ?

                    //reset matchFound after every guess
                        matchFound = false;
                }
            } while (!Arrays.equals(brokenDownWord, hiddenWord));

            //Final message of completed round and total guesses
            System.out.println("The word is " + new String(hiddenWord) + "." +
                    "You missed " + numGuesses + " time(s)");
            System.out.println("Would you like to play the game again? Enter y or n");
            guess = input.nextLine().trim().toLowerCase().charAt(0);
            numGuesses = 0;

            round1 = true;
            //System.out.println(5>3 ? "Five" : "three");

        }while (guess == 'y');


        //play the game
        input.close();
    }
}