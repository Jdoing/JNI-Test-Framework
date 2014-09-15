package com.sandisk.zsjtf;

import java.util.Properties;

/**
 * Created by ray on 7/9/14.
 */
public class JTFCommandFactory
{
    public static JTFCommand generateCommandObject(String rawCommand) throws Exception
    {
    	
        String[] tokens = rawCommand.split("\\s+");
        JTFCommand command = (JTFCommand) Class.forName("com.sandisk.zsjtf.command." + tokens[0]).newInstance();
        command.setArgs(parse(tokens));
        return command;
    }

    private static Properties parse(String[] tokens)
    {
        Properties args = new Properties();

        for (int i = 0; i < tokens.length; ++i) {
            if (i == 0) {
                continue;
            }
            String[] arg = tokens[i].split("=");
            args.setProperty(arg[0], arg[1]);
        }

        return args;
    }
}

