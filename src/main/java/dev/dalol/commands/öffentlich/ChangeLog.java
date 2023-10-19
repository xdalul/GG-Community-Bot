package dev.dalol.commands.öffentlich;

import dev.dalol.util.Embeds;
import dev.dalol.util.Rollen;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class ChangeLog extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("changelog")) {
            if (event.getMember().getRoles().contains(Rollen.getAllTeamRoles(event.getGuild()))) {
            event.replyEmbeds(Embeds.changeLogEmbed().build()).setEphemeral(true).queue();
            return;
        } else {
                event.replyEmbeds(Embeds.changeLogAdminEmbed().build()).setEphemeral(true).queue();
                return;
            }
        }
    }
}
