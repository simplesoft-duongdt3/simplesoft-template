package com.simplesoft.simplesofttemplate.constance;

public enum PermissionGroup {
	COST_MONEY("COST_MONEY"),
	;

	String name;
	private PermissionGroup(String pName) {
		this.name = pName;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
}
