package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.code.asm.mangling.IUnmangledData;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

public class cgn {
   private final C pC;
   private acb A = new acb();

   public cgn(C var1) {
      this.pC = var1;
   }

   public boolean pC(String var1) {
      return this.A.pC(var1);
   }

   public boolean pC(String var1, String var2, boolean var3, boolean var4) {
      aca var5 = new aca(var1, var2);
      if (var4) {
         var5.pC("VirtualInheritanceAttr", Boolean.TRUE);
      }

      if (var3) {
         var5.pC("PublicInheritanceAttr", Boolean.TRUE);
      } else {
         var5.pC("PrivateInheritanceAttr", Boolean.TRUE);
      }

      var5.pC("IndexInheritanceAttr", this.A.UT(var1));
      return this.A.pC(var5);
   }

   public boolean pC() {
      return this.A.kS();
   }

   @Override
   public String toString() {
      return this.A.A();
   }

   public String pC(boolean var1, boolean var2) {
      StringBuilder var3 = new StringBuilder();
      var3.append(S.L("C++ class declarations reconstructed from RTTI:"));
      var3.append(Strings.LINESEP);
      var3.append("-----------------------------------------------");
      var3.append(Strings.LINESEP);
      var3.append(Strings.LINESEP);

      for (String var6 : new TreeSet(this.A.pC())) {
         this.pC(var3, var6, var1, true);
         if (this.A.UT(var6) <= 0) {
            var3.append(';');
         } else {
            ArrayList var7 = new ArrayList(this.A.A(var6));
            if (var2) {
               cgo var8 = new cgo(this);
               Collections.sort(var7, var8);
            }

            var3.append(" :");

            for (aca var9 : var7) {
               if (var9.pC("PublicInheritanceAttr") != null) {
                  var3.append(" public");
               }

               if (var9.pC("PrivateInheritanceAttr") != null) {
                  var3.append(" private");
               }

               if (var9.pC("VirtualInheritanceAttr") != null) {
                  var3.append(" virtual");
               }

               this.pC(var3, (String)var9.A(), var1, false);
               var3.append(",");
            }

            var3.setCharAt(var3.length() - 1, ';');
         }

         var3.append(Strings.LINESEP);
         var3.append(Strings.LINESEP);
      }

      return var3.toString();
   }

   private void pC(StringBuilder var1, String var2, boolean var3, boolean var4) {
      boolean var5 = false;
      if (var3 && this.pC.getCodeAnalyzer().getUnmanglerService() != null) {
         IUnmangledData var6 = this.pC.getCodeAnalyzer().getUnmanglerService().unmangle(var2, true);
         if (var6 != null && var6.getFull() != null && !var6.getFull().isEmpty()) {
            var1.append(" ");
            String var7 = var6.getFull();
            this.pC(var1, var7, var4);
            var5 = true;
         }
      }

      if (!var5) {
         var1.append(" ");
         this.pC(var1, var2, var4);
      }
   }

   private void pC(StringBuilder var1, String var2, boolean var3) {
      if (var3 && !var2.startsWith("class ") && !var2.startsWith("struct ")) {
         var1.append("class ");
      }

      if (!var3 && var2.startsWith("class ")) {
         var2 = var2.substring(6);
      }

      if (!var3 && var2.startsWith("struct ")) {
         var2 = var2.substring(7);
      }

      var1.append(var2);
   }
}
