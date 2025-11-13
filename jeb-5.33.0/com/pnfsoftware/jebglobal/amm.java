package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.util.format.Strings;

public class amm {
   public CFG pC;
   public BasicBlock A;
   public EState kS;
   public EState wS;

   public amm(CFG var1, BasicBlock var2) {
      this.pC = var1;
      this.A = var2;
   }

   @Override
   public String toString() {
      return Strings.ff("PointerTracker{b=%Xh,in=%s,out=%s}", this.A.getFirstAddress(), this.kS, this.wS);
   }
}
