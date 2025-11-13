package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaClass;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCompound;
import com.pnfsoftware.jeb.core.units.code.java.IJavaConstant;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDecompilableElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaGoto;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIdentifier;
import com.pnfsoftware.jeb.core.units.code.java.IJavaLabel;
import com.pnfsoftware.jeb.core.units.code.java.IJavaMethod;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.util.collect.IdentityHashSet;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class blr {
   public static bni q(bmj var0, String var1) {
      if (var0.d_() instanceof com.pnfsoftware.jeb.corei.parsers.dexdec.ej) {
         com.pnfsoftware.jeb.corei.parsers.dexdec.ej var2 = (com.pnfsoftware.jeb.corei.parsers.dexdec.ej)var0.d_();
         return var2.q(var1, true);
      } else {
         return null;
      }
   }

   public static bnn RF(bmj var0, String var1) {
      if (var0.d_() instanceof com.pnfsoftware.jeb.corei.parsers.dexdec.ej) {
         com.pnfsoftware.jeb.corei.parsers.dexdec.ej var2 = (com.pnfsoftware.jeb.corei.parsers.dexdec.ej)var0.d_();
         return var2.RF(var1, true);
      } else {
         return null;
      }
   }

   public static bno xK(bmj var0, String var1) {
      if (var0.d_() instanceof com.pnfsoftware.jeb.corei.parsers.dexdec.ej) {
         com.pnfsoftware.jeb.corei.parsers.dexdec.ej var2 = (com.pnfsoftware.jeb.corei.parsers.dexdec.ej)var0.d_();
         return var2.xK(var1, true);
      } else {
         return null;
      }
   }

   public static List q(List var0) {
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

   public static List RF(List var0) {
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

   public static List xK(List var0) {
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

   public static int q(IJavaStatement var0, IJavaBlock var1) {
      return q(var0, var1, 0, var1.size());
   }

   public static int q(IJavaStatement var0, IJavaBlock var1, int var2, int var3) {
      for (int var4 = var2; var4 < var3; var4++) {
         if (var1.get(var4) == var0) {
            return var4;
         }
      }

      return -1;
   }

   public static void q(IJavaDecompilableElement var0) {
      if (var0 instanceof IJavaClass var1) {
         for (IJavaMethod var4 : var1.getMethods()) {
            q(var4);
         }
      } else if (var0 instanceof IJavaMethod var2) {
         q(var2);
      }
   }

   public static int q(IJavaMethod var0) {
      IdentityHashSet var1 = new IdentityHashSet();

      for (IJavaStatement var3 : var0.getStatements()) {
         q(var3, var1);
      }

      return var1.size();
   }

   public static int q(IJavaElement var0) {
      IdentityHashSet var1 = new IdentityHashSet();
      return q(var0, var1);
   }

   private static int q(IJavaElement var0, Set var1) {
      ArrayDeque var2 = new ArrayDeque();
      var2.add(var0);

      while (!var2.isEmpty()) {
         IJavaElement var3 = (IJavaElement)var2.remove();
         if (!(var3 instanceof IJavaDecompilableElement)
            && !(var3 instanceof IJavaConstant)
            && !(var3 instanceof IJavaIdentifier)
            && !(var3 instanceof IJavaLabel)
            && !var1.add(var3)) {
            throw new IllegalStateException(Strings.ff("Duplicate found: %s (type:%s) in: %s", var3, var3.getClass().getSimpleName(), var0));
         }

         if (var3 instanceof IJavaCompound var4) {
            var2.addAll(var4.getSubElements(true));
         } else {
            var2.addAll(var3.getSubElements());
         }
      }

      return var1.size();
   }

   public static class eo {
      IJavaMethod q;
      IJavaBlock RF;
      Set xK = new HashSet();

      public eo(IJavaMethod var1, IJavaBlock var2) {
         this.q = var1;
         this.RF = var2;
      }

      public boolean q() {
         this.RF(this.RF);
         return this.xK.isEmpty() ? true : this.q(this.q.getBody());
      }

      private boolean q(IJavaBlock var1) {
         if (var1 == this.RF) {
            return true;
         } else {
            for (int var2 = 0; var2 < var1.size(); var2++) {
               IJavaStatement var3 = var1.get(var2);
               if (var3 instanceof IJavaGoto) {
                  IJavaLabel var4 = ((IJavaGoto)var3).getLabel();
                  if (this.xK.contains(var4)) {
                     return false;
                  }
               }

               if (var3 instanceof IJavaCompound) {
                  for (IJavaBlock var5 : ((IJavaCompound)var3).getBlocks()) {
                     if (!this.q(var5)) {
                        return false;
                     }
                  }
               }
            }

            return true;
         }
      }

      private void RF(IJavaBlock var1) {
         for (int var2 = 0; var2 < var1.size(); var2++) {
            IJavaStatement var3 = var1.get(var2);
            if (var3 instanceof IJavaLabel) {
               this.xK.add((IJavaLabel)var3);
            }

            if (var3 instanceof IJavaCompound) {
               for (IJavaBlock var5 : ((IJavaCompound)var3).getBlocks()) {
                  this.RF(var5);
               }
            }
         }
      }
   }
}
