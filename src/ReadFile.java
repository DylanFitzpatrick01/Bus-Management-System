import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author DylanFitzpatrick01
 */
public class ReadFile {

    /**
     * read the "readStops.txt" file
     * @param stopID
     * @param stopCode
     * @param stopName
     * @param stopDesc
     * @param stopLat
     * @param stopLon
     * @param stopURL
     * @param zoneID
     * @param locationType
     * @param parentStation
     * @throws IOException
     */
    static void readStops(ArrayList<String> stopID, ArrayList<String> stopCode, ArrayList<String> stopName,
                            ArrayList<String> stopDesc, ArrayList<String> stopLat, ArrayList<String> stopLon,
                            ArrayList<String> stopURL, ArrayList<String> zoneID, ArrayList<String> locationType,
                            ArrayList<String> parentStation) throws IOException {
        try {
            BufferedReader br = new BufferedReader((new FileReader("stops.txt")));
            // skip line of headings
            br.readLine();
            String line;
            String[] splitLine;
            // add values to their respective ArrayLists
            while ((line = br.readLine()) != null) {
                // split array by comma
                splitLine = line.split(",");
                stopID.add(splitLine[0]);
                stopCode.add(splitLine[1]);
                stopName.add(moveFlagstop(splitLine[2]));
                stopDesc.add(splitLine[3]);
                stopLat.add(splitLine[4]);
                stopLon.add(splitLine[5]);
                stopURL.add(splitLine[6]);
                zoneID.add(splitLine[7]);
                locationType.add(splitLine[8]);
                // if there is no value for parent station, add a space character (to represent no value)
                if(splitLine.length == 9){
                    parentStation.add("");
                }
                else parentStation.add((splitLine[9]));
            }
        } catch (FileNotFoundException e){
            System.err.println("\"stops.txt\" not found. ");
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Method to move specified keywords from start of string to end of string for search functionality
     * @param string: input string
     * @return string with first word moved to last position
     * */
   static String moveFlagstop(String string) {
       // copy each word in string into an array splitString
       String[] splitString = string.split("\\s+");

       // save first value of splitString (we want to move this value)
       String wordToMove = splitString[0];
       // only alter the string if it contains one of the keywords
       if (wordToMove.equalsIgnoreCase("flagstop") || wordToMove.equalsIgnoreCase("wb") ||
               wordToMove.equalsIgnoreCase("nb") || wordToMove.equalsIgnoreCase("sb") ||
               wordToMove.equalsIgnoreCase("eb")){
           // move each element in splitString to the left
           for (int i = 0; i <= splitString.length - 2; i++) {
               splitString[i] = splitString[i + 1];
           }
       // insert word we want to move to the end of splitString
       splitString[splitString.length - 1] = wordToMove;
       // save splitString into original string variable
       string = Arrays.toString(splitString);
       string = string.replace(",", "").replace("[", "").replace("]", "");
       }
       return string;
   }

    /**
     * read in the "stop_times.txt" file
     * @param tripId
     * @param arrivalTime
     * @param departureTime
     * @param stopID
     * @param stopSequence
     * @param pickupType
     * @param dropOffType
     * @param shapeDistTravelled
     */
    static void readStopTimes(ArrayList<String> tripId, ArrayList<String> arrivalTime, ArrayList<String> departureTime,
                            ArrayList<String> stopID, ArrayList<String> stopSequence, ArrayList<String> pickupType,
                            ArrayList<String> dropOffType,ArrayList<String> shapeDistTravelled) {
        try{
            BufferedReader br = new BufferedReader(new FileReader("stop_times.txt"));
            String line;
            String[] splitString;
            // skip header line
            br.readLine();
            // insert values into their respective array lists
            while ((line = br.readLine()) != null){
                // split array by commas
                splitString = line.split(",");
                tripId.add(splitString[0]);
                arrivalTime.add(splitString[1]);
                departureTime.add(splitString[2]);
                stopID.add(splitString[3]);
                stopSequence.add(splitString[4]);
                pickupType.add(splitString[5]);
                dropOffType.add(splitString[6]);
                shapeDistTravelled.add(splitString[7]);
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
     * @param fromStopID
     * @param toStopID
     * @param transferType
     * @param minTransferTime
     */
    static void readTransfers(ArrayList<String> fromStopID, ArrayList<String> toStopID, ArrayList<String> transferType,
                                ArrayList<String> minTransferTime) {
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
                fromStopID.add(splitLine[0]);
                toStopID.add(splitLine[1]);
                transferType.add(splitLine[2]);
                if(splitLine.length == 3){
                    minTransferTime.add("");
                }
                else minTransferTime.add(splitLine[3]);
            }
        } catch (FileNotFoundException e) {
            System.err.println("\"transfers.txt\" not found. ");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * main method to test code above
     * @param args
     * @throws IOException
     */
    /*
    public static void main(String[] args) throws IOException {
        String testString = ("WB This is my test string");
        System.out.println(moveFlagstop(testString));
        ArrayList<String> stopID = new ArrayList<>();
        ArrayList<String> stopCode = new ArrayList<>();
        ArrayList<String> stopName = new ArrayList<>();
        ArrayList<String> stopDesc = new ArrayList<>();
        ArrayList<String> stopLat  = new ArrayList<>();
        ArrayList<String> stopLon = new ArrayList<>();
        ArrayList<String> zoneID = new ArrayList<>();
        ArrayList<String> stopURL = new ArrayList<>();
        ArrayList<String> locationType = new ArrayList<>();
        ArrayList<String> parentStation = new ArrayList<>();
        readStops(stopID, stopCode, stopName, stopDesc, stopLat, stopLon, zoneID, stopURL, locationType, parentStation);
        ArrayList<String> tripId = new ArrayList<>();
        ArrayList<String> arrivalTime = new ArrayList<>();
        ArrayList<String> departureTime = new ArrayList<>();
        ArrayList<String> stopSequence = new ArrayList<>();
        ArrayList<String> pickupType = new ArrayList<>();
        ArrayList<String> dropOffType = new ArrayList<>();
        ArrayList<String> shapeDistTravelled = new ArrayList<>();
        readStopTimes(tripId, arrivalTime, departureTime, stopSequence, stopID, pickupType, dropOffType, shapeDistTravelled);
        System.out.println(tripId);
        System.out.println(arrivalTime);
        System.out.println(departureTime);
        System.out.println(stopSequence);
        System.out.println(stopID);
        System.out.println(pickupType);
        System.out.println(dropOffType);
        System.out.println(shapeDistTravelled);
        ArrayList<String> fromStopID = new ArrayList<>();
        ArrayList<String> toStopID = new ArrayList<>();
        ArrayList<String> transferType = new ArrayList<>();
        ArrayList<String> minTransferTime = new ArrayList<>();
        readTransfers(fromStopID,toStopID,transferType,minTransferTime);
        System.out.println(fromStopID);
        System.out.println(toStopID);
        System.out.println(transferType);
        System.out.println(minTransferTime);
    }
     */
}
