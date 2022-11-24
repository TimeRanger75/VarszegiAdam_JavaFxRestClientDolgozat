package hu.petrik.varszegiadam_javafxrestclientdolgozat;

import com.google.gson.annotations.Expose;

public class Driver {

    private int id;

    @Expose
    private String driver_name;

    @Expose
    private String team_name;

    @Expose
    private boolean retired;

    @Expose
    private int start_Year;


    public Driver(int id, String driver_name, String team_name, boolean retired, int start_Year) {
        this.id = id;
        this.driver_name = driver_name;
        this.team_name = team_name;
        this.retired = retired;
        this.start_Year = start_Year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDriver_name() {
        return driver_name;
    }

    public void setDriver_name(String driver_name) {
        this.driver_name = driver_name;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public boolean isRetired() {
        return retired;
    }

    public void setRetired(boolean retired) {
        this.retired = retired;
    }

    public int getStart_Year() {
        return start_Year;
    }

    public void setStart_Year(int start_Year) {
        this.start_Year = start_Year;
    }
}
