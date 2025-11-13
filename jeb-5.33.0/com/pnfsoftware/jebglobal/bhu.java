package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCompound;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaGoto;
import com.pnfsoftware.jeb.core.units.code.java.IJavaLabel;
import com.pnfsoftware.jeb.core.units.code.java.IJavaMethod;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class bhu {
   public static bjl pC(bim var0, String var1) {
      if (var0.d_() instanceof com.pnfsoftware.jeb.corei.parsers.dexdec.Ws) {
         com.pnfsoftware.jeb.corei.parsers.dexdec.Ws var2 = (com.pnfsoftware.jeb.corei.parsers.dexdec.Ws)var0.d_();
         return var2.pC(var1, true);
      } else {
         return null;
      }
   }

   public static bjq A(bim var0, String var1) {
      if (var0.d_() instanceof com.pnfsoftware.jeb.corei.parsers.dexdec.Ws) {
         com.pnfsoftware.jeb.corei.parsers.dexdec.Ws var2 = (com.pnfsoftware.jeb.corei.parsers.dexdec.Ws)var0.d_();
         return var2.A(var1, true);
      } else {
         return null;
      }
   }

   public static bjr kS(bim var0, String var1) {
      if (var0.d_() instanceof com.pnfsoftware.jeb.corei.parsers.dexdec.Ws) {
         com.pnfsoftware.jeb.corei.parsers.dexdec.Ws var2 = (com.pnfsoftware.jeb.corei.parsers.dexdec.Ws)var0.d_();
         return var2.kS(var1, true);
      } else {
         return null;
      }
   }

   public static List pC(List var0) {
      if (var0 == null) {
         return null;
      } else {
         ArrayList var1 = new ArrayList(var0.size());

         for (IJavaExpression var3 : var0) {
            var1.add(var3 == null ? null : var3.duplicate());
         }

         return var1;
      }
   }

   public static List A(List var0) {
      if (var0 == null) {
         return null;
      } else {
         ArrayList var1 = new ArrayList(var0.size());

         for (IJavaStatement var3 : var0) {
            var1.add(var3 == null ? null : var3.duplicate());
         }

         return var1;
      }
   }

   public static List kS(List var0) {
      if (var0 == null) {
         return null;
      } else {
         ArrayList var1 = new ArrayList(var0.size());

         for (IJavaElement var3 : var0) {
            var1.add(var3 == null ? null : var3.duplicate());
         }

         return var1;
      }
   }

   public static int pC(IJavaStatement var0, IJavaBlock var1) {
      return pC(var0, var1, 0, var1.size());
   }

   public static int pC(IJavaStatement var0, IJavaBlock var1, int var2, int var3) {
      for (int var4 = var2; var4 < var3; var4++) {
         if (var1.get(var4) == var0) {
            return var4;
         }
      }

      return -1;
   }

   public static class Av {
      IJavaMethod pC;
      IJavaBlock A;
      Set kS = new HashSet();

      public Av(IJavaMethod var1, IJavaBlock var2) {
         this.pC = var1;
         this.A = var2;
      }

      public boolean pC() {
         this.A(this.A);
         return this.kS.isEmpty() ? true : this.pC(this.pC.getBody());
      }

      private boolean pC(IJavaBlock var1) {
         if (var1 == this.A) {
            return true;
         } else {
            for (int var2 = 0; var2 < var1.size(); var2++) {
               IJavaStatement var3 = var1.get(var2);
               if (var3 instanceof IJavaGoto) {
                  IJavaLabel var4 = ((IJavaGoto)var3).getLabel();
                  if (this.kS.contains(var4)) {
                     return false;
                  }
               }

               if (var3 instanceof IJavaCompound) {
                  for (IJavaBlock var5 : ((IJavaCompound)var3).getBlocks()) {
                     if (!this.pC(var5)) {
                        return false;
                     }
                  }
               }
            }

            return true;
         }
      }

      private void A(IJavaBlock var1) {
         for (int var2 = 0; var2 < var1.size(); var2++) {
            IJavaStatement var3 = var1.get(var2);
            if (var3 instanceof IJavaLabel) {
               this.kS.add((IJavaLabel)var3);
            }

            if (var3 instanceof IJavaCompound) {
               for (IJavaBlock var5 : ((IJavaCompound)var3).getBlocks()) {
                  this.A(var5);
               }
            }
         }
      }
   }
}
