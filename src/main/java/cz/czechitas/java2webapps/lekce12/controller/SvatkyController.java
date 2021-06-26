package cz.czechitas.java2webapps.lekce12.controller;

import cz.czechitas.java2webapps.lekce12.service.SvatkyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

/**
 * Controller pro zobrazení svátků.
 */
@Controller
public class SvatkyController {
  private final SimpleDateFormat dateFormat = new SimpleDateFormat("d. MMM yyyy", new Locale("cs-CZ"));

  private final SvatkyService svatkyService;

  @Autowired
  public SvatkyController(SvatkyService svatkyService) {
    this.svatkyService = svatkyService;
  }

  @GetMapping(path = "/")
  public ModelAndView today() {
    return html(svatkyService.today(), "Dnes");
  }

  /**
   * Pomocí parametru produces říkám, co chci od serveru. V tomto případě jsou to data ve formátu JSON.
   */
  @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public List<String> todayJson() {
    return json(svatkyService.today());
  }

  @GetMapping(path = "/tomorrow")
  public ModelAndView tomorrow() {
    return html(svatkyService.tomorrow(), "Zítra");
  }

  @GetMapping(path = "/tomorrow", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public List<String> tomorrowJson() {
    return json(svatkyService.tomorrow());
  }

  @GetMapping(path = "/{date}")
  public ModelAndView date(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
    return html(date, dateFormat.format(date));
  }

  @GetMapping(path = "/{date}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public List<String> dateJson(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
    return json(date);
  }

  private ModelAndView html(LocalDate date, String textDay) {
    String svatek = svatkyService.spojit(svatkyService.jmeniny(date));
    return new ModelAndView("index")
            .addObject("den", textDay)
            .addObject("svatek", svatek);

  }

  private List<String> json(LocalDate date) {
    return svatkyService.jmeniny(date);

  }
}
