package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IProcessor;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.ArrayList;

@Ser
public class CB extends uq implements bR {
   private static final ILogger RW = GlobalLog.getLogger(CB.class);
   @SerId(1)
   IEVar AN;
   @SerTransient
   private IEVar YR;
   @SerTransient
   private IEGeneric fN;

   public CB(IProcessor var1) {
      super(var1);
      this.es.q(false);
      this.AN = this.gCtx.createVirtualRegister(69632, "Rsw", this.regNormalBitsize);
   }

   @Override
   public void Dw() {
      aml var1 = new aml((alr)this.gCtx, this.getProgramCounter().getId(), this.getStackPointer().getId());
      this.setCurrentContext(var1);
   }

   public void Uv() {
      this.setCurrentContext(null);
   }

   @Override
   public void q(Ia.nI var1, long var2) {
      this.q(var1.q(), var2);
      this.q(var1.q, var2);
   }

   @Override
   public void q(IEGeneric var1, long var2) {
      var1.replaceVar(this.q(), this.ctx.createImm(var2, this.q().getBitsize()));
   }

   @Override
   public IEGeneric RF(fA var1, long var2, IEGeneric var4) {
      IEGeneric[] var5 = new IEGeneric[]{var4};
      return this.q(var1, var2, var5) ? var5[0] : null;
   }

   @Override
   public boolean q(fA var1, long var2, Ia.nI var4) {
      IEGeneric[] var5 = new IEGeneric[]{var4.RF(), var4.q};
      if (!this.q(var1, var2, var5)) {
         return false;
      } else {
         var4.q(var5[0], var1);
         var4.q = var5[1];
         return true;
      }
   }

   private boolean q(fA var1, long var2, IEGeneric... var4) {
      if (var1.zz()) {
         return true;
      } else {
         ArrayList var5 = new ArrayList();

         try {
            this.RF(var1, var5, var2);
            if (this.regNormalBitsize == 64) {
               for (int var6 = 0; var6 < var5.size(); var6++) {
                  IEStatement var12 = (IEStatement)var5.get(var6);
                  if (var12.isAssign()) {
                     if (var12.asAssign().getRightOperand().equals(this.nf())) {
                        var5.set(var6, this.ctx.createAssign(var12.asAssign().getLeftOperand(), this.gP()));
                     } else {
                        var12.asAssign().getRightOperand().replaceVar(this.nf(), this.gP());
                     }
                  }
               }
            }
         } catch (Exception var11) {
            for (IEGeneric var10 : var4) {
               if (var10 == null) {
                  return false;
               }

               if (tB.q(this, var1, var10)) {
                  Object[] var10000 = new Object[]{var1};
                  return false;
               }
            }

            return true;
         }

         if (var5.size() == 0) {
            return true;
         } else {
            return var4 == null ? false : EUtil.resolveExpressionsBackward(var1, this, var5, var4);
         }
      }
   }

   private IEVar nf() {
      if (this.YR == null) {
         this.YR = this.gCtx.getVar(xK(33));
      }

      return this.YR;
   }

   private IEGeneric gP() {
      if (this.fN == null) {
         this.fN = EUtil.zero(this.nf().getBitsize());
      }

      return this.fN;
   }

   @Override
   public IEGeneric RF(IEGeneric var1) {
      auy var2 = new auy(this.ctx);
      IEGeneric var3 = var2.q(var1);
      if (var3 != null) {
         var1 = var3;
      }

      return var1;
   }

   @Override
   public void q(Ia.nI var1, CW var2) {
      CZ var3 = this.q(null, var2);
      if (var3.gP()) {
         var1.q().replaceVar(var3.q().asVar(), this.AN);
         var1.q.replaceVar(var3.q().asVar(), this.AN);
      }
   }

   @Override
   public void RF(Ia.nI var1, CW var2) {
      IEGeneric var3 = this.q(null, var2).RF();
      var1.q().replaceVar(this.AN, var3);
      var1.q.replaceVar(this.AN, var3.duplicate());
   }

   @Override
   public IEGeneric oW() {
      return this.fe;
   }

   @Override
   public IEVar gO() {
      return this.AN;
   }

   @Override
   public IEGeneric xK(fA var1, CW var2, long var3) {
      switch (var2.getOperandType()) {
         case 0:
            return this.q(var1, var2).RF();
         default:
            return this.q(var1, (IInstructionOperand)var2, var3);
      }
   }
}
