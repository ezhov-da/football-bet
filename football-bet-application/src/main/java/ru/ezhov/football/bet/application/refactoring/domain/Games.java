package ru.ezhov.football.bet.application.refactoring.domain;

import ru.ezhov.football.bet.application.refactoring.dao.GamesDao;
import ru.ezhov.football.bet.application.refactoring.infrastructure.DataSource;

import java.util.List;

public class Games {
    private GamesDao gamesDao;

    public Games() {
        gamesDao = new GamesDao(new DataSource());
    }

    public List<Game> getGames(String username) throws GamesException {
        try {
            return gamesDao.games(username);
        } catch (Exception e) {
            throw new GamesException(e);
        }
    }
}
