package com.pnfsoftware.jeb.corei.parsers.wincoff;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

@Ser
public class eo {
   public static final int q = 10;
   public static final short RF = 20;
   public static final short xK = 6;
   public static final short Dw = 4;
   public static final short Uv = 5;
   public static final short oW = 6;
   public static final short gO = 7;
   public static final short nf = 8;
   public static final short gP = 9;
   @SerId(1)
   public int za;
   @SerId(2)
   public int lm;
   @SerId(3)
   public short zz;

   public static eo q(ByteBuffer var0) {
      eo var1 = new eo();

      try {
         var1.za = var0.getInt();
         var1.lm = var0.getInt();
         var1.zz = var0.getShort();
      } catch (BufferUnderflowException var2) {
      }

      return var1;
   }
}
