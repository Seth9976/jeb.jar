package com.pnfsoftware.jeb.corei.parsers.wasm;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EPrototypeHandler;
import java.util.List;

public class Sb extends EPrototypeHandler {
   public Sb(IERoutineContext var1) {
      super(var1);
   }

   @Override
   public boolean applyKnownPrototype(boolean var1) {
      return true;
   }

   @Override
   public boolean retrieveFromPrototype(List var1, List var2) {
      io var3 = (io)this.ctx.getConverter();
      var1.addAll(var3.pC(this.ctx));
      var2.add(var3.A(this.ctx));
      return true;
   }

   @Override
   public boolean refinePrototype() {
      return false;
   }
}
