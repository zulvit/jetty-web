package ru.zulvit.servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import ru.zulvit.dao.InvoiceDaoImpl;
import ru.zulvit.entity.Invoice;

import java.io.IOException;
import java.util.List;

public class GetEntityServlet extends HttpServlet {
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
    protected void doGet(@NotNull HttpServletRequest req, @NotNull HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/plain");
        resp.getWriter().write("Method do get\n");
        List<@NotNull Invoice> all = invoiceDao.getAll();
        resp.getWriter().write(all.toString());
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    public void destroy() {
        super.destroy();
    }
}
