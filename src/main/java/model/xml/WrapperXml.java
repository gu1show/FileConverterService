package model.xml;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import model.mapper.Wrapper;

/**
 * Обёртка с данными о художниках.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WrapperXml implements Wrapper {
    /**
     * Содержимое тега "художники".
     */
    private ArtistsXml artists;
}
