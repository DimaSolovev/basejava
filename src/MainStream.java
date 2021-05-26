import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainStream {

    public static void main(String[] args) {

        int[] arr = {1, 2, 3, 4, 5};
        int min = minValue(arr);
        System.out.println(min);

        List<Integer> list = Arrays.asList(1, 2, 3);
        List<Integer> result = oddOrEven(list);
        System.out.println(result);
    }

    public static int minValue(int[] values) {

        int number = Arrays.stream(values).distinct().sorted()
                .reduce((a, b) -> a * 10 + b).getAsInt();
        return number;
    }

    public static List<Integer> oddOrEven(List<Integer> integers) {

        int sum = integers.stream().mapToInt(i -> i).sum();

        List<Integer> oddOrEven = integers.stream().filter(x -> {
            if (sum % 2 == 0) {
                return x % 2 != 0;
            } else
                return x % 2 == 0;
        }).collect(Collectors.toList());

        return oddOrEven;
    }
}
