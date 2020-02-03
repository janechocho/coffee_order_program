package dto;

public class OrderDto {
	private int seq;
	private String id;			//로그인id
	private String coffee_name;	//커피이름
	private String coffee_size; 	//커피사이즈
	private int cup;			//총 잔수
	private String coffee_syrup;	//커피시럽
	private int whipping;	//휘핑추가여부
	private int shot;		//샷추가여부
	private int total;			//총 금액
	private String order_date;	//주문일
	
	public OrderDto() {
	}

	public OrderDto(int seq, String id, String coffee_name, String coffee_size, int cup, String coffee_syrup,
			int whipping, int shot, int total, String order_date) {
		super();
		this.seq = seq;
		this.id = id;
		this.coffee_name = coffee_name;
		this.coffee_size = coffee_size;
		this.cup = cup;
		this.coffee_syrup = coffee_syrup;
		this.whipping = whipping;
		this.shot = shot;
		this.total = total;
		this.order_date = order_date;
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCoffee_name() {
		return coffee_name;
	}

	public void setCoffee_name(String coffee_name) {
		this.coffee_name = coffee_name;
	}

	public String getCoffee_size() {
		return coffee_size;
	}

	public void setCoffee_size(String coffee_size) {
		this.coffee_size = coffee_size;
	}

	public int getCup() {
		return cup;
	}

	public void setCup(int cup) {
		this.cup = cup;
	}

	public String getCoffee_syrup() {
		return coffee_syrup;
	}

	public void setCoffee_syrup(String coffee_syrup) {
		this.coffee_syrup = coffee_syrup;
	}

	public int getWhipping() {
		return whipping;
	}

	public void setWhipping(int whipping) {
		this.whipping = whipping;
	}

	public int getShot() {
		return shot;
	}

	public void setShot(int shot) {
		this.shot = shot;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public String getOrder_date() {
		return order_date;
	}

	public void setOrder_date(String order_date) {
		this.order_date = order_date;
	}

	@Override
	public String toString() {
		return "OrderDto [seq=" + seq + ", id=" + id + ", coffee_name=" + coffee_name + ", coffee_size=" + coffee_size
				+ ", cup=" + cup + ", coffee_syrup=" + coffee_syrup + ", whipping=" + whipping + ", shot=" + shot
				+ ", total=" + total + ", order_date=" + order_date + "]";
	}
	
	
	


	
}
