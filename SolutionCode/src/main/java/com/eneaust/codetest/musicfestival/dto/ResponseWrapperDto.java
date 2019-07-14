package com.eneaust.codetest.musicfestival.dto;

import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class ResponseWrapperDto {
	

	private String errorDesc;
	private String statusCode;
	private String errorCode;
	private List<IncomingRestResMusicFestDto> incomingRestResMusicFestList;
	private SortedSet<MusicFesRecordLabelResponseDto> responseSet;
	private String responseJsonStr;
	/**
	 * @return the incomingRestResMusicFestList
	 */
	public List<IncomingRestResMusicFestDto> getIncomingRestResMusicFestList() {
		return incomingRestResMusicFestList;
	}
	/**
	 * @param incomingRestResMusicFestList the incomingRestResMusicFestList to set
	 */
	public void setIncomingRestResMusicFestList(List<IncomingRestResMusicFestDto> incomingRestResMusicFestList) {
		this.incomingRestResMusicFestList = incomingRestResMusicFestList;
	}
	
	/**
	 * @return the errorDesc
	 */
	public String getErrorDesc() {
		return errorDesc;
	}
	/**
	 * @param errorDesc the errorDesc to set
	 */
	public void setErrorDesc(String errorDesc) {
		this.errorDesc = errorDesc;
	}
	/**
	 * @return the statusCode
	 */
	public String getStatusCode() {
		return statusCode;
	}
	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	/**
	 * @return the responseSet
	 */
	public SortedSet<MusicFesRecordLabelResponseDto> getResponseSet() {
		return responseSet;
	}
	/**
	 * @param responseSet the responseSet to set
	 */
	public void setResponseSet(SortedSet<MusicFesRecordLabelResponseDto> responseSet) {
		this.responseSet = responseSet;
	}
	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}
	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	/**
	 * @return the responseJsonStr
	 */
	public String getResponseJsonStr() {
		return responseJsonStr;
	}
	/**
	 * @param responseJsonStr the responseJsonStr to set
	 */
	public void setResponseJsonStr(String responseJsonStr) {
		this.responseJsonStr = responseJsonStr;
	}
	
}
