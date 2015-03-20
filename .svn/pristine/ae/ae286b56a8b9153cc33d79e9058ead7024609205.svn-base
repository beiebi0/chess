package org.alexanderoskotsky.hw10;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.alexanderoskotsky.hw3.Presenter;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * An AsyncCallback with default implementations of the onFailure and onSuccess
 * methods
 * 
 * @param <T>
 */
public class SimpleAsyncCallback<T> implements AsyncCallback<T> {
	private Logger logger = Logger.getLogger(Presenter.class.toString());

	@Override
	public void onFailure(Throwable caught) {
		logger.log(Level.SEVERE, "ajax request error", caught);
	}

	@Override
	public void onSuccess(T result) {
		logger.info(result.toString());
	}

}
