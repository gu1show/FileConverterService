package writer;

import lombok.val;
import model.Wrapper;

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
        val jaxbContext = JAXBContext.newInstance(Wrapper.class);
        val marshaller = jaxbContext.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);

        try (val output = new OutputStreamWriter(
                              new FileOutputStream(path), encoding)) {
            marshaller.marshal(artistsWrapper.getArtists(), output);
        }
    }
}
