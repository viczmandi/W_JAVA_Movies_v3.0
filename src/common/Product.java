package common;

public abstract class Product {
	
	private String id;
	private String title;
	private Person person;
	
	
	public Product(String title, Person person) {
		this.title = title;
		this.person = person;
	}
	
	public String getTitle() {
		return title;
	}
	public Person getPerson() {
		return person;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
	public abstract long getInvestment();
	
}
