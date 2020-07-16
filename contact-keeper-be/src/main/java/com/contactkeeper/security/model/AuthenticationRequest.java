package com.contactkeeper.security.model;

public class AuthenticationRequest {

    private String useraname;
    private String password;

    public AuthenticationRequest() {
    }

    public AuthenticationRequest(String useraname, String password) {
        this.useraname = useraname;
        this.password = password;
    }

    public String getUseraname() {
        return useraname;
    }

    public void setUseraname(String useraname) {
        this.useraname = useraname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
