package com.example.demo;

import lombok.SneakyThrows;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;

@WebServlet("/deleteServlet")
public class DeleteServlet extends HttpServlet {
    @SneakyThrows
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String sid = request.getParameter("order");
        int iniqid = Integer.parseInt(sid);
        int status = ClientRepository.delete(iniqid);

        if (status > 0) {
            out.println("Order is delete!");
        } else {
            out.println("Something wrong");
        }

    }
}
