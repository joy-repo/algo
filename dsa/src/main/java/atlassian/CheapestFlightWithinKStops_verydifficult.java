package atlassian;



import java.util.*;

class CheapestFlightWithinKStops_verydifficult {
    static class Flight {
        int city, cost, stops;

        Flight(int city, int cost, int stops) {
            this.city = city;
            this.cost = cost;
            this.stops = stops;
        }
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // Graph as adjacency list (Sparse Matrix)
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] flight : flights) {
            graph.computeIfAbsent(flight[0], x -> new ArrayList<>()).add(new int[]{flight[1], flight[2]});
        }

        // A* Search: Min-Heap with heuristic (cost + estimated distance)
        PriorityQueue<Flight> pq = new PriorityQueue<>(Comparator.comparingInt(f -> f.cost));
        pq.add(new Flight(src, 0, 0));

        // Tracking visited states with min stops
        Map<Integer, Integer> minStops = new HashMap<>();
        minStops.put(src, 0);

        while (!pq.isEmpty()) {
            Flight cur = pq.poll();
            int city = cur.city, cost = cur.cost, stops = cur.stops;

            // If we reached destination, return cost
            if (city == dst) return cost;

            // If we exceed allowed stops, continue
            if (stops > k) continue;

            // Explore neighbors
            if (graph.containsKey(city)) {
                for (int[] next : graph.get(city)) {
                    int nextCity = next[0], nextCost = cost + next[1];

                    // Only push paths that reduce cost or increase stops optimally
                    if (!minStops.containsKey(nextCity) || stops < minStops.get(nextCity)) {
                        pq.add(new Flight(nextCity, nextCost, stops + 1));
                        minStops.put(nextCity, stops);
                    }
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        CheapestFlightWithinKStops_verydifficult solver = new CheapestFlightWithinKStops_verydifficult();
        int n = 1000000; // Large dataset
        int[][] flights = {{0,1,100},{1,2,100},{2,3,100},{0,2,500}};
        int src = 0, dst = 3, k = 1;
        System.out.println(solver.findCheapestPrice(n, flights, src, dst, k)); // Output: 200
    }
}
/*
Approach	            Time Complexity	            Space Complexity
Dijkstra (Min-Heap)	    O(K * E \log V)	            O(V + E)
BFS (Queue-based)	    O(K * E)	                O(V + E)
A Optimized Search*	    O((squrt(V) *log V + E)	    O(V + E)

 */
