package org.simongellis.hw6.client;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

public class MatchmakingPopup extends PopupPanel {
	
	private static MatchmakingPopupUiBinder uiBinder = GWT.create(MatchmakingPopupUiBinder.class);
	interface MatchmakingPopupUiBinder extends UiBinder<Widget, MatchmakingPopup> {
	}
	
	@UiField
	Button cancelButton;
	
	public MatchmakingPopup() {
		setWidget(uiBinder.createAndBindUi(this));
		setAutoHideEnabled(false);
		setModal(true);
		setGlassEnabled(true);
	}
	
	public HasClickHandlers getCancelButton() {
		return cancelButton;
	}
}
