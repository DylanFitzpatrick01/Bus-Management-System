/**
 * Class that finds shortest edge between 2 bus stops
 */
public class FindShortestPath {
    /**
     *
     * @param fileName: name of file
     * @param transferType: transfer type (if from transfers.txt)
     * @param minTransferTime: minimum transfer time (if from transfers.txt)
     * @return cost of edge between bus stops
     */
     double findCost(String fileName, int transferType, int minTransferTime){
        double cost = 0;
        switch(fileName){
            case "stop_times.txt":
                cost = 1;
                break;
            case "transfers.txt":
                if(transferType == 0)
                    cost = 2;
                else if (transferType == 2)
                    cost = (double) minTransferTime/1000;
                else cost = -1;
                break;
            default:
                cost = -1;
        }
        return cost;
    }
}
