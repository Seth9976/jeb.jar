package com.pnfsoftware.jeb.corei.parsers.asm.decompiler.explorer;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLabel;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.HashSet;
import java.util.Set;

public class vn extends ej {
   public boolean q = false;
   public ICLabel RF = null;
   public ICLabel xK = null;
   public boolean Dw = false;
   public int Uv = 0;
   public int oW = 0;
   public int gO = 0;
   public Set nf = new HashSet();

   @Override
   public String q() {
      StringBuilder var1 = new StringBuilder();

      for (String var3 : this.nf) {
         var1.append(var3 + System.getProperty("line.separator"));
      }

      return var1.toString();
   }

   @Override
   public String RF() {
      StringBuilder var1 = new StringBuilder();
      var1.append("WHILE:");
      Strings.ff(var1, "empty=%d/", this.q ? 1 : 0);
      Strings.ff(var1, "alwaystrue=%d/", this.Dw ? 1 : 0);
      Strings.ff(var1, "continuelabel=%d/", this.RF == null ? 0 : 1);
      Strings.ff(var1, "breaklabel=%d/", this.xK == null ? 0 : 1);
      Strings.ff(var1, "#if=%d/", this.Uv);
      Strings.ff(var1, "#precheckins=%d/", this.oW);
      Strings.ff(var1, "#postcheckins=%d", this.gO);
      var1.append(System.getProperty("line.separator"));
      return var1.toString();
   }

   @Override
   public void q(String var1) {
      this.nf.add(var1);
   }
}
