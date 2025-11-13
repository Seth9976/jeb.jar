package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.type.IPrimitiveType;
import com.pnfsoftware.jeb.core.units.code.asm.type.PrimitiveCategory;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class ayq extends aye implements IPrimitiveType {
   @SerId(1)
   int pC;
   @SerId(2)
   PrimitiveCategory A;

   ayq(ayy var1, String var2, int var3, PrimitiveCategory var4) {
      super(var1, var2);
      if (var3 < 0) {
         throw new IllegalArgumentException();
      } else {
         this.pC = var3;
         if (var4 == null) {
            throw new IllegalArgumentException();
         } else {
            this.A = var4;
         }
      }
   }

   @Override
   public PrimitiveCategory getCategory() {
      return this.A;
   }

   @Override
   public String pC(boolean var1, String var2) {
      if (var2 != null && var2.length() != 0) {
         char var3 = var2.charAt(0);
         return var3 != '*' && var3 != '[' ? this.getName(var1) + " " + var2 : this.getName(var1) + var2;
      } else {
         return this.getName(var1);
      }
   }

   @Override
   public int getSize() {
      return this.pC;
   }

   @Override
   protected boolean ED() {
      Assert.debugFail("Attempt to dispose a primitive type");
      return false;
   }

   @Override
   public String toString() {
      return Strings.ff("PrimType(%s)", this.getName());
   }
}
