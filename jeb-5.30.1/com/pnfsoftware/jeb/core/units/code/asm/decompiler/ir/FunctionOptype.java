package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class FunctionOptype {
   public static final int FLAG_OPND_HAVE_SAME_BITSIZE = 1;
   public static final int FLAG_PASSTHRU_FOR_ECALL_DFA_CALCULATION = 16;
   @SerId(1)
   String name;
   @SerId(2)
   int flags;
   @SerId(3)
   int minOpndCount;
   @SerId(4)
   int maxOpndCount;
   @SerId(5)
   int resultBitsize;

   FunctionOptype(String var1, int var2, int var3, int var4, int var5) {
      if (Strings.isBlank(var1)) {
         throw new IllegalArgumentException();
      } else if (var3 < 0 || var4 < var3) {
         throw new IllegalArgumentException();
      } else if ((var2 & 1) != 0 && var4 <= 1) {
         throw new IllegalArgumentException();
      } else if (var5 < 0) {
         throw new IllegalArgumentException();
      } else if (var5 == 0 && var3 == 0) {
         throw new IllegalArgumentException();
      } else {
         this.name = var1;
         this.flags = var2;
         this.minOpndCount = var3;
         this.maxOpndCount = var4;
         this.resultBitsize = var5;
      }
   }

   public String getName() {
      return this.name;
   }

   public int getFlags() {
      return this.flags;
   }

   public boolean hasFlags(int var1) {
      return (this.flags & var1) == var1;
   }

   public int getMinOperandCount() {
      return this.minOpndCount;
   }

   public int getMaxOperandCount() {
      return this.maxOpndCount;
   }

   public int getResultBitsize(IEGeneric... var1) {
      if (this.resultBitsize > 0) {
         return this.resultBitsize;
      } else if (var1.length >= 1) {
         return var1[0].getBitsize();
      } else {
         throw new IllegalArgumentException();
      }
   }

   public void validateOperands(IEGeneric... var1) {
      int var2 = var1.length;
      if (var2 >= this.minOpndCount && var2 <= this.maxOpndCount) {
         for (IEGeneric var6 : var1) {
            if (var6 == null) {
               throw new IllegalArgumentException();
            }
         }

         if (var2 >= 2 && (this.flags & 1) != 0) {
            int var7 = var1[0].getBitsize();

            for (int var8 = 1; var8 < var1.length; var8++) {
               if (var1[var8].getBitsize() != var7) {
                  throw new IllegalArgumentException();
               }
            }
         }
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public String toString() {
      return this.name;
   }
}
