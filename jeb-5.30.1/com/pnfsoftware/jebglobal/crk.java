package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.Pointer;
import com.pnfsoftware.jeb.core.units.code.asm.ChainedOperationResult;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.AbstractAnalyzerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.BinaryPattern;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.BinaryPatternVerifier;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.IReferenceManager;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.MemoryRanges;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.ReferenceType;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeInstructionItem;
import com.pnfsoftware.jeb.core.units.code.asm.memory.VirtualMemoryUtil;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IInstructionOperandCMA;
import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterType;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterBankX64;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Ser
public class crk extends AbstractAnalyzerExtension {
   private static final ILogger RF = GlobalLog.getLogger(crk.class);
   private static Set xK = new HashSet();
   @SerTransient
   IReferenceManager q;

   @Override
   protected void initializePaddingPatterns(BinaryPatternVerifier var1) {
      var1.addPatterns(new BinaryPattern(new byte[]{-52}));
   }

   @Override
   public void initializeProloguePatterns(BinaryPatternVerifier var1) {
      var1.addPatterns(new BinaryPattern(new byte[]{85, -117, -20}), new BinaryPattern(new byte[]{85, -119, -27}), new ctg());
      if (this.gca.getProcessor().getMode() == 64) {
         var1.addPatterns(new BinaryPattern(new byte[]{85, 72, -119, -27}));
      }
   }

   @Override
   public ChainedOperationResult getTrampolineTarget(CFG var1) {
      if (var1.size() != 1) {
         return ChainedOperationResult.continue_();
      } else {
         BasicBlock var2 = var1.get(0);
         if (var2.size() < 1) {
            return ChainedOperationResult.continue_();
         } else {
            ctc var3 = (ctc)var2.get(0);
            byte var4 = 0;
            if (var3.getMnemonic().equalsIgnoreCase("endbr32") || var3.getMnemonic().equalsIgnoreCase("endbr64")) {
               if (var2.size() != 2) {
                  return ChainedOperationResult.continue_();
               }

               var3 = (ctc)var2.get(1);
               var4 = 1;
            }

            if (var3.getMnemonic().equals("jmp")) {
               long var5 = -1L;
               if (var3.Dw()[0].RF()) {
                  ctd var7 = (ctd)var3.Dw()[0];
                  if (var7.RF == -1L && var7.Dw == -1L) {
                     var5 = var7.Uv;
                  } else if (var3.getProcessorMode() == 64
                     && var7.RF == RegisterBankX64.getInstance().getDescriptionEntryByType(RegisterType.ProgramCounter).getId()
                     && var7.Dw == -1L) {
                     var5 = var7.Uv + var2.getEndAddress();
                  }
               } else if (var3.Dw()[0].getOperandType() == 3) {
                  var5 = var3.Dw()[0].getOperandValue() + var2.getAddressOfInstruction(0);
               }

               if (var5 != -1L) {
                  if (this.q == null) {
                     this.q = this.gca.getModel().getReferenceManager();
                  }

                  this.q.recordInternalReference(var2.getAddressOfInstruction(var4), var5, ReferenceType.BRANCH);
                  return new ChainedOperationResult(new Pointer(var5, 0, 5));
               }
            }

            return ChainedOperationResult.continue_();
         }
      }
   }

   @Override
   public ChainedOperationResult determineRoutineStackPointerDelta(CFG var1) {
      Integer var2 = null;

      for (BasicBlock var4 : var1.getExitBlocks()) {
         ctc var5 = (ctc)var4.getLast();
         int var6 = var5.q();
         if (var6 == 194 || var6 == 195) {
            int var7 = 0;
            if (var6 == 194) {
               var7 = (int)var5.Dw()[0].getOperandValue();
            }

            if (var2 == null) {
               var2 = var7;
            } else if (var2 != var7) {
               RF.trace("Inconsistent return deltas for method at %Xh", var1.getEntryAddress());
               return ChainedOperationResult.ignore();
            }
         }
      }

      return new ChainedOperationResult(var2);
   }

   public ChainedOperationResult q(long var1, ctc var3, List var4) {
      if (xK.contains(var3.getMnemonic())) {
         cqj[] var5 = var3.Dw();
         if (var5 != null) {
            boolean var6 = true;

            for (cqj var10 : var5) {
               if (!var10.isRegister() && !var10.isImmediate()) {
                  var6 = false;
                  break;
               }
            }

            if (var6) {
               return ChainedOperationResult.stop(true);
            }
         }
      }

      boolean var15 = false;
      if (var3.getProcessorMode() == 64) {
         for (int var16 = 0; var16 < var3.Dw().length; var16++) {
            cqj var18 = var3.Dw()[var16];
            if (var18.RF()) {
               IInstructionOperandCMA var19 = (IInstructionOperandCMA)var18;
               if (var19.getMemoryIndexRegister() == -1L
                  && var19.getMemoryBaseRegister() != -1L
                  && var19.getMemoryDisplacement() != 0L
                  && ctf.RF(var19.getMemoryBaseRegister()) == 10) {
                  long var20 = var19.getMemoryDisplacement() + var1 + var3.getSize();
                  int var11 = var19.getOperandBitsize();
                  boolean var12 = var3.q() == 141;
                  int var13 = var12 ? 0 : var11 / 8;
                  int var14 = var12 ? 0 : 2;
                  var4.add(new Pointer(var20, var13, var14));
                  var15 = true;
               }
            }
         }
      } else if (var3.q() == 141 && var3.Dw().length == 2 && var3.Dw()[1].RF()) {
         IInstructionOperandCMA var17 = (IInstructionOperandCMA)var3.Dw()[1];
         if (var17.getMemoryBaseRegister() == -1L && var17.getMemoryIndexRegister() == -1L && var17.getMemoryDisplacement() != 0L) {
            var4.add(new Pointer(var17.getMemoryDisplacement(), 0, 0));
            return ChainedOperationResult.TRUE_STOP;
         }
      }

      return var15 ? ChainedOperationResult.FALSE_STOP : ChainedOperationResult.FALSE_CONTINUE;
   }

   public ChainedOperationResult q(long var1, ctc var3) {
      MemoryRanges var4 = ((aae)this.gca).q();
      return var4 != null && var4.contains(var1) && !var4.contains(var1 + var3.getSize())
         ? ChainedOperationResult.TRUE_STOP
         : ChainedOperationResult.FALSE_CONTINUE;
   }

   @Override
   public ChainedOperationResult customizeInstructionItem(INativeInstructionItem var1) {
      ctc var2 = (ctc)var1.getInstruction();
      if (var2.getCode()[0] == -24) {
         long var3 = ((cqj)var2.getOperand(0)).getOperandValue(var1.getMemoryAddress());
         long var5 = var3 - var1.getMemoryAddress();
         if (var5 >= 0L && var5 <= 16L) {
            byte[] var7 = new byte[1];
            int var8 = 16;

            while (var8-- >= 0) {
               if (VirtualMemoryUtil.readByteSafe(this.gca.getMemory(), var3, var7)) {
                  int var9 = var7[0] & 255;
                  if (var9 != 144) {
                     if (var9 >= 88 && var9 <= 95) {
                        var1.getHints(true).setFakeCall(true);
                        return ChainedOperationResult.TRUE_CONTINUE;
                     }
                     break;
                  }

                  var3++;
               }
            }
         }
      }

      return super.customizeInstructionItem(var1);
   }

   static {
      xK.add("adc");
      xK.add("add");
      xK.add("and");
      xK.add("cmp");
      xK.add("dec");
      xK.add("div");
      xK.add("idiv");
      xK.add("inc");
      xK.add("imul");
      xK.add("mul");
      xK.add("neg");
      xK.add("sbb");
      xK.add("sub");
   }
}
