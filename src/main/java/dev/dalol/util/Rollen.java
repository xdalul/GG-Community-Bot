package dev.dalol.util;

import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.guild.voice.GuildVoiceGuildDeafenEvent;

public class Rollen {
    public static Role getOwnerRole(Guild guild) {
        return guild.getRoleById("897123045469204520");
    }
    public static Role getAdminRole(Guild guild) {
        return guild.getRoleById("989895363123114025");
    }
    public static Role getModRole(Guild guild) {
        return guild.getRoleById("968225559597436978");
    }
    public static Role getLSupportRole(Guild guild) {
        return guild.getRoleById("968225559597436978");
    }
    public static Role getSupportRole(Guild guild) {
        return guild.getRoleById("901836559215640616");
    }
    public static Role getEventManagerRole(Guild guild) {
        return guild.getRoleById("1157803576807137330");
    }
    public static Role getLBuilderRole(Guild guild) {
        return guild.getRoleById("1157762886739951689");
    }
    public static Role getBuilderRole(Guild guild) {
        return guild.getRoleById("1157762230738227200");
    }
    public static Role getDeveloperRole(Guild guild) {
        return guild.getRoleById("937361810439417876");
    }
    public static Role getTeamRole(Guild guild) {
        return guild.getRoleById("1155076938579320912");
    }
    public static Role getBoosterRole(Guild guild) {
        return guild.getRoleById("1111653930552787035");
    }
    public static Role getVIPRole(Guild guild) {
        return guild.getRoleById("936232905489932298");
    }
    public static Role getEhrenmitgliedRole(Guild guild) {
        return guild.getRoleById("917860601471045742");
    }

    // Mehrere Rollen

    public static Role[] getModerationsRoles(Guild guild) {
        Role[] full_moderation_roles = {getOwnerRole(guild), getAdminRole(guild), getModRole(guild), getLSupportRole(guild), getDeveloperRole(guild)};
        return full_moderation_roles;
    }
    public static Role[] getModerationsRolesBegrenzt(Guild guild) {
        Role[] begrenzt_moderation_roles = {getSupportRole(guild), getBuilderRole(guild), getEventManagerRole(guild), getLBuilderRole(guild)};
        return begrenzt_moderation_roles;
    }

    public static Role[] getAllTeamRoles(Guild guild) {
        Role[] all_team_roles = {getOwnerRole(guild), getSupportRole(guild), getTeamRole(guild), getAdminRole(guild), getModRole(guild), getLSupportRole(guild), getEventManagerRole(guild), getLBuilderRole(guild), getBuilderRole(guild), getDeveloperRole(guild)};
        return all_team_roles;
    }
}
