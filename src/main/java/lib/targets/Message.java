package lib.targets;

public class Message extends AbstractTarget {

	private User user;
	private String body;

	public Message(User user, String body) {
		this.user = user;
		this.body = body;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "Message { " + "body='" + body + '\'' + " }";
	}
}
