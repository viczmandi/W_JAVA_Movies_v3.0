package common;

import java.io.Serializable;

public abstract class Product implements Serializable{
	
	private String id;
	private String title;
	private Person person;
	
	
	public Product(String title, Person person) {
		id = IdGenerator.generate(this);
		this.title = title;
		this.person = person;
	}
	
	public String getTitle() {
		return title;
	}
	public Person getPerson() {
		return person;
	}
	
	
	public String getId() {
		return id;
	}
	
	public abstract long getInvestment();
	
}
