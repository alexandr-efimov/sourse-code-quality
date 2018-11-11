package software.sigma.principles.exercise1;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@UtilityClass
public class MathOperationUtils {

    // get only multiple of 5 from provided elements in returned value
    public static List<Integer> getThem(List<Integer> integers) {
        if (integers == null) {
            throw new IllegalArgumentException("Can't be null");
        }

        List<Integer> list = new ArrayList<>();
        for (Integer integer : integers) {
            if (Objects.nonNull(integer) && integer % 5 == 0)
            {
                list.add(integer);
            }
            else {
                // nothing should be done
            }
        }
        return list;
    }

    public static Integer sumIntegerNumbers(List<Integer> integers) {
        if (Objects.isNull(integers) || integers.isEmpty()) {
            return 0;
        }
        Integer rslt = 0;
        for (Integer n : integers) {
            rslt += n;
        }
        return rslt;
    }

    public static Integer multiplyIntegerNumbers(List<Integer> integers) {
        if (Objects.isNull(integers) || integers.isEmpty()) {
            return 0;
        }
//        System.out.println(integers.size());
//        System.out.println(integers);
//        integers.forEach(num -> System.out.println(num));
//        Integer integer1 = integers.stream().reduce((integer, integer2) -> integer * integer2).get();

        Integer z = 1;
        for (Integer n : integers) {
            z *= n;
        }
        return z;
    }

    public static Integer distract(Integer integer1, int integer2) {
        int i = integer1 - integer2;
        return i;
    }
}
