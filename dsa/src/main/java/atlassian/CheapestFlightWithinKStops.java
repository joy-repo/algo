package atlassian;


import java.util.*;

//Dijkstra (PriorityQueue)
//Time complexity -> O(K * E * log V)
// Space complexity : O(V+E)

class CheapestFlightWithinKStops {
    static class Flight {
        int city, cost, stops;

        Flight(int city, int cost, int stops) {
            this.city = city;
            this.cost = cost;
            this.stops = stops;
        }
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // Adjacency list (Graph representation)
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] flight : flights) {
            graph.computeIfAbsent(flight[0], x -> new ArrayList<>()).add(new int[]{flight[1], flight[2]});
        }

        // Min-Heap based on cost (Dijkstra-style)
        PriorityQueue<Flight> pq = new PriorityQueue<>(Comparator.comparingInt(f -> f.cost));
        pq.add(new Flight(src, 0, 0));

        // Track the minimum cost for each node at each stop level
        Map<Integer, Integer> minStops = new HashMap<>();
        minStops.put(src, 0);

        while (!pq.isEmpty()) {
            Flight cur = pq.poll();
            int city = cur.city, cost = cur.cost, stops = cur.stops;

            // If we reached the destination, return the cost
            if (city == dst) return cost;

            // If we exceed the stop limit, continue
            if (stops > k) continue;

            // Explore neighbors
            if (graph.containsKey(city)) {
                for (int[] next : graph.get(city)) {
                    int nextCity = next[0], nextCost = cost + next[1];

                    // Only consider paths with fewer stops or a cheaper cost
                    if (!minStops.containsKey(nextCity) || stops < minStops.get(nextCity)) {
                        pq.add(new Flight(nextCity, nextCost, stops + 1));
                        minStops.put(nextCity, stops);
                    }
                }
            }
        }
        return -1; // No possible route
    }

    public static void main(String[] args) {
        CheapestFlightWithinKStops solver = new CheapestFlightWithinKStops();
        int n = 4;
        int[][] flights = {
                {0,1,100},
                {1,2,100},
                {2,3,100},
                {0,2,500}
        };
        int src = 0, dst = 3, k = 1;
        System.out.println(solver.findCheapestPrice(n, flights, src, dst, k)); // Output: 200
    }
}