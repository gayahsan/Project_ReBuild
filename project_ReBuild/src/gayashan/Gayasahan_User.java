package gayashan;

public class Gayasahan_User {

	private int id;
	private String name;
	private String email;
	private int phone;
	private String address;
	private String description;
		
	public Gayasahan_User(int id, String name, String email, int phone, String address, String description) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.description = description;
	}
	
	public Gayasahan_User(String name, String email, int phone, String address, String description) {
		super();
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.description = description;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
