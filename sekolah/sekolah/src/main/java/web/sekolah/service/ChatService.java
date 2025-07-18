package web.sekolah.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.sekolah.controller.IntentMapping;
import web.sekolah.repository.FaqIntentRepository;
import web.sekolah.model.FaqIntent;

@Service
public class ChatService {

    @Autowired
    private FaqIntentRepository faqIntentRepo;

    public String getResponse(String userInput) {
        String intent = IntentMapping.detectIntent(userInput);

        if (intent.equals("unknown")) {
            return "Maaf, saya belum bisa memahami pertanyaannya.";
        }

        return faqIntentRepo.findByIntent(intent)
                .map(FaqIntent::getJawaban)
                .orElse("Belum ada jawaban untuk pertanyaan ini.");
    }
}
