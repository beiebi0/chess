package org.chenji.hw5;

import org.shared.chess.State;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;

public class ChessEntryPoint implements EntryPoint {
  @Override
  public void onModuleLoad() {
    final Presenter presenter = new Presenter();
    final Graphics graphics = new Graphics(presenter);
    presenter.setView(graphics);
    
    State state;
    if(History.getToken().isEmpty()) {
      state = new State();
    } else {
      state = presenter.getStateFromString(History.getToken());
    }
      
    presenter.setState(state);

    RootPanel.get().add(graphics);
  }
}
