package com.movietracker;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;

@Controller
public class MovieController {
  @RequestMapping(value = "/movielist", method = RequestMethod.GET)
  public ModelAndView movies() {
	ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
	MovieJDBCTemplate movieJDBCTemplate = (MovieJDBCTemplate)context.getBean("movieJDBCTemplate");
	List<Movie> list = movieJDBCTemplate.listMovies();
	ModelAndView model = new ModelAndView("movielist");
	model.addObject("lists",list);
	model.addObject("command", new Movie());
	return model;
  }
  
  @RequestMapping(value = "/search", method = RequestMethod.GET)
  public ModelAndView movieSearch(HttpServletRequest request) {
	ModelAndView model = new ModelAndView("searchMovie");
	model.addObject("command", new Movie());
	model.addObject("movieTitle", request.getParameter("searchTitle"));
	return model;
  }
  
  @RequestMapping(value = "/addMovie", method = RequestMethod.POST)
  public String addMovie(@ModelAttribute("WebsiteProject")Movie movie, 
  	   ModelMap model) {
  	ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

	  MovieJDBCTemplate movieJDBCTemplate = (MovieJDBCTemplate)context.getBean("movieJDBCTemplate");
	      
	  movieJDBCTemplate.create(movie.getImdbID(), movie.getTmdbID(), movie.getDate(), movie.getTitle(), movie.getDirector(), movie.getRuntime(), movie.getRelease(), movie.getGenre());

	return "redirect:/movielist";
  }
}