package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.dex.IDexFieldData;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class bfz implements IDexFieldData {
   @SerId(1)
   private int pC;
   @SerId(2)
   private int A;
   @SerId(3)
   private volatile int kS;
   @SerId(4)
   private Integer wS;

   public bfz(int var1, int var2, int var3) {
      this.pC = var1;
      this.A = var2;
      this.wS = var3;
   }

   @Override
   public int getDexEntryIndex() {
      return this.wS == null ? -1 : this.wS;
   }

   @Override
   public int getFieldIndex() {
      return this.pC;
   }

   @Override
   public int getAccessFlags() {
      return this.A;
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
   public boolean isStatic() {
      return (this.A & 8) != 0;
   }

   @Override
   public boolean isFinal() {
      return (this.A & 16) != 0;
   }

   @Override
   public boolean isSynthetic() {
      return (this.A & 4096) != 0;
   }

   @Override
   public boolean isVolatile() {
      return (this.A & 64) != 0;
   }

   @Override
   public int getUserFlags() {
      return this.kS;
   }

   @Override
   public void setUserFlags(int var1) {
      this.kS = var1;
   }

   @Override
   public String toString() {
      return Strings.ff("idx=%d", this.pC);
   }
}
