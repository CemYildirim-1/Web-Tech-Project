package HTWBERLIN.webtech.web.api;

import java.time.LocalDateTime;

public class NotizbucheintragCreateRequest {

    private String ldt;
    private String entry;
    private String colour;

    public NotizbucheintragCreateRequest() {}
    public NotizbucheintragCreateRequest(String ldt, String entry, String colour) {
        this.ldt = ldt;
        this.entry = entry;
        this.colour = colour;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getLdt() {
        return ldt;
    }

    public void setLdt(String ldt) {
        this.ldt = ldt;
    }

    public String getEntry() {
        return entry;
    }

    public void setEntry(String entry) {
        this.entry = entry;
    }
}
