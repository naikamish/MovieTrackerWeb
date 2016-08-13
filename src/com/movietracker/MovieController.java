package com.movietracker;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.googlecode.charts4j.Color;
import com.googlecode.charts4j.GCharts;
import com.googlecode.charts4j.PieChart;
import com.googlecode.charts4j.Slice;

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
  	ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

	  EpisodeJDBCTemplate episodeJDBCTemplate = (EpisodeJDBCTemplate)context.getBean("episodeJDBCTemplate");
  	String imdbID = episodeJDBCTemplate.getImdbID(ep.getShow().getTmdbID());
	  ep.getShow().setImdbID(imdbID);
	  
  	if(ep.getImdbID().equals("")){
  		getImdbID(ep);
  		System.out.println(ep.getImdbID());
  	}
  	getRuntime(ep);
   
	  episodeJDBCTemplate.create(ep.getDate(), ep.getShow().getImdbID(), ep.getShow().getTmdbID(), ep.getImdbID(), ep.getTmdbID(), ep.getTitle(), ep.getRelease(), ep.getRuntime(), ep.getSeason(), ep.getEpisode());

	  return "redirect:/episodelist";
  }
  
  public void getImdbID(Episode episode){
  	
  	try{
  		String imdbID = episode.getShow().getImdbID();
  		int season = episode.getSeason();
  		String url = "http://www.imdb.com/title/tt"+imdbID+"/episodes?season="+season;
      URL siteURL = new URL(url);
      System.out.println(url);
      URLConnection yc = siteURL.openConnection();
      yc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:x.x.x) Gecko/20041107 Firefox/x.x");
      InputStreamReader isr = new InputStreamReader(yc.getInputStream());
      BufferedReader reader = new BufferedReader(isr);
      String fullString = "";
      for (String line; (line = reader.readLine()) != null;) {
          fullString+=line;
      }
      String subStr = "itemprop=\"episodeNumber\" content=\""+episode.getEpisode()+"\"";
      fullString = fullString.substring(fullString.indexOf(subStr));
      String episodeImdbID = fullString.substring(fullString.indexOf("/title/tt")+9,fullString.indexOf("/?"));
      episode.setImdbID(episodeImdbID);
  	}
  	catch(Exception e){
  		e.printStackTrace();
  	}
  }
  
  public void getRuntime(Episode episode){
  	
  	try{
  		URL siteURL = new URL("http://www.imdb.com/title/tt"+episode.getImdbID());
	    URLConnection yc = siteURL.openConnection();
	    yc.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US; rv:x.x.x) Gecko/20041107 Firefox/x.x");
	    InputStreamReader isr = new InputStreamReader(yc.getInputStream());
	    BufferedReader reader = new BufferedReader(isr);
	    String fullString = "";
	    for (String line; (line = reader.readLine()) != null;) {
	        fullString+=line;
	    }
	    fullString = fullString.substring(fullString.indexOf("<time itemprop=\"duration\" datetime=\""));
	    String rnTm = fullString.substring(fullString.indexOf("itemprop=\"duration\" datetime=\"")+32, fullString.indexOf("M"));
	    episode.setRuntime(Integer.parseInt(rnTm));
  	}
  	catch(Exception e){
  		e.printStackTrace();
  	}
  }
  @RequestMapping(value = "/statistics", method = RequestMethod.GET)
  public ModelAndView statistics() {
		Slice s1 = Slice.newSlice(15, Color.newColor("CACACA"), "Mac", "Mac");
		Slice s2 = Slice.newSlice(50, Color.newColor("DF7417"), "Windows", "Windows");
		Slice s3 = Slice.newSlice(25, Color.newColor("951800"), "Linux", "Linux");
		Slice s4 = Slice.newSlice(10, Color.newColor("01A1DB"), "Others", "Others");
		PieChart pieChart = GCharts.newPieChart(s1, s2, s3, s4);
		pieChart.setTitle("Google Pie Chart", Color.BLACK, 15);
		pieChart.setSize(720, 360);
		pieChart.setThreeD(true);
		ModelAndView model = new ModelAndView("statistics");
		model.addObject("pieUrl", pieChart.toURLString());
		return model;
  }
}
