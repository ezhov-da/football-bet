package ru.ezhov.football.bet.application.refactoring.domain;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class GamesTest {
    @Test
    public void getGames() throws Exception {
        Games games = new Games();
        List<Game> gameList = games.getGames("1");
        System.out.println(Arrays.toString(gameList.toArray()));
    }

}