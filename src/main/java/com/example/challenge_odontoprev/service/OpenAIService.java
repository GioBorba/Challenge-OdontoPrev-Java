package com.example.challenge_odontoprev.service;

import org.springframework.ai.chat.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OpenAIService {

    private final ChatClient chatClient;

    @Value("${spring.ai.openai.model}")
    private String model;

    @Autowired
    public OpenAIService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String askQuestion(String question) {
        try {
            if (question == null || question.trim().isEmpty()) {
                return "Por favor, digite uma pergunta válida.";
            }

            String promptText = """
            Você é um assistente odontológico especializado. 
            Responda de forma clara, profissional e com informações precisas.
            Pergunta: {question}
            Resposta:""";

            Prompt prompt = new PromptTemplate(promptText)
                    .create(Map.of("question", question));

            return chatClient.call(prompt).getResult().getOutput().getContent();

        } catch (Exception e) {
            return "Desculpe, ocorreu um erro ao processar sua pergunta. Por favor, tente novamente.";
        }
    }
}