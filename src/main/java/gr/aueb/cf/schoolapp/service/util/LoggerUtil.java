package gr.aueb.cf.schoolapp.service.util;

import org.slf4j.bridge.SLF4JBridgeHandler;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
/**
 * Utility class for configuring and obtaining a logger instance.
 */
public class LoggerUtil {
    private static final Logger logger = Logger.getLogger(LoggerUtil.class.getName());

    static {
        SLF4JBridgeHandler.install(); // Installs the bridge (JUL-to-SLF) Handler

        Handler fileHandler;
        try {
            fileHandler = new FileHandler("cf.log", true);  // true for appending to the file
            fileHandler.setFormatter(new SimpleFormatter());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        logger.addHandler(fileHandler);
    }

    private LoggerUtil() {}

    /**
     * Get the configured logger instance.
     *
     * @return The logger instance with the specified configuration.
     */
    public static Logger getCurrentLogger() {
        return logger;
    }
}
