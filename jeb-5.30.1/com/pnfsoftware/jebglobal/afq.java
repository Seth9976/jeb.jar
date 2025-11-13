package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COutputSink;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstantFloat32;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class afq extends afo implements ICConstantFloat32 {
   afq(float var1) {
      super(null, var1);
   }

   @Override
   public int getBitsize() {
      return 32;
   }

   @Override
   public float getFP32Value() {
      return (Float)this.RF;
   }

   @Override
   public boolean isTrueLike() {
      return (Float)this.RF == 0.0F;
   }

   @Override
   protected void xK(COutputSink var1) {
      String var2 = Float.toString((Float)this.RF);
      long var3 = 0L;
      ItemClassIdentifiers var5 = ItemClassIdentifiers.NUMBER;
      this.q(var1, var2, var5, var3);
   }
}
