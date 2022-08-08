package com.example.demo;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@WebServlet(name = "getServlet", value = "/getServlet")
public class GetServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        try{
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        String sibiqid = request.getParameter("order");
        int iniqid = Integer.parseInt(sibiqid);

        Client client = ClientRepository.getOrder(iniqid);

            String json = ow.writeValueAsString(client);
            out.println(json);
    }catch (Exception e){
        e.printStackTrace();
        }finally {
            out.close();
        }
    }


}
