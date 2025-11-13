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
public class MI extends com.pnfsoftware.jeb.corei.parsers.arm.converter.HE implements Sp {
   private static final ILogger xM = GlobalLog.getLogger(MI.class);
   @SerId(1)
   IEVar e;
   @SerTransient
   private IEVar kU;
   @SerTransient
   private IEGeneric Kq;

   public MI(IProcessor var1) {
      super(var1);
      this.wS.pC(false);
      this.e = this.gCtx.createVirtualRegister(69632, "Rsw", this.regNormalBitsize);
   }

   @Override
   public void wS() {
      aki var1 = new aki((ajo)this.gCtx, this.getProgramCounter().getId(), this.getStackPointer().getId());
      this.setCurrentContext(var1);
   }

   public void UT() {
      this.setCurrentContext(null);
   }

   @Override
   public void pC(Ro.K var1, long var2) {
      this.pC(var1.pC(), var2);
      this.pC(var1.pC, var2);
   }

   @Override
   public void pC(IEGeneric var1, long var2) {
      var1.replaceVar(this.pC(), this.ctx.createImm(var2, this.pC().getBitsize()));
   }

   @Override
   public IEGeneric A(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, long var2, IEGeneric var4) {
      IEGeneric[] var5 = new IEGeneric[]{var4};
      return this.pC(var1, var2, var5) ? var5[0] : null;
   }

   @Override
   public boolean pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, long var2, Ro.K var4) {
      IEGeneric[] var5 = new IEGeneric[]{var4.A(), var4.pC};
      if (!this.pC(var1, var2, var5)) {
         return false;
      } else {
         var4.pC(var5[0], var1);
         var4.pC = var5[1];
         return true;
      }
   }

   private boolean pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, long var2, IEGeneric... var4) {
      if (var1.fI()) {
         return true;
      } else {
         ArrayList var5 = new ArrayList();

         try {
            this.A(var1, var5, var2);
            if (this.regNormalBitsize == 64) {
               for (int var6 = 0; var6 < var5.size(); var6++) {
                  IEStatement var12 = (IEStatement)var5.get(var6);
                  if (var12.isAssign()) {
                     if (var12.asAssign().getRightOperand().equals(this.ys())) {
                        var5.set(var6, this.ctx.createAssign(var12.asAssign().getLeftOperand(), this.ld()));
                     } else {
                        var12.asAssign().getRightOperand().replaceVar(this.ys(), this.ld());
                     }
                  }
               }
            }
         } catch (Exception var11) {
            for (IEGeneric var10 : var4) {
               if (var10 == null) {
                  return false;
               }

               if (Td.pC(this, var1, var10)) {
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

   private IEVar ys() {
      if (this.kU == null) {
         this.kU = this.gCtx.getVar(A(33));
      }

      return this.kU;
   }

   private IEGeneric ld() {
      if (this.Kq == null) {
         this.Kq = EUtil.zero(this.ys().getBitsize());
      }

      return this.Kq;
   }

   @Override
   public IEGeneric A(IEGeneric var1) {
      ash var2 = new ash(this.ctx);
      IEGeneric var3 = var2.pC(var1);
      if (var3 != null) {
         var1 = var3;
      }

      return var1;
   }

   @Override
   public void pC(Ro.K var1, Yg var2) {
      pY var3 = this.pC(null, var2);
      if (var3.ld()) {
         var1.pC().replaceVar(var3.pC().asVar(), this.e);
         var1.pC.replaceVar(var3.pC().asVar(), this.e);
      }
   }

   @Override
   public void A(Ro.K var1, Yg var2) {
      IEGeneric var3 = this.pC(null, var2).A();
      var1.pC().replaceVar(this.e, var3);
      var1.pC.replaceVar(this.e, var3.duplicate());
   }

   @Override
   public IEGeneric E() {
      return this.rl;
   }

   @Override
   public IEVar sY() {
      return this.e;
   }

   @Override
   public IEGeneric kS(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, Yg var2, long var3) {
      switch (var2.getOperandType()) {
         case 0:
            return this.pC(var1, var2).A();
         default:
            return this.pC(var1, (IInstructionOperand)var2, var3);
      }
   }
}
