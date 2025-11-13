package com.pnfsoftware.jeb.core;

public interface ITypedOptionDefinition extends IOptionDefinition {
   ITypedOptionDefinition.Type getType();

   Object getOptions();

   public static enum Type {
      Description,
      Text,
      Checkbox,
      List;
   }
}
