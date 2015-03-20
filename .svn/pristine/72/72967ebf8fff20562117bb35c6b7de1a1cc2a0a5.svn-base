package org.chenji.hw7;

import org.chenji.hw7.client.*;
import org.shared.chess.State;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Anchor;
import com.googlecode.gwtphonegap.client.PhoneGap;
import com.googlecode.gwtphonegap.client.PhoneGapAvailableEvent;
import com.googlecode.gwtphonegap.client.PhoneGapAvailableHandler;
import com.googlecode.gwtphonegap.client.PhoneGapTimeoutEvent;
import com.googlecode.gwtphonegap.client.PhoneGapTimeoutHandler;
import com.googlecode.gwtphonegap.client.util.PhonegapUtil;

public class ChessEntryPoint implements EntryPoint {
  private ChessConstants constants = GWT.create(ChessConstants.class);
  //private ChessMessages messages = GWT.create(ChessMessages.class);
  private LoginInfo loginInfo = null;
  private VerticalPanel loginPanel = new VerticalPanel();
  private Label loginLabel = new Label(constants.loginInfo());
  private Anchor signInLink = new Anchor(constants.signInLink());
  private Anchor signOutLink = new Anchor(constants.signOutLink());

  @Override
  public void onModuleLoad() {
    // Check login status using login service.
    final GameServiceAsync gameService = GWT.create(GameService.class);
    ((ServiceDefTarget) gameService).setServiceEntryPoint("http://chenji-app3.appspot.com/chenji/game");
    String emails = "test1@gmail.com " + "test2@gmail.com " + "test3@gmail.com ";
    int key = 0;
    gameService.sendEmails(key, emails, new AsyncCallback<Void>(){

      @Override
      public void onFailure(Throwable caught) {
        // TODO Auto-generated method stub
        
      }

      @Override
      public void onSuccess(Void result) {
        // TODO Auto-generated method stub
        
      }});
    
    final LoginServiceAsync loginService = GWT.create(LoginService.class);
    ((ServiceDefTarget) loginService).setServiceEntryPoint("http://chenji-app3.appspot.com/chenji/login");
    loginService.login("http://chenji-app3.appspot.com/chenji.html?key=0", new AsyncCallback<LoginInfo>() {
      public void onFailure(Throwable error) {
      }

      public void onSuccess(LoginInfo result) {
    //LoginInfo result = new LoginInfo();
    //result.setEmailAddress("test1@gmail.com");
    //result.setNickname("test1");
    //result.setLoggedIn(true);
        loginInfo = result;
        if(loginInfo.isLoggedIn()) {

          final PhoneGap phoneGap = GWT.create(PhoneGap.class);

          phoneGap.addHandler(new PhoneGapAvailableHandler() {

            @Override
            public void onPhoneGapAvailable(PhoneGapAvailableEvent event) {
              //PhonegapUtil.prepareService(service, moduleUrl, relativeServiceUrl);
              //startShowCase(phoneGap);

            }
          });

          phoneGap.addHandler(new PhoneGapTimeoutHandler() {

            @Override
            public void onPhoneGapTimeout(PhoneGapTimeoutEvent event) {
              Window.alert("can not load phonegap");

            }
          });

          phoneGap.initializePhoneGap();
          
          final Presenter presenter = new Presenter();
          final Graphics graphics = new Graphics(presenter, loginInfo);
          presenter.setView(graphics);
          State state;
          if(History.getToken().isEmpty()) {
            state = new State();
          } else {
            state = presenter.getStateFromString(History.getToken());
          }            
          presenter.setState(state);
          graphics.setService(gameService);
          graphics.connect();
          signOutLink.setHref(loginInfo.getLogoutUrl());
          RootPanel.get().add(signOutLink);
          RootPanel.get().add(graphics);
        } else {
          loadLogin();
        }
      }  //*/
    });  //*/
  }

  private void loadLogin() {
    // Assemble login panel.
    signInLink.setHref(loginInfo.getLoginUrl());
    loginPanel.add(loginLabel);
    loginPanel.add(signInLink);
    RootPanel.get().add(loginPanel);
  }
}
