import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EurojackpotGenerator {

    public static void main(String[] args) {
        // Using Streams and lambda expressions to generate Eurojackpot numbers
        List<Integer> eurojackpotNumbers = IntStream.concat(
                        new Random().ints(5, 1, 51),
                        new Random().ints(2, 1, 13)
                )
                .boxed()
                .collect(Collectors.toList());

        // Displaying the generated Eurojackpot numbers
        System.out.println("Your Eurojackpot numbers: " + eurojackpotNumbers);
    }
}
