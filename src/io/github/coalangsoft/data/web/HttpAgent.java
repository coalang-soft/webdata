package io.github.coalangsoft.data.web;

/**
 * Created by Matthias on 22.06.2017.
 */
public enum HttpAgent {

    MOZILLA("Mozilla/5.0");

    private final String agent;

    private HttpAgent(String agent){
        this.agent = agent;
    }

    public void use(){
        System.setProperty("http.agent", agent);
    }

}
