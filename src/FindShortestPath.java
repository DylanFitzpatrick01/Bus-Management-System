import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

/**
 * Class that finds the shortest edge between 2 bus stops
 */
public class FindShortestPath {

    final int INFINITY = Integer.MAX_VALUE;
    final int V = 12478;

    // construct our graph
    FindShortestPath(EdgeWeightedDigraph ewd, ArrayList<Integer> stopTimes, ArrayList<Integer> tripID, ArrayList<Integer>transfersFrom,
                            ArrayList<Integer> transfersTo, ArrayList<Integer> transferType, ArrayList<Integer> minTransferTime){
        DirectedEdge df;
        // if edge is from "stop_times.txt" add edge with weight 1
        for (int i = 0; i < stopTimes.size()-1; i++){
                if(Objects.equals(tripID.get(i), tripID.get(i+1))){
                    df = new DirectedEdge(stopTimes.get(i), stopTimes.get(i+1), 1);
                    ewd.addEdge(df);
            }
        }
        // if edge is from "transfers.txt"
        for(int i = 0; i < transfersFrom.size(); i++){
            // if transfer type is 0, add edge with weight 2
            if(transferType.get(i) == 0){
                df = new DirectedEdge(transfersFrom.get(i), transfersTo.get(i), 2);
                ewd.addEdge(df);
            }
            // if transfer time is 2, add edge with weight minimum transfer time / 2
            if(transferType.get(i) == 2){
                df = new DirectedEdge(transfersFrom.get(i), transfersTo.get(i), (double )minTransferTime.get(i)/100);
                ewd.addEdge(df);
            }
        }
    }

    /**
     * show the user the path from one vertex to another as well as the associated cost for each edge
     * @param ewd: graph
     * @param source: source vertex
     * @param destination: destination vertex
     */
    void listOfStops(EdgeWeightedDigraph ewd, int source, int destination) {
        DijkstraSP dijkstra = new DijkstraSP(ewd, source);
        System.out.println(dijkstra.pathTo(destination));
    }
}
