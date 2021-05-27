package model;

import annotation.PropertyKey;
import lombok.ToString;

@ToString
public class AppProperties {
    @PropertyKey("java.version")
    public double version;

    @PropertyKey("url")
    public String url;

    @PropertyKey("app.host")
    public String host;

    @PropertyKey("role")
    public Role role;

    @PropertyKey("app.status")
    public boolean status;

    @PropertyKey("user.name")
    private String name;

    @PropertyKey("user.pass")
    private int password;

}
