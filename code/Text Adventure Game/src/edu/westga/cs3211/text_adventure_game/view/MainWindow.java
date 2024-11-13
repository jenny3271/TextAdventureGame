package edu.westga.cs3211.text_adventure_game.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

/** Codebehind for the Main Window of the application.
 * 
 * @author CS 3211
 * @version Fall 2024
 */
public class MainWindow {

    @FXML
    private TextArea actionDescriptionsTextArea;

    @FXML
    private ComboBox<?> actionsComboBox;

    @FXML
    private TextArea locationDescriptionTextArea;

    @FXML
    private Button takeActionButton;

    @FXML
    void initialize() {
        assert this.actionDescriptionsTextArea != null : "fx:id=\"actionDescriptionsTextArea\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.actionsComboBox != null : "fx:id=\"actionsComboBox\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.locationDescriptionTextArea != null : "fx:id=\"locationDescriptionTextArea\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.takeActionButton != null : "fx:id=\"takeActionButton\" was not injected: check your FXML file 'MainWindow.fxml'.";

    }
}
