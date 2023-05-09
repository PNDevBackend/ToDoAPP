package MODEL;


import java.time.LocalDate;

public class ToDo {
	private String title;
	private LocalDate targetDate;
	private boolean status;
	private Long id;
	private String userName;
	private String description;
	public ToDo(long id2, String title2, String username2, String description2, LocalDate targetDate2,
			boolean isStatus) {
		// TODO Auto-generated constructor stub
	}
	public ToDo(String title2, String username2, String description2, LocalDate now, boolean isDone) {
		// TODO Auto-generated constructor stub
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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

}
