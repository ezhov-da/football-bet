package ru.ezhov.football.bet.application.refactoring.domain;

import ru.ezhov.football.bet.application.refactoring.dao.PlayersDao;
import ru.ezhov.football.bet.application.refactoring.infrastructure.DataSource;

import java.util.List;

public class Players {
    private PlayersDao playersDao;

    public Players() {
        this.playersDao = new PlayersDao(new DataSource());
    }

    public List<Player> players(String username) throws PlayersException {
        try {
            return playersDao.players(username);
        } catch (Exception e) {
            throw new PlayersException(e);
        }
    }

    public Player playerBy(String username) throws PlayerNotFoundException {
        try {
            return playersDao.playerBy(username);
        } catch (Exception e) {
            throw new PlayerNotFoundException(e);
        }
    }

    public Player save(NewPlayer player) throws PlayersException {
        try {
            return playersDao.save(player.getFio(), player.getUsername(), player.getPassword(), player.getDoubleFio(), player.getDoubleUsername(), player.getWinner());
        } catch (Exception e) {
            throw new PlayersException(e);
        }
    }

    public Player authPlayer(String username, String password) throws PlayerAuthorizationException {
        try {
            return playersDao.authPlayer(username, password);
        } catch (Exception e) {
            throw new PlayerAuthorizationException(e);
        }
    }

    public boolean existsPlayer(String fio, String username, String doubleFio, String doubleUsername) throws PlayersException {
        try {
            return playersDao.existsPlayer(fio, username, doubleFio, doubleUsername);
        } catch (Exception e) {
            throw new PlayersException(e);
        }
    }
}
