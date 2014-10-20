package epam.jmp.task.memory_managementm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import epam.jmp.task.classloader.JarClassLoader;
import epam.jmp.task.classloader.core.IAppPlugin;

public class App 
{
	static final Logger logger = Logger.getLogger(App.class);
	
	public static void main( String[] args )
	{
		System.out.println( "Please choose:" );
		System.out.println( "1. java.lang.OutOfMemoryError: Java heap space." );
		System.out.println( "2. java.lang.OutOfMemoryError: PermGen space." );
		
		int inputValue = readInput(1);
		
		switch (inputValue) {
		case 1:
			logger.info( "1. java.lang.OutOfMemoryError: Java heap space.");
			heapSpace();
			break;
		case 2:
			logger.info( "2. java.lang.OutOfMemoryError: PermGen space.");
			permGenSpace();
			break;

		default:
			break;
		}
	}
	
	private static void heapSpace()
	{
		int initialSize = 1024;
		Map<Integer, int[]> objMap = new HashMap<Integer, int[]>();
		int i = 1;
		
		try
		{
			while (i >0)
			{
				int size = initialSize * i++;
				logger.info( "Create object: int[" + String.valueOf(size) + "]" );
				objMap.put(size, new int[size]);
			}
		}
		catch (Throwable e)
		{
			logger.error(e);
		}
		
	}
	
	private static void permGenSpace()
	{
		Map<Integer, Class<?>> clazzMap = new HashMap<Integer, Class<?>>();
		int i = 1;
		
		String pluginDir = menuStr(
    			"Enter plugin folder(default \"" + IAppPlugin.PLUGIN_DIR + "\"): ", IAppPlugin.PLUGIN_DIR);
    	String jarFile = menuStr(
    			"Enter plugin jar-file name: ", "");
    	
    	String pluginPackage = menuStr(
    			"Enter plugin package(default \"" + IAppPlugin.PLUGIN_PACKAGE + "\"): ", IAppPlugin.PLUGIN_PACKAGE);
		
		try
		{
			while (i > 0)
			{
				logger.info( "Step " + String.valueOf(i));

		        JarClassLoader jarClassLoader = new JarClassLoader(pluginDir, jarFile, pluginPackage);
		        try
		        {
					Class<?> clazz = jarClassLoader.loadClass("AppPluginImpl");
					IAppPlugin plugin = (IAppPlugin) clazz.newInstance();
					plugin.execute();
					clazzMap.put(i++, clazz);
				}
		        catch (ClassNotFoundException e)
				{
		        	logger.error(e);
				}
		        catch (ClassCastException e1)
		        {
		        	logger.error(e1);
		        }
			}
		}
		catch (Throwable e)
		{
			logger.error(e);
		}
	}
	

	private static int readInput(int defValue)
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String inputValue = null;
		try
		{
		   inputValue = br.readLine();
		}
		catch (IOException ioe)
		{
			System.out.println("IO error! Default value: " + defValue);
			System.exit(1);
		}
		
		try
		{
			 if (!(inputValue == null || inputValue.isEmpty()))
			 {
				 return Integer.parseInt(inputValue);
			 }
		}
		catch (NumberFormatException e)
		{
			System.out.println("IO error! Default value: " + defValue);
			System.exit(1);
		}
		
		return defValue;
	}
	
	private static String menuStr(String menuStr, String defValue)
    {
    	System.out.print(menuStr);
    		
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String inputValue = null;
        try
        {
           inputValue = br.readLine();
        }
        catch (IOException ioe)
        {
           System.out.println("IO error! Default value: " + defValue);
           System.exit(1);
        }
        
        if (!(inputValue == null || inputValue.isEmpty()))
        {
        	return inputValue;
        }
        else
        {
        	return defValue;
        }
    }
}
