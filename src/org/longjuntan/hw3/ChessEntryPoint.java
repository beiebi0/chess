package org.longjuntan.hw3;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.longjuntan.hw6.client.GameService;
import org.longjuntan.hw6.client.GameServiceAsync;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.googlecode.gwtphonegap.client.PhoneGap;
import com.googlecode.gwtphonegap.client.PhoneGapAvailableEvent;
import com.googlecode.gwtphonegap.client.PhoneGapAvailableHandler;
import com.googlecode.gwtphonegap.client.PhoneGapTimeoutEvent;
import com.googlecode.gwtphonegap.client.PhoneGapTimeoutHandler;
import com.googlecode.gwtphonegap.client.contacts.Contact;
import com.googlecode.gwtphonegap.client.contacts.ContactError;
import com.googlecode.gwtphonegap.client.contacts.ContactField;
import com.googlecode.gwtphonegap.client.contacts.ContactFindCallback;
import com.googlecode.gwtphonegap.client.contacts.ContactFindOptions;
import com.googlecode.gwtphonegap.client.util.PhonegapUtil;
import com.googlecode.gwtphonegap.collection.shared.CollectionFactory;
import com.googlecode.gwtphonegap.collection.shared.LightArray;

public class ChessEntryPoint implements EntryPoint {
	private Presenter presenter;

	private List<String> oracle = new ArrayList<String>();

	private Logger log = Logger.getLogger(getClass().getName());

	final PhoneGap phoneGap = GWT.create(PhoneGap.class);

	final GameServiceAsync service = GWT.create(GameService.class);

	@Override
	public void onModuleLoad() {

		phoneGap.addHandler(new PhoneGapAvailableHandler() {

			@Override
			public void onPhoneGapAvailable(PhoneGapAvailableEvent event) {
				LightArray<String> fields = CollectionFactory
						.<String> constructArray();
				fields.push("emails"); // search in email
				ContactFindOptions findOptions = new ContactFindOptions("@gmail.com", true);

				phoneGap.getContacts().find(fields, new ContactFindCallback() {

					@Override
					public void onSuccess(LightArray<Contact> contacts) {
						for (int i = 0; i < contacts.length(); i++) {
							Contact contact = contacts.get(i);
							LightArray<ContactField> emails = contact
									.getEmails();
							for (int j = 0; j < emails.length(); j++) {
								String email = emails.get(j).getValue();
								if (email.length() > 9
										&& email.substring(email.length() - 10)
												.equals("@gmail.com"))
									oracle.add(email);
							}
						}
					}

					@Override
					public void onFailure(ContactError error) {
					
					}
				}, findOptions);
				startLoadPage(oracle);
			}
		});

		phoneGap.addHandler(new PhoneGapTimeoutHandler() {

			@Override
			public void onPhoneGapTimeout(PhoneGapTimeoutEvent event) {
				Window.alert("can not load phonegap");
			}
		});
		phoneGap.initializePhoneGap();

	}

	public void startLoadPage(final List<String> list) {
		((ServiceDefTarget) service)
				.setServiceEntryPoint("http://chessgamebylongjuntan-hw11.appspot.com/longjuntan/move");
		PhonegapUtil.prepareService((ServiceDefTarget) service,
				"http://chessgamebylongjuntan-hw11.appspot.com/longjuntan/",
				"move");

		final TextBox myEmail = new TextBox();
		Button save = new Button("Login");
		RootPanel.get().add(myEmail);
		RootPanel.get().add(save);
		save.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get().clear();
				final Graphics graphics = new Graphics(list);
				presenter = new Presenter();
				graphics.setPresenter(presenter);

				presenter.setView(graphics);
				presenter.setEmail(myEmail.getText());

				RootPanel.get().add(graphics);
				presenter.init();

			}
		});

	}
}