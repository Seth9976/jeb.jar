package com.pnfsoftware.jeb.core.properties;

public interface IPropertyTypeInteger extends IPropertyType {
   Integer getDefault();

   int getMin();

   int getMax();
}
