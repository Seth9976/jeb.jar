package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.controlflow.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDInstruction;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;

@SerDisabled
public class bvl extends Couple {
   public bvl() {
      this(null, -1);
   }

   public bvl(BasicBlock var1, Integer var2) {
      super(var1, var2);
   }

   public BasicBlock q() {
      return (BasicBlock)this.getFirst();
   }

   public int RF() {
      return (Integer)this.getSecond();
   }

   public void q(int var1) {
      this.setSecond((Integer)this.getSecond() + var1);
   }

   public IDInstruction xK() {
      return (IDInstruction)((BasicBlock)this.getFirst()).get((Integer)this.getSecond());
   }
}
