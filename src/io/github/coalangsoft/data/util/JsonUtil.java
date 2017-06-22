package io.github.coalangsoft.data.util;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import io.github.coalangsoft.data.parse.RawData;
import io.github.coalangsoft.data.parse.ScriptObjectMirrorWrapper;
import jdk.nashorn.api.scripting.ScriptObjectMirror;

public class JsonUtil {

	public static RawData parse(String json) throws ScriptException{
		ScriptObjectMirror raw = (ScriptObjectMirror) new ScriptEngineManager().getEngineByExtension("js").eval("JSON.parse('" + (json.replace("'", "\\'")) + "')");
		return new ScriptObjectMirrorWrapper(raw);
	}
	
}
