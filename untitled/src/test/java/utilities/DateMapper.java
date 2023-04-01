package utilities;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Ismail Koembe
 */
@Slf4j
public class DateMapper {

    /**
    * @param from
     * @param to
     * This method create a map that contains LocalDate and its String for given time period
    * */
    public static Map<String, LocalDate> dateMapper(int from, int to){
        return IntStream.range(from, to)
                .boxed()
                .collect(Collectors.toMap(
                        i -> {
                            switch (i) {
                                case -1:
                                    return "yesterday";
                                case 0:
                                    return "today";
                                case 1:
                                    return "tomorrow";
                                default:
                                    return "day" + i;
                            }
                        },
                        i -> LocalDate.now().plusDays(i)
                ));



    }

    public static String dateFormatter(String dateString){
        log.info("Given date string {}", dateString);
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("E, dd/MM/");
        LocalDate date = LocalDate.parse(dateString, inputFormatter);
        return date.format(outputFormatter);
    }
}
