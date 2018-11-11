package software.sigma.library.aamyne;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

class NamingTask {

    // get multiple of 5 from provided elements
    private List<Integer> getThem(List<Integer> integers) {
        if (integers == null) {
            throw new IllegalArgumentException("Can't be null");
        }
        List<Integer> list = new ArrayList<>();
        for (Integer x : integers)
            if (x % 5 == 0) {
                list.add(x);
            }
        return list;
    }

    @Test
    void testGetThemShouldReturnExpected() {
        List<Integer> items = IntStream.range(0, 50).boxed().collect(toList());
        List<Integer> expected = items.stream().filter(number -> number % 5 == 0).collect(toList());

        List<Integer> result = getThem(items);
        Assertions.assertEquals(expected, result);
    }

    @Test
    void testGetThemShouldReturnEmptyForEmptyParam() {
        List<Integer> result = getThem(emptyList());
        Assertions.assertEquals(emptyList(), result);
    }

    @Test
    void testGetThemShouldBeErrorForNullParam() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> getThem(null));
    }
}
