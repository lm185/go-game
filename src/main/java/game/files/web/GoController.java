package game.files.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GoController {

  @GetMapping("/")
  public String overview(Model model) {
    return "overview";
  }
}
