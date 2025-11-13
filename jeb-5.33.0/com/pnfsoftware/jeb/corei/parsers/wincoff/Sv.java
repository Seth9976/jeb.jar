package com.pnfsoftware.jeb.corei.parsers.wincoff;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

@Ser
public class Sv {
   @SerId(1)
   public byte[] pC = new byte[8];
   @SerId(2)
   public int A;
   @SerId(3)
   public short kS;
   @SerId(4)
   public short wS;
   @SerId(5)
   public byte UT;
   @SerId(6)
   public byte E;

   public static Sv pC(ByteBuffer var0) {
      Sv var1 = new Sv();

      try {
         var0.get(var1.pC);
         var1.A = var0.getInt();
         var1.kS = var0.getShort();
         var1.wS = var0.getShort();
         var1.UT = var0.get();
         var1.E = var0.get();
      } catch (BufferUnderflowException var2) {
      }

      return var1;
   }

   public static boolean pC(Sv var0) {
      return var0.wS == 0 && var0.UT == 6 && var0.kS > 0 && var0.E == 0;
   }

   public static boolean A(Sv var0) {
      return var0.wS == 32 && (var0.UT == 3 || var0.UT == 2) && var0.kS > 0 && var0.E == 0;
   }

   public static boolean kS(Sv var0) {
      return var0.wS == 0 && (var0.UT == 3 || var0.UT == 2) && var0.kS > 0 && var0.E == 0;
   }

   public static boolean wS(Sv var0) {
      return var0.wS == 0 && var0.UT == 3 && var0.kS > 0 && var0.A == 0;
   }

   public static boolean UT(Sv var0) {
      return var0.wS == 32 && var0.UT == 2 && var0.kS == 0 && var0.E == 0;
   }

   public static boolean E(Sv var0) {
      return var0.wS == 0 && var0.UT == 2 && var0.kS == 0 && var0.E == 0;
   }
}
