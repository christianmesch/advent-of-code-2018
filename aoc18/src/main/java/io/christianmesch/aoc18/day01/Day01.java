package io.christianmesch.aoc18.day01;

import io.christianmesch.aoc18.utils.InputUtils;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day01 {

  private static final String INPUT_FILENAME = "/day1.txt";

  public static void main(String... args) {
    Day01 day = new Day01();
    try {
      day.puzzle1();
      day.puzzle2();
    } catch (URISyntaxException | IOException e) {
      e.printStackTrace();
    }
  }

  private void puzzle1() throws URISyntaxException, IOException {
    int sum = InputUtils.read(INPUT_FILENAME).stream().mapToInt(Integer::valueOf).sum();

    System.out.println(sum);
  }

  private void puzzle2() throws IOException, URISyntaxException {
    List<Integer> input = InputUtils.read(INPUT_FILENAME).stream().map(Integer::valueOf).collect(Collectors.toList());
    List<Integer> foundFrequencies = new ArrayList<>();

    int lastFrequency = 0;
    foundFrequencies.add(0);
    while(true) {
      for (int change : input) {
        lastFrequency += change;

        if (foundFrequencies.contains(lastFrequency)) {
          System.out.println(lastFrequency);
          return;
        }

        foundFrequencies.add(lastFrequency);
      }
    }
  }
}