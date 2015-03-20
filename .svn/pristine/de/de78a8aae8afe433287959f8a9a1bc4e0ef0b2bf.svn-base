package org.simongellis.hw10;

import java.util.Set;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.MultiWordSuggestOracle;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.Widget;

public class OptionsPopup extends PopupPanel {
	private static OptionsPanelUiBinder uiBinder = GWT.create(OptionsPanelUiBinder.class);

	interface OptionsPanelUiBinder extends UiBinder<Widget, OptionsPopup> {
	}

	@UiField
	Button aiButton;
	@UiField(provided = true)
	SuggestBox emailBox;
	@UiField
	Button emailButton;
	@UiField
	Button autoButton;
	@UiField
	Button deleteButton;
	@UiField
	Button closeButton;
	
	MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();

	public OptionsPopup() {
		emailBox = new SuggestBox(oracle);
		setWidget(uiBinder.createAndBindUi(this));
		setAutoHideEnabled(false);
		setModal(true);
		setGlassEnabled(true);
	}

	public String getText() {
		return emailBox.getText();
	}

	public void setText(String text) {
		emailBox.setText(text);
	}

	public HasClickHandlers getAIButton() {
		return aiButton;
	}

	public HasClickHandlers getEmailButton() {
		return emailButton;
	}

	public HasClickHandlers getAutoButton() {
		return autoButton;
	}

	public HasClickHandlers getDeleteButton() {
		return deleteButton;
	}

	public HasClickHandlers getCloseButton() {
		return closeButton;
	}

	public void addContacts(Set<String> emails) {
		oracle.addAll(emails);
	}
}
