package common;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class RentManager {
	// help from Knapecz Ádám
	public static void send(ObjectOutputStream oos, Object object) throws IOException {
		oos.write(0);
		oos.writeObject(object);
	}

	public static int getBuyablesIncome(List<Buyable> buyables) {

		int incomeSum = 0;
		for (Buyable buyable : buyables) {
			incomeSum += buyable.getPrice();
		}
		return incomeSum;

	}

	public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException {

		// Game staff
		Person person1 = new Person("Game1", "Dev1", Gender.MALE, 897046);
		Person person2 = new Person("Game2", "Dev2", Gender.MALE, 847675);
		Person person9 = new Person("Game3", "Dev3", Gender.FEMALE, 888052);
		Person person10 = new Person("Game4", "Dev4", Gender.FEMALE, 665159);

		// Movie1 cast
		Person person3 = new Person("Scarlett", "Johansson", Gender.FEMALE, 767140);
		Person person4 = new Person("Elizabeth", "Olsen", Gender.FEMALE, 712642);

		// Movie2 cast
		Person person5 = new Person("Christian", "Bale", Gender.MALE, 768916);
		Person person6 = new Person("Heath", "Ledger", Gender.MALE, 759184);

		// Book authors
		Person person7 = new Person("J. K.", "Rowling", Gender.FEMALE, 652475);
		Person person8 = new Person("George R. R.", "Martin", Gender.MALE, 690034);

		//////////////////////////////////////

		// Game1 staff
		List<Person> people1 = new ArrayList<Person>();
		people1.add(person1);
		people1.add(person2);

		// Movie1 cast
		List<Person> people2 = new ArrayList<Person>();
		people2.add(person3);
		people2.add(person4);

		// Movie2 cast
		List<Person> people3 = new ArrayList<Person>();
		people3.add(person5);
		people3.add(person6);

		// Game2 staff
		List<Person> people4 = new ArrayList<Person>();
		people4.add(person9);
		people4.add(person10);

		//////////////////////////////////////

		Product movie1 = new Movie("Avengers: Age of Ultron", person5, Genre.ACTION, 142, 7.6, people2, 10);
		Product movie2 = new Movie("The Dark Knight", person3, Genre.ACTION, 152, 9.0, people3, 20);

		//////////////////////////////////////

		Product book1 = new Book("Harry Potter and the Philosopher's Stone", person8, person7);
		Product book2 = new Book("Game of Thrones", person7, person8);

		//////////////////////////////////////

		Product game1 = new Game("WoW", person9, true, people1, 74);
		Product game2 = new Game("CSGO", person10, false, people4, 91);

		//////////////////////////////////////

		Socket socket = new Socket("localhost", ObjectServer.PORT);
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

		send(oos, Command.PUT);
		send(oos, game1);
		send(oos, book1);
		send(oos, movie1);
		send(oos, person1);
		send(oos, Command.GET);
		send(oos, Command.EXIT);

		Object fromServer = ois.readObject();
		List<Object> ofs = (List<Object>) fromServer;
		for (Object object : ofs) {
			System.out.println(object);
		}

		socket.close();

	}
}
