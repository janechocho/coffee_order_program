package dto;

public class MenuDto {

	private String coffee_name;
	private int coffee_short;
	private int coffee_tall;
	private int coffee_grande;
	
	public MenuDto() {
	}

	public MenuDto(String coffee_name, int coffee_short, int coffee_tall, int coffee_grande) {
		super();
		this.coffee_name = coffee_name;
		this.coffee_short = coffee_short;
		this.coffee_tall = coffee_tall;
		this.coffee_grande = coffee_grande;
	}

	public String getCoffee_name() {
		return coffee_name;
	}

	public void setCoffee_name(String coffee_name) {
		this.coffee_name = coffee_name;
	}

	public int getCoffee_short() {
		return coffee_short;
	}

	public void setCoffee_short(int coffee_short) {
		this.coffee_short = coffee_short;
	}

	public int getCoffee_tall() {
		return coffee_tall;
	}

	public void setCoffee_tall(int coffee_tall) {
		this.coffee_tall = coffee_tall;
	}

	public int getCoffee_grande() {
		return coffee_grande;
	}

	public void setCoffee_grande(int coffee_grande) {
		this.coffee_grande = coffee_grande;
	}

	@Override
	public String toString() {
		return "MenuDto [coffee_name=" + coffee_name + ", coffee_short=" + coffee_short + ", coffee_tall=" + coffee_tall
				+ ", coffee_grande=" + coffee_grande + "]";
	}
	
	
	
}
