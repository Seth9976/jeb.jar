package com.pnfsoftware.jeb.util.format;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;

public class PrettyPrinter {
   public static final int DEFAULT_MAX_STRING_LENGTH = 200;
   public static final int DEFAULT_MAX_ARRAY_ITEM_COUNT = 40;
   public static final int DEFAULT_MAX_COLLECTION_ITEM_COUNT = 40;
   public static final int DEFAULT_MAX_MAP_ITEM_COUNT = 40;
   private int maxEltsPerString = 200;
   private int maxEltsPerArray = 40;
   private int maxEltsPerColl = 40;
   private int maxEltsPerMap = 40;
   private boolean allowUnsafeCalls;
   private boolean newlinesForMaps;
   private String indentString;

   public PrettyPrinter(boolean var1, int var2) {
      this.allowUnsafeCalls = var1;
      if (var2 < 0) {
         this.newlinesForMaps = false;
         this.indentString = "";
      } else {
         this.newlinesForMaps = true;
         this.indentString = Strings.spaces(var2);
      }
   }

   public PrettyPrinter(boolean var1) {
      this(var1, 2);
   }

   public PrettyPrinter() {
      this(true, 2);
   }

   public void setMaxStringLength(int var1) {
      this.maxEltsPerString = var1;
   }

   public void setMaxArrayLength(int var1) {
      this.maxEltsPerArray = var1;
   }

   public void setMaxCollectionLength(int var1) {
      this.maxEltsPerColl = var1;
   }

   public void setMaxMapLength(int var1) {
      this.maxEltsPerMap = var1;
   }

   public String format(Object var1) {
      StringBuilder var2 = new StringBuilder();
      this.toString(var1, var2, 0, false);
      return var2.toString();
   }

   private void toString(Object var1, StringBuilder var2, int var3, boolean var4) {
      if (!var4 && var3 > 0 && var2.length() > 1 && var2.charAt(var2.length() - 1) == '\n') {
         this.indent(var3, var2);
      }

      if (var1 == null) {
         var2.append("null");
      } else if (var1 instanceof Character) {
         Strings.ff(var2, "'%s'", Formatter.escapeCharacter((Character)var1));
      } else if (var1 instanceof Boolean || var1 instanceof Integer || var1 instanceof Double) {
         Strings.ff(var2, "%s", var1);
      } else if (var1 instanceof Byte) {
         Strings.ff(var2, "%d", var1);
      } else if (var1 instanceof Short) {
         Strings.ff(var2, "%d", var1);
      } else if (var1 instanceof Long) {
         Strings.ff(var2, "%sL", var1);
      } else if (var1 instanceof Float) {
         Strings.ff(var2, "%sF", var1);
      } else if (var1 instanceof String var5) {
         if (var5.length() > this.maxEltsPerString) {
            var5 = var5.substring(0, this.maxEltsPerString) + "...";
         }

         Strings.ff(var2, "\"%s\"", Formatter.escapeString(var5));
      } else if (var1.getClass().isArray()) {
         boolean var12 = false;
         int var6 = Array.getLength(var1);
         if (var6 > this.maxEltsPerArray) {
            var6 = this.maxEltsPerArray;
            var12 = true;
         }

         var2.append('(');

         for (int var7 = 0; var7 < var6; var7++) {
            if (var7 > 0) {
               var2.append(", ");
            }

            Object var8 = Array.get(var1, var7);
            this.toString(var8, var2, var3, true);
         }

         if (var12) {
            var2.append(", ...");
         }

         var2.append(')');
      } else {
         boolean var13 = this.allowUnsafeCalls || var1.getClass().getName().startsWith("java.");
         if (!var13) {
            String var14 = this.formatObject(var1);
            if (var14 == null) {
               var14 = "?";
            }

            var2.append(var14);
         } else if (var1 instanceof CharSequence) {
            String var15 = ((CharSequence)var1).toString();
            if (var15.length() > this.maxEltsPerString) {
               var15 = var15.substring(0, this.maxEltsPerString) + "...";
            }

            Strings.ff(var2, "\"%s\"", Formatter.escapeString(var15));
         } else if (var1 instanceof Map var16) {
            var2.append('{');
            if (!var16.isEmpty()) {
               boolean var20 = false;
               int var22 = var16.size();
               if (var22 > this.maxEltsPerMap) {
                  var22 = this.maxEltsPerMap;
                  var20 = true;
               }

               if (this.newlinesForMaps) {
                  var2.append('\n');
               }

               int var9 = 0;

               for (Entry var11 : var16.entrySet()) {
                  this.toString(var11.getKey(), var2, var3 + 1, false);
                  var2.append(": ");
                  this.toString(var11.getValue(), var2, var3 + 1, true);
                  if (++var9 < var16.size()) {
                     var2.append(',');
                  }

                  if (this.newlinesForMaps) {
                     var2.append('\n');
                  } else {
                     var2.append(' ');
                  }

                  if (var9 >= var22) {
                     break;
                  }
               }

               this.indent(var3, var2);
               if (var20) {
                  var2.append(", ...");
               }
            }

            var2.append('}');
         } else if (var1 instanceof Collection var17) {
            var2.append('[');
            if (!var17.isEmpty()) {
               boolean var21 = false;
               int var23 = var17.size();
               if (var23 > this.maxEltsPerColl) {
                  var23 = this.maxEltsPerColl;
                  var21 = true;
               }

               int var24 = 0;

               for (Object var26 : var17) {
                  if (var24 > 0) {
                     var2.append(", ");
                  }

                  this.toString(var26, var2, var3, true);
                  if (++var24 >= var23) {
                     break;
                  }
               }

               if (var21) {
                  var2.append(", ...");
               }
            }

            var2.append(']');
         } else {
            String var18 = this.formatObject(var1);
            if (var18 != null) {
               var2.append(var18);
            } else {
               var18 = var1.toString();
               if (var18.length() > this.maxEltsPerString) {
                  var18 = var18.substring(0, this.maxEltsPerString) + "...";
               }

               Strings.ff(var2, "`%s`", Formatter.escapeString(var18));
            }
         }
      }
   }

   private void indent(int var1, StringBuilder var2) {
      for (int var3 = 0; var3 < var1; var3++) {
         var2.append(this.indentString);
      }
   }

   protected String formatObject(Object var1) {
      return null;
   }
}
