import java.io.*;
import java.util.ArrayList;

/**
 * Class to read in the input files given
 * @author DylanFitzpatrick01
 */
public class ReadFile {

    /**
     * read the "readStops.txt" file
     * @param stopID: ArrayList containing the stop IDs
     * @param stopCode: ArrayList containing the stop codes
     * @param stopName: ArrayList containing the stop names
     * @param stopDesc: ArrayList containing the stop descriptions
     * @param stopLat: ArrayList containing the stop latitudes
     * @param stopLon: ArrayList containing the stop longitudes
     * @param stopURL: ArrayList containing the stop URLs
     * @param zoneID: ArrayList containing the zone IDs
     * @param locationType: ArrayList containing the location types
     * @param parentStation: ArrayList containing the parent stations
     */
    void readStops(ArrayList<Integer> stopID, ArrayList<Integer> stopCode, ArrayList<String> stopName,
                            ArrayList<String> stopDesc, ArrayList<Double> stopLat, ArrayList<Double> stopLon,
                            ArrayList<String> stopURL, ArrayList<Integer> zoneID, ArrayList<Integer> locationType,
                            ArrayList<Integer> parentStation) {
        try {
            BufferedReader br = new BufferedReader((new FileReader("stops.txt")));
            StopSearch ss = new StopSearch();
            // skip line of headings
            br.readLine();
            String line;
            String[] splitLine;
            // add values to their respective ArrayLists
            while ((line = br.readLine()) != null) {
                // split array by comma
                splitLine = line.split(",");
                stopID.add(checkIntIsNotBlank(splitLine[0]));
                stopCode.add(checkIntIsNotBlank(splitLine[1]));
                stopName.add(ss.moveKeywords(splitLine[2]));
                stopDesc.add(splitLine[3]);
                stopLat.add(checkDoubleIsNotBlank(splitLine[4]));
                stopLon.add(checkDoubleIsNotBlank(splitLine[5]));
                stopURL.add(splitLine[6]);
                zoneID.add(checkIntIsNotBlank(splitLine[7]));
                locationType.add(checkIntIsNotBlank(splitLine[8]));
                // if there is no value for parent station, add a space character (to represent no value)
                if(splitLine.length == 9){
                    parentStation.add(0);
                }
                else parentStation.add((checkIntIsNotBlank(splitLine[9])));
            }
        } catch (FileNotFoundException e){
            System.err.println("\"stops.txt\" not found. ");
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }



    /**
     * read in the "stop_times.txt" file
     * @param tripId: ArrayList containing the trip IDs
     * @param arrivalTime: ArrayList containing the arrival times
     * @param departureTime: ArrayList containing the departure times
     * @param stopID: ArrayList containing the stop IDs
     * @param stopSequence: ArrayList containing the stop sequences
     * @param stopHeadsign: ArrayList containing the stop headsigns
     * @param pickupType: ArrayList containing the pickup types
     * @param dropOffType: ArrayList containing the dropoff types
     * @param shapeDistTravelled: ArrayList containing the shape dist travelled
     */
    void readStopTimes(ArrayList<Integer> tripId, ArrayList<String> arrivalTime, ArrayList<String> departureTime,
                       ArrayList<Integer> stopID, ArrayList<Integer> stopSequence, ArrayList<String> stopHeadsign,
                       ArrayList<Integer> pickupType, ArrayList<Integer> dropOffType, ArrayList<Double> shapeDistTravelled) {
        try{
            BufferedReader br = new BufferedReader(new FileReader("stop_times.txt"));
            TimeSearch ts = new TimeSearch();
            String line;
            String[] splitString;
            // skip header line
            br.readLine();
            // insert values into their respective array lists
            while ((line = br.readLine()) != null){
                // split array by commas
                splitString = line.split(",");
                tripId.add(checkIntIsNotBlank(splitString[0]));
                arrivalTime.add(ts.removeInvalidTimes(ts.makeTimeValid(splitString[1])));
                departureTime.add(ts.removeInvalidTimes(ts.makeTimeValid(splitString[2])));
                stopID.add(checkIntIsNotBlank(splitString[3]));
                stopSequence.add(checkIntIsNotBlank(splitString[4]));
                stopHeadsign.add(splitString[5]);
                pickupType.add(checkIntIsNotBlank(splitString[6]));
                dropOffType.add(checkIntIsNotBlank(splitString[7]));
                if(splitString.length == 8){
                    shapeDistTravelled.add(0.0);
                }
                else shapeDistTravelled.add(checkDoubleIsNotBlank(splitString[8]));
            }
        } catch(FileNotFoundException e){
            System.err.println("\"stop_times.txt\" not found. ");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * read in the "transfers.txt" file
     * @param fromStopID: ArrayList containing the source stop IDs
     * @param toStopID: ArrayList containing the destination stop IDs
     * @param transferType: ArrayList containing the transfer types
     * @param minTransferTime: ArrayList containing the minimum transfer times
     */
    void readTransfers(ArrayList<Integer> fromStopID, ArrayList<Integer> toStopID, ArrayList<Integer> transferType,
                                ArrayList<Integer> minTransferTime) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("transfers.txt"));
            // skip the header line of the file
            br.readLine();
            String line;
            String[] splitLine;
            // insert values into their respective ArrayLists
            while((line = br.readLine()) != null){
                // split string by commas
                splitLine = line.split(",");
                fromStopID.add(checkIntIsNotBlank(splitLine[0]));
                toStopID.add(checkIntIsNotBlank(splitLine[1]));
                transferType.add(checkIntIsNotBlank(splitLine[2]));
                if(splitLine.length == 3){
                    minTransferTime.add(0);
                }
                else minTransferTime.add(checkIntIsNotBlank(splitLine[3]));
            }
        } catch (FileNotFoundException e) {
            System.err.println("\"transfers.txt\" not found. ");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * avoid null value returning error for int
     * @param string: input string
     * @return 0 is string is null, parsed int otherwise
     */
    int checkIntIsNotBlank(String string){
        if (string != null && !string.equals(" ")) {
            return Integer.parseInt(string);
        }
        return 0;
    }

    /**
     *  avoid null value returning error for double
     * @param string: input string
     * @return 0.0 if string is null, parsed double otherwise
     */
    double checkDoubleIsNotBlank(String string){
        if (string != null && !string.equals(" ")) {
            return Double.parseDouble(string);
        }
        return 0;
    }
}
