package com.eneaust.codetest.musicfestival.dto;

import java.util.SortedSet;
import java.util.TreeSet;

public class  MusicFesBandResponseDto {
	private String bandName;
	private  SortedSet<String> festivalName = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
	/**
	 * @return the bandName
	 */
	public String getBandName() {
		return bandName;
	}
	/**
	 * @param bandName the bandName to set
	 */
	public void setBandName(String bandName) {
		this.bandName = bandName;
	}
	/**
	 * @return the festivalName
	 */
	public SortedSet<String> getFestivalName() {
		return festivalName;
	}
	/**
	 * @param festivalName the festivalName to set
	 */
	public void setFestivalName(SortedSet<String> festivalName) {
		this.festivalName = festivalName;
	}

}

