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

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
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
  
  @RequestMapping(value = "/episodelist", method = RequestMethod.GET)
  public ModelAndView episodes() {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		EpisodeJDBCTemplate episodeJDBCTemplate = (EpisodeJDBCTemplate)context.getBean("episodeJDBCTemplate");
		List<Episode> list = episodeJDBCTemplate.listEpisodes();
		ModelAndView model = new ModelAndView("episodelist");
		model.addObject("command", new Episode());
		model.addObject("lists",list);
		return model;
  }
  
  @RequestMapping(value = "/searchEpisode", method = RequestMethod.GET)
	public ModelAndView episodeSearch(HttpServletRequest request) {
		ModelAndView model = new ModelAndView("searchEpisode");
		model.addObject("command", new Show());
		model.addObject("episodeTitle", request.getParameter("searchTitle"));
		return model;
  }
  
  @RequestMapping(value = "/addShow", method = RequestMethod.POST)
  public String addShow(@ModelAttribute("WebsiteProject")Show show, 
  	   ModelMap model) {
  	ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

	  ShowJDBCTemplate showJDBCTemplate = (ShowJDBCTemplate)context.getBean("showJDBCTemplate");
	      
	  showJDBCTemplate.create(show.getImdbID(), show.getTmdbID(), show.getTitle(), show.getRuntime());

	return "redirect:/episodelist";
  }
  
  @RequestMapping(value = "/showlist", method = RequestMethod.GET)
  public @ResponseBody List<Show> showList() {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		ShowJDBCTemplate showJDBCTemplate = (ShowJDBCTemplate)context.getBean("showJDBCTemplate");
		List<Show> list = showJDBCTemplate.listShows();
		return list;
  }
  
  @RequestMapping(value = "/addEpisode", method = RequestMethod.POST)
  public String addEpisode(@ModelAttribute("WebsiteProject")Episode episode, 
  	   ModelMap model) {
  	Episode ep = episode;
  	int runtime = getRuntime(ep.getImdbID());
  	ep.setRuntime(runtime);
  	ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

	  EpisodeJDBCTemplate episodeJDBCTemplate = (EpisodeJDBCTemplate)context.getBean("episodeJDBCTemplate");
	      
	  episodeJDBCTemplate.create(ep.getDate(), ep.getShow().getTmdbID(), ep.getImdbID(), ep.getTmdbID(), ep.getTitle(), ep.getRelease(), ep.getRuntime(), ep.getSeason(), ep.getEpisode());
	  //System.out.println(ep.getDate()+ ep.getShow().getTmdbID()+ ep.getImdbID()+ ep.getTmdbID()+ ep.getTitle()+ ep.getRelease()+ ep.getRuntime()+ ep.getSeason()+ ep.getEpisode());
  	
	return "redirect:/episodelist";
  }
  
  public int getRuntime(String imdbID){
  	
  	try{
	  	URL siteURL = new URL("http://www.imdb.com/title/tt"+imdbID);
	    URLConnection yc = siteURL.openConnection();
	//set the User-Agent value (currently using the Firefox user-agent value)
	    yc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:x.x.x) Gecko/20041107 Firefox/x.x");
	    InputStreamReader isr = new InputStreamReader(yc.getInputStream());
	    BufferedReader reader = new BufferedReader(isr);
	    String fullString = "";
	    for (String line; (line = reader.readLine()) != null;) {
	        fullString+=line;
	    }
	    fullString = fullString.substring(fullString.indexOf("<time itemprop=\"duration\" datetime=\""));
	    String rnTm = fullString.substring(fullString.indexOf("itemprop=\"duration\" datetime=\"")+32, fullString.indexOf("M"));
	    return Integer.parseInt(rnTm);
  	}
  	catch(Exception e){
  		e.printStackTrace();
  	}
  	return 0;
  }
}
