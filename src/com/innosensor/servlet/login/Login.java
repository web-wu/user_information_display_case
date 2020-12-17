package com.innosensor.servlet.login;

import com.innosensor.entity.User;
import com.innosensor.service.UserService;
import com.innosensor.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/login")
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        Map<String, String[]> parameterMap = req.getParameterMap();
        String verifycode = req.getParameter("verifycode");
        User _user = new User();
        try {
            BeanUtils.populate(_user,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        HttpSession session = req.getSession();
        String checkCode = (String) session.getAttribute("checkCode");
        session.removeAttribute("checkCode");
        if (checkCode != null && checkCode.equalsIgnoreCase(verifycode)) {
            UserService service = new UserServiceImpl();
            User user = service.login(_user);
            if (user != null) {
                session.setAttribute("user",user);
                resp.sendRedirect(req.getContextPath() + "/index.jsp");
            } else {
                req.setAttribute("msg", "用户名或密码错误");
                req.getRequestDispatcher("login.jsp").forward(req,resp);
            }
        } else {
            req.setAttribute("msg","验证码错误！！！");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
