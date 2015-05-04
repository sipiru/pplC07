package ppl.sipiru4.Entity;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
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

    public User(Parcel source) {
        username = source.readString();
        nama = source.readString();
        kodeOrg = source.readString();
        role = source.readString();
        kodeIdentitas = source.readString();
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(nama);
        dest.writeString(kodeOrg);
        dest.writeString(role);
        dest.writeString(kodeIdentitas);
    }

    public static final Parcelable.Creator<User> CREATOR
            = new Parcelable.Creator<User>() {

        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
