package org.bohouli.hw6.client;

import java.util.ArrayList;
import java.util.Date;

import org.bohouli.hw6.UserInformation;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface BohouChessServiceAsync {
	public void login(String requestUri, AsyncCallback<UserInformation> async);

	public void sendState(final String matchKey, final String state, final String winner,
			AsyncCallback<Void> callback);
	
	public void makeAutoMatch(final String state, AsyncCallback<Void> callback);
	
	public void makeSelectMatch(final String otherEmail, final String state,
			AsyncCallback<Void> callback);
	
	public void getState(String matchKey, AsyncCallback<Void> callback);
	
	public void loadMatches(AsyncCallback<Void> callback);
	
	public void deleteMatch(String matchKey, AsyncCallback<Void> callback);
	
	public void getDate(String matchKey, AsyncCallback<Date> async);
	
	public void sendContacts(long key, ArrayList<String> emails, AsyncCallback<Void> async);
}
