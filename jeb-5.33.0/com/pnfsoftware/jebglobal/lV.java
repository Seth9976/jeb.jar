package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;

public class lV {
   int pC;
   int A;
   int kS;
   fp wS;

   public lV(EX var1) throws IOException {
      this.pC = var1.readInt();
      this.A = var1.readInt();
      this.kS = var1.readInt();
      this.wS = new fp(var1);
   }

   @Override
   public String toString() {
      return Strings.ff("ns=%d,name=%d,raw=0x%X,val={%s}", this.pC, this.A, this.kS, this.wS);
   }
}
