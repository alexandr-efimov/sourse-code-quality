package com.software.sigma.hometask.naming;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NamingTask {

    private List<Integer> getThem(List<Integer> integers) {
        List<Integer> list = new ArrayList<>();
        for (Integer x : integers)
            if (x % 5 == 0) {
                list.add(x);
            }
        return list;
    }

    @Test
    void testGetThem() {
        List<Integer> items = IntStream.range(0, 50).boxed().collect(toList());
        List<Integer> expected = items.stream().filter(number -> number % 5 == 0).collect(toList());

        List<Integer> result = getThem(items);
        assertEquals(expected, result);
    }
}
