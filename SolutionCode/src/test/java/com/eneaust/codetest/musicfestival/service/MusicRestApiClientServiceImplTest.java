package com.eneaust.codetest.musicfestival.service;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.eneaust.codetest.musicfestival.dto.ResponseWrapperDto;
import com.eneaust.codetest.musicfestival.util.MusicFestivalAppConstant;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MusicRestApiClientServiceImplTest {
	@Autowired
	RestTemplate restTemplate;
	@Value("${restapi.url}")
	String restApiURL;
	@Autowired
	MusicRestApiClientServiceImpl musicRestApiClientServiceImpl;


	
	@Test
	public void testGetFestivalBandInfo_shouldReturnStatusNotOKWhenJsonStrEmpty() {

		try {
			ResponseWrapperDto incomingRestResWrapperDto = musicRestApiClientServiceImpl.getFestivalBandInfo();
			if((null !=incomingRestResWrapperDto.getResponseJsonStr())&& (incomingRestResWrapperDto.getResponseJsonStr().length()>5)) {
				assertEquals(MusicFestivalAppConstant.STATUS_OK, incomingRestResWrapperDto.getStatusCode());
			}else {
				assertEquals(MusicFestivalAppConstant.STATUS_NOT_OK, incomingRestResWrapperDto.getStatusCode());
			}
			if (CollectionUtils.isNotEmpty(incomingRestResWrapperDto.getIncomingRestResMusicFestList())) {
				assertEquals(MusicFestivalAppConstant.STATUS_OK, incomingRestResWrapperDto.getStatusCode());
			} else {
				assertEquals(MusicFestivalAppConstant.STATUS_NOT_OK, incomingRestResWrapperDto.getStatusCode());
			}
			  
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}

	}
	
	
}
