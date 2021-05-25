import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainStream {

    public static int minValue(int[] values) {

        String number = Arrays.stream(values).distinct().sorted().mapToObj(String::valueOf).reduce((x, y) -> x + y).get();
        return Integer.parseInt(number);
    }

    public static List<Integer> oddOrEven(List<Integer> integers) {

        int sum = integers.stream().mapToInt(i -> i).sum();

        List<Integer> oddOrEven;
        if (sum % 2 == 0) {
            oddOrEven = integers.stream().filter(x -> x % 2 != 0).collect(Collectors.toList());
        }
        oddOrEven = integers.stream().filter(x -> x % 2 == 0).collect(Collectors.toList());

        return oddOrEven;
    }

    public static void main(String[] args) {

    }
}
