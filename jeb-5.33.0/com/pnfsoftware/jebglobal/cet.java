package com.pnfsoftware.jebglobal;

import java.nio.ByteBuffer;

public class cet {
   public int pC;
   public int A;
   public int kS;
   public int wS;
   public long UT;
   public long E;
   public long sY;
   public long ys;
   public long ld;
   public long gp;
   public long oT;

   public static cet pC(ByteBuffer var0, boolean var1) {
      cet var2 = new cet();
      var2.pC = var0.getInt();
      var2.A = var0.getInt();
      var2.kS = var0.getInt();
      if (var1) {
         var2.wS = var0.getInt();
      }

      var2.UT = var1 ? var0.getLong() : var0.getInt() & 4294967295L;
      var2.E = var1 ? var0.getLong() : var0.getInt() & 4294967295L;
      var2.sY = var1 ? var0.getLong() : var0.getInt() & 4294967295L;
      var2.ys = var1 ? var0.getLong() : var0.getInt() & 4294967295L;
      var2.ld = var1 ? var0.getLong() : var0.getInt() & 4294967295L;
      var2.gp = var1 ? var0.getLong() : var0.getInt() & 4294967295L;
      var2.oT = var1 ? var0.getLong() : var0.getInt() & 4294967295L;
      return var2;
   }
}
