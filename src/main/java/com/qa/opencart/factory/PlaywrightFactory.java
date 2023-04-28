package com.qa.opencart.factory;

import com.microsoft.playwright.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PlaywrightFactory {
    Playwright playwright;
    Browser browser;
    BrowserContext browserContext;
    Page page;
    Properties prop;

    private static ThreadLocal<Browser> browserThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> browserContextThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<Page> pageThreadLocal = new ThreadLocal<>();
    private static ThreadLocal<Playwright> playwrightThreadLocal = new ThreadLocal<>();


    public static Playwright getPlaywright() {
        return playwrightThreadLocal.get();
    }

    public static Browser getBrowser() {
        return browserThreadLocal.get();
    }

    public static BrowserContext getBrowserContext() {
        return browserContextThreadLocal.get();
    }

    public static Page getPage() {
        return pageThreadLocal.get();
    }


    public Page initBrowser(Properties prop) {

        String browserName = prop.getProperty("browser").trim();
        System.out.println("bowser name is :- " + browserName);

//        playwright = Playwright.create();
        playwrightThreadLocal.set(Playwright.create());

        switch (browserName.toLowerCase()) {
            case "chromium":
//                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
                browserThreadLocal.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            case "firefox":
//                browser = playwright.firefox().launch(new BrowserType.LaunchOptions().setHeadless(false));
                browserThreadLocal.set(getPlaywright().firefox().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            case "safari":
//                browser = playwright.webkit().launch(new BrowserType.LaunchOptions().setHeadless(false));
                browserThreadLocal.set(getPlaywright().webkit().launch(new BrowserType.LaunchOptions().setHeadless(false)));
                break;
            case "chrome":
//                browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false));
                browserThreadLocal.set(getPlaywright().chromium().launch(new BrowserType.LaunchOptions().setChannel("chrome").setHeadless(false)));
                break;
            default:
                System.out.println("Please pass the correct browser name.");
                break;
        }

//        browserContext = browser.newContext();
//        page = browserContext.newPage();
//        page.navigate(prop.getProperty("url").trim());
//        return page;

        browserContextThreadLocal.set(getBrowser().newContext());
        pageThreadLocal.set(getBrowserContext().newPage());
        getPage().navigate(prop.getProperty("url"));
        return getPage();
    }

    public Properties initProp() {
        try {
            FileInputStream ip = new FileInputStream("./src/test/resources/config/config.properties");
            prop = new Properties();
            prop.load(ip);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return prop;
    }
}