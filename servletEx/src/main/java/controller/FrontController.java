package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private Map<String, commandHandler> commandHandlerMap = new HashMap<>();
	
	@Override
	public void init() throws ServletException {
//		commandHandlerMap.put("/add", new AddHandler());
//		commandHandlerMap.put("/min", new MinHandler());
		
		//초기화 파라미터를 이용하여 Properties 불러오기
		String ContextConfigFile = this.getInitParameter("handlerProperties");
		System.out.println("ContextConfigFile string : " + ContextConfigFile);
		Properties properties = new Properties();
		FileInputStream fis = null;
		try {
			String contextConfigFilePath = this.getServletContext().getRealPath(ContextConfigFile);
			System.out.println("contextConfigFilePath : "+ contextConfigFilePath);
			fis = new FileInputStream(contextConfigFilePath);
			properties.load(fis);
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(fis!=null) fis.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		//핸들러 객체 생성 및 Map에 등록
		Iterator<Object> propIt = properties.keySet().iterator();
		while(propIt.hasNext()) {
			String command = (String) propIt.next();
			String handlerClassName = properties.getProperty(command);
			System.out.println(command + ":" + handlerClassName);
			try {
				Class<?> handlerClass = Class.forName(handlerClassName);
				commandHandlerMap.put(command, (commandHandler)handlerClass.newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		System.out.println("Controller가 요청 분석");
		String requestURI = req.getRequestURI().toString();
		System.out.println("요청 URI:" + requestURI);
		
		String command = requestURI.substring(req.getContextPath().length());
		System.out.println("command:" + command);
		
		commandHandler handler = null;
		String viewPage = null;
		
		if(requestURI.indexOf(req.getContextPath()) == 0) {
			handler = commandHandlerMap.get(command);
			viewPage = handler.handlerAction(req, resp);
			System.out.println("Model관련(비즈니스 로직) 동작");
		}
		
		System.out.println("Controller가 결과 데이터를 보여줄 뷰로 포워딩");
		req.getRequestDispatcher(viewPage).forward(req, resp);
	}
}
