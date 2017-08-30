package com.github.adam6806.catamaranindex.boat;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@Scope(value = "session")
@RequestMapping(value = "/", method = RequestMethod.GET)
public class BoatsController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String sayHelloAgain(ModelMap model) {
        return "/index";
    }
}
