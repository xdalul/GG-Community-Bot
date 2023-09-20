package dev.dalol.commands.admin;

import dev.dalol.util.Embeds;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.awt.*;

public class Umfrage extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("create-umfrage")) {
            String getTitle = event.getOption("titel").getAsString();
            String getDescription = event.getOption("beschreibung").getAsString();
            String ButtonColor = event.getOption("button_farbe").getAsString();
            String getPointOne = event.getOption("antwort_1").getAsString();
            String getPointTwo = event.getOption("antwort_2").getAsString();
            String getPointThree = event.getOption("antwort_3").getAsString();
            String getPointFour = event.getOption("antwort_4").getAsString();

            if (ButtonColor.equals("blurple")) {
                Button Eins = Button.primary("one", getPointOne);
                Button Zwei = Button.primary("two", getPointTwo);
                Button Drei = Button.primary("three", getPointThree);
                Button Vier = Button.primary("four", getPointFour);
            } if (ButtonColor.equals("rot")) {
                Button Eins = Button.danger(getPointOne, getPointOne);
                Button Zwei = Button.danger(getPointTwo, getPointTwo);
                Button Drei = Button.danger(getPointThree, getPointThree);
                Button Vier = Button.danger(getPointFour, getPointFour);
            }


            event.reply("Erfolgreich!").setEphemeral(true).queue();
            event.getChannel().sendMessageEmbeds(Embeds.umfrageEmbed(getTitle, getDescription, getPointOne, getPointTwo, getPointThree, getPointFour).build()).addActionRow().queue();
        }
    }
}
