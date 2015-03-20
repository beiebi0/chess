package org.chenji.hw3;

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
    
    History.addValueChangeHandler(new ValueChangeHandler<String>() {
      public void onValueChange(ValueChangeEvent<String> event) {
        String historyToken = event.getValue();

        // Parse the history token
        try {
          if (historyToken.substring(0, 4).equals("page")) {
            String tabIndexToken = historyToken.substring(4);
            int index = Integer.parseInt(tabIndexToken);
            System.out.println(index);
            // Select the specified tab panel
            presenter.setStateAt(index);
          } else {
            presenter.setStateAt(0);
          }
        } catch (IndexOutOfBoundsException e) {
          presenter.setStateAt(0);
        }
      }
    });
    
    RootPanel.get().add(graphics);
  }
}
