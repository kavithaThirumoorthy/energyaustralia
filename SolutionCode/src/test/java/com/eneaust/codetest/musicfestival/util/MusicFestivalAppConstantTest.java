package com.eneaust.codetest.musicfestival.util;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MusicFestivalAppConstantTest {
	@Test
	public void testMusicFestivalAppConstant() {
		MusicFestivalAppConstant musicFestivalAppConstant= new MusicFestivalAppConstant();
		assertEquals("OK",musicFestivalAppConstant.STATUS_OK);
		assertEquals("NOT_OK",musicFestivalAppConstant.STATUS_NOT_OK);
		assertEquals("429",musicFestivalAppConstant.TOO_MANY_REQUESTS_CODE);
	}
}
