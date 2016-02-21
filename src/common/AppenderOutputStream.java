package common;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class AppenderOutputStream extends ObjectOutputStream {

	public AppenderOutputStream(OutputStream os) throws IOException {
		super(os);
	}

	protected void writeStreamHeader() throws IOException {
		reset();
	}
}
