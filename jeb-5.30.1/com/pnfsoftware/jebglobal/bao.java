package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IBasicBlock;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IInstructionOperandGeneric;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class bao extends baf {
   private static final ILogger q = GlobalLog.getLogger(bao.class);
   private final int RF;
   private List xK;
   private Map Dw;

   public bao(int var1) {
      this.RF = var1;
      this.xK = new ArrayList();
      this.Dw = new HashMap();
   }

   @Override
   public void q(INativeMethodItem var1) {
      for (IBasicBlock var3 : var1.getData().getCFG().getBlocks()) {
         this.q(var3);
      }
   }

   @Override
   public void q(IBasicBlock var1) {
      for (IInstruction var3 : var1) {
         this.q(var3);
      }
   }

   @Override
   public void q(IInstruction var1) {
      for (IInstructionOperand var5 : var1.getOperands()) {
         if (var5 instanceof IInstructionOperandGeneric var6 && var6.getOperandType() == 1 && var6.getOperandBitsize() == this.RF) {
            switch (this.RF) {
               case 8:
                  long var7 = ((IInstructionOperandGeneric)var5).getOperandValue();
                  ban var9 = (ban)this.Dw.get(var7);
                  int var10 = 1;
                  if (var9 != null) {
                     var10 = var9.q() + 1;
                     this.Dw.remove(var7);
                     this.xK.remove(var9);
                  }

                  var9 = new ban((byte)var7, var10);
                  this.Dw.put(var7, var9);
                  this.xK.add(var9);
            }
         }
      }
   }

   @Override
   public List q() {
      return this.xK;
   }

   @Override
   public void RF() {
      this.xK.clear();
      this.Dw.clear();
   }
}
