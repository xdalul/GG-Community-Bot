package dev.dalol.listener;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRoleAddEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import dev.dalol.util.Rollen;

public class RoleNickEvent extends ListenerAdapter {
    @Override
    public void onGuildMemberRoleAdd(GuildMemberRoleAddEvent event) {
        Member m = event.getMember();

        Role[] teamrollen = {Rollen.getTeamRole(event.getGuild()), Rollen.getDeveloperRole(event.getGuild()), Rollen.getLSupportRole(event.getGuild()), Rollen.getEventManagerRole(event.getGuild()), Rollen.getBuilderRole(event.getGuild()), Rollen.getSupportRole(event.getGuild()), Rollen.getAdminRole(event.getGuild()), Rollen.getModRole(event.getGuild()), Rollen.getLBuilderRole(event.getGuild())};


        if (event.equals(teamrollen)) {
            m.modifyNickname("[Team] " + m.getEffectiveName()).queue();
        } else if (event.equals(Rollen.getVIPRole(event.getGuild()))) {
            m.modifyNickname("[VIP] " + m.getEffectiveName()).queue();
        }
    }
}
