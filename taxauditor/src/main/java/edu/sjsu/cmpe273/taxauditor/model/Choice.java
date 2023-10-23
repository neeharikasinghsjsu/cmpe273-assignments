package edu.sjsu.cmpe273.taxauditor.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Choice {
	private Message message;
	private String finishReason;
	private long index;

}
