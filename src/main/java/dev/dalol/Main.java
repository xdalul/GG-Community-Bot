package dev.dalol;

import dev.dalol.commands.admin.*;
import dev.dalol.commands.öffentlich.ChangeLog;
import dev.dalol.commands.öffentlich.HelpCommand;
import dev.dalol.commands.öffentlich.ReportCommand;
import dev.dalol.listener.MessageEvent;
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
                .addEventListeners(new MessageEvent(), new ReportCommand(), new UnterbrechenCommand(), new RemoveLastMessageCommand(), new HelpCommand(), new AddRemoveRoleCMD(), new InstantKickBanTimeoutCMD(), new NickCommand(), new ChangeLog())
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
        jda.upsertCommand("addrole", "Füge eine Rolle zu einen User hinzu.")
                .addOption(OptionType.USER, "nutzer", "Der User, der eine Rolle hinzugefügt bekommen soll.", true)
                .addOption(OptionType.ROLE, "rolle", "Die rolle die du dem User geben möchtest.", true)
                .queue();
        jda.upsertCommand("removerole", "Entferne eine Rolle von einem User.")
                .addOption(OptionType.USER, "nutzer", "Der User, der eine Rolle entfernt bekommen soll.", true)
                .addOption(OptionType.ROLE, "rolle", "Die Rolle die du dem User entfernen möchtest.", true)
                .queue();
        jda.upsertCommand("instantban", "Banne einen User.")
                .addOption(OptionType.USER, "nutzer", "Gebe den Nutzer an den du bannen möchtest.", true)
                .addOption(OptionType.INTEGER, "deldays", "Gebe an, wieviele Tage zurück sollen von dem User die nachrichten entfernt werden.", true)
                .queue();
        jda.upsertCommand("instantkick", "Kicke einen User.")
                .addOption(OptionType.USER, "nutzer", "Gebe an, welcher Nutzer gekickt werden soll.", true)
                .queue();
        jda.upsertCommand("instanttimeout", "Timeoute einen User.")
                .addOption(OptionType.USER, "nutzer", "Gebe an, welchen Nutzer du Timeouten willst.", true)
                .addOption(OptionType.STRING, "dauer", "Und für wie lang.", true)
                .queue();
        jda.upsertCommand("nick", "Bennene eine Nutzer hier auf dem Discord Server um.")
                .addOption(OptionType.USER, "nutzer", "Den Nutzer, der den Namen ändern soll.", true)
                .addOption(OptionType.STRING, "nickname", "Wie der Nutzer anschließend heißen Soll.", true)
                .queue();
        jda.upsertCommand("taschenrechner", "Ein Taschenrenchner. Was soll ich mehr sagen...")
                .addOption(OptionType.STRING, "rechnung", "Deine Rechnung.", true)
                .queue();
        jda.upsertCommand("ask-gpt", "Frage ChatGPT eine Frage oder sonstiges :))")
                .addOption(OptionType.STRING, "frage", "Deine Frage.", true)
                .queue();
        jda.upsertCommand("create-umfrage", "Erstelle eine Umfrage.")
                .addOption(OptionType.STRING, "titel", "Title, von der Umfrage.", true)
                .addOption(OptionType.STRING, "beschreibung", "Beschreibung, von der Umfrage.", true)
                .addOption(OptionType.STRING, "antwort_1", "Erste Antwort", true)
                .addOption(OptionType.STRING, "antwort_2", "Zweite Antwort", true)
                .addOption(OptionType.STRING, "antwort_3", "Dritte Antwort")
                .addOption(OptionType.STRING, "antwort_4", "Vierte Antwort")
                .queue();
    }
}