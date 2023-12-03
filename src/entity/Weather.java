package entity;

public interface Weather {
    public String getLocation();

    public String getTime();

    public int getTempC();

    public int getTempF();

    public String getCondition();
}
