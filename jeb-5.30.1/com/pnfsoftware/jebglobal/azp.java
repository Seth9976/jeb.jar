package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.sig.NativeSignatureScopeException;
import java.util.List;

public abstract class azp {
   public void q(axp var1) throws NativeSignatureScopeException {
      throw new NativeSignatureScopeException("This attribute cannot be extractred from a routine");
   }

   public void q(BasicBlock var1) throws NativeSignatureScopeException {
      throw new NativeSignatureScopeException("This attribute cannot be extractred from a basic block");
   }

   public void q(IInstruction var1) throws NativeSignatureScopeException {
      throw new NativeSignatureScopeException("This attribute cannot be extractred from an instruction");
   }

   public void q(byte[] var1) throws NativeSignatureScopeException {
      throw new NativeSignatureScopeException("This attribute cannot be extractred from data");
   }

   public abstract List q();

   public abstract void RF();
}
