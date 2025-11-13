package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;

public class AM {
   public byte pC;
   public int A;
   public int kS;
   public long wS;
   public long UT;
   public String E;
   public Jx sY;
   public boolean ys;
   public boolean ld;
   public long gp;
   public int oT;
   public int fI;
   public long WR;
   public String NS;

   private AM(byte var1) {
      this.pC = var1;
   }

   public static AM pC(int var0) {
      AM var1 = new AM((byte)1);
      var1.A = var0;
      return var1;
   }

   public static AM pC(long var0) {
      AM var2 = new AM((byte)4);
      var2.UT = var0;
      return var2;
   }

   public static AM pC(String var0) {
      AM var1 = new AM((byte)5);
      var1.E = var0;
      return var1;
   }

   public static AM A(String var0) {
      AM var1 = new AM((byte)6);
      var1.E = var0;
      return var1;
   }

   public static AM pC(Jx var0) {
      Jx.pC(var0);
      AM var1 = new AM((byte)7);
      var1.sY = var0;
      return var1;
   }

   public static AM pC(long var0, boolean var2, boolean var3) {
      AM var4 = new AM((byte)8);
      var4.UT = var0;
      var4.ys = var2;
      var4.ld = var3;
      return var4;
   }

   public static AM pC(long var0, long var2) {
      AM var4 = new AM((byte)9);
      var4.UT = var0;
      var4.gp = var2;
      return var4;
   }

   public static AM pC(long var0, int var2, int var3) {
      CA.pC(var2);
      Fn.pC(var3);
      AM var4 = new AM((byte)10);
      var4.wS = var0;
      var4.oT = var2;
      var4.fI = var3;
      return var4;
   }

   @Override
   public String toString() {
      return Strings.ff("kind=%d", this.pC);
   }
}
