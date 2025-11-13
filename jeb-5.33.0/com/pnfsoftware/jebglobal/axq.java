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

public class axq extends axi {
   private static final ILogger pC = GlobalLog.getLogger(axq.class);
   private final int A;
   private List kS;
   private Map wS;

   public axq(int var1) {
      this.A = var1;
      this.kS = new ArrayList();
      this.wS = new HashMap();
   }

   @Override
   public void pC(INativeMethodItem var1) {
      for (IBasicBlock var3 : var1.getData().getCFG().getBlocks()) {
         this.pC(var3);
      }
   }

   @Override
   public void pC(IBasicBlock var1) {
      for (IInstruction var3 : var1) {
         this.pC(var3);
      }
   }

   @Override
   public void pC(IInstruction var1) {
      for (IInstructionOperand var5 : var1.getOperands()) {
         if (var5 instanceof IInstructionOperandGeneric var6 && var6.getOperandType() == 1 && var6.getOperandBitsize() == this.A) {
            switch (this.A) {
               case 8:
                  long var7 = ((IInstructionOperandGeneric)var5).getOperandValue();
                  axp var9 = (axp)this.wS.get(var7);
                  int var10 = 1;
                  if (var9 != null) {
                     var10 = var9.pC() + 1;
                     this.wS.remove(var7);
                     this.kS.remove(var9);
                  }

                  var9 = new axp((byte)var7, var10);
                  this.wS.put(var7, var9);
                  this.kS.add(var9);
            }
         }
      }
   }

   @Override
   public List pC() {
      return this.kS;
   }

   @Override
   public void A() {
      this.kS.clear();
      this.wS.clear();
   }
}
