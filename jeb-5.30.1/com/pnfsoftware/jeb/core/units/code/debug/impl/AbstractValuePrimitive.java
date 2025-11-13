package com.pnfsoftware.jeb.core.units.code.debug.impl;

import com.pnfsoftware.jeb.core.units.code.debug.ITypedValue;
import com.pnfsoftware.jeb.util.format.Formatter;

public abstract class AbstractValuePrimitive implements ITypedValue {
   @Override
   public String format() {
      return this.toString();
   }

   public static ITypedValue parseValue(String var0, String var1) {
      if (var1 == null) {
         return null;
      } else {
         switch (var0) {
            case "boolean":
               return new ValueBoolean(Boolean.parseBoolean(var1));
            case "char":
               if (var1.length() == 3 && var1.charAt(0) == '\'' && var1.charAt(2) == '\'') {
                  return new ValueCharacter(var1.charAt(1));
               } else {
                  if (var1.length() != 1) {
                     return null;
                  }

                  return new ValueCharacter(var1.charAt(0));
               }
            case "raw":
               byte[] var4 = Formatter.hexStringToByteArray(var1);
               if (var4 == null) {
                  return null;
               }

               return new ValueRaw(var4);
            case "string":
               return new ValueString(var1);
            case "byte":
            case "short":
            case "int":
            case "long":
            case "double":
            case "float":
               try {
                  return AbstractValueNumber.parseNumber(var0, var1);
               } catch (NumberFormatException var5) {
                  return null;
               }
            default:
               return null;
         }
      }
   }
}
