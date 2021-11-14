package htwberlin.webtech.persistence;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "Notizen")
public class NotizbucheintragEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "Eintragszeit",nullable = false)
    private String ldt = LocalDateTime.now().toString();

    @Column(name = "Eintrag",nullable = false)
    private String entry;

    public NotizbucheintragEntity(String ldt, String entry) {
        this.ldt = ldt;
        this.entry = entry;
    }

    public NotizbucheintragEntity() {

    }

    public Long getId() {
        return id;
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
