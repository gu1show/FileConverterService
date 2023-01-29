package model.mapper;

import model.json.ArtistsJson;
import model.xml.ArtistsXml;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Интерфейс для маппинга ArtistsXml и ArtistsJson.
 */
@Mapper(uses = CountryMapper.class)
public interface ArtistsMapper {
    @Mapping(target = "countryList", source = "artists.countryList")
    ArtistsXml artistsJsonToXml(ArtistsJson artists);

    @Mapping(target = "countryList", source = "artists.countryList")
    ArtistsJson artistsXmlToJson(ArtistsXml artists);
}
