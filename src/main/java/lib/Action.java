package lib;

import lib.enums.OperationEnum;

import java.io.Serializable;

public class Action implements Serializable {
	private OperationEnum type;
	private Object target;


	public Action(OperationEnum type) {
		this.type = type;
	}

	public Action(OperationEnum name, Object target) {
		this.type = name;
		this.target = target;
	}

	public OperationEnum getType() {
		return type;
	}

	public void setType(OperationEnum type) {
		this.type = type;
	}

	public Object getTarget() {
		return target;
	}

	public void setTarget(Object target) {
		this.target = target;
	}

	@Override
	public String toString() {
		return "Action { " + "type=" + type + ", target=" + target + " }";
	}
}
