package com.innosensor.servlet.user;

import com.innosensor.entity.User;
import com.innosensor.service.UserService;
import com.innosensor.service.impl.UserServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/modifyuser")
public class UpdateUserById extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.setCharacterEncoding("utf-8");
            Map<String, String[]> parameterMap = req.getParameterMap();
            User user = new User();
            BeanUtils.populate(user,parameterMap);
            UserService service = new UserServiceImpl();
            service.modifyUser(user);
            req.getRequestDispatcher("/list").forward(req,resp);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }
}
