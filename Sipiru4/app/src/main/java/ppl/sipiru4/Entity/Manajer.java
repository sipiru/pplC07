package ppl.sipiru4.Entity;

public class Manajer {
    private String username;
    private String nama;
    private String role;

    public Manajer(String username, String nama, String role) {
        this.username = username;
        this.nama = nama;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getNama() {
        return nama;
    }

    public String getRole() {
        return role;
    }
}
