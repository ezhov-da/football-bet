package ru.ezhov.football.bet.server.filters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ezhov.football.bet.application.refactoring.domain.Player;
import spark.Filter;
import spark.Request;
import spark.Response;
import spark.Session;

public class AuthorizationFilter implements Filter {
    public static final Logger LOG = LoggerFactory.getLogger(AuthorizationFilter.class);

    @Override
    public void handle(Request request, Response response) throws Exception {
        Session session = request.session(true);
        Player player = session.attribute("player");
        if (player == null) {
            String pathInfo = request.pathInfo();
            LOG.info(pathInfo);
            if (pathInfo.contains("registration")) {
                //
            } else if (!pathInfo.contains("authorization")) {
                System.out.println(request.pathInfo());
                response.redirect("/authorization");
            }
        }
    }
}
