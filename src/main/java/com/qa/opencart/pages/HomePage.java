package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class HomePage {
    private Page page;

    private String search = "input[name='search']";
    private  String searchBtn = "div#search button";
    private String searchPageHeader = "div#content h1";

    public HomePage(Page page) {
        this.page = page;
    }

    public String getHomePageTitle(){
        return page.title();
    }

    public String getHomePageURL() {
        return page.url();
    }

    public String doSearch(String productName) {
        page.fill(search, productName);
        page.click(searchBtn);
        String header = page.textContent(searchPageHeader);
        System.out.println("Search header is :- " + header);
        return header;
    }
}