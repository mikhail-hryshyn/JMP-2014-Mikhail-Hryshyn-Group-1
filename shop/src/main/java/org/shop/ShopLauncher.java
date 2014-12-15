package org.shop;

import java.util.Map;

import org.apache.log4j.Logger;
import org.shop.data.Product;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


/**
 * The ShopLauncher class.
 */
public class ShopLauncher {
    
	final static Logger logger = Logger.getLogger(ShopLauncher.class);
	
	/**
     * The main method.
     *
     * @param args the arguments
     */
    public static void main(String[] args) {
    	logger.info("Initializing Spring context.");
        
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/application-context.xml");
        
        logger.info("Get been by name:");
        Product product = (Product) applicationContext.getBean("product1");
        logger.info(product.toString());
        
        logger.info("Get beens by type:");
        Map<?, ?> products = applicationContext.getBeansOfType(Product.class);
        for (Object key : products.keySet())
        {
        	logger.info(products.get(key).toString());
		}
        
        logger.info("Get beens by name and type:");
        product = (Product) applicationContext.getBean("product2", Product.class);
        logger.info(product.toString());
        
        logger.info("Get beens by alias:");
        product = (Product) applicationContext.getBean("alias_p3");
        logger.info(product.toString());
    }
}
