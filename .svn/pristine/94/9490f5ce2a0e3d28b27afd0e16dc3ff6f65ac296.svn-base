package org.alexanderoskotsky.hw10;

import com.allen_sauer.gwt.dnd.client.DragContext;
import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;

public class PieceDragController extends PickupDragController {

	public PieceDragController(AbsolutePanel boundaryPanel, boolean allowDroppingOnBoundaryPanel) {
		super(boundaryPanel, allowDroppingOnBoundaryPanel);
	}
	
	@Override
	public Widget newDragProxy(DragContext ctx) {
		Image src = (Image) ctx.draggable;
		Image img = new Image(src.getUrl());
		return img;
	}
}
