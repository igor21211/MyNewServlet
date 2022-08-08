package com.example.demo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteServlet")
public class DeleteServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String sid = request.getParameter("order");
            int iniqid = Integer.parseInt(sid);
            ClientRepository.delete(iniqid);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
