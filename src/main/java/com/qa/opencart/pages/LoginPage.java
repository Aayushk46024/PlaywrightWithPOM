package com.qa.opencart.pages;

import com.microsoft.playwright.Page;

public class LoginPage {

    Page page;

    private String emailId= "//input[@id='input-email']";
    private String password = "//input[@id='input-password']";
    private String loginBtn = "//input[@value='Login']";
    private String forgetPassword = "//div[@class='form-group']//a[normalize-space()='Forgotten Password']";
    private String logoutBtnLink = "//a[@class='list-group-item'][normalize-space()='Logout']";

    public LoginPage(Page page) {
        this.page = page;
    }

    public String getLoginPageTitle() {
        return page.title();
    }

    public boolean isForgotPwdVisible() {
        return page.isVisible(forgetPassword);
    }

    public boolean doLogin(String appUserName, String appPassword) {
        System.out.println("App credential :- " + appUserName + ":" + appPassword);
        page.fill(emailId, appUserName);
        page.fill(password, appPassword);
        page.click(loginBtn);
        if(page.isVisible(logoutBtnLink)) {
            System.out.println("User is logged in successfully....");
            return true;
        }
        return false;
    }
}
