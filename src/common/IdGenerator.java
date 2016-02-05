package common;

public class IdGenerator {
	
	public static String generate(Product product){
		
		//Knapecz Ádám nyomán
		long time = System.nanoTime();
		StringBuilder sb = new StringBuilder();
		
		if (product instanceof Movie) {
			sb.append("MOV");
			sb.append(time);
		} else if(product instanceof Book){
			sb.append("BOO");
			sb.append(time);
		} else if(product instanceof Game){
			sb.append("GAM");
			sb.append(time);
		}
		
		return sb.toString();
	}
}
