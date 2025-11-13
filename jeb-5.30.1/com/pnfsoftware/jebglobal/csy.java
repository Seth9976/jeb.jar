package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.units.code.IBasicBlockSkeleton;
import com.pnfsoftware.jeb.core.units.code.Pointer;
import com.pnfsoftware.jeb.core.units.code.asm.ChainedOperationResult;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.AbstractAnalyzerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.BinaryPattern;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.BinaryPatternVerifier;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.NativeAnalyzerException;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.SwitchInformation;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.memory.MemoryException;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IInstructionOperandCMA;
import com.pnfsoftware.jeb.core.units.codeobject.CodeObjectUnitUtil;
import com.pnfsoftware.jeb.core.units.codeobject.ICodeObjectUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ISegmentInformation;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.core.units.codeobject.SymbolType;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.List;

@Ser
public class csy extends AbstractAnalyzerExtension {
   private static final ILogger xK = GlobalLog.getLogger(csy.class);
   @SerId(1)
   private long Dw = -1L;
   @SerId(2)
   private boolean Uv = false;
   @SerTransient
   ctk q;
   @SerTransient
   csz RF;

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
            if (var3.getMnemonic().equalsIgnoreCase("endbr32")) {
               if (var2.size() != 2) {
                  return ChainedOperationResult.continue_();
               }

               var3 = (ctc)var2.get(1);
            }

            if (var3.getProcessorMode() == 32 && var3.nf.length == 6 && var3.nf[0] == -1 && (var3.nf[1] == -95 || var3.nf[1] == -93)) {
               ICodeObjectUnit var4 = this.gca.getContainer();
               if (var4 == null) {
                  return ChainedOperationResult.continue_();
               }

               if (!this.Uv && this.Dw == -1L) {
                  ISegmentInformation var5 = CodeObjectUnitUtil.findSectionByName(this.gca.getContainer(), ".got.plt");
                  if (var5 == null) {
                     var5 = CodeObjectUnitUtil.findSectionByName(this.gca.getContainer(), ".got");
                  }

                  if (var5 != null) {
                     this.Dw = var5.getOffsetInMemory() + this.getUnit().getVirtualImageBase();
                  } else if (this.gca.getProcessor().getType().equals(ProcessorType.X86)) {
                     this.Dw = this.q();
                  }

                  this.Uv = true;
               }

               if (this.Dw == -1L) {
                  return ChainedOperationResult.continue_();
               }

               if (((aae)this.gca).Dw() != null) {
                  ISegmentInformation var7 = CodeObjectUnitUtil.findSectionByRelativeAddress(var4, var1.getEntryAddress() - ((aae)this.gca).Dw().io());
                  if (var7 != null && !var7.getName().startsWith(".plt")) {
                     return ChainedOperationResult.continue_();
                  }

                  cqj[] var6 = var3.Dw();
                  if (var6 != null && var6.length == 1 && var6[0].RF()) {
                     return new ChainedOperationResult(new Pointer(this.Dw + ((IInstructionOperandCMA)var6[0]).getMemoryDisplacement(), 0, 5));
                  }
               }
            }

            return ChainedOperationResult.continue_();
         }
      }
   }

   // $VF: Handled exception range with multiple entry points by splitting it
   // $VF: Duplicated exception handlers to handle obfuscated exceptions
   private long q() {
      aaw var1 = ((aae)this.gca).Dw();
      ICodeObjectUnit var2 = this.gca.getContainer();
      long var3 = var1.io();
      long var5 = var3;
      long var7 = var3 + var1.qa();
      int var9 = this.gca.getTypeManager().getPointerSize();

      while (true) {
         long var10;
         int var16;
         while (true) {
            if (var5 >= var7) {
               return -1L;
            }

            try {
               var10 = var5 - var9;
               long var12 = this.gca.getMemory().readPointer(var5);
               long var14 = this.gca.getMemory().readPointer(var5 + var9);
               if (var12 == 0L && var14 == 0L) {
                  var5 += var9 * 2;
                  var16 = 0;
                  break;
               }
            } catch (MemoryException var17) {
               return -1L;
            }

            var5 += var9;
         }

         while (true) {
            try {
               if (var16 >= 3) {
                  return var10;
               }

               if (CodeObjectUnitUtil.findAllSymbolsByRelativeAddress(var2, var5 - var3)
                     .stream()
                     .filter(var0 -> var0.getType().equals(SymbolType.PTRFUNCTION) && (var0.getFlags() & 1) != 0)
                     .count()
                  != 1L) {
                  break;
               }
            } catch (MemoryException var18) {
               return -1L;
            }

            var5 += var9;
            var16++;
         }
      }
   }

   @Override
   public void initializeProloguePatterns(BinaryPatternVerifier var1) {
      var1.addPatterns(new BinaryPattern(new byte[]{85, 87, 86, 83, -128}, new byte[]{-1, -1, -1, -1, -16}));
   }

   @Override
   protected void initializePaddingPatterns(BinaryPatternVerifier var1) {
      var1.addPatterns(
         new BinaryPattern(new byte[]{-112}),
         new BinaryPattern(new byte[]{102, -112}),
         new BinaryPattern(new byte[]{-115, 118, 0}),
         new BinaryPattern(new byte[]{-115, 116, 38, 0}),
         new BinaryPattern(new byte[]{-112, -115, 116, 38, 0}),
         new BinaryPattern(new byte[]{-115, -74, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{-115, -76, 38, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{-112, -115, -76, 38, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{-119, -10, -115, -68, 39, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{-115, 118, 0, -115, -68, 39, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{-115, 116, 38, 0, -115, -68, 39, 0, 0, 0, 0}),
         new BinaryPattern(
            new byte[]{
               -21,
               29,
               -112,
               -112,
               -112,
               -112,
               -112,
               -112,
               -112,
               -112,
               -112,
               -112,
               -112,
               -112,
               -112,
               -112,
               -112,
               -112,
               -112,
               -112,
               -112,
               -112,
               -112,
               -112,
               -112,
               -112,
               -112,
               -112,
               -112,
               -112,
               -112
            }
         ),
         new BinaryPattern(new byte[]{-21, 13, -112, -112, -112, -112, -112, -112, -112, -112, -112, -112, -112, -112, -112}),
         new BinaryPattern(new byte[]{-115, -65, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{-115, -68, 39, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{-115, 116, 0}),
         new BinaryPattern(new byte[]{-115, -76, 0, 0}),
         new BinaryPattern(new byte[]{-112, -115, -76, 0, 0}),
         new BinaryPattern(new byte[]{-119, -10, -115, -67, 0, 0}),
         new BinaryPattern(new byte[]{-115, -67, 0, 0}),
         new BinaryPattern(new byte[]{15, 31, 0}),
         new BinaryPattern(new byte[]{15, 31, 64, 0}),
         new BinaryPattern(new byte[]{15, 31, 68, 0, 0}),
         new BinaryPattern(new byte[]{102, 15, 31, 68, 0, 0}),
         new BinaryPattern(new byte[]{15, 31, -128, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{15, 31, -124, 0, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{102, 15, 31, -124, 0, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{102, 46, 15, 31, -124, 0, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{102, 102, 46, 15, 31, -124, 0, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{102, 102, 102, 46, 15, 31, -124, 0, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{102, 102, 102, 102, 46, 15, 31, -124, 0, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{102, 102, 102, 102, 102, 46, 15, 31, -124, 0, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{102, 102, 102, 102, 102, 102, 46, 15, 31, -124, 0, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{15, 31, 68, 0, 0, 102, 15, 31, 68, 0, 0}),
         new BinaryPattern(new byte[]{102, 15, 31, 68, 0, 0, 102, 15, 31, 68, 0, 0}),
         new BinaryPattern(new byte[]{102, 15, 31, 68, 0, 0, 15, 31, -128, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{15, 31, -128, 0, 0, 0, 0, 15, 31, -128, 0, 0, 0, 0}),
         new BinaryPattern(new byte[]{15, 31, -128, 0, 0, 0, 0, 15, 31, -124, 0, 0, 0, 0, 0})
      );
   }

   public ChainedOperationResult q(long var1, ctc var3) {
      return ChainedOperationResult.FALSE_CONTINUE;
   }

   public ChainedOperationResult q(long var1, ctc var3, IBasicBlockSkeleton var4) {
      if (this.q == null) {
         this.q = ctn.q(this.gca);
      }

      return this.q != null && this.q.q(var1, var3, var4) ? ChainedOperationResult.TRUE_STOP : ChainedOperationResult.FALSE_CONTINUE;
   }

   @Override
   public ChainedOperationResult determineSwitchInformation(long var1, IBasicBlockSkeleton var3, List var4) {
      if (this.q != null) {
         SwitchInformation var5 = this.q.q(var1, var3, var4);
         if (var5 != null) {
            return new ChainedOperationResult(var5);
         }
      }

      return ChainedOperationResult.ignore();
   }

   @Override
   public ChainedOperationResult postprocessImage(int var1) {
      ChainedOperationResult var2 = ChainedOperationResult.FALSE_IGNORE;

      try {
         if (this.RF == null) {
            this.RF = new csz(this.gca, ((aae)this.gca).Dw().lm());
         }

         if (this.RF != null && this.RF.RF()) {
            this.RF.xK();
            var2 = ChainedOperationResult.TRUE_CONTINUE;
         }
      } catch (Exception var4) {
         xK.error(S.L("Tail call analyzer failed"));
         JebCoreService.notifySilentExceptionToClient(new NativeAnalyzerException("tail call analyzer failed", var4));
      }

      return var2;
   }
}
