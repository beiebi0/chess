package org.simongellis.hw3;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.gwtphonegap.client.PhoneGap;
import com.googlecode.gwtphonegap.client.PhoneGapAvailableEvent;
import com.googlecode.gwtphonegap.client.PhoneGapAvailableHandler;
import com.googlecode.gwtphonegap.client.PhoneGapTimeoutEvent;
import com.googlecode.gwtphonegap.client.PhoneGapTimeoutHandler;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.MGWTSettings;

public class ChessEntryPoint implements EntryPoint {

	private Logger log = Logger.getLogger(getClass().getName());
	
	@Override
	public void onModuleLoad() {
		MGWTSettings settings = MGWTSettings.getAppSetting();
		MGWTSettings.ViewPort viewPort = settings.getViewPort();
		viewPort.setMinimumScale(0.75).setMaximumScale(0.75);
		settings.setViewPort(viewPort);
		settings.setPreventScrolling(true);
		MGWT.applySettings(settings);

		final PhoneGap phoneGap = GWT.create(PhoneGap.class);
		GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {
			@Override
			public void onUncaughtException(Throwable e) {
				Window.alert(e.getLocalizedMessage() + ": " + e.getMessage());
				log.log(Level.SEVERE, "exception " + e);
			}
		});
		phoneGap.addHandler(new PhoneGapAvailableHandler() {
			@Override
			public void onPhoneGapAvailable(PhoneGapAvailableEvent event) {
				onStart(phoneGap);
			}
		});
		phoneGap.addHandler(new PhoneGapTimeoutHandler() {
			@Override
			public void onPhoneGapTimeout(PhoneGapTimeoutEvent event) {
				Window.alert("Phonegap won't loooooooooad");
			}
		});
		
		phoneGap.initializePhoneGap();
	}
	
	private void onStart(PhoneGap phoneGap) {
		final Graphics graphics = new Graphics(phoneGap);
		Presenter presenter = new Presenter();
		presenter.setView(graphics);
		RootPanel.get().add(graphics);
	}

}
