package common;

public class Book extends Product {
	
	private Person author;

	public Person getAuthor() {
		return author;
	}

	public void setAuthor(Person author) {
		this.author = author;
	}
	
	public long getInvestment(){
		return author.getSalary();
	}

	public Book(String title, Person person, Person author) {
		super(title, person);
		this.author = author;
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
