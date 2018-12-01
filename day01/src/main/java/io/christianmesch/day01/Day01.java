package io.christianmesch.day01;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Day01 {
  public static void main(String... args) {
    Day01 day = new Day01();
    try {
      day.puzzle1();
    } catch (URISyntaxException | IOException e) {
      e.printStackTrace();
    }
  }

  private List<String> readInput(String filename) throws URISyntaxException, IOException {
    Path inputPath = Paths.get(Day01.class.getResource(filename).toURI());
    return Files.readAllLines(inputPath);
  }

  private void puzzle1() throws URISyntaxException, IOException {
    int sum = readInput("/input.txt").stream().mapToInt(Integer::valueOf).sum();

    System.out.println(sum);
  }
}