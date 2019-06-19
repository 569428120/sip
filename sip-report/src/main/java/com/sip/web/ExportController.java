package com.sip.web;

import com.bstek.ureport.export.ExportManager;
import com.bstek.ureport.export.html.HtmlReport;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/export")
public class ExportController {

    @Resource
    private HttpServletRequest request;

    @Resource
    private HttpServletResponse response;

    @Resource(name = ExportManager.BEAN_ID)
    private ExportManager exportManager;

    @GetMapping("/html")
    public ResponseEntity<String> queryUser(String template, Map<String, Object> params) {
        Map<String, Object> parameters = new HashMap<String, Object>();
        HtmlReport htmlReport = exportManager.exportHtml("file:demo.ureport.xml", request.getContextPath(), parameters);
        String sb = "<style type=\"text/css\">" +
                htmlReport.getStyle() +
                "</style>" +
                htmlReport.getContent();
        return ResponseEntity.ok(sb);
    }
}
