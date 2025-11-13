package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IBasicBlock;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IInstructionOperandCMA;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IInstructionOperandGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IInstructionOperandList;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IProcessor;
import com.pnfsoftware.jeb.util.io.EndianUtil;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.List;

public class axn extends axi {
   private static final ILogger pC = GlobalLog.getLogger(axn.class);
   private int A;
   private byte[] kS;
   private final IProcessor wS;
   private boolean UT = false;

   public axn(IProcessor var1) {
      this.A = 0;
      this.kS = new byte[64];
      this.wS = var1;
   }

   @Override
   public List pC() {
      ArrayList var1 = new ArrayList();
      var1.add(new axm(this.kS));
      return var1;
   }

   @Override
   public void A() {
      this.A = 0;
      this.kS = new byte[64];
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
      int var2 = 0;
      if (var1.getPrefix() != null) {
         var2 = var1.getPrefix().hashCode();
      }

      if (this.wS instanceof com.pnfsoftware.jeb.corei.parsers.arm.zp) {
         var2 += this.pC(((com.pnfsoftware.jeb.corei.parsers.arm.rQ)var1).wS().pC()).hashCode();
      } else {
         var2 += var1.getMnemonic().hashCode();
      }

      for (IInstructionOperand var6 : var1.getOperands()) {
         if (var6 instanceof IInstructionOperandGeneric) {
            switch (((IInstructionOperandGeneric)var6).getOperandType()) {
               case 7:
                  for (IInstructionOperand var10 : ((IInstructionOperandList)var6).getOperands()) {
                     byte var11 = this.pC(var10, true);
                     if (var11 != -1) {
                        var2 = var2 * 31 + var11;
                     }
                  }
                  break;
               default:
                  byte var7 = this.pC(var6, true);
                  if (var7 != -1) {
                     var2 = var2 * 31 + this.pC(var6, false);
                     var2 = var2 * 31 + ((IInstructionOperandGeneric)var6).getOperandBitsize();
                  }
            }
         }
      }

      byte[] var14 = EndianUtil.intToBEBytes(var2);
      byte[] var15 = var14;
      int var16 = var14.length;

      for (int var17 = 0; var17 < var16; var17++) {
         Byte var19 = var15[var17];
         this.kS[this.A] = (byte)(this.kS[this.A] + var19);
         this.A = (this.A + 1) % 64;
      }
   }

   private String pC(String var1) {
      return var1.startsWith("BL") ? "BL" : var1;
   }

   private byte pC(IInstructionOperand var1, boolean var2) {
      byte var3 = -1;
      if (var1 instanceof IInstructionOperandGeneric) {
         switch (((IInstructionOperandGeneric)var1).getOperandType()) {
            case 0:
            case 4:
               var3 = (byte)(((IInstructionOperandGeneric)var1).getOperandValue() % 64L);
               break;
            case 1:
               if (this.wS instanceof com.pnfsoftware.jeb.corei.parsers.arm.zp) {
                  if (var1 instanceof cv) {
                     var3 = 67;
                  } else if (!var2) {
                     var3 = 65;
                  }
               } else {
                  var3 = 65;
               }
               break;
            case 2:
               var3 = 66;
               break;
            case 3:
               var3 = 67;
               break;
            case 5:
               var3 = 68;
               break;
            case 6:
               var3 = 69;
               break;
            case 7:
               if (this.wS instanceof com.pnfsoftware.jeb.corei.parsers.mips.p && ((IInstructionOperandList)var1).getOperands().length == 1) {
                  return this.pC(((IInstructionOperandList)var1).getOperands()[0], true);
               }

               var3 = 70;
               break;
            case 8:
               var3 = 64;
               long var4 = ((IInstructionOperandCMA)var1).getMemoryIndexRegister();
               int var6 = ((IInstructionOperandCMA)var1).getMemoryScale();
               long var7 = ((IInstructionOperandCMA)var1).getMemoryBaseRegister();
               long var9 = ((IInstructionOperandCMA)var1).getMemoryDisplacement();
               byte var11 = var4 != -1L ? (byte)(var4 + 5L) : 0;
               var11 = var6 != 0 ? (byte)(var11 * var6) : var11;
               var11 = var7 != -1L ? (byte)(var11 + (var7 + 1L) * 2L + 1L) : var11;
               var11 = (byte)(var9 != 0L ? var11 + 27 : var11);
               var3 = (byte)(var3 + var11 % 192);
               break;
            default:
               var3 = (byte)((IInstructionOperandGeneric)var1).getOperandType();
         }
      }

      return var3;
   }
}
