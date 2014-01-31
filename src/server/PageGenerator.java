package server;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.eclipse.jetty.client.*;

public class PageGenerator extends AbstractHandler {

	public void handle(String target, Request baseRequest,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		response.setContentType("text/html;charset=utf-8");
		response.setStatus(HttpServletResponse.SC_OK);
		baseRequest.setHandled(true);
		// response.getWriter().println("<h1>Hello World</h1>");

		BufferedReader in = new BufferedReader(new FileReader("hello.html"));
		String line;
		String fileText = "";
		while ((line = in.readLine()) != null) {
			fileText += line + "\n";
		}

		response.getWriter().println(fileText);
		System.out.println("Status is: " + response.getOutputStream());
	}

	public void startServer() {
		
		Server server = new Server(8082);
		server.setHandler(new PageGenerator());
        
		
		try {
			if (server.isStopped()) {
				server.start();
			}
			server.join();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
