package com.pnfsoftware.jebglobal;

import com.google.common.base.Preconditions;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class g {
   public List pC;
   public BitSet A;
   public byte[] kS;

   public static void pC(DataInputStream var0) throws IOException {
      Preconditions.checkArgument(var0.readInt() == 1296389185);
      int var1 = var0.readInt();
      var0.skip(var1 - 8);
   }

   public g(DataInputStream var1) throws IOException {
      Preconditions.checkArgument(var1.readInt() == 1296389185);
      var1.readInt();
      this.kS = new byte[2];
      var1.readFully(this.kS);
      BitSet var2 = new BitSet();
      int var3 = Se.pC(var1);
      int var4 = 0;
      int var5 = 0;

      for (boolean var6 = false; var4 < var3; var4++) {
         int var7 = Se.pC(var1);
         if (var7 != 0 && var6) {
            var2.set(var5, var5 + var7);
         }

         var5 += var7;
         var6 ^= true;
      }

      this.A = var2;
      ArrayList var10 = new ArrayList();
      int var8 = Se.pC(var1);

      for (int var9 = 0; var9 < var8; var9++) {
         var10.add(Se.pC(var1));
      }

      this.pC = var10;
   }
}
