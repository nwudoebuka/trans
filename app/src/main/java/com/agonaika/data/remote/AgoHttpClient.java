package com.agonaika.data.remote;

import android.support.annotation.Nullable;
import android.util.Log;

import com.agonaika.data.domain.user.DeviceUser;
import com.agonaika.utils.AgoLog;
import com.agonaika.utils.StreamUtil;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.HttpURLConnection;
import java.util.ArrayDeque;
import java.util.ArrayList;

public class AgoHttpClient {

    private static AgoHttpClient instance;

    public static AgoHttpClient get() {
        if (instance == null) {
            instance = new AgoHttpClient();
        }
        return instance;
    }

    private static final String TAG = AgoHttpClient.class.getSimpleName();

    private ArrayDeque<IAgoRequest> normalProrityRequests = new ArrayDeque<IAgoRequest>();
    private ArrayDeque<IAgoRequest> urgentPriorityRequests = new ArrayDeque<IAgoRequest>();

    private ArrayList<ArrayDeque<IAgoRequest>> pendingRequestQueues = new ArrayList<ArrayDeque<IAgoRequest>>();

    private Gson gson = new Gson();

    public AgoHttpClient() {
        pendingRequestQueues.add(urgentPriorityRequests);
        pendingRequestQueues.add(normalProrityRequests);
    }

    public int getPendingRequestCount() {
        int count = 0;

        for (ArrayDeque<IAgoRequest> pendingRequests : pendingRequestQueues) {
            count += pendingRequests.size();
        }

        return count;
    }

    public void addRequest(IAgoRequest request) {
        addRequest(request, request.getPriority());

        if (getPendingRequestCount() == 1) {
            checkForNext();
        }

    }

    public Object executeRequestImmediately(IAgoRequest request) {
        String responseString = executeRequest(request);
        Object responseObject = null;

        responseObject = handleResponse(request, responseString);
        return responseObject;
    }

    @Nullable
    private String executeRequest(IAgoRequest request) {
        String requestName = request.getClass().getSimpleName();

        //IMLog.v(TAG,  String.format(" %s has begun!  %s %s", requestName, request.getMethod(), request.getUrl()));

        HttpURLConnection urlConnection = request.openConnection();
        currentConnection = urlConnection;

        try {
            DataOutputStream os = new DataOutputStream(urlConnection.getOutputStream());
            String outputString = gson.toJson(request);

            AgoLog.i(TAG, "JsonRequest:" + new JSONObject(outputString).toString(2));

            os.writeBytes(outputString);
            os.flush();
            os.close();
        } catch (IOException e) {
            AgoLog.e(TAG, "Error writing to output stream for " + requestName);
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String responseString = null;
        try {
            InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
            responseString = StreamUtil.getStringFromStream(inputStream);

            AgoLog.d(TAG, String.format(" %s has succeeded! Responded with: %s", requestName , responseString));

        } catch (IOException e) {
            InputStream errorStream = new BufferedInputStream(urlConnection.getErrorStream());
            String errorString = StreamUtil.getStringFromStream(errorStream);

            AgoLog.e(TAG, String.format(" %s has failed! Responded with: ", requestName, errorString));

            e.printStackTrace();
        } finally {
            urlConnection.disconnect();
            AgoLog.v(TAG, requestName + " has finished executing!");
        }
        return responseString;
    }

    public <T> ArrayList<T> executeRequestImmediately(IAgoRequest request, Class<T> classType) {
        String requestName = request.getClass().getSimpleName();

        //IMLog.v(TAG,  String.format(" %s has begun!  %s %s", requestName, request.getMethod(), request.getUrl()));

        HttpURLConnection urlConnection = request.openConnection();

        ArrayList<T> returnObject = null;
        try {

            InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
            String responseString = StreamUtil.getStringFromStream(inputStream);

            returnObject = ParseJson.toObjectListFromJsonString(responseString, classType);

            Log.d(TAG, String.format(" %s has succeeded! Responded with: %s", requestName, responseString));

        } catch (IOException e) {
            InputStream errorStream = new BufferedInputStream(urlConnection.getErrorStream());
            String errorString = StreamUtil.getStringFromStream(errorStream);

            AgoLog.e(TAG, String.format(" %s has failed! Responded with: %s", requestName, errorString));

            e.printStackTrace();
        } catch (Exception exception) {
            AgoLog.e(TAG, String.format(" %s has failed! Responded with: %s", requestName, exception.toString()));
        } finally {
            if(urlConnection != null) {
                urlConnection.disconnect();
            }

            AgoLog.v(TAG, String.format(" %s has finished executing!", requestName));
        }

        return returnObject;
    }

    public <T> Object executeRequest(IAgoRequest request, Class<T> classType) {
        String responseString = executeRequest(request);

        if (responseString != null) {
            AgoLog.i("IMHttpClient.JsonResponse", String.format("The lenth of the retuned Json String is [%s]", responseString.trim().length()));
        }
        Object responseObject = null;

        try {
            responseObject = responseString == null ? null : ParseJson.jsonStringToGenericType(responseString, classType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseObject;
    }

    public void addRequest(IAgoRequest request, RequestPriority priority) {
        switch (priority) {
            case NORMAL:
                normalProrityRequests.add(request);
                break;
            case URGENT:
                urgentPriorityRequests.add(request);
                break;
        }
    }

    private void checkForNext() {
        if (getPendingRequestCount() > 0) {
            doNextRequest();
        }
    }

    private void doNextRequest() {
        for (ArrayDeque<IAgoRequest> pendingRequests : pendingRequestQueues) {
            if (!pendingRequests.isEmpty()) {
                doRequest(pendingRequests.pop());
                return;
            }
        }
    }

    private HttpURLConnection currentConnection;

    public void cancelJsonRequest() {
        if (currentConnection != null) {
            currentConnection.disconnect();
        }
    }

    private void doRequest(final IAgoRequest request) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String requestName = request.getClass().getSimpleName();

                //IMLog.v(TAG, requestName + " has begun!", request.getMethod() + " " + request.getUrl());

                HttpURLConnection urlConnection = request.openConnection();
                currentConnection = urlConnection;

                try {
                    DataOutputStream os = new DataOutputStream(urlConnection.getOutputStream());

                    if (request.getCredentials() == null) {
                        request.setCredentials(DeviceUser.get().getCurrentCredentials());
                    }

                    String outputString = gson.toJson(request);

                    AgoLog.i(TAG, "Outputting:",
                            new JSONObject(outputString).toString(2));

                    os.writeBytes(outputString);
                    os.flush();
                    os.close();
                } catch (IOException e) {
                    AgoLog.e(TAG, "Error writing to output stream for " + requestName);
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                try {
                    InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
                    String responseString = StreamUtil.getStringFromStream(inputStream);

                    AgoLog.d(TAG, String.format(" %s has succeeded! Responded with: %s", requestName,responseString));

                    handleResponse(request, responseString);
                } catch (IOException e) {
                    InputStream errorStream = new BufferedInputStream(urlConnection.getErrorStream());
                    String errorString = StreamUtil.getStringFromStream(errorStream);

                    AgoLog.e(TAG, String.format(" %s has failed! Responded with: %s", requestName, errorString));

                    e.printStackTrace();
                } finally {
                    urlConnection.disconnect();
                    AgoLog.v(TAG, requestName + " has finished executing!");
                }
            }
        }).start();
    }

    private Object handleResponse(IAgoRequest request, String response) {
        Class<?> responseType = request.getResponseType();
        Object responseObject = response;

        try {
            if (responseType != null) {
                Constructor<?> constructor = responseType.getConstructor(JSONObject.class);
                responseObject = constructor.newInstance(new JSONObject(response));
            } else {
                AgoLog.w(TAG, "Response type for request " + request.getName() + " was not provided.");
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            AgoLog.e(TAG, "Response type for request " +
                    request.getName() + ", " + responseType.getSimpleName() +
                    ", does not have a constructor with the parameters (JSONObject).");

            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }

        return responseObject;
        //HttpResponseBroadcaster.get().broadcastSuccess(request, responseObject);
    }
}
