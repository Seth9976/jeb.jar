package com.pnfsoftware.jebglobal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Px extends tH {
   List E = new ArrayList();

   public Px(tH var1, EX var2) throws IOException {
      super(var1, 515);
      int var3 = var2.readInt();
      this.pC(var2);

      for (int var4 = 0; var4 < var3; var4++) {
         int var5 = var2.readInt();
         String var6 = bM.pC(var2, 128, true);
         this.E.add(new VL(var5, var6));
      }

      this.kS(var2);
   }

   public List pC() {
      return this.E;
   }
}
