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

        return Arrays.stream(values).distinct().sorted()
                .reduce(0, (a, b) -> a * 10 + b);
    }

    public static List<Integer> oddOrEven(List<Integer> integers) {

        int sum = integers.stream().mapToInt(i -> i).sum();

        return integers.stream().filter(x -> x % 2 != sum % 2).collect(Collectors.toList());
    }
}
