package org.longjuntan.hw6.client;

import java.util.List;

import org.longjuntan.hw8.PageInfo;
import org.longjuntan.hw8.PlayerInfo;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("move")
public interface GameService extends RemoteService {
        public PlayerInfo join(String email);
        public PageInfo load(String email,String matchId);
        String sendMove(String email, String sessionID, String move,String state);
        public PageInfo createMatch(String email, String opponent);

        public List<String> loadMatchList(String email);
//        public List<String> deleteMatch(String email, String matchId);
        public void deleteMatch(String email, String matchId);
        public String callAIPlayer(String matchId,String state);
}