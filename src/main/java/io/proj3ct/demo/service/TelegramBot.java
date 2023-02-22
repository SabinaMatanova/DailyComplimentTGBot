package io.proj3ct.demo.service;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Calendar;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


@Component
public class TelegramBot extends TelegramLongPollingBot {

    private final String BOT_TOKEN = "5901420352:AAG9_NUVH4gFN1A4dMHqzBVdNeSn7emvxeE";
    private final String BOT_USERNAME = "DailyCompliments13Bot";
    private final Random random = new Random();
    private final String[] compliments = {"You're amazing!", "You have a beautiful smile!", "You're so smart!"};

    public void onUpdateReceived(Update update) {
        // Do nothing in this example
    }

    public void sendRandomCompliment() {
        int index = random.nextInt(compliments.length);
        SendMessage message = new SendMessage()
                .setChatId("YOUR_CHAT_ID_HERE")
                .setText(compliments[index]);
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    public void sendDailyCompliment() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 9);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                sendRandomCompliment();
            }
        }, calendar.getTime(), 1000 * 60 * 60 * 24);
    }

    public String getBotUsername() {
        return BOT_USERNAME;
    }

    public String getBotToken() {
        return BOT_TOKEN;
    }
}
