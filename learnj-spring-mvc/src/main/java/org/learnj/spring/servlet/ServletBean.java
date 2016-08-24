package org.learnj.spring.servlet;

import org.learnj.spring.mvc.controller.AnyBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Loster on 2016/8/22.
 */
public class ServletBean extends HttpServlet {

    @Autowired
    private AnyBean anyBean;

    @Override
    public void init() throws ServletException {
        super.init();
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, this.getServletContext());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain; charset=UTF-8");

        PrintWriter writer = response.getWriter();
        writer.write(anyBean.name() + " inject successfully!");
        writer.flush();
        writer.close();
    }
}
