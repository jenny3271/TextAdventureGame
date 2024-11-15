package edu.westga.cs3211.text_adventure_game.view;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.ListCell;
import edu.westga.cs3211.text_adventure_game.model.Action;
import edu.westga.cs3211.text_adventure_game.viewmodel.GameViewModel;

/** 
 * Codebehind for the Main Window of the application.
 * Handles the interaction between the UI and the ViewModel.
 * 
 * @author Jennifer Alvarez
 * @version Fall 2024
 */
public class MainWindow {

    @FXML
    private TextArea actionDescriptionsTextArea;

    @FXML
    private ComboBox<Action> actionsComboBox;

    @FXML
    private TextArea locationDescriptionTextArea;

    @FXML
    private Button takeActionButton;

    private GameViewModel viewModel;

    /**
     * Initializes the controller and sets up bindings.
     */
    @FXML
    void initialize() {
        assert this.actionDescriptionsTextArea != null : "fx:id=\"actionDescriptionsTextArea\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.actionsComboBox != null : "fx:id=\"actionsComboBox\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.locationDescriptionTextArea != null : "fx:id=\"locationDescriptionTextArea\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert this.takeActionButton != null : "fx:id=\"takeActionButton\" was not injected: check your FXML file 'MainWindow.fxml'.";

        this.viewModel = new GameViewModel();
        this.setupBindings();
        this.setupActionsComboBox();
        this.setupTakeActionButton();
        this.disableTextAreas();
    }

    private void setupBindings() {
        this.locationDescriptionTextArea.textProperty().bind(this.viewModel.locationDescriptionProperty());
        this.actionDescriptionsTextArea.textProperty().bind(this.viewModel.actionDescriptionsProperty());
        this.actionsComboBox.setItems(this.viewModel.getAvailableActions());
    }
    
    private void setupActionsComboBox() {
        this.actionsComboBox.setCellFactory(param -> new ListCell<Action>() {
            @Override
            protected void updateItem(Action action, boolean empty) {
                super.updateItem(action, empty);
                if (empty || action == null) {
                    setText(null);
                } else {
                    setText(action.getName());
                }
            }
        });
        
        this.actionsComboBox.setButtonCell(new ListCell<Action>() {
            @Override
            protected void updateItem(Action action, boolean empty) {
                super.updateItem(action, empty);
                if (empty || action == null) {
                    setText(null);
                } else {
                    setText(action.getName());
                }
            }
        });
    }

    private void setupTakeActionButton() {
    	this.takeActionButton.setOnAction(event -> {
            Action selectedAction = this.actionsComboBox.getSelectionModel().getSelectedItem();
            if (selectedAction != null) {
                this.viewModel.performAction(selectedAction);
            }
        });
    }
    
    private void disableTextAreas() {
        this.locationDescriptionTextArea.setEditable(false);
        this.actionDescriptionsTextArea.setEditable(false);
    }
}
