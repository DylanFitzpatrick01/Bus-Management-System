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
    // initialise ArrayLists for transfers.txt
    static ArrayList<Integer> fromStopID = new ArrayList<>();
    static ArrayList<Integer> toStopID = new ArrayList<>();
    static ArrayList<Integer> transferType = new ArrayList<>();
    static ArrayList<Integer> minTransferTime = new ArrayList<>();
    // initialise graph
    static EdgeWeightedDigraph graph;

    public static void main(String[] args) {
        boolean quit = false;
        int V;
        EdgeWeightedDigraph ewd = new EdgeWeightedDigraph(12479);
        ReadFile rf = new ReadFile();
        rf.readStops(stopsStopID, stopCode, stopName, stopDesc, stopLat, stopLon, stopURL, zoneID, locationType,
                parentStation);
        rf.readStopTimes(tripId, arrivalTime, departureTime, stopTimesStopID, stopSequence);
        rf.readTransfers(fromStopID, toStopID, transferType, minTransferTime);
        // initialise Scanner to allow user input
        Scanner sc = new Scanner(System.in);
        System.out.print("Welcome to the Vancouver Bus Management System! \n");
        while (!quit) {
            System.out.print("What would you like to do? \n");
            System.out.print("1) Find route between 2 stops. \n");
            System.out.print("2) Search by stop name. \n");
            System.out.print("3) Search by arrival time. \n");
            System.out.print("4) Exit. \n");
            System.out.print("Please enter number of preferred option (e.g. '1' for Find route between 2 stops): ");
            String status = sc.nextLine();
            if (status.equalsIgnoreCase("1")) {
                System.out.println("\nFind route between 2 stops: ");
                System.out.print("Enter starting stop (using the stop ID): ");
                int source = sc.nextInt();
                System.out.print("Enter destination stop (using the stop ID): ");
                int destination = sc.nextInt();
                FindShortestPath fsp = new FindShortestPath(ewd, stopTimesStopID, tripId, fromStopID, toStopID, transferType, minTransferTime);
                System.out.println("The path from stop " + source + " to stop " + destination + " is: ");
                fsp.listOfStops(ewd, source, destination);

            } else if (status.equalsIgnoreCase("2")) {
                System.out.println("\nSearch by stop name: ");
                System.out.println("Enter stop name :");
                String searchString = sc.nextLine();
            } else if (status.equalsIgnoreCase("4")) {
                quit = true;
                sc.close();
            } else {
                System.out.print("Invalid: please type 'Enter' or 'Exit': \n");
                sc.next();
            }
        }
    }
}
