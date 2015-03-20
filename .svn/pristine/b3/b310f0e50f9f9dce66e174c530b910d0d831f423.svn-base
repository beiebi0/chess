package org.shihweihuang.hw8;

import com.google.gwt.i18n.client.Messages;
import com.google.gwt.i18n.client.LocalizableResource.DefaultLocale;
import com.google.gwt.i18n.client.LocalizableResource.Generate;
//
@Generate(format = "com.google.gwt.i18n.rebind.format.PropertiesFormat")
@DefaultLocale("tw")
public interface MyMessage extends Messages{
	
	@DefaultMessage("English")
	String language();
	
	@DefaultMessage("刪除對戰")
	String removeMatch();
	
	@DefaultMessage("對戰已刪除")
	String matchRemoved();
	
	@DefaultMessage("對戰")
	String match();
	
	@DefaultMessage("自動尋找對手")
	String autoMatchStart();
	
	@DefaultMessage("停止搜尋")
	String autoMatchStop();
	
	@DefaultMessage("輸入對手的email")
	String enterEmail();
	
	@DefaultMessage("新的對戰：{0}")
	String newMatch(String matchID);
	
	@DefaultMessage("新的移動在：{0}")
	String newMoveOn(String matchID);
	
	@DefaultMessage("{0}贏了：{1}!")
	String gameResult(String winner, String reason);
	
	@DefaultMessage("請選擇一場對戰")
	String selectMatch();
	
	@DefaultMessage("連上伺服器")
	String connectToSever();
	
	@DefaultMessage("登出")
	String signOut();
	
	@DefaultMessage("登入")
	String signIn();
	
	@DefaultMessage("搜尋中.....")
	String searching();
	
	@DefaultMessage("從{0}開始")
	String startOn(String date);
	
	@DefaultMessage("輪到你")
	@AlternateMessage({
    "WHITE", "輪到你(白色)",
    "BLACK", "輪到你(黑色)",
    })
	String yourTurn(@Select String color);
	
	@DefaultMessage("輪到對手")
	@AlternateMessage({
    "WHITE", "輪到對手(白色)",
    "BLACK", "輪到對手(黑色)",
    })
	String opponentTurn(@Select String color);

	@DefaultMessage("單人遊戲")
	String singlePlayer();
	
}
