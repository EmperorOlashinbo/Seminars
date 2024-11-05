import java.util.List;
import java.util.Random;
import java.util.function.IntSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class LambdaExample4 {
    public static void main(String[] args) {
        IntSupplier selectFirstFive = () -> new Random().nextInt(100) + 1;
        IntSupplier selectLastTwo = () -> new Random().nextInt(10) + 1;

        List<Integer> myNumbers = IntStream.concat(
                IntStream.generate(selectFirstFive).limit(5),
                IntStream.generate(selectLastTwo).limit(2)
        )
                .boxed()
                .collect(Collectors.toList());

        System.out.println("My Random numbers: " + myNumbers);
    }
}