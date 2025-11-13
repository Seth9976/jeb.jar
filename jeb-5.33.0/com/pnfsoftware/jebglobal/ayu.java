package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureTypeField;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;

@Ser
public class ayu implements IStructureTypeField {
   @SerId(1)
   String pC;
   @SerId(2)
   aye A;
   @SerId(3)
   int kS;
   @SerId(4)
   private int UT;
   @SerId(5)
   private int E;
   @SerId(6)
   private int sY;
   @SerTransient
   int wS;

   ayu(String var1, aye var2, int var3, int var4, int var5, int var6, int var7) {
      if (var2 != null && var1 != null) {
         this.A = var2;
         this.kS = var3;
         this.pC = var1;
         if (var5 != 0) {
            int var8 = var2.getSize() * 8;
            if (var4 < 0 || var5 < 0 || var4 >= var8 || var4 + var5 > var8) {
               throw new IllegalArgumentException(Strings.ff("Illegal bitfield specifications: bitstart=%d bitsize=%d", var4, var5));
            }

            this.UT = var4 | var5 << 16;
         }

         this.E = var6;
         this.sY = var7;
      } else {
         throw new NullPointerException();
      }
   }

   ayu(String var1, int var2, int var3) {
      if (var2 <= 0) {
         throw new IllegalArgumentException();
      } else {
         this.wS = var2;
         this.kS = var3;
         this.pC = var1;
      }
   }

   @Override
   public boolean isSynthetic() {
      return this.A == null;
   }

   @Override
   public boolean isAnonymous() {
      return this.pC != null && this.pC.startsWith("__ANONYMOUS__") ? true : (this.sY & 2097152) != 0;
   }

   @Override
   public boolean isBitfield() {
      return this.UT != 0;
   }

   @Override
   public int getBitstart() {
      return this.UT == 0 ? 0 : this.UT & 65535;
   }

   @Override
   public int getBitsize() {
      return this.UT == 0 ? 0 : this.UT >>> 16;
   }

   @Override
   public int getBitend() {
      return this.getBitstart() + this.getBitsize();
   }

   @Override
   public String getName() {
      return this.getName(false);
   }

   @Override
   public String getName(boolean var1) {
      String var2 = this.pC;
      if (var1 && this.isBitfield()) {
         var2 = var2 + ":" + this.getBitsize();
      }

      return var2;
   }

   public aye pC() {
      return this.A;
   }

   @Override
   public int getOffset() {
      return this.kS;
   }

   @Override
   public int getSize() {
      return this.isSynthetic() ? this.wS : this.A.getSize();
   }

   @Override
   public int getEndOffset() {
      return this.kS + this.getSize();
   }

   @Override
   public int getAlignment() {
      return this.E;
   }

   @Override
   public int getFlags() {
      return this.sY;
   }

   public void pC(int var1) {
      this.sY = var1;
   }

   @Override
   public String toString() {
      String var1 = Strings.ff("%s{%s}@0x%X", this.getName(), this.pC(), this.getOffset());
      if (this.isBitfield()) {
         var1 = var1 + Strings.ff("[%d:%d[", this.getBitstart(), this.getBitend());
      }

      if (this.getAlignment() > 0) {
         var1 = var1 + Strings.ff("(align:%d)", this.getAlignment());
      }

      return var1;
   }
}
