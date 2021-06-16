package cz.czechitas.java2webapps.lekce12.controller;

import cz.czechitas.java2webapps.lekce12.service.SvatkyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Controller pro zobrazení svátků.
 */
@Controller
public class SvatkyController {

  private final SvatkyService svatkyService;

  @Autowired
  public SvatkyController(SvatkyService svatkyService) {
    this.svatkyService = svatkyService;
  }

  @GetMapping(path = "/")
  public ModelAndView today() {
    return new ModelAndView("today")
            .addObject("svatek", svatkyService.spojit(svatkyService.jmeniny(svatkyService.today())));
  }

  @GetMapping(path = "/", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public List<String> todayJson() {
    return svatkyService.jmeniny(svatkyService.today());
  }
}
