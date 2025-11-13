package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.output.code.coordinates.ICodeCoordinates;
import com.pnfsoftware.jeb.core.output.code.coordinates.InstructionCoordinates;
import java.util.HashSet;
import java.util.Set;

public class bjh {
   com.pnfsoftware.jeb.corei.parsers.dex.bK q;
   bip RF;
   bje xK;
   bji Dw;
   Set Uv = new HashSet();
   Set oW = new HashSet();
   bjh.eo gO = new bjh.eo();

   bjh(bip var1, com.pnfsoftware.jeb.corei.parsers.dex.bK var2, bje var3) {
      this.RF = var1;
      this.q = var2;
      this.xK = var3;
   }

   void q(bji var1) {
      this.Dw = var1;
   }

   void q(int var1, boolean var2) {
      if (var2) {
         if (!this.Uv.add(var1)) {
            this.gO.q.add(var1);
         }
      } else {
         this.oW.add(var1);
      }
   }

   bjh.eo q() {
      for (int var2 : this.oW) {
         if (!this.Uv.contains(var2)) {
            this.gO.xK.add(var2);
         }
      }

      for (int var4 : this.Uv) {
         if (!this.oW.contains(var4)) {
            this.gO.RF.add(var4);
         }
      }

      return this.gO;
   }

   void q(bip var1, int var2, boolean var3) {
      InstructionCoordinates var4 = new InstructionCoordinates(var1.q(), var2);
      String var5 = this.q.RF((ICodeCoordinates)var4);
      if (var5 == null) {
         if (this.Dw != null) {
            bji.eo var6 = this.Dw.q(var2);
            if (var6 != null) {
               if (!var6.RF.isEmpty()) {
                  var5 = "try_" + bip.xK(var2);
               } else if (!var6.xK.isEmpty()) {
                  var5 = "tryend_" + bip.xK(var2);
               } else if (!var6.Dw.isEmpty()) {
                  var5 = "catch_" + bip.xK(var2);
               }
            }
         }

         if (var5 == null) {
            var5 = bip.xK(var2);
         }
      }

      this.xK.appendAndRecord(":" + var5, ItemClassIdentifiers.LABEL, this.q.q(var4), var3 ? 1 : 0);
      this.q(var2, var3);
   }

   static class eo {
      Set q = new HashSet();
      Set RF = new HashSet();
      Set xK = new HashSet();
   }
}
