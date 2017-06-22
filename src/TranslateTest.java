import io.github.coalangsoft.data.web.QDUrlEncode;
import io.github.coalangsoft.data.web.glosbe.translate.GlobseTranslation;
import io.github.coalangsoft.data.web.glosbe.translate.GlosbeTranslations;
import io.github.coalangsoft.data.web.glosbe.translate.GlosbeTranslator;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import javax.script.ScriptException;
import java.io.IOException;

/**
 * Created by Matthias on 22.06.2017.
 */
public class TranslateTest extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        TextField tf = new TextField();
        ListView<String> results = new ListView<>();

        tf.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    ObservableList<String> list = getTranslations(tf.getText());
                    results.setItems(list);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ScriptException e) {
                    e.printStackTrace();
                }

            }
        });

        BorderPane layout = new BorderPane(results);
        layout.setTop(tf);

        Scene sc = new Scene(layout);
        primaryStage.setScene(sc);
        primaryStage.show();;
    }

    public static void main(String[] args){
        launch(args);
    }

    public static ObservableList<String> getTranslations(String in) throws IOException, ScriptException {
        ObservableList<String> list = FXCollections.observableArrayList();

        GlosbeTranslations translations = GlosbeTranslator.translate("en", "de", QDUrlEncode.encode(in));
        for(int i = 0; i < translations.getResultCount(); i++){
            try{
                GlobseTranslation translation = translations.get(i);
                for(int k = 0; k < translation.getResultCount(); k++){
                    String text = translation.getPhraseText();
                    if(text == null) continue;
                    if(!list.contains(text)){
                        list.add(text);
                    }
                }
            }catch (RuntimeException e){
                e.printStackTrace();
            }
        }

        return list;
    }

}
