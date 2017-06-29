import io.github.coalangsoft.data.web.HttpAgent;
import io.github.coalangsoft.data.web.UrlEncode;
import io.github.coalangsoft.data.web.glosbe.translate.GlosbeTranslations;
import io.github.coalangsoft.data.web.glosbe.translate.GlosbeTranslator;
import io.github.coalangsoft.data.web.google.translate.GoogleTranslate;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import javax.script.ScriptException;
import java.io.IOException;
import java.util.Locale;

/**
 * Created by Matthias on 22.06.2017.
 */
public class TranslateTest extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception {
        //setup
        final Locale[] languages = Locale.getAvailableLocales();

        ObservableList<String> uiLanguages = FXCollections.observableArrayList();
        for(int i = 0; i < languages.length; i++){
            uiLanguages.add(languages[i].getDisplayName());
        }

        //UI
        final ComboBox<String> fromLanguage = new ComboBox<String>();
        final ComboBox<String> toLanguage = new ComboBox<String>();
        fromLanguage.getSelectionModel().select(Locale.getDefault().getDisplayName());
        fromLanguage.setItems(uiLanguages);
        toLanguage.setItems(uiLanguages);
        toLanguage.getSelectionModel().select(Locale.forLanguageTag("en").getDisplayName());

        fromLanguage.setOnShown(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                System.out.println(fromLanguage.getItems());
                fromLanguage.hide();
            }
        });

        TextArea toTranslate = new TextArea();
        toTranslate.setPromptText("Insert text to translate...");

        final TextFlow result = new TextFlow();
        final ListView<String> options = new ListView<String>();

        toTranslate.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                try {
                    result.getChildren().clear();
                    String translation = GoogleTranslate.translate(
                            languages[fromLanguage.getSelectionModel().getSelectedIndex()].getLanguage(),
                            languages[toLanguage.getSelectionModel().getSelectedIndex()].getLanguage(),
                            UrlEncode.encode(newValue)
                                .replaceAll("\\s+", "%20")
                    );
                    String[] split = translation.split("\\s+");
                    for(int i = 0; i < split.length; i++){
                        result.getChildren().add(wordText(
                                options.getItems(),
                                languages[fromLanguage.getSelectionModel().getSelectedIndex()].getLanguage(),
                                languages[toLanguage.getSelectionModel().getSelectedIndex()].getLanguage(),
                                split[i]
                        ));
                        result.getChildren().add(new Text(" "));
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ScriptException e) {
                    e.printStackTrace();
                }
            }
        });
        toTranslate.selectedTextProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                System.out.println(newValue);
                showOptions(options.getItems(),
                        languages[fromLanguage.getSelectionModel().getSelectedIndex()].getLanguage(),
                        languages[toLanguage.getSelectionModel().getSelectedIndex()].getLanguage(),
                        UrlEncode.encode(newValue.trim())
                                .replaceAll("\\s+", "%20"));
            }
        });

        BorderPane mainLayout = new BorderPane();
        mainLayout.setTop(new HBox(fromLanguage,toLanguage));

        SplitPane mainView = new SplitPane();
        mainView.getItems().add(toTranslate);
        mainView.getItems().add(result);

        SplitPane splitPane = new SplitPane();
        splitPane.setOrientation(Orientation.VERTICAL);
        splitPane.getItems().add(mainView);
        splitPane.getItems().add(options);

        mainLayout.setCenter(splitPane);

        Scene sc = new Scene(mainLayout);
        setUserAgentStylesheet(Application.STYLESHEET_CASPIAN);
        primaryStage.setScene(sc);
        primaryStage.show();
    }

    private Text wordText(final ObservableList<String> options, final String from, final String to, final String s) {
        Text text = new Text(s);
        text.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                showOptions(options, to, from, s);
            }
        });
        return text;
    }

    public static void main(String[] args){
        HttpAgent.MOZILLA.use();
        launch(args);
    }

    private void showOptions(ObservableList<String> list, String from, String to, String original){
        try {
            //from -> to
            GlosbeTranslations translations = GlosbeTranslator.translate(
                    from,
                    to,
                    UrlEncode.encode(original.trim())
                            .replaceAll("\\s+", "%20")
            );
            list.clear();
            for(int i = 0; i < translations.getResultCount(); i++){
                try{
                    String phraseText = translations.get(i).getPhraseText();
                    if(phraseText != null)
                        list.add(phraseText);
                }catch (RuntimeException e){
                    e.printStackTrace();
                }
            }

            //from -> from
            translations = GlosbeTranslator.translate(
                    from,
                    from,
                    UrlEncode.encode(original.trim())
                            .replaceAll("\\s+", "%20")
            );
            for(int i = 0; i < translations.getResultCount(); i++){
                try{
                    String text = translations.get(i).text(0);
                    if(text != null)
                        list.add(text);
                }catch (RuntimeException e){
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ScriptException e) {
            e.printStackTrace();
        }
    }

}
