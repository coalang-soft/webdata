package io.github.coalangsoft.data.web.glosbe.translate;

import io.github.coalangsoft.data.parse.RawData;
import io.github.coalangsoft.data.util.JsonUtil;
import io.github.coalangsoft.data.util.ReadUtil;

import javax.script.ScriptException;
import java.io.IOException;
import java.net.URL;

/**
 * Created by Matthias on 22.06.2017.
 */
public class GlosbeTranslator {

    public static GlosbeTranslations translate(String langFrom, String langTo, String phrase) throws IOException, ScriptException {
        //https://glosbe.com/gapi/translate?from=en&dest=de&phrase=How%20are%20you&format=json
        StringBuilder sb = new StringBuilder("https://glosbe.com/gapi/translate?from=");
        sb.append(langFrom);
        sb.append("&dest=");
        sb.append(langTo);
        sb.append("&phrase=");
        sb.append(phrase);
        sb.append("&format=json");

        RawData dat = JsonUtil.parse(ReadUtil.readLine(new URL(sb.toString())));
        return new GlosbeTranslations(dat);
    }

}
