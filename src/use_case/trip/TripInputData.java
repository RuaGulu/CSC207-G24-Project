package use_case.trip;

// TODO
public class TripInputData {
    final private String tripName;
    final private String password;
    final private String repeatPassword;

    public TripInputData(String tripName, String password, String repeatPassword) {
        this.tripName = tripName;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    String getTripName() {
        return tripName;
    }

    String getPassword() {
        return password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }
}
