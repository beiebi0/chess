package org.markanderson.hw6.client;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.markanderson.hw6.helper.ManderChessGameSessionInfo;
import org.markanderson.hw6.helper.ManderChessUserSessionInfo;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("service")
public interface ManderChessService extends RemoteService {
	public void updateStateForMove(String clientID, String stateStr);
	public ManderChessGameSessionInfo sendInvitationToUser(String emailTo) throws UnsupportedEncodingException;
	public ManderChessUserSessionInfo createUserChannel();
	public List<ManderChessGameSessionInfo> retrieveMatchesForCurrentPlayer();
	public String loadGameFromDropDown(String textStr);
}
