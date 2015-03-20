package org.kuangchelee.hw10;

import org.kuangchelee.hw5.Presenter;

import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.AbsolutePanel;

public class DragController extends PickupDragController{
	Presenter presenter;
	public DragController(AbsolutePanel AP, boolean allowDroppingOnBoundaryPanel, Presenter presenter){
		super(AP, allowDroppingOnBoundaryPanel);
		this.presenter = presenter;
	}
	@Override
	public void dragStart(){
		//super.dragStart();
		int row = (context.draggable.getAbsoluteTop() - 232) / 52;
		int col = (context.draggable.getAbsoluteLeft() - 232) / 52;
		//Window.alert("Drag start " + row + ", " + col);
		presenter.dragStartOnBoard(row, col);
	}
	/*
	@Override
	public void dragMove(){
		int row = (context.draggable.getAbsoluteTop() - 232) / 52;
		int col = (context.draggable.getAbsoluteLeft() - 232) / 52;
		//Window.alert("Drag start " + row + ", " + col);
		presenter.dragOverBoard(row, col);
	}
	*/
	
}