package org.example.bggapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.List;

@Service
public class BGGApiService {

    private final RestTemplate restTemplate;

    public BGGApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String searchBoardGameandExtractId(String query) {
        String apiUrl = "https://boardgamegeek.com/xmlapi2/search?type=boardgame&query=" + query + "&exact=1";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(apiUrl, String.class);
        System.out.println(responseEntity.getBody());

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(BGGSearchResults.class, BGGItem.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(responseEntity.getBody());
            BGGSearchResults response = (BGGSearchResults) unmarshaller.unmarshal(reader);
            List<BGGItem> bggItems = response.getItems();
            return bggItems.get(0).getId();
        } catch (JAXBException e) {
            e.printStackTrace();
            // Handle JAXB exception
            return null;
        }
    }

    public String searchGameById(String gameId) {
        String url = "https://boardgamegeek.com/xmlapi2/thing?id=" + gameId;
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        System.out.println(responseEntity.getBody());

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(BGGItems.class, BGGItem.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(responseEntity.getBody());
            BGGItems response = (BGGItems) unmarshaller.unmarshal(reader);
            List<BGGItem> bggItems = response.getItems();
            String thumbnail = bggItems.get(0).getThumbnail();
            String image = bggItems.get(0).getImage();
            return bggItems.get(0).toString() + "\nthumbnail='" + thumbnail + "'\nimage='" + image + "'";
        } catch (Exception e) {
            // Handle exceptions, e.g., log or throw a custom exception
            e.printStackTrace();
            return null;
        }
    }
}
