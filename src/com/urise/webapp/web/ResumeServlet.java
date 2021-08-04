package com.urise.webapp.web;

import com.urise.webapp.Config;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.Storage;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ResumeServlet extends HttpServlet {

    Storage storage =  Config.get().getStorage();

//    @Override
//    public void init(ServletConfig config) throws ServletException {
//        super.init(config);
//        storage = Config.get().getStorage();
//    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
//        response.setHeader("Content-Type", "text/html; charset=UTF-8");
        response.setContentType("text/html; charset=UTF-8");
//        String name = request.getParameter("name");
//        response.getWriter().write(name == null ? "Hello Resumes!" : "Hello " + name + '!');

        //List<Resume> resumeList = new SqlStorage("jdbc:postgresql://localhost:5432/resumes", "postgres", "postgres").getAllSorted();
        List<Resume> resumeList = storage.getAllSorted();
        request.setAttribute("resumes", resumeList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("resume.jsp");
        requestDispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
