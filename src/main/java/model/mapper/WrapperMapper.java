package model.mapper;

import model.json.WrapperJson;
import model.xml.WrapperXml;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Интерфейс для маппинга WrapperXml и WrapperJson.
 */
@Mapper(uses = ArtistsMapper.class)
public interface WrapperMapper {
    @Mapping(target = "artists", source = "wrapper.artists")
    WrapperXml wrapperJsonToXml(WrapperJson wrapper);

    @Mapping(target = "artists", source = "wrapper.artists")
    WrapperJson wrapperXmlToJson(WrapperXml wrapper);
}
