package com.pnfsoftware.jeb.core;

import com.pnfsoftware.jeb.util.format.Strings;

public class OptionDefinition implements ITypedOptionDefinition {
   private String description;
   private String name;
   private String defaultValue;

   public OptionDefinition(String var1) {
      this.description = var1;
   }

   public OptionDefinition(String var1, String var2) {
      this.name = var1;
      this.description = var2;
   }

   public OptionDefinition(String var1, String var2, String var3) {
      if (Strings.isBlank(var1)) {
         throw new IllegalArgumentException("name must not be empty");
      } else {
         this.name = var1;
         this.defaultValue = var2;
         this.description = var3;
      }
   }

   @Override
   public String getDescription() {
      return this.description;
   }

   @Override
   public String getName() {
      return this.name;
   }

   @Override
   public String getDefaultValue() {
      return this.defaultValue;
   }

   @Override
   public ITypedOptionDefinition.Type getType() {
      return this.name == null ? ITypedOptionDefinition.Type.Description : ITypedOptionDefinition.Type.Text;
   }

   @Override
   public Object getOptions() {
      return null;
   }
}
