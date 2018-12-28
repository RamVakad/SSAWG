/*
package sswag.converters;

import sswag.model.PatternStatus;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class PatternStatusConverter implements AttributeConverter<PatternStatus, String> {

    @Override
    public String convertToDatabaseColumn(PatternStatus status) {
        return status.getStatus();
    }

    @Override
    public PatternStatus convertToEntityAttribute(String dbData) {
        return PatternStatus.getStatusFromString(dbData);
    }

}

*/