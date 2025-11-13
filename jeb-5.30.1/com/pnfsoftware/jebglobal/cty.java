package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IBasicBlockSkeleton;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IInstructionOperandCMA;
import com.pnfsoftware.jeb.util.base.Couple;
import java.util.List;

public class cty extends ctk {
   public cty(INativeCodeAnalyzer var1, ctk.eo var2) {
      super(var1, var2);
   }

   @Override
   public long q(IBasicBlockSkeleton var1, List var2, long var3) {
      for (IBasicBlockSkeleton var6 : var2) {
         if (var6.getDstOffsets().contains(var1.getFirstAddress())) {
            if (var6.getInsntructions().size() >= 2) {
               ctc var7 = (ctc)var6.getInsntructions().get(var6.getInsntructions().size() - 1);
               if (var7.getMnemonic().equalsIgnoreCase("ja") || var7.getMnemonic().equalsIgnoreCase("jnbe")) {
                  boolean var8 = false;
                  int var9 = 0;

                  for (int var10 = var6.getInsntructions().size() - 2; var10 >= 0; var10--) {
                     if (((ctc)var6.getInsntructions().get(var10)).getMnemonic().equalsIgnoreCase("cmp")) {
                        var8 = true;
                        var9 = var10;
                        break;
                     }
                  }

                  if (var8) {
                     ctc var15 = (ctc)var6.getInsntructions().get(var9);
                     cqj var11 = var15.Dw()[1];
                     if (var11.isImmediate()) {
                        return var11.getOperandValue() + 1L;
                     }

                     if (var11.isRegister() && var6.getInsntructions().size() >= 4) {
                        boolean var12 = false;

                        for (int var13 = var6.getInsntructions().size() - 3; var13 >= 0; var13--) {
                           ctc var14 = (ctc)var6.getInsntructions().get(var13);
                           if (!var12) {
                              if (var14.getMnemonic().equalsIgnoreCase("pop")
                                 && var14.Dw()[0].isRegister()
                                 && var14.Dw()[0].getOperandValue() == var11.getOperandValue()) {
                                 var12 = true;
                              }
                           } else if (var14.getMnemonic().equalsIgnoreCase("push") && var14.Dw()[0].isImmediate()) {
                              return var14.Dw()[0].getOperandValue() + 1L;
                           }
                        }
                     }
                  }
               }
            }
            break;
         }
      }

      return -1L;
   }

   @Override
   public Couple q(IBasicBlockSkeleton var1, long var2) {
      if (var1.getInsntructions().size() > 1) {
         for (int var4 = var1.getInsntructions().size() - 2; var4 >= 0; var4--) {
            ctc var5 = (ctc)var1.getInsntructions().get(var4);
            if (var5.Dw().length == 2 && var5.Dw()[0].isRegister() && var5.Dw()[1].RF()) {
               ctd var6 = (ctd)var5.Dw()[1];
               if (ctf.q(var5.Dw()[0].getOperandValue(), var2)
                  && var6.getMemoryScale() == 0
                  && var6.getMemoryBaseRegister() != -1L
                  && var6.getMemoryIndexRegister() == -1L
                  && var6.getMemoryDisplacement() != 0L
                  && ((aae)this.xK).getAnalysisRanges().contains(var6.getMemoryDisplacement())) {
                  return new Couple(var6.getMemoryDisplacement(), var6.getMemoryBaseRegister());
               }
            }
         }
      }

      return null;
   }

   @Override
   public long RF(IBasicBlockSkeleton var1, List var2, long var3) {
      for (IBasicBlockSkeleton var6 : var2) {
         if (var6.getDstOffsets().contains(var1.getFirstAddress())) {
            if (var6.getInsntructions().size() != 3) {
               break;
            }

            ctc var7 = (ctc)var6.getInsntructions().get(var6.getInsntructions().size() - 1);
            if (!var7.getMnemonic().equalsIgnoreCase("ja") && !var7.getMnemonic().equalsIgnoreCase("jnbe")) {
               break;
            }

            ctc var8 = (ctc)var6.getInsntructions().get(var6.getInsntructions().size() - 2);
            if (!var8.getMnemonic().equalsIgnoreCase("cmp")) {
               break;
            }

            ctc var9 = (ctc)var6.getInsntructions().get(var6.getInsntructions().size() - 3);
            if (!var9.getMnemonic().equalsIgnoreCase("lea")) {
               break;
            }

            cqj var10 = var9.Dw()[1];
            if (var10.RF() && ((IInstructionOperandCMA)var10).getMemoryBaseRegister() == var3 && ((IInstructionOperandCMA)var10).getMemoryDisplacement() < 0L) {
               return Math.abs(((IInstructionOperandCMA)var10).getMemoryDisplacement());
            }
         }
      }

      return 0L;
   }
}
