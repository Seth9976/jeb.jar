package com.pnfsoftware.jeb.corei.parsers.x86;

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
import com.pnfsoftware.jebglobal.a;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Ser
public class io extends AbstractAnalyzerExtension {
   private static final ILogger A = GlobalLog.getLogger(io.class);
   private static Set kS = new HashSet();
   @SerTransient
   IReferenceManager pC;

   @Override
   protected void initializePaddingPatterns(BinaryPatternVerifier var1) {
      var1.addPatterns(new BinaryPattern(new byte[]{-52}));
   }

   @Override
   public void initializeProloguePatterns(BinaryPatternVerifier var1) {
      var1.addPatterns(new BinaryPattern(new byte[]{85, -117, -20}), new BinaryPattern(new byte[]{85, -119, -27}), new Ee());
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
            vh var3 = (vh)var2.get(0);
            byte var4 = 0;
            if (var3.getMnemonic().equalsIgnoreCase("endbr32") || var3.getMnemonic().equalsIgnoreCase("endbr64")) {
               if (var2.size() != 2) {
                  return ChainedOperationResult.continue_();
               }

               var3 = (vh)var2.get(1);
               var4 = 1;
            }

            if (var3.getMnemonic().equals("jmp")) {
               long var5 = -1L;
               if (var3.A()[0].A()) {
                  QM var7 = (QM)var3.A()[0];
                  if (var7.A == -1L && var7.wS == -1L) {
                     var5 = var7.UT;
                  } else if (var3.getProcessorMode() == 64
                     && var7.A == RegisterBankX64.getInstance().getDescriptionEntryByType(RegisterType.ProgramCounter).getId()
                     && var7.wS == -1L) {
                     var5 = var7.UT + var2.getEndAddress();
                  }
               } else if (var3.A()[0].getOperandType() == 3) {
                  var5 = var3.A()[0].getOperandValue() + var2.getAddressOfInstruction(0);
               }

               if (var5 != -1L) {
                  if (this.pC == null) {
                     this.pC = this.gca.getModel().getReferenceManager();
                  }

                  this.pC.recordInternalReference(var2.getAddressOfInstruction(var4), var5, ReferenceType.BRANCH);
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
         vh var5 = (vh)var4.getLast();
         int var6 = var5.pC();
         if (var6 == 194 || var6 == 195) {
            int var7 = 0;
            if (var6 == 194) {
               var7 = (int)var5.A()[0].getOperandValue();
            }

            if (var2 == null) {
               var2 = var7;
            } else if (var2 != var7) {
               A.trace("Inconsistent return deltas for method at %Xh", var1.getEntryAddress());
               return ChainedOperationResult.ignore();
            }
         }
      }

      return new ChainedOperationResult(var2);
   }

   public ChainedOperationResult pC(long var1, vh var3, List var4) {
      if (kS.contains(var3.getMnemonic())) {
         Av[] var5 = var3.A();
         if (var5 != null) {
            boolean var6 = true;

            for (Av var10 : var5) {
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
         for (int var16 = 0; var16 < var3.A().length; var16++) {
            Av var18 = var3.A()[var16];
            if (var18.A()) {
               IInstructionOperandCMA var19 = (IInstructionOperandCMA)var18;
               if (var19.getMemoryIndexRegister() == -1L
                  && var19.getMemoryBaseRegister() != -1L
                  && var19.getMemoryDisplacement() != 0L
                  && MG.A(var19.getMemoryBaseRegister()) == 10) {
                  long var20 = var19.getMemoryDisplacement() + var1 + var3.getSize();
                  int var11 = var19.getOperandBitsize();
                  boolean var12 = var3.pC() == 141;
                  int var13 = var12 ? 0 : var11 / 8;
                  int var14 = var12 ? 0 : 2;
                  var4.add(new Pointer(var20, var13, var14));
                  var15 = true;
               }
            }
         }
      } else if (var3.pC() == 141 && var3.A().length == 2 && var3.A()[1].A()) {
         IInstructionOperandCMA var17 = (IInstructionOperandCMA)var3.A()[1];
         if (var17.getMemoryBaseRegister() == -1L && var17.getMemoryIndexRegister() == -1L && var17.getMemoryDisplacement() != 0L) {
            var4.add(new Pointer(var17.getMemoryDisplacement(), 0, 0));
            return ChainedOperationResult.TRUE_STOP;
         }
      }

      return var15 ? ChainedOperationResult.FALSE_STOP : ChainedOperationResult.FALSE_CONTINUE;
   }

   public ChainedOperationResult pC(long var1, vh var3) {
      MemoryRanges var4 = ((a)this.gca).pC();
      return var4 != null && var4.contains(var1) && !var4.contains(var1 + var3.getSize())
         ? ChainedOperationResult.TRUE_STOP
         : ChainedOperationResult.FALSE_CONTINUE;
   }

   @Override
   public ChainedOperationResult customizeInstructionItem(INativeInstructionItem var1) {
      vh var2 = (vh)var1.getInstruction();
      if (var2.getCode()[0] == -24) {
         long var3 = ((Av)var2.getOperand(0)).getOperandValue(var1.getMemoryAddress());
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
      kS.add("adc");
      kS.add("add");
      kS.add("and");
      kS.add("cmp");
      kS.add("dec");
      kS.add("div");
      kS.add("idiv");
      kS.add("inc");
      kS.add("imul");
      kS.add("mul");
      kS.add("neg");
      kS.add("sbb");
      kS.add("sub");
   }
}
