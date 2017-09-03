package com.github.adam6806.catamaranindex.scraper.webdriver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.io.File;
import java.io.FileNotFoundException;

@Component
@PropertySource(value = {"classpath:application.properties"})
public class WebDriverFactory {

    private static Environment environment;

    @Inject
    private static Driver webDriver;

    @Autowired
    public WebDriverFactory(Environment environment) {
        WebDriverFactory.environment = environment;
    }

    public static Driver getWebDriver() throws FileNotFoundException {
        File geckoDriver = new File(environment.getRequiredProperty("geckodriver.path"));
        if (!geckoDriver.exists()) {
            throw new FileNotFoundException("You are missing the geckodriver.exe file. " +
                    "Download from the internet and configure path with application.properties");
        }
        System.setProperty("webdriver.gecko.driver", geckoDriver.getPath());
        return webDriver;
    }
}
