package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.util.format.Strings;

public class aot {
   public CFG q;
   public BasicBlock RF;
   public EState xK;
   public EState Dw;

   public aot(CFG var1, BasicBlock var2) {
      this.q = var1;
      this.RF = var2;
   }

   public CFG q() {
      return this.q;
   }

   public BasicBlock RF() {
      return this.RF;
   }

   public EState xK() {
      return this.xK;
   }

   public EState Dw() {
      return this.Dw;
   }

   @Override
   public String toString() {
      return Strings.ff("PointerTracker{b=%Xh,in=%s,out=%s}", this.RF.getFirstAddress(), this.xK, this.Dw);
   }
}
