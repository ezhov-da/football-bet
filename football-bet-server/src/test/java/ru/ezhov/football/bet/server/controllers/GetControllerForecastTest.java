package ru.ezhov.football.bet.server.controllers;

import org.junit.Test;
import ru.ezhov.football.bet.application.refactoring.domain.Player;
import spark.Request;
import spark.Response;
import spark.Session;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetControllerForecastTest {
    @Test
    public void handle() throws Exception {
        GetControllerForecast getControllerForecast = new GetControllerForecast();
        Player player = mock(Player.class);
        when(player.getDoubleUsername()).thenReturn("ezhov_da");
        when(player.getUsername()).thenReturn("javaf");
        Session session = mock(Session.class);
        when(session.attribute("player")).thenReturn(player);
        Request request = mock(Request.class);
        when(request.session(true)).thenReturn(session);

//        String s = getControllerForecast.handle(mock(Request.class), mock(Response.class));
//        System.out.println(s);


//        "{\n" +
//                "        'forecasts': [\n" +
//                "            {\n" +
//                "                'gameDate': '30.06.2018',\n" +
//                "                \"game\": \"Грузия - армения\",\n" +
//                "                \"forecast\": \"2:1\",\n" +
//                "            }\n" +
//                "        ]\n" +
//                "    }"
    }

}