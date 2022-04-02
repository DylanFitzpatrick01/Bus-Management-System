import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main class which the user interacts with
 */


public class Main {
    //initialise ArrayLists for stops.txt
    static ArrayList<Integer> stopsStopID = new ArrayList<>();
    static ArrayList<Integer> stopCode = new ArrayList<>();
    static ArrayList<String> stopName = new ArrayList<>();
    static ArrayList<String> stopDesc = new ArrayList<>();
    static ArrayList<Double> stopLat = new ArrayList<>();
    static ArrayList<Double> stopLon = new ArrayList<>();
    static ArrayList<String> stopURL = new ArrayList<>();
    static ArrayList<Integer> zoneID = new ArrayList<>();
    static ArrayList<Integer> locationType = new ArrayList<>();
    static ArrayList<Integer> parentStation = new ArrayList<>();
    // initialise ArrayLists for stop_times.txt
    static ArrayList<Integer> tripId = new ArrayList<>();
    static ArrayList<String> arrivalTime = new ArrayList<>();
    static ArrayList<String> departureTime = new ArrayList<>();
    static ArrayList<Integer> stopTimesStopID = new ArrayList<>();
    static ArrayList<Integer> stopSequence = new ArrayList<>();
    static ArrayList<String> stopHeadsign = new ArrayList<>();
    static ArrayList<Integer> pickupType = new ArrayList<>();
    static ArrayList<Integer> dropOffType = new ArrayList<>();
    static ArrayList<Double> shapeDistTravelled = new ArrayList<>();
    // initialise ArrayLists for transfers.txt
    static ArrayList<Integer> fromStopID = new ArrayList<>();
    static ArrayList<Integer> toStopID = new ArrayList<>();
    static ArrayList<Integer> transferType = new ArrayList<>();
    static ArrayList<Integer> minTransferTime = new ArrayList<>();
    // initialise graph
    static double[][] graph;
    public static void main(String[] args) {
        // read in the input files
        ReadFile.readStops(stopsStopID, stopCode, stopName, stopDesc, stopLat,  stopLon, stopURL, zoneID, locationType,
                 parentStation);
        ReadFile.readStopTimes(tripId, arrivalTime, departureTime, stopTimesStopID, stopSequence, stopHeadsign, pickupType,
                dropOffType, shapeDistTravelled);
        ReadFile.readTransfers(fromStopID, toStopID, transferType, minTransferTime);
        FindShortestPath shortestPath = new FindShortestPath(graph);
        System.out.println(graph);
        // initialise Scanner to allow user input
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the Vancouver Bus Management System! \n");
    }
}
