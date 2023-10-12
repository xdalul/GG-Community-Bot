package dev.dalol.commands.öffentlich;

import dev.dalol.util.Embeds;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.Channel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.time.Instant;
import java.util.Objects;

public class ReportCommand extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("report")) {
            String fehler = Objects.requireNonNull(event.getOption("fehler")).getAsString();
            Member member = event.getMember();
            event.reply("Danke für deinen Report!").setEphemeral(true).queue();
            event.getGuild().getTextChannelById("1153680683630338151").sendMessageEmbeds(Embeds.errorEmbed(fehler, event.getMember()).build()).queue();
        }
    }
}
