import java.util.ArrayList;
import java.util.List;
import java.util.Random;

interface NumberSelector {
    int selectNumber();
}
public class LambdaExample2 {
    public static void main(String[] args) {
        NumberSelector selectFirstFive = () -> new Random().nextInt(100) + 1;
        NumberSelector selectLastTwo = () -> new Random().nextInt(10) + 1;

        List<Integer> myNumbers = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            myNumbers.add(selectFirstFive.selectNumber());
        }
        for (int i = 0; i < 2; i++) {
            myNumbers.add(selectLastTwo.selectNumber());
        }
        System.out.println("My numbers: " + myNumbers);
    }
}