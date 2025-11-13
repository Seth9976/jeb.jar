package com.pnfsoftware.jeb.core.units.code.debug.impl;

import com.pnfsoftware.jeb.util.format.Strings;

public class ValueString extends AbstractValuePrimitive {
   private Long objectId;
   private String v;

   public ValueString(String var1) {
      this(var1, null);
   }

   public ValueString(String var1, Long var2) {
      this.v = var1;
      this.objectId = var2;
   }

   @Override
   public String getTypeName() {
      return "string";
   }

   public String getValue() {
      return this.v;
   }

   public Long getObjectId() {
      return this.objectId;
   }

   @Override
   public String toString() {
      return Strings.ff("string@%d:\"%s\"", this.getObjectId(), this.getValue());
   }
}
