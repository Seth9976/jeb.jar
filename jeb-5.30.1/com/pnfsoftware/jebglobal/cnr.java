package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.code.asm.mangling.IUnmangledData;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

public class cnr {
   private static final String q = "PublicInheritanceAttr";
   private static final String RF = "PrivateInheritanceAttr";
   private static final String xK = "VirtualInheritanceAttr";
   private static final String Dw = "IndexInheritanceAttr";
   private final abg Uv;
   private adt oW = new adt();

   public cnr(abg var1) {
      this.Uv = var1;
   }

   public boolean q(String var1) {
      return this.oW.q(var1);
   }

   public boolean q(String var1, String var2, boolean var3, boolean var4) {
      ads var5 = new ads(var1, var2);
      if (var4) {
         var5.q("VirtualInheritanceAttr", Boolean.TRUE);
      }

      if (var3) {
         var5.q("PublicInheritanceAttr", Boolean.TRUE);
      } else {
         var5.q("PrivateInheritanceAttr", Boolean.TRUE);
      }

      var5.q("IndexInheritanceAttr", this.oW.oW(var1));
      return this.oW.q(var5);
   }

   public boolean q() {
      return this.oW.xK();
   }

   @Override
   public String toString() {
      return this.oW.RF();
   }

   public String q(boolean var1, boolean var2) {
      StringBuilder var3 = new StringBuilder();
      var3.append(S.L("C++ class declarations reconstructed from RTTI:"));
      var3.append(Strings.LINESEP);
      var3.append("-----------------------------------------------");
      var3.append(Strings.LINESEP);
      var3.append(Strings.LINESEP);

      for (String var6 : new TreeSet(this.oW.q())) {
         this.q(var3, var6, var1, true);
         if (this.oW.oW(var6) <= 0) {
            var3.append(';');
         } else {
            ArrayList var7 = new ArrayList(this.oW.RF(var6));
            if (var2) {
               cns var8 = new cns(this);
               Collections.sort(var7, var8);
            }

            var3.append(" :");

            for (ads var9 : var7) {
               if (var9.RF("PublicInheritanceAttr") != null) {
                  var3.append(" public");
               }

               if (var9.RF("PrivateInheritanceAttr") != null) {
                  var3.append(" private");
               }

               if (var9.RF("VirtualInheritanceAttr") != null) {
                  var3.append(" virtual");
               }

               this.q(var3, (String)var9.xK(), var1, false);
               var3.append(",");
            }

            var3.setCharAt(var3.length() - 1, ';');
         }

         var3.append(Strings.LINESEP);
         var3.append(Strings.LINESEP);
      }

      return var3.toString();
   }

   private void q(StringBuilder var1, String var2, boolean var3, boolean var4) {
      boolean var5 = false;
      if (var3 && this.Uv.getCodeAnalyzer().getUnmanglerService() != null) {
         IUnmangledData var6 = this.Uv.getCodeAnalyzer().getUnmanglerService().unmangle(var2, true);
         if (var6 != null && var6.getFull() != null && !var6.getFull().isEmpty()) {
            var1.append(" ");
            String var7 = var6.getFull();
            this.q(var1, var7, var4);
            var5 = true;
         }
      }

      if (!var5) {
         var1.append(" ");
         this.q(var1, var2, var4);
      }
   }

   private void q(StringBuilder var1, String var2, boolean var3) {
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
