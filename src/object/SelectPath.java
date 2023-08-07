package object;

public class SelectPath {
	private int path_no;
	private int user_choice_no;
	private int location1;
	private int location2;
	private int location3;
	private int location4;

	public SelectPath(int path_no, int user_choice_no, int location1, int location2, int location3, int location4) {
		super();
		this.path_no = path_no;
		this.user_choice_no = user_choice_no;
		this.location1 = location1;
		this.location2 = location2;
		this.location3 = location3;
		this.location4 = location4;
	}

	public int getPath_no() {
		return path_no;
	}

	public void setPath_no(int path_no) {
		this.path_no = path_no;
	}

	public int getUser_choice_no() {
		return user_choice_no;
	}

	public void setUser_choice_no(int user_choice_no) {
		this.user_choice_no = user_choice_no;
	}

	public int getLocation1() {
		return location1;
	}

	public void setLocation1(int location1) {
		this.location1 = location1;
	}

	public int getLocation2() {
		return location2;
	}

	public void setLocation2(int location2) {
		this.location2 = location2;
	}

	public int getLocation3() {
		return location3;
	}

	public void setLocation3(int location3) {
		this.location3 = location3;
	}

	public int getLocation4() {
		return location4;
	}

	public void setLocation4(int location4) {
		this.location4 = location4;
	}

}
