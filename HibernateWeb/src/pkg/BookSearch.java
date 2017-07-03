package pkg;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;

import pkg1.Book;

/**
 * Servlet implementation class BookSearch
 */
public class BookSearch extends HttpServlet {
	
	SessionFactory sfact;
	
	public void init(ServletConfig config) throws ServletException {

		sfact = new Configuration().configure().buildSessionFactory();
		System.out.println("Factory has been created..");
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		PrintWriter out =  response.getWriter();
		//capture the client data
		int bid = Integer.parseInt(request.getParameter("t1"));
		//String title = request.getParameter("t2");
		
		Session session = sfact.openSession();
		
		Book b2 =  (Book)session.get(Book.class, bid);
		
	/*	Criteria c = session.createCriteria(Book.class);
		Book b1 = new Book();
		b1.setBookId(bid);
		b1.setTitle(title);
		Example bookExample = Example.create(b1);
		
		c.add(bookExample);
		List<Book> books = c.list();*/
//		Book b2 =  (pkg1.Book)session.get(Book.class, bid);
		/*for (Book book : books) {
			System.out.println(book.getPrice());
		}*/
		if(b2 != null)
			out.print("<b> Book Details are:"+b2.getTitle()+"\t"+b2.getPrice());
		session.close();
	}

	public void destroy() {
		sfact.close();
	}
}
