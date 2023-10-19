package dev.dalol.commands.admin;

import dev.dalol.listener.MessageEvent;
import dev.dalol.util.Embeds;
import dev.dalol.util.Rollen;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class RemoveLastMessageCommand extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("removelastmessage")) {
            if (event.getMember().getRoles().contains(Rollen.getAllTeamRoles(event.getGuild()))) {
                MessageEvent.words.remove(MessageEvent.lastMessage);
                event.replyEmbeds(Embeds.removeLastMessage(MessageEvent.lastMessage).build()).setEphemeral(true).queue();
                System.out.println("Entfernt: " + MessageEvent.lastMessage);
                MessageEvent.lastMessage = "";
            } else {
                event.reply("Du hast keine Berechtigung dafür!").setEphemeral(true).queue();
            }
        }
    }
}
