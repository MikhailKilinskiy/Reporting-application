package org.service;

import javax.servlet.ServletOutputStream;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

public class ExportReportStrategy {
    private String reportFormat;

    public ExportReportStrategy(String reportFormat) {
        this.reportFormat = reportFormat;
    }

    public String getReportFormat() {
        return reportFormat;
    }

    public void setReportFormat(String reportFormat) {
        this.reportFormat = reportFormat;
    }

    private void exportPdf(JasperPrint print, ServletOutputStream servletOutputStream) {
        try {
            JasperExportManager.exportReportToPdfStream(print, servletOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void exportCsv(JasperPrint print, ServletOutputStream servletOutputStream) {
        try {
            JRCsvExporter csvExporter = new JRCsvExporter();
            csvExporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
            csvExporter.setParameter(JRExporterParameter.OUTPUT_STREAM, servletOutputStream);
            csvExporter.exportReport();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void exportReport(JasperPrint print, ServletOutputStream servletOutputStream) {
        try {
            switch (reportFormat) {
                case "pdf":
                    exportPdf(print, servletOutputStream);
                    break;

                case "csv":
                    exportCsv(print, servletOutputStream);
                    break;

                default:
                    throw new Exception("There is unhandled report format!");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

