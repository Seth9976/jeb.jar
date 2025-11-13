package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COutputSink;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstantFloat32;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class adx extends adv implements ICConstantFloat32 {
   adx(float var1) {
      super(null, var1);
   }

   @Override
   public int getBitsize() {
      return 32;
   }

   @Override
   public float getFP32Value() {
      return (Float)this.A;
   }

   @Override
   public boolean isTrueLike() {
      return (Float)this.A == 0.0F;
   }

   @Override
   protected void kS(COutputSink var1) {
      String var2 = Float.toString((Float)this.A);
      long var3 = 0L;
      ItemClassIdentifiers var5 = ItemClassIdentifiers.NUMBER;
      this.pC(var1, var2, var5, var3);
   }
}
