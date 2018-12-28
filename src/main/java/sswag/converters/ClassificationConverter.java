/*
package sswag.converters;


import sswag.model.Classification;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ClassificationConverter implements AttributeConverter<Classification, String> {

    @Override
    public String convertToDatabaseColumn(Classification _class) {
        return _class.getClassification();
    }

    @Override
    public Classification convertToEntityAttribute(String dbData) {
        return Classification.getClassFromString(dbData);
    }

}
*/