package com.example.mgalante.marvel_v20.api;

import com.example.mgalante.marvel_v20.utils.Constants;
import com.example.mgalante.marvel_v20.utils.Utils;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by mgalante on 17/01/17.
 */

public class SendRequest {

    private static final String apiKey = Constants.API_KEY;
    private static final String privateKey= Constants.PRIVATE_KEY;
    private static final long rTime = 1000L;

    private long timeStamp;
    private static final String publicKey = apiKey;
    private String hashSignature;
    private static Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

    public String getHashSignature() {
        return hashSignature;
    }

    public static String getApiKey() {
        return apiKey;
    }

    public static String getPrivateKey() {
        return privateKey;
    }

    public static String getPublicKey() {
        return publicKey;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    private SendRequest(){
        this.timeStamp = 1;
        this.hashSignature = Utils.md5(String.valueOf(this.timeStamp) + privateKey + publicKey);
    }

    /**
     * Returnes a new instance of a send request.
     * @return
     */
    public static SendRequest create(){
        return  new SendRequest();
    }
}
