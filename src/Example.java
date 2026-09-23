import java.util.Scanner;

public class Example {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("How many names will you enter?");
        byte num = input.nextByte();
        input.nextLine();
        System.out.println("Enter the list of names");
        String[] names = new String[num];
        for(int i = 0; i < names.length; i++){
            names[i] = input.nextLine();
        }
        for(int i = 0; i < names.length; i++){
            System.out.println(names[i]);
        }
        input.close();
    }
}
