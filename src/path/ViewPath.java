package path;

import java.util.Arrays;
import java.util.List;

public class ViewPath {
	private int viewPathNo; // 1, 2, 3
	private int user_choice_no;
	private List<ViewLocation> location;

	public ViewPath(int viewPathNo, int user_choice_no, List<ViewLocation> location) {
		super();
		this.viewPathNo = viewPathNo;
		this.user_choice_no = user_choice_no;
		this.location = location;
	}

	public int getViewPathNo() {
		return viewPathNo;
	}

	public void setViewPathNo(int viewPathNo) {
		this.viewPathNo = viewPathNo;
	}

	public int getUser_choice_no() {
		return user_choice_no;
	}

	public void setUser_choice_no(int user_choice_no) {
		this.user_choice_no = user_choice_no;
	}

	public List<ViewLocation> getLocation() {
		return location;
	}

	public void setLocation(List<ViewLocation> location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "ViewPath [viewPathNo=" + viewPathNo + ", user_choice_no=" + user_choice_no + ", location=" + location
				+ "]";
	}

}
