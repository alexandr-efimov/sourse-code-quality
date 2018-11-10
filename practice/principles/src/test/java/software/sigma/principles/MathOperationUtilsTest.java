package software.sigma.principles;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static software.sigma.principles.MathOperationUtils.distract;
import static software.sigma.principles.MathOperationUtils.getThem;
import static software.sigma.principles.MathOperationUtils.multiplyIntegerNumbers;
import static software.sigma.principles.MathOperationUtils.sumIntegerNumbers;

class MathOperationUtilsTest {

    @Test
    void getThemShouldReturnExpected() {
        List<Integer> items = IntStream.range(0, 50).boxed().collect(toList());
        List<Integer> expected = items.stream().filter(number -> number % 5 == 0).collect(toList());

        List<Integer> result = getThem(items);
        assertEquals(expected, result);
    }

    @Test
    void getThemShouldReturnEmptyForEmptyParam() {
        List<Integer> result = getThem(emptyList());
        assertEquals(emptyList(), result);
    }

    @Test
    void getThemShouldBeErrorForNullParam() {
        assertThrows(IllegalArgumentException.class, () -> getThem(null));
    }

    @Test
    void sumIntegerNumbersShouldBeSameToExpected() {
        List<Integer> sourceNumbers = Arrays.asList(1, 2, 3, 4);
        Integer expectedResult = sourceNumbers.stream().reduce(Integer::sum).get();

        Integer result = sumIntegerNumbers(sourceNumbers);
        assertEquals(expectedResult, result);
    }

    @Test
    void multiplyIntegerNumbersShouldBeSameToExpected() {
        List<Integer> sourceNumbers = Arrays.asList(1, 2, 3, 4);
        Integer expectedResult = sourceNumbers.stream().reduce((u, v) -> u * v).get();

        Integer result = multiplyIntegerNumbers(sourceNumbers);
        assertEquals(expectedResult, result);
    }

    @Test
    void distractShouldBeSameToExpected() {
        int sourceNumber = 11;
        int numberToDestructFromSource = 22;
        Integer expected = sourceNumber - numberToDestructFromSource;

        Integer result = distract(sourceNumber, numberToDestructFromSource);
        assertEquals(expected, result);
    }
}