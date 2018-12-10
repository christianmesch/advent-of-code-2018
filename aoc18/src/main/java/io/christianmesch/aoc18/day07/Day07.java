package io.christianmesch.aoc18.day07;

import io.christianmesch.aoc18.utils.InputUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Day07 {
  private static final String INPUT_FILENAME = "/day7.txt";

  public static void main(String... args) {
    Day07 day = new Day07();
    try {
      System.out.println(day.part1());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private String part1() throws Exception {
    Map<String, BeforeAfter> nodes = getNodes();

    List<String> first = nodes.entrySet().stream()
        .filter(e -> e.getValue().before.isEmpty())
        .map(Entry::getKey)
        .collect(Collectors.toList());

    List<String> result = new ArrayList<>();
    PriorityQueue<String> possibleWays = new PriorityQueue<>(first);
    while (!possibleWays.isEmpty()) {
      String current = possibleWays.poll();
      BeforeAfter currentBA = nodes.get(current);

      if (result.containsAll(currentBA.before) && !result.contains(current)) {
        result.add(current);
        possibleWays.addAll(currentBA.after);
      }
    }


    return result.stream().reduce("", String::concat);
  }

  private Map<String, BeforeAfter> getNodes() throws Exception {
    Map<String, BeforeAfter> nodes = new HashMap<>();
    InputUtils.read(INPUT_FILENAME)
        .forEach(s -> {
          String aKey = s.substring(5, 6);
          String bKey = s.substring(36, 37);

          BeforeAfter a = nodes.getOrDefault(aKey, new BeforeAfter());
          BeforeAfter b = nodes.getOrDefault(bKey, new BeforeAfter());

          a.after.add(bKey);
          b.before.add(aKey);

          nodes.put(aKey, a);
          nodes.put(bKey, b);
        });

    return nodes;
  }

  private class BeforeAfter {
    public List<String> before = new ArrayList<>();
    public List<String> after = new ArrayList<>();
  }
}
