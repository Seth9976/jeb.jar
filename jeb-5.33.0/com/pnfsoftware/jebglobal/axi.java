package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IBasicBlock;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.sig.NativeSignatureScopeException;
import java.util.List;

public abstract class axi {
   public void pC(INativeMethodItem var1) throws NativeSignatureScopeException {
      throw new NativeSignatureScopeException("This feature cannot be computed on a routine");
   }

   public void pC(IBasicBlock var1) throws NativeSignatureScopeException {
      throw new NativeSignatureScopeException("This feature cannot be computed on a basic block");
   }

   public void pC(IInstruction var1) throws NativeSignatureScopeException {
      throw new NativeSignatureScopeException("This feature cannot be computed on an instruction");
   }

   public abstract List pC();

   public abstract void A();
}
