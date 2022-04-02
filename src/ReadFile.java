import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class to read in the input files given
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
    static void readStops(ArrayList<Integer> stopID, ArrayList<Integer> stopCode, ArrayList<String> stopName,
                            ArrayList<String> stopDesc, ArrayList<Double> stopLat, ArrayList<Double> stopLon,
                            ArrayList<String> stopURL, ArrayList<Integer> zoneID, ArrayList<Integer> locationType,
                            ArrayList<Integer> parentStation) {
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
                stopID.add(checkIntIsNotBlank(splitLine[0]));
                stopCode.add(checkIntIsNotBlank(splitLine[1]));
                stopName.add(splitLine[2]);
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
    static void readStopTimes(ArrayList<Integer> tripId, ArrayList<String> arrivalTime, ArrayList<String> departureTime,
                              ArrayList<Integer> stopID, ArrayList<Integer> stopSequence, ArrayList<String> stopHeadsign,
                              ArrayList<Integer> pickupType, ArrayList<Integer> dropOffType, ArrayList<Double> shapeDistTravelled) {
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
                tripId.add(checkIntIsNotBlank(splitString[0]));
                arrivalTime.add(splitString[1]);
                departureTime.add(splitString[2]);
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
     * @param fromStopID
     * @param toStopID
     * @param transferType
     * @param minTransferTime
     */
    static void readTransfers(ArrayList<Integer> fromStopID, ArrayList<Integer> toStopID, ArrayList<Integer> transferType,
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
    static int checkIntIsNotBlank(String string){
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
    static double checkDoubleIsNotBlank(String string){
        if (string != null && !string.equals(" ")) {
            return Double.parseDouble(string);
        }
        return 0;
    }

    static String convertTime(String string){
           if(string.charAt(0) == ' '){
               string = "0" + string.substring(1);
           }
           return string;
    }
}
