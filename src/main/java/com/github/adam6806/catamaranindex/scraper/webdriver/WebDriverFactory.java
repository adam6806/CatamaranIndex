package com.github.adam6806.catamaranindex.scraper.webdriver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;

@Component
@PropertySource(value = {"classpath:application.properties"})
public class WebDriverFactory {

    private static ResourceLoader resourceLoader;

    private static Environment environment;
    private static Driver webDriver;

    @Autowired
    public WebDriverFactory(Environment environment, ResourceLoader resourceLoader) {
        WebDriverFactory.environment = environment;
        WebDriverFactory.resourceLoader = resourceLoader;
    }

    public static Driver getWebDriver() throws FileNotFoundException {
        webDriver = new Driver(resourceLoader);
        return webDriver;
    }
}
