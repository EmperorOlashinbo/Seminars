import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

// Functional interface for number selection
interface NumberSelector {
    int selectNumber();
}

public class EurojackpotGenerator {

    public static void main(String[] args) {
        Random random = new Random();

        // Using lambda expressions to implement NumberSelector interface
        NumberSelector selectFirstFive = () -> random.nextInt(50) + 1;
        NumberSelector selectLastTwo = () -> random.nextInt(12) + 1;

        // Using Set to ensure unique numbers
        Set<Integer> firstFiveNumbers = new HashSet<>();
        while (firstFiveNumbers.size() < 5) {
            firstFiveNumbers.add(selectFirstFive.selectNumber());
        }

        Set<Integer> lastTwoNumbers = new HashSet<>();
        while (lastTwoNumbers.size() < 2) {
            lastTwoNumbers.add(selectLastTwo.selectNumber());
        }

        // Combine the sets into one list for display
        List<Integer> eurojackpotNumbers = new ArrayList<>(firstFiveNumbers);
        eurojackpotNumbers.addAll(lastTwoNumbers);

        // Displaying the generated Eurojackpot numbers
        System.out.println("Your Eurojackpot numbers: " + eurojackpotNumbers);
    }
}
