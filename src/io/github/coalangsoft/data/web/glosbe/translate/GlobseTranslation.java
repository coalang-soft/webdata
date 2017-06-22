package io.github.coalangsoft.data.web.glosbe.translate;

import io.github.coalangsoft.data.parse.RawData;

import java.util.Arrays;

/**
 * Created by Matthias on 22.06.2017.
 */
public class GlobseTranslation {

    private RawData phrase;
    private RawData meanings;
    private Integer resultCount;

    public GlobseTranslation(RawData dat) {
        try{this.phrase = (RawData) dat.get("phrase");}catch(RuntimeException e){};
        try{this.meanings = (RawData) dat.get("meanings");}catch(RuntimeException e){};
        try{this.resultCount = (Integer) meanings.get("length");}catch(RuntimeException e){};
    }

    public int getResultCount(){
        if(resultCount == null){
            return 0;
        }
        return resultCount;
    }

    public String language(int index){
        if(meanings == null){
            return null;
        }
        return (String) ((RawData) meanings.at(index)).get("language");
    }

    public String text(int index){
        if(meanings == null){
            return null;
        }
        return (String) ((RawData) meanings.at(index)).get("text");
    }

    public String getPhraseLanguage(){
        if(phrase == null){
            return null;
        }
        return (String) phrase.get("language");
    }

    public String getPhraseText(){
        if(phrase == null){
            return null;
        }
        return (String) phrase.get("text");
    }

}
