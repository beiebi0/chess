package org.ashishmanral.hw11;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class SuggestionBox {
	
	private MultiWordSuggestOracle oracle;
	
	public SuggestionBox(){
		this.oracle = new MultiWordSuggestOracle();
	}
	
	public void displayBox(){

		VerticalPanel panel = new VerticalPanel();
	    SuggestBox suggestbox = new SuggestBox(oracle);
	    panel.add(new Label("Opponent's Email Suggestion Box"));
	    panel.add(suggestbox);
	    panel.addStyleName("demo-panel-padded");
	    RootPanel.get().add(panel);
	    
	}
	
	public void populateSuggestionBox(String email){
		oracle.add(email);
	}
}
