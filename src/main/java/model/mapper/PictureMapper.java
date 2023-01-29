package model.mapper;

import model.json.PictureJson;
import model.xml.PictureXml;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Интерфейс для маппинга PictureXml и PictureJson.
 */
@Mapper
public interface PictureMapper {
    @Mapping(target = "name", source = "picture.name")
    @Mapping(target = "publicationYear", source = "picture.publicationYear")
    PictureXml pictureJsonToXml(PictureJson picture);

    @Mapping(target = "name", source = "picture.name")
    @Mapping(target = "publicationYear", source = "picture.publicationYear")
    PictureJson pictureXmlToJson(PictureXml picture);
}
