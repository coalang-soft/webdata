package io.github.coalangsoft.data.web;

/**
 * Created by Matthias on 22.06.2017.
 */
public class QDUrlEncode {

    public static String encode(String part){
        return part
                .replace(" ", "%20");
    }

}
