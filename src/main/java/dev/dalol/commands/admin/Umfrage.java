package dev.dalol.commands.admin;

import dev.dalol.util.Embeds;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Umfrage extends ListenerAdapter {

    private static List<Member> gevotedOne = new ArrayList<>();
    private static List<Member> gevotedTwo = new ArrayList<>();
    private static List<Member> gevotedThree = new ArrayList<>();
    private static List<Member> gevotedFour = new ArrayList<>();
    public static String getIdOne = "";
    public static String getIdTwo = "";
    public static String getIdThree = "";
    public static String getIdFour = "";
    public static String getTextOne = "";
    public static String getTextTwo = "";
    public static String getTextFour = "";
    public static String getTextThree = "";

    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("create-umfrage")) {
            String getTitle = event.getOption("titel").getAsString();
            String getDescription = event.getOption("beschreibung").getAsString();
            String getPointOne = event.getOption("antwort_1").getAsString();
            String getPointTwo = event.getOption("antwort_2").getAsString();
            String getPointThree = Objects.requireNonNull(event.getOption("antwort_3")).getAsString();
            String getPointFour = Objects.requireNonNull(event.getOption("antwort_4")).getAsString();

            Button one = Button.success(getPointOne, getPointOne);
            Button two = Button.success(getPointTwo, getPointTwo);
            Button four = Button.success(getPointThree, getPointThree);
            Button three = Button.success(getPointFour, getPointFour);
            Button ergebnis = Button.danger("ergebniss", "Ergebniss Anzeigen");

            getIdOne = getPointOne;
            getIdTwo = getPointTwo;
            getIdThree = getPointThree;
            getIdFour = getPointFour;

            getTextOne = getPointOne;
            getTextTwo = getPointTwo;
            getTextThree = getPointThree;
            getTextFour = getPointFour;



            gevotedOne.clear();
            gevotedTwo.clear();
            gevotedThree.clear();
            gevotedFour.clear();

            event.reply("Erfolgreich!").setEphemeral(true).queue();
            if (getPointThree == null) {
                event.getChannel().sendMessageEmbeds(Embeds.umfrageEmbed(getTitle, getDescription, getPointOne, getPointTwo, getPointThree, getPointFour).build()).addActionRow(one, two).queue();
                return;
            } else {
                event.getChannel().sendMessageEmbeds(Embeds.umfrageEmbed(getTitle, getDescription, getPointOne, getPointTwo, getPointThree, getPointFour).build()).addActionRow(one, two, three).queue();
            }
            if (getPointFour != null) {
                event.getChannel().sendMessageEmbeds(Embeds.umfrageEmbed(getTitle, getDescription, getPointOne, getPointTwo, getPointThree, getPointFour).build()).addActionRow(one, two, three, four).queue();
            }
        }
    }
    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {

        if (event.getButton().getId().equals(getIdOne)) {
            if (!getGevotedOne().equals(event.getMember())) {
                event.reply("Du hast erfolgreich für \"" + getTextOne + "\" abgestimmt!").setEphemeral(true).queue();
                getGevotedOne().add(event.getMember());
            } else {
                event.reply("Du hast bereits für \"" + getTextOne + "\" abgestimmt!").setEphemeral(true).queue();
            }
        } else if (event.getButton().getId().equals(getIdTwo)) {
            if (!getGevotedOne().equals(event.getMember())) {
                event.reply("Du hast erfolgreich für \"" + getTextTwo + "\" abgestimmt!").setEphemeral(true).queue();
                getGevotedOne().add(event.getMember());
            } else {
                event.reply("Du hast bereits für \"" + getTextTwo + "\" abgestimmt!").setEphemeral(true).queue();
            }
        } else if (event.getButton().getId().equals(getIdThree)) {
            if (!getGevotedOne().equals(event.getMember())) {
                event.reply("Du hast erfolgreich für \"" + getTextThree + "\" abgestimmt!").setEphemeral(true).queue();
                getGevotedOne().add(event.getMember());
            } else {
                event.reply("Du hast bereits für \"" + getTextThree + "\" abgestimmt!").setEphemeral(true).queue();
            }
        } else if (event.getButton().getId().equals(getIdFour)) {
            if (!getGevotedOne().equals(event.getMember())) {
                event.reply("Du hast erfolgreich für \"" + getTextFour + "\" abgestimmt!").setEphemeral(true).queue();
                getGevotedOne().add(event.getMember());
            } else {
                event.reply("Du hast bereits für \"" + getTextFour + "\" abgestimmt!").setEphemeral(true).queue();
            }
        }
    }

    public static List<Member> getGevotedOne() {
        return gevotedOne;
    }

    public static List<Member> getGevotedTwo() {
        return gevotedTwo;
    }

    public static List<Member> getGevotedThree() {
        return gevotedThree;
    }

    public static List<Member> getGevotedFour() {
        return gevotedFour;
    }
}