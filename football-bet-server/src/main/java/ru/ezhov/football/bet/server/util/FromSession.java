package ru.ezhov.football.bet.server.util;

import ru.ezhov.football.bet.application.refactoring.domain.Player;
import spark.Request;
import spark.Session;

public class FromSession {
    private Session session;

    public FromSession(Session session) {
        this.session = session;
    }

    public FromSession(Request request) {
        this(request.session(true));
    }

    public Player player() throws NotFoundValueInSession {
        Player player = session.attribute("player");
        if (player == null) {
            throw new NotFoundValueInSession("Not found value [player] in session");
        }
        return player;
    }
}
