package org.longjuntan.hw6.client;

import java.util.List;

import org.longjuntan.hw8.PageInfo;
import org.longjuntan.hw8.PlayerInfo;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface GameServiceAsync {

	public void sendMove(String email, String sessionID, String move,String state,
			AsyncCallback<String> callback);

	void join(String email, AsyncCallback<PlayerInfo> callback);

	void load(String email, String matchId, AsyncCallback<PageInfo> callback);

	void createMatch(String email, String opponent,
			AsyncCallback<PageInfo> callback);

	void loadMatchList(String email, AsyncCallback<List<String>> callback);

	void deleteMatch(String email, String matchId,
//			AsyncCallback<List<String>> callback);
			AsyncCallback<Void> callback);

	void callAIPlayer(String matchId, String state,
			AsyncCallback<String> callback);

}