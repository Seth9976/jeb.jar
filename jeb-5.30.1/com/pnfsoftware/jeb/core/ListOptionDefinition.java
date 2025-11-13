package com.pnfsoftware.jeb.core;

public class ListOptionDefinition extends OptionDefinition implements ITypedOptionDefinition {
   final String[] options;

   public ListOptionDefinition(String var1, String var2, String var3, String... var4) {
      super(var1, var2, var3);
      this.options = var4;
   }

   public String[] getOptions() {
      return this.options;
   }

   @Override
   public ITypedOptionDefinition.Type getType() {
      return ITypedOptionDefinition.Type.List;
   }
}
