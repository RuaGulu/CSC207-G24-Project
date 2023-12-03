package data_access;

import entity.Trip;
import entity.TripFactory;
import use_case.trip.TripUserDataAccessInterface;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class FilerTripDataAccessObject implements TripUserDataAccessInterface {

    private final File csvFile;
    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<String, Trip> trips = new HashMap<>();
    private final TripFactory tripFactory;

    public FilerTripDataAccessObject(String csvPath, TripFactory tripFactory) throws IOException {
        this.tripFactory = tripFactory;
        csvFile = new File(csvPath);
        headers.put("tripId", 0);
        headers.put("tripName", 1);

        if (csvFile.length() == 0) {
            saveHeaders(); // This is a new method to save just the headers
        } else {
            load();
        }
    }

    // This new method saves only the headers to the file.
    private void saveHeaders() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile, false))) {
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();
        }
    }

    private void load() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
            String header = reader.readLine();
            assert header.equals("tripId,tripName");
            String row;
            while ((row = reader.readLine()) != null) {
                String[] col = row.split(",");
                String tripId = col[headers.get("tripId")];
                String tripName = col[headers.get("tripName")];
                Trip trip = tripFactory.create(tripId, tripName);
                trips.put(tripId, trip);
            }
        }
    }

    @Override
    public Optional<Trip> findById(String tripId) {
        return Optional.ofNullable(trips.get(tripId));
    }

    @Override
    public void save(Trip trip) {
        trips.put(trip.getTripId(), trip);
        saveToFile();
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(csvFile, false))) {
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();
            for (Trip trip : trips.values()) {
                String[] tripData = new String[headers.size()];
                tripData[headers.get("tripId")] = trip.getTripId();
                tripData[headers.get("tripName")] = trip.getTripName();
                writer.write(String.join(",", tripData));
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to save trips to CSV.", e);
        }
    }
}
