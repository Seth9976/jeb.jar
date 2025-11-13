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

public class bak extends baf {
   private static final ILogger q = GlobalLog.getLogger(bak.class);
   private static final int RF = 64;
   private int xK;
   private byte[] Dw;
   private final IProcessor Uv;
   private boolean oW = false;
   private static final byte gO = -1;
   private static final byte nf = 64;
   private static final byte gP = 65;
   private static final byte za = 66;
   private static final byte lm = 67;
   private static final byte zz = 68;
   private static final byte JY = 69;
   private static final byte HF = 70;

   public bak(IProcessor var1) {
      this.xK = 0;
      this.Dw = new byte[64];
      this.Uv = var1;
   }

   @Override
   public List q() {
      ArrayList var1 = new ArrayList();
      var1.add(new baj(this.Dw));
      return var1;
   }

   @Override
   public void RF() {
      this.xK = 0;
      this.Dw = new byte[64];
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
      int var2 = 0;
      if (var1.getPrefix() != null) {
         var2 = var1.getPrefix().hashCode();
      }

      if (this.Uv instanceof hB) {
         var2 += this.q(((fA)var1).Dw().q()).hashCode();
      } else {
         var2 += var1.getMnemonic().hashCode();
      }

      for (IInstructionOperand var6 : var1.getOperands()) {
         if (var6 instanceof IInstructionOperandGeneric) {
            switch (((IInstructionOperandGeneric)var6).getOperandType()) {
               case 7:
                  for (IInstructionOperand var10 : ((IInstructionOperandList)var6).getOperands()) {
                     byte var11 = this.q(var10, true);
                     if (var11 != -1) {
                        var2 = var2 * 31 + var11;
                     }
                  }
                  break;
               default:
                  byte var7 = this.q(var6, true);
                  if (var7 != -1) {
                     var2 = var2 * 31 + this.q(var6, false);
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
         this.Dw[this.xK] = (byte)(this.Dw[this.xK] + var19);
         this.xK = (this.xK + 1) % 64;
      }
   }

   private String q(String var1) {
      return var1.startsWith("BL") ? "BL" : var1;
   }

   private byte q(IInstructionOperand var1, boolean var2) {
      byte var3 = -1;
      if (var1 instanceof IInstructionOperandGeneric) {
         switch (((IInstructionOperandGeneric)var1).getOperandType()) {
            case 0:
            case 4:
               var3 = (byte)(((IInstructionOperandGeneric)var1).getOperandValue() % 64L);
               break;
            case 1:
               if (this.Uv instanceof hB) {
                  if (var1 instanceof fp) {
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
               if (this.Uv instanceof clc && ((IInstructionOperandList)var1).getOperands().length == 1) {
                  return this.q(((IInstructionOperandList)var1).getOperands()[0], true);
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
