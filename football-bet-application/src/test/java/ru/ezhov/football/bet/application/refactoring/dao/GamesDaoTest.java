package ru.ezhov.football.bet.application.refactoring.dao;

import org.junit.Test;
import ru.ezhov.football.bet.application.refactoring.domain.Game;
import ru.ezhov.football.bet.application.refactoring.infrastructure.DataSource;

import java.util.Arrays;
import java.util.List;

public class GamesDaoTest {
    @Test
    public void games() throws Exception {
        GamesDao gamesDao = new GamesDao(new DataSource());
        List<Game> gameList = gamesDao.games("ezhov_da");
        System.out.println(Arrays.toString(gameList.toArray()));
    }

}