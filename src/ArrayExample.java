public class ArrayExample {
    public static void main(String[] args) {
        String[] names = {"Cai", "Mike", "Jade"};
                        //  0  ,    1  ,   2
        System.out.println(names.length);
        System.out.println(names[0]);
        System.out.println(names[2]);
        names[2] = "Tom";
        System.out.println(names[2]);
        names[1] = "Tam";
    }
}
