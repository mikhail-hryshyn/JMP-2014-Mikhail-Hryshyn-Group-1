package epam.jmp.task.classloader;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.apache.log4j.Logger;

public class JarClassLoader extends ClassLoader
{
	static final Logger logger = Logger.getLogger(JarClassLoader.class);

    private HashMap<String, Class<?>> cache = new HashMap<String, Class<?>>();

    private String jarFilePath;
    
    private String jarFileName;

    private String packageName;

    private static String WARNING = "Warning : No jar file found. Please verify your path.";

    public JarClassLoader(String jarFilePath, String jarFileName, String packageName)
    {    
    	this.jarFilePath = jarFilePath;
    	this.jarFileName = jarFileName;        
        this.packageName = packageName;
        cacheClasses();
    }


    private void cacheClasses()
    {
        try
        {           
            JarFile jarFile = findFile();
            Enumeration<JarEntry> entries = jarFile.entries();

            while (entries.hasMoreElements())
            {
                JarEntry jarEntry = (JarEntry) entries.nextElement();

                if (match(normalize(jarEntry.getName()), packageName))
                {                   
                    byte[] classData = loadClassData(jarFile, jarEntry);                   

                    if (classData != null)
                    {
                        Class<?> clazz = defineClass(stripClassName(normalize(jarEntry.getName())), classData, 0, classData.length);
                        cache.put(clazz.getName(), clazz);
                        logger.info("== class " + clazz.getName() + " loaded in cache");
                    }
                }
            }
        }
        catch (IOException IOE)
        {
            logger.info(WARNING);
        }
    }

    public synchronized Class<?> loadClass(String name) throws ClassNotFoundException
    {
        Class<?> result = cache.get(name);

        if (result == null)
            result = cache.get(packageName + "." + name);           

        if (result == null)
            result = super.findSystemClass(name);       

        logger.info("== loadClass(" + name + ")");       

        return result;

    }

    private String stripClassName(String className)
    {
        return className.substring(0, className.length() - 6);
    }

    private String normalize(String className)
    {
        return className.replace('/', '.');
    }

    private boolean match(String className, String packageName)
    {
        return className.startsWith(packageName) && className.endsWith(".class");
    }   

    private byte[] loadClassData(JarFile jarFile, JarEntry jarEntry) throws IOException
    {

        long size = jarEntry.getSize();     

        if (size == -1 || size == 0)
            return null;
       
        byte[] data = new byte[(int)size];
        InputStream in = jarFile.getInputStream(jarEntry);
        in.read(data);

        return data;
    }
    
    private JarFile findFile() throws IOException
    {
    	return new JarFile(new File(jarFilePath).getPath()
                + File.separatorChar + jarFileName.replace('/', File.separatorChar));
    }
}