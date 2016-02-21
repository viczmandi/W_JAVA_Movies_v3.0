package common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Movie extends Product implements Buyable, Serializable{
	
	private static final long serialVersionUID = -7378125090148511833L;
	
	private Genre genre;
	private int duration;
	private double rate;
	private List<Person> cast = new ArrayList<Person>();
	private int price;
	
	public Movie(String title, Person person, Genre genre, int duration, double rate, List<Person> cast, int price) {
		super(title, person);
		this.genre = genre;
		this.duration = duration;
		this.rate = rate;
		this.cast = cast;
		this.price = price;
	}
	
	public Genre getGenre() {
		return genre;
	}
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public List<Person> getCast() {
		return cast;
	}
	public void setCast(List<Person> cast) {
		this.cast = cast;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public long getInvestment(){
		
		long sumSalary = 0;
		
		for (Person person : cast) {
			sumSalary += person.getSalary();
		}
		
		return sumSalary;
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
		sb.append("\nGenre: ");
		sb.append(genre);
		sb.append("\nDuration: ");
		sb.append(duration);
		sb.append("\nRate: ");
		sb.append(rate);
		sb.append("\nCast: ");
		sb.append(cast);
		sb.append("\nPrice: ");
		sb.append(price);
		return sb.toString();
	}
}
