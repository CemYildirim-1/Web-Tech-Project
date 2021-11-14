package htwberlin.webtech.web.api;

import java.time.LocalDateTime;

public class NotizbucheintragCreateRequest {

    private String ldt;
    private String entry;

    public NotizbucheintragCreateRequest(String ldt, String entry) {
        this.ldt = ldt;
        this.entry = entry;
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
