package io.christianmesch.day01;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day01 {
  public static void main(String... args) {
    Day01 day = new Day01();
    try {
      day.puzzle1();
      day.puzzle2();
    } catch (URISyntaxException | IOException e) {
      e.printStackTrace();
    }
  }

  private List<String> readInput() throws URISyntaxException, IOException {
    Path inputPath = Paths.get(Day01.class.getResource("/input.txt").toURI());
    return Files.readAllLines(inputPath);
  }

  private void puzzle1() throws URISyntaxException, IOException {
    int sum = readInput().stream().mapToInt(Integer::valueOf).sum();

    System.out.println(sum);
  }

  private void puzzle2() throws IOException, URISyntaxException {
    List<Integer> input = readInput().stream().map(Integer::valueOf).collect(Collectors.toList());
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