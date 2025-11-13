package com.pnfsoftware.jebglobal;

import com.google.common.base.Preconditions;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class Yy {
   static final int q = 1296389185;
   public List RF;
   public BitSet xK;
   public byte[] Dw;

   public static void q(DataInputStream var0) throws IOException {
      Preconditions.checkArgument(var0.readInt() == 1296389185);
      int var1 = var0.readInt();
      var0.skip(var1 - 8);
   }

   public Yy(DataInputStream var1) throws IOException {
      Preconditions.checkArgument(var1.readInt() == 1296389185);
      var1.readInt();
      this.Dw = new byte[2];
      var1.readFully(this.Dw);
      BitSet var2 = new BitSet();
      int var3 = TS.q(var1);
      int var4 = 0;
      int var5 = 0;

      for (boolean var6 = false; var4 < var3; var4++) {
         int var7 = TS.q(var1);
         if (var7 != 0 && var6) {
            var2.set(var5, var5 + var7);
         }

         var5 += var7;
         var6 ^= true;
      }

      this.xK = var2;
      ArrayList var10 = new ArrayList();
      int var8 = TS.q(var1);

      for (int var9 = 0; var9 < var8; var9++) {
         var10.add(TS.q(var1));
      }

      this.RF = var10;
   }
}
