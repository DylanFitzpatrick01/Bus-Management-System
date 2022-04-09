import java.util.ArrayList;
import java.util.InputMismatchException;
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
    static EdgeWeightedDigraph graph;

    public static void main(String[] args) {
        System.out.println("Welcome to the Vancouver Bus Management System!\n");
        System.out.println("Loading...");
        boolean quit = false;
        boolean valid = false;
        ReadFile rf = new ReadFile();
        rf.readStops(stopsStopID, stopCode, stopName, stopDesc, stopLat, stopLon, stopURL, zoneID, locationType,
                parentStation);
        rf.readStopTimes(tripId, arrivalTime, departureTime, stopTimesStopID, stopSequence, stopHeadsign, pickupType,
                dropOffType, shapeDistTravelled);
        rf.readTransfers(fromStopID, toStopID, transferType, minTransferTime);
        // initialise Scanner to allow user input
        Scanner sc = new Scanner(System.in);
        while (!quit) {
            System.out.print("\nWhat would you like to do? \n");
            System.out.print("1) Find route between 2 stops. \n");
            System.out.print("2) Search by stop name. \n");
            System.out.print("3) Search by arrival time. \n");
            System.out.print("4) Exit. \n");
            System.out.print("Please enter number of preferred option (e.g. '1' for Find route between 2 stops): ");
            String status = sc.nextLine();
            // check if user has entered a single digit value
            if (!Character.isDigit(status.charAt(0)) || status.length() > 1) {
                System.err.println("Please enter a number between 1 and 4\n");
            }
            // make sure input is between 1 and 4
            else if (Integer.parseInt(status) >= 1 && Integer.parseInt(status) <= 4) {
                // the user wants to find the route between 2 stops
                if (status.equalsIgnoreCase("1")) {
                    int source = 0;
                    int destination = 0;
                    // initialise edge weighted digraph
                    EdgeWeightedDigraph ewd = new EdgeWeightedDigraph(12479);
                    System.out.println("\nFind route between 2 stops ");
                    // user inputs source vertex
                    System.out.print("Enter starting stop (using the stop ID): ");
                    while (!valid) {
                        try {
                            source = sc.nextInt();
                            valid = true;
                            // check if within range of vertices
                            if (source < 0 || source > 12478) {
                                System.err.print("Value must be between 0 and 12478, please enter a valid value: ");
                                valid = false;
                            }
                        } catch (InputMismatchException e) {
                            System.err.print("Please enter a numeric value: ");
                        }
                        sc.nextLine();
                    }
                    // user inputs destination vertex
                    System.out.print("Enter destination stop (using the stop ID): ");
                    valid = false;
                    // while input is invalid
                    while (!valid) {
                        try {
                            destination = sc.nextInt();
                            valid = true;
                            // check if within range of vertices
                            if (destination < 0 || destination > 12478) {
                                System.err.print("Value must be between 0 and 12478, please enter a valid value: ");
                                valid = false;
                            }
                        } catch (InputMismatchException e) {
                            System.err.print("Please enter a numeric value: ");
                        }
                        sc.nextLine();
                    }
                    valid = false;
                    // find the shortest path from source to destination
                    FindShortestPath fsp = new FindShortestPath(ewd, stopTimesStopID, tripId, fromStopID, toStopID, transferType, minTransferTime);
                    // print list of stops
                    fsp.listOfStops(ewd, source, destination);
                    System.out.print("Would you like to continue? (type 'N' if no, any other key to continue): ");
                    String runAgain = sc.nextLine();
                    // quit if 'n' is entered
                    if (runAgain.equalsIgnoreCase("n")) {
                        quit = true;
                    }
                }

                // the user wants to search by stop name
                else if (status.equalsIgnoreCase("2")) {
                    String searchStop = "";
                    System.out.println("\nSearch by stop name ");
                    System.out.print("Enter stop name (in CAPITALS): ");
                    StopSearch ss = new StopSearch();
                    TST tst = new TST();
                    // user inputs stop to search for
                    searchStop = sc.nextLine();
                    ss.printDetails(searchStop, stopsStopID, stopName, stopCode, stopDesc, stopLat, stopLon, stopURL,
                            zoneID, locationType, parentStation, tst);
                    System.out.print("\nWould you like to continue? (type 'N' if no, any other key to continue): ");
                    String runAgain = sc.nextLine();
                    // quit if 'n' is entered
                    if (runAgain.equalsIgnoreCase("n")) {
                        quit = true;
                    }
                }

                // the user wants to search by arrival time
                else if (status.equalsIgnoreCase("3")) {
                    String arrivalTimeSearch = "";
                    TimeSearch ts = new TimeSearch();
                    System.out.println("\nSearch by arrival time ");
                    // user inputs arrival time
                    System.out.print("Enter arrival time (hh:mm:ss): ");
                    while (!valid) {
                        arrivalTimeSearch = sc.nextLine();
                        // check if string is of the correct format
                        if (arrivalTimeSearch.length() != 8 || arrivalTimeSearch.charAt(2) != ':' ||
                                arrivalTimeSearch.charAt(5) != ':' || !Character.isDigit(arrivalTimeSearch.charAt(0))
                                || !Character.isDigit(arrivalTimeSearch.charAt(1)) || !Character.isDigit(arrivalTimeSearch.charAt(3)) ||
                                !Character.isDigit(arrivalTimeSearch.charAt(4)) || !Character.isDigit(arrivalTimeSearch.charAt(6))
                                || !Character.isDigit(arrivalTimeSearch.charAt(7))) {
                            System.err.print("Invalid. Please use format hh:mm:ss (hh,mm,ss are numeric): ");
                        }
                        //check if time entered is valid
                        else if (!ts.checkIsTimeValid(arrivalTimeSearch)) {
                            System.err.println("Please enter a time between 00:00:00 and 23:59:59: ");
                        }
                        // input must be of the format hh:mm:ss
                        else valid = true;
                    }
                    valid = false;
                    // make time entered valid
                    arrivalTimeSearch = ts.removeInvalidTimes(arrivalTimeSearch);
                    // make sure time is of format hh:mm:ss
                    arrivalTimeSearch = ts.makeTimeValid(arrivalTimeSearch);
                    // print results
                    ts.searchByArrivalTime(arrivalTimeSearch, tripId, arrivalTime, departureTime, stopTimesStopID, stopSequence, stopHeadsign, pickupType,
                            dropOffType, shapeDistTravelled);
                    System.out.print("Would you like to continue? ('type 'N' if no, any other key to continue): ");
                    String runAgain = sc.nextLine();
                    // quit if 'n' is entered
                    if (runAgain.equalsIgnoreCase("n")) {
                        quit = true;
                    }
                }

                // if the user wishes to quit
                else if (status.equalsIgnoreCase("4")) {
                    quit = true;
                }

                // if the user has entered a single digit number that isn't between 1 and 4
            } else {
                System.err.print("Please enter a number between 1 and 4 \n\n");
            }
        }
        System.out.println("\nThank you for using our service!");
        sc.close();
    }
}


