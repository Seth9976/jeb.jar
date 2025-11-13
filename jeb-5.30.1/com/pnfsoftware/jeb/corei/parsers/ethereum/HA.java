package com.pnfsoftware.jeb.corei.parsers.ethereum;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EPrototypeHandler;
import java.util.List;

public class HA extends EPrototypeHandler {
   public HA(IERoutineContext var1) {
      super(var1);
   }

   @Override
   public boolean applyKnownPrototype(boolean var1) {
      return true;
   }

   @Override
   public boolean retrieveFromPrototype(List var1, List var2) {
      return super.retrieveFromPrototype(var1, var2);
   }

   @Override
   public boolean refinePrototype() {
      return false;
   }
}
