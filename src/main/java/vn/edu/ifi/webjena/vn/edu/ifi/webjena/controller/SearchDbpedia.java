package vn.edu.ifi.webjena.vn.edu.ifi.webjena.controller;


import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import vn.edu.ifi.webjena.vn.edu.ifi.jena.RdfQuery;
import vn.edu.ifi.webjena.vn.edu.ifi.webjena.data.Town;

@RestController
public class SearchDbpedia {

    @RequestMapping(value = "/webjena")
    public  String  search(){
        System.out.print("ici");
        return "index";
    }

    @RequestMapping(value = "/getcountry", method = RequestMethod.POST)
    public ModelAndView getCountry(@ModelAttribute(value = "town") Town town){
        RdfQuery rdf = new RdfQuery();
        String country;
        country = rdf.Query(town.getTname());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("country",country);
        System.out.print(country);

        return  modelAndView;
    }
}
