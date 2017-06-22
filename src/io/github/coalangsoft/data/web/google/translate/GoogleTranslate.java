package io.github.coalangsoft.data.web.google.translate;

import io.github.coalangsoft.data.parse.RawData;
import io.github.coalangsoft.data.util.JsonUtil;
import io.github.coalangsoft.data.util.ReadUtil;

import javax.script.ScriptException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Matthias on 22.06.2017.
 */
public class GoogleTranslate {

    public static String translate(String fromLang, String toLang, String text) throws IOException, ScriptException {
        StringBuilder sb = new StringBuilder("https://translate.googleapis.com/translate_a/single?dt=t&client=gtx&sl=");
        sb.append(fromLang);
        sb.append("&tl=");
        sb.append(toLang);
        sb.append("&q=");
        sb.append(text);

        RawData dat = JsonUtil.parse(ReadUtil.readLine(new URL(sb.toString())));
        RawData array1 = (RawData) dat.at(0);
        RawData array2 = (RawData) array1.at(0);
        return (String) array2.at(0);
    }

}
