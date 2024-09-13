package com.rafaeltech.musicvault.service;

import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;

import javax.annotation.processing.Completion;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultChatGPT {
    public static String getInformation(String text){
        OpenAiService service = new OpenAiService(System.getenv("OPEN_IA_KEY"));

        CompletionRequest request = CompletionRequest.builder()
                .model("gpt-3.5-turbo-instruct")
                .prompt("talk about that artist: " + text)
                .maxTokens(1000)
                .temperature(0.7)
                .build();

        var response = service.createCompletion(request);
        return response.getChoices().get(0).getText();
    }
}
