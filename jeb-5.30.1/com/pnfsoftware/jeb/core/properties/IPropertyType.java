package com.pnfsoftware.jeb.core.properties;

public interface IPropertyType {
   String getName();

   Object getDefault();

   boolean validate(Object var1);

   default Object afterRead(IPropertyDefinition var1, Object var2) {
      return var2;
   }

   default Object beforeWrite(IPropertyDefinition var1, Object var2) {
      return var2;
   }
}
