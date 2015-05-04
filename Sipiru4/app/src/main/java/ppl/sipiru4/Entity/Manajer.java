package ppl.sipiru4.Entity;

public class Manajer {
    private String username;
    private String tipe;

    public Manajer(String username, String tipe) {
        this.username = username;
        this.tipe = tipe;
    }

    public String getUsername() {
        return username;
    }

    public String getTipe() {
        return tipe;
    }
}
