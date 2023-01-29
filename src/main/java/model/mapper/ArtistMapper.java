package model.mapper;

import model.json.ArtistJson;
import model.xml.ArtistXml;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Интерфейс для маппинга ArtistXml и ArtistJson.
 */
@Mapper(uses = PictureMapper.class)
public interface ArtistMapper {
    @Mapping(target = "name", source = "artist.name")
    @Mapping(target = "pictures", source = "artist.pictures")
    ArtistXml artistJsonToXml(ArtistJson artist);

    @Mapping(target = "name", source = "artist.name")
    @Mapping(target = "pictures", source = "artist.pictures")
    ArtistJson artistXmlToJson(ArtistXml artist);
}
