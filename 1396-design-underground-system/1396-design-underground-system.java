import java.util.*;

class UndergroundSystem {

    // id -> [stationName, checkInTime]
    private Map<Integer, CheckInData> checkInMap;

    // "start#end" -> [totalTime, numberOfTrips]
    private Map<String, double[]> travelMap;

    public UndergroundSystem() {
        checkInMap = new HashMap<>();
        travelMap = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        checkInMap.put(id, new CheckInData(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        CheckInData data = checkInMap.get(id);

        String startStation = data.stationName;
        int startTime = data.time;

        int travelTime = t - startTime;

        String key = startStation + "#" + stationName;

        if (!travelMap.containsKey(key)) {
            travelMap.put(key, new double[]{0.0, 0.0});
        }

        double[] values = travelMap.get(key);

        values[0] += travelTime; // total time
        values[1]++;             // number of trips

        checkInMap.remove(id);
    }

    public double getAverageTime(String startStation, String endStation) {
        String key = startStation + "#" + endStation;

        double[] values = travelMap.get(key);

        return values[0] / values[1];
    }

    // Helper class
    class CheckInData {
        String stationName;
        int time;

        CheckInData(String stationName, int time) {
            this.stationName = stationName;
            this.time = time;
        }
    }
}