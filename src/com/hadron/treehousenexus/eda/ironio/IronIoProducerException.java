package com.hadron.treehousenexus.eda.ironio;

import java.io.IOException;

import com.hadron.treehousenexus.eda.ProducerException;

public class IronIoProducerException extends ProducerException {

	public IronIoProducerException(IOException e) {
		super(e);
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 5668524212640624499L;

}
