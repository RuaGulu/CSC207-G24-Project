package entity;

import org.junit.Assert;
import org.junit.Test;

public class AirQualityTest {
    @Test
    public void testComponents(){
        String location = "Test Location";
        int co = 5;
        int no2 = 10;
        int o3 = 15;
        int so2 = 20;
        int pm2_5 = 25;
        int pm10 = 30;

        AirQuality airQuality = new AirQuality(location, co, no2, o3, so2, pm2_5, pm10);

        assert(location.equals(airQuality.getLocation()));
        assert(co == airQuality.getCo());
        assert(no2 == airQuality.getNo2());
        assert(o3 == airQuality.getO3());
        assert(so2 == airQuality.getSo2());
        assert(pm2_5 == airQuality.getPm2_5());
        assert(pm10 == airQuality.getPm10());
        assert(airQuality.toString().equals("Air Quality{location= Test Location', co=5', no2=10', o3=15', so2=20', pm2_5=25'pm10=30'}"));
    }
}
