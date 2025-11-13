package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class cv extends Yg {
   private static final ILogger A = GlobalLog.getLogger(cv.class);
   @SerId(1)
   private int kS;
   @SerId(2)
   private boolean wS;
   @SerId(3)
   private boolean UT;

   public cv(long var1, int var3, boolean var4, boolean var5) {
      super(var5 ? 1 : 3, var3 == 64 ? 64 : 32, var1, var5 ? 65536 : 0);
      this.kS = var3;
      this.wS = var4;
      this.UT = var5;
   }

   @Override
   public long getOperandValue(long var1) {
      if (this.kS == 64) {
         return this.UT ? this.getOperandValue() + (var1 & -4096L) : var1 + this.getOperandValue();
      } else {
         int var3 = this.pC(var1) + pC(this.kS) + (int)this.getOperandValue();
         return var3 & 4294967295L;
      }
   }

   private int pC(long var1) {
      return this.wS ? Gq.pC((int)var1, 4) : (int)var1;
   }

   public static int pC(int var0) {
      switch (var0) {
         case 16:
            return 4;
         case 64:
            return 0;
         default:
            return 8;
      }
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      var1 = 31 * var1 + (this.UT ? 1231 : 1237);
      var1 = 31 * var1 + (this.wS ? 1231 : 1237);
      return 31 * var1 + this.kS;
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
         cv var2 = (cv)var1;
         if (this.UT != var2.UT) {
            return false;
         } else {
            return this.wS != var2.wS ? false : this.kS == var2.kS;
         }
      }
   }
}
