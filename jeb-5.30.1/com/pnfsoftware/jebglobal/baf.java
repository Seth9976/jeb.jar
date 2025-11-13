package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IBasicBlock;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.sig.NativeSignatureScopeException;
import java.util.List;

public abstract class baf {
   public void q(INativeMethodItem var1) throws NativeSignatureScopeException {
      throw new NativeSignatureScopeException("This feature cannot be computed on a routine");
   }

   public void q(IBasicBlock var1) throws NativeSignatureScopeException {
      throw new NativeSignatureScopeException("This feature cannot be computed on a basic block");
   }

   public void q(IInstruction var1) throws NativeSignatureScopeException {
      throw new NativeSignatureScopeException("This feature cannot be computed on an instruction");
   }

   public void q(byte[] var1) throws NativeSignatureScopeException {
      throw new NativeSignatureScopeException("This feature cannot be computed on data");
   }

   public abstract List q();

   public abstract void RF();
}
