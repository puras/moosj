package net.mooko.moosj.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author puras <he@puras.me>
 * @since 15/8/24  下午11:14
 */
@Controller
public class DashboardController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index() {
        return "redirect:/dashboard";
    }

    @RequestMapping(value = "dashboard", method = RequestMethod.GET)
    public String dashboard() {
        return "dashboard";
    }
}
