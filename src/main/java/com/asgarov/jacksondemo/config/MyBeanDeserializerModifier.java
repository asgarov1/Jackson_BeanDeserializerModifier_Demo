package com.asgarov.jacksondemo.config;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.deser.BeanDeserializerBuilder;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.deser.SettableBeanProperty;

import java.util.Iterator;

public class MyBeanDeserializerModifier extends BeanDeserializerModifier {
    @Override
    public BeanDeserializerBuilder updateBuilder(DeserializationConfig config, BeanDescription beanDesc, BeanDeserializerBuilder builder) {
        for (Iterator<SettableBeanProperty> properties = builder.getProperties(); properties.hasNext(); ) {
            SettableBeanProperty property = properties.next();
            if (isStringAndNotExcluded(property, beanDesc)
                    && doesNotHaveExistingDeserializer(property)
            ) {
                property = property.withValueDeserializer(new AntisamyDeserializer());
                builder.addOrReplaceProperty(property, true);
            }
        }
        return builder;
    }

    private boolean doesNotHaveExistingDeserializer(SettableBeanProperty property) {
        return property.getAnnotation(JsonDeserialize.class) == null;
    }

    private boolean isStringAndNotExcluded(SettableBeanProperty property, BeanDescription beanDesc) {
        return property.getType().getRawClass().equals(String.class)
                && !beanDesc.getBeanClass().isAnnotationPresent(ExcludeAntisamy.class)
                && !property.getMember().hasAnnotation(ExcludeAntisamy.class);
    }
}
