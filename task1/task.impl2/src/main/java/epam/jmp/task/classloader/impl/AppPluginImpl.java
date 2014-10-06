package epam.jmp.task.classloader.impl;

import org.apache.log4j.Logger;

import epam.jmp.task.classloader.core.IAppPlugin;

public class AppPluginImpl implements IAppPlugin
{
	static final Logger logger = Logger.getLogger(AppPluginImpl.class);

	public void execute()
	{
		logger.info("AppPluginImpl_2:  run execute().");

	}

}
