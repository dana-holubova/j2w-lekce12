package cz.czechitas.java2webapps.lekce12.repository;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository pro získávání dat o svátcích.
 */
@Repository
public class SvatkyRepository {

  private final JdbcTemplate jdbcTemplate;

  public SvatkyRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<String> svatky(int month, int day) {
    return jdbcTemplate.queryForList("SELECT jmeno FROM jmeniny WHERE mesic = ? AND den = ? ORDER BY jmeno", String.class, month, day);
  }
}
