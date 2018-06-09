package ru.ezhov.football.bet.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.ezhov.football.bet.application.refactoring.domain.Player;
import spark.ModelAndView;
import spark.Session;
import spark.Spark;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.Map;

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
        port(50121);

        Spark.staticFiles.location("/staticFiles");

        before((request, response) -> {
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
        });

        get("/", (req, res) -> {
            Map<String, Object> model = new HashMap<>();
            return new VelocityTemplateEngine().render(
                    new ModelAndView(model, "index.html")
            );
        });

        get("/comboBoxPlayers", (req, res) -> "Hello World");
        get("/comboBoxGames", (req, res) -> "Hello World");

        post("/registerScore", (req, res) -> "Hello World");

        get("/gameResult", (req, res) -> "Hello World");
        get("/finalResult", (req, res) -> "Hello World");
        get("/forecast", (req, res) -> "Hello World");

        get("/registration", (req, res) -> {
                    Map<String, Object> model = new HashMap<>();
                    return new VelocityTemplateEngine().render(
                            new ModelAndView(model, "registration.html")
                    );
                }
        );
        post("/registration", (req, res) -> "Hello World");

        get("/authorization", (req, res) -> {
                    Map<String, Object> model = new HashMap<>();
                    return new VelocityTemplateEngine().render(
                            new ModelAndView(model, "login.html")
                    );
                }
        );
        post("/authorization", (req, res) -> {
            Session session = req.session(true);
            Player player = session.attribute("player");
            if (player == null) {
                session.attribute("player", new Player());
            }
            req.params().forEach((String s, String s2) -> {
                System.out.println(s + " " + s2);
            });

            return "/";
        });

    }
}
