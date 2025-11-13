package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;

@SerDisabled
public class brd extends Couple {
   public brd() {
      this(null, -1);
   }

   public brd(BasicBlock var1, Integer var2) {
      super(var1, var2);
   }

   public BasicBlock pC() {
      return (BasicBlock)this.getFirst();
   }

   public int A() {
      return (Integer)this.getSecond();
   }

   public IDInstruction kS() {
      return (IDInstruction)((BasicBlock)this.getFirst()).get((Integer)this.getSecond());
   }
}
