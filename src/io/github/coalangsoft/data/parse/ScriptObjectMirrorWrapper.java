package io.github.coalangsoft.data.parse;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

/**
 * Created by Matthias on 22.06.2017.
 */
public class ScriptObjectMirrorWrapper implements RawData{

    private final ScriptObjectMirror base;

    public ScriptObjectMirrorWrapper(ScriptObjectMirror base){
        this.base = base;
    }

    private static Object wrap(Object res){
        if(res instanceof ScriptObjectMirror){
            return new ScriptObjectMirrorWrapper((ScriptObjectMirror) res);
        }else{
            return res;
        }
    }

    @Override
    public Object get(String name) {
        return wrap(base.get(name));
    }

    @Override
    public Object at(int index) {
        return wrap(base.getSlot(index));
    }

    @Override
    public String[] keys() {
        return base.getOwnKeys(false);
    }
}
