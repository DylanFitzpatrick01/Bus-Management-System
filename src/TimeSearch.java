import java.util.ArrayList;

public class TimeSearch {
    /**
     * format string as hh:mm:ss
     *
     * @param time: input string
     * @return formatted string
     */
    String makeTimeValid(String time) {
        // replace any blank spaces with a 0
        time = time.replaceAll(" ", "0");
        String[] splitTime = time.split(":");
        // append 0 to front of any lone integer value (0-9)
        if (splitTime[0].length() == 1)
            splitTime[0] = "0" + splitTime[0];
        if (splitTime[1].length() == 1)
            splitTime[1] = "0" + splitTime[1];
        if (splitTime[2].length() == 1)
            splitTime[2] = "0" + splitTime[2];
        time = splitTime[0] + ":" + splitTime[1] + ":" + splitTime[2];
        return time;
    }

    /**
     * If time is higher than 23:59:59 it must be removed
     *
     * @param time: input string
     * @return valid time
     */
    String removeInvalidTimes(String time) {
        String[] splitTime = time.split(":");
        if (Integer.parseInt(splitTime[0]) >= 24) {
            int validTime = 24 - Integer.parseInt(splitTime[0]);
            time = validTime + ":" + splitTime[1] + ":" + splitTime[2];
            time = makeTimeValid(time);
        }
        return time;
    }

    /**
     *  check if the time value entered is between 00:00:00
     * @param time: input string
     * @return true if time is valid, false otherwise
     */
    boolean checkIsTimeValid(String time) {
        String[] splitTime = time.split(":");
        if (Integer.parseInt(splitTime[0]) > 23 || Integer.parseInt(splitTime[1]) > 59 || Integer.parseInt(splitTime[2])
                > 59) {
            return false;
        }
        return true;
    }

    /**
     * return trip details for given arrival time
     *
     * @param time:              input string
     * @param tripId:            ArrayList containing the trip IDs
     * @param arrivalTime:       ArrayList containing the arrival times
     * @param departureTime:     ArrayList containing the departure times
     * @param stopId:            ArrayList containing the stop IDs
     * @param stopSequence:      ArrayList containing the stop sequences
     * @param stopHeadsign:      ArrayList containing the stop headsigns
     * @param pickupType:        ArrayList containing the pickup types
     * @param dropoffType:       ArrayList containing the dropoff types
     * @param shapeDistTraveled: ArrayList containing the shape dist travelled
     */
    void searchByArrivalTime(String time, ArrayList<Integer> tripId, ArrayList<String> arrivalTime, ArrayList<String>
            departureTime, ArrayList<Integer> stopId, ArrayList<Integer> stopSequence,
                             ArrayList<String> stopHeadsign, ArrayList<Integer> pickupType, ArrayList<Integer> dropoffType,
                             ArrayList<Double> shapeDistTraveled) {
        makeTimeValid(time);
        removeInvalidTimes(time);
        ArrayList<Integer>  index = new ArrayList<>();
        for(int i = 0; i < arrivalTime.size(); i++){
            if(time.equals(arrivalTime.get(i))){
                index.add(i);
            }
        }
        if (arrivalTime.contains(time)) {
            for(int i = 0; i < index.size(); i++) {

                // find the trip ID associated with the arrival time
                int currTripId = tripId.get(index.get(i));
                // index of first instance of the trip ID
                int indexOfCurrTripId = tripId.indexOf(currTripId);
                System.out.println("\nTrip ID: " + currTripId + "\n");
                // print results
                while (tripId.get(indexOfCurrTripId) == currTripId) {
                    System.out.println("Stop ID: " + stopId.get(indexOfCurrTripId));
                    System.out.println("Arrival time: " + arrivalTime.get(indexOfCurrTripId));
                    System.out.println("Departure time: " + departureTime.get(indexOfCurrTripId));
                    System.out.println("Stop Sequence: " + stopSequence.get(indexOfCurrTripId));
                    System.out.println("Stop Headsign: " + stopHeadsign.get(indexOfCurrTripId));
                    System.out.println("Pickup type: " + pickupType.get(indexOfCurrTripId));
                    System.out.println("Dropoff Type: " + dropoffType.get(indexOfCurrTripId));
                    System.out.println("Distance travelled: " + shapeDistTraveled.get(indexOfCurrTripId));
                    System.out.println();
                    indexOfCurrTripId++;
                }
            }
        } else System.out.println("No trips with this arrival time found. ");
    }
}

