package xyz.johansson.shortestpath;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Contains static methods that can read a graph from a file to an adjency
 * matrix and get the nodes furthest appart and the shortest path between them.
 *
 * @author Tobias Johansson
 */
public class Graph {

    private static final boolean DEBUGGING = false; // print data inside methods

    /**
     * Reads a graph from a text file and return an adjacency matrix that
     * displays the weight of all links between the nodes.
     *
     * @param filename filename for a file containg a graph
     * @return an adjacency matrix that display the weight of all links between
     * the nodes
     */
    public static int[][] readFile(String filename) {
        int[][] w = null;
        try {
            BufferedReader in = new BufferedReader(new FileReader(filename));
            int nodes = Integer.parseInt(in.readLine());
            if (DEBUGGING) {
                System.out.println("Number of nodes: " + nodes);
            }
            w = new int[nodes][nodes];
            for (int i = 0; i < nodes; i++) {
                for (int j = 0; j < nodes; j++) {
                    w[i][j] = Dijkstra.X; // representing infinity
                }
            }
            int links = Integer.parseInt(in.readLine());
            if (DEBUGGING) {
                System.out.println("Number of links: " + links);
            }
            for (int i = 0; i < links; i++) {
                String[] splitted = in.readLine().split("\\s+");
                int a = Integer.parseInt(splitted[0]) - 1;
                int b = Integer.parseInt(splitted[1]) - 1;
                int d = Integer.parseInt(splitted[2]);
                w[a][b] = d;
                w[b][a] = d;
            }
            in.close(); // close the stream
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return w;
    }

    /**
     * Returns the nodes furhtest appart and the shortest path between them.
     *
     * @param w adjency matrix
     * @return for the nodes furhtest appart: an array containg first their
     * index numbers and then the shortest path between them
     */
    public static int[] furthestApart(int[][] w) {
        int[] fA = new int[3];
        for (int i = 0; i < w.length; i++) {
            int[] d = Dijkstra.distanceAll(w, i);
            int max = indexOfMax(d);
            if (fA[2] < d[max]) {
                fA[0] = i;
                fA[1] = max;
                fA[2] = d[max];
            }
            if (DEBUGGING) {
                System.out.println((i + 1) + " → " + (max + 1) + " = "
                        + (d[max]));
            }
        }
        return fA;
    }

    /**
     * Returns the index of the first maximum value in the input array.
     */
    private static int indexOfMax(int[] ints) {
        int max = 0;
        for (int i = 0; i < ints.length; i++) {
            if (ints[max] < ints[i]) {
                max = i;
            }
        }
        return max;
    }
}
