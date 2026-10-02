package leetcode.mals.tasks.graphs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class CheapestFlightsWithinKStops {

    final int INF = Integer.MAX_VALUE / 2;

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {

        int[] cost = new int[n];
        Arrays.fill(cost, INF);
        cost[src] = 0;

        List<Flight>[] graph = new ArrayList[n];

        for (int[] fl : flights) {
            if (graph[fl[0]] == null) {
                graph[fl[0]] = new ArrayList<>();
            }
            graph[fl[0]].add(new Flight(fl[1], fl[2]));
        }

        Deque<City> queue = new ArrayDeque<>();

        queue.offer(new City(src, cost[src]));

        int i = 0;

        while (!queue.isEmpty() && i <= k) {

            int levelSize = queue.size();

            for (int j = 0; j < levelSize; j++) {

                City current = queue.poll();

                List<Flight> flightList = graph[current.value];

                if (flightList == null) {
                    continue;
                }

                for (Flight f : flightList) {
                    int newCost = current.cost + f.price;

                    if (newCost < cost[f.to]) {
                        cost[f.to] = newCost;
                        queue.offer(new City(f.to, newCost));
                    }
                }
            }
            i++;
        }


        return cost[dst] != INF ? cost[dst] : -1;
    }

    record City(int value, int cost) {}
    record Flight(int to, int price) {}

}
