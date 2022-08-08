package com.example.demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/saveServlet")
public class SaveServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        try {


            String name = request.getParameter("name");
            String coffe = request.getParameter("coffe");
            int iniqid = ClientRepository.generateINQID();
            int price = ClientRepository.getPriceForCoffe(coffe);

            Client client = new Client();


            client.setName(name);
            client.setCoffe(coffe);
            client.setPrice(price);
            client.setIniqid(iniqid);


            //out.println(employee.toString());
            //out.println(EmployeeRepository.getConnection());

            int status = ClientRepository.save(client);
            //out.println(status);
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(client);

            if (status > 0) {
                out.print(json);
            } else {
                out.println("Sorry! unable to save record");
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            out.close();
        }

    }
}
