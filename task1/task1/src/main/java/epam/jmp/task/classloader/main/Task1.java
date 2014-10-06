package epam.jmp.task.classloader.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.log4j.Logger;

import epam.jmp.task.classloader.JarClassLoader;
import epam.jmp.task.classloader.core.IAppPlugin;

/**
 * 
 * @author Henadzi_Lukashenka
 *
 */
public class Task1
{
	static final Logger logger = Logger.getLogger(Task1.class);
	
    public static void main( String[] args ) throws Exception
    {
    	String pluginDir = menuStr(
    			"Enter plugin folder(default \"" + IAppPlugin.PLUGIN_DIR + "\"): ", IAppPlugin.PLUGIN_DIR);
    	String jarFile = menuStr(
    			"Enter plugin jar-file name: ", "");
    	
    	String pluginPackage = menuStr(
    			"Enter plugin package(default \"" + IAppPlugin.PLUGIN_PACKAGE + "\"): ", IAppPlugin.PLUGIN_PACKAGE);

        JarClassLoader jarClassLoader = new JarClassLoader(pluginDir, jarFile, pluginPackage);
        try
        {
			Class<?> clazz = jarClassLoader.loadClass("AppPluginImpl");
			IAppPlugin plugin = (IAppPlugin) clazz.newInstance();
			plugin.execute();
		}
        catch (ClassNotFoundException e)
		{
        	logger.error(e);
		}
    }
    
    /**
     * 
     * @param defValue
     * @return
     */
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
