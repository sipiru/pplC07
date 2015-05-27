package ppl.sipiru4.Entity;

public class Manajer {
    private String username;
    private String nama;
    private String role;
    private String no_hp;

    public Manajer(String username, String nama, String role, String no_hp) {
        this.username = username;
        this.nama = nama;
        this.role = role;
        this.no_hp = no_hp;
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

    public String getNo_hp() {
        return no_hp;
    }
}
