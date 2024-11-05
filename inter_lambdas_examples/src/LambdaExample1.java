import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class LambdaExample1 {
    public static void main(String[] args) {
        List<Integer> myNumbers = IntStream.concat(
                new Random().ints(5,1,100),
                new Random().ints(2,1,10)
        )
                .boxed()
                .collect(Collectors.toList());

        System.out.println("My Random Numbers: " + myNumbers);
    }
}