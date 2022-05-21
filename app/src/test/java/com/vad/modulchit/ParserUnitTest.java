package com.vad.modulchit;

import com.vad.modulchit.models.Parser;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Arrays;

public class ParserUnitTest {
    @Test
    public void parser_isCorrect() {
        String input = "10, 4, 5, 6,5,3 1, 5, 7";
        int[] arr = {10, 4, 5, 6, 5, 3, 1, 5, 7};
        System.out.println(Arrays.toString(Parser.parseNumber(input)));
        assertArrayEquals(arr, Parser.parseNumber(input));
    }
}
