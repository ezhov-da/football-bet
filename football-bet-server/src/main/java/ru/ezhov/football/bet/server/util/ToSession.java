package ru.ezhov.football.bet.server.util;

import ru.ezhov.football.bet.application.refactoring.domain.Player;
import spark.Request;
import spark.Session;

public class ToSession {
    private Session session;

    public ToSession(Session session) {
        this.session = session;
    }

    public ToSession(Request request) {
        this(request.session(true));
    }

    public void player(Player player) {
        session.attribute("player", player);
    }
}
