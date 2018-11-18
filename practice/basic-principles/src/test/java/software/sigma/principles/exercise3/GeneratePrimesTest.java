package software.sigma.principles.exercise3;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GeneratePrimesTest {

    private static final int PRIME_CERTAINTY = 5;

    @Test
    void generatePrimesShouldBeAsExpected() {
        int maxNumberForPrimeCheck = 53;

        List<Integer> expectedResult = IntStream.range(1, maxNumberForPrimeCheck + 1)
                .mapToObj(BigInteger::valueOf)
                .filter(number -> number.isProbablePrime(PRIME_CERTAINTY))
                .map(BigInteger::intValue)
                .collect(Collectors.toList());

        List<Integer> result = Arrays.stream(GeneratePrimes.primeGenerator(maxNumberForPrimeCheck))
                .boxed().collect(Collectors.toList());

        assertEquals(expectedResult, result);
    }
}