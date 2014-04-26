package beans;

import java.util.List;

public class ReviewForm {
	private int article_id;
	
	private int auther_id;
	private int reviewer_id;
	private String overall ;
	private String level;
	private String summary;
	private String secrete;
	private List<Comment> commentList;
	private List<Error> errors;

	public ReviewForm()
	{
		
	}
	public ReviewForm(int _article_id, int _auther_id, int _reviewer_id, String _overall, String _level,String _summary, String _secrete, List<Comment> _commentList,List<Error> _errors)
	{
		article_id = _article_id;
		auther_id = _auther_id;
		reviewer_id = _reviewer_id;
		overall = _overall;
		level = _level;
		summary = _summary;
		secrete = _secrete;
		commentList = _commentList;
		errors = _errors;
	}
	
	public int getArticle_id() {
		return article_id;
	}
	public void setArticle_id(int article_id) {
		this.article_id = article_id;
	}
	public int getAuther_id() {
		return auther_id;
	}
	public void setAuther_id(int auther_id) {
		this.auther_id = auther_id;
	}
	public int getReviewer_id() {
		return reviewer_id;
	}
	public void setReviewer_id(int reviewer_id) {
		this.reviewer_id = reviewer_id;
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
}
