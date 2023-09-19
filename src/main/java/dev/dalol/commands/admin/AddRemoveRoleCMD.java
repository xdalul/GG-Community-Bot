package dev.dalol.commands.admin;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class AddRemoveRoleCMD extends ListenerAdapter {
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("addrole")) {
            Member member = event.getOption("nutzer").getAsMember();
            Role role = event.getOption("rolle").getAsRole();
            if (event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
                if (!member.getRoles().equals(role)) {
                    member.getRoles().add(role);
                    event.reply("**Du hast dem User __" + member.getAsMention() + "__ die Rolle  \"" + role.getAsMention() + "\" geaddet.**").setEphemeral(true).queue();
                } else {
                    event.reply("**Der User hat bereits die Rolle!**").setEphemeral(true).queue();
                }
            }
        }
        if (event.getName().equals("removerole")) {
            Member member = (Member) event.getOption("nutzer").getAsMember();
            Role role = event.getOption("rolle").getAsRole();
            if (event.getMember().hasPermission(Permission.ADMINISTRATOR)) {
                if (member.getRoles().equals(role)) {
                    member.getRoles().remove(role);
                    event.reply("**Du hast dem User __" + member.getAsMention() + "__ die Rolle  \"" + role.getAsMention() + "\" entfernt.**").setEphemeral(true).queue();
                } else {
                    event.reply("**Der User hat diese Rolle nicht!**").setEphemeral(true).queue();
                }
            }
        }
    }
}

