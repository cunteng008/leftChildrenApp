package log;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4j {
   
	static Logger logger = LogManager.getLogger("mylog");  
    
	public static void info(String string){ 
        logger.trace("entry");  

        logger.info("注意："+string);  
          
        logger.trace("exit");  
    }
    public static void error(String string){  
        logger.trace("entry");  
          
        logger.error(string+"出错!");  
          
        logger.trace("exit");  
    }
    public static void debug(String string){  
        logger.trace("entry"); 
        
        logger.debug("调试内容为："+string);  
          
        logger.trace("exit");  	          
    }
    public static void warn(String string){  
        logger.trace("entry");  
          
        logger.warn("警告："+string);  
          
        logger.trace("exit");  
    }
    public static void fatal(String string){  
        logger.trace("entry"); 
          
        logger.fatal(string+"发生严重错误");  
          
        logger.trace("exit");  
    }
}
