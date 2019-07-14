package com.eneaust.codetest.musicfestival.dto;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class MusicFesRecordLabelResponseDto{
	private String recordLabel;
	
	private  SortedSet<MusicFesBandResponseDto> musicFesBandResponseDtoList = new TreeSet<>(Comparator.comparing(MusicFesBandResponseDto::getBandName, String.CASE_INSENSITIVE_ORDER));

	/**
	 * @return the musicFesBandResponseDtoList
	 * 
	 */
	public SortedSet<MusicFesBandResponseDto> getMusicFesBandResponseDtoList() {
		return musicFesBandResponseDtoList;
	}

	/**
	 * @param musicFesBandResponseDtoList the musicFesBandResponseDtoList to set
	 */
	public void setMusicFesBandResponseDtoList(SortedSet<MusicFesBandResponseDto> musicFesBandResponseDtoList) {
		this.musicFesBandResponseDtoList = musicFesBandResponseDtoList;
	}

	/**
	 * @return the recordLabel
	 */
	public String getRecordLabel() {
		return recordLabel;
	}

	/**
	 * @param recordLabel the recordLabel to set
	 */
	public void setRecordLabel(String recordLabel) {
		this.recordLabel = recordLabel;
	}

	
	
}
 