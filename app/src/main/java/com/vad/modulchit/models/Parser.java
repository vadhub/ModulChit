package com.vad.modulchit.models;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    public static int[] parseNumber(String str) {
        Matcher matcher = Pattern.compile("\\d+").matcher(str);
        List<String> list = new ArrayList<>();
        while (matcher.find()) {
            list.add(matcher.group());
        }

        return list.stream().mapToInt(Integer::parseInt).toArray();
    }
}
