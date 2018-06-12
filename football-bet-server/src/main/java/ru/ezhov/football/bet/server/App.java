package ru.ezhov.football.bet.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ezhov.football.bet.application.refactoring.infrastructure.AdminLoadException;
import ru.ezhov.football.bet.application.refactoring.infrastructure.AdminService;
import ru.ezhov.football.bet.server.controllers.GetControllerAuthorization;
import ru.ezhov.football.bet.server.controllers.GetControllerComboBoxAllGames;
import ru.ezhov.football.bet.server.controllers.GetControllerComboBoxGames;
import ru.ezhov.football.bet.server.controllers.GetControllerComboBoxPlayers;
import ru.ezhov.football.bet.server.controllers.GetControllerFinalResult;
import ru.ezhov.football.bet.server.controllers.GetControllerForecast;
import ru.ezhov.football.bet.server.controllers.GetControllerGameResult;
import ru.ezhov.football.bet.server.controllers.GetControllerIndex;
import ru.ezhov.football.bet.server.controllers.GetControllerLogout;
import ru.ezhov.football.bet.server.controllers.GetControllerRegistration;
import ru.ezhov.football.bet.server.controllers.PostControllerAuthorization;
import ru.ezhov.football.bet.server.controllers.PostControllerRegisterGameScore;
import ru.ezhov.football.bet.server.controllers.PostControllerRegisterScore;
import ru.ezhov.football.bet.server.controllers.PostControllerRegistration;
import ru.ezhov.football.bet.server.filters.AuthorizationFilter;
import spark.Spark;

import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.post;

/**
 * Hello world!
 */
public class App {
    public static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        try {
            AdminService.load();

            port(50121);

            Spark.staticFiles.location("/staticFiles");
            before(new AuthorizationFilter());
            get("/", new GetControllerIndex());
            get("/logout", new GetControllerLogout());
            get("/comboBoxPlayers", new GetControllerComboBoxPlayers());
            get("/comboBoxGames", new GetControllerComboBoxGames());
            get("/allComboBoxGames", new GetControllerComboBoxAllGames());
            post("/registerScore", new PostControllerRegisterScore());
            post("/registerScoreGame", new PostControllerRegisterGameScore());
            get("/gameResult", new GetControllerGameResult());
            get("/finalResult", new GetControllerFinalResult());
            get("/forecast", new GetControllerForecast());
            get("/registration", new GetControllerRegistration());
            post("/registration", new PostControllerRegistration());
            get("/authorization", new GetControllerAuthorization());
            post("/authorization", new PostControllerAuthorization());
        } catch (AdminLoadException e) {
            e.printStackTrace();
        }
    }
}
