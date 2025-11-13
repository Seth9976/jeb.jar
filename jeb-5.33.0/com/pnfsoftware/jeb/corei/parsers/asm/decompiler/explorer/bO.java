package com.pnfsoftware.jeb.corei.parsers.asm.decompiler.explorer;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLabel;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.HashSet;
import java.util.Set;

public class bO extends Ws {
   public boolean pC = false;
   public ICLabel A = null;
   public ICLabel kS = null;
   public boolean wS = false;
   public int UT = 0;
   public int E = 0;
   public int sY = 0;
   public Set ys = new HashSet();

   @Override
   public String pC() {
      StringBuilder var1 = new StringBuilder();

      for (String var3 : this.ys) {
         var1.append(var3 + System.getProperty("line.separator"));
      }

      return var1.toString();
   }

   @Override
   public String A() {
      StringBuilder var1 = new StringBuilder();
      var1.append("DOWHILE:");
      Strings.ff(var1, "empty=%d/", this.pC ? 1 : 0);
      Strings.ff(var1, "alwaystrue=%d/", this.wS ? 1 : 0);
      Strings.ff(var1, "continuelabel=%d/", this.A == null ? 0 : 1);
      Strings.ff(var1, "breaklabel=%d/", this.kS == null ? 0 : 1);
      Strings.ff(var1, "#if=%d/", this.UT);
      Strings.ff(var1, "#precheckins=%d/", this.E);
      Strings.ff(var1, "#postcheckins=%d", this.sY);
      var1.append(System.getProperty("line.separator"));
      return var1.toString();
   }

   public void pC(String var1) {
      this.ys.add(var1);
   }
}
