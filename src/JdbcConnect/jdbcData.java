package JdbcConnect;

public class jdbcData {
    private String username;
    private String password;
    private String DriverClass;
    private String url;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverClass() {
        return DriverClass;
    }

    public void setDriverClass(String driverClass) {
        DriverClass = driverClass;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
