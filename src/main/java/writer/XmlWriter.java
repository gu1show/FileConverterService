package writer;

import lombok.val;
import model.json.WrapperJson;
import model.mapper.Wrapper;
import model.mapper.WrapperMapper;
import model.xml.WrapperXml;
import org.mapstruct.factory.Mappers;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.*;

/**
 * Записыватель информации о художниках в XML-файл с определённой кодировкой.
 */
public class XmlWriter implements BasicWriter {
    /**
     * Записывает информацию о художниках в XML-файл по заданной кодировке.
     * @param path Путь, куда записывается информация о художниках.
     * @param artistsWrapper Обёртка с данными о художниках.
     * @param encoding Кодировка, в которой записывается файл.
     * @throws JAXBException Срабатывает, если невозможно создать экземпляр
     *                       без аргументов у какого-то класса из model.
     * @throws IOException Возникает, если нельзя записать в файл.
     */
    public void write(final String path, final Wrapper artistsWrapper, final String encoding) throws JAXBException,
                                                                                                     IOException {
        val jaxbContext = JAXBContext.newInstance(WrapperXml.class);
        val marshaller = jaxbContext.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);

        WrapperXml wrapperXml = (WrapperXml) getConvertedWrapper(artistsWrapper);
        try (val output = new OutputStreamWriter(
                              new FileOutputStream(path), encoding)) {
            marshaller.marshal(wrapperXml.getArtists(), output);
        }
    }

    /**
     * Конвертирует WrapperJson в WrapperXml.
     * @param artistsWrapper Обёртка с данными о художниках в виде WrapperJson.
     * @return Обёртка с данными о художниках в виде WrapperXml.
     */
    private Wrapper getConvertedWrapper(final Wrapper artistsWrapper) {
        WrapperMapper mapper = Mappers.getMapper(WrapperMapper.class);

        return mapper.wrapperJsonToXml((WrapperJson) artistsWrapper);
    }
}
