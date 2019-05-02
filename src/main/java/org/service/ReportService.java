package org.service;

import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.engine.JRExporterParameter;

import org.dao.GenericDao;
import org.service.ExportReportStrategy;

public class ReportService {
    private final static String REPORT_NAME = "report";
    private static String jrxmlPath = null;
    private static String jasperPath = null;

    public static void compilerJasperReport(ServletContext sc, String selectedReport) {
        try {
            jrxmlPath = sc.getRealPath("/WEB-INF/reports/" + selectedReport + ".jrxml");
            jasperPath = jrxmlPath.replace(".jrxml", ".jasper");
            JasperCompileManager.compileReportToFile(jrxmlPath, jasperPath);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generateReport(String selectedReport, String selectedFormat,
                               ServletContext sc, HttpServletRequest request,
                               HttpServletResponse response) {

        compilerJasperReport(sc, selectedReport);
        Connection conn = null;
        Map reportParams = new HashMap();

        try {
            conn = new GenericDao<>().getJdbcConnection();
            JasperPrint print = JasperFillManager.fillReport(jasperPath, reportParams, conn);

            if (!selectedReport.equals("viewer")) {
                response.setContentType("application/download");
                String header = "attachment;filename=\"" + REPORT_NAME + "." + selectedFormat + "\"";
                response.setHeader("Content-Disposition", header);
                ExportReportStrategy exportReportStrategy = new ExportReportStrategy("pdf");
                ServletOutputStream servletOutputStream = response.getOutputStream();
                exportReportStrategy.exportReport(print, servletOutputStream);
                servletOutputStream.flush();
                servletOutputStream.close();
            } else {
                response.setContentType("text/html");
                HtmlExporter htmlExporter = new HtmlExporter();
                PrintWriter printWriter = response.getWriter();
                /*
                htmlExporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
                htmlExporter.setParameter(JRExporterParameter.OUTPUT_WRITER, printWriter);
                */
                htmlExporter.setExporterOutput(new SimpleHtmlExporterOutput(printWriter));
                htmlExporter.setExporterInput(new SimpleExporterInput(print));
                htmlExporter.exportReport();
                printWriter.flush();
                printWriter.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }




    }
}
