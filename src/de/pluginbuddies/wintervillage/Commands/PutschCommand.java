//Plugin by: PluginBuddies
//-> mullemann25 and Mannam01

package de.pluginbuddies.wintervillage.Commands;

import de.pluginbuddies.wintervillage.Main.Main;
import de.pluginbuddies.wintervillage.Util.Team;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.FileUtils;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.IOUtils;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;

public class PutschCommand implements CommandExecutor {

    static File configteams = new File("plugins//Teams//config.yml");
    public static YamlConfiguration ymlConfigteams = YamlConfiguration.loadConfiguration(configteams);
    private String rEr = ""; //FINAL Winner PUTSCH Rot
    private String rEb = ""; //FINAL Winner PUTSCH Blau
    private int votesRed = 0;
    private int votesBlue = 0;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("wintervillage.redteam") && !p.hasPermission("wintervillage.prisonred")) {
                if (args.length == 0) {
                    if (Main.getPlugin().getVoteOpen() != "true" && Main.getPlugin().getPutschRot() == false) { //PENIS Gucken ob vote phase bevor steht
                        Main.getPlugin().setPutschRot(true);
                        p.sendMessage(Main.getPlugin().PREFIX + "§3Die anderen Mitbürger werden benachrichtigt! Stimme mit §r/putsch <NAME> §3für einen neuen RotMeister ab!");
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            if (all.hasPermission("wintervillage.redteam") && !all.hasPermission("wintervillage.prisonred")) {
                                if (all != p) {
                                    all.sendMessage(Main.getPlugin().PREFIX + "§3Achtung! " + p.getName() + " §3hat einen Putsch gestartet. Unterstütze ihn, indem du §r/putsch <Name> §3benutzt, um einen neuen RotMeister zu wählen!");
                                }
                            }
                        }
                        new BukkitRunnable() {
                            int time = 10;////PENIS 1200
                            String t1;
                            String t2;
                            int sec = 60;
                            int min = 19;

                            @Override
                            public void run() {
                                time--;
                                sec--;
                                if (sec == 0) {
                                    sec = 60;
                                    min--;
                                }
                                if (time == 0) {
                                    if (votesRed == 1) {//PENIS
                                        for (final Player all : Bukkit.getOnlinePlayers()) {
                                            if (all.hasPermission("wintervillage.redteam") && !all.hasPermission("wintervillage.prisonred")) {
                                                t2 = String.format("§6§lPutsch vorbei!");
                                                String message2 = t2;
                                                all.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message2));
                                            }
                                        }
                                        try {
                                            getResult();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        String winnerrot = getrEr();
                                        if (!getrEr().isEmpty()) {
                                            ymlConfigteams.set("RotMeister.1", getUuid(winnerrot));
                                            try {
                                                ymlConfigteams.save(configteams);
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        }

                                        for (Player all : Bukkit.getOnlinePlayers()) {
                                            if (all.hasPermission("wintervillage.prisonred")) {
                                                all.kickPlayer(Main.getPlugin().PREFIX + "§cDu wurdest deines Amtes enthoben! §6Bitte trete dem Server erneut bei. Du wurdest ersetzt durch: \n§4RotMeister: §7" + winnerrot.toUpperCase());
                                            }
                                        }

                                        Main.BlauBuerger.clear();
                                        Main.RotBuerger.clear();
                                        Main.Buergermeisterred.clear();
                                        Main.Buergermeisterblue.clear();
                                        Team.maketeams();

                                        for (Player all : Bukkit.getOnlinePlayers()) {
                                            all.sendMessage(Main.getPlugin().PREFIX + "§bVillage-Rot hat einen neuen Bürgermeister gewählt! \n§4RotMeister: §7" + winnerrot.toUpperCase());
                                        }
                                        votesRed = 0;
                                        Main.getPlugin().setPutschRot(false);
                                        cancel();
                                    } else {
                                        for (Player all : Bukkit.getOnlinePlayers()) {
                                            if (all.hasPermission("wintervillage.redteam") && !all.hasPermission("wintervillage.prisonred")) {
                                                all.sendMessage(Main.getPlugin().PREFIX + "§cEs haben zu wenige Mitbürger abgestimmt. Probiert es später erneut!");
                                            }
                                        }
                                        votesRed = 0;
                                        Main.getPlugin().setPutschRot(false);
                                        cancel();
                                    }
                                } else {
                                    for (final Player all : Bukkit.getOnlinePlayers()) {
                                        if (all.hasPermission("wintervillage.redteam") && !all.hasPermission("wintervillage.prisonred")) {
                                            t1 = String.format("§6§lPutsch noch: " + min + "min:" + sec + "sec");
                                            String message = t1;
                                            all.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
                                        }
                                    }

                                }
                            }
                        }.runTaskTimer(Main.getPlugin(), 0L, 20L);
                    } else
                        p.sendMessage(Main.getPlugin().PREFIX + "§cDies geht nicht während der Voting-Phase!");
                } else {
                    if (args.length != 1) {
                        p.sendMessage(Main.getPlugin().PREFIX + "§cBitte benutze §r/putsch <NAME>§c!");
                    }
                }
                if (args.length == 1) {
                    if (Main.getPlugin().getPutschRot() == true) {
                        votesRed++;
                        Scanner scanner = null;
                        boolean voted = false;
                        try {
                            scanner = new Scanner(new File("plugins//Vote//putschvoted.yml"));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        while (scanner.hasNextLine()) {
                            String line = scanner.nextLine();
                            if (line.equals(p.getName())) {
                                voted = true;
                            }
                        }
                        if (voted == false) {
                            voted = true;
                            if (Main.getPlugin().namesred.contains(args[0].toLowerCase())) {
                                try (PrintWriter output = new PrintWriter(new FileWriter("plugins//Vote//putschvotesred.yml", true))) {
                                    output.printf("%s\r\n", args[0].toLowerCase());
                                } catch (Exception e) {
                                }
                                try (PrintWriter output = new PrintWriter(new FileWriter("plugins//Vote//putschvoted.yml", true))) {
                                    output.printf("%s\r\n", p.getName());
                                } catch (Exception e) {
                                }
                                p.sendMessage(Main.getPlugin().PREFIX + "§3Du hast für §c" + args[0].toUpperCase() + " §3gestimmt und den Putsch somit unterstützt!");
                            } else
                                p.sendMessage(Main.getPlugin().PREFIX + "§cDieser Spieler ist nicht in deinem Village!");
                        } else
                            p.sendMessage(Main.getPlugin().PREFIX + "§cDu hast bereits abgestimmt!");
                    } else
                        p.sendMessage(Main.getPlugin().PREFIX + "§cBitte benutze §r/putsch§c, um einen Putsch zu starten!");
                }
            } else {
                if (p.hasPermission("wintervillage.prisonred")) {
                    p.sendMessage(Main.getPlugin().PREFIX + "§cBist du des WAHNSINNS?! Wenn du unzufrieden mit dir bist, dann mach deinen Job richtig...");
                }
            }
            //////***************************************BLAU*******************
            if (p.hasPermission("wintervillage.blueteam") && !p.hasPermission("wintervillage.prisonblue")) {
                if (args.length == 0) {
                    if (Main.getPlugin().getVoteOpen() != "true" && Main.getPlugin().getPutschBlau() == false) { //PENIS Gucken ob vote phase bevor steht
                        Main.getPlugin().setPutschBlau(true);
                        p.sendMessage(Main.getPlugin().PREFIX + "§3Die anderen Mitbürger werden benachrichtigt! Stimme mit §r/putsch <NAME> §3für einen neuen BlauMeister ab!");
                        for (Player all : Bukkit.getOnlinePlayers()) {
                            if (all.hasPermission("wintervillage.blueteam") && !all.hasPermission("wintervillage.prisonblue")) {
                                if (all != p) {
                                    all.sendMessage(Main.getPlugin().PREFIX + "§3Achtung! " + p.getName() + " §3hat einen Putsch gestartet. Unterstütze ihn, indem du §r/putsch <Name> §3benutzt, um einen neuen BlauMeister zu wählen!");
                                }
                            }
                        }
                        new BukkitRunnable() {
                            int time = 1200;
                            String t1;
                            String t2;
                            int sec = 60;
                            int min = 20;

                            @Override
                            public void run() {
                                time--;
                                sec--;
                                if (sec == 0) {
                                    sec = 60;
                                    min--;
                                }
                                if (time == 0) {
                                    if (votesBlue == 5) {
                                        for (final Player all : Bukkit.getOnlinePlayers()) {
                                            if (all.hasPermission("wintervillage.blueteam") && !all.hasPermission("wintervillage.prisonblue")) {
                                                t2 = String.format("§6§lPutsch vorbei!");
                                                String message2 = t2;
                                                all.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message2));
                                            }
                                        }
                                        try {
                                            getResult();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        String winnerblau = getrEb();
                                        if (!getrEb().isEmpty()) {
                                            ymlConfigteams.set("BlauMeister.1", getUuid(winnerblau));
                                        }
                                        try {
                                            ymlConfigteams.save(configteams);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        for (Player all : Bukkit.getOnlinePlayers()) {
                                            if (all.hasPermission("wintervillage.prisonblue")) {
                                                all.kickPlayer(Main.getPlugin().PREFIX + "§cDu wurdest deines Amtes enthoben! §6Bitte trete dem Server erneut bei. Du wurdest ersetzt durch: \n§1BlauMeister: §7" + winnerblau.toUpperCase());
                                            }
                                        }

                                        Main.BlauBuerger.clear();
                                        Main.RotBuerger.clear();
                                        Main.Buergermeisterred.clear();
                                        Main.Buergermeisterblue.clear();
                                        Team.maketeams();

                                        for (Player all : Bukkit.getOnlinePlayers()) {
                                            all.sendMessage(Main.getPlugin().PREFIX + "§bVillage-Blau hat einen neuen Bürgermeister gewählt! \n§1BlauMeister: §7" + winnerblau.toUpperCase());
                                        }
                                        votesBlue = 0;
                                        Main.getPlugin().setPutschRot(false);
                                        cancel();
                                    } else {
                                        for (Player all : Bukkit.getOnlinePlayers()) {
                                            if (all.hasPermission("wintervillage.blueteam") && !all.hasPermission("wintervillage.prisonblue")) {
                                                all.sendMessage(Main.getPlugin().PREFIX + "§cEs haben zu wenige Mitbürger abgestimmt. Probiert es später erneut (5 Bürger müssen)!");
                                            }
                                        }
                                        votesBlue = 0;
                                        Main.getPlugin().setPutschRot(false);
                                        cancel();
                                    }
                                } else {
                                    for (final Player all : Bukkit.getOnlinePlayers()) {
                                        if (all.hasPermission("wintervillage.blueteam") && !all.hasPermission("wintervillage.prisonblue")) {
                                            t1 = String.format("§6§lPutsch noch: " + min + "min:" + sec + "sec");
                                            String message = t1;
                                            all.spigot().sendMessage(ChatMessageType.ACTION_BAR, TextComponent.fromLegacyText(message));
                                        }
                                    }

                                }
                            }
                        }.runTaskTimer(Main.getPlugin(), 0L, 20L);
                    } else
                        p.sendMessage(Main.getPlugin().PREFIX + "§cDies geht nicht während der Voting-Phase!");
                } else {
                    if (args.length != 1) {
                        p.sendMessage(Main.getPlugin().PREFIX + "§cBitte benutze §r/putsch <NAME>§c!");
                    }
                }
                if (args.length == 1) {
                    if (Main.getPlugin().getPutschBlau() == true) {
                        votesBlue++;
                        Scanner scanner = null;
                        boolean voted = false;
                        try {
                            scanner = new Scanner(new File("plugins//Vote//putschvoted.yml"));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        while (scanner.hasNextLine()) {
                            String line = scanner.nextLine();
                            if (line.equals(p.getName())) {
                                voted = true;
                            }
                        }
                        if (voted == false) {
                            voted = true;
                            if (Main.getPlugin().namesblue.contains(args[0].toLowerCase())) {
                                try (PrintWriter output = new PrintWriter(new FileWriter("plugins//Vote//putschvotesblue.yml", true))) {
                                    output.printf("%s\r\n", args[0].toLowerCase());
                                } catch (Exception e) {
                                }
                                try (PrintWriter output = new PrintWriter(new FileWriter("plugins//Vote//putschvoted.yml", true))) {
                                    output.printf("%s\r\n", p.getName());
                                } catch (Exception e) {
                                }
                                p.sendMessage(Main.getPlugin().PREFIX + "§3Du hast für §9" + args[0].toUpperCase() + " §3gestimmt und den Putsch somit unterstützt!");
                            } else
                                p.sendMessage(Main.getPlugin().PREFIX + "§cDieser Spieler ist nicht in deinem Village!");
                        } else
                            p.sendMessage(Main.getPlugin().PREFIX + "§cDu hast bereits abgestimmt!");
                    } else
                        p.sendMessage(Main.getPlugin().PREFIX + "§cBitte benutze §r/putsch§c, um einen Putsch zu starten!");
                }
            } else {
                if (p.hasPermission("wintervillage.prisonblue")) {
                    p.sendMessage(Main.getPlugin().PREFIX + "§cBist du des WAHNSINNS?! Wenn du unzufrieden mit dir bist, dann mach deinen Job richtig...");
                }
            }
        }
        return false;
    }

    public String getrEr() {
        return rEr;
    }

    public String getrEb() {
        return rEb;
    }

    public void getResult() throws IOException {

        HashMap<String, Integer> votesblue = new HashMap<>();
        HashMap<String, Integer> votesred = new HashMap<>();

        for (String all : Main.getPlugin().namesred) {
            votesred.put(all, 0);
        }
        for (String all : Main.getPlugin().namesblue) {
            votesblue.put(all, 0);
        }

        Scanner scannerRed = null;
        try {
            scannerRed = new Scanner(new File("plugins//Vote//putschvotesred.yml"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scannerRed.hasNextLine()) {
            String line = scannerRed.nextLine();
            votesred.put(line.toLowerCase(), votesred.get(line.toLowerCase()) + 1);
        }
        Scanner scannerBlue = null;
        try {
            scannerBlue = new Scanner(new File("plugins//Vote//putschvotesblue.yml"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scannerBlue.hasNextLine()) {
            String line = scannerBlue.nextLine();
            votesblue.put(line.toLowerCase(), votesblue.get(line.toLowerCase()) + 1);
        }

        int maxred = 0;
        for (int i : votesred.values()) {
            if (i > maxred) {
                maxred = i;
            }
        }
        int maxblue = 0;
        for (int i : votesblue.values()) {
            if (i > maxblue) {
                maxblue = i;
            }
        }

        List<String> winnerred = new ArrayList<>();
        int wr = 0;
        for (String all : votesred.keySet()) {
            if (votesred.get(all) == maxred) {
                winnerred.add(all);
                wr++;
            }
        }
        List<String> winnerblue = new ArrayList<>();
        int wb = 0;
        for (String all : votesblue.keySet()) {
            if (votesblue.get(all) == maxblue) {
                winnerblue.add(all);
                wb++;
            }
        }

        boolean workaround = true;
        do {
            Random rand = new Random();
            int ri = rand.nextInt(winnerred.size());
            rEr = winnerred.get(ri);
            rEb = winnerblue.get(ri);
            if (rEr != "error" && rEb != "error") {
                break;
            }
        }
        while (workaround == true);


        FileUtils.write(new File("plugins//Vote//putschvotesred.yml"), "", Charset.defaultCharset());
        FileUtils.write(new File("plugins//Vote//putschvotesblue.yml"), "", Charset.defaultCharset());
        FileUtils.write(new File("plugins//Vote//putschvoted.yml"), "", Charset.defaultCharset());

    }

    public String getUuid(String name) {
        String url = "https://api.mojang.com/users/profiles/minecraft/" + name;
        try {
            @SuppressWarnings("deprecation")
            String UUIDJson = IOUtils.toString(new URL(url));
            if (UUIDJson.isEmpty()) return "invalid name";
            JSONObject UUIDObject = (JSONObject) JSONValue.parseWithException(UUIDJson);
            return UUIDObject.get("id").toString();
        } catch (IOException | org.json.simple.parser.ParseException e) {
            e.printStackTrace();
        }

        return "error";
    }
}