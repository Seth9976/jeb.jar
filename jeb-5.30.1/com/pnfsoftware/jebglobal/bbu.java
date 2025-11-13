package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureTypeField;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;

@Ser
public class bbu implements IStructureTypeField {
   @SerId(1)
   String q;
   @SerId(2)
   bbd RF;
   @SerId(3)
   int xK;
   @SerId(4)
   private int Uv;
   @SerId(5)
   private int oW;
   @SerId(6)
   private int gO;
   @SerTransient
   int Dw;

   bbu(String var1, bbd var2, int var3, int var4, int var5, int var6, int var7) {
      if (var2 != null && var1 != null) {
         this.RF = var2;
         this.xK = var3;
         this.q = var1;
         if (var5 != 0) {
            int var8 = var2.getSize() * 8;
            if (var4 < 0 || var5 < 0 || var4 >= var8 || var4 + var5 > var8) {
               throw new IllegalArgumentException(Strings.ff("Illegal bitfield specifications: bitstart=%d bitsize=%d", var4, var5));
            }

            this.Uv = var4 | var5 << 16;
         }

         this.oW = var6;
         this.gO = var7;
      } else {
         throw new NullPointerException();
      }
   }

   bbu(String var1, int var2, int var3) {
      if (var2 <= 0) {
         throw new IllegalArgumentException();
      } else {
         this.Dw = var2;
         this.xK = var3;
         this.q = var1;
      }
   }

   @Override
   public boolean isSynthetic() {
      return this.RF == null;
   }

   @Override
   public boolean isAnonymous() {
      return this.q != null && this.q.startsWith("__ANONYMOUS__") ? true : (this.gO & 2097152) != 0;
   }

   @Override
   public boolean isBitfield() {
      return this.Uv != 0;
   }

   @Override
   public int getBitstart() {
      return this.Uv == 0 ? 0 : this.Uv & 65535;
   }

   @Override
   public int getBitsize() {
      return this.Uv == 0 ? 0 : this.Uv >>> 16;
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
      String var2 = this.q;
      if (var1 && this.isBitfield()) {
         var2 = var2 + ":" + this.getBitsize();
      }

      return var2;
   }

   public bbd q() {
      return this.RF;
   }

   @Override
   public int getOffset() {
      return this.xK;
   }

   @Override
   public int getSize() {
      return this.isSynthetic() ? this.Dw : this.RF.getSize();
   }

   @Override
   public int getEndOffset() {
      return this.xK + this.getSize();
   }

   @Override
   public int getAlignment() {
      return this.oW;
   }

   @Override
   public int getFlags() {
      return this.gO;
   }

   public void q(int var1) {
      this.gO = var1;
   }

   @Override
   public String toString() {
      String var1 = Strings.ff("%s{%s}@0x%X", this.getName(), this.q(), this.getOffset());
      if (this.isBitfield()) {
         var1 = var1 + Strings.ff("[%d:%d[", this.getBitstart(), this.getBitend());
      }

      if (this.getAlignment() > 0) {
         var1 = var1 + Strings.ff("(align:%d)", this.getAlignment());
      }

      return var1;
   }
}
