package builder;

import model.LogTemplate;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Logger;

public class LogMessageBuilder {

    public LogMessageBuilder() {}

//    public LogMessageBuilder(Logger logger,
//                             String customer_no,
//                             String corrID,
//                             String className,
//                             String humanReadable,
//                             String part,
//                             Level level)
//    {
//        LogTemplate loggingMessage = new LogTemplate(customer_no, corrID, humanReadable, className, part);
//        logger.log(level, loggingMessage);
//    }

    public static void Log(Logger logger,
                           String customer_no,
                           String corrID,
                           String className,
                           String humanReadable,
                           String part,
                           Level level)
    {
        LogTemplate loggingMessage = new LogTemplate(customer_no, corrID, humanReadable, className, part);
        logger.log(level, loggingMessage);
    }

}
