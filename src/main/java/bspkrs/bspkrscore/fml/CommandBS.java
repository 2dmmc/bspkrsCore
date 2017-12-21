package bspkrs.bspkrscore.fml;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.command.*;
import bspkrs.util.ModVersionChecker;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

import javax.annotation.Nullable;

@SuppressWarnings("unchecked")
public class CommandBS extends CommandBase
{
    @SuppressWarnings("rawtypes")
    private static List version = new ArrayList();

    static
    {
        version.add("version");
    }

    @Override
    public String getCommandName()
    {
        return "bs";
    }

    @Override
    public String getCommandUsage(ICommandSender sender)
    {
        return "commands.bs.usage";
    }

    @Override
    public void execute(MinecraftServer minecraftServer, ICommandSender sender, String[] args) throws CommandException {
        if (!bspkrsCoreMod.instance.allowUpdateCheck)
            throw new WrongUsageException("commands.bs.disabled");

        if (args.length != 2)
            throw new WrongUsageException("commands.bs.usage");

        if (!args[0].equalsIgnoreCase("version"))
            throw new WrongUsageException("commands.bs.usage");

        String[] message = ModVersionChecker.checkVersionForMod(args[1]);

        for (String s : message)
            sender.addChatMessage(new TextComponentString(s));
    }

    @Override
    public boolean checkPermission(MinecraftServer p_checkPermission_1_, ICommandSender p_checkPermission_2_) {
        return true;
    }

    @Override
    public int getRequiredPermissionLevel()
    {
        return 1;
    }

    @Override
    public List<String> getTabCompletionOptions(MinecraftServer p_getTabCompletionOptions_1_, ICommandSender p_getTabCompletionOptions_2_, String[] args, @Nullable BlockPos p_getTabCompletionOptions_4_) {
        return args.length == 2 ? getListOfStringsMatchingLastWord(args, ModVersionChecker.getVersionCheckerMap().keySet().toArray(new String[] {})) : args.length == 1 ? version : null;
    }

    @Override
    public int compareTo(ICommand iCommand) {
        if (iCommand instanceof CommandBase)
            return this.getCommandName().compareTo(((CommandBase) iCommand).getCommandName());

        return 0;
    }
}
