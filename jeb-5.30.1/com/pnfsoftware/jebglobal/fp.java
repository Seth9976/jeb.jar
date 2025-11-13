package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class fp extends CW {
   private static final ILogger q = GlobalLog.getLogger(fp.class);
   @SerId(1)
   private int gP;
   @SerId(2)
   private boolean za;
   @SerId(3)
   private boolean lm;

   public fp(long var1, int var3, boolean var4, boolean var5) {
      super(var5 ? 1 : 3, var3 == 64 ? 64 : 32, var1, var5 ? 65536 : 0);
      this.gP = var3;
      this.za = var4;
      this.lm = var5;
   }

   @Override
   public long getOperandValue(long var1) {
      if (this.gP == 64) {
         return this.lm ? this.getOperandValue() + (var1 & -4096L) : var1 + this.getOperandValue();
      } else {
         int var3 = this.q(var1) + q(this.gP) + (int)this.getOperandValue();
         return var3 & 4294967295L;
      }
   }

   private int q(long var1) {
      return this.za ? k.q((int)var1, 4) : (int)var1;
   }

   public static int q(int var0) {
      switch (var0) {
         case 16:
            return 4;
         case 64:
            return 0;
         default:
            return 8;
      }
   }

   public boolean q() {
      return this.lm;
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      var1 = 31 * var1 + (this.lm ? 1231 : 1237);
      var1 = 31 * var1 + (this.za ? 1231 : 1237);
      return 31 * var1 + this.gP;
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (!super.equals(var1)) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         fp var2 = (fp)var1;
         if (this.lm != var2.lm) {
            return false;
         } else {
            return this.za != var2.za ? false : this.gP == var2.gP;
         }
      }
   }
}
