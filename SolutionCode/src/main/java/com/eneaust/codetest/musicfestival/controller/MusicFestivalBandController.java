package com.eneaust.codetest.musicfestival.controller;

import java.io.IOException;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.eneaust.codetest.musicfestival.dto.MusicFesRecordLabelResponseDto;
import com.eneaust.codetest.musicfestival.dto.ResponseWrapperDto;
import com.eneaust.codetest.musicfestival.service.MusicFestivalBandService;
import com.eneaust.codetest.musicfestival.util.MusicFestivalAppConstant;

@RestController
public class MusicFestivalBandController {
	Logger logger = LoggerFactory.getLogger(MusicFestivalBandController.class);
	@Autowired
	MusicFestivalBandService musicFestivalBandService;
	@Value("${notFound.errormsg}")
	String notFoundErrorMsg;
	@Value("${toomany.req.errormsg}")
	String toomanyRequestErrorMsg;
	
	@Value("${service.notavail.errormsg}")
	String serverErrorMsg;
	
	@GetMapping(path = "/musicFestival/recordLabelInfo", produces = MediaType.APPLICATION_JSON_VALUE)
	/**
	 * This method return json string as rest API
	 * @return   Set<MusicFesRecordLabelResponseDto>
	 */
	public Set<MusicFesRecordLabelResponseDto> getRecordLabelList() {
		try {
			ResponseWrapperDto incomingRestResWrapperDto = musicFestivalBandService.getRecordLabelInformation();
			Set<MusicFesRecordLabelResponseDto> musicFesRecordLabelResponseDtoSet = null;
			musicFesRecordLabelResponseDtoSet = incomingRestResWrapperDto.getResponseSet();
			if ((CollectionUtils.isNotEmpty(musicFesRecordLabelResponseDtoSet))
					&& (incomingRestResWrapperDto.getStatusCode().equals(MusicFestivalAppConstant.STATUS_OK))) {
				logger.info("getRecodLabelList musicFesRecordLabelResponseDtoSet Size =>{}",
						musicFesRecordLabelResponseDtoSet.size());
				return musicFesRecordLabelResponseDtoSet;
			} else if ((incomingRestResWrapperDto.getStatusCode().equals(MusicFestivalAppConstant.STATUS_NOT_OK))
					&& (incomingRestResWrapperDto.getErrorCode()
							.equals(MusicFestivalAppConstant.TOO_MANY_REQUESTS_CODE))) {
				throw new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS,
						incomingRestResWrapperDto.getErrorDesc() + toomanyRequestErrorMsg);
			} else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, notFoundErrorMsg);
			}
		} catch (IOException e) {
			throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, serverErrorMsg);
		}

	}

	@GetMapping(path = "/musicFestival/recordLabelInfostr")
	/**
	 * This method  return string output to display in the web page
	 * @return
	 */
	public String getRecordLabelListStr() {

		try {
			ResponseWrapperDto incomingRestResWrapperDto = musicFestivalBandService.getRecordLabelInformation();
			Set<MusicFesRecordLabelResponseDto> musicFesRecordLabelResponseDtoSet = null;
			musicFesRecordLabelResponseDtoSet = incomingRestResWrapperDto.getResponseSet();
			if ((CollectionUtils.isNotEmpty(musicFesRecordLabelResponseDtoSet))
					&& (incomingRestResWrapperDto.getStatusCode().equals(MusicFestivalAppConstant.STATUS_OK))) {
				logger.info("getRecodLabelList musicFesRecordLabelResponseDtoSet Size =>{}",musicFesRecordLabelResponseDtoSet.size());			
				return musicFestivalBandService.getFormatedOutputStr(musicFesRecordLabelResponseDtoSet);
			} else if ((incomingRestResWrapperDto.getStatusCode().equals(MusicFestivalAppConstant.STATUS_NOT_OK))
					&& (incomingRestResWrapperDto.getErrorCode()
							.equals(MusicFestivalAppConstant.TOO_MANY_REQUESTS_CODE))) {
				throw new ResponseStatusException(HttpStatus.TOO_MANY_REQUESTS,
						incomingRestResWrapperDto.getErrorDesc() + toomanyRequestErrorMsg);
			} else {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, notFoundErrorMsg);
			}
		} catch (IOException e) {
			throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, serverErrorMsg);
		}
	
	}

}
