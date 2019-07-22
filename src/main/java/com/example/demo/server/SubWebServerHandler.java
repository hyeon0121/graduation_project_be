package com.example.demo.server;

import com.example.demo.global.HttpGlobal;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class SubWebServerHandler implements HttpHandler {
    public SubWebServerHandler() {

    }

    @Override
    public void handle(HttpExchange ex) throws IOException {

    }

    public static class scenarioHandler implements HttpHandler {
        String getAnswer;

        public scenarioHandler(String getAnswer) {
            this.getAnswer = getAnswer;
        }
        @Override
        public void handle(HttpExchange ex) throws IOException {

//            Headers cliHeader = ex.getRequestHeaders();
//            cliHeader.get("User-Agent");

//            if (HttpGlobal.ansArr.containsKey(sno)) {
//            } else {
//                System.err.println("File not found");
//
//                ex.sendResponseHeaders(404, 0);
//                out.write("404 File not found.".getBytes());
//            }

            if (ex.getRequestMethod().equals("GET")) {
                String str = ex.getProtocol();
                String http = str.split("/")[0];
                String version = str.split("/")[1];

                if (http.equals("HTTP")) {
                    HttpGlobal.httpCheck = true;
                } else {
                    HttpGlobal.httpCheck = false;
                }

                if (version.equals("1.1")) {
                    HttpGlobal.httpVersion = true;
                } else {
                    HttpGlobal.httpVersion = false;
                }

                System.err.println(HttpGlobal.httpCheck);
                System.err.println(HttpGlobal.httpVersion);

                System.err.println("http : "+http);
                System.err.println("ver : "+version);

                System.err.println("Method : "+ex.getRequestMethod());

                Headers cliHeader2 = ex.getRequestHeaders();
                System.err.println("HEADER2 : "+cliHeader2.get("User-Agent"));

                if(cliHeader2.get("User-agent").toString().contains("Mozilla")) {
                    HttpGlobal.headerUserAgent = false;
                    System.out.println("false");
                } else {
                    HttpGlobal.headerUserAgent = true;
                    System.out.println("true");
                }

                OutputStream out = ex.getResponseBody();
                String result = getAnswer;
                ex.sendResponseHeaders(200, result.getBytes().length);
                out.write(result.getBytes());
                out.close();
            }

            else if (ex.getRequestMethod().equals("POST")) {
                System.err.println("Method : "+ex.getRequestMethod());
                InputStreamReader isr = new InputStreamReader(ex.getRequestBody(), "utf-8");
                BufferedReader br = new BufferedReader(isr);
                String json = br.readLine();


                try {
                    JSONObject obj = new JSONObject(json);
                    String sno = (String)obj.get("studentID");

                    Long baseNum =  Long.parseLong(sno);
                    Long tes = (long) (Math.random() * (1000));
                    Long testNum = baseNum - tes;

                    System.out.println(sno);
                    System.out.println(testNum);

                    HttpGlobal.postAnswer = testNum.toString();

                    OutputStream out = ex.getResponseBody();

                    ex.sendResponseHeaders(200, testNum.toString().length());

                    out.write(testNum.toString().getBytes());
                    out.close();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static class getHandler implements HttpHandler {
        String getAnswer;

        public getHandler(String getAnswer) {
            this.getAnswer = getAnswer;
        }
        @Override
        public void handle(HttpExchange ex) throws IOException {

//            Headers cliHeader = ex.getRequestHeaders();
//            cliHeader.get("User-Agent");

//            if (HttpGlobal.ansArr.containsKey(sno)) {
//            } else {
//                System.err.println("File not found");
//
//                ex.sendResponseHeaders(404, 0);
//                out.write("404 File not found.".getBytes());
//            }

            if (ex.getRequestMethod().equals("GET")) {
                String str = ex.getProtocol();
                String http = str.split("/")[0];
                String version = str.split("/")[1];

                if (http.equals("HTTP")) {
                    HttpGlobal.httpCheck = true;
                } else {
                    HttpGlobal.httpCheck = false;
                }

                if (version.equals("1.1")) {
                    HttpGlobal.httpVersion = true;
                } else {
                    HttpGlobal.httpVersion = false;
                }

                System.err.println(HttpGlobal.httpCheck);
                System.err.println(HttpGlobal.httpVersion);

                System.err.println("http : "+http);
                System.err.println("ver : "+version);

                System.err.println("Method : "+ex.getRequestMethod());

                Headers cliHeader2 = ex.getRequestHeaders();
                System.err.println("Header : "+cliHeader2.get("User-Agent"));

                if(cliHeader2.get("User-agent").toString().contains("Mozilla")) {
                    HttpGlobal.headerUserAgent = false;
                    System.out.println("false");
                } else {
                    HttpGlobal.headerUserAgent = true;
                    System.out.println("true");
                }

                OutputStream out = ex.getResponseBody();
                String result = getAnswer;
                ex.sendResponseHeaders(200, result.getBytes().length);
                out.write(result.getBytes());
                out.close();
            }

        }
    }

    public static class postHandler implements HttpHandler {

        public postHandler() {

        }

        @Override
        public void handle(HttpExchange ex) throws IOException {
            if (ex.getRequestMethod().equals("POST")) {
                InputStreamReader isr = new InputStreamReader(ex.getRequestBody(), "utf-8");
                BufferedReader br = new BufferedReader(isr);
                String json = br.readLine();

                try {
                    JSONObject obj = new JSONObject(json);
                    String sno = (String)obj.get("studentID");


                    Long baseNum =  Long.parseLong(sno);
                    Long tes = (long) (Math.random() * (1000));
                    Long testNum = baseNum - tes;

                    System.out.println(sno);
                    System.out.println(testNum);

                    HttpGlobal.postAnswer = testNum.toString();

                    OutputStream out = ex.getResponseBody();

                    ex.sendResponseHeaders(200, testNum.toString().length());

                    out.write(testNum.toString().getBytes());
                    out.close();

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        }
    }
}

