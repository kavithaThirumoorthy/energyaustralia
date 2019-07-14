package com.eneaust.codetest.musicfestival.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.text.ParseException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.eneaust.codetest.musicfestival.dto.IncomingRestResMusicFestDto;
import com.eneaust.codetest.musicfestival.dto.MusicFesBandResponseDto;
import com.eneaust.codetest.musicfestival.dto.MusicFesRecordLabelResponseDto;
import com.eneaust.codetest.musicfestival.dto.ResponseWrapperDto;
import com.eneaust.codetest.musicfestival.util.MusicFestivalAppConstant;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;



public class MusicFestivalBandServiceImplTest {

	
	@Mock
	MusicRestApiClientService musicRestApiClientService;
	@InjectMocks
	private MusicFestivalBandServiceImpl musicFestivalBandServiceImpl;
	List<IncomingRestResMusicFestDto> incomingRestResMusicFestDtoList =null;
	ResponseWrapperDto incomingRestResWrapperDto = null;
	ResponseWrapperDto incomingRestResWrapperDtoEmpty = null;
	SortedSet<MusicFesRecordLabelResponseDto> responseSet=null;
	@Before
	/**
	 * This method takes care of initial set up of data
	 * 
	 */
	public void setup() throws ParseException {
		MockitoAnnotations.initMocks(this);
		String jsonStrInputFromRest="[{\"name\":\"LOL-palooza\",\"bands\":[{\"name\":\"Frank Jupiter\",\"recordLabel\":\"Pacific Records\"},{\"name\":\"Werewolf Weekday\",\"recordLabel\":\"XS Recordings\"},{\"name\":\"Jill Black\",\"recordLabel\":\"Fourth Woman Records\"},{\"name\":\"Winter Primates\",\"recordLabel\":\"\"}]},{\"name\":\"Small Night In\",\"bands\":[{\"name\":\"The Black Dashes\",\"recordLabel\":\"Fourth Woman Records\"},{\"name\":\"Wild Antelope\",\"recordLabel\":\"Marner Sis. Recording\"},{\"name\":\"Green Mild Cold Capsicum\",\"recordLabel\":\"Marner Sis. Recording\"},{\"name\":\"Yanke East\",\"recordLabel\":\"MEDIOCRE Music\"},{\"name\":\"Squint-281\",\"recordLabel\":\"Outerscope\"}]},{\"name\":\"Trainerella\",\"bands\":[{\"name\":\"Wild Antelope\",\"recordLabel\":\"Still Bottom Records\"},{\"name\":\"Manish Ditch\",\"recordLabel\":\"ACR\"},{\"name\":\"Adrian Venti\",\"recordLabel\":\"Monocracy Records\"},{\"name\":\"YOUKRANE\",\"recordLabel\":\"Anti Records\"}]},{\"name\":\"Twisted Tour\",\"bands\":[{\"name\":\"Summon\",\"recordLabel\":\"Outerscope\"},{\"name\":\"Auditones\",\"recordLabel\":\"Marner Sis. Recording\"},{\"name\":\"Squint-281\"}]},{\"bands\":[{\"name\":\"Propeller\",\"recordLabel\":\"Pacific Records\"},{\"name\":\"Critter Girls\",\"recordLabel\":\"ACR\"}]}]";
		ObjectMapper mapper = new ObjectMapper();
		try {
		 incomingRestResMusicFestDtoList = mapper.reader()
					.forType(new TypeReference<List<IncomingRestResMusicFestDto>>() {
					}).readValue(jsonStrInputFromRest);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	incomingRestResWrapperDto = new ResponseWrapperDto();
	incomingRestResWrapperDto.setIncomingRestResMusicFestList(incomingRestResMusicFestDtoList);
	incomingRestResWrapperDto.setStatusCode(MusicFestivalAppConstant.STATUS_OK);
	incomingRestResWrapperDtoEmpty = new ResponseWrapperDto();
	incomingRestResWrapperDtoEmpty.setStatusCode(MusicFestivalAppConstant.STATUS_NOT_OK);
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
	}
	
	@Test
	public void testGetRecordLabelInformation_shouldReturnResponseSetWhenAPIReturnNonEmptyStr() throws IOException {
		when(musicRestApiClientService.getFestivalBandInfo()).thenReturn(incomingRestResWrapperDto);		
		incomingRestResWrapperDto= musicFestivalBandServiceImpl.getRecordLabelInformation();
		assertEquals(11, incomingRestResWrapperDto.getResponseSet().size());
		Iterator<MusicFesRecordLabelResponseDto> iterator = incomingRestResWrapperDto.getResponseSet().iterator();
		String recordlabel2="";
		String recordlabel3="";
		String recordlabel4="";
		String band1="";
		String band2="";
		String festival1="";
		String festival2="";
		int i=0;
	    while(iterator.hasNext()) {
	    	MusicFesRecordLabelResponseDto tempele = iterator.next();
	    
	    	if(i==1) {
	    		recordlabel2=tempele.getRecordLabel();
	    		Iterator<MusicFesBandResponseDto> iteratorBand = tempele.getMusicFesBandResponseDtoList().iterator();
	    		 while(iteratorBand.hasNext()) {
	    		    	MusicFesBandResponseDto tempBand = iteratorBand.next();
	    		    	band1=tempBand.getBandName();
	    		    	Iterator<String> iteratorfest =  	tempBand.getFestivalName().iterator();
	    		   	 while(iteratorfest.hasNext()) {
	    		   		festival1=iteratorfest.next();
	    		   	 
	    		   	 }
	    	}
	    	}
	    	if(i==2) {
	    		recordlabel3=tempele.getRecordLabel();
	    		Iterator<MusicFesBandResponseDto> iteratorBand1 = tempele.getMusicFesBandResponseDtoList().iterator();
	    		 while(iteratorBand1.hasNext()) {
	    		    	MusicFesBandResponseDto tempBand1 = iteratorBand1.next();
	    		    	band2=tempBand1.getBandName();
	    		    	Iterator<String> iteratorfest1 =  	tempBand1.getFestivalName().iterator();
		    		   	 while(iteratorfest1.hasNext()) {
		    		   		festival2=iteratorfest1.next();
		    		   	 
		    		   	 }
	    	}
	    	}
	    	if(i==3) {
	    		recordlabel4=tempele.getRecordLabel();
	    	}
	    	i++;
	    }
		assertEquals("ACR",recordlabel2);
		assertEquals("Anti Records",recordlabel3);
		assertEquals("Fourth Woman Records",recordlabel4);
		assertEquals("Manish Ditch",band1);
		assertEquals("YOUKRANE",band2);
		assertEquals("Trainerella",festival2);
		assertEquals("Trainerella",festival1);
		
	}
	@Test
	public void testGetRecordLabelInformation_shouldReturnEmptySetWhenAPIReturnEmptyStr() throws IOException {
		when(musicRestApiClientService.getFestivalBandInfo()).thenReturn(incomingRestResWrapperDtoEmpty);		
		incomingRestResWrapperDto= musicFestivalBandServiceImpl.getRecordLabelInformation();
		assertNull(incomingRestResWrapperDto.getResponseSet());
	}
	
	@Test
	public void testGetFormatedOutputStr_shouldReturnNonEmptySetWhenNonEmptySet() throws IOException {
		
		String response= musicFestivalBandServiceImpl.getFormatedOutputStr(responseSet);
		assertTrue(response.length()>0);
	}
}
