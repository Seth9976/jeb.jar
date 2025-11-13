package com.pnfsoftware.jeb.corei.parsers.asm.type.axgen;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class bO {
   @SerId(1)
   long pC;

   bO(long var1) {
      this.pC = var1;
   }

   public int pC() {
      return (int)(this.pC & 3L);
   }

   public bO.Av A() {
      int var1 = (int)(this.pC >> 16 & 65535L);
      return new bO.Av(var1 & 7, var1 >> 3 & 31, var1 >> 8 & 0xFF);
   }

   public bO.Av kS() {
      int var1 = (int)(this.pC >> 32 & 65535L);
      return new bO.Av(var1 & 7, var1 >> 3 & 31, var1 >> 8 & 0xFF);
   }

   public static class Av {
      public int pC;
      public int A;
      public int kS;

      Av(int var1, int var2, int var3) {
         this.pC = var1;
         this.A = var2;
         this.kS = var3;
      }
   }
}
