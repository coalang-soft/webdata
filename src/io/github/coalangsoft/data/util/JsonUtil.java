package io.github.coalangsoft.data.util;

import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

public class JsonUtil {
	
	public static ScriptObjectMirror parse(String json) throws ScriptException{
		return (ScriptObjectMirror) new ScriptEngineManager().getEngineByExtension("js").eval("JSON.parse('" + (json.replace("'", "\\'")) + "')");
	}
	
}
