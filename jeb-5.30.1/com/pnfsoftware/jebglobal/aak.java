package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.type.IArrayType;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IReferenceType;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.List;

public class aak {
   static String q(String var0, String var1) {
      int var2 = 0;

      for (int var3 = 0; var3 < var0.length() && var3 < var1.length() && var0.charAt(var0.length() - var3 - 1) == var1.charAt(var1.length() - var3 - 1); var3++) {
         var2++;
      }

      if (var2 > 0 && var2 < var1.length() && Strings.containsAt(var1, var1.length() - var2, " for ")) {
         var1 = var1.substring(0, var1.length() - var2);
      } else if (var1.substring(0, var1.length() - var2).equals("vftable_")) {
         var1 = "vftable";
      } else if (var0.startsWith("construction vtable for ")) {
         String var5 = var0.substring("construction vtable for ".length());
         int var4 = var1.indexOf(" for ");
         if (var4 > 0 && var5.startsWith(var1.substring(var4 + " for ".length()))) {
            var1 = var1.substring(0, var4);
         } else if (var1.startsWith("vftable_") && var5.startsWith(var1.substring("vftable_".length()))) {
            var1 = "vftable";
         }
      }

      return var1;
   }

   static String q(String var0, int var1, List var2, List var3) {
      boolean var4 = false;
      String var5 = "uint" + var1 * 8 + "_t";
      if (var0.startsWith("vtable for ") || var0.startsWith("construction vtable for")) {
         byte var6 = 0;
         StringBuilder var7 = new StringBuilder();
         int var8 = 0;
         boolean var9 = false;

         for (int var10 = 0; var10 < var2.size(); var10++) {
            INativeType var11 = (INativeType)var2.get(var10);
            String var12 = (String)var3.get(var10);
            if (var6 == 0) {
               if (!var5.equals(var12)) {
                  var4 = true;
                  break;
               }

               var6 = 1;
               var8++;
            } else if (var6 == 1) {
               if (var11 instanceof IReferenceType && Strings.isContainedIn(var12, "typeinfo", "void*")) {
                  if ("void*".equals(var12)) {
                     var9 = true;
                  }

                  var6 = 2;
               } else {
                  if (var11.getSize() != var1 || !var5.equals(var12)) {
                     var4 = true;
                     break;
                  }

                  var8++;
               }
            } else if (var6 == 2) {
               int var13;
               if (var11 instanceof IArrayType && ((IArrayType)var11).getElementType() instanceof IReferenceType && "vftable".equals(var12)) {
                  var13 = ((IArrayType)var11).getElementCount();
               } else {
                  int var14;
                  for (var14 = var10; var14 < var2.size(); var14++) {
                     INativeType var15 = (INativeType)var2.get(var14);
                     String var16 = (String)var3.get(var14);
                     if (!(var15 instanceof IReferenceType)) {
                        break;
                     }

                     if (!"void*".equals(var16)) {
                        var4 = true;
                        break;
                     }
                  }

                  if (var4 || var10 == var14) {
                     var4 = true;
                     break;
                  }

                  var13 = var14 - var10;
                  var10 = var14 - 1;
                  var7.append("_flat");
               }

               var6 = 0;
               if (!var7.isEmpty() || var8 != 1) {
                  var7.append('_').append(var8);
                  var8 = 0;
               }

               if (var9) {
                  var7.append("_g");
               }

               var7.append('_').append(var13);
            }
         }

         if (!var4 && var6 == 0) {
            return "vtable" + var7.toString();
         }
      }

      if (var0.startsWith("VTT for ")) {
         for (int var17 = 0; var17 < var2.size(); var17++) {
            INativeType var19 = (INativeType)var2.get(var17);
            String var20 = (String)var3.get(var17);
            if (var19.getSize() != var1 || !"vtable".equals(var20)) {
               var4 = true;
               break;
            }
         }

         if (!var4) {
            return "VTT_" + var2.size();
         }
      }

      if (var0.startsWith("typeinfo for ")
         && (var2.size() == 2 || var2.size() == 4)
         && var2.get(0) instanceof IReferenceType
         && ((String)var3.get(0)).startsWith("vtable for ")) {
         String var18 = ((String)var3.get(0)).substring("vtable for ".length());
         if (var2.get(1) instanceof IReferenceType && "typeinfo name".equals(var3.get(1))) {
            if (var2.size() == 2) {
               var3.set(0, "vtable");
               return "typeinfo_short_" + var18;
            }

            if (var5.equals(var3.get(2))
               && var2.get(3) instanceof IReferenceType
               && ((String)var3.get(3)).startsWith("typeinfo for ")
               && var0.substring("typeinfo for ".length()).startsWith(((String)var3.get(3)).substring("typeinfo for ".length()))) {
               var3.set(0, "vtable");
               var3.set(3, "typeinfo");
               return "typeinfo_long_" + var18;
            }
         }
      }

      return null;
   }

   static boolean q(String var0) {
      return var0 != null
         && (var0.startsWith("vtable for ") || var0.startsWith("construction vtable for") || var0.startsWith("VTT for ") || var0.startsWith("typeinfo for "));
   }
}
