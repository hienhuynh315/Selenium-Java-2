package dashboard.model;

public class AddNewPageData {

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getParrentPage() {
		return parrentPage;
	}

	public void setParrentPage(String parrentPage) {
		this.parrentPage = parrentPage;
	}

	public String getNumberOfCol() {
		return numberOfCol;
	}

	public void setNumberOfCol(String numberOfCol) {
		this.numberOfCol = numberOfCol;
	}

	public String getIsPublic() {
		return isPublic;
	}

	public void setIsPublic(String isPublic) {
		this.isPublic = isPublic;
	}

	// define some thing here
	private String pageName;
	private String parrentPage;
	private String numberOfCol;
	public String isPublic;
}
