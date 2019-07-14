package com.eneaust.codetest.musicfestival.service;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.eneaust.codetest.musicfestival.dto.IncomingRestMusicBandDto;
import com.eneaust.codetest.musicfestival.dto.IncomingRestResMusicFestDto;
import com.eneaust.codetest.musicfestival.dto.ResponseWrapperDto;
import com.eneaust.codetest.musicfestival.dto.MusicFesBandResponseDto;
import com.eneaust.codetest.musicfestival.dto.MusicFesRecordLabelResponseDto;
import com.eneaust.codetest.musicfestival.util.MusicFestivalAppConstant;
import com.google.gson.Gson;

@Service
public class MusicFestivalBandServiceImpl implements MusicFestivalBandService {
	static Logger logger = LoggerFactory.getLogger(MusicFestivalBandServiceImpl.class);
	@Autowired
	MusicRestApiClientService musicRestApiClientService;

	@Override
	/**
	 * This method convert the rest API response to required format
	 */
	public ResponseWrapperDto getRecordLabelInformation() throws IOException {

		ResponseWrapperDto incomingRestResWrapperDto = musicRestApiClientService.getFestivalBandInfo();
		SortedSet<MusicFesRecordLabelResponseDto> responseSet = new TreeSet<>(
		Comparator.comparing(MusicFesRecordLabelResponseDto::getRecordLabel, String.CASE_INSENSITIVE_ORDER));
		if (null != incomingRestResWrapperDto.getStatusCode() && incomingRestResWrapperDto.getStatusCode().equals(MusicFestivalAppConstant.STATUS_OK)) {
			List<IncomingRestResMusicFestDto> incomingRestResMusicFestList = incomingRestResWrapperDto.getIncomingRestResMusicFestList();
			logger.info("Incoming Json message:==>{}", new Gson().toJson(incomingRestResMusicFestList));
			Map<String, MusicFesRecordLabelResponseDto> recordLableMap = new HashMap<>();
			for (IncomingRestResMusicFestDto incomingRestResMusicFestDto : incomingRestResMusicFestList) {
				List<IncomingRestMusicBandDto> bandsList = incomingRestResMusicFestDto.getBands();
				if (CollectionUtils.isNotEmpty(bandsList)) {
					for (IncomingRestMusicBandDto bands : bandsList) {
						if (!StringUtils.isEmpty(bands.getRecordLabel())) {
							if (!recordLableMap.containsKey(bands.getRecordLabel())) {
								recordLableMap.put(bands.getRecordLabel(),addToRecordLabelMap(incomingRestResMusicFestDto, bands));
							} else {
								MusicFesRecordLabelResponseDto musicFesRecordLabelResponseDto = recordLableMap.get(bands.getRecordLabel());
								updateRecordLabelMap(incomingRestResMusicFestDto, musicFesRecordLabelResponseDto,bands);
							}
						} else {
							if(!recordLableMap.containsKey("EMPTY")) {
								 MusicFesBandResponseDto musicFesBandResponseDto= new MusicFesBandResponseDto();
								 if(!StringUtils.isEmpty(bands.getName())) {
									 musicFesBandResponseDto.setBandName(bands.getName());}else {
										 musicFesBandResponseDto.setBandName("");
									 }
								 if(!StringUtils.isEmpty(incomingRestResMusicFestDto.getName())) {
									 musicFesBandResponseDto.getFestivalName().add(incomingRestResMusicFestDto.getName());}else {
										 musicFesBandResponseDto.getFestivalName().add("");
									 }
								 MusicFesRecordLabelResponseDto musicFesRecordLabelResponseDto= new MusicFesRecordLabelResponseDto();
								 musicFesRecordLabelResponseDto.setRecordLabel("");
								 musicFesRecordLabelResponseDto.getMusicFesBandResponseDtoList().add(musicFesBandResponseDto);
								 recordLableMap.put("EMPTY", musicFesRecordLabelResponseDto);
							 }else {
								 MusicFesRecordLabelResponseDto musicFesRecordLabelResponseDto=recordLableMap.get("EMPTY");
								 MusicFesBandResponseDto musicFesBandResponseDto= new MusicFesBandResponseDto();
								 if(!StringUtils.isEmpty(bands.getName())) {
									 musicFesBandResponseDto.setBandName(bands.getName());}else {
										 musicFesBandResponseDto.setBandName("");
									 }
								 if(!StringUtils.isEmpty(incomingRestResMusicFestDto.getName())) {
									 musicFesBandResponseDto.getFestivalName().add(incomingRestResMusicFestDto.getName());}else {
										 musicFesBandResponseDto.getFestivalName().add("");
									 }
							
								 musicFesRecordLabelResponseDto.getMusicFesBandResponseDtoList().add(musicFesBandResponseDto);								 
							 }
						}
					}
				}
			}
			
			responseSet.addAll(recordLableMap.values());
			incomingRestResWrapperDto.setResponseSet(responseSet);
		}
	
		
		
		return incomingRestResWrapperDto;
	}

	private MusicFesRecordLabelResponseDto addToRecordLabelMap(IncomingRestResMusicFestDto incomingRestResMusicFestDto,
			IncomingRestMusicBandDto bands) {
		MusicFesBandResponseDto musicFesBandResponseDto = new MusicFesBandResponseDto();
		if (!StringUtils.isEmpty(bands.getName())) {
			musicFesBandResponseDto.setBandName(bands.getName());
		} else {
			musicFesBandResponseDto.setBandName("");
		}
		if (!StringUtils.isEmpty(incomingRestResMusicFestDto.getName())) {
			musicFesBandResponseDto.getFestivalName().add(incomingRestResMusicFestDto.getName());
		} else {
			musicFesBandResponseDto.getFestivalName().add("");
		}

		MusicFesRecordLabelResponseDto musicFesRecordLabelResponseDto = new MusicFesRecordLabelResponseDto();
		musicFesRecordLabelResponseDto.setRecordLabel(bands.getRecordLabel());
		musicFesRecordLabelResponseDto.getMusicFesBandResponseDtoList().add(musicFesBandResponseDto);
		return musicFesRecordLabelResponseDto;
	}

	private MusicFesRecordLabelResponseDto updateRecordLabelMap(IncomingRestResMusicFestDto incomingRestResMusicFestDto,
			MusicFesRecordLabelResponseDto musicFesRecordLabelResponseDto, IncomingRestMusicBandDto bands) {
		MusicFesBandResponseDto musicFesBandResponseDto = new MusicFesBandResponseDto();
		if (!StringUtils.isEmpty(bands.getName())) {
			musicFesBandResponseDto.setBandName(bands.getName());
		} else {
			musicFesBandResponseDto.setBandName("");
		}
		if (!StringUtils.isEmpty(incomingRestResMusicFestDto.getName())) {
			musicFesBandResponseDto.getFestivalName().add(incomingRestResMusicFestDto.getName());
		} else {
			musicFesBandResponseDto.getFestivalName().add("");
		}
		musicFesRecordLabelResponseDto.getMusicFesBandResponseDtoList().add(musicFesBandResponseDto);
		return musicFesRecordLabelResponseDto;
	}

	@Override
	/**
	 * This method convert the set value in to a String to display in web page
	 */
	public String getFormatedOutputStr(Set<MusicFesRecordLabelResponseDto> musicFesRecordLabelResponseDtoSet) {
		int rownum = 1;
		StringBuilder temp = new StringBuilder();
		for (MusicFesRecordLabelResponseDto musicFesRecordLabelResponseDto : musicFesRecordLabelResponseDtoSet) {
			temp.append("<br>");
			temp.append("<b> Record Label_" + rownum + ":</b>");
			temp.append(musicFesRecordLabelResponseDto.getRecordLabel());
			temp.append("<br>");
			temp.append("<br>");

			Iterator<MusicFesBandResponseDto> iteratortemp = musicFesRecordLabelResponseDto
					.getMusicFesBandResponseDtoList().iterator();
			while (iteratortemp.hasNext()) {
				MusicFesBandResponseDto temp1 = iteratortemp.next();
				temp.append("&emsp;<b>Band:</b>");
				temp.append(temp1.getBandName());
				temp.append("<br>");

				if (CollectionUtils.isNotEmpty(temp1.getFestivalName())) {
					SortedSet<String> festList = temp1.getFestivalName();
					for (String tempStr : festList) {
						if (!StringUtils.isEmpty(tempStr)) {
						temp.append("&emsp;&emsp;&emsp;&emsp;&emsp;<b>Festival:</b>");
						temp.append(tempStr);
						temp.append("<br>");
						}
					}
				}
				temp.append("<br>");

			}
			rownum++;
		}

		return temp.toString();
	}

}
