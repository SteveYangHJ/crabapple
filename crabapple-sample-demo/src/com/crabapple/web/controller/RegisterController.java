package com.crabapple.web.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crabapple.entity.Account;
import com.crabapple.service.RegisterServiceImpl;

/**
 * Servlet implementation class RegisterController
 */
@WebServlet("/register")
public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从request中获取页面用户的填充数据
		String username = request.getParameter("username");  
        String password = request.getParameter("password");  
        Account account = new Account(username, password);
        
        // TODO: 注册,插入数据库
        RegisterServiceImpl registerServiceImpl = new RegisterServiceImpl();
        registerServiceImpl.register(account);

        // 准备把值传到WEB-INF/pages/ok.jsp中
        request.setAttribute("account", account); 
        
        try {  
        	// 指明页面跳转的下一页
            request.getRequestDispatcher("pages/success.jsp").forward(request,response);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
	}

}
