package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Hy extends jA {
   static Set oW = new HashSet();
   int gO;
   int nf;
   boolean gP;
   byte[] za;

   public Hy(jA var1, uL var2, boolean var3) throws IOException {
      super(var1, 0);
      this.gP = var3;
      if (!oW.contains(this.xK)) {
         throw new IllegalArgumentException(Strings.ff("Illegal XML Tree Node: %Xh", this.xK));
      } else {
         this.gO = var2.readInt();
         this.nf = var2.readInt();
         if (var3) {
            this.za = this.Dw(var2);
         } else {
            this.xK(var2);
         }
      }
   }

   @Override
   public void q(pK var1) {
      if (!this.gP) {
         throw new IllegalStateException("This option does not support regeneration");
      } else {
         super.q(var1);
         var1.q.writeIntLE(this.gO);
         var1.q.writeIntLE(this.nf);
         var1.q.writeBytes(this.za);
      }
   }

   static {
      oW.add(384);
      oW.add(256);
      oW.add(257);
      oW.add(258);
      oW.add(259);
      oW.add(260);
   }
}
