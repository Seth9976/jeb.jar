package com.pnfsoftware.jeb.corei.parsers.ar;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

@Ser
public class Sv {
   @SerId(1)
   public byte[] pC = new byte[16];
   @SerId(2)
   public byte[] A = new byte[12];
   @SerId(3)
   public byte[] kS = new byte[6];
   @SerId(4)
   public byte[] wS = new byte[6];
   @SerId(5)
   public byte[] UT = new byte[8];
   @SerId(6)
   public byte[] E = new byte[10];
   @SerId(7)
   public byte[] sY = new byte[2];

   public static Sv pC(ByteBuffer var0) {
      Sv var1 = new Sv();

      try {
         var0.get(var1.pC);
         var0.get(var1.A);
         var0.get(var1.kS);
         var0.get(var1.wS);
         var0.get(var1.UT);
         var0.get(var1.E);
         var0.get(var1.sY);
      } catch (BufferUnderflowException var2) {
      }

      return var1;
   }
}
