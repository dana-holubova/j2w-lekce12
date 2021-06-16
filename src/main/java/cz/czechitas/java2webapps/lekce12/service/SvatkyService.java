package cz.czechitas.java2webapps.lekce12.service;

import cz.czechitas.java2webapps.lekce12.repository.SvatkyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.MonthDay;
import java.util.Iterator;
import java.util.List;

/**
 * Služba pro získávání údajů o svátcích.
 */
@Service
public class SvatkyService {
  private final SvatkyRepository svatkyRepository;

  @Autowired
  public SvatkyService(SvatkyRepository svatkyRepository) {
    this.svatkyRepository = svatkyRepository;
  }

  public List<String> jmeniny(MonthDay monthDay) {
    return svatkyRepository.svatky(monthDay.getMonthValue(), monthDay.getDayOfMonth());
  }

  public List<String> jmeniny(LocalDate date) {
    return jmeniny(MonthDay.from(date));
  }

  public LocalDate today() {
    return LocalDate.now();
  }

  public LocalDate tomorrow() {
    return LocalDate.now().plusDays(1L);
  }

  public String spojit(List<String> seznam) {
    if (seznam == null || seznam.isEmpty()) {
      return null;
    }

    StringBuilder builder = new StringBuilder();
    Iterator<String> iterator = seznam.iterator();
    boolean first = true;
    while (iterator.hasNext()) {
      String next = iterator.next();
      if (!first) {
        if (iterator.hasNext()) {
          builder.append(", ");
        } else {
          builder.append(" a ");
        }
      } else {
        first = false;
      }
      builder.append(next);
    }
    return builder.toString();
  }
}
