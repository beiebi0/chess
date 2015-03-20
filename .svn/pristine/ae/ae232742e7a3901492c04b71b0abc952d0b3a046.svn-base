package org.markanderson.hw6.client;

import java.util.List;

import org.markanderson.hw6.helper.ManderChessGameSessionInfo;
import org.markanderson.hw6.helper.ManderChessUserSessionInfo;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface ManderChessServiceAsync {
	void updateStateForMove(String clientID, String stateStr, AsyncCallback<Void> callback);
	void sendInvitationToUser(String emailTo, AsyncCallback<ManderChessGameSessionInfo> callback);
	void createUserChannel(AsyncCallback<ManderChessUserSessionInfo> callback);
	void retrieveMatchesForCurrentPlayer(AsyncCallback<List<ManderChessGameSessionInfo>> callback);
	void loadGameFromDropDown(String textStr, AsyncCallback<String> callback);
}
