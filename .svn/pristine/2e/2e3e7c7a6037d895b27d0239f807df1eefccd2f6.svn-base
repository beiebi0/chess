package org.markanderson.hw3;

import org.markanderson.hw6.client.ManderLoginService;
import org.markanderson.hw6.client.ManderLoginServiceAsync;
import org.markanderson.hw6.helper.ManderChessUserSessionInfo;
import org.markanderson.hw8.ManderLocalizedStrings;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.googlecode.gwtphonegap.client.PhoneGap;
import com.googlecode.gwtphonegap.collection.shared.CollectionFactory;
import com.googlecode.gwtphonegap.collection.shared.LightArray;

public class ChessEntryPoint implements EntryPoint {
//	final PhoneGap phoneGap = GWT.create(PhoneGap.class);

	ManderLocalizedStrings messages = (ManderLocalizedStrings) GWT
			.create(ManderLocalizedStrings.class);

	private ManderChessUserSessionInfo userInfo = null;
	private VerticalPanel loginPanel = new VerticalPanel();
	private Label loginLabel = new Label(messages.pleaseSignInString());
	private Label greeting;
	private Anchor signInLink = new Anchor(messages.signInString());
	private Anchor signOutLink = new Anchor(messages.signOutString());

	// TODO: need to add a random string, attach it to the contacts, redirect,
	// and then grab the contacts ffrom that
//	private String stringKeyXxx;
//	public LightArray<String> fields = CollectionFactory.<String> constructArray();
//	public VerticalPanel panel = new VerticalPanel();

	public void onModuleLoad() {
//		ManderChessServiceAsync service = GWT.create(ManderChessService.class);
//		((ServiceDefTarget) service).setServiceEntryPoint("http://manderchess.appspot.com/markanderson/login");
//		PhonegapUtil.prepareService((ServiceDefTarget) service, "http://manderchess.appspot.com/markanderson/", "login");
//		Random rand = new Random();
//		Integer rInt = rand.nextInt(10000);
//		setStringKeyXxx(rInt.toString());
		
//		Window.Location.assign("http://manderchess.appspot.com/markanderson.html?key="+getStringKeyXxx());
		// make RPC call to store this at the end of the thing, as a key for gmail contacts
//		phoneGap.addHandler(new PhoneGapAvailableHandler() {
//
//			@Override
//			public void onPhoneGapAvailable(PhoneGapAvailableEvent event) {
//				fields.push("emails"); //search in emails 
//				ContactFindOptions findOptions = new ContactFindOptions("<string to search>", true);
//
//				phoneGap.getContacts().find(fields, new ContactFindCallback() {
//
//			        @Override
//			        public void onSuccess(LightArray<Contact> contacts) {
//			            //read contacts here...stack overflow if we save into local storage...
////			    		Storage storage = Storage.getLocalStorageIfSupported();
////			    		if (storage != null) {
////			    			StorageMap map = new StorageMap(storage);
////			    			map.put("contacts", contacts.toString());
////			    		}
//			    		
//			        	MultiWordSuggestOracle oracle = new MultiWordSuggestOracle();
//			        	
//			        	for (int i = 0; i < contacts.length(); i++) {
//			        		String [] gmailStr = contacts.get(i).getEmails().get(i).getValue().split("@");
//			        		if (gmailStr[1].contains("gmail.com")) {
//			        			// we have a gmail contact, so put it in the oracle
//			        			oracle.add(contacts.get(i).getEmails().get(i).getValue());
//			        		}
//			        	}
//			            
//			        	SuggestBox suggestBox = new SuggestBox(oracle);
//			        	panel.add(suggestBox);
//			        	RootPanel.get().add(panel);
//						Button but = new Button();
//						panel.add(but);
//						but.setText("Login");
//						but.addClickHandler(new ClickHandler() {
//							
//							@Override
//							public void onClick(ClickEvent event) {
//								loadFakeGame();
//							}
//						});
//			        }
//
//			        @Override
//			        public void onFailure(ContactError error) {
//			                //something went wrong, doh!
//
//			        }
//			}, findOptions);
//				// start your app - phonegap is ready
//				ManderLoginServiceAsync loginService = GWT
//						.create(ManderLoginService.class);
////				loginService.login(GWT.getHostPageBaseURL(),
//				loginService.login("http://manderchess.appspot.com/markanderson.html?key="+getStringKeyXxx(),
//
//						new AsyncCallback<ManderChessUserSessionInfo>() {
//							public void onFailure(Throwable error) {
//							}
//
//							public void onSuccess(ManderChessUserSessionInfo result) {
//								userInfo = result;
////								if (userInfo.isLoggedIn()) {
////									loadManderChess();
////								} else {
////									loadUserLogin();
////								}
//							}
//						});
//			}
//		});
//				
//		phoneGap.addHandler(new PhoneGapTimeoutHandler() {
//
//			@Override
//			public void onPhoneGapTimeout(PhoneGapTimeoutEvent event) {
//				// can not start phonegap - something is for with your setup
//
//			}
//		});
//		phoneGap.initializePhoneGap();

		ManderLoginServiceAsync loginService = GWT
				.create(ManderLoginService.class);
		loginService.login(GWT.getHostPageBaseURL(),
				new AsyncCallback<ManderChessUserSessionInfo>() {
					public void onFailure(Throwable error) {
					}

					public void onSuccess(ManderChessUserSessionInfo result) {
						userInfo = result;
						if (userInfo.isLoggedIn()) {
							loadManderChess();
						} else {
							loadUserLogin();
						}
					}
				});
	}

	public void loadFakeGame() {
		final Graphics graphics = new Graphics();
		RootPanel.get().add(graphics);		
	}
	
	public void loadUserLogin() {
		// Assemble login panel.
		signInLink.setHref(userInfo.getLoginUrl());
		loginPanel.add(loginLabel);
		loginPanel.add(signInLink);
		RootPanel.get().add(loginPanel);
	}

	public void loadManderChess() {
		final Graphics graphics = new Graphics();
		signOutLink.setHref(userInfo.getLogoutUrl());
		greeting = new Label(messages.helloString() + userInfo.getNickname()
				+ messages.getReadyToPlayString());
		RootPanel.get().add(greeting);
		RootPanel.get().add(signOutLink);
		RootPanel.get().add(graphics);
	}

//	public String getStringKeyXxx() {
//		return stringKeyXxx;
//	}
//
//	public void setStringKeyXxx(String stringKeyXxx) {
//		this.stringKeyXxx = stringKeyXxx;
//	}
}