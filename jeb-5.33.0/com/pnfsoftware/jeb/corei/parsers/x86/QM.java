package com.pnfsoftware.jeb.corei.parsers.x86;

import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IInstructionOperandCMA;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public final class QM implements IInstructionOperandCMA, Av {
   @SerId(1)
   int pC;
   @SerId(2)
   long A = -1L;
   @SerId(3)
   int kS;
   @SerId(4)
   long wS = -1L;
   @SerId(5)
   long UT;

   public QM(int var1, long var2, int var4, long var5, long var7) {
      this.pC = var1;
      this.A = var2;
      this.kS = var4;
      this.wS = var5;
      this.UT = var7;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (int)(this.A ^ this.A >>> 32);
      var1 = 31 * var1 + (int)(this.UT ^ this.UT >>> 32);
      var1 = 31 * var1 + (int)(this.wS ^ this.wS >>> 32);
      var1 = 31 * var1 + this.kS;
      return 31 * var1 + this.pC;
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
         QM var2 = (QM)var1;
         if (this.A != var2.A) {
            return false;
         } else if (this.UT != var2.UT) {
            return false;
         } else if (this.wS != var2.wS) {
            return false;
         } else {
            return this.kS != var2.kS ? false : this.pC == var2.pC;
         }
      }
   }

   @Override
   public int getOperandType() {
      return 8;
   }

   @Override
   public int getOperandBitsize() {
      return this.pC;
   }

   @Override
   public long getOperandValue(long var1) {
      return 0L;
   }

   @Override
   public long getMemoryBaseRegister() {
      return this.A;
   }

   @Override
   public int getMemoryScale() {
      return this.kS;
   }

   @Override
   public long getMemoryIndexRegister() {
      return this.wS;
   }

   @Override
   public long getMemoryDisplacement() {
      return this.UT;
   }

   @Override
   public String format(IInstruction var1, long var2) {
      StringBuilder var4 = new StringBuilder();
      var4.append("[");
      if (var2 != 0L && this.wS < 0L && this.A >= 0L && MG.A(this.A) == 10) {
         long var5 = this.UT;
         int var7 = MG.kS(this.A);
         if (var7 == 32) {
            var5 = var5 + var2 + var1.getSize() & 4294967295L;
         } else if (var7 == 64) {
            var5 = var5 + var2 + var1.getSize();
         }

         Strings.ff(var4, "%Xh", var5);
      } else {
         if (this.A >= 0L) {
            var4.append(this.getRegisterName(this.A));
         }

         if (this.wS >= 0L) {
            if (this.A >= 0L) {
               var4.append("+");
            }

            if (this.kS >= 2) {
               var4.append(this.kS);
               var4.append("*");
            }

            var4.append(this.getRegisterName(this.wS));
         }

         if (this.UT != 0L) {
            if (this.A >= 0L || this.wS >= 0L) {
               var4.append("+");
            }

            Strings.ff(var4, "%Xh", this.UT);
         } else if (this.A < 0L && this.wS < 0L) {
            var4.append("0");
         }
      }

      var4.append("]");
      return MG.pC((vh)var1, this, var4.toString()).toString();
   }

   @Override
   public String toString() {
      return this.format(null, 0L);
   }

   @Override
   public boolean pC() {
      return true;
   }

   @Override
   public boolean A() {
      return true;
   }

   @Override
   public boolean isRegister() {
      return false;
   }

   @Override
   public boolean isImmediate() {
      return false;
   }

   public long pC(vh var1) {
      return MG.pC(var1, this);
   }

   @Override
   public String getRegisterName(long var1) {
      return MG.UT(var1);
   }

   @Override
   public long getOperandValue() {
      return this.getOperandValue(0L);
   }

   @Override
   public String getPrefix(IInstruction var1) {
      return null;
   }

   @Override
   public String getSuffix(IInstruction var1) {
      return null;
   }

   @Override
   public String getAlias(long var1) {
      return null;
   }
}
