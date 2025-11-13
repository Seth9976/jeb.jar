package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COutputSink;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstantFloat64;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class afr extends afo implements ICConstantFloat64 {
   afr(double var1) {
      super(null, var1);
   }

   @Override
   public int getBitsize() {
      return 64;
   }

   @Override
   public double getFP64Value() {
      return (Double)this.RF;
   }

   @Override
   public boolean isTrueLike() {
      return (Double)this.RF == 0.0;
   }

   @Override
   protected void xK(COutputSink var1) {
      String var2 = Double.toString((Double)this.RF);
      long var3 = 0L;
      ItemClassIdentifiers var5 = ItemClassIdentifiers.NUMBER;
      this.q(var1, var2, var5, var3);
   }
}
