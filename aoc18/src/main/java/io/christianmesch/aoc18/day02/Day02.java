package io.christianmesch.aoc18.day02;

import io.christianmesch.aoc18.utils.InputUtils;
import java.util.HashMap;
import java.util.Map;

public class Day02 {

  private static final String INPUT_FILENAME = "/day2.txt";

  public static void main(String... args) {
    Day02 day = new Day02();
    try {
      System.out.println(day.part1());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private int part1() throws Exception {
    int twice = 0;
    int thrice = 0;

    for (String line : InputUtils.read(INPUT_FILENAME)) {
      Map<String, Integer> frequencies = new HashMap<>();
      for (String c : line.split("")) {
        frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
      }

      if (frequencies.values().contains(3)) {
        thrice++;
      }

      if (frequencies.values().contains(2)) {
        twice++;
      }
    }

    return twice * thrice;
  }
}
