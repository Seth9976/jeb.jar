package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;

public class oG {
   public static final oG q = new oG((byte)0, 0L, 0L);
   public byte RF;
   public long xK;
   public long Dw;
   public long Uv;

   public static void q(oG var0) {
      if (!var0.q()) {
         Fb.q(var0.RF);
         if (var0.Uv < 0L) {
            throw new si("Illegal JDWPLocation index: " + var0.Uv);
         }
      }
   }

   public oG(byte var1, long var2, long var4, long var6) {
      this.RF = var1;
      this.xK = var2;
      this.Dw = var4;
      this.Uv = var6;
   }

   public oG(byte var1, long var2, long var4) {
      this(var1, var2, var4, 0L);
   }

   public boolean q() {
      return this.equals(q);
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (int)(this.xK ^ this.xK >>> 32);
      var1 = 31 * var1 + (int)(this.Uv ^ this.Uv >>> 32);
      var1 = 31 * var1 + (int)(this.Dw ^ this.Dw >>> 32);
      return 31 * var1 + this.RF;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         oG var2 = (oG)var1;
         if (this.xK != var2.xK) {
            return false;
         } else if (this.Uv != var2.Uv) {
            return false;
         } else {
            return this.Dw != var2.Dw ? false : this.RF == var2.RF;
         }
      }
   }

   @Override
   public String toString() {
      return this.q() ? "NO_LOCATION" : Strings.ff("%s=%d.%d:%d", Fb.q(this.RF), this.xK, this.Dw, this.Uv);
   }
}
