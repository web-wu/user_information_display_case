package com.innosensor.servlet.login;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

@WebServlet("/checkcode")
public class CheckCode extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int width = 80;
        int height = 30;
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();
        g.setColor(Color.GRAY);
        g.fillRect(0,0,width,height);

        String str = "qwertyuiopasdfghjklzxcvbnm1234567890QWERTYUIOPASDFGHJKLZXCVBNM";
        Random random = new Random();
        StringBuilder strb = new StringBuilder();
        for (int i = 1;i <= 4;i++) {
            int index = random.nextInt(str.length());
            char ch = str.charAt(index);
            strb.append(ch);
        }
        String checkCode = strb.toString();
        HttpSession session = req.getSession();
        session.setAttribute("checkCode",checkCode);
        g.setColor(Color.yellow);
        g.setFont(new Font("黑体",Font.BOLD,24));
        g.drawString(checkCode,15,24);
        ImageIO.write(image,"png",resp.getOutputStream());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
