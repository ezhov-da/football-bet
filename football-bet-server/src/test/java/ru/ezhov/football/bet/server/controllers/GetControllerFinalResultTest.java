package ru.ezhov.football.bet.server.controllers;

import org.junit.Test;
import spark.Request;
import spark.Response;

import static org.mockito.Mockito.mock;

public class GetControllerFinalResultTest {
    @Test
    public void handle() throws Exception {
        GetControllerFinalResult getControllerFinalResult = new GetControllerFinalResult();
        String s = getControllerFinalResult.handle(mock(Request.class), mock(Response.class));
        System.out.println(s);


//        "{\n" +
//                "        'results': [\n" +
//                "            {\n" +
//                "                'player': 'Пупкин Ктото Ктотович',\n" +
//                "                \"winner\": \"Грузия - армения\",\n" +
//                "                \"score\": \"2:1\",\n" +
//                "                \"plusFive\": \"1\",\n" +
//                "                \"finalScore\": \"12\"\n" +
//                "            }\n" +
//                "        ]\n" +
//                "    }";
    }

}