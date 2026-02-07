package im.wity.vo;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class PageNameConverter implements AttributeConverter<PageName,String> {
    @Override
    public String convertToDatabaseColumn(PageName attribute) {
        return attribute.getPageName();
    }

    @Override
    public PageName convertToEntityAttribute(String dbData) {
        return PageName.of(dbData);
    }
}
