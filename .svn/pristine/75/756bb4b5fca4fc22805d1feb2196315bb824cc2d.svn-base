package org.bohouli.hw6.client;

import java.util.ArrayList;
import java.util.Date;

import org.bohouli.hw6.UserInformation;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("service")
public interface BohouChessService extends RemoteService {
	public UserInformation login(String requestUri);

	public void sendState(final String matchKey, final String state, final String winner);
	
	public void makeAutoMatch(final String state);
	
	public void makeSelectMatch(final String otherEmail, final String state);
	
	public void getState(String matchKey);
	
	public void loadMatches();
	
	public void deleteMatch(String matchKey);
	
	public Date getDate(String matchKey);
	
	public void sendContacts(long key, ArrayList<String> emails);
}