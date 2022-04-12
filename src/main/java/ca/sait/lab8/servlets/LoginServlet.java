package ca.sait.lab8.servlets;
import ca.sait.lab8.utilities.CookieUtil;
import ca.sait.lab8.models.User;
import ca.sait.lab8.services.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
             HttpSession session = request.getSession();
        session.invalidate(); 
        
        
        Cookie[] cookies = request.getCookies();
        String email = CookieUtil.getCookieValue(cookies, "email");
        request.setAttribute("email", email);
        
        getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
      String email = request.getParameter("email");
        String password = request.getParameter("password");
        
        // save email to a cookie
        Cookie cookie = new Cookie("email", email);
        cookie.setMaxAge(60 * 60 * 24 * 365 * 3);
        response.addCookie(cookie);
        
        AccountService as = new AccountService();
        String path = getServletContext().getRealPath("/WEB-INF");
        User user = as.login(email, password, path);
        
        if (user == null) {
            request.setAttribute("email", email);
            getServletContext().getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
            return;
        }
        
        HttpSession session = request.getSession();
        session.setAttribute("email", email);
        
        if (user.getRole().getRoleId() == 1) {
            response.sendRedirect("admin");
        } else {
            response.sendRedirect("notes");
        }
    }
}
