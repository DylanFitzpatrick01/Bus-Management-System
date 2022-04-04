import java.util.ArrayList;

public class TimeSearch {
    /**
     * format string as hh:mm:ss
     * @param time: input string
     * @return formatted string
     */
    String makeTimeValid(String time){
        // replace any blank spaces with a 0
        time =  time.replaceAll(" ", "0");
        String[] splitTime = time.split(":");
        // append 0 to front of any lone integer value
        if(splitTime[0].length() == 1)
            splitTime[0] = "0" + splitTime[0];
        if(splitTime[1].length() == 1)
            splitTime[1] = "0" + splitTime[1];
        if(splitTime[2].length() == 1)
            splitTime[2] = "0" + splitTime[2];
        time = splitTime[0] + ":" + splitTime[1] + ":" + splitTime[2];
        return time;
    }

    /**
     * If time is higher than 23:59:59 it must be removed
     * @param time: input string
     * @return valid time
     */
    String removeInvalidTimes(String time){
        String[] splitTime = time.split(":");
        if(Integer.parseInt(splitTime[0]) >= 24){
            int validTime = 24 - Integer.parseInt(splitTime[0]);
            time = validTime + ":" + splitTime[1] + ":" + splitTime[2];
            time = makeTimeValid(time);
        }
        return time;
    }

    /**
     * return trip details for given arrival time
     * @param time: input string
     * @param tripId
     * @param arrivalTime
     * @param departureTime
     * @param stopId
     * @param stopSequence
     * @param stopHeadsign
     * @param pickupType
     * @param dropoffType
     * @param shapeDistTraveled
     */
    void searchByArrivalTime(String time, ArrayList<Integer> tripId, ArrayList<String> arrivalTime, ArrayList<String>
                             departureTime, ArrayList<Integer> stopId, ArrayList<Integer> stopSequence,
                             ArrayList<String> stopHeadsign, ArrayList<Integer> pickupType, ArrayList<Integer>dropoffType,
                             ArrayList<Double> shapeDistTraveled){
        makeTimeValid(time);
        removeInvalidTimes(time);
        if(arrivalTime.contains(time)) {
            // get index of time value
            int index = arrivalTime.indexOf(time);
            // find the trip ID associated with the arrival time
            int currTripId = tripId.get(index);
            // index of first instance of the trip ID
            int indexOfCurrTripId = tripId.indexOf(currTripId);
            System.out.println("Trip ID: " + currTripId + "\n");
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
        } else System.out.println("No trips with this arrival time found. ");
    }
}

