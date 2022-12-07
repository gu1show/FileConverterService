package writer;

import storage.Wrapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

/**
 * Записыватель информации о художниках в XML-файл.
 */
public class XmlWriter {
    /**
     * Обёртка для хранения информации о художниках.
     */
    private final Wrapper artistsWrapper;

    /**
     * Создание записывателя информации о художниках.
     * @param artistsWrapper Обёртка с данными о художниках.
     */
    public XmlWriter(Wrapper artistsWrapper) {
        this.artistsWrapper = artistsWrapper;
    }

    /**
     * Записывает информацию о художниках в XML-файл.
     * @param path Путь, куда записывается информация о художниках.
     * @throws JAXBException Происходит из-за метода read у XmlReader.
     * Срабатывает, если невозможно создать экземпляр без аргументов у какого-то класса из storage.
     */
    public void write(final String path) throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Wrapper.class);
        Marshaller marshaller = jaxbContext.createMarshaller();

        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty("com.sun.xml.bind.xmlDeclaration", false);
        marshaller.setProperty("com.sun.xml.bind.xmlHeaders", "<?xml version=\"1.0\" encoding=\"UTF-8\"?>");

        marshaller.marshal(artistsWrapper.getArtists(), new File(path));
    }
}
