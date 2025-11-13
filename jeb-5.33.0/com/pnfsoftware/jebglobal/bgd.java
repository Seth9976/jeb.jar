package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethodData;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class bgd implements IDexMethodData {
   @SerId(1)
   private int pC;
   @SerId(2)
   private int A;
   @SerId(3)
   private bev kS;
   @SerId(4)
   private int[] wS;
   @SerId(5)
   private volatile int UT;
   @SerId(6)
   private Integer E;
   @SerId(7)
   private int sY;

   public bgd(int var1, int var2, bev var3, int var4) {
      this.pC = var1;
      this.A = var2;
      this.kS = var3;
      this.E = var4;
   }

   @Override
   public int getDexEntryIndex() {
      return this.E == null ? -1 : this.E;
   }

   public void pC(int[] var1) {
      this.wS = var1;
   }

   @Override
   public int[] getExceptionTypeIndices() {
      return this.wS;
   }

   @Override
   public int getMethodIndex() {
      return this.pC;
   }

   @Override
   public int getAccessFlags() {
      return this.A;
   }

   public bev pC() {
      return this.kS;
   }

   public bev A() {
      return this.pC();
   }

   @Override
   public void setInlineMode(int var1) {
      if (var1 >= 0 && var1 <= 3) {
         this.sY = this.sY & -4 | var1;
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public int getInlineMode() {
      return this.sY & 3;
   }

   @Override
   public boolean isConstructor() {
      return (this.A & 65536) != 0;
   }

   @Override
   public boolean isStatic() {
      return (this.A & 8) != 0;
   }

   @Override
   public boolean isAbstract() {
      return (this.A & 1024) != 0;
   }

   @Override
   public boolean isNative() {
      return (this.A & 256) != 0;
   }

   @Override
   public boolean isSynthetic() {
      return (this.A & 4096) != 0;
   }

   @Override
   public boolean isPublic() {
      return (this.A & 1) != 0;
   }

   @Override
   public boolean isProtected() {
      return (this.A & 4) != 0;
   }

   @Override
   public boolean isPrivate() {
      return (this.A & 2) != 0;
   }

   @Override
   public boolean isFinal() {
      return (this.A & 16) != 0;
   }

   @Override
   public int getUserFlags() {
      return this.UT;
   }

   @Override
   public void setUserFlags(int var1) {
      this.UT = var1;
   }

   @Override
   public String toString() {
      return Strings.ff("idx=%d", this.pC);
   }
}
