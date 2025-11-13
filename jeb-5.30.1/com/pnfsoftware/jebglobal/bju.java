package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.dex.IDexFieldData;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class bju implements IDexFieldData {
   @SerId(1)
   private int q;
   @SerId(2)
   private int RF;
   @SerId(3)
   private volatile int xK;
   @SerId(4)
   private Integer Dw;

   public bju(int var1, int var2, int var3) {
      this.q = var1;
      this.RF = var2;
      this.Dw = var3;
   }

   @Override
   public int getDexEntryIndex() {
      return this.Dw == null ? -1 : this.Dw;
   }

   @Override
   public int getFieldIndex() {
      return this.q;
   }

   @Override
   public int getAccessFlags() {
      return this.RF;
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
   public boolean isStatic() {
      return (this.RF & 8) != 0;
   }

   @Override
   public boolean isFinal() {
      return (this.RF & 16) != 0;
   }

   @Override
   public boolean isSynthetic() {
      return (this.RF & 4096) != 0;
   }

   @Override
   public boolean isVolatile() {
      return (this.RF & 64) != 0;
   }

   @Override
   public int getUserFlags() {
      return this.xK;
   }

   @Override
   public void setUserFlags(int var1) {
      this.xK = var1;
   }

   @Override
   public String toString() {
      return Strings.ff("idx=%d", this.q);
   }
}
