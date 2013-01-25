package com.garbagemule.MobArena.mortl8324;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import com.garbagemule.MobArena.Msg;

public class Methods 
{
	private static final String prefix = ChatColor.WHITE + "[" + ChatColor.DARK_RED + "Horde" + ChatColor.WHITE + "] ";
	private static final File file = new File("plugins/MobArena/arenaNames.txt");
	
	public static void broadcastAll(String msg) 
	{
    	Bukkit.broadcastMessage(prefix + msg);
    }
	
	public static void broadcastArenaOpen(String name)
	{
		broadcastAll(ChatColor.RED + name + ChatColor.WHITE + Msg.ARENA_OPEN);
	}
	
	public static String findArenaName(String keyName)
	{
		keyName=keyName.toLowerCase();
		if (file.exists()==false)
    	{
			createArenaNameFile();
    	}
		if (file.exists()==true)
		{
			String name=null;
			Scanner sf=null;
	        try 
	        {
				sf=new Scanner(file);
			} 
	        catch (FileNotFoundException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        sf.useDelimiter("\r\n|\n|\r"); 
	        
	        while (sf.hasNext())
	        {
	        	String thisLine=sf.next();
	        	if (thisLine!=null && thisLine!="" && thisLine!=" ")
	        	{
	        		int delimiterLocation=0;
	        		for (int x=0; x<thisLine.length(); x++)
	        		{
	        			if (thisLine.substring(x,x+1).equals("="))
	        			{
	        				delimiterLocation=x;
	        			}
	        		}
	        		if (delimiterLocation==0)
	        		{
	        			return keyName;
	        		}
		        	if ((thisLine.substring(0, delimiterLocation)).equals(keyName))
		        	{
		        		name=thisLine.substring(keyName.length()+1,thisLine.length());
		        	}
	        	}
	        }
	        sf.close();
	        if (name==null)
	        {
	        	return keyName;
	        }
			return name;
		}
		return keyName;
	}
	
	public static String[] createAvailableList(String list[])
	{
		ArrayList<String> index=new ArrayList<String>();
		for (int x=0; x<list.length; x++)
		{
			list[x]=findArenaName(list[x]);
			//Bukkit.broadcastMessage(list[x]);
			if (list[x]!=null && list[x]!="" && list[x]!=" ")
	    	{
	    		int delimiterLocation=0;
	    		for (int y=0; y<list[x].length(); y++)
	    		{
	    			if (list[x].substring(y,y+1).equals(":"))
	    			{
	    				delimiterLocation=y;
	    			}
	    		}
	    		if (delimiterLocation!=0)
	    		{
		    		String indexID=list[x].substring(0, delimiterLocation);
		    		Boolean exist=false;
		    		for (int y=0; y<index.size(); y++)
		    		{
		    			if (indexID.equals(index.get(y)))
		    			{
		    				exist=true;
		    			}
		    		}
		    		if (exist==false)
		    		{
		    			index.add(indexID);
		    		}	
	    		}
	    	}
		}
		String[][] available=new String[index.size()][15];
		int[] indexAmount=new int[index.size()];
		for (int x=0; x<index.size(); x++)
		{
			indexAmount[x]=0;
		}
		for (int x=0; x<list.length; x++)
		{
			if (list[x]!=null && list[x]!="" && list[x]!=" ")
	    	{
	    		int delimiterLocation=0;
	    		for (int y=0; y<list[x].length(); y++)
	    		{
	    			if (list[x].substring(y,y+1).equals(":"))
	    			{
	    				delimiterLocation=y;
	    			}
	    		}
	    		//Bukkit.broadcastMessage(list[x]+", DelimiterLocation: "+delimiterLocation);
	    		//Bukkit.broadcastMessage(list[x].substring(0, delimiterLocation+1));
	    		String indexID=list[x].substring(0, delimiterLocation);
	    		for (int y=0; y<index.size(); y++)
	    		{
	    			//Bukkit.broadcastMessage("'"+indexID+"'");
		    		//Bukkit.broadcastMessage("'"+index.get(y)+"'");
	    			if (indexID.equals(index.get(y)))
	    			{
	    				available[y][indexAmount[y]]=list[x].substring(delimiterLocation+2, list[x].length());
	    				//Bukkit.broadcastMessage("Final: ["+y+"]["+indexAmount[y]+"], "+available[y][indexAmount[y]]);
	    				indexAmount[y]=indexAmount[y]+1;
	    			}
	    		}
	    	}
		}
		String[] finalAvailable=new String[index.size()];
		for(int x=0; x<available.length; x++)
		{
			String temp="";
			for(int y=0; y<available[x].length; y++)
			{
				//Bukkit.broadcastMessage("Final: ["+y+"]["+x+"], "+available[x][y]);
				if (available[x][y]!=null)
					temp=temp+available[x][y];
				if (y!=available[x].length-1)
				{
					if (available[x][y+1]!=null)
						temp=temp+", ";
				}
			}
			finalAvailable[x]=" - "+ChatColor.RED+index.get(x)+ChatColor.WHITE+": "+temp;
		}
		return finalAvailable;
	}
	
	public static void createArenaNameFile()
    {
    	if (file.exists()==false)
    	{
	    	FileWriter fw = null;
			try 
			{
				fw = new FileWriter(file);
			} 
			catch (IOException e)
			{
				e.printStackTrace();
			}
			PrintWriter pw=new PrintWriter(fw);
			
			pw.println("vipspace=Dread Space: VIP Arena");
			pw.println("space=Dread Space: Arena 1");
			pw.println("space2=Dread Space: Arena 2");
			pw.println("space3=Dread Space: Arena 3");
			pw.println("space4=Dread Space: Arena 4");
			
			pw.println("vipcold=Cold Dead Hordes: VIP Arena");
			pw.println("cold=Cold Dead Hordes: Arena 1");
			pw.println("cold2=Cold Dead Hordes: Arena 2");
			pw.println("cold3=Cold Dead Hordes: Arena 3");
			pw.println("cold4=Cold Dead Hordes: Arena 4");
			
			pw.println("vipdawn=Dawn of the Horde: VIP Arena");
			pw.println("dawn=Dawn of the Horde: Arena 1");
			pw.println("dawn2=Dawn of the Horde: Arena 2");
			pw.println("dawn3=Dawn of the Horde: Arena 3");
			pw.println("dawn4=Dawn of the Horde: Arena 4");
			
			pw.println("vipcurse=Curse of the Horde: VIP Arena");
			pw.println("curse=Curse of the Horde: Arena 1");
			pw.println("curse2=Curse of the Horde: Arena 2");
			pw.println("curse3=Curse of the Horde: Arena 3");
			pw.println("curse4=Curse of the Horde: Arena 4");
			
			pw.println("viplands=The Hordelands: VIP Arena");
			pw.println("lands=The Hordelands: Arena 1");
			pw.println("lands2=The Hordelands: Arena 2");
			pw.println("lands3=The Hordelands: Arena 3");
			pw.println("lands4=The Hordelands: Arena 4");
			
			try 
			{
				pw.close();
				fw.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
    	}
    }
}
