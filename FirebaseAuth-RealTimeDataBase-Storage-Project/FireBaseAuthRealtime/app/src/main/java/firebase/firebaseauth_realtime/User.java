package firebase.firebaseauth_realtime;

/**
 * Created by Furkan on 13.12.2017.
 */

public class User {
    String name;
    String uid;

    public User(String name, String uid) {
        this.name = name;
        this.uid = uid;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
