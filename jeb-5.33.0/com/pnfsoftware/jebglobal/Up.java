package com.pnfsoftware.jebglobal;

import java.io.IOException;

public class Up extends tH {
   public int E;
   public int[] sY;

   Up(tH var1, EX var2) throws IOException {
      super(var1, 514);
      this.E = var2.readUnsignedByte();
      if (this.E == 0) {
         throw new IOException("Zero id in resource specification");
      } else {
         var2.readByte();
         var2.readShort();
         int var3 = var2.readInt();
         this.pC(var2);
         this.sY = bM.pC(var2, var3);
         this.kS(var2);
      }
   }
}
