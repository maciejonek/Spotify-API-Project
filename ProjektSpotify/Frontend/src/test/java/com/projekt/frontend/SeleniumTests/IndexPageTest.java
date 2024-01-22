package com.projekt.frontend.SeleniumTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class IndexPageTest {

    private WebDriver webDriver;
    private IndexPage page;

    private WebDriverWait wait;
    @BeforeEach
    void setUp() {
        webDriver = new ChromeDriver();
        page = new IndexPage(webDriver);
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(5));
    }

    @Test
    public void clickOnSpotifyLogin() {
        page.open();
        page.clickOnConnectButton();
        page.spotifyLogin();
    }

    @Test
    public void editPlaylist(){
        clickOnSpotifyLogin();
        page.clickOnPlaylist();
        page.editPlaylistDetails("new name", "new description");
        page.clickOnStartButton();
    }
    @Test
    public void undoEditPlaylist(){
        clickOnSpotifyLogin();
        page.clickOnPlaylist();
        page.editPlaylistDetails("TestPlaylist1", "old description");
    }

    @Test
    public void addTrack(){
        clickOnSpotifyLogin();
        page.clickOnPlaylist();
        page.addTrack();
    }

    @Test
    public void deleteTrack(){
        clickOnSpotifyLogin();
        page.clickOnPlaylist();
        page.deleteTrack();
    }



}
