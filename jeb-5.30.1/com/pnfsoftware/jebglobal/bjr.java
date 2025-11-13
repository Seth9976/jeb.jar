package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.dex.IDexPrototype;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexType;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class bjr implements IDexPrototype, bjx {
   @SerId(1)
   private com.pnfsoftware.jeb.corei.parsers.dex.bK q;
   @SerId(2)
   private int RF;
   @SerId(3)
   private int xK;
   @SerId(4)
   private int Dw;
   @SerId(5)
   private int[] Uv;

   public bjr(com.pnfsoftware.jeb.corei.parsers.dex.bK var1, int var2, int var3, int var4, int[] var5) {
      this.q = var1;
      this.RF = var2;
      this.xK = var3;
      this.Dw = var4;
      this.Uv = var5;
   }

   @Override
   public int getIndex() {
      return this.RF;
   }

   @Override
   public int getShortyIndex() {
      return this.xK;
   }

   @Override
   public String getShorty() {
      return this.q.RF(this.xK);
   }

   @Override
   public int getReturnTypeIndex() {
      return this.Dw;
   }

   @Override
   public IDexType getReturnType() {
      return this.q.Dw(this.Dw);
   }

   @Override
   public String getReturnTypeSignature(boolean var1) {
      return this.q.q(this.Dw, var1);
   }

   @Override
   public int[] getParameterTypeIndexes() {
      return this.Uv;
   }

   @Override
   public IDexType[] getParameterTypes() {
      bjt[] var1 = new bjt[this.Uv.length];
      int var2 = 0;

      for (int var6 : this.Uv) {
         var1[var2] = this.q.Dw(var6);
         var2++;
      }

      return var1;
   }

   @Override
   public String[] getParameterSignatures(boolean var1) {
      String[] var2 = new String[this.Uv.length];
      int var3 = 0;

      for (int var7 : this.Uv) {
         var2[var3] = this.q.q(var7, var1);
         var3++;
      }

      return var2;
   }

   @Override
   public String generate(boolean var1) {
      return this.q(var1, true, true);
   }

   public String q(boolean var1, boolean var2, boolean var3) {
      return this.q(var1, var2, var3, true);
   }

   public String q(boolean var1, boolean var2, boolean var3, boolean var4) {
      StringBuilder var5 = new StringBuilder("(");
      int var6 = 0;

      for (int var10 : this.Uv) {
         if (!var2 && var6 >= 1) {
            var5.append(", ");
         }

         var5.append(this.q.q(var10, var1, var2, var4));
         var6++;
      }

      var5.append(")");
      if (var3) {
         if (!var2) {
            var5.append(" : ");
         }

         var5.append(this.q.q(this.Dw, var1, var2, var4));
      }

      return var5.toString();
   }

   @Override
   public String toString() {
      return Strings.ff("Proto:#%d=%s", this.RF, this.generate(true));
   }

   public static List q(String var0, String[] var1) {
      if (var0 == null) {
         return null;
      } else {
         int var2 = var0.length();
         if (var2 >= 3 && var0.charAt(0) == '(') {
            ArrayList var3 = new ArrayList(16);
            StringBuilder var4 = new StringBuilder();
            boolean var5 = false;
            int var6 = 1;

            while (var6 < var2) {
               int var7 = var6;
               char var8 = var0.charAt(var6++);
               if (var8 == ')') {
                  if (var5) {
                     return null;
                  }

                  var5 = true;
               } else {
                  int var9;
                  for (var9 = 0; var6 < var2 && var8 == '['; var8 = var0.charAt(var6++)) {
                     var9++;
                  }

                  if (var9 >= 1 && var8 == '[') {
                     return null;
                  }

                  switch (var8) {
                     case 'B':
                     case 'C':
                     case 'D':
                     case 'F':
                     case 'I':
                     case 'J':
                     case 'S':
                     case 'Z':
                        break;
                     case 'E':
                     case 'G':
                     case 'H':
                     case 'K':
                     case 'M':
                     case 'N':
                     case 'O':
                     case 'P':
                     case 'Q':
                     case 'R':
                     case 'T':
                     case 'U':
                     case 'W':
                     case 'X':
                     case 'Y':
                     default:
                        return null;
                     case 'L':
                        int var10 = var0.indexOf(59, var6);
                        if (var10 <= var6 + 1) {
                           return null;
                        }

                        var6 = var10 + 1;
                        break;
                     case 'V':
                        if (!var5) {
                           return null;
                        }
                  }

                  if (var9 >= 1) {
                     var8 = 'L';
                  }

                  String var11 = var0.substring(var7, var6);
                  if (var5) {
                     var3.add(0, var11);
                     var4.insert(0, var8);
                     break;
                  }

                  var3.add(var11);
                  var4.append(var8);
               }
            }

            if (var5 && var6 == var2) {
               if (var1 != null && var1.length >= 1) {
                  var1[0] = var4.toString();
               }

               return var3;
            } else {
               return null;
            }
         } else {
            return null;
         }
      }
   }
}
