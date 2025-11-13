package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.type.IPrimitiveType;
import com.pnfsoftware.jeb.core.units.code.asm.type.PrimitiveCategory;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public class bbq extends bbd implements IPrimitiveType {
   @SerId(1)
   int q;
   @SerId(2)
   PrimitiveCategory RF;

   bbq(bby var1, String var2, int var3, PrimitiveCategory var4) {
      super(var1, var2);
      if (var3 < 0) {
         throw new IllegalArgumentException();
      } else {
         this.q = var3;
         if (var4 == null) {
            throw new IllegalArgumentException();
         } else {
            this.RF = var4;
         }
      }
   }

   @Override
   public PrimitiveCategory getCategory() {
      return this.RF;
   }

   @Override
   public String q(boolean var1, String var2) {
      if (var2 != null && var2.length() != 0) {
         char var3 = var2.charAt(0);
         return var3 != '*' && var3 != '[' ? this.getName(var1) + " " + var2 : this.getName(var1) + var2;
      } else {
         return this.getName(var1);
      }
   }

   @Override
   public int getSize() {
      return this.q;
   }

   public int oW() {
      return this.RF == PrimitiveCategory.COMPLEX ? this.q / 2 : this.q;
   }

   @Override
   protected boolean oQ() {
      Assert.debugFail("Attempt to dispose a primitive type");
      return false;
   }

   @Override
   public String toString() {
      return Strings.ff("PrimType(%s)", this.getName());
   }
}
