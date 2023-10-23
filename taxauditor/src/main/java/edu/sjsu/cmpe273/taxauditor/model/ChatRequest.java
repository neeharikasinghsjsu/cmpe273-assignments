package edu.sjsu.cmpe273.taxauditor.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ChatRequest {
	private String model;
	private List<Message> messages;
	private double temperature;

}
