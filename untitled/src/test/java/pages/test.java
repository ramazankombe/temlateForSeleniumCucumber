package pages;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class test {
    public static void main(String[] args) {
        List<String> names= Arrays.asList("Ali","Burak","Cemal", "Derya");

        List<String> zeni = names.stream()
                .filter(x-> x.startsWith("A"))
                .collect(Collectors.toList());

        zeni.forEach(System.out::println);

    }
}
