package io.github.coalangsoft.data.web.glosbe.translate;

import io.github.coalangsoft.data.parse.RawData;

/**
 * Created by Matthias on 22.06.2017.
 */
public class GlosbeTranslations {

    private Integer resultCount;
    private String from;
    private String to;
    private RawData result;

    public GlosbeTranslations(RawData dat) {
        try{this.from = (String) dat.get("from");}catch(RuntimeException e){};
        try{this.to = (String) dat.get("dest");}catch(RuntimeException e){};
        try{this.result = (RawData) dat.get("tuc");}catch(RuntimeException e){};
        try{this.resultCount = (Integer) result.get("length");}catch(RuntimeException e){};
    }

    public int getResultCount() {
        if(resultCount == null){
            return 0;
        }
        return resultCount;
    }

    public String getFromLanguage() {
        return from;
    }

    public String getToLanguage() {
        return to;
    }

    public GlobseTranslation get(int index){
        return new GlobseTranslation((RawData) result.at(index));
    }

}
