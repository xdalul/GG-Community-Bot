package dev.dalol.commands.admin;

import dev.dalol.listener.MessageEvent;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class RemoveLastMessageCommand extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("removelastmessage")) {
            if (event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
                MessageEvent.words.remove(MessageEvent.lastMessage);
                event.reply("**Erfolgreich aus dem System entfernt: **" + MessageEvent.lastMessage).setEphemeral(true).queue();
                MessageEvent.lastMessage = "";
            } else {
                event.reply("Du hast keine Berechtigung dafür!").setEphemeral(true).queue();
            }
        }
    }
}
