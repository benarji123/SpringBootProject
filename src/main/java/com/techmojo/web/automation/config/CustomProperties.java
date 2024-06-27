package com.techmojo.web.automation.config;


 

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;


 

import lombok.Data;


 

@Configuration
@ConfigurationProperties(prefix = "application")
//@Data
public class CustomProperties {
private String url;
private String password;
private String username;
private String otp;


 


public String getUrl() {
return url;
}
public void setUrl(String url) {
this.url = url;
}
public String getPassword() {
return password;
}
public void setPassword(String password) {
this.password = password;
}
public String getUsername() {
return username;
}
public void setUsername(String username) {
this.username = username;
}
public String getOtp() {
return otp;
}
public void setOtp(String otp) {
this.otp = otp;
}
}