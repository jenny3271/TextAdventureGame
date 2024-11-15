package edu.westga.cs3211.text_adventure_game.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import edu.westga.cs3211.text_adventure_game.model.Action;
import edu.westga.cs3211.text_adventure_game.model.GameManager;
import edu.westga.cs3211.text_adventure_game.viewmodel.GameViewModel;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

class TestGameViewModel {

	private GameViewModel viewModel;
    private GameManager gameManager;

    @BeforeEach
    public void setUp() {
        this.viewModel = new GameViewModel();
        this.gameManager = new GameManager();
    }

    @Test
    public void testConstructorInitializesProperties() {
        assertNotNull(this.viewModel.locationDescriptionProperty());
        assertNotNull(this.viewModel.actionDescriptionsProperty());
        assertNotNull(this.viewModel.getAvailableActions());
    }
    
    @Test
    public void testLocationDescriptionProperty() {
        StringProperty locationDescription = this.viewModel.locationDescriptionProperty();
        assertEquals(this.gameManager.getCurrentLocation().getDescription(), locationDescription.get());
    }

    @Test
    public void testActionDescriptionsProperty() {
        StringProperty actionDescriptions = this.viewModel.actionDescriptionsProperty();
        assertNotNull(actionDescriptions.get());
    }

    @Test
    public void testGetAvailableActions() {
        ObservableList<Action> availableActions = this.viewModel.getAvailableActions();
        assertNotNull(availableActions);
        assertTrue(availableActions.size() > 0);
    }
    
    @Test
    public void testPerformAction() {
    	Action action = this.gameManager.getCurrentLocation().getAvailableActions().get(0);
        this.viewModel.performAction(action);
        assertEquals("A dense forest with towering trees.", this.viewModel.locationDescriptionProperty().get());
    }
}
