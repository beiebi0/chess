package org.longjuntan.hw5;

import com.google.gwt.animation.client.Animation;
import com.google.gwt.user.client.ui.Widget;

public class MoveAnimation extends Animation {

	private Widget image;

	public MoveAnimation(Widget image) {
		this.image = image;
	}

	//Fade-in and fade-out animation when move making
	@Override
	protected void onUpdate(double progress) {
		// TODO Auto-generated method stub
		if (progress < 0.25) {
			image.getElement().getStyle().setOpacity(1 - 4 * progress);
		} else if (progress >= 0.5 && progress < 0.75) {
			image.getElement().getStyle().setOpacity(3 - 4 * progress);
		} else if (progress >= 0.25 && progress < 0.5) {
			image.getElement().getStyle().setOpacity(4 * progress - 1);
		} else {
			image.getElement().getStyle().setOpacity(4 * progress - 3);
		}
	}

}
