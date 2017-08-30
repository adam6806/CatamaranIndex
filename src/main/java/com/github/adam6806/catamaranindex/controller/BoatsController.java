package com.github.adam6806.catamaranindex.controller;

import com.github.adam6806.catamaranindex.database.model.BoatEntity;
import com.github.adam6806.catamaranindex.database.service.BoatService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@Scope(value = "session")
public class BoatsController {

    @Autowired
    private BoatService boatService;

    private static final String URL = "http://www.yachtworld.com/core/listing/cache/searchResults.jsp?toPrice=175000&fromPrice=25000&enid=101&Ntk=boatsEN&type=%28Sail%29+Catamaran&searchtype=advancedsearch&hmid=0&sm=3&enid=0&cit=true&luom=126&currencyid=100&boatsAddedSelected=-1&ftid=0&slim=quick&No=0&rid=100&rid=104&rid=105&rid=107&rid=112&rid=115&rid=125&fracts=1&ps=2000&Ns=PBoat_sortByPriceDesc|1";
    private static final String MAIN_URL = "http://www.yachtworld.com";

    @RequestMapping(value = "Boats", method = RequestMethod.POST)
    @Transactional
    public String getBoats(ModelMap modelMap, @RequestParam(value = "username") String username) {
        modelMap.addAttribute("boats", boatService.findAll());
        modelMap.addAttribute("username", username);
        return "boats";
    }

    @RequestMapping(value = "Boats/setrating", method = RequestMethod.POST)
    protected void setRating(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer rating = Integer.parseInt(req.getParameter("rating"));
        String username = req.getParameter("username");
        Integer id = Integer.parseInt(req.getParameter("id"));
        Integer combinedRating = 0;

        BoatEntity boat = boatService.findById(Integer.valueOf(id));
        if ("adam".equals(username)) {
            boat.setAdamRating(rating);
            combinedRating = rating + boat.getDougRating();
        } else {
            boat.setDougRating(rating);
            combinedRating = rating + boat.getAdamRating();
        }

        boatService.update(boat);

        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        JSONObject jsonObject = new JSONObject();
        jsonObject.append("combinedRating", combinedRating);
        jsonObject.append("id", id);
        out.print(jsonObject.toString());
        out.flush();
    }
}
