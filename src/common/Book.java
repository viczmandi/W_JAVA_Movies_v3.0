package common;

import java.io.Serializable;

public class Book extends Product implements Serializable{
	
	private static final long serialVersionUID = 2776841944041776719L;
	
	private Person author;
	
	public Book(String title, Person person, Person author) {
		super(title, person);
		this.author = author;
	}

	public Person getAuthor() {
		return author;
	}

	public void setAuthor(Person author) {
		this.author = author;
	}
	
	public long getInvestment(){
		return author.getSalary();
	}

	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder();
		sb.append("\nId: ");
		sb.append(getId());
		sb.append("\nTitle: ");
		sb.append(getTitle());
		sb.append("\nPerson: ");
		sb.append(getPerson());
		sb.append("\nAuthor: ");
		sb.append(author);
		
		return sb.toString();
	}
}
