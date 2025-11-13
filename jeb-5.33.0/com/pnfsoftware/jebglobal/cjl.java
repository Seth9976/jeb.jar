package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IBasicBlockSkeleton;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IInstructionOperandCMA;
import com.pnfsoftware.jeb.util.base.Couple;
import java.util.List;

public class cjl extends com.pnfsoftware.jeb.corei.parsers.x86.wn {
   public cjl(INativeCodeAnalyzer var1, com.pnfsoftware.jeb.corei.parsers.x86.wn.Av var2) {
      super(var1, var2);
   }

   @Override
   public long pC(IBasicBlockSkeleton var1, List var2, long var3) {
      for (IBasicBlockSkeleton var6 : var2) {
         if (var6.getDstOffsets().contains(var1.getFirstAddress())) {
            if (var6.getInsntructions().size() >= 2) {
               com.pnfsoftware.jeb.corei.parsers.x86.vh var7 = (com.pnfsoftware.jeb.corei.parsers.x86.vh)var6.getInsntructions()
                  .get(var6.getInsntructions().size() - 1);
               if (var7.getMnemonic().equalsIgnoreCase("ja") || var7.getMnemonic().equalsIgnoreCase("jnbe")) {
                  boolean var8 = false;
                  int var9 = 0;

                  for (int var10 = var6.getInsntructions().size() - 2; var10 >= 0; var10--) {
                     if (((com.pnfsoftware.jeb.corei.parsers.x86.vh)var6.getInsntructions().get(var10)).getMnemonic().equalsIgnoreCase("cmp")) {
                        var8 = true;
                        var9 = var10;
                        break;
                     }
                  }

                  if (var8) {
                     com.pnfsoftware.jeb.corei.parsers.x86.vh var15 = (com.pnfsoftware.jeb.corei.parsers.x86.vh)var6.getInsntructions().get(var9);
                     com.pnfsoftware.jeb.corei.parsers.x86.Av var11 = var15.A()[1];
                     if (var11.isImmediate()) {
                        return var11.getOperandValue() + 1L;
                     }

                     if (var11.isRegister() && var6.getInsntructions().size() >= 4) {
                        boolean var12 = false;

                        for (int var13 = var6.getInsntructions().size() - 3; var13 >= 0; var13--) {
                           com.pnfsoftware.jeb.corei.parsers.x86.vh var14 = (com.pnfsoftware.jeb.corei.parsers.x86.vh)var6.getInsntructions().get(var13);
                           if (!var12) {
                              if (var14.getMnemonic().equalsIgnoreCase("pop")
                                 && var14.A()[0].isRegister()
                                 && var14.A()[0].getOperandValue() == var11.getOperandValue()) {
                                 var12 = true;
                              }
                           } else if (var14.getMnemonic().equalsIgnoreCase("push") && var14.A()[0].isImmediate()) {
                              return var14.A()[0].getOperandValue() + 1L;
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
   public Couple pC(IBasicBlockSkeleton var1, long var2) {
      if (var1.getInsntructions().size() > 1) {
         for (int var4 = var1.getInsntructions().size() - 2; var4 >= 0; var4--) {
            com.pnfsoftware.jeb.corei.parsers.x86.vh var5 = (com.pnfsoftware.jeb.corei.parsers.x86.vh)var1.getInsntructions().get(var4);
            if (var5.A().length == 2 && var5.A()[0].isRegister() && var5.A()[1].A()) {
               com.pnfsoftware.jeb.corei.parsers.x86.QM var6 = (com.pnfsoftware.jeb.corei.parsers.x86.QM)var5.A()[1];
               if (com.pnfsoftware.jeb.corei.parsers.x86.MG.pC(var5.A()[0].getOperandValue(), var2)
                  && var6.getMemoryScale() == 0
                  && var6.getMemoryBaseRegister() != -1L
                  && var6.getMemoryIndexRegister() == -1L
                  && var6.getMemoryDisplacement() != 0L
                  && ((a)this.kS).getAnalysisRanges().contains(var6.getMemoryDisplacement())) {
                  return new Couple(var6.getMemoryDisplacement(), var6.getMemoryBaseRegister());
               }
            }
         }
      }

      return null;
   }

   @Override
   public long A(IBasicBlockSkeleton var1, List var2, long var3) {
      for (IBasicBlockSkeleton var6 : var2) {
         if (var6.getDstOffsets().contains(var1.getFirstAddress())) {
            if (var6.getInsntructions().size() != 3) {
               break;
            }

            com.pnfsoftware.jeb.corei.parsers.x86.vh var7 = (com.pnfsoftware.jeb.corei.parsers.x86.vh)var6.getInsntructions()
               .get(var6.getInsntructions().size() - 1);
            if (!var7.getMnemonic().equalsIgnoreCase("ja") && !var7.getMnemonic().equalsIgnoreCase("jnbe")) {
               break;
            }

            com.pnfsoftware.jeb.corei.parsers.x86.vh var8 = (com.pnfsoftware.jeb.corei.parsers.x86.vh)var6.getInsntructions()
               .get(var6.getInsntructions().size() - 2);
            if (!var8.getMnemonic().equalsIgnoreCase("cmp")) {
               break;
            }

            com.pnfsoftware.jeb.corei.parsers.x86.vh var9 = (com.pnfsoftware.jeb.corei.parsers.x86.vh)var6.getInsntructions()
               .get(var6.getInsntructions().size() - 3);
            if (!var9.getMnemonic().equalsIgnoreCase("lea")) {
               break;
            }

            com.pnfsoftware.jeb.corei.parsers.x86.Av var10 = var9.A()[1];
            if (var10.A() && ((IInstructionOperandCMA)var10).getMemoryBaseRegister() == var3 && ((IInstructionOperandCMA)var10).getMemoryDisplacement() < 0L) {
               return Math.abs(((IInstructionOperandCMA)var10).getMemoryDisplacement());
            }
         }
      }

      return 0L;
   }
}
