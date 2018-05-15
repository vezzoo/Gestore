package it.lucaVezzoli.Application;

import java.io.Serializable;

public class DBConnection implements Serializable {
    private String dbName;
    private String dbTable;
    private String dbHost;
    private String dbUser;
    private String dbPassword;
    private String errors;

    public DBConnection(String dbName, String dbTable, String dbHost, String dbUser, String dbPassword, String errors) {
        this.dbName = dbName;
        this.dbTable = dbTable;
        this.dbHost = dbHost;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
        this.errors = errors;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public void setDbHost(String dbHost) {
        this.dbHost = dbHost;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public String getDbName() {
        return dbName;
    }

    public String getDbTable() {
        return dbTable;
    }

    public void setDbTable(String dbTable) {
        this.dbTable = dbTable;
    }

    public String getDbHost() {
        return dbHost;
    }

    public String getDbUser() {
        return dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "DBConnection{" +
                "dbName='" + dbName + '\'' +
                ", dbTable='" + dbTable + '\'' +
                ", dbHost='" + dbHost + '\'' +
                ", dbUser='" + dbUser + '\'' +
                ", dbPassword='" + dbPassword + '\'' +
                ", errors='" + errors + '\'' +
                '}';
    }
}