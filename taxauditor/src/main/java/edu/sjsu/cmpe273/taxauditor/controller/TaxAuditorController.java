package edu.sjsu.cmpe273.taxauditor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import edu.sjsu.cmpe273.taxauditor.model.ChatRequest;
import edu.sjsu.cmpe273.taxauditor.model.ChatResponse;
import edu.sjsu.cmpe273.taxauditor.model.Message;

@RestController
@RequestMapping("/taxauditor")
public class TaxAuditorController {
	
	@Autowired
	@Qualifier("chatRestTemplate")
	private RestTemplate restTemplate;
	
	@Value("${openai.api.url}")
	private String chatUrl;
	
	@Value("${openai.model}")
	private String model;
	
	@Value("${openai.temperature}")
	private double temperature;
	
	@CrossOrigin(origins = "http://localhost:5174")
	@PostMapping("/chat")
	public String sendMessage(@RequestParam String prompt) {
		Message systemPrompt = new Message("system", "You are an expert on tax. Please answer questions only related to tax. "
				+ "If asked other questions. Please reply saying it is out of scope.");
		Message userPrompt = new Message("user", prompt);
		ChatRequest chatRequest = ChatRequest.builder()
				.model(model)
				.temperature(temperature)
				.messages(List.of(systemPrompt, userPrompt))
				.build();
		ChatResponse chatResponse = restTemplate.postForObject(chatUrl, chatRequest, ChatResponse.class);
		String message = chatResponse.getChoices().get(0).getMessage().getContent();
		return message;
	}
}
