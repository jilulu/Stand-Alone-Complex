package model;

public class SQLAdminImpl implements IAdmin {
    private int id;
    private String userName, userPassword;
    private String userEmail;

    public SQLAdminImpl(int id, String userName, String userPassword, String userEmail) {
        this.id = id;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userEmail = userEmail;
    }

    public SQLAdminImpl(int id, String userName, String userPassword) {
        this.id = id;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public SQLAdminImpl(String userName, String userPassword) {
        this.userName = userName;
        this.userPassword = userPassword;
    }

    @Override
    public int getUserId() {
        return id;
    }

    @Override
    public String getUserName() {
        return userName;
    }

    @Override
    public String getUserPassword() {
        return userPassword;
    }

    @Override
    public String getUserEmail() {
        return userEmail;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SQLAdminImpl)) {
            return false;
        }
        SQLAdminImpl sqlAdmin = (SQLAdminImpl) obj;
        return id == sqlAdmin.id &&
                (userName == null ? sqlAdmin.userName == null : userName.equals(sqlAdmin.userName)) &&
                (userPassword == null ? sqlAdmin.userPassword == null : userPassword.equals(sqlAdmin.userPassword));
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = result * 31 + id;
        result = result * 31 + userName.hashCode();
        result = result * 31 + userPassword.hashCode();
        return result;
    }
}
