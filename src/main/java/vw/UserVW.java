package vw;

public class UserVW {
    private String email;
    private String password;
    private boolean type;

    public UserVW() {
    }

    public UserVW(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserVW(String email, String password, boolean type) {
        this.email = email;
        this.password = password;
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean getType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "UserVW{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", type=" + type +
                '}';
    }
}