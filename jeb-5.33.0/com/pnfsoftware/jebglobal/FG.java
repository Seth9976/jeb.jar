package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class FG extends tH {
   static Set E = new HashSet();
   int sY;
   int ys;
   boolean ld;
   byte[] gp;

   public FG(tH var1, EX var2, boolean var3) throws IOException {
      super(var1, 0);
      this.ld = var3;
      if (!E.contains(this.kS)) {
         throw new IllegalArgumentException(Strings.ff("Illegal XML Tree Node: %Xh", this.kS));
      } else {
         this.sY = var2.readInt();
         this.ys = var2.readInt();
         if (var3) {
            this.gp = this.A(var2);
         } else {
            this.pC(var2);
         }
      }
   }

   static {
      E.add(384);
      E.add(256);
      E.add(257);
      E.add(258);
      E.add(259);
      E.add(260);
   }
}
