package dev.dalol.listener;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.time.Instant;
import java.util.*;

public class MessageEvent extends ListenerAdapter {


    public static List<String> words = new ArrayList<>();
    public static int currentIndex = 0;
    public static String lastWord = "";

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if (!event.getChannel().getId().equals("1151867308789211166")) return;
        if (event.getAuthor().isBot()) return;

        String messageContent = event.getMessage().getContentRaw();

        if (messageContent.contains(".")) {
            if (words.size() > 8) {
                lastWord = messageContent;
                words.add(lastWord);
                String sentence = String.join(" ", words);

                EmbedBuilder builder = new EmbedBuilder();

                builder.setTitle("`👀` Euer Satz ist vollendet!");
                builder.setDescription("Mal sehen was ihr veranstaltet habt:\n\n**" + sentence + "**");
                builder.setFooter("GG-Community");
                builder.setTimestamp(Instant.now());
                builder.setColor(0x8dfc32);

                event.getChannel().sendMessageEmbeds(builder.build()).queue();
                words.clear();
                currentIndex = 0;
                lastWord = "";
            } else {
                EmbedBuilder builder = new EmbedBuilder();
                event.getMessage().delete().queue();

                builder.setTitle("`❌` Ihr müsst mind. 8 Wörter schreiben.");
                builder.setDescription("*Falls dies ein Fehler ist, bitte </report:1151951919137427466> machen ;p* (Ihr könnt hier übrigens noch weiter schreiben, eure Wörter Reihe wurde dadurch nicht unterbrochen!)");
                builder.setFooter("GG-Community");
                builder.setColor(0xf55142);
                builder.setTimestamp(Instant.now());

                event.getChannel().sendMessageEmbeds(builder.build()).addContent(event.getMember().getAsMention()).queue();
            }
        } else {
            String[] wordsToAdd = messageContent.split("\\s+");
            if (wordsToAdd.length == 1) {
                if (currentIndex < event.getMember().getUser().getIdLong()) {
                    words.add(messageContent);
                    currentIndex = (int) event.getMember().getUser().getIdLong();
                } if (currentIndex > event.getMember().getUser().getIdLong()) {
                    event.getChannel().sendMessage("Test").queue();
                }
            } else {
                EmbedBuilder builder = new EmbedBuilder();
                event.getMessage().delete().queue();

                builder.setTitle("`⚠️` Schreibe bitte nur ein Wort!");
                builder.setDescription("*Falls dies ein Fehler ist, bitte </report:1151951919137427466> machen ;p*");
                builder.setColor(0xfa8d28);
                builder.setFooter("GG-Community");
                builder.setTimestamp(Instant.now());

                event.getChannel().sendMessageEmbeds(builder.build()).addContent(event.getMember().getAsMention()).queue();
            }
        }
    }
}
