package ru.ezhov.football.bet.server.controllers;

import org.junit.Test;
import spark.Request;
import spark.Response;

import static org.mockito.Mockito.mock;

public class GetControllerGameResultTest {
    @Test
    public void handle() throws Exception {
        GetControllerGameResult getControllerGameResult = new GetControllerGameResult();
        String s = getControllerGameResult.handle(mock(Request.class), mock(Response.class));
        System.out.println(s);

//        "{\n" +
//                "        \"games\": [\n" +
//                "            {\n" +
//                "                \"gameDate\": '22.06.2018',\n" +
//                "                \"game\": \"Грузия - армения\",\n" +
//                "                \"gamer\": \"Пупкин Ктото Ктотович\",\n" +
//                "                \"result\": \"2:1\",\n" +
//                "                \"gamerForecast\": \"2:1\",\n" +
//                "                \"score\": \"1\"\n" +
//                "            }\n" +
//                "        ]\n" +
//                "    }"
    }

}