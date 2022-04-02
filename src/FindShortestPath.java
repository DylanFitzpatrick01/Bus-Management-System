import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Class that finds the shortest edge between 2 bus stops
 */
public class FindShortestPath {

    final static int INFINITY = Integer.MAX_VALUE;
    static int V;

    FindShortestPath(double[][] graph) {
        graph = new double[12478][12478];
        for(int i = 0; i < 12478; i++){
            for(int j = 0; j < 12478; j++){
                graph[i][j] = INFINITY;
            }
        }
        ArrayList<Integer> tripId = new ArrayList<>();
        ArrayList<String> arrivalTime = new ArrayList<>();
        ArrayList<String> departureTime = new ArrayList<>();
        ArrayList<Integer> stopID = new ArrayList<>();
        ArrayList<Integer> stopSequence = new ArrayList<>();
        ArrayList<String> stopHeadsign = new ArrayList<>();
        ArrayList<Integer> pickupType = new ArrayList<>();
        ArrayList<Integer> dropOffType = new ArrayList<>();
        ArrayList<Double> shapeDistTravelled = new ArrayList<>();
        ArrayList<Integer> fromStopID = new ArrayList<>();
        ArrayList<Integer> toStopID = new ArrayList<>();
        ArrayList<Integer> transferType = new ArrayList<>();
        ArrayList<Integer> minTransferTime = new ArrayList<>();
        ReadFile.readStopTimes(tripId, arrivalTime, departureTime, stopID, stopSequence, stopHeadsign, pickupType,
                dropOffType, shapeDistTravelled);
        ReadFile.readTransfers(fromStopID, toStopID, transferType, minTransferTime);

        // add values from "stop_times.txt" to graph
        for (int i = 0; i < tripId.size(); i++) {
            for (int j = 0; j < tripId.size(); j++) {
                if (tripId.get(i).equals(tripId.get(j))) {
                    graph[stopID.get(i)][stopID.get(j)] = 1;
                }
            }
        }
        // add values from "transfers.txt" to graph
        for(int i = 0; i < fromStopID.size(); i++){
            if(transferType.get(i) == 0){
                graph[fromStopID.get(i)][toStopID.get(i)] = 2;
            }
            else if(transferType.get(i) == 2){
                graph[fromStopID.get(i)][toStopID.get(i)] = minTransferTime.get(i)/100;
            }
        }
    }




    /**
     * @param graph: adjacency matrix representing graph
     * @return distance matrix
     */
    double[][] floydWarshall(double[][] graph) {
        // initialise adjacency matrix
        double[][] dist = new double[V][V];
        int i, j, k;

        // input weight values into adjacency matrix
        for (i = 0; i < V; i++)
            for (j = 0; j < V; j++) {
                if (i == j)
                    dist[i][j] = 0;
                else dist[i][j] = graph[i][j];
            }

        // find the shortest path  for all i,j
        for (k = 0; k < V; k++) {
            for (i = 0; i < V; i++) {
                for (j = 0; j < V; j++) {
                    if (dist[i][k] + dist[k][j] < dist[i][j])
                        dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }
        return dist;
    }

}
