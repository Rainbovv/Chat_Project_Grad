package lib.targets;

public class Message extends AbstractTarget {

	private String body;
	private User from;
	private User to;

	public Message(String body) {
		this.body = body;
	}

	public User getFrom() {
		return from;
	}

	public void setFrom(User from) {
		this.from = from;
	}

	public User getTo() {
		return to;
	}

	public void setTo(User to) {
		this.to = to;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	@Override
	public String toString() {
		return "Message { " + "body='" + body + '\'' + ", from=" + from + ", to=" + to + " }";
	}
}
