package com.movietracker;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class EpisodeJDBCTemplate implements EpisodeDAO{
  private DataSource dataSource;
  private JdbcTemplate jdbcTemplateObject;
  
  public void setDataSource(DataSource dataSource) {
    this.dataSource = dataSource;
    this.jdbcTemplateObject = new JdbcTemplate(dataSource);
  }
  
  public List<Episode> listEpisodes() {
    String SQL = "select e.viewdate, s.title, e.episodeseason, e.episodenumber, e.episodetitle, e.releasedate, e.runtime from episodewatched e inner join tvshow s on e.showimdbid=s.showimdbid order by viewdate desc, episodewatchedid desc;";
    List <Episode> episodes = jdbcTemplateObject.query(SQL, new EpisodeMapper());
    return episodes;
  }
}
