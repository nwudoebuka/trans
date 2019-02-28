package com.agonaika.data.remote;

import android.content.ContentValues;

import com.agonaika.data.domain.user.UserLogin;

import java.net.HttpURLConnection;
import java.util.ArrayList;

public interface IAgoRequest {

    public ArrayList<ContentValues> getHeaders();
    public boolean doInput();
    public boolean doOutput();
    public Class<?> getResponseType();
    public int getConnectTimeout();
    public int getId();
    public int getReadTimeout();
    public HttpURLConnection openConnection();
    public RequestMethod getMethod();
    public void setMethod(RequestMethod requestMethod);
    public RequestPriority getPriority();
    public String getEncoding();
    public String getName();
    public String getQueryString();
    public String getUrl();
    public Object executeRequestImmediately();
    public <T> Object executeRequest(Class<T> classType);
    public <T> ArrayList<T> executeRequestImmediately(Class<T> classType);

    public UserLogin getCredentials();

    public void setCredentials(UserLogin credentials);
}
