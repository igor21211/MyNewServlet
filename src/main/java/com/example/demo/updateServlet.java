package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.SneakyThrows;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "updateServlet", value = "/updateServlet")
public class updateServlet extends HttpServlet {


    @SneakyThrows
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();

        String order = request.getParameter("order");
        String coffe = request.getParameter("coffe");
        int iniqid  = Integer.parseInt(order);
        int price = ClientRepository.getPriceForCoffe(coffe);

            Client client = new Client();
            client.setCoffe(coffe);
            client.setIniqid(iniqid);
            client.setPrice(price);

            int status = ClientRepository.update(client);

            if (status > 0) {
               Client client1 =  ClientRepository.getOrder(iniqid);
                ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
                String json = ow.writeValueAsString(client1);
                writer.println(json);
            } else {
                writer.println("Something Wrong!");
            }

    }
}
