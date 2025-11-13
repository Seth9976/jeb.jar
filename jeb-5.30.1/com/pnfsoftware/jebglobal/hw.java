package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import java.io.IOException;

public class hw {
   public static final int q = 1;
   public static final int RF = 2;
   public static final int xK = 4;
   public static final int Dw = 8;
   public static final int Uv = 16;
   public static final int oW = 64;
   public static final int gO = 128;
   public static final int nf = 4096;
   public static final int gP = 16384;
   public long za;
   public String lm;
   public String zz;
   public String JY;
   public int HF;

   public static hw q(Hv var0) throws IOException {
      hw var1 = new hw();
      var1.za = var0.Me();
      var1.lm = var0.xW();
      var1.zz = var0.xW();
      var1.JY = var0.xW();
      var1.HF = var0.Uv();
      return var1;
   }

   public boolean q(int var1) {
      return (this.HF & var1) != 0;
   }

   @Override
   public String toString() {
      return Strings.ff("id=%d,mod=0x%X,name=%s,sig=%s%s", this.za, this.HF, this.lm, this.zz, Strings.isBlank(this.JY) ? "" : ",genSig=" + this.JY);
   }
}
