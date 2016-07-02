package com.movietracker;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class EpisodeMapper implements RowMapper<Episode>{
  public Episode mapRow(ResultSet rs, int rowNum) throws SQLException {
    Episode episode = new Episode();
    Show show = new Show();
    
    show.setTitle(rs.getString("title"));
    
    episode.setShow(show);
    episode.setTitle(rs.getString("episodetitle"));
    episode.setRelease(rs.getInt("releasedate"));
    episode.setRuntime(rs.getInt("runtime"));
    episode.setDate(rs.getDate("viewdate"));
    episode.setSeason(rs.getInt("episodeseason"));
    episode.setEpisode(rs.getInt("episodenumber"));
    return episode;
  }
}
