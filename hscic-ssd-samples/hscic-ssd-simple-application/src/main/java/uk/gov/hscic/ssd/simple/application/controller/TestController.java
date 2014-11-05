package uk.gov.hscic.ssd.simple.application.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TestController {

    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping("/test")
    public String test(@RequestParam(value = "name", required = false, defaultValue = "World") String name, Model model) {

        logger.debug("call test endpoint");

        model.addAttribute("name", name);

        return "test";
    }

}
