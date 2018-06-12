package ru.ezhov.football.bet.application.refactoring.domain;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class PlayersTest {
    @Test
    public void players() throws Exception {
        List<Player> playerList = new Players().players("1");
        System.out.println(Arrays.toString(playerList.toArray()));
    }

}