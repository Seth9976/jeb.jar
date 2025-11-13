package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IBasicBlockSkeleton;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.codeobject.CodeObjectUnitUtil;
import com.pnfsoftware.jeb.core.units.codeobject.ISegmentInformation;
import com.pnfsoftware.jeb.util.base.Couple;
import java.util.List;

public class cto extends ctk {
   private boolean za;

   public cto(INativeCodeAnalyzer var1, ctk.eo var2) {
      super(var1, var2);
   }

   private void q() {
      ISegmentInformation var1 = CodeObjectUnitUtil.findSectionByName(this.xK.getContainer(), ".got.plt");
      if (var1 != null) {
         this.Dw = this.Dw + var1.getOffsetInMemory();
      }

      this.oW = true;
   }

   @Override
   public boolean q(long var1, ctc var3, IBasicBlockSkeleton var4) {
      if (super.q(var1, var3, var4)) {
         return true;
      } else {
         List var5 = var4.getInsntructions();
         if (var5.size() >= 2 && var3.getMnemonic().equals("jmp") && var3.Dw()[0].isRegister()) {
            long var6 = var3.Dw()[0].getOperandValue();
            ctc var8 = (ctc)var5.get(var5.size() - 2);
            if (var8.getMnemonic().equals("mov") && var8.Dw()[0].isRegister() && var8.Dw()[0].getOperandValue() == var6 && var8.Dw()[1].RF()) {
               ctd var9 = (ctd)var8.Dw()[1];
               if (var9.getMemoryScale() == (this.Uv ? 8 : 4) && var9.getMemoryIndexRegister() != -1L && var9.getMemoryDisplacement() != 0L) {
                  this.gP.put(var1, new Couple(var9.getMemoryDisplacement(), var9.getMemoryIndexRegister()));
                  return true;
               }

               if (var9.getMemoryScale() == 0 && var9.getMemoryBaseRegister() != -1L && var9.getMemoryDisplacement() != 0L && var5.size() >= 3) {
                  ctc var12 = (ctc)var5.get(var5.size() - 3);
                  if (var12.getMnemonic().equalsIgnoreCase("shl")
                     && var12.Dw()[0].isRegister()
                     && var12.Dw()[0].getOperandValue() == var9.getMemoryBaseRegister()
                     && var12.Dw()[1].isImmediate()
                     && var12.Dw()[1].getOperandValue() == (this.Uv ? 4 : 2)) {
                     this.gP.put(var1, new Couple(var9.getMemoryDisplacement(), var9.getMemoryBaseRegister()));
                     return true;
                  }
               } else if (var9.getMemoryScale() == 0 && var9.getMemoryBaseRegister() != -1L && var9.getMemoryDisplacement() == 0L && var5.size() >= 4) {
                  ctc var10 = (ctc)var5.get(var5.size() - 3);
                  if (var10.getMnemonic().equalsIgnoreCase("add")
                     && var10.Dw()[0].isRegister()
                     && var10.Dw()[0].getOperandValue() == var9.getMemoryBaseRegister()
                     && var10.Dw()[1].isImmediate()) {
                     ctc var11 = (ctc)var5.get(var5.size() - 4);
                     if (var11.getMnemonic().equalsIgnoreCase("shl")
                        && var11.Dw()[0].isRegister()
                        && var11.Dw()[0].getOperandValue() == var9.getMemoryBaseRegister()
                        && var11.Dw()[1].isImmediate()
                        && var11.Dw()[1].getOperandValue() == (this.Uv ? 4 : 2)) {
                        this.gP.put(var1, new Couple(var10.Dw()[1].getOperandValue(), var9.getMemoryBaseRegister()));
                        return true;
                     }
                  }
               }
            }
         }

         if (this.RF(var1, var3, var4)) {
            return true;
         } else {
            return this.xK(var1, var3, var4) ? true : this.Dw(var1, var3, var4);
         }
      }
   }

   private boolean RF(long var1, ctc var3, IBasicBlockSkeleton var4) {
      List var5 = var4.getInsntructions();
      if (var5.size() < 3) {
         return false;
      } else {
         int var6 = var5.size() - 1;
         if (((ctc)var5.get(var6)).getMnemonic().equals("jmp")
            && (((ctc)var5.get(var6 - 1)).getMnemonic().equals("lea") || ((ctc)var5.get(var6 - 1)).getMnemonic().equals("add"))) {
            long var7 = var3.Dw()[0].getOperandValue();
            if (((ctc)var5.get(var6 - 1)).Dw()[0].getOperandValue() != var7) {
               return false;
            } else {
               boolean var9 = false;
               int var10 = 0;

               for (int var11 = var6 - 2; var11 >= 0; var11--) {
                  if (((ctc)var5.get(var11)).getMnemonic().equals("mov") && ((ctc)var5.get(var11)).Dw()[1].RF()) {
                     var9 = true;
                     var10 = var11;
                     break;
                  }
               }

               if (!var9) {
                  return false;
               } else {
                  ctd var12 = (ctd)((ctc)var5.get(var10)).Dw()[1];
                  if (var12.getMemoryScale() == 4 && var12.getMemoryIndexRegister() != -1L && var12.getMemoryDisplacement() != 0L) {
                     if (!this.za) {
                        this.za = true;
                        this.q();
                     }

                     this.gP.put(var1, new Couple(var12.getMemoryDisplacement() + this.Dw, var12.getMemoryIndexRegister()));
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

   private boolean xK(long var1, ctc var3, IBasicBlockSkeleton var4) {
      List var5 = var4.getInsntructions();
      if (var5.size() < 4) {
         return false;
      } else {
         int var6 = var5.size() - 1;
         if (((ctc)var5.get(var6)).getMnemonic().equals("jmp")
            && ((ctc)var5.get(var6 - 1)).getMnemonic().equals("add")
            && ((ctc)var5.get(var6 - 2)).getMnemonic().equals("movsxd")
            && ((ctc)var5.get(var6 - 2)).Dw()[1].RF()) {
            byte var7;
            if (((ctc)var5.get(var6 - 3)).getMnemonic().equals("lea")) {
               var7 = 3;
            } else if (var6 >= 4 && ((ctc)var5.get(var6 - 4)).getMnemonic().equals("lea")) {
               var7 = 4;
            } else {
               if (var6 < 5 || !((ctc)var5.get(var6 - 5)).getMnemonic().equals("lea")) {
                  return false;
               }

               var7 = 5;
            }

            long var8 = var3.Dw()[0].getOperandValue();
            if (((ctc)var5.get(var6 - 1)).Dw()[0].getOperandValue() == var8 && ((ctc)var5.get(var6 - 2)).Dw()[0].getOperandValue() == var8) {
               ctd var10 = (ctd)((ctc)var5.get(var6 - 2)).Dw()[1];
               if (var10.getMemoryScale() == 4 && var10.getMemoryIndexRegister() != -1L && var10.getMemoryDisplacement() == 0L) {
                  this.gO = true;
                  this.nf = true;
                  ctd var11 = (ctd)((ctc)var5.get(var6 - var7)).Dw()[1];
                  long var12 = ((kR)var4).q(var6 - var7 + 1);
                  long var14 = var11.getMemoryDisplacement() + var12;
                  this.gP.put(var1, new Couple(var14, var10.getMemoryIndexRegister()));
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

   private boolean Dw(long var1, ctc var3, IBasicBlockSkeleton var4) {
      List var5 = var4.getInsntructions();
      if (var5.size() != 8 && var5.size() != 7) {
         return false;
      } else {
         int var6 = var5.size() - 1;
         if (((ctc)var5.get(var6)).getMnemonic().equals("jmp")
            && ((ctc)var5.get(var6 - 1)).getMnemonic().equals("add")
            && ((ctc)var5.get(var6 - 2)).getMnemonic().equals("lea")
            && ((ctc)var5.get(var6 - 2)).Dw()[1].RF()
            && ((ctc)var5.get(var6 - 3)).getMnemonic().equals("cdqe")) {
            this.gO = true;
            this.nf = true;
            byte var7 = 2;
            ctd var8 = (ctd)((ctc)var5.get(var6 - var7)).Dw()[1];
            long var9 = ((kR)var4).q(var6 - var7 + 1);
            long var11 = var8.getMemoryDisplacement() + var9;
            this.gP.put(var1, new Couple(var11, 0L));
            return true;
         } else {
            return false;
         }
      }
   }

   @Override
   public long q(IBasicBlockSkeleton var1, List var2, long var3) {
      for (IBasicBlockSkeleton var6 : var2) {
         if (var6.getDstOffsets().contains(var1.getFirstAddress())) {
            if (var6.getInsntructions().size() >= 2) {
               ctc var7 = (ctc)var6.getInsntructions().get(var6.getInsntructions().size() - 1);
               if (var7.getMnemonic().equals("ja") || var7.getMnemonic().equals("jnbe") || var7.getMnemonic().equals("jbe")) {
                  ctc var8 = null;

                  for (int var9 = 2; var9 <= var6.getInsntructions().size(); var9++) {
                     ctc var10 = (ctc)var6.getInsntructions().get(var6.getInsntructions().size() - var9);
                     if (var10.getMnemonic().equals("cmp") && var10.Dw()[1].isImmediate() || var10.getMnemonic().equals("sub") && var10.Dw()[1].isImmediate()) {
                        var8 = var10;
                        break;
                     }
                  }

                  if (var8 != null) {
                     return var8.Dw()[1].getOperandValue() + 1L;
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
      return null;
   }
}
