package org.yuanjia.hw5;

import org.shared.chess.Move;

import org.shared.chess.State;

import org.yuanjia.hw3.Graphics;
import org.yuanjia.hw3.Presenter;

import com.google.gwt.animation.client.Animation;

import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;

public class MoveAnimation extends Animation {
	private int fromLeft;
	private int fromTop;
	private int toLeft;
	private int toTop;

	private Image image;


	public MoveAnimation(State in_state, Image in_image, int fromRow,
			int fromCol, int toRow, int toCol, Move inmove) {
	
		fromTop = Graphics.gameGrid.getAbsoluteTop() + (7 - fromRow) * 50;
		fromLeft = Graphics.gameGrid.getAbsoluteLeft() + fromCol * 50;
		toTop = Graphics.gameGrid.getAbsoluteTop() + (7 - toRow) * 50;
		toLeft = Graphics.gameGrid.getAbsoluteLeft() + toCol * 50;
		this.image = in_image;
	}

	@Override
	protected void onUpdate(double progress) {
		int top = extractProportionalTop(progress);
		int left = extractProportionalLeft(progress);
		RootPanel.get().add(image, left, top);
	}

	@Override
	protected void onComplete() {

		//History.newItem(StateSerializer.serialize(state));
		image.removeFromParent();

	}

	private int extractProportionalTop(double progress) {
		int outTop = (int) (fromTop + (toTop - fromTop) * progress);
		return outTop;
	}

	private int extractProportionalLeft(double progress) {
		int outLeft = (int) (fromLeft + (toLeft - fromLeft) * progress);
		return outLeft;
	}
}