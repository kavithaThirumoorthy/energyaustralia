package com.eneaust.codetest.musicfestival.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.eneaust.codetest.musicfestival.dto.IncomingRestResMusicFestDto;
import com.eneaust.codetest.musicfestival.dto.MusicFesBandResponseDto;
import com.eneaust.codetest.musicfestival.dto.MusicFesRecordLabelResponseDto;
import com.eneaust.codetest.musicfestival.dto.ResponseWrapperDto;
import com.eneaust.codetest.musicfestival.service.MusicFestivalBandService;
import com.eneaust.codetest.musicfestival.util.MusicFestivalAppConstant;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


public class MusicFestivalBandControllerTest {

	@Mock
	MusicFestivalBandService musicFestivalBandService;

	private MockMvc mockMvc;
	@InjectMocks
	private MusicFestivalBandController musicFestivalBandControllerTest;
	List<IncomingRestResMusicFestDto> incomingRestResMusicFestDtoList =null;
	SortedSet<MusicFesRecordLabelResponseDto> responseSet=null;
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(musicFestivalBandControllerTest).build();
		
		String jsonStrInputFromRest="[{\"name\":\"LOL-palooza\",\"bands\":[{\"name\":\"Frank Jupiter\",\"recordLabel\":\"Pacific Records\"},{\"name\":\"Werewolf Weekday\",\"recordLabel\":\"XS Recordings\"},{\"name\":\"Jill Black\",\"recordLabel\":\"Fourth Woman Records\"},{\"name\":\"Winter Primates\",\"recordLabel\":\"\"}]},{\"name\":\"Small Night In\",\"bands\":[{\"name\":\"The Black Dashes\",\"recordLabel\":\"Fourth Woman Records\"},{\"name\":\"Wild Antelope\",\"recordLabel\":\"Marner Sis. Recording\"},{\"name\":\"Green Mild Cold Capsicum\",\"recordLabel\":\"Marner Sis. Recording\"},{\"name\":\"Yanke East\",\"recordLabel\":\"MEDIOCRE Music\"},{\"name\":\"Squint-281\",\"recordLabel\":\"Outerscope\"}]},{\"name\":\"Trainerella\",\"bands\":[{\"name\":\"Wild Antelope\",\"recordLabel\":\"Still Bottom Records\"},{\"name\":\"Manish Ditch\",\"recordLabel\":\"ACR\"},{\"name\":\"Adrian Venti\",\"recordLabel\":\"Monocracy Records\"},{\"name\":\"YOUKRANE\",\"recordLabel\":\"Anti Records\"}]},{\"name\":\"Twisted Tour\",\"bands\":[{\"name\":\"Summon\",\"recordLabel\":\"Outerscope\"},{\"name\":\"Auditones\",\"recordLabel\":\"Marner Sis. Recording\"},{\"name\":\"Squint-281\"}]},{\"bands\":[{\"name\":\"Propeller\",\"recordLabel\":\"Pacific Records\"},{\"name\":\"Critter Girls\",\"recordLabel\":\"ACR\"}]}]";
		ObjectMapper mapper = new ObjectMapper();
		try {
		 incomingRestResMusicFestDtoList = mapper.reader()
					.forType(new TypeReference<List<IncomingRestResMusicFestDto>>() {
					}).readValue(jsonStrInputFromRest);
			 responseSet = new TreeSet<>(
					Comparator.comparing(MusicFesRecordLabelResponseDto::getRecordLabel, String.CASE_INSENSITIVE_ORDER));
			 MusicFesBandResponseDto musicFesBandResponseDto= new MusicFesBandResponseDto();
			 musicFesBandResponseDto.setBandName("Adrian Venti");
			 musicFesBandResponseDto.getFestivalName().add("Trainerella");
			 musicFesBandResponseDto.getFestivalName().add("Twisted Tour");
			 musicFesBandResponseDto.getFestivalName().add("LOL-palooz");
			 MusicFesBandResponseDto musicFesBandResponseDto1= new MusicFesBandResponseDto();
			 musicFesBandResponseDto1.setBandName("Jill Black");
			 musicFesBandResponseDto1.getFestivalName().add("Trainerella");
			 musicFesBandResponseDto1.getFestivalName().add("Small Night In");
			 musicFesBandResponseDto1.getFestivalName().add("LOL-palooz");
			 SortedSet<MusicFesBandResponseDto> musicFesBandResponseDtoList = new TreeSet<>(Comparator.comparing(MusicFesBandResponseDto::getBandName, String.CASE_INSENSITIVE_ORDER));
			 musicFesBandResponseDtoList.add(musicFesBandResponseDto);
			 musicFesBandResponseDtoList.add(musicFesBandResponseDto1);
			 MusicFesRecordLabelResponseDto musicFesRecordLabelResponseDto= new MusicFesRecordLabelResponseDto();
			 musicFesRecordLabelResponseDto.setMusicFesBandResponseDtoList(musicFesBandResponseDtoList);
			 musicFesRecordLabelResponseDto.setRecordLabel("Marner Sis. Recording");
			 responseSet.add(musicFesRecordLabelResponseDto);
			 MusicFesBandResponseDto musicFesBandResponseDto2= new MusicFesBandResponseDto();
			 musicFesBandResponseDto2.setBandName("nAdrian Venti");
			 musicFesBandResponseDto2.getFestivalName().add("lTrainerella");
			 musicFesBandResponseDto2.getFestivalName().add("kTwisted Tour");
			 musicFesBandResponseDto2.getFestivalName().add("uLOL-palooz");
			 MusicFesBandResponseDto musicFesBandResponseDto3= new MusicFesBandResponseDto();
			 musicFesBandResponseDto3.setBandName("tJill Black");
			 musicFesBandResponseDto3.getFestivalName().add("hTrainerella");
			 musicFesBandResponseDto3.getFestivalName().add("rSmall Night In");
			 musicFesBandResponseDto3.getFestivalName().add("xLOL-palooz");
			 SortedSet<MusicFesBandResponseDto> musicFesBandResponseDtoList1 = new TreeSet<>(Comparator.comparing(MusicFesBandResponseDto::getBandName, String.CASE_INSENSITIVE_ORDER));
			 musicFesBandResponseDtoList1.add(musicFesBandResponseDto2);
			 musicFesBandResponseDtoList1.add(musicFesBandResponseDto3);
			 MusicFesRecordLabelResponseDto MusicFesRecordLabelResponseDto1= new MusicFesRecordLabelResponseDto();
			 MusicFesRecordLabelResponseDto1.setMusicFesBandResponseDtoList(musicFesBandResponseDtoList);
			 MusicFesRecordLabelResponseDto1.setRecordLabel("Fourth Woman Records");
			 responseSet.add(MusicFesRecordLabelResponseDto1);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@Test
	/**
	 * this test case tests Not found response returned when the data is not in DB for the method getAllCryptoCurrency method in CryptoCurrencyController
	 * @throws Exception
	 */
	public void testGetRecordLabelList_shouldReturnNotFoundWhenStatusNotOK() throws Exception {
		ResponseWrapperDto incomingRestResWrapperDto = new ResponseWrapperDto();
		incomingRestResWrapperDto.setStatusCode(MusicFestivalAppConstant.STATUS_NOT_OK);
		incomingRestResWrapperDto.setErrorCode("");
		when(musicFestivalBandService.getRecordLabelInformation()).thenReturn(incomingRestResWrapperDto);		 
        mockMvc.perform(get("/musicFestival/recordLabelInfo"))
                .andExpect(status().isNotFound());
        verify(musicFestivalBandService, times(1)).getRecordLabelInformation();
        
	}
	@Test
	public void testGetRecordLabelList_shouldReturnTooManyRequestWhenStatusNotOK429Error() throws Exception {
		ResponseWrapperDto incomingRestResWrapperDto = new ResponseWrapperDto();
		incomingRestResWrapperDto.setStatusCode(MusicFestivalAppConstant.STATUS_NOT_OK);
		incomingRestResWrapperDto.setErrorCode(MusicFestivalAppConstant.TOO_MANY_REQUESTS_CODE);
		when(musicFestivalBandService.getRecordLabelInformation()).thenReturn(incomingRestResWrapperDto);		 
        mockMvc.perform(get("/musicFestival/recordLabelInfo"))
                .andExpect(status().isTooManyRequests());
        verify(musicFestivalBandService, times(1)).getRecordLabelInformation();
        
	}
	@Test
	public void testgetRecordLabelListStr_shouldReturnNotFoundWhenStatusNotOK() throws Exception {
		ResponseWrapperDto incomingRestResWrapperDto = new ResponseWrapperDto();
		incomingRestResWrapperDto.setStatusCode(MusicFestivalAppConstant.STATUS_NOT_OK);
		incomingRestResWrapperDto.setErrorCode("");
		when(musicFestivalBandService.getRecordLabelInformation()).thenReturn(incomingRestResWrapperDto);		 
        mockMvc.perform(get("/musicFestival/recordLabelInfostr"))
                .andExpect(status().isNotFound());
        verify(musicFestivalBandService, times(1)).getRecordLabelInformation();
        
	}
	@Test
	public void testGetRecordLabelListStr_shouldReturnTooManyRequestWhenStatusNotOK429Error() throws Exception {
		ResponseWrapperDto incomingRestResWrapperDto = new ResponseWrapperDto();
		incomingRestResWrapperDto.setStatusCode(MusicFestivalAppConstant.STATUS_NOT_OK);
		incomingRestResWrapperDto.setErrorCode(MusicFestivalAppConstant.TOO_MANY_REQUESTS_CODE);
		when(musicFestivalBandService.getRecordLabelInformation()).thenReturn(incomingRestResWrapperDto);		 
        mockMvc.perform(get("/musicFestival/recordLabelInfostr"))
                .andExpect(status().isTooManyRequests());
        verify(musicFestivalBandService, times(1)).getRecordLabelInformation();
        
	}
	@Test
	public void testGetRecordLabelList_shouldReturn200WhenStatusOKNonEmptyList() throws Exception {
		ResponseWrapperDto incomingRestResWrapperDto = new ResponseWrapperDto();
		incomingRestResWrapperDto.setIncomingRestResMusicFestList(incomingRestResMusicFestDtoList);
		incomingRestResWrapperDto.setResponseSet(responseSet);
		incomingRestResWrapperDto.setStatusCode(MusicFestivalAppConstant.STATUS_OK);
				when(musicFestivalBandService.getRecordLabelInformation()).thenReturn(incomingRestResWrapperDto);		 
        mockMvc.perform(get("/musicFestival/recordLabelInfo"))
                .andExpect(status().isOk()) .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].recordLabel", is("Marner Sis. Recording")))
                .andExpect(jsonPath("$[0].recordLabel", is("Fourth Woman Records")));
        verify(musicFestivalBandService, times(1)).getRecordLabelInformation();
        
	}
	@Test
	public void testGetRecordLabelListStr_shouldReturn200WhenStatusOKNonEmptyList() throws Exception {
		ResponseWrapperDto incomingRestResWrapperDto = new ResponseWrapperDto();
		incomingRestResWrapperDto.setIncomingRestResMusicFestList(incomingRestResMusicFestDtoList);
		incomingRestResWrapperDto.setStatusCode(MusicFestivalAppConstant.STATUS_OK);
		incomingRestResWrapperDto.setResponseSet(responseSet);
				when(musicFestivalBandService.getRecordLabelInformation()).thenReturn(incomingRestResWrapperDto);		 
        mockMvc.perform(get("/musicFestival/recordLabelInfostr"))
                .andExpect(status().isOk());
        verify(musicFestivalBandService, times(1)).getRecordLabelInformation();
        
	}
	
	
}

