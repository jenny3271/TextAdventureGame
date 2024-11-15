package edu.westga.cs3211.text_adventure_game.viewmodel;

import java.util.List;

import edu.westga.cs3211.text_adventure_game.model.Action;
import edu.westga.cs3211.text_adventure_game.model.GameManager;
import edu.westga.cs3211.text_adventure_game.model.Location;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The GameViewModel
 * 
 * @author Jennifer Alvarez
 * @version Fall 2024
 */
public class GameViewModel {
	private GameManager gameManager;
    private StringProperty locationDescription;
    private StringProperty actionDescriptions;
    private ObservableList<Action> availableActions;
    
    /**
     * Constructs a new GameViewModel and initializes properties.
     */
    public GameViewModel() {
        this.gameManager = new GameManager();
        this.locationDescription = new SimpleStringProperty(this.gameManager.getCurrentLocation().getDescription());
        this.actionDescriptions = new SimpleStringProperty();
        this.availableActions = FXCollections.observableArrayList();
        this.updateAvailableActions();
    }
    
    /**
     * Gets the location description property.
     * 
     * @return the location description property
     */
    public StringProperty locationDescriptionProperty() {
        return this.locationDescription;
    }
    
    /**
     * Gets the action descriptions property.
     * 
     * @return the action descriptions property
     */
    public StringProperty actionDescriptionsProperty() {
        return this.actionDescriptions;
    }

    /**
     * Gets the list of available actions.
     * 
     * @return the list of available actions
     */
    public ObservableList<Action> getAvailableActions() {
        return this.availableActions;
    }

    private void updateAvailableActions() {
    	this.availableActions.clear();
        if (this.gameManager.getCurrentLocation() != null) {
            List<Action> actions = this.gameManager.getCurrentLocation().getAvailableActions();
            if (actions != null) {
                this.availableActions.addAll(actions);
                this.updateActionDescriptions(actions);
            }
        }
    }
    
    private void updateActionDescriptions(List<Action> actions) {
        StringBuilder descriptions = new StringBuilder();
        for (Action action : actions) {
            descriptions.append(action.getName()).append(": ").append(action.getDescription()).append("\n");
        }
        this.actionDescriptions.set(descriptions.toString());
    }

    /**
     * Performs the specified action.
     * 
     * @param action the action to perform
     */
    public void performAction(Action action) {
    	action.execute(this.gameManager.getPlayer());
        if (action.getNewLocation() != null) {
            this.setCurrentLocation(action.getNewLocation());
        }
        this.updateLocationDescription();
        this.updateAvailableActions();
    }
    
    private void setCurrentLocation(Location newLocation) {
        this.gameManager.setCurrentLocation(newLocation);
        this.updateLocationDescription();
        this.updateAvailableActions();
    }

    private void updateLocationDescription() {
    	this.locationDescription.set(this.gameManager.getCurrentLocation().getDescription());
    }
}
