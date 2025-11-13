package com.pnfsoftware.jeb.core.units.code.debug.impl;

import com.pnfsoftware.jeb.util.format.Strings;

public class ValueCharacter extends AbstractValuePrimitive {
   private char v;

   public ValueCharacter(char var1) {
      this.v = var1;
   }

   @Override
   public String getTypeName() {
      return "char";
   }

   public Character getValue() {
      return this.v;
   }

   @Override
   public String toString() {
      return Strings.ff("'%c'", this.getValue());
   }
}
