package com.eneaust.codetest.musicfestival.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;
import com.eneaust.codetest.musicfestival.dto.IncomingRestResMusicFestDto;
import com.eneaust.codetest.musicfestival.dto.ResponseWrapperDto;
import com.eneaust.codetest.musicfestival.util.MusicFestivalAppConstant;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MusicRestApiClientServiceImpl implements MusicRestApiClientService {
	@Autowired
	RestTemplate restTemplate;
	@Value("${restapi.url}")
	String restApiURL;
	static Logger logger = LoggerFactory.getLogger(MusicRestApiClientServiceImpl.class);

	@Override
	/**
	 * This method gets response from the rest API
	 * http://eacodingtest.digital.energyaustralia.com.au/api/v1/festivals
	 */
	public ResponseWrapperDto getFestivalBandInfo() throws IOException {
		ResponseWrapperDto incomingRestResWrapperDto = getResponseFromRestAPI();
		String responseStr = incomingRestResWrapperDto.getResponseJsonStr();
		// if response string is not empty the proceed with normal flow
		if ((!StringUtils.isEmpty(responseStr) && (responseStr.matches(".*[a-zA-Z]+.*")))) {
			ObjectMapper mapper = new ObjectMapper();
			List<IncomingRestResMusicFestDto> incomingRestResMusicFestDtoList = mapper.reader()
					.forType(new TypeReference<List<IncomingRestResMusicFestDto>>() {
					}).readValue(responseStr);
			incomingRestResWrapperDto.setStatusCode(MusicFestivalAppConstant.STATUS_OK);
			incomingRestResWrapperDto.setIncomingRestResMusicFestList(incomingRestResMusicFestDtoList);
		} else {
			// if response string is empty the proceed with normal flow need to display 404
			if(StringUtils.isEmpty(incomingRestResWrapperDto.getStatusCode())){
			incomingRestResWrapperDto.setStatusCode(MusicFestivalAppConstant.STATUS_NOT_OK);
			incomingRestResWrapperDto.setErrorCode(MusicFestivalAppConstant.STATUS_NOT_OK);}
		}

		return incomingRestResWrapperDto;
	}

	public ResponseWrapperDto getResponseFromRestAPI() {
		ResponseWrapperDto incomingRestResWrapperDto = new ResponseWrapperDto();
		ResponseEntity<String> response = null;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
			HttpEntity<String> entity = new HttpEntity<String>(headers);
			response = restTemplate.exchange(restApiURL, HttpMethod.GET, entity, String.class);
			if ((null!=response)&&(null != response.getStatusCode() && (response.getStatusCode().equals(HttpStatus.OK)))) {
				incomingRestResWrapperDto.setResponseJsonStr(response.getBody());
				incomingRestResWrapperDto.setErrorCode(response.getStatusCode().value()+"");
			}
		} catch (HttpStatusCodeException exception) {
			// handling for '429 Too Many Requests'
			logger.info("exception.getStatusCode().value()=={}" + exception.getStatusCode().value());
			logger.info("exception.getStatusCode().value()=={}" + exception.getMessage());
			incomingRestResWrapperDto.setErrorCode(exception.getRawStatusCode()+ "");
			incomingRestResWrapperDto.setStatusCode(MusicFestivalAppConstant.STATUS_NOT_OK);
			incomingRestResWrapperDto.setErrorDesc(exception.getMessage());
		}
		return incomingRestResWrapperDto;
	}

}
