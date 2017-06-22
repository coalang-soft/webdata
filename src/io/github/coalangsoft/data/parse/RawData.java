package io.github.coalangsoft.data.parse;

/**
 * Created by Matthias on 22.06.2017.
 */
public interface RawData {

    Object get(String name);
    Object at(int index);
    String[] keys();

}
