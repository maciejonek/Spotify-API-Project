package com.projekt.frontend.SeleniumTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class IndexPage {
    private String spotifyLogin = System.getenv("SPOTIFY_TEST_USER_LOGIN");
    private String spotifyPassword = System.getenv("SPOTIFY_TEST_USER_PASSWORD");
    public static final String URL = "http://localhost:8082/index";

    private WebDriver webDriver;

    public IndexPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver,this);
    }

    public void open(){
        webDriver.get(URL);
    }

    public void fullscreen(){
        webDriver.manage().window().maximize();
    }

    public void hoverText(){
        WebElement optionsElement = webDriver.findElement(By.xpath("//*[@id=\"tracks-preview\"]/div[2]/p[2]"));
        Actions actions = new Actions(webDriver);
        webDriver.manage().window().maximize();
        actions.moveToElement(optionsElement);
        actions.build().perform();
        System.out.println(spotifyLogin+" "+spotifyPassword);
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }public void clickOnConnectButton(){
        WebElement button = webDriver.findElement(By.xpath("//*[@id=\"button-box\"]/a/button"));
        Actions actions = new Actions(webDriver);
        webDriver.manage().window().maximize();
        actions.moveToElement(button);
        actions.click().build().perform();
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
    }

    public void spotifyLogin(){
        WebElement loginInput = webDriver.findElement(By.xpath("//*[@id=\"login-username\"]"));
        WebElement passwordInput = webDriver.findElement(By.xpath("//*[@id=\"login-password\"]"));
        WebElement loginButton = webDriver.findElement(By.xpath("//*[@id=\"login-button\"]/span[1]"));

        loginInput.sendKeys(spotifyLogin);
        passwordInput.sendKeys(spotifyPassword);
        loginButton.click();
    }

    public void authorizeClick(){
        WebElement authorizeButton = webDriver.findElement(By.xpath("//*[@id=\"encore-web-main-content\"]/div/main/section/div/div/div[2]/div[1]/button/span[1]"));
        authorizeButton.click();
    }

    public void clickOnPlaylist(){
        WebElement playlistButton = webDriver.findElement(By.className("table-data"));
        playlistButton.click();
    }

    public void editPlaylistDetails(String name, String description){
        WebElement nameInput = webDriver.findElement(By.id("name"));
        WebElement descriptionInput = webDriver.findElement(By.id("description"));
        WebElement submitButton = webDriver.findElement(By.xpath("//*[@id=\"playlist-properties-inputs\"]/button"));

        nameInput.clear();
        nameInput.sendKeys(name);
        descriptionInput.clear();
        descriptionInput.sendKeys(description);
        submitButton.click();
    }

    public void clickOnStartButton(){
        WebElement startButton = webDriver.findElement(By.xpath("//*[@id=\"start-button\"]"));
        startButton.click();
    }

    public void addTrack(){
        WebElement addTrackButton = webDriver.findElement(By.xpath("//*[@id=\"moved3\"]/div[3]/table/tbody/tr[1]/td[3]/a/button"));
        addTrackButton.click();
    }

    public void deleteTrack(){
        WebElement trackToDelete = webDriver.findElement(By.xpath("//*[@id=\"moved2\"]//p[contains(text(), 'Numb')]"));
        trackToDelete.click();
    }





}
