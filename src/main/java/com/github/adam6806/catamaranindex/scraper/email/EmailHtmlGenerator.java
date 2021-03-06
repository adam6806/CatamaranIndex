package com.github.adam6806.catamaranindex.scraper.email;

import com.github.adam6806.catamaranindex.database.model.BoatEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EmailHtmlGenerator {

    public static String generateHTML(List<BoatEntity> boats) {
        StringBuilder stringBuilder = new StringBuilder();
        for (BoatEntity boat : boats) {
            stringBuilder.append("<div><p>");
            stringBuilder.append("$").append(boat.getPrice()).append(" - ");
            stringBuilder.append(boat.getLength()).append("ft - ");
            stringBuilder.append(boat.getYear()).append(" - ");
            stringBuilder.append(boat.getMakeModel()).append(" - ");
            stringBuilder.append(boat.getLocation());
            stringBuilder.append("</p>");
            stringBuilder.append("<a href=\"").append(boat.getUrl()).append("\">");
            stringBuilder.append("<img width=\"400\" src=\"").append(boat.getImages().iterator().next().getUrl()).append("\"/>");
            stringBuilder.append("</a></div>");
        }
        return stringBuilder.toString();
    }
}
