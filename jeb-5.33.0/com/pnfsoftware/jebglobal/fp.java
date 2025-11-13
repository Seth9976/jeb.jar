package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;

public class fp {
   public int pC;
   public int A;
   public int kS;

   public fp(int var1, int var2, int var3) {
      this.pC = var1;
      this.A = var2;
      this.kS = var3;
   }

   public fp(EX var1) throws IOException {
      this.pC = var1.readUnsignedShort();
      if (var1.readByte() != 0) {
         var1.pC(S.L("Illegal resource value, reserved byte should be 0"));
      }

      this.A = var1.readUnsignedByte();
      this.kS = var1.readInt();
   }

   @Override
   public String toString() {
      return Strings.ff("t=%d,d=0x%X,s=%d", this.A, this.kS, this.pC);
   }
}
