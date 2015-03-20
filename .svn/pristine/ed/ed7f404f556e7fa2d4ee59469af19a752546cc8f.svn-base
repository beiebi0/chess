package org.chenji.hw6;

import org.chenji.hw6.client.*;
import org.shared.chess.State;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Anchor;

public class ChessEntryPoint implements EntryPoint {
  private LoginInfo loginInfo = null;
  private VerticalPanel loginPanel = new VerticalPanel();
  private Label loginLabel = new Label(
      "Please sign in to your Google Account to access the Chess application.");
  private Anchor signInLink = new Anchor("Sign In");
  private Anchor signOutLink = new Anchor("Sign Out");

  @Override
  public void onModuleLoad() {
    // Check login status using login service.
    LoginServiceAsync loginService = GWT.create(LoginService.class);
    loginService.login(Window.Location.getHref(),
        new AsyncCallback<LoginInfo>() {
          public void onFailure(Throwable error) {
          }

          public void onSuccess(LoginInfo result) {
            loginInfo = result;
            if (loginInfo.isLoggedIn()) {
              final Presenter presenter = new Presenter();
              final Graphics graphics = new Graphics(presenter);
              presenter.setView(graphics);
              State state;
              if (History.getToken().isEmpty()) {
                state = new State();
              } else {
                state = presenter.getStateFromString(History.getToken());
              }
              presenter.setState(state);
              graphics.connect(loginInfo);
              //RootPanel.get().add(signOutLink);
              RootPanel.get().add(graphics);
            } else {
              loadLogin();
            }
          }
        });
  }

  private void loadLogin() {
    // Assemble login panel.
    signInLink.setHref(loginInfo.getLoginUrl());
    loginPanel.add(loginLabel);
    loginPanel.add(signInLink);
    RootPanel.get().add(loginPanel);
  }
}
