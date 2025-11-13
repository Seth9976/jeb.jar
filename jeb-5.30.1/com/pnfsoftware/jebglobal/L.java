package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.TextBuilder;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class L extends jA {
   List oW = new ArrayList();

   public L(jA var1, uL var2) throws IOException {
      super(var1, 515);
      int var3 = var2.readInt();
      this.xK(var2);

      for (int var4 = 0; var4 < var3; var4++) {
         int var5 = var2.readInt();
         String var6 = zR.q(var2, 128, true);
         this.oW.add(new Aj(var5, var6));
      }

      this.Uv(var2);
   }

   public List q() {
      return this.oW;
   }

   @Override
   public void q(TextBuilder var1) {
      for (Aj var3 : this.oW) {
         var3.q(var1);
      }
   }
}
