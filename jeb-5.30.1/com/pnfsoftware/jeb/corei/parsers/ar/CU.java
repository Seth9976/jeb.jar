package com.pnfsoftware.jeb.corei.parsers.ar;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

@Ser
public class CU {
   public static final int q = 60;
   @SerId(1)
   public byte[] RF = new byte[16];
   @SerId(2)
   public byte[] xK = new byte[12];
   @SerId(3)
   public byte[] Dw = new byte[6];
   @SerId(4)
   public byte[] Uv = new byte[6];
   @SerId(5)
   public byte[] oW = new byte[8];
   @SerId(6)
   public byte[] gO = new byte[10];
   @SerId(7)
   public byte[] nf = new byte[2];

   public static CU q(ByteBuffer var0) {
      CU var1 = new CU();

      try {
         var0.get(var1.RF);
         var0.get(var1.xK);
         var0.get(var1.Dw);
         var0.get(var1.Uv);
         var0.get(var1.oW);
         var0.get(var1.gO);
         var0.get(var1.nf);
      } catch (BufferUnderflowException var2) {
      }

      return var1;
   }
}
