package com.example.demo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.SneakyThrows;
import org.slf4j.LoggerFactory;

@WebServlet(name = "getServlet", value = "/getServlet")
public class GetServlet extends HttpServlet {

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int status = 0;
        var log = LoggerFactory.getLogger(GetServlet.class);
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        String sibiqid = request.getParameter("order");
        log.info("Put parametr method: " + sibiqid);
        int iniqid = Integer.parseInt(sibiqid);



        Client client = ClientRepository.getOrder(iniqid);

        if(ClientRepository.checkOrder(iniqid )== 1){
            out.println("sorry your account is deleted");
        }else {
            String json = ow.writeValueAsString(client);
            out.println(json);
        }


    }

}
