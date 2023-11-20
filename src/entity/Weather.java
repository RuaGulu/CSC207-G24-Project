package entity;

public class Weather {

    // Refer to the API documentation for the meaning of these fields.
    private String location;
    private String time;
    private int tempC;
    private int tempF;
    private String condition;

    public Weather(String location, String time, int tempC, int tempF, String condition) {
        this.location = location; // City Name
        this.time = time;
        this.tempC = tempC;
        this.tempF = tempF;
        this.condition = condition;
    }

    public static WeatherBuilder builder() {
        return new WeatherBuilder();
    }

    public static class WeatherBuilder {
        private String location;
        private String time;
        private int tempC;
        private int tempF;
        private String condition;

        WeatherBuilder() {
        }

        public WeatherBuilder location(String location) {
            this.location = location;
            return this;
        }

        public WeatherBuilder time(String time) {
            this.time = time;
            return this;
        }

        public WeatherBuilder tempC(int tempC) {
            this.tempC = tempC;
            return this;
        }

        public WeatherBuilder tempF(int tempF) {
            this.tempF = tempF;
            return this;
        }

        public WeatherBuilder condition(String condition) {
            this.condition = condition;
            return this;
        }

        public Weather build() {
            return new Weather(location, time, tempC, tempF, condition);
        }
    }

    @Override
    public String toString() {
        return "Weather{" +
                "location='" + location + '\'' +
                ", time='" + time + '\'' +
                ", tempC=" + tempC + '\'' +
                ", tempF=" + tempF + '\'' +
                " condition=" + condition + '\'' +
                '}';
    }

    public String getLocation() {
        return location;
    }

    public String getTime() {
        return time;
    }

    public int getTempC() {return tempC;}

    public int getTempF() {return tempF;}

    public String getCondition() {return condition;}

}
