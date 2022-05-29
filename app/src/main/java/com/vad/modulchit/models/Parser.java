package com.vad.modulchit.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Parser {
    public static int[] parseToArray(String str) {
        Matcher matcher = Pattern.compile("\\d+").matcher(str); //([0-9]*\.?[0-9]+)
        List<String> list = new ArrayList<>();
        while (matcher.find()) {
            list.add(matcher.group());
        }

        return list.stream().mapToInt(Integer::parseInt).toArray();
    }

    public static List<Integer> parseToList(String str){
        return Arrays.stream(parseToArray(str)).boxed().collect(Collectors.toList());
    }
}
