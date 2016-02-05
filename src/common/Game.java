package common;

import java.util.ArrayList;
import java.util.List;

public class Game extends Product implements Buyable{
	
	private boolean preOrdered;
	private List<Person> staff = new ArrayList<Person>();
	private int price;
	
	public Game(String title, Person person, boolean preOrdered, List<Person> staff, int price) {
		super(title, person);
		this.preOrdered = preOrdered;
		this.staff = staff;
		this.price = price;
	}
	public boolean isPreOrdered() {
		return preOrdered;
	}
	public void setPreOrdered(boolean preOrdered) {
		this.preOrdered = preOrdered;
	}
	public List<Person> getStaff() {
		return staff;
	}
	public void setStaff(List<Person> staff) {
		this.staff = staff;
	}
	public int getPrice() {
		if (preOrdered) {
			return (int) (price * 0.8);
		}else{
			return price;
		}
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	public long getInvestment(){
		
		long sumSalary = 0;
		
		for (Person person : staff) {
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
		sb.append("\nPreorder?: ");
		sb.append(preOrdered);
		sb.append("\nStaff: ");
		sb.append(staff);
		sb.append("\nPrice: ");
		sb.append(price);
		return sb.toString();
	}
}
