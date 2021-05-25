import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainStream {

    public static int minValue(int[] values) {

        String number = Arrays.stream(values).distinct().sorted().mapToObj(x -> String.valueOf(x)).reduce((x, y) -> x + y).get();
        int min = Integer.valueOf(number);
        return min;
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

        int[] arr = {1, 2, 3, 4, 5};
        int min = minValue(arr);
        System.out.println(min);


//        List<Integer> list = new ArrayList<>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        list.add(4);
//        list.add(5);
//        List<Integer> list1 = oddOrEven(list);
//        System.out.println(list1);
    }
}
