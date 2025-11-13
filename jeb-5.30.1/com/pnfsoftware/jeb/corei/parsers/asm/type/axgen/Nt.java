package com.pnfsoftware.jeb.corei.parsers.asm.type.axgen;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class Nt {
   @SerId(1)
   long q;

   Nt(long var1) {
      this.q = var1;
   }

   public int q() {
      return (int)(this.q & 3L);
   }

   public Nt.eo RF() {
      int var1 = (int)(this.q >> 16 & 65535L);
      return new Nt.eo(var1 & 7, var1 >> 3 & 31, var1 >> 8 & 0xFF);
   }

   public Nt.eo xK() {
      int var1 = (int)(this.q >> 32 & 65535L);
      return new Nt.eo(var1 & 7, var1 >> 3 & 31, var1 >> 8 & 0xFF);
   }

   public static class eo {
      public int q;
      public int RF;
      public int xK;

      eo(int var1, int var2, int var3) {
         this.q = var1;
         this.RF = var2;
         this.xK = var3;
      }
   }
}
