package org.kuangchelee.hw10;

import org.kuangchelee.hw5.Presenter;

import com.allen_sauer.gwt.dnd.client.DragContext;
import com.allen_sauer.gwt.dnd.client.drop.SimpleDropController;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.SimplePanel;

public class DropController extends SimpleDropController {

	  private final SimplePanel dropTarget;
	  private Presenter presenter;

	  public DropController(SimplePanel dropTarget, Presenter presenter) {
	    super(dropTarget);
	    this.presenter = presenter; 
	    this.dropTarget = dropTarget;
	  }

	  @Override
	  public void onDrop(DragContext context) {
		  super.onDrop(context);
		  dropTarget.setWidget(context.draggable);
//		  int row = (context.selectedWidgets.get(0).getAbsoluteTop() - 232) / 52;
//		  int col = (context.selectedWidgets.get(0).getAbsoluteLeft() - 232) / 52;
//		  Window.alert("Drop on " + row + ", " + col);
//		  presenter.dragEndOnBoard(row, col);
	  }
	}