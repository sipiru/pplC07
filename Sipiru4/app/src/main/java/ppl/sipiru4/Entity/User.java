package ppl.sipiru4.Entity;

/**
 * Created by User on 11/04/2015.
 */
public class User {
    private String username;
    private String nama;
    private String kodeOrg;
    private String role;
    private String kodeIdentitas;

    public User(String username, String nama, String kodeOrg, String role, String kodeIdentitas) {
        this.username = username;
        this.nama = nama;
        this.kodeOrg = kodeOrg;
        this.role = role;
        this.kodeIdentitas = kodeIdentitas;
    }

    public String getUsername() {return username;}

    public String getNama() {
        return nama;
    }

    public String getKodeOrg() {
        return kodeOrg;
    }

    public String getRole() {
        return role;
    }

    public String getKodeIdentitas() {
        return kodeIdentitas;
    }

    @Override
    public boolean equals(Object o) {
        return ((User)o).getUsername().compareTo(username) == 0;
    }
}
