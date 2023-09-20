package dev.dalol.util;

import net.dv8tion.jda.api.EmbedBuilder;

import java.time.Instant;

public class Embeds {

    public static EmbedBuilder changeLogEmbed() {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("`🌼` Changelog - v0.0.1");
        builder.setDescription("> Hey :)) Dies hier ist der erste Changelog ever von diesem Bot, hier. Changes:\n\n- /report wurde gefixt.\n- Dieser Command wurde geaddet.\n- bei /help gibt es nun eine \"Datenschutz Information\".\n- Dieser Bot ist nun ein Community Bot bzw. kein OneWord bot mehr :))\n\n*Gibt mehr Changes aber alles für Admins halt.*");
        builder.setColor(0xfcc428);
        builder.setFooter("GG-Community");
        builder.setTimestamp(Instant.now());
        return builder;
    }
    public static EmbedBuilder changeLogAdminEmbed() {
        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("`🌼` Changelog - v0.0.1");
        builder.setDescription("> Hey :)) Dies hier ist der erste Changelog ever von diesem Bot, hier. Changes:\n\n- /report wurde gefixt.\n- Dieser Command wurde geaddet.\n- bei /help gibt es nun eine \"Datenschutz Information\".\n- Dieser Bot ist nun ein Community Bot bzw. kein OneWord bot mehr :))\n\n### Changes für Administratoren\n\n- /addrole <user> <role> wurde hinzugefügt & /removerole <user> <role>\n- Instant Kick & Bann sind aktiv, Timeout ist noch in Arbeit.\n- /nick wurde geaddet.\n- Mehr folgt noch...");
        builder.setColor(0xfcc428);
        builder.setFooter("GG-Community");
        builder.setTimestamp(Instant.now());
        return builder;
    }

    public static EmbedBuilder umfrageEmbed(String getTitle, String getDescription, String getPointOne, String getPointTwo, String getPointThree, String getPointFour) {
        EmbedBuilder builder = new EmbedBuilder();

        if (getPointThree == null) {
            builder.setTitle(getTitle);
            builder.setDescription(getDescription + "\n\n1. " + getPointOne + "\n2. " + getPointTwo + "\n\n**Stimme unten ab, wo die Buttons sind, deine Meinung kannst du ändern.**");
            builder.setColor(0x23fa6e);
            builder.setTimestamp(Instant.now());
            builder.setFooter("GG-Community");
        } else {
            builder.setTitle(getTitle);
            builder.setDescription(getDescription + "\n\n1. " + getPointOne + "\n2. " + getPointTwo + "\n3. " + getPointThree + "\n\n**Stimme unten ab, wo die Buttons sind, deine Meinung kannst du ändern.**");
            builder.setColor(0x23fa6e);
            builder.setTimestamp(Instant.now());
            builder.setFooter("GG-Community");
        }
        if (getPointFour != null) {
            if (getPointThree != null) {
                builder.setTitle(getTitle);
                builder.setDescription(getDescription + "\n\n1. " + getPointOne + "\n2. " + getPointTwo + "\n3. " + getPointThree + "\n4. " + getPointFour + "\n\n**Stimme unten ab, wo die Buttons sind, deine Meinung kannst du ändern.**");
                builder.setColor(0x23fa6e);
                builder.setTimestamp(Instant.now());
                builder.setFooter("GG-Community");
            }
        }

        return builder;
    }
}
