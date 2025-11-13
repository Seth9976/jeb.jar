package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.analyzer.INativeCodeAnalyzer;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.memory.IVirtualMemory;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.util.List;
import java.util.Map;

@SerDisabled
class jp implements BH {
   boolean q = false;
   private Vl RF;
   private INativeCodeAnalyzer xK;
   private FS Dw;
   private IVirtualMemory Uv;
   private AL oW;
   private bR gO;
   private IEGlobalContext nf;

   public jp(INativeCodeAnalyzer var1, FS var2, bR var3, AL var4) {
      this.xK = var1;
      this.Dw = var2;
      this.Uv = var1.getMemory();
      this.gO = var3;
      this.nf = var3.getGlobalContext();
      this.oW = var4;
   }

   @Override
   public boolean q(long var1, long var3, Map var5, Map var6) {
      if (var5 != null && !var5.isEmpty()) {
         List var7 = (List)var5.get(var3);
         LI var8 = new LI(var3, var5, var6);
         KV var9 = new KV(this.Uv, this.oW, this.gO, this.nf);
         this.RF = var9.q(var1, var7, var5, var8);
         if (this.RF == null) {
            return false;
         } else if (this.RF.q()) {
            return true;
         } else if (this.Dw != null && this.Dw.xK != null && this.Dw.xK != ctk.eo.xK) {
            return false;
         } else if (this.RF.RF().RF != null && EUtil.countVariableUse(this.RF.RF().RF) <= 1) {
            try {
               this.q = true;
               int var10 = ((fA)var7.get(0)).getProcessorMode();
               if (var10 != 64) {
                  fA var11 = (fA)var7.get(var7.size() - 1);
                  if (var11.q().q()) {
                     var10 = 0;
                  }
               }

               hu var13 = new hu(this.RF, this.xK, this.Dw, var10);
               return var13.q(var1, var3);
            } catch (Exception var12) {
               return false;
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   @Override
   public int q() {
      return this.RF.xK().q();
   }

   @Override
   public long RF() {
      return -1L;
   }

   @Override
   public int xK() {
      return -1;
   }

   @Override
   public Long q(int var1) {
      return this.RF.xK().q(this.Uv, var1);
   }

   @Override
   public int RF(int var1) {
      return this.RF.RF().q();
   }

   @Override
   public long xK(int var1) {
      return this.RF.xK().RF(this.Uv, var1);
   }

   @Override
   public boolean Dw() {
      return this.RF.RF().RF();
   }

   @Override
   public int Uv() {
      return this.RF.RF().xK();
   }

   @Override
   public Long q(int var1, int var2) {
      return this.RF.RF().q(this.Uv, var1, var2);
   }

   @Override
   public int RF(int var1, int var2) {
      return this.RF.RF().q(var1);
   }

   @Override
   public boolean oW() {
      return this.q;
   }
}
