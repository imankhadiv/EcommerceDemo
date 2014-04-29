package beans;

import java.util.List;

public class ReviewForm {
	private int id;
	private int articleId;
	private int autherId;
	private int reviewerId;
	private String overall ;
	private String level;
	private String summary;
	private String secrete;
	private String formApproved;
	private String articleApproved;
	private List<Comment> commentList;
	private List<Error> errors;
	private List<Reason> reasons;

	public ReviewForm()
	{
		
	}
	public ReviewForm(int articleId, int authorId, int reviewerId, String overall, String level,String summary, String secrete, List<Comment> commentList,List<Error> errors)
	{
		this.articleId = articleId;
		this.autherId = authorId;
		this.reviewerId = reviewerId;
		this.overall = overall;
		this.level = level;
		this.summary = summary;
		this.secrete = secrete;
		this.commentList = commentList;
		this.errors = errors;
	}
	
	
	public int getArticleId() {
		return articleId;
	}
	public void setArticleId(int articleId) {
		this.articleId = articleId;
	}
	public int getAutherId() {
		return autherId;
	}
	public void setAutherId(int autherId) {
		this.autherId = autherId;
	}
	public int getReviewerId() {
		return reviewerId;
	}
	public void setReviewerId(int reviewerId) {
		this.reviewerId = reviewerId;
	}
	public String getOverall() {
		return overall;
	}
	public void setOverall(String overall) {
		this.overall = overall;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getSecrete() {
		return secrete;
	}
	public void setSecrete(String secrete) {
		this.secrete = secrete;
	}
	public List<Comment> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}
	public List<Error> getErrors() {
		return errors;
	}
	public void setErrors(List<Error> errors) {
		this.errors = errors;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFormApproved() {
		return formApproved;
	}
	public void setFormApproved(String formApproved) {
		this.formApproved = formApproved;
	}
	public String getArticleApproved() {
		return articleApproved;
	}
	public void setArticleApproved(String articleApproved) {
		this.articleApproved = articleApproved;
	}
	public List<Reason> getReasons() {
		return reasons;
	}
	public void setReasons(List<Reason> reasons) {
		this.reasons = reasons;
	}
	
	
}
