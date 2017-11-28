package com.iamshift.mineaddons.cmds;

import java.util.ArrayList;
import java.util.List;

import com.iamshift.mineaddons.utils.TimeSkipHandler;

import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;

public class TimeSkipCommand extends CommandBase
{
	private String name = "timeskip";
	private List<String> alias = new ArrayList<String>();
	
	public TimeSkipCommand() 
	{
		alias.add("timeskip");
		alias.add("ts");
	}
	
	@Override
	public String getCommandName() 
	{
		return name;
	}
	
	@Override
	public List<String> getCommandAliases() 
	{
		return alias;
	}

	@Override
	public String getCommandUsage(ICommandSender sender) 
	{
		return "/timeskip [cancel]";
	}
	
	@Override
	public int getRequiredPermissionLevel() 
	{
		return 0;
	}
	
	@Override
	public boolean checkPermission(MinecraftServer server, ICommandSender sender) 
	{
		return true;
	}
	
	@Override
	public List<String> getTabCompletionOptions(MinecraftServer server, ICommandSender sender, String[] args, BlockPos pos) 
	{
		if(args.length == 1)
			return getListOfStringsMatchingLastWord(args, "cancel");
			
		return super.getTabCompletionOptions(server, sender, args, pos);
	}

	@Override
	public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException 
	{
		if(sender.getEntityWorld().isRemote)
			return;
		
		if(args.length < 1)
			return;
		
		TimeSkipHandler.cancelTimeSkip();
	}

}
