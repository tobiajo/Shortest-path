package xyz.johansson.shortestpath;

/**
 * Driver class for Graph that uses Dijkstra.
 *
 * @author Tobias Johansson
 */
public class Main {

    /**
     * Main method for Shortest path.
     *
     * @param args not used
     */
    public static void main(String[] args) {
        int[] fA = Graph.furthestApart(Graph.readFile("map2.txt"));

        System.out.println("House number number " + (fA[0] + 1)
                + " and house number " + (fA[1] + 1)
                + " is located furthest apart.\nThe closest path between them"
                + " is " + fA[2] + "*10² m.");
    }
}
