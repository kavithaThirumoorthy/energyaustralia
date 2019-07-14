package com.eneaust.codetest.musicfestival.dto;

import java.io.Serializable;


public 	class IncomingRestMusicBandDto implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String name;
		private String recordLabel;
		public  IncomingRestMusicBandDto() {}
		public  IncomingRestMusicBandDto(String name, String recordLabel) {
			this.name=name;
			this.recordLabel=recordLabel;
		}
		
		/**
		 * @return the recordLabel
		 */
		public String getRecordLabel() {
			return recordLabel;
		}

		/**
		 * @param recordLabel the recordLabel to set
		 */
		public void setRecordLabel(String recordLabel) {
			this.recordLabel = recordLabel;
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

