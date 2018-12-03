package io.christianmesch.aoc18.day03;

import io.christianmesch.aoc18.utils.InputUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Day03 {

  private static final String INPUT_FILENAME = "/day3.txt";

  public static void main(String... args) {
    Day03 day = new Day03();
    try {
      System.out.println(day.part1());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private long part1() throws Exception {
    final Pattern pattern = Pattern.compile("^#(\\d+) @ (\\d+),(\\d+): (\\d+)x(\\d+)$");
    return InputUtils.read(INPUT_FILENAME).stream()
        .map(pattern::matcher)
        .filter(Matcher::matches)
        .map(matcher -> {
          List<String> coordinates = new ArrayList<>();
          int startX = Integer.parseInt(matcher.group(2));
          int startY = Integer.parseInt(matcher.group(3));
          int width = Integer.parseInt(matcher.group(4));
          int height = Integer.parseInt(matcher.group(5));

          for (int x = startX; x < startX + width; x++) {
            for (int y = startY; y < startY + height; y++) {
              coordinates.add(x + ", " + y);
            }
          }

          return coordinates;
        })
        .flatMap(List::stream)
        .collect(Collectors.groupingBy(c -> c, Collectors.counting()))
        .entrySet()
        .stream()
        .filter(e -> e.getValue() > 1)
        .count();
  }
}
