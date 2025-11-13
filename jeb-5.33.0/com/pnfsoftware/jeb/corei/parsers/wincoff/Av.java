package com.pnfsoftware.jeb.corei.parsers.wincoff;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

@Ser
public class Av {
   @SerId(1)
   public int pC;
   @SerId(2)
   public int A;
   @SerId(3)
   public short kS;

   public static Av pC(ByteBuffer var0) {
      Av var1 = new Av();

      try {
         var1.pC = var0.getInt();
         var1.A = var0.getInt();
         var1.kS = var0.getShort();
      } catch (BufferUnderflowException var2) {
      }

      return var1;
   }
}
