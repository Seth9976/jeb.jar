package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;

public class ih {
   public static final int q = 1;
   public static final int RF = 2;
   public static final int xK = 4;
   public static final int Dw = 8;
   public static final int Uv = 16;
   public static final int oW = 32;
   public static final int gO = 64;
   public static final int nf = 128;
   public static final int gP = 256;
   public static final int za = 1024;
   public static final int lm = 2048;
   public static final int zz = 4096;
   public long JY;
   public String HF;
   public String LK;
   public String io;
   public int qa;

   public static ih q(Hv var0) throws IOException {
      ih var1 = new ih();
      var1.JY = var0.Hk();
      var1.HF = var0.xW();
      var1.LK = var0.xW();
      var1.io = var0.xW();
      var1.qa = var0.Uv();
      return var1;
   }

   public boolean q(int var1) {
      return (this.qa & var1) != 0;
   }

   @Override
   public String toString() {
      return Strings.ff("id=%d,mod=0x%X,name=%s,sig=%s%s", this.JY, this.qa, this.HF, this.LK, Strings.isBlank(this.io) ? "" : ",genSig=" + this.io);
   }
}
