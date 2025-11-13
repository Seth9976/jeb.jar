package com.pnfsoftware.jeb.core.properties;

public interface IPropertyTypeString extends IPropertyType {
   String getDefault();

   int getMinLength();

   int getMaxLength();
}
