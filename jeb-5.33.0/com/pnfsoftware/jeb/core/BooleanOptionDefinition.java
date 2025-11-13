package com.pnfsoftware.jeb.core;

public class BooleanOptionDefinition extends OptionDefinition implements ITypedOptionDefinition {
   public BooleanOptionDefinition(String var1, boolean var2, String var3) {
      super(var1, Boolean.toString(var2), var3);
   }

   @Override
   public Object getOptions() {
      return null;
   }

   @Override
   public ITypedOptionDefinition.Type getType() {
      return ITypedOptionDefinition.Type.Checkbox;
   }
}
