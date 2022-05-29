package com.vad.modulchit;

import com.vad.modulchit.models.Parser;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class ParserUnitTest {

    private String input;

    @Before
    public void setUp() {
        input = "10, 4, 5, 6,5,3 1, 5, 7";
    }

    @Test
    public void parserToArray_isCorrect() {
        int[] arr = {10, 4, 5, 6, 5, 3, 1, 5, 7};
        assertArrayEquals(arr, Parser.parseToIntArray(input));
    }

    @Test
    public void parserToList_isCorrect() {
        List<Integer> list = List.of(10, 4, 5, 6, 5, 3, 1, 5, 7);
        System.out.println(Arrays.toString(Parser.parseToIntList(input).toArray()));
        assertArrayEquals(list.toArray(), Parser.parseToIntList(input).toArray());
    }
}
