package com.simplesoft.simplesofttemplate.main.controller;

public enum RequestAction{
	GET_LIST_APP("Get_list_app");
	
	private String rquestName;
	private RequestAction(String rquestName){
		this.rquestName = rquestName;
	}
	
	/**
	 * @return the rquestName
	 */
	public String getRquestName() {
		return rquestName;
	}
}