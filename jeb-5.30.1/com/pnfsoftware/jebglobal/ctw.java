package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IBasicBlockSkeleton;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.util.base.Couple;
import java.util.List;

public class ctw extends ctk {
   public ctw(INativeCodeAnalyzer var1, ctk.eo var2) {
      super(var1, var2);
      this.oW = true;
   }

   @Override
   public boolean q(long var1, ctc var3, IBasicBlockSkeleton var4) {
      List var5 = var4.getInsntructions();
      if (var5.size() >= 3 && var5.size() <= 10 && var3.getMnemonic().equals("jmp") && var3.Dw()[0].isRegister()) {
         long var6 = var3.Dw()[0].getOperandValue();
         boolean var8 = false;
         long var9 = -1L;

         for (int var11 = var5.size() - 2; var11 >= 0; var11--) {
            ctc var12 = (ctc)var5.get(var11);
            String var13 = var12.getMnemonic();
            if (!var8 && var13.equalsIgnoreCase("add") && var12.Dw()[0].isRegister() && var12.Dw()[0].getOperandValue() == var6) {
               var9 = var12.Dw()[1].getOperandValue();
               var8 = true;
            } else if (var8
               && (var13.equalsIgnoreCase("mov") || var13.equalsIgnoreCase("movsxd"))
               && var12.Dw()[0].isRegister()
               && (ctf.q(var12.Dw()[0].getOperandValue(), var6) || ctf.q(var12.Dw()[0].getOperandValue(), var9))
               && var12.Dw()[1].RF()) {
               if (var13.equalsIgnoreCase("movsxd")) {
                  this.nf = true;
               }

               ctd var14 = (ctd)var12.Dw()[1];
               if (var14.getMemoryScale() == 4) {
                  if (var14.getMemoryDisplacement() != 0L) {
                     this.gP.put(var1, new Couple(var14.getMemoryDisplacement() + this.Dw, var14.getMemoryIndexRegister()));
                     return true;
                  }

                  if (var11 >= 1) {
                     ctc var15 = (ctc)var5.get(var11 - 1);
                     if (var15.getMnemonic().equalsIgnoreCase("lea") && ctf.q(var14.getMemoryBaseRegister(), var15.Dw()[0].getOperandValue())) {
                        ctd var16 = (ctd)var15.Dw()[1];
                        if (ctf.RF(var16.getMemoryBaseRegister()) == 10) {
                           this.gP.put(var1, new Couple(((ctd)var15.Dw()[1]).getMemoryDisplacement() + ((kR)var4).q(var11), var14.getMemoryIndexRegister()));
                           this.oW = false;
                           this.gO = true;
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
   public long q(IBasicBlockSkeleton var1, List var2, long var3) {
      for (IBasicBlockSkeleton var6 : var2) {
         if (var6.getDstOffsets().contains(var1.getFirstAddress())) {
            if (var6.getInsntructions().size() >= 2) {
               String var7 = ((ctc)var6.getInsntructions().get(var6.getInsntructions().size() - 1)).getMnemonic();
               boolean var8 = false;
               if (var7.equalsIgnoreCase("jb")) {
                  var8 = true;
               } else if (!var7.equalsIgnoreCase("ja") && !var7.equalsIgnoreCase("jna") && !var7.equalsIgnoreCase("jnbe") && !var7.equalsIgnoreCase("jbe")) {
                  break;
               }

               int var9 = 2;
               ctc var10 = (ctc)var6.getInsntructions().get(var6.getInsntructions().size() - var9);
               if (!var10.getMnemonic().equalsIgnoreCase("cmp")) {
                  if (var6.getInsntructions().size() < 3) {
                     break;
                  }

                  var10 = (ctc)var6.getInsntructions().get(var6.getInsntructions().size() - (var9 + 1));
                  if (!var10.getMnemonic().equalsIgnoreCase("cmp")) {
                     break;
                  }

                  var9++;
               }

               cqj var11 = var10.Dw()[1];
               if (var11.isImmediate()) {
                  return var8 ? var11.getOperandValue() : var11.getOperandValue() + 1L;
               }

               ctc var12 = (ctc)var6.getInsntructions().get(var6.getInsntructions().size() - (var9 + 1));
               if (var12.getMnemonic().equalsIgnoreCase("mov")) {
                  cqj var13 = var12.Dw()[1];
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
   public Couple q(IBasicBlockSkeleton var1, long var2) {
      if (var1.getInsntructions().size() > 3) {
         for (int var4 = var1.getInsntructions().size() - 4; var4 >= 0; var4--) {
            ctc var5 = (ctc)var1.getInsntructions().get(var4);
            if (var5.Dw().length == 2 && var5.Dw()[0].isRegister() && var5.Dw()[1].RF()) {
               ctd var6 = (ctd)var5.Dw()[1];
               if (ctf.q(var5.Dw()[0].getOperandValue(), var2)
                  && var6.getMemoryScale() == 1
                  && var6.getMemoryBaseRegister() != -1L
                  && var6.getMemoryIndexRegister() != -1L
                  && var6.getMemoryDisplacement() != 0L) {
                  long var7 = this.Dw + var6.getMemoryDisplacement();
                  if (((aae)this.xK).getAnalysisRanges().contains(var7)) {
                     return new Couple(var7, var6.getMemoryIndexRegister());
                  }
               }
            }
         }
      }

      return null;
   }
}
