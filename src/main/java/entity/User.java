package entity;

/**
 * The user entity.
 *
 * @author Guozhi Zhan
 */
public class User {

    private int id;
    private String userId;
    private String passwd;
    private String nickname;

    /**
     * Construct an empty object.
     */
    public User() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", passwd='" + passwd + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
