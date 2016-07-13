package mxawsiot.impl;

/**
 * Created by ako on 4/21/2016.
 */
public class AwsSecurityException extends Exception {
    private final int statusCode;

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusText() {
        return statusText;
    }

    private final String statusText;

    public AwsSecurityException(int statusCode, String statusText) {
        this.statusCode = statusCode;
        this.statusText = statusText;
    }
}
