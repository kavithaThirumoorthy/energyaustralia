package com.eneaust.codetest.musicfestival.service;

import java.io.IOException;

import com.eneaust.codetest.musicfestival.dto.ResponseWrapperDto;

public interface MusicRestApiClientService {

	public ResponseWrapperDto getFestivalBandInfo() throws IOException;

}
