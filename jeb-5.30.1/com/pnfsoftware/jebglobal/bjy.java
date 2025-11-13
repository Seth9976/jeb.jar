package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethodData;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class bjy implements IDexMethodData {
   @SerId(1)
   private int q;
   @SerId(2)
   private int RF;
   @SerId(3)
   private bip xK;
   @SerId(4)
   private int[] Dw;
   @SerId(5)
   private volatile int Uv;
   @SerId(6)
   private Integer oW;
   @SerId(7)
   private int gO;

   public bjy(int var1, int var2, bip var3, int var4) {
      this.q = var1;
      this.RF = var2;
      this.xK = var3;
      this.oW = var4;
   }

   @Override
   public int getDexEntryIndex() {
      return this.oW == null ? -1 : this.oW;
   }

   public void q(int[] var1) {
      this.Dw = var1;
   }

   @Override
   public int[] getExceptionTypeIndices() {
      return this.Dw;
   }

   @Override
   public int getMethodIndex() {
      return this.q;
   }

   @Override
   public int getAccessFlags() {
      return this.RF;
   }

   public bip q() {
      return this.xK;
   }

   public bip RF() {
      return this.q();
   }

   @Override
   public void setInlineMode(int var1) {
      if (var1 >= 0 && var1 <= 3) {
         this.gO = this.gO & -4 | var1;
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public int getInlineMode() {
      return this.gO & 3;
   }

   @Override
   public boolean isConstructor() {
      return (this.RF & 65536) != 0;
   }

   @Override
   public boolean isStatic() {
      return (this.RF & 8) != 0;
   }

   @Override
   public boolean isAbstract() {
      return (this.RF & 1024) != 0;
   }

   @Override
   public boolean isNative() {
      return (this.RF & 256) != 0;
   }

   @Override
   public boolean isSynthetic() {
      return (this.RF & 4096) != 0;
   }

   @Override
   public boolean isPublic() {
      return (this.RF & 1) != 0;
   }

   @Override
   public boolean isProtected() {
      return (this.RF & 4) != 0;
   }

   @Override
   public boolean isPrivate() {
      return (this.RF & 2) != 0;
   }

   @Override
   public boolean isFinal() {
      return (this.RF & 16) != 0;
   }

   @Override
   public int getUserFlags() {
      return this.Uv;
   }

   @Override
   public void setUserFlags(int var1) {
      this.Uv = var1;
   }

   @Override
   public String toString() {
      return Strings.ff("idx=%d", this.q);
   }
}
