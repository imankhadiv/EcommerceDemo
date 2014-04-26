package beans;

public class Error {
	private String error;

	public Error() {

	}

	public Error(String errorString) {
		error = errorString;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
