package com.pnfsoftware.jeb.core.units.code.debug.impl;

import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.format.Strings;

public abstract class AbstractValueNumber extends AbstractValuePrimitive {
   public abstract Number getValue();

   @Override
   public String toString() {
      Number var1 = this.getValue();
      long var2 = var1.longValue();
      return var2 >= -9L && var2 <= 9L ? Long.toString(var2) : Strings.ff("%d (0x%X)", var2, var2);
   }

   public static AbstractValueNumber parseNumber(String var0, String var1) {
      switch (var0) {
         case "byte":
            return new ValueByte((byte)Conversion.stringToInt(var1));
         case "short":
            return new ValueShort((short)Conversion.stringToInt(var1));
         case "int":
            return new ValueInteger(Conversion.stringToInt(var1));
         case "long":
            return new ValueLong(Conversion.stringToLong(var1));
         case "float":
            return new ValueFloat(Float.parseFloat(var1));
         case "double":
            return new ValueDouble(Double.parseDouble(var1));
         default:
            return null;
      }
   }
}
