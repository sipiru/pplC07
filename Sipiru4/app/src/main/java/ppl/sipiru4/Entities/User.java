package ppl.sipiru4.Entities;

/**
 * Created by User on 11/04/2015.
 */
public class User {
    private String username;
    private String kodeIdentitas;
    private String role;

    public User(String username, String kodeIdentitas, String role) {
        this.username = username;
        this.kodeIdentitas = kodeIdentitas;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getKodeIdentitas() {
        return kodeIdentitas;
    }

    public void setKodeIdentitas(String kodeIdentitas) {
        this.kodeIdentitas = kodeIdentitas;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        return ((User)o).getUsername().compareTo(username) == 0;
    }
}
