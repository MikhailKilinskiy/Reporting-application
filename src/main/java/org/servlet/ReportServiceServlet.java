package org.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.InputStream;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.service.ReportService;

public class ReportServiceServlet extends HttpServlet {
    static final Logger reportLogger = LogManager.getLogger(ReportServiceServlet.class);

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String selectedReport = request.getParameter("selectReport");
        String selectFormat = request.getParameter("format");

        try {
            ServletContext sc = this.getServletContext();
            ReportService reportService = new ReportService();
            reportService.generateReport(selectedReport, selectFormat, sc, request, response);

        } catch (Exception e) {
            response.setContentType("text/plain");
            response.getOutputStream().print(printWebError(e));
        }

    }

    private String printWebError(Exception e) {
        StringWriter stringWriter = new StringWriter();
        PrintWriter printWriter = new PrintWriter(stringWriter);
        e.printStackTrace(printWriter);
        return stringWriter.toString();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        this.doPost(request, response);
    }

}


