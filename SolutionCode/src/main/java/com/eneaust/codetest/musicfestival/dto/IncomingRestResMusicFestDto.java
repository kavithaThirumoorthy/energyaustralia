package com.eneaust.codetest.musicfestival.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class IncomingRestResMusicFestDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	private List<IncomingRestMusicBandDto> bands;
	public IncomingRestResMusicFestDto(){}
	public IncomingRestResMusicFestDto(String name,List<IncomingRestMusicBandDto> bands) {
		this.name =name;
		this.bands=bands;
	}
	/**
	 * @return the bands
	 */
	public List<IncomingRestMusicBandDto> getBands() {
		return bands;
	}
	/**
	 * @param bands the bands to set
	 */
	public void setBands(List<IncomingRestMusicBandDto> bands) {
		this.bands = bands;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

}
 