package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IBasicBlockSkeleton;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.util.base.Couple;
import java.util.List;

public class cjj extends com.pnfsoftware.jeb.corei.parsers.x86.wn {
   public cjj(INativeCodeAnalyzer var1, com.pnfsoftware.jeb.corei.parsers.x86.wn.Av var2) {
      super(var1, var2);
      this.E = true;
   }

   @Override
   public boolean pC(long var1, com.pnfsoftware.jeb.corei.parsers.x86.vh var3, IBasicBlockSkeleton var4) {
      List var5 = var4.getInsntructions();
      if (var5.size() >= 3 && var5.size() <= 10 && var3.getMnemonic().equals("jmp") && var3.A()[0].isRegister()) {
         long var6 = var3.A()[0].getOperandValue();
         boolean var8 = false;
         long var9 = -1L;

         for (int var11 = var5.size() - 2; var11 >= 0; var11--) {
            com.pnfsoftware.jeb.corei.parsers.x86.vh var12 = (com.pnfsoftware.jeb.corei.parsers.x86.vh)var5.get(var11);
            String var13 = var12.getMnemonic();
            if (!var8 && var13.equalsIgnoreCase("add") && var12.A()[0].isRegister() && var12.A()[0].getOperandValue() == var6) {
               var9 = var12.A()[1].getOperandValue();
               var8 = true;
            } else if (var8
               && (var13.equalsIgnoreCase("mov") || var13.equalsIgnoreCase("movsxd"))
               && var12.A()[0].isRegister()
               && (
                  com.pnfsoftware.jeb.corei.parsers.x86.MG.pC(var12.A()[0].getOperandValue(), var6)
                     || com.pnfsoftware.jeb.corei.parsers.x86.MG.pC(var12.A()[0].getOperandValue(), var9)
               )
               && var12.A()[1].A()) {
               if (var13.equalsIgnoreCase("movsxd")) {
                  this.ys = true;
               }

               com.pnfsoftware.jeb.corei.parsers.x86.QM var14 = (com.pnfsoftware.jeb.corei.parsers.x86.QM)var12.A()[1];
               if (var14.getMemoryScale() == 4) {
                  if (var14.getMemoryDisplacement() != 0L) {
                     this.ld.put(var1, new Couple(var14.getMemoryDisplacement() + this.wS, var14.getMemoryIndexRegister()));
                     return true;
                  }

                  if (var11 >= 1) {
                     com.pnfsoftware.jeb.corei.parsers.x86.vh var15 = (com.pnfsoftware.jeb.corei.parsers.x86.vh)var5.get(var11 - 1);
                     if (var15.getMnemonic().equalsIgnoreCase("lea")
                        && com.pnfsoftware.jeb.corei.parsers.x86.MG.pC(var14.getMemoryBaseRegister(), var15.A()[0].getOperandValue())) {
                        com.pnfsoftware.jeb.corei.parsers.x86.QM var16 = (com.pnfsoftware.jeb.corei.parsers.x86.QM)var15.A()[1];
                        if (com.pnfsoftware.jeb.corei.parsers.x86.MG.A(var16.getMemoryBaseRegister()) == 10) {
                           this.ld
                              .put(
                                 var1,
                                 new Couple(
                                    ((com.pnfsoftware.jeb.corei.parsers.x86.QM)var15.A()[1]).getMemoryDisplacement() + ((ON)var4).pC(var11),
                                    var14.getMemoryIndexRegister()
                                 )
                              );
                           this.E = false;
                           this.sY = true;
                           return true;
                        }
                     }
                  }

                  return false;
               }
            }
         }
      }

      return false;
   }

   @Override
   public long pC(IBasicBlockSkeleton var1, List var2, long var3) {
      for (IBasicBlockSkeleton var6 : var2) {
         if (var6.getDstOffsets().contains(var1.getFirstAddress())) {
            if (var6.getInsntructions().size() >= 2) {
               String var7 = ((com.pnfsoftware.jeb.corei.parsers.x86.vh)var6.getInsntructions().get(var6.getInsntructions().size() - 1)).getMnemonic();
               boolean var8 = false;
               if (var7.equalsIgnoreCase("jb")) {
                  var8 = true;
               } else if (!var7.equalsIgnoreCase("ja") && !var7.equalsIgnoreCase("jna") && !var7.equalsIgnoreCase("jnbe") && !var7.equalsIgnoreCase("jbe")) {
                  break;
               }

               int var9 = 2;
               com.pnfsoftware.jeb.corei.parsers.x86.vh var10 = (com.pnfsoftware.jeb.corei.parsers.x86.vh)var6.getInsntructions()
                  .get(var6.getInsntructions().size() - var9);
               if (!var10.getMnemonic().equalsIgnoreCase("cmp")) {
                  if (var6.getInsntructions().size() < 3) {
                     break;
                  }

                  var10 = (com.pnfsoftware.jeb.corei.parsers.x86.vh)var6.getInsntructions().get(var6.getInsntructions().size() - (var9 + 1));
                  if (!var10.getMnemonic().equalsIgnoreCase("cmp")) {
                     break;
                  }

                  var9++;
               }

               com.pnfsoftware.jeb.corei.parsers.x86.Av var11 = var10.A()[1];
               if (var11.isImmediate()) {
                  return var8 ? var11.getOperandValue() : var11.getOperandValue() + 1L;
               }

               com.pnfsoftware.jeb.corei.parsers.x86.vh var12 = (com.pnfsoftware.jeb.corei.parsers.x86.vh)var6.getInsntructions()
                  .get(var6.getInsntructions().size() - (var9 + 1));
               if (var12.getMnemonic().equalsIgnoreCase("mov")) {
                  com.pnfsoftware.jeb.corei.parsers.x86.Av var13 = var12.A()[1];
                  if (var13.isImmediate()) {
                     return var13.getOperandValue() + 1L;
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
      if (var1.getInsntructions().size() > 3) {
         for (int var4 = var1.getInsntructions().size() - 4; var4 >= 0; var4--) {
            com.pnfsoftware.jeb.corei.parsers.x86.vh var5 = (com.pnfsoftware.jeb.corei.parsers.x86.vh)var1.getInsntructions().get(var4);
            if (var5.A().length == 2 && var5.A()[0].isRegister() && var5.A()[1].A()) {
               com.pnfsoftware.jeb.corei.parsers.x86.QM var6 = (com.pnfsoftware.jeb.corei.parsers.x86.QM)var5.A()[1];
               if (com.pnfsoftware.jeb.corei.parsers.x86.MG.pC(var5.A()[0].getOperandValue(), var2)
                  && var6.getMemoryScale() == 1
                  && var6.getMemoryBaseRegister() != -1L
                  && var6.getMemoryIndexRegister() != -1L
                  && var6.getMemoryDisplacement() != 0L) {
                  long var7 = this.wS + var6.getMemoryDisplacement();
                  if (((a)this.kS).getAnalysisRanges().contains(var7)) {
                     return new Couple(var7, var6.getMemoryIndexRegister());
                  }
               }
            }
         }
      }

      return null;
   }
}
