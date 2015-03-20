package xyz.johansson.shortestpath;

/**
 * Contains a static method that finds the closest path to all other nodes in a
 * graph using Dijkstra's algorithm.
 *
 * @author Tobias Johansson
 */
public class Dijkstra {

    /**
     * Representing infinity.
     */
    public static final int X = Integer.MAX_VALUE;

    /**
     * Finds the closest path between the start node and all other nodes.
     *
     * @param w an adjacency matrix
     * @param start the start node
     * @return the closest path between the start node and all other nodes
     */
    public static int[] distanceAll(int[][] w, int start) {
        int d[] = new int[w.length];
        int p[] = new int[w.length];
        boolean[] taken = new boolean[w.length];

        for (int i = 0; i < w.length; i++) {
            d[i] = X;
            taken[i] = false;
        }
        d[start] = 0;

        for (int i = 0; i < d.length; i++) {
            int u = next(d, taken);
            taken[u] = true;
            for (int v = 0; v < w[u].length; v++) {
                if (w[u][v] < X && d[v] > d[u] + w[u][v]) {
                    d[v] = d[u] + w[u][v];
                    p[v] = (u);
                }
            }
        }

        return d;
    }

    /**
     * Returns the first index for a node that have the lowest value and is not
     * taken.
     */
    private static int next(int[] d, boolean[] taken) {
        int dMin = X;
        int minIndex = 0;
        for (int i = 0; i < d.length; i++) {
            if (d[i] < dMin && !taken[i]) {
                dMin = d[i];
                minIndex = i;
            }
        }
        return minIndex;
    }
}
