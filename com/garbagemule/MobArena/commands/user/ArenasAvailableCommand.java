package com.garbagemule.MobArena.commands.user;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.garbagemule.MobArena.Messenger;
import com.garbagemule.MobArena.commands.Command;
import com.garbagemule.MobArena.commands.CommandInfo;
import com.garbagemule.MobArena.commands.Commands;
import com.garbagemule.MobArena.framework.Arena;
import com.garbagemule.MobArena.framework.ArenaMaster;
import com.garbagemule.MobArena.mortl8324.Methods;

@CommandInfo(
    name    = "arenasavailable",
    pattern = "arena|arenas|open|open.*|openarena.*",
    usage   = "/arenas|/ma open",
    desc    = "lists all arenas that are available to join",
    permission = "mobarena.use.arenalist"
)
public class ArenasAvailableCommand implements Command
{
	@Override
    public boolean execute(ArenaMaster am, CommandSender sender, String... args) {
        List<Arena> arenas;
        List<String> arenasAvailable=new ArrayList<String>();
        
        if (Commands.isPlayer(sender)) {
            Player p = (Player) sender;
            arenas = am.getPermittedArenas(p); 
        } 
        else 
        {
            arenas = am.getArenas();
        }
        
        if (arenas.size()>0)
        {
	        for (int x=0; x<arenas.size(); x++)
	        {
	        	if (arenas.get(x).isRunning()==false)
	        	{
	        		arenasAvailable.add(arenas.get(x).arenaName());
	        	}
	        }
	        String[] finalArenasAvailable=new String[arenasAvailable.size()];
	        for (int x=0; x<arenasAvailable.size(); x++)
	        {
	        	finalArenasAvailable[x]=arenasAvailable.get(x);
	        }
	        String[] reallyFinalArenasAvailable=Methods.createAvailableList(finalArenasAvailable);
	        Messenger.tellPlayer(sender, "Available arenas:");
	        for(int x=0; x<reallyFinalArenasAvailable.length; x++)
	        {
	        	Messenger.tellPlayerNoPrefix(sender, reallyFinalArenasAvailable[x]);
	        }
        }
        else
        {
        	Messenger.tellPlayer(sender, "There are no available arenas at this time!");
        }
        return true;
    }
}
