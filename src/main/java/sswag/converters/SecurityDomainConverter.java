/*
package sswag.converters;

import sswag.model.SecurityDomain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class SecurityDomainConverter implements AttributeConverter<SecurityDomain, String> {

    @Override
    public String convertToDatabaseColumn(SecurityDomain domain) {
        return domain.getSecurityDomain();
    }

    @Override
    public SecurityDomain convertToEntityAttribute(String dbData) {
        return SecurityDomain.getSecDomainFromString(dbData);
    }

}
*/