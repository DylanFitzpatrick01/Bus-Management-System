import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;

public class StopSearch {
    /**
     * Method to move specified keywords from start of string to end of string for search functionality
     * @param string: input string
     * @return string with first word moved to last position
     * */
    String moveKeywords(String string) {
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
     * print stop details
     * @param searchStop: stop to search for
     * @param stopId
     * @param stopName
     * @param stopCode
     * @param stopDesc
     * @param stopLat
     * @param stopLon
     * @param stopURL
     * @param zoneID
     * @param locationType
     * @param parentStation
     * @param tst: ternary search tree
     */
    void printDetails(String searchStop, ArrayList<Integer> stopId, ArrayList<String> stopName, ArrayList<Integer> stopCode,
                     ArrayList<String> stopDesc, ArrayList<Double> stopLat, ArrayList<Double> stopLon, ArrayList<String> stopURL,
                      ArrayList<Integer> zoneID, ArrayList<Integer> locationType, ArrayList<Integer> parentStation, TST tst) {
        ArrayList<String> movedKeywords = new ArrayList<>();
        System.out.println();
        // insert all stops into ternary search tree
        for (String value : stopName) {
            tst.put(moveKeywords(value), 0);
        }
        // print stop details
        for (Object s : tst.keysWithPrefix(searchStop)) {
            String string = s.toString();
            movedKeywords.add(string);
            System.out.println(string);
            System.out.println("Stop ID: " + stopId.get(stopName.indexOf(string)));
            System.out.println("Stop code: " + stopCode.get(stopName.indexOf(string)));
            System.out.println("Description: " + stopDesc.get(stopName.indexOf(string)));
            System.out.println("Stop Latitude: " + stopLat.get(stopName.indexOf(string)));
            System.out.println("Stop Longitude: " + stopLon.get(stopName.indexOf(string)));
            System.out.println("Stop URL: " + stopURL.get(stopName.indexOf(string)));
            System.out.println("Zone ID: " + zoneID.get(stopName.indexOf(string)));
            System.out.println("Location Type : " + locationType.get(stopName.indexOf(string)));
            System.out.println("Parent Station: " + parentStation.get(stopName.indexOf(string)));
            System.out.println();
        }
    }
}
