package model.mapper;

import model.json.CountryJson;
import model.xml.CountryXml;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Интерфейс для маппинга CountryXml и CountryJson.
 */
@Mapper(uses = ArtistMapper.class)
public interface CountryMapper {
    @Mapping(target = "countryName", source = "country.countryName")
    @Mapping(target = "artistList", source = "country.artistList")
    CountryXml countryJsonToXml(CountryJson country);

    @Mapping(target = "countryName", source = "country.countryName")
    @Mapping(target = "artistList", source = "country.artistList")
    CountryJson countryXmlToJson(CountryXml country);
}
