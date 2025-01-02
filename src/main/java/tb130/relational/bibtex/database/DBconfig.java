package tb130.relational.bibtex.database;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DBconfig{
    private String jdbcurl;
    private String username;
    private String password;

    @JsonProperty("jdbcurl")
    public String getJdbcurl() {
        return jdbcurl;
    }

    public void setJdbcurl(String jdbcurl) {
        this.jdbcurl = jdbcurl;
    }

    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty("password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "DBconfig{" +
                "jdbcurl='" + jdbcurl + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}