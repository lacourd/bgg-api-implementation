package org.example.bggapi;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;
import java.util.List;

@XmlRootElement(name = "item")
public class BGGItem {

    private List<BGGName> names;
    private String id;
    private String thumbnail;
    private String image;

    @XmlAttribute(name="id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlElement(name = "name")
    public List<BGGName> getItems() {
        return names;
    }

    public void setItems(List<BGGName> names) {
        this.names = names;
    }

    @XmlElement(name = "thumbnail")
    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @XmlElement(name = "image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "BGGItem{" +
                "id='" + id + '\'' +
                "name='" + names.get(0) + '\'' +
                '}';
    }
}