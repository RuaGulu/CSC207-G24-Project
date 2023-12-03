package entity;

public class AirQuality {

    private String location;

    private int co;
    private int no2;
    private int o3;
    private int so2;
    private int pm2_5;
    private int pm10;

    public AirQuality(String location, int co, int no2, int o3, int so2, int pm2_5, int pm10) {
        this.location = location;
        this.co = co;
        this.no2 = no2;
        this.o3 = o3;
        this.so2 = so2;
        this.pm2_5 = pm2_5;
        this.pm10 = pm10;
    }

    public static AirQualityBuilder builder() {return new AirQualityBuilder(); }

    public static class AirQualityBuilder {
        private String location;
        private int co;
        private int no2;
        private int o3;
        private int so2;
        private int pm2_5;
        private int pm10;

        AirQualityBuilder() {
        }

        public AirQualityBuilder location(String locaiton) {
            this.location = location;
            return this;
        }
        public AirQualityBuilder co(int co) {
            this.co = co;
            return this;
        }
        public AirQualityBuilder no2(int no2) {
            this.no2 = no2;
            return this;
        }
        public AirQualityBuilder o3(int o3) {
            this.o3 = o3;
            return this;
        }
        public AirQualityBuilder so2(int so2) {
            this.so2 = so2;
            return this;
        }
        public AirQualityBuilder pm2_5(int pm2_5) {
            this.pm2_5 = pm2_5;
            return this;
        }
        public AirQualityBuilder pm10(int pm10) {
            this.pm10 = pm10;
            return this;
        }

        public AirQuality build() {return new AirQuality(location, co, no2, o3, so2, pm2_5, pm10);}
    }

    @Override
    public String toString() {
        return "Air Quality{" +
                "location= " + location + '\'' +
                ", co=" + co + '\'' +
                ", no2=" + no2 + '\'' +
                ", o3=" + o3 + '\'' +
                ", so2=" + so2 + '\'' +
                ", pm2_5=" + pm2_5 + '\'' +
                "pm10=" + pm10 + '\'' +
                '}';
    }

    public String getLocation() {return location;}
    public int getCo() {return co;}
    public int getNo2() {return no2;}
    public int getO3() {return o3;}
    public int getSo2() {return so2;}
    public int getPm2_5() {return pm2_5;}
    public int getPm10() {return pm10;}
}