import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class LambdaExample3 {
    public static void main(String[] args) {
        List<Integer> myNumbers = generateMyNumbers();
        System.out.println("My Random numbers: " + myNumbers);
    }
    private static List<Integer> generateMyNumbers() {
        List<Integer> myNumbers = new ArrayList<>();

        Random random = new Random();
        for (int i = 0; i < 5; i++) {
            myNumbers.add(random.nextInt(100) + 1);
        }
        for (int i = 0; i < 2; i++) {
            myNumbers.add(random.nextInt(10) + 1);
        }
        return myNumbers;
    }
}
