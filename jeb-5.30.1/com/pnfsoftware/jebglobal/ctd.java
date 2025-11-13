package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IInstructionOperandCMA;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public final class ctd implements IInstructionOperandCMA, cqj {
   @SerId(1)
   int q;
   @SerId(2)
   long RF = -1L;
   @SerId(3)
   int xK;
   @SerId(4)
   long Dw = -1L;
   @SerId(5)
   long Uv;

   public ctd(int var1, long var2, int var4, long var5, long var7) {
      this.q = var1;
      this.RF = var2;
      this.xK = var4;
      this.Dw = var5;
      this.Uv = var7;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (int)(this.RF ^ this.RF >>> 32);
      var1 = 31 * var1 + (int)(this.Uv ^ this.Uv >>> 32);
      var1 = 31 * var1 + (int)(this.Dw ^ this.Dw >>> 32);
      var1 = 31 * var1 + this.xK;
      return 31 * var1 + this.q;
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
         ctd var2 = (ctd)var1;
         if (this.RF != var2.RF) {
            return false;
         } else if (this.Uv != var2.Uv) {
            return false;
         } else if (this.Dw != var2.Dw) {
            return false;
         } else {
            return this.xK != var2.xK ? false : this.q == var2.q;
         }
      }
   }

   @Override
   public int getOperandType() {
      return 8;
   }

   @Override
   public int getOperandBitsize() {
      return this.q;
   }

   @Override
   public long getOperandValue(long var1) {
      return 0L;
   }

   @Override
   public long getMemoryBaseRegister() {
      return this.RF;
   }

   @Override
   public int getMemoryScale() {
      return this.xK;
   }

   @Override
   public long getMemoryIndexRegister() {
      return this.Dw;
   }

   @Override
   public long getMemoryDisplacement() {
      return this.Uv;
   }

   @Override
   public String format(IInstruction var1, long var2) {
      StringBuilder var4 = new StringBuilder();
      var4.append("[");
      if (var2 != 0L && this.Dw < 0L && this.RF >= 0L && ctf.RF(this.RF) == 10) {
         long var5 = this.Uv;
         int var7 = ctf.xK(this.RF);
         if (var7 == 32) {
            var5 = var5 + var2 + var1.getSize() & 4294967295L;
         } else if (var7 == 64) {
            var5 = var5 + var2 + var1.getSize();
         }

         Strings.ff(var4, "%Xh", var5);
      } else {
         if (this.RF >= 0L) {
            var4.append(this.getRegisterName(this.RF));
         }

         if (this.Dw >= 0L) {
            if (this.RF >= 0L) {
               var4.append("+");
            }

            if (this.xK >= 2) {
               var4.append(this.xK);
               var4.append("*");
            }

            var4.append(this.getRegisterName(this.Dw));
         }

         if (this.Uv != 0L) {
            if (this.RF >= 0L || this.Dw >= 0L) {
               var4.append("+");
            }

            Strings.ff(var4, "%Xh", this.Uv);
         } else if (this.RF < 0L && this.Dw < 0L) {
            var4.append("0");
         }
      }

      var4.append("]");
      return ctf.q((ctc)var1, this, var4.toString()).toString();
   }

   @Override
   public String toString() {
      return this.format(null, 0L);
   }

   @Override
   public boolean q() {
      return true;
   }

   @Override
   public boolean RF() {
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

   @Override
   public long q(ctc var1) {
      return ctf.q(var1, this);
   }

   @Override
   public String getRegisterName(long var1) {
      return ctf.Uv(var1);
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
