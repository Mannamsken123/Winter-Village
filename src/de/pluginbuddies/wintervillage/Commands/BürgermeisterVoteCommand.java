//Plugin by: PluginBuddies
//-> mullemann25 and Mannam01

package de.pluginbuddies.wintervillage.Commands;

import de.pluginbuddies.wintervillage.Main.Main;
import de.pluginbuddies.wintervillage.Util.Team;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.FileUtils;
import org.bukkit.craftbukkit.libs.org.apache.commons.io.IOUtils;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;

public class BürgermeisterVoteCommand implements CommandExecutor {

    private String rEr = ""; //FINAL Winner Rot
    private String rEb = ""; //FINAL Winner Blau
    static File configteams = new File("plugins//Teams//config.yml");
    public static YamlConfiguration ymlConfigteams = YamlConfiguration.loadConfiguration(configteams);
    private int RotPutschVotes = 0;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("wintervillage.redteam") || p.hasPermission("wintervillage.prisonred")) {
                if (Main.getPlugin().getVoteOpen() == "true" || Main.getPlugin().getPutschRot() == true) {
                    if (args.length == 1) {
                        Scanner scanner = null;
                        boolean voted = false;
                        try {
                            scanner = new Scanner(new File("plugins//Vote//voted.yml"));
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
                                try (PrintWriter output = new PrintWriter(new FileWriter("plugins//Vote//votesred.yml", true))) {
                                    output.printf("%s\r\n", args[0].toLowerCase());
                                } catch (Exception e) {
                                }
                                try (PrintWriter output = new PrintWriter(new FileWriter("plugins//Vote//voted.yml", true))) {
                                    output.printf("%s\r\n", p.getName());
                                } catch (Exception e) {
                                }


                                //PENIS Putsch fängt hier an nur ein bespiel wie es gehen soll
                                if (Main.getPlugin().getPutschRot() == true) {
                                    RotPutschVotes++;

                                    //PENIS ROTPUTSCHVOTES in files wegen neustart

                                    p.sendMessage(Main.getPlugin().PREFIX + "§3Du hast für §c" + args[0].toUpperCase() + " §3gestimmt und damit den Putsch unterstützt!");
                                    if (RotPutschVotes == 5) {
                                        //PENIS ROTPUTSCHVOTES FILE LÖSCHEN
                                        //PENIS Außerdem müssen hier dei VoteOpen sachen zurückgesetzt werden!
                                        for (Player all : Bukkit.getOnlinePlayers()) {
                                            if (all.hasPermission("wintervillage.prisonred")) {
                                                all.kickPlayer(Main.getPlugin().PREFIX + "§cDu wurdest deines Amtes enthoben!");
                                            }
                                        }
                                        try {
                                            getResult();
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }
                                        String winnerblau = getrEb();
                                        String winnerrot = getrEr();
                                        if (!getrEr().isEmpty()) {
                                            ymlConfigteams.set("RotMeister.1", getUuid(winnerrot));
                                        }
                                        try {
                                            ymlConfigteams.save(configteams);
                                        } catch (IOException e) {
                                            e.printStackTrace();
                                        }

                                        Main.BlauBuerger.clear();
                                        Main.RotBuerger.clear();
                                        Main.Buergermeisterred.clear();
                                        Main.Buergermeisterblue.clear();
                                        Team.maketeams();
                                        Main.getPlugin().setPutschRot(false);

                                        for (Player all : Bukkit.getOnlinePlayers()) {
                                            File configMessages = new File("plugins//Messages//" + all.getUniqueId() + ".yml");
                                            YamlConfiguration ymlConfigMessages = YamlConfiguration.loadConfiguration(configMessages);

                                            String xxx = ymlConfigMessages.getString("VoteClose");

                                            if (xxx.equals("false")) {
                                                ymlConfigMessages.set("VoteClose", "true");
                                                ymlConfigMessages.set("VoteOpen", "false");

                                                all.sendMessage(Main.getPlugin().PREFIX + "§bNeue Bürgermeister wurden gewählt! \n§4RotMeister: §7" + winnerrot.toUpperCase() + "\n§1BlauMeister: §7" + winnerblau.toUpperCase());

                                                try {
                                                    ymlConfigMessages.save(configMessages);
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }
                                    }
                                } else
                                    p.sendMessage(Main.getPlugin().PREFIX + "§3Du hast für §c" + args[0].toUpperCase() + " §3gestimmt!");
                            } else
                                p.sendMessage(Main.getPlugin().PREFIX + "§cDieser Spieler ist nicht in deinem Village!");
                        } else
                            p.sendMessage(Main.getPlugin().PREFIX + "§cDu hast bereits abgestimmt!");
                    } else
                        p.sendMessage(Main.getPlugin().PREFIX + "§cBitte benutze §r/vote <Name>§c!");
                } else
                    p.sendMessage(Main.getPlugin().PREFIX + "§cEs ist gerade keine Voting-Phase. \n§6Wenn du unzufrieden mit deinem Bürgermeister bist schreibe §r/putsch§6, um zu versuchen ihn zu stürzen!");
            }
            if (p.hasPermission("wintervillage.blueteam") || p.hasPermission("wintervillage.prisonblue")) {
                if (Main.getPlugin().getVoteOpen() == "true" || Main.getPlugin().getPutschBlau() == true) {
                    if (args.length == 1) {
                        Scanner scanner = null;
                        boolean voted = false;
                        try {
                            scanner = new Scanner(new File("plugins//Vote//voted.yml"));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        while (scanner.hasNextLine()) {
                            String line = scanner.nextLine();
                            if (line.equals(p.getName())) {
                                voted = true;
                            }
                        }
                        if (voted == false) { //penis maybe error
                            voted = true;
                            if (Main.getPlugin().namesblue.contains(args[0].toLowerCase())) {
                                try (PrintWriter output = new PrintWriter(new FileWriter("plugins//Vote//votesblue.yml", true))) {
                                    output.printf("%s\r\n", args[0].toLowerCase());
                                } catch (Exception e) {
                                }
                                try (PrintWriter output = new PrintWriter(new FileWriter("plugins//Vote//voted.yml", true))) {
                                    output.printf("%s\r\n", p.getName());
                                } catch (Exception e) {
                                }
                                p.sendMessage(Main.getPlugin().PREFIX + "§3Du hast für §9" + args[0].toUpperCase() + " §3gestimmt!");
                            } else
                                p.sendMessage(Main.getPlugin().PREFIX + "§cDieser Spieler ist nicht in deinem Village");
                        } else
                            p.sendMessage(Main.getPlugin().PREFIX + "§cDu hast bereits abgestimmt!");
                    } else
                        p.sendMessage(Main.getPlugin().PREFIX + "§cBitte benutze §r/vote <Name>§c!");
                } else
                    p.sendMessage(Main.getPlugin().PREFIX + "§cEs ist gerade keine Voting-Phase. \n§6Wenn du unzufrieden mit deinem Bürgermeister bist schreibe §r/putsch§6, um zu versuchen ihn zu stürzen!");
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
            scannerRed = new Scanner(new File("plugins//Vote//votesred.yml"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scannerRed.hasNextLine()) {
            String line = scannerRed.nextLine();
            votesred.put(line.toLowerCase(), votesred.get(line.toLowerCase()) + 1);
        }
        Scanner scannerBlue = null;
        try {
            scannerBlue = new Scanner(new File("plugins//Vote//votesblue.yml"));
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

        //PENIS in config bürgermeister von false auf true
        FileUtils.write(new File("plugins//Vote//votesred.yml"), "", Charset.defaultCharset());
        FileUtils.write(new File("plugins//Vote//votesblue.yml"), "", Charset.defaultCharset());
        FileUtils.write(new File("plugins//Vote//voted.yml"), "", Charset.defaultCharset());

        Main.getPlugin().namesred.clear();
        Main.getPlugin().namesblue.clear();
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
