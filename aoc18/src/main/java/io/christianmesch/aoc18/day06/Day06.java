package io.christianmesch.aoc18.day06;

import io.christianmesch.aoc18.utils.InputUtils;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Day06 {
  private static final String INPUT_FILENAME = "/day6.txt";

  public static void main(String... args) {
    Day06 day = new Day06();
    try {
      System.out.println(day.part1());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private long part1() throws Exception {
    List<Coordinate> coordinates = InputUtils.read(INPUT_FILENAME).stream()
        .map(s -> s.split(", "))
        .map(s -> new Coordinate(Integer.valueOf(s[0]), Integer.valueOf(s[1])))
        .collect(Collectors.toList());

    for (int i = 0; i < coordinates.size(); i++) {
      coordinates.get(i).id = i;
    }

    int xMin = coordinates.get(0).x;
    int xMax = xMin;
    int yMin = coordinates.get(0).y;
    int yMax = yMin;

    for(Coordinate c : coordinates) {
      xMin = Integer.min(xMin, c.x);
      xMax = Integer.max(xMax, c.x);
      yMin = Integer.min(yMin, c.y);
      yMax = Integer.max(yMax, c.y);
    }

    List<Coordinate> allCoordinates = new ArrayList<>();
    for (int x = xMin; x <= xMax; x++) {
      for (int y = yMin; y < yMax; y++) {
        allCoordinates.add(new Coordinate(x, y));
      }
    }

    for (Coordinate c1 : coordinates) {
      for (Coordinate c2 : allCoordinates) {
        if (c2.id == -2 || c2.distance > distance(c1, c2)) {
          c2.id = c1.id;
          c2.distance = distance(c1, c2);
        } else if (c2.id != c1.id && c2.distance == distance(c1, c2)) {
          c2.id = -1;
          c2.distance = distance(c1, c2);
        }
      }
    }

    final int xMinF = xMin;
    final int xMaxF = xMax;
    final int yMinF = yMin;
    final int yMaxF = yMax;

    List<Integer> infiniteIds = allCoordinates.stream()
        .filter(c -> c.id != -1 && ((c.x == xMinF || c.x == xMaxF) || (c.y == yMinF || c.y == yMaxF)))
        .map(c -> c.id)
        .distinct()
        .collect(Collectors.toList());

    return allCoordinates.stream()
        .filter(c -> !infiniteIds.contains(c.id))
        .map(c -> c.id)
        .collect(Collectors.groupingBy(id -> id, Collectors.counting()))
        .values()
        .stream()
        .max(Comparator.naturalOrder())
        .orElse(-1L);
  }

  private int distance(Coordinate c1, Coordinate c2) {
    return Math.abs(c1.x - c2.x) + Math.abs(c1.y - c2.y);
  }

  private class Coordinate {
    public int id = -2;
    public int distance = 0;
    public int x;
    public int y;

    public Coordinate(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
}
