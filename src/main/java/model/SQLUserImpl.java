package model;

/**
 * Created by jamesji on 04/04/2017.
 * Copyright © 2017 James Ji. All rights reserved.
 */
public class SQLUserImpl implements IUser {
    private int id;
    private String userName, userPassword;
    private String userPurchaseRecord;

    public SQLUserImpl(int id, String userName, String userPassword, String userPurchaseRecord) {
        this.id = id;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userPurchaseRecord = userPurchaseRecord;
    }

    public SQLUserImpl(int id, String userName, String userPassword) {
        this.id = id;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public SQLUserImpl(String userName, String userPassword) {
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
    public String getUserPurchaseRecord() {
        return userPurchaseRecord;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof SQLUserImpl)) {
            return false;
        }
        SQLUserImpl sqlUser = (SQLUserImpl) obj;
        return id == sqlUser.id &&
                (userName == null ? sqlUser.userName == null : userName.equals(sqlUser.userName)) &&
                (userPassword == null ? sqlUser.userPassword == null : userPassword.equals(sqlUser.userPassword));
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
