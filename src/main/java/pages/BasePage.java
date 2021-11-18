package pages;

import com.microsoft.playwright.Page;

import java.io.*;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import static org.testng.Assert.assertEquals;

public abstract class BasePage {
    Logger log = Logger.getLogger("BasePage");
    protected Page page;
    protected static Properties locatorRepository;
    public BasePage(Page page) {

        try {
            this.page = page;

            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(Objects.requireNonNull(classLoader.getResource(System.getProperty("tool") + ".properties")).getFile());
            String absolutePath = file.getAbsolutePath();
            InputStream configFile = new FileInputStream(absolutePath);
            locatorRepository = new Properties();
            locatorRepository.load(configFile);
        }catch (IOException e) {
            log.info("Could not read the repository file ...");
        }
    }
}