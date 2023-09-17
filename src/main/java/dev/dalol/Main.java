package dev.dalol;

import dev.dalol.commands.HelpCommand;
import dev.dalol.commands.RemoveLastMessageCommand;
import dev.dalol.commands.ReportCommand;
import dev.dalol.commands.UnterbrechenCommand;
import dev.dalol.listener.MessageEvent;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.requests.GatewayIntent;

import java.util.Timer;
import java.util.TimerTask;

public class Main {
    private static String[] messages = {"OneWord","/help","& mehr"};
    private static int currentIndex = 0;
    public static void main(String[] args) {


        JDA jda = JDABuilder.createDefault("")
                .addEventListeners(new MessageEvent(), new ReportCommand(), new UnterbrechenCommand(), new RemoveLastMessageCommand(), new HelpCommand())
                .setStatus(OnlineStatus.ONLINE)
                .enableIntents(GatewayIntent.MESSAGE_CONTENT)
                .build();
        new Timer().schedule(new TimerTask(){
            public void run(){
                jda.getPresence().setActivity(Activity.playing(messages[currentIndex]));
                currentIndex=(currentIndex+1)%messages.length;
            }},0,3_000);


        jda.upsertCommand("report", "Melde einen Fehler.")
                .addOption(OptionType.STRING, "fehler", "Den Fehler den du gefunden hast.", true)
                .queue();

        jda.upsertCommand("unterbrechen", "Unterbricht die Wörter-Reihe")
                .addOption(OptionType.STRING, "grund", "Gebe einen Grund dazu", true)
                .queue();

        jda.upsertCommand("removelastmessage", "Lösche die zuletzt gelöschte Nachricht vom Kanal & System").queue();

        jda.upsertCommand("help", "Hilfe zu OneWord.").queue();
    }
}