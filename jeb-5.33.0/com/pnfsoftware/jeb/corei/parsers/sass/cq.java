package com.pnfsoftware.jeb.corei.parsers.sass;

import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.asm.ChainedOperationResult;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.AbstractAnalyzerExtension;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeInstructionItem;
import com.pnfsoftware.jeb.core.units.code.asm.mangling.IUnmangledData;
import com.pnfsoftware.jeb.core.units.code.asm.mangling.UnmanglerService;
import com.pnfsoftware.jeb.core.units.codeobject.ICodeObjectUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ISegmentInformation;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public class cq extends AbstractAnalyzerExtension {
   private static final ILogger pC = GlobalLog.getLogger(cq.class);

   @Override
   public void initialize(INativeCodeAnalyzer var1) {
      super.initialize(var1);
   }

   @Override
   public boolean skipSymbolProcessing() {
      return true;
   }

   @Override
   public ChainedOperationResult preprocessImage(int var1) {
      if (var1 == 0) {
         ICodeObjectUnit var2 = this.gca.getContainer();
         if (var2 != null) {
            HE var3 = new HE();
            UnmanglerService var4 = this.gca.getUnmanglerService();
            var4.registerEngine(4);
            int var5 = 0;

            for (ISegmentInformation var7 : var2.getValidSections()) {
               if ((var7.getFlags() & 6) == 6 && var7.getSizeInFile() > 0L) {
                  long var8 = var2.convertFileOffsetToRelativeAddress(var7.getOffsetInFile());
                  if (var8 != -1L) {
                     String var10 = "kernel" + var5;
                     String var11 = var7.getName();
                     if (var11.startsWith(".text.")) {
                        var10 = var11.substring(6);
                        IUnmangledData var12 = var4.unmangle(var10, true);
                        if (var12 != null) {
                           String var13 = var12.getSimple();
                           if (var13 != null) {
                              int var14 = var13.indexOf("<");
                              if (var14 >= 0) {
                                 var13 = var13.substring(0, var14);
                              }

                              if (!var13.isEmpty()) {
                                 var10 = var13;
                              }
                           }
                        }
                     }

                     var3.pC(var7.getName(), var8, var7.getSizeInMemory());
                     this.gca.getModel().getLabelManager().setLabel(var8, var10, true, true, false);
                     this.gca.enqueuePointerForAnalysis(new CodePointer(var8), 1);
                     var5++;
                  }
               }
            }

            var2.setData("SassMappedKernelsInfo", var3, true);
         }
      }

      return ChainedOperationResult.TRUE_CONTINUE;
   }

   @Override
   public ChainedOperationResult customizeInstructionItem(INativeInstructionItem var1) {
      sy var2 = (sy)var1.getInstruction();
      if (var2.getMnemonic().equals("CALL") && var2.pC(false).equals("CALL.REL.NOINC")) {
         HE var3 = (HE)this.gca.getContainer().getData("SassMappedKernelsInfo");
         if (var3 == null) {
            return ChainedOperationResult.FALSE_CONTINUE;
         } else {
            long var4 = var1.getMemoryAddress();
            HE.Av var6 = (HE.Av)var3.pC.getSegmentContaining(var4);
            if (var6 == null) {
               return ChainedOperationResult.FALSE_CONTINUE;
            } else {
               long var7 = var4 + 16L - var6.getBegin();

               for (int var9 = 0; var9 < 10; var9++) {
                  var4 -= 16L;
                  if (var4 < var6.getBegin() || !(this.gca.getModel().getItemAt(var4) instanceof INativeInstructionItem var11)) {
                     break;
                  }

                  sy var12 = (sy)var11.getInstruction();
                  String var13 = var12.getMnemonic();
                  if (var9 == 0 && var13.equals("LEPC")) {
                     return ChainedOperationResult.FALSE_CONTINUE;
                  }

                  if (var13.startsWith("MOV")) {
                     oP var14 = (oP)var12.getOperand(1);
                     if (var14.getOperandType() == 1 && var14.getOperandValue(var4) == var7) {
                        return ChainedOperationResult.FALSE_CONTINUE;
                     }
                  }
               }

               var1.getHints(true).setFakeCall(true);
               return ChainedOperationResult.TRUE_CONTINUE;
            }
         }
      } else {
         return ChainedOperationResult.FALSE_CONTINUE;
      }
   }
}
