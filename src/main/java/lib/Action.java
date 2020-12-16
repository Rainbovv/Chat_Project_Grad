package lib;

import lib.enums.ActionName;

import java.io.Serializable;

public class Action implements Serializable {
	private ActionName name;

	public Action(ActionName name) {
		this.name = name;
	}

	public ActionName getName() {
		return name;
	}

	public void setName(ActionName name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Action{" + "name='" + name + '\'' + '}';
	}
}
