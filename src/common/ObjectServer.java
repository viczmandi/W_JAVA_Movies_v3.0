package common;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ObjectServer {

	final static int PORT = 1234;
	private ServerMode mode;
	private static String fileName = "rentmanager_object.ser";
	private static File file = new File(fileName);

	public ServerMode getMode() {
		return mode;
	}

	public void setMode(ServerMode mode) {
		this.mode = mode;
	}

	public static List<Object> load() {

		FileInputStream inFile;
		ObjectInputStream inStream = null;
		Object tempObject;

		List<Object> objectList = new ArrayList<Object>();

		try {
			inFile = new FileInputStream(fileName);
			inStream = new ObjectInputStream(inFile);

			while (true) {
				tempObject = (Object) inStream.readObject();
				objectList.add(tempObject);
			}
		} catch (FileNotFoundException ex) {
			System.out.println("File named " + fileName + " not found.\n");
		} catch (EOFException ex) {
			try {
				System.out.println(objectList.size() + " object(s) read from file.\n");
				inStream.close();
			} catch (IOException eexx) {
			}
		} catch (IOException ex) {
			System.out.println(ex + "\n");
		} catch (ClassNotFoundException ex) {
			System.out.println(ex + "\n");
		}
		return objectList;
	}

	public static void save(Object inputObject) {
		try {
			AppenderOutputStream out = new AppenderOutputStream(new FileOutputStream(file, true));
			out.writeObject(inputObject);
			out.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void run() throws IOException, ClassNotFoundException {
		System.out.println("Server running on port: " + PORT);
		ServerSocket ss = new ServerSocket(PORT);
		Socket socket = ss.accept();
		if (!file.exists()) {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
			oos.close();
		}
		ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());

		ObjectServer os = new ObjectServer();

		while (true) {
			if (ois.read() > -1) { // help from Knapecz Ádám
				Object object = ois.readObject();
				if (object instanceof Command) {
					if (object.equals(Command.EXIT)) {
						System.out.println("Server: shut down");
						break;
					}
					if (object.equals(Command.PUT)) {
						os.setMode(ServerMode.SAVE);
						System.out.println("Mode set to: " + os.getMode());
					}
					if (object.equals(Command.GET)) {
						os.setMode(ServerMode.LOAD);
						System.out.println("Mode set to: " + os.getMode());
						oos.writeObject(load());
					}
				}
				if (object instanceof Product || object instanceof Person) {
					if (os.getMode().equals(ServerMode.SAVE)) {
						save(object);
						System.out.println("Object stored to " + fileName);
					}
				}
			}
		}
	}

	public static void main(String[] args) throws ClassNotFoundException, IOException {
		ObjectServer.run();
	}
}