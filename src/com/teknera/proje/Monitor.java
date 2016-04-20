package com.teknera.proje;

import java.io.File;
import java.util.TimerTask;

/**
 * @author volkan
 *
 */
public abstract class Monitor extends TimerTask {
	private long timeStamp;
	private File file;

	public Monitor(File file) {
		this.file = file;
		this.timeStamp = file.lastModified();
	}

	public final void run() {
		long timeStamp = file.lastModified();
		if (this.timeStamp != timeStamp) {
			this.timeStamp = timeStamp;
			onChange(file);
		}
	}

	protected abstract void onChange(File file);
}