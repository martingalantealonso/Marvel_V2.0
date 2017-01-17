package com.example.mgalante.marvel_v20.api;

import com.example.mgalante.marvel_v20.utils.Constants;
import com.example.mgalante.marvel_v20.utils.Utils;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * Created by mgalante on 17/01/17.
 *
 * el SendRequest.create() es llamado DESDE el PRESENTER (MainPresenterImpl) a la hora de obtener los héroes (getHeroes()) cuando se cambia el texto en el edTxt de la Main.
 * De este modo:
 *
 * 1º Se llama al getHeroes en la MainActivity.
 * 2º Este está definido en el Presenter, sobreescribiendo al del MainContract; ahí se llama(Call) al SendRequest.create() para que se cree la petición, y se devolverán los datos a traves del CALLBACK.
 *  2.1 Una vez el Callback obtenga la informacion de forma Asyncrona, rellenará con los datos en el ONRESPONSE.
 *  2.2 Este relleno de datos, se hará en el metodo sobreescrito en la MainActivity. Override de MainContract
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
