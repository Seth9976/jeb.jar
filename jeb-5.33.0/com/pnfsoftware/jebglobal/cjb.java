package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IBasicBlockSkeleton;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.codeobject.CodeObjectUnitUtil;
import com.pnfsoftware.jeb.core.units.codeobject.ISegmentInformation;
import com.pnfsoftware.jeb.util.base.Couple;
import java.util.List;

public class cjb extends com.pnfsoftware.jeb.corei.parsers.x86.wn {
   private boolean gp;

   public cjb(INativeCodeAnalyzer var1, com.pnfsoftware.jeb.corei.parsers.x86.wn.Av var2) {
      super(var1, var2);
   }

   private void pC() {
      ISegmentInformation var1 = CodeObjectUnitUtil.findSectionByName(this.kS.getContainer(), ".got.plt");
      if (var1 != null) {
         this.wS = this.wS + var1.getOffsetInMemory();
      }

      this.E = true;
   }

   @Override
   public boolean pC(long var1, com.pnfsoftware.jeb.corei.parsers.x86.vh var3, IBasicBlockSkeleton var4) {
      if (super.pC(var1, var3, var4)) {
         return true;
      } else {
         List var5 = var4.getInsntructions();
         if (var5.size() >= 2 && var3.getMnemonic().equals("jmp") && var3.A()[0].isRegister()) {
            long var6 = var3.A()[0].getOperandValue();
            com.pnfsoftware.jeb.corei.parsers.x86.vh var8 = (com.pnfsoftware.jeb.corei.parsers.x86.vh)var5.get(var5.size() - 2);
            if (var8.getMnemonic().equals("mov") && var8.A()[0].isRegister() && var8.A()[0].getOperandValue() == var6 && var8.A()[1].A()) {
               com.pnfsoftware.jeb.corei.parsers.x86.QM var9 = (com.pnfsoftware.jeb.corei.parsers.x86.QM)var8.A()[1];
               if (var9.getMemoryScale() == (this.UT ? 8 : 4) && var9.getMemoryIndexRegister() != -1L && var9.getMemoryDisplacement() != 0L) {
                  this.ld.put(var1, new Couple(var9.getMemoryDisplacement(), var9.getMemoryIndexRegister()));
                  return true;
               }

               if (var9.getMemoryScale() == 0 && var9.getMemoryBaseRegister() != -1L && var9.getMemoryDisplacement() != 0L && var5.size() >= 3) {
                  com.pnfsoftware.jeb.corei.parsers.x86.vh var12 = (com.pnfsoftware.jeb.corei.parsers.x86.vh)var5.get(var5.size() - 3);
                  if (var12.getMnemonic().equalsIgnoreCase("shl")
                     && var12.A()[0].isRegister()
                     && var12.A()[0].getOperandValue() == var9.getMemoryBaseRegister()
                     && var12.A()[1].isImmediate()
                     && var12.A()[1].getOperandValue() == (this.UT ? 4 : 2)) {
                     this.ld.put(var1, new Couple(var9.getMemoryDisplacement(), var9.getMemoryBaseRegister()));
                     return true;
                  }
               } else if (var9.getMemoryScale() == 0 && var9.getMemoryBaseRegister() != -1L && var9.getMemoryDisplacement() == 0L && var5.size() >= 4) {
                  com.pnfsoftware.jeb.corei.parsers.x86.vh var10 = (com.pnfsoftware.jeb.corei.parsers.x86.vh)var5.get(var5.size() - 3);
                  if (var10.getMnemonic().equalsIgnoreCase("add")
                     && var10.A()[0].isRegister()
                     && var10.A()[0].getOperandValue() == var9.getMemoryBaseRegister()
                     && var10.A()[1].isImmediate()) {
                     com.pnfsoftware.jeb.corei.parsers.x86.vh var11 = (com.pnfsoftware.jeb.corei.parsers.x86.vh)var5.get(var5.size() - 4);
                     if (var11.getMnemonic().equalsIgnoreCase("shl")
                        && var11.A()[0].isRegister()
                        && var11.A()[0].getOperandValue() == var9.getMemoryBaseRegister()
                        && var11.A()[1].isImmediate()
                        && var11.A()[1].getOperandValue() == (this.UT ? 4 : 2)) {
                        this.ld.put(var1, new Couple(var10.A()[1].getOperandValue(), var9.getMemoryBaseRegister()));
                        return true;
                     }
                  }
               }
            }
         }

         if (this.A(var1, var3, var4)) {
            return true;
         } else {
            return this.kS(var1, var3, var4) ? true : this.wS(var1, var3, var4);
         }
      }
   }

   private boolean A(long var1, com.pnfsoftware.jeb.corei.parsers.x86.vh var3, IBasicBlockSkeleton var4) {
      List var5 = var4.getInsntructions();
      if (var5.size() < 3) {
         return false;
      } else {
         int var6 = var5.size() - 1;
         if (((com.pnfsoftware.jeb.corei.parsers.x86.vh)var5.get(var6)).getMnemonic().equals("jmp")
            && (
               ((com.pnfsoftware.jeb.corei.parsers.x86.vh)var5.get(var6 - 1)).getMnemonic().equals("lea")
                  || ((com.pnfsoftware.jeb.corei.parsers.x86.vh)var5.get(var6 - 1)).getMnemonic().equals("add")
            )) {
            long var7 = var3.A()[0].getOperandValue();
            if (((com.pnfsoftware.jeb.corei.parsers.x86.vh)var5.get(var6 - 1)).A()[0].getOperandValue() != var7) {
               return false;
            } else {
               boolean var9 = false;
               int var10 = 0;

               for (int var11 = var6 - 2; var11 >= 0; var11--) {
                  if (((com.pnfsoftware.jeb.corei.parsers.x86.vh)var5.get(var11)).getMnemonic().equals("mov")
                     && ((com.pnfsoftware.jeb.corei.parsers.x86.vh)var5.get(var11)).A()[1].A()) {
                     var9 = true;
                     var10 = var11;
                     break;
                  }
               }

               if (!var9) {
                  return false;
               } else {
                  com.pnfsoftware.jeb.corei.parsers.x86.QM var12 = (com.pnfsoftware.jeb.corei.parsers.x86.QM)((com.pnfsoftware.jeb.corei.parsers.x86.vh)var5.get(
                        var10
                     ))
                     .A()[1];
                  if (var12.getMemoryScale() == 4 && var12.getMemoryIndexRegister() != -1L && var12.getMemoryDisplacement() != 0L) {
                     if (!this.gp) {
                        this.gp = true;
                        this.pC();
                     }

                     this.ld.put(var1, new Couple(var12.getMemoryDisplacement() + this.wS, var12.getMemoryIndexRegister()));
                     return true;
                  } else {
                     return false;
                  }
               }
            }
         } else {
            return false;
         }
      }
   }

   private boolean kS(long var1, com.pnfsoftware.jeb.corei.parsers.x86.vh var3, IBasicBlockSkeleton var4) {
      List var5 = var4.getInsntructions();
      if (var5.size() < 4) {
         return false;
      } else {
         int var6 = var5.size() - 1;
         if (((com.pnfsoftware.jeb.corei.parsers.x86.vh)var5.get(var6)).getMnemonic().equals("jmp")
            && ((com.pnfsoftware.jeb.corei.parsers.x86.vh)var5.get(var6 - 1)).getMnemonic().equals("add")
            && ((com.pnfsoftware.jeb.corei.parsers.x86.vh)var5.get(var6 - 2)).getMnemonic().equals("movsxd")
            && ((com.pnfsoftware.jeb.corei.parsers.x86.vh)var5.get(var6 - 2)).A()[1].A()) {
            byte var7;
            if (((com.pnfsoftware.jeb.corei.parsers.x86.vh)var5.get(var6 - 3)).getMnemonic().equals("lea")) {
               var7 = 3;
            } else if (var6 >= 4 && ((com.pnfsoftware.jeb.corei.parsers.x86.vh)var5.get(var6 - 4)).getMnemonic().equals("lea")) {
               var7 = 4;
            } else {
               if (var6 < 5 || !((com.pnfsoftware.jeb.corei.parsers.x86.vh)var5.get(var6 - 5)).getMnemonic().equals("lea")) {
                  return false;
               }

               var7 = 5;
            }

            long var8 = var3.A()[0].getOperandValue();
            if (((com.pnfsoftware.jeb.corei.parsers.x86.vh)var5.get(var6 - 1)).A()[0].getOperandValue() == var8
               && ((com.pnfsoftware.jeb.corei.parsers.x86.vh)var5.get(var6 - 2)).A()[0].getOperandValue() == var8) {
               com.pnfsoftware.jeb.corei.parsers.x86.QM var10 = (com.pnfsoftware.jeb.corei.parsers.x86.QM)((com.pnfsoftware.jeb.corei.parsers.x86.vh)var5.get(
                     var6 - 2
                  ))
                  .A()[1];
               if (var10.getMemoryScale() == 4 && var10.getMemoryIndexRegister() != -1L && var10.getMemoryDisplacement() == 0L) {
                  this.sY = true;
                  this.ys = true;
                  com.pnfsoftware.jeb.corei.parsers.x86.QM var11 = (com.pnfsoftware.jeb.corei.parsers.x86.QM)((com.pnfsoftware.jeb.corei.parsers.x86.vh)var5.get(
                        var6 - var7
                     ))
                     .A()[1];
                  long var12 = ((ON)var4).pC(var6 - var7 + 1);
                  long var14 = var11.getMemoryDisplacement() + var12;
                  this.ld.put(var1, new Couple(var14, var10.getMemoryIndexRegister()));
                  return true;
               } else {
                  return false;
               }
            } else {
               return false;
            }
         } else {
            return false;
         }
      }
   }

   private boolean wS(long var1, com.pnfsoftware.jeb.corei.parsers.x86.vh var3, IBasicBlockSkeleton var4) {
      List var5 = var4.getInsntructions();
      if (var5.size() != 8 && var5.size() != 7) {
         return false;
      } else {
         int var6 = var5.size() - 1;
         if (((com.pnfsoftware.jeb.corei.parsers.x86.vh)var5.get(var6)).getMnemonic().equals("jmp")
            && ((com.pnfsoftware.jeb.corei.parsers.x86.vh)var5.get(var6 - 1)).getMnemonic().equals("add")
            && ((com.pnfsoftware.jeb.corei.parsers.x86.vh)var5.get(var6 - 2)).getMnemonic().equals("lea")
            && ((com.pnfsoftware.jeb.corei.parsers.x86.vh)var5.get(var6 - 2)).A()[1].A()
            && ((com.pnfsoftware.jeb.corei.parsers.x86.vh)var5.get(var6 - 3)).getMnemonic().equals("cdqe")) {
            this.sY = true;
            this.ys = true;
            byte var7 = 2;
            com.pnfsoftware.jeb.corei.parsers.x86.QM var8 = (com.pnfsoftware.jeb.corei.parsers.x86.QM)((com.pnfsoftware.jeb.corei.parsers.x86.vh)var5.get(
                  var6 - var7
               ))
               .A()[1];
            long var9 = ((ON)var4).pC(var6 - var7 + 1);
            long var11 = var8.getMemoryDisplacement() + var9;
            this.ld.put(var1, new Couple(var11, 0L));
            return true;
         } else {
            return false;
         }
      }
   }

   @Override
   public long pC(IBasicBlockSkeleton var1, List var2, long var3) {
      for (IBasicBlockSkeleton var6 : var2) {
         if (var6.getDstOffsets().contains(var1.getFirstAddress())) {
            if (var6.getInsntructions().size() >= 2) {
               com.pnfsoftware.jeb.corei.parsers.x86.vh var7 = (com.pnfsoftware.jeb.corei.parsers.x86.vh)var6.getInsntructions()
                  .get(var6.getInsntructions().size() - 1);
               if (var7.getMnemonic().equals("ja") || var7.getMnemonic().equals("jnbe") || var7.getMnemonic().equals("jbe")) {
                  com.pnfsoftware.jeb.corei.parsers.x86.vh var8 = null;

                  for (int var9 = 2; var9 <= var6.getInsntructions().size(); var9++) {
                     com.pnfsoftware.jeb.corei.parsers.x86.vh var10 = (com.pnfsoftware.jeb.corei.parsers.x86.vh)var6.getInsntructions()
                        .get(var6.getInsntructions().size() - var9);
                     if (var10.getMnemonic().equals("cmp") && var10.A()[1].isImmediate() || var10.getMnemonic().equals("sub") && var10.A()[1].isImmediate()) {
                        var8 = var10;
                        break;
                     }
                  }

                  if (var8 != null) {
                     return var8.A()[1].getOperandValue() + 1L;
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
      return null;
   }
}
