package todoList;

import java.time.LocalDate;

public class Todo {
	
	private Long id;
	private String title;
	private String userName;
	private String description;
	private LocalDate targetDate;
	private boolean status;
	
	protected Todo() {
		
	}

	public Todo(Long id, String title, String userName, String description, LocalDate targetDate, boolean status) {
		super();
		this.id = id;
		this.title = title;
		this.userName = userName;
		this.description = description;
		this.targetDate = targetDate;
		this.status = status;
	}

	public Todo(String title, String userName, String description, LocalDate targetDate, boolean status) {
		super();
		this.title = title;
		this.userName = userName;
		this.description = description;
		this.targetDate = targetDate;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(LocalDate targetDate) {
		this.targetDate = targetDate;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public boolean getStatus() {
		return status;
	}
	
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int)(id ^ (id >>> 32));
		return result;
	}
	
	public boolean equals (Object obj) {
		
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Todo other = (Todo) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}
	
	

}
