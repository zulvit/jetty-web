package ru.zulvit.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.eclipse.jetty.http.HttpStatus;
import org.jetbrains.annotations.NotNull;
import ru.zulvit.dao.InvoiceDaoImpl;
import ru.zulvit.entity.Invoice;

import java.io.IOException;
import java.util.Map;

public class AddEntityServlet extends HttpServlet {
    private final InvoiceDaoImpl invoiceDao = new InvoiceDaoImpl();

    @Override
    public void init() throws ServletException {
        super.init();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    protected void doPost(@NotNull HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Map<String, String[]> parameterMap = req.getParameterMap();
        resp.getWriter().write("Method do post\n");

        if (req.getParameter("name").equals("") || req.getParameter("firm").equals("") || req.getParameter("amount").equals("")
                || req.getParameter("name") == null || req.getParameter("firm") == null || req.getParameter("amount") == null) {
            resp.setStatus(HttpStatus.BAD_REQUEST_400);
            resp.sendError(resp.getStatus());
        } else {
            String name = req.getParameter("name");
            String firm = req.getParameter("firm");
            String amount = req.getParameter("amount");
            Invoice invoice = new Invoice(name, firm, amount);
            invoiceDao.save(invoice);
            resp.getWriter().println(invoice);
            resp.setStatus(HttpServletResponse.SC_OK);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
