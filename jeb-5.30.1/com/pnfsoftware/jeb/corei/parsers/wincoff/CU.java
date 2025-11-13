package com.pnfsoftware.jeb.corei.parsers.wincoff;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

@Ser
public class CU {
   public static final int q = 18;
   public static final short RF = 0;
   public static final short xK = -1;
   public static final short Dw = -2;
   public static final short Uv = 0;
   public static final short oW = 32;
   public static final byte gO = 2;
   public static final byte nf = 3;
   public static final byte gP = 6;
   public static final byte za = 101;
   public static final byte lm = 103;
   @SerId(1)
   public byte[] zz = new byte[8];
   @SerId(2)
   public int JY;
   @SerId(3)
   public short HF;
   @SerId(4)
   public short LK;
   @SerId(5)
   public byte io;
   @SerId(6)
   public byte qa;

   public static CU q(ByteBuffer var0) {
      CU var1 = new CU();

      try {
         var0.get(var1.zz);
         var1.JY = var0.getInt();
         var1.HF = var0.getShort();
         var1.LK = var0.getShort();
         var1.io = var0.get();
         var1.qa = var0.get();
      } catch (BufferUnderflowException var2) {
      }

      return var1;
   }

   public static boolean q(CU var0) {
      return var0.LK == 0 && var0.io == 6 && var0.HF > 0 && var0.qa == 0;
   }

   public static boolean RF(CU var0) {
      return var0.LK == 32 && (var0.io == 3 || var0.io == 2) && var0.HF > 0 && var0.qa == 0;
   }

   public static boolean q(CU var0, String var1) {
      return var0.io == 2 && var0.HF > 0 && var0.qa == 0 && var1.startsWith(".text");
   }

   public static boolean xK(CU var0) {
      return var0.LK == 0 && (var0.io == 3 || var0.io == 2) && var0.HF > 0 && var0.qa == 0;
   }

   public static boolean Dw(CU var0) {
      return var0.LK == 0 && var0.io == 3 && var0.HF > 0 && var0.JY == 0;
   }

   public static boolean Uv(CU var0) {
      return var0.LK == 32 && var0.io == 2 && var0.HF == 0 && var0.qa == 0;
   }

   public static boolean oW(CU var0) {
      return var0.LK == 0 && var0.io == 2 && var0.HF == 0 && var0.qa == 0;
   }
}
