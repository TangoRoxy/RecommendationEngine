/** Customer.java  
* Michael, Katherine, Constantine
*/ 
public class Customer {
	public String name;
	public String psw;
	public String rating;
	public String answer1;
	public String answer2;
	public String answer3;
	
	public Customer (String name, String psw, String rating, String answer1, String answer2, String answer3){
		this.name = name;
		this.psw = psw;
		this.rating = rating;
		this.answer1 = answer1;
		this.answer2 = answer2;
		this.answer3 = answer3;
	}
	
	public Customer (String name){         //for removing purpose
		this.name = name;
		this.psw = "";
		this.rating = "";
		this.answer1 = "";
		this.answer2 = "";
		this.answer3 = "";
	}
	public void setPsw (String psw){
		this.psw = psw;
	}
	public void setRating (String rating){
		this.rating = rating;
	}
	
	public boolean equals(Customer cust){
		return this.name.equalsIgnoreCase(cust.name);
	}
}
