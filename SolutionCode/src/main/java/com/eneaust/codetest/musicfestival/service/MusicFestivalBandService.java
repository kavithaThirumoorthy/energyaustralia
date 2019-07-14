package com.eneaust.codetest.musicfestival.service;

import java.io.IOException;
import java.util.Set;

import com.eneaust.codetest.musicfestival.dto.MusicFesRecordLabelResponseDto;
import com.eneaust.codetest.musicfestival.dto.ResponseWrapperDto;

public interface MusicFestivalBandService {

	public ResponseWrapperDto getRecordLabelInformation() throws IOException;

	public String getFormatedOutputStr(Set<MusicFesRecordLabelResponseDto> musicFesRecordLabelResponseDtoSet);

}
