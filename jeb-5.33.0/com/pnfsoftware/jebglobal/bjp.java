package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.java.IJavaMethod;
import com.pnfsoftware.jeb.core.units.code.java.JavaOutputSink;
import java.util.Comparator;

class bjp implements Comparator {
   bjp(bjl var1, JavaOutputSink var2) {
      this.A = var1;
      this.pC = var2;
   }

   public int pC(IJavaMethod var1, IJavaMethod var2) {
      String var3 = var1.getName();
      String var4 = var2.getName();
      if (!this.pC.getDisplayPrivateMethodsLast()) {
         return var3.compareTo(var4);
      } else {
         byte var5 = 1;
         if (var1.isPublic()) {
            var5 = 0;
         } else if (var1.isProtected()) {
            var5 = 2;
         } else if (var1.isPrivate()) {
            var5 = 3;
         }

         byte var6 = 1;
         if (var2.isPublic()) {
            var6 = 0;
         } else if (var2.isProtected()) {
            var6 = 2;
         } else if (var2.isPrivate()) {
            var6 = 3;
         }

         return var5 != var6 && !var3.startsWith("<") && !var4.startsWith("<") ? var5 - var6 : var3.compareTo(var4);
      }
   }
}
