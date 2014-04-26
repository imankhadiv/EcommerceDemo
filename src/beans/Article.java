package beans;

public class Article {
	private int id;
	private String userId;
	private int review_count;
	private String title;
	private String abst;
	private String keywords;
	private String pdfPath;
	
	public Article(String title,String abst,String userId,String keywords,String pdfPath) {
		this.title = title;
		this.abst = abst;
		this.userId = userId;
		this.keywords = keywords;
		this.pdfPath = pdfPath;
	}

	public int getId() {
		return id;
	}

	public String getUserId() {
		return userId;
	}

	public int getReview_count() {
		return review_count;
	}

	public String getTitle() {
		return title;
	}

	public String getAbst() {
		return abst;
	}

	public String getKeywords() {
		return keywords;
	}

	public String getPdfPath() {
		return pdfPath;
	}
	
	
	
}

