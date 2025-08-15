package com.example.hai_dien_church.controller;

import com.example.hai_dien_church.dto.response.ChatBotResponse;
import com.example.hai_dien_church.service.ChatBotService;
import jakarta.annotation.PostConstruct;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.InMemoryChatMemory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


@RestController
@RequestMapping("/chat-bot")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PACKAGE)
public class ChatBotController {

    final ChatClient.Builder chatClientBuilder;
    final ChatBotService chatBotService;

    ChatClient chatClient;
    ChatMemory chatMemory = new InMemoryChatMemory();



    private static String SYSTEM_PROMPT = "Prompt Nâng Cấp nhà thờ Hải Điền - Trợ Lý Tư Vấn:\n" +
            "\n" +
            "Vai trò: Bạn là trợ lý tư vấn của trang thông tin nhà thờ Hải Điền tập trung vào việc đề xuất thông tin các sự kiện, hoạt động của nhà thờ. Không trả lời chung chung.\n" +
            "\n" +
            "Phân tích yêu cầu:\n" +
            "\n" +
            "Xác định rõ từ khóa/tiêu chí (ví dụ: mới nhất, trong ngày).\n" +
            "\n" +
            "Trả lời:\n" +
            "\n" +
            "Ngắn gọn, tự nhiên, dùng gạch đầu dòng hoặc đánh số nếu có nhiều thông tin.\n" +
            "\n" +
            "Nếu không có thông tin phù hợp:\n" +
            "→ “Bạn có thể hỏi về lịch sử hình thành, các thông tin về sự kiện và hoạt động của nhà thờ!”\n" +
            "\n" +
            "Lưu ý:\n" +
            "\n" +
            "Chủ động đặt câu hỏi nếu thiếu thông tin.\n" +
            "\n" +
            "Giọng văn thân thiện nhưng chuyên nghiệp, tránh dài dòng.\n" +
            "\n";

    @PostConstruct
    public void init() {
        this.chatClient = chatClientBuilder
                .defaultAdvisors(new MessageChatMemoryAdvisor(chatMemory))
                .defaultSystem(SYSTEM_PROMPT)
                .build();
    }

    @GetMapping("/stream")
    public Flux<String> chatWithStream(@RequestParam("message") String message){
        ChatBotResponse data = chatBotService.getAll();
        return chatClient
                .prompt(data.toString())
                .user(message)
                .stream()
                .content();
    }

}
