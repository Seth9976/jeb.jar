package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.IllegalConversionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import java.util.List;

public class IP {
   uq q;
   IERoutineContext RF;

   public IP(uq var1) {
      this.q = var1;
   }

   public void q(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, OperationType.ADD, false);
   }

   public void RF(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, OperationType.SUB, false);
   }

   public void xK(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, OperationType.ADD, true);
   }

   public void Dw(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, OperationType.SUB, true);
   }

   public void q(fA var1, List var2, long var3, OperationType var5, boolean var6) {
      CZ var7 = this.q.q(var1, var1.RF()[0]);
      IEGeneric var8;
      IEGeneric var9;
      if (var1.RF().length > 2) {
         var8 = this.q.q(var1, (IInstructionOperand)var1.RF()[1], var3);
         var9 = this.q.q(var1, var1.RF()[2], var8.getBitsize(), var3);
      } else {
         var8 = this.q.q(var1, (IInstructionOperand)var1.RF()[0], var3);
         var9 = this.q.q(var1, var1.RF()[1], var8.getBitsize(), var3);
      }

      IEGeneric var10 = var9;
      IEGeneric var11 = null;
      if (var6) {
         ii var12 = this.RF(var9.signExtend(64).leftShift(1), 32);
         var10 = var12.q();
         var11 = var12.RF().duplicate();
      }

      IEGeneric var16 = var8.signExtend(64);
      IEGeneric var13 = var10.signExtend(64);
      ii var14 = this.RF(this.RF.createOperation(var5, var16, var13), 32);
      Object var15 = var14.RF();
      if (var11 != null) {
         var15 = EUtil.orL((IEGeneric)var15, var11);
      }

      var2.add(this.RF.createAssign(this.q.Kl, this.RF.createCond((IEGeneric)var15, this.q.bj, this.q.Kl)));
      this.q.q(var1, var2, var7, var14.q());
   }

   public void Uv(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, true);
   }

   public void oW(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, false);
   }

   public void q(fA var1, List var2, long var3, boolean var5) {
      CZ var6 = this.q.q(var1, var1.RF()[0]);
      int var7 = (int)var1.RF()[1].getOperandValue();
      IEGeneric var8 = this.q.q(var1, (IInstructionOperand)var1.RF()[2], var3);
      ii var9 = this.q(var8, var7, var5);
      IEGeneric var10 = var9.RF();
      var2.add(this.RF.createAssign(this.q.Kl, this.RF.createCond(var10, this.q.bj, this.q.Kl)));
      this.q.q(var1, var2, var6, EUtil.extend(var9.q(), this.q.JF, var5));
   }

   public void gO(fA var1, List var2, long var3) {
      this.RF(var1, var2, var3, true);
   }

   public void nf(fA var1, List var2, long var3) {
      this.RF(var1, var2, var3, false);
   }

   public void RF(fA var1, List var2, long var3, boolean var5) {
      CZ var6 = this.q.q(var1, var1.RF()[0]);
      int var7 = (int)var1.RF()[1].getOperandValue();
      IEGeneric var8 = this.q.q(var1, (IInstructionOperand)var1.RF()[2], var3);
      ii var9 = this.q(var8.slice(0, 16), var7, var5);
      ii var10 = this.q(var8.slice(16, 32), var7, var5);
      IEOperation var11 = EUtil.orL(var9.RF(), var10.RF());
      var2.add(this.RF.createAssign(this.q.Kl, this.RF.createCond(var11, this.q.bj, this.q.Kl)));
      IEGeneric var12 = EUtil.extend(var9.q(), 16, var5);
      IEGeneric var13 = EUtil.extend(var10.q(), 16, var5);
      this.q.q(var1, var2, var6, this.RF.createCompose(var12, var13));
   }

   public void gP(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, IP.CU.q, 16, true);
   }

   public void za(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, IP.CU.q, 8, true);
   }

   public void lm(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, IP.CU.RF, 16, true);
   }

   public void zz(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, IP.CU.RF, 8, true);
   }

   public void JY(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, IP.CU.xK, 16, true);
   }

   public void HF(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, IP.CU.Dw, 16, true);
   }

   public void LK(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, IP.CU.q, 16, false);
   }

   public void io(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, IP.CU.q, 8, false);
   }

   public void qa(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, IP.CU.RF, 16, false);
   }

   public void Hk(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, IP.CU.RF, 8, false);
   }

   public void Me(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, IP.CU.xK, 16, false);
   }

   public void PV(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, IP.CU.Dw, 16, false);
   }

   private void q(fA var1, List var2, long var3, IP.CU var5, int var6, boolean var7) {
      CZ var8 = this.q.q(var1, var1.RF()[0]);
      IEGeneric var9;
      IEGeneric var10;
      if (var1.RF().length > 2) {
         var10 = this.q.q(var1, (IInstructionOperand)var1.RF()[1], var3);
         var9 = this.q.q(var1, var1.RF()[2], var10.getBitsize(), var3);
      } else {
         var10 = this.q.q(var1, (IInstructionOperand)var1.RF()[0], var3);
         var9 = this.q.q(var1, var1.RF()[1], var10.getBitsize(), var3);
      }

      IEGeneric[] var11 = this.q(var5, var10, var9, var6, var7);
      this.q.q(var1, var2, var8, EUtil.compose(this.RF, var11));
   }

   public void oQ(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, IP.CU.q, 16);
   }

   public void xW(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, IP.CU.q, 8);
   }

   public void KT(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, IP.CU.RF, 16);
   }

   public void Gf(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, IP.CU.RF, 8);
   }

   public void Ef(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, IP.CU.xK, 16);
   }

   public void cC(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, IP.CU.Dw, 16);
   }

   private void q(fA var1, List var2, long var3, IP.CU var5, int var6) {
      CZ var7 = this.q.q(var1, var1.RF()[0]);
      IEGeneric var8;
      IEGeneric var9;
      if (var1.RF().length > 2) {
         var9 = this.q.q(var1, (IInstructionOperand)var1.RF()[1], var3);
         var8 = this.q.q(var1, var1.RF()[2], var9.getBitsize(), var3);
      } else {
         var9 = this.q.q(var1, (IInstructionOperand)var1.RF()[0], var3);
         var8 = this.q.q(var1, var1.RF()[1], var9.getBitsize(), var3);
      }

      IEGeneric[] var10 = this.RF(var5, var9, var8, var6, true);
      IEImm var11 = this.q.Uv(var10[0].getBitsize());
      IEImm var12 = this.RF.createImm((var6 >>> 2) - 1, var6 >>> 3);
      IEImm var13 = this.q.Uv(var6 >>> 3);
      IEGeneric[] var14 = new IEGeneric[var10.length];

      for (int var15 = 0; var15 < var10.length; var15++) {
         IEOperation var16 = EUtil.geS(var10[var15], var11);
         var14[var15] = this.RF.createCond(var16, var12, var13);
      }

      var2.add(this.RF.createAssign(this.q.So, EUtil.compose(this.RF, var14)));

      for (int var17 = 0; var17 < var10.length; var17++) {
         var10[var17] = var10[var17].part(var6);
      }

      this.q.q(var1, var2, var7, EUtil.compose(this.RF, var10));
   }

   public void sH(fA var1, List var2, long var3) {
      this.RF(var1, var2, var3, IP.CU.q, 16);
   }

   public void CE(fA var1, List var2, long var3) {
      this.RF(var1, var2, var3, IP.CU.q, 8);
   }

   public void wF(fA var1, List var2, long var3) {
      this.RF(var1, var2, var3, IP.CU.RF, 16);
   }

   public void If(fA var1, List var2, long var3) {
      this.RF(var1, var2, var3, IP.CU.RF, 8);
   }

   public void Dz(fA var1, List var2, long var3) {
      this.RF(var1, var2, var3, IP.CU.xK, 16);
   }

   public void mI(fA var1, List var2, long var3) {
      this.RF(var1, var2, var3, IP.CU.Dw, 16);
   }

   private void RF(fA var1, List var2, long var3, IP.CU var5, int var6) {
      CZ var7 = this.q.q(var1, var1.RF()[0]);
      IEGeneric var8;
      IEGeneric var9;
      if (var1.RF().length > 2) {
         var9 = this.q.q(var1, (IInstructionOperand)var1.RF()[1], var3);
         var8 = this.q.q(var1, var1.RF()[2], var9.getBitsize(), var3);
      } else {
         var9 = this.q.q(var1, (IInstructionOperand)var1.RF()[0], var3);
         var8 = this.q.q(var1, var1.RF()[1], var9.getBitsize(), var3);
      }

      IEGeneric[] var10 = this.RF(var5, var9, var8, var6, false);
      IEImm var11 = this.RF.createImm(1 << var6, var10[0].getBitsize());
      IEImm var12 = this.q.Uv(var10[0].getBitsize());
      IEImm var13 = this.RF.createImm((var6 >>> 2) - 1, var6 >>> 3);
      IEImm var14 = this.q.Uv(var6 >>> 3);
      IEGeneric[] var15 = new IEGeneric[var10.length];
      switch (var5) {
         case xK:
            var15[0] = this.RF.createCond(EUtil.geS(var10[0], var12), var13, var14);
            var15[1] = this.RF.createCond(EUtil.geS(var10[1], var11), var13, var14);
            break;
         case Dw:
            var15[0] = this.RF.createCond(EUtil.geS(var10[0], var11), var13, var14);
            var15[1] = this.RF.createCond(EUtil.geS(var10[1], var12), var13, var14);
            break;
         default:
            for (int var16 = 0; var16 < var10.length; var16++) {
               IEOperation var17 = EUtil.geS(var10[var16], var5 == IP.CU.q ? var11 : var12);
               var15[var16] = this.RF.createCond(var17, var13, var14);
            }
      }

      var2.add(this.RF.createAssign(this.q.So, EUtil.compose(this.RF, var15)));

      for (int var18 = 0; var18 < var10.length; var18++) {
         var10[var18] = var10[var18].part(var6);
      }

      this.q.q(var1, var2, var7, EUtil.compose(this.RF, var10));
   }

   public void jq(fA var1, List var2, long var3) {
      this.RF(var1, var2, var3, IP.CU.q, 16, true);
   }

   public void ui(fA var1, List var2, long var3) {
      this.RF(var1, var2, var3, IP.CU.q, 8, true);
   }

   public void TX(fA var1, List var2, long var3) {
      this.RF(var1, var2, var3, IP.CU.RF, 16, true);
   }

   public void Rr(fA var1, List var2, long var3) {
      this.RF(var1, var2, var3, IP.CU.RF, 8, true);
   }

   public void EB(fA var1, List var2, long var3) {
      this.RF(var1, var2, var3, IP.CU.xK, 16, true);
   }

   public void Xo(fA var1, List var2, long var3) {
      this.RF(var1, var2, var3, IP.CU.Dw, 16, true);
   }

   public void Bu(fA var1, List var2, long var3) {
      this.RF(var1, var2, var3, IP.CU.q, 16, false);
   }

   public void IN(fA var1, List var2, long var3) {
      this.RF(var1, var2, var3, IP.CU.q, 8, false);
   }

   public void rL(fA var1, List var2, long var3) {
      this.RF(var1, var2, var3, IP.CU.RF, 16, false);
   }

   public void eJ(fA var1, List var2, long var3) {
      this.RF(var1, var2, var3, IP.CU.RF, 8, false);
   }

   public void YN(fA var1, List var2, long var3) {
      this.RF(var1, var2, var3, IP.CU.xK, 16, false);
   }

   public void Rv(fA var1, List var2, long var3) {
      this.RF(var1, var2, var3, IP.CU.Dw, 16, false);
   }

   private void RF(fA var1, List var2, long var3, IP.CU var5, int var6, boolean var7) {
      CZ var8 = this.q.q(var1, var1.RF()[0]);
      IEGeneric var9;
      IEGeneric var10;
      if (var1.RF().length > 2) {
         var10 = this.q.q(var1, (IInstructionOperand)var1.RF()[1], var3);
         var9 = this.q.q(var1, var1.RF()[2], var10.getBitsize(), var3);
      } else {
         var10 = this.q.q(var1, (IInstructionOperand)var1.RF()[0], var3);
         var9 = this.q.q(var1, var1.RF()[1], var10.getBitsize(), var3);
      }

      IEGeneric[] var11 = this.RF(var5, var10, var9, var6, var7);

      for (int var12 = 0; var12 < var11.length; var12++) {
         var11[var12] = var11[var12].slice(1, var6 + 1);
      }

      this.q.q(var1, var2, var8, EUtil.compose(this.RF, var11));
   }

   public IEGeneric[] q(IP.CU var1, IEGeneric var2, IEGeneric var3, int var4, boolean var5) {
      IEGeneric[] var6 = this.RF(var1, var2, var3, var4, var5);
      if (var6 != null) {
         for (int var7 = 0; var7 < var6.length; var7++) {
            var6[var7] = var5 ? this.q(var6[var7], var4) : this.xK(var6[var7], var4);
         }
      }

      return var6;
   }

   public IEGeneric[] RF(IP.CU var1, IEGeneric var2, IEGeneric var3, int var4, boolean var5) {
      switch (var1) {
         case xK:
            return new IEGeneric[]{
               EUtil.sub(EUtil.extend(var2.slice(0, 16), 32, var5), EUtil.extend(var3.slice(16, 32), 32, var5)),
               EUtil.add(EUtil.extend(var2.slice(16, 32), 32, var5), EUtil.extend(var3.slice(0, 16), 32, var5))
            };
         case Dw:
            return new IEGeneric[]{
               EUtil.add(EUtil.extend(var2.slice(0, 16), 32, var5), EUtil.extend(var3.slice(16, 32), 32, var5)),
               EUtil.sub(EUtil.extend(var2.slice(16, 32), 32, var5), EUtil.extend(var3.slice(0, 16), 32, var5))
            };
         case q:
         case RF:
            int var6 = 32 / var4;
            IEGeneric[] var7 = new IEGeneric[var6];
            OperationType var8 = var1 == IP.CU.q ? OperationType.ADD : OperationType.SUB;

            for (int var9 = 0; var9 < var6; var9++) {
               var7[var9] = this.RF
                  .createOperation(
                     var8,
                     EUtil.extend(var2.slice(var9 * var4, var9 * var4 + var4), 32, var5),
                     EUtil.extend(var3.slice(var9 * var4, var9 * var4 + var4), 32, var5)
                  );
            }

            return var7;
         default:
            return null;
      }
   }

   private ii q(IEGeneric var1, int var2, boolean var3) {
      return var3 ? this.RF(var1, var2) : this.Dw(var1, var2);
   }

   private IEGeneric q(IEGeneric var1, int var2) {
      return this.RF(var1, var2).q();
   }

   private ii RF(IEGeneric var1, int var2) {
      if (var2 <= 32 && var2 > 0) {
         IEImm var3 = this.RF.createImm((1L << var2 - 1) - 1L, var1.getBitsize());
         IEImm var4 = this.RF.createImm(-(1L << var2 - 1), var1.getBitsize());
         IEOperation var5 = EUtil.gtS(var1, var3);
         IEOperation var6 = EUtil.ltS(var1.duplicate(), var4);
         IECond var7 = this.RF.createCond(var6, var4, var1.duplicate());
         IEGeneric var8 = this.RF.createCond(var5, var3, var7).part(var2);
         IEOperation var9 = EUtil.orL(var5, var6);
         return new ii(var8, var9);
      } else {
         throw new IllegalConversionException("Allowed value for saturate_to are in range [1,32]");
      }
   }

   private IEGeneric xK(IEGeneric var1, int var2) {
      return this.Dw(var1, var2).q();
   }

   private ii Dw(IEGeneric var1, int var2) {
      if (var2 <= 32 && var2 >= 0) {
         IEImm var3 = this.RF.createImm((1L << var2) - 1L, var1.getBitsize());
         IEImm var4 = this.q.Uv(var1.getBitsize());
         if (var2 == 0) {
            IEOperation var10 = EUtil.ne(var1, var4);
            return new ii(this.q.SM, var10);
         } else {
            IEOperation var5 = EUtil.gtS(var1, var3);
            IEOperation var6 = EUtil.ltS(var1.duplicate(), var4);
            IEOperation var7 = EUtil.orL(var5, var6);
            IECond var8 = this.RF.createCond(var6, var4, var1.duplicate());
            IEGeneric var9 = this.RF.createCond(var5, var3, var8).part(var2);
            return new ii(var9, var7);
         }
      } else {
         throw new IllegalConversionException("Allowed value for saturate_to are in range [0,32]");
      }
   }

   public void zx(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, 8);
   }

   private void q(fA var1, List var2, long var3, int var5) {
      CZ var6 = this.q.q(var1, var1.RF()[0]);
      IEGeneric var7;
      IEGeneric var8;
      if (var1.RF().length > 2) {
         var8 = this.q.q(var1, (IInstructionOperand)var1.RF()[1], var3);
         var7 = this.q.q(var1, var1.RF()[2], var8.getBitsize(), var3);
      } else {
         var8 = this.q.q(var1, (IInstructionOperand)var1.RF()[0], var3);
         var7 = this.q.q(var1, var1.RF()[1], var8.getBitsize(), var3);
      }

      IEGeneric var9 = this.xK(IP.CU.RF, var8, var7, var5, false);
      this.q.q(var1, var2, var6, var9);
   }

   public void ZT(fA var1, List var2, long var3) {
      this.RF(var1, var2, var3, 8);
   }

   private void RF(fA var1, List var2, long var3, int var5) {
      CZ var6 = this.q.q(var1, var1.RF()[0]);
      IEGeneric var8 = this.q.q(var1, (IInstructionOperand)var1.RF()[1], var3);
      IEGeneric var7 = this.q.q(var1, var1.RF()[2], var8.getBitsize(), var3);
      IEGeneric var9 = this.q.q(var1, var1.RF()[3], var8.getBitsize(), var3);
      IEGeneric var10 = this.xK(IP.CU.RF, var8, var7, var5, false);
      this.q.q(var1, var2, var6, EUtil.add(var9, var10));
   }

   public IEGeneric xK(IP.CU var1, IEGeneric var2, IEGeneric var3, int var4, boolean var5) {
      IEGeneric[] var6 = this.RF(var1, var2, var3, var4, var5);
      IEGeneric var7 = null;
      if (var6 != null) {
         IEImm var8 = this.q.Uv(var6[0].getBitsize());

         for (int var9 = 0; var9 < var6.length; var9++) {
            var6[var9] = this.RF.createCond(EUtil.geS(var6[var9], var8), var6[var9].duplicate(), EUtil.sub(var8, var6[var9].duplicate()));
            var7 = Lb.q(var7, var6[var9]);
         }
      }

      return var7;
   }

   public void Ri(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, Lb.oM.q, true);
   }

   public void GY(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, Lb.oM.RF, true);
   }

   public void Wx(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, Lb.oM.xK, true);
   }

   public void AB(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, Lb.oM.q, false);
   }

   public void CY(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, Lb.oM.RF, false);
   }

   public void WI(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, Lb.oM.xK, false);
   }

   private void q(fA var1, List var2, long var3, Lb.oM var5, boolean var6) {
      CW var7 = var1.RF()[0];
      CZ var8 = this.q.q(var1, var1.RF()[0]);
      IEGeneric var9;
      IEGeneric var10;
      if (var1.RF().length > 2) {
         var10 = this.q.q(var1, (IInstructionOperand)var1.RF()[1], var3);
         var9 = this.q.q(var1, var1.RF()[2], var10.getBitsize(), var3);
      } else {
         var10 = this.q.q(var1, (IInstructionOperand)var1.RF()[0], var3);
         var9 = this.q.q(var1, var1.RF()[1], var10.getBitsize(), var3);
      }

      IEGeneric var11 = Lb.q(this.RF, var10, var9, var5, var6, var7.getOperandBitsize());
      this.q.q(var1, var2, var8, var11);
   }

   public void Tq(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, false, false);
   }

   public void Yp(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, false, true);
   }

   public void Gu(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, true, false);
   }

   public void nY(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, true, true);
   }

   public void lF(fA var1, List var2, long var3) {
      this.RF(var1, var2, var3, false, false);
   }

   public void nq(fA var1, List var2, long var3) {
      this.RF(var1, var2, var3, false, true);
   }

   public void NX(fA var1, List var2, long var3) {
      this.RF(var1, var2, var3, true, false);
   }

   public void br(fA var1, List var2, long var3) {
      this.RF(var1, var2, var3, true, true);
   }

   public void tW(fA var1, List var2, long var3) {
      this.xK(var1, var2, var3, false, false);
   }

   public void ZA(fA var1, List var2, long var3) {
      this.xK(var1, var2, var3, false, true);
   }

   public void Ov(fA var1, List var2, long var3) {
      this.xK(var1, var2, var3, true, false);
   }

   public void Lj(fA var1, List var2, long var3) {
      this.xK(var1, var2, var3, true, true);
   }

   private void q(fA var1, List var2, long var3, boolean var5, boolean var6) {
      CZ var7 = this.q.q(var1, var1.RF()[0]);
      IEGeneric var8 = this.q.q(var1, (IInstructionOperand)var1.RF()[1], var3);
      IEGeneric var9 = this.q.q(var1, var1.RF()[2], var8.getBitsize(), var3);
      IEGeneric var10 = this.q.q(var1, var1.RF()[3], var8.getBitsize(), var3);
      IEGeneric var11 = var5 ? var8.slice(16, 32) : var8.part(16);
      IEGeneric var12 = var6 ? var9.slice(16, 32) : var9.part(16);
      IEOperation var13 = EUtil.mul(var11.signExtend(this.q.JF), var12.signExtend(this.q.JF));
      IEOperation var14 = EUtil.add(var13.signExtend(this.q.JF + 1), var10.signExtend(this.q.JF + 1));
      IEOperation var15 = EUtil.ne(var14, var14.duplicate().part(this.q.JF).signExtend(this.q.JF + 1));
      var2.add(this.RF.createAssign(this.q.Kl, this.RF.createCond(var15, this.q.bj, this.q.Kl)));
      this.q.q(var1, var2, var7, var14.part(this.q.JF));
   }

   private void RF(fA var1, List var2, long var3, boolean var5, boolean var6) {
      CZ var7 = this.q.q(var1, var1.RF()[0]);
      IEGeneric var8;
      IEGeneric var9;
      if (var1.RF().length > 2) {
         var9 = this.q.q(var1, (IInstructionOperand)var1.RF()[1], var3);
         var8 = this.q.q(var1, var1.RF()[2], var9.getBitsize(), var3);
      } else {
         var9 = this.q.q(var1, (IInstructionOperand)var1.RF()[0], var3);
         var8 = this.q.q(var1, var1.RF()[1], var9.getBitsize(), var3);
      }

      IEGeneric var10 = var5 ? var9.slice(16, 32) : var9.part(16);
      IEGeneric var11 = var6 ? var8.slice(16, 32) : var8.part(16);
      IEOperation var12 = EUtil.mul(var10.signExtend(this.q.JF), var11.signExtend(this.q.JF));
      this.q.q(var1, var2, var7, var12.part(this.q.JF));
   }

   private void xK(fA var1, List var2, long var3, boolean var5, boolean var6) {
      CZ var7 = this.q.q(var1, var1.RF()[0]);
      CZ var8 = this.q.q(var1, var1.RF()[1]);
      IEGeneric var9 = this.q.q(var1, (IInstructionOperand)var1.RF()[2], var3);
      IEGeneric var10 = this.q.q(var1, var1.RF()[3], var9.getBitsize(), var3);
      IEGeneric var11 = var5 ? var9.slice(16, 32) : var9.part(16);
      IEGeneric var12 = var6 ? var10.slice(16, 32) : var10.part(16);
      IEOperation var13 = EUtil.mul(var11.signExtend(64), var12.signExtend(64));
      IEOperation var14 = EUtil.add(var13, this.RF.createCompose(var7.RF(), var8.RF()));
      IEVar var15 = this.q.gO(64);
      var2.add(this.RF.createAssign(var15, var14));
      this.q.q(var1, var2, var8, var15.slice(this.q.JF, this.q.JF * 2));
      this.q.q(var1, var2, var7, var15.part(this.q.JF));
   }

   public void nv(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, true, IP.eo.RF);
   }

   public void LL(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, true, IP.eo.Dw);
   }

   public void PQ(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, true, IP.eo.q);
   }

   private void q(fA var1, List var2, long var3, boolean var5, IP.eo var6) {
      CZ var7 = this.q.q(var1, var1.RF()[0]);
      IEGeneric var8;
      IEGeneric var9;
      if (var1.RF().length > 2) {
         var8 = this.q.q(var1, (IInstructionOperand)var1.RF()[1], var3);
         var9 = this.q.q(var1, var1.RF()[2], var8.getBitsize(), var3);
      } else {
         var8 = this.q.q(var1, (IInstructionOperand)var1.RF()[0], var3);
         var9 = this.q.q(var1, var1.RF()[1], var8.getBitsize(), var3);
      }

      IEImm var10 = null;
      IEGeneric var11 = null;
      if (var1.RF().length == 4) {
         var10 = this.q.Uv(this.q.JF);
         var11 = this.q.q(var1, var1.RF()[3], var10.getBitsize(), var3);
      }

      Object var12 = this.q(var6, var5, var10, var11, var8, var9);
      if (var1.Dw().q().endsWith("R")) {
         var12 = EUtil.add((IEGeneric)var12, this.RF.createImm(2147483648L, 64));
      }

      this.q.q(var1, var2, var7, ((IEGeneric)var12).slice(this.q.JF, 64));
   }

   public void fQ(fA var1, List var2, long var3) {
      this.RF(var1, var2, var3, false, IP.eo.xK);
   }

   public void fi(fA var1, List var2, long var3) {
      this.RF(var1, var2, var3, false, IP.eo.RF);
   }

   public void bl(fA var1, List var2, long var3) {
      this.RF(var1, var2, var3, true, IP.eo.RF);
   }

   public void jb(fA var1, List var2, long var3) {
      this.RF(var1, var2, var3, false, IP.eo.q);
   }

   public void pQ(fA var1, List var2, long var3) {
      this.RF(var1, var2, var3, true, IP.eo.q);
   }

   private void RF(fA var1, List var2, long var3, boolean var5, IP.eo var6) {
      CZ var7 = this.q.q(var1, var1.RF()[0]);
      CZ var8 = this.q.q(var1, var1.RF()[1]);
      IEGeneric var9 = this.q.q(var1, (IInstructionOperand)var1.RF()[2], var3);
      IEGeneric var10 = this.q.q(var1, var1.RF()[3], var9.getBitsize(), var3);
      IEGeneric var11 = this.q(var6, var5, var7.RF(), var8.RF(), var9, var10);
      IEVar var12 = this.q.gO(64);
      var2.add(this.RF.createAssign(var12, var11));
      if (var1.Dw().RF()) {
         this.q.Of.q(var11, var2);
      }

      this.q.q(var1, var2, var8, var12.slice(this.q.JF, this.q.JF * 2));
      this.q.q(var1, var2, var7, var12.part(this.q.JF));
   }

   public void kf(fA var1, List var2, long var3) {
      this.xK(var1, var2, var3, false);
   }

   public void GM(fA var1, List var2, long var3) {
      this.xK(var1, var2, var3, true);
   }

   private void xK(fA var1, List var2, long var3, boolean var5) {
      CZ var6 = this.q.q(var1, var1.RF()[0]);
      IEGeneric var7;
      IEGeneric var8;
      if (var1.RF().length > 2) {
         var7 = this.q.q(var1, (IInstructionOperand)var1.RF()[1], var3);
         var8 = this.q.q(var1, var1.RF()[2], var7.getBitsize(), var3);
      } else {
         var7 = this.q.q(var1, (IInstructionOperand)var1.RF()[0], var3);
         var8 = this.q.q(var1, var1.RF()[1], var7.getBitsize(), var3);
      }

      boolean var9 = var1.Dw().q().endsWith("T");
      IEGeneric var10 = var9 ? var8.slice(16, 32) : var8.part(16);
      Object var11 = this.q(IP.eo.q, true, null, null, var7, var10);
      if (var5 && var1.RF().length == 4) {
         IEGeneric var12 = this.q.q(var1, var1.RF()[3], var7.getBitsize(), var3);
         var11 = EUtil.add((IEGeneric)var11, var12.signExtend(64).leftShift(16));
         IEOperation var13 = EUtil.ne(((IEGeneric)var11).slice(16, 64), ((IEGeneric)var11).duplicate().slice(16, 48).signExtend(48));
         var2.add(this.RF.createAssign(this.q.Kl, this.RF.createCond(var13, this.q.bj, this.q.Kl)));
      }

      this.q.q(var1, var2, var6, ((IEGeneric)var11).slice(16, 48));
   }

   private IEGeneric q(IP.eo var1, boolean var2, IEGeneric var3, IEGeneric var4, IEGeneric var5, IEGeneric var6) {
      switch (var1) {
         case q:
            return EUtil.mul(EUtil.extend(var5, 64, var2), EUtil.extend(var6, 64, var2));
         case RF:
            return EUtil.add(this.q(IP.eo.q, var2, null, null, var5, var6), this.RF.createCompose(var3, var4));
         case xK:
            IEGeneric var7 = this.q(IP.eo.q, var2, null, null, var5, var6);
            return EUtil.add(var7, EUtil.extend(var4, 64, var2), EUtil.extend(var3, 64, var2));
         case Dw:
            return EUtil.sub(this.RF.createCompose(var3, var4), this.q(IP.eo.q, var2, null, null, var5, var6));
         default:
            return null;
      }
   }

   public void TQ(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, IP.eo.RF);
   }

   public void Yw(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, IP.eo.Dw);
   }

   public void IY(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, IP.eo.q);
   }

   public void qR(fA var1, List var2, long var3) {
      this.q(var1, var2, var3, IP.eo.Uv);
   }

   private void q(fA var1, List var2, long var3, IP.eo var5) {
      CZ var6 = this.q.q(var1, var1.RF()[0]);
      IEGeneric var7;
      IEGeneric var8;
      if (var1.RF().length > 2) {
         var7 = this.q.q(var1, (IInstructionOperand)var1.RF()[1], var3);
         var8 = this.q.q(var1, var1.RF()[2], var7.getBitsize(), var3);
      } else {
         var7 = this.q.q(var1, (IInstructionOperand)var1.RF()[0], var3);
         var8 = this.q.q(var1, var1.RF()[1], var7.getBitsize(), var3);
      }

      IEGeneric var9 = null;
      if (var1.RF().length == 4) {
         var9 = this.q.q(var1, var1.RF()[3], var7.getBitsize(), var3);
      }

      boolean var10 = var1.Dw().q().endsWith("X");
      Object var11 = var10 ? this.RF.createCompose(var8.slice(16, 32), var8.part(16)) : var8;
      IEGeneric var12 = this.q(var5, var7, (IEGeneric)var11, var9);
      if (var5 != IP.eo.Uv) {
         IEOperation var13 = EUtil.ne(var12, var12.duplicate().part(this.q.JF).signExtend(64));
         var2.add(this.RF.createAssign(this.q.Kl, this.RF.createCond(var13, this.q.bj, this.q.Kl)));
      }

      this.q.q(var1, var2, var6, var12.part(this.q.JF));
   }

   public void YA(fA var1, List var2, long var3) {
      this.RF(var1, var2, var3, IP.eo.RF);
   }

   public void fw(fA var1, List var2, long var3) {
      this.RF(var1, var2, var3, IP.eo.Dw);
   }

   private void RF(fA var1, List var2, long var3, IP.eo var5) {
      CZ var6 = this.q.q(var1, var1.RF()[0]);
      CZ var7 = this.q.q(var1, var1.RF()[1]);
      IEGeneric var8 = this.q.q(var1, (IInstructionOperand)var1.RF()[2], var3);
      IEGeneric var9 = this.q.q(var1, var1.RF()[3], var8.getBitsize(), var3);
      boolean var10 = var1.Dw().q().endsWith("X");
      Object var11 = var10 ? this.RF.createCompose(var9.slice(16, 32), var9.part(16)) : var9;
      IEGeneric var12 = this.q(var5, var8, (IEGeneric)var11, this.RF.createCompose(var6.RF(), var7.RF()));
      IEVar var13 = this.q.gO(64);
      var2.add(this.RF.createAssign(var13, var12));
      this.q.q(var1, var2, var7, var13.slice(this.q.JF, this.q.JF * 2));
      this.q.q(var1, var2, var6, var13.part(this.q.JF));
   }

   private IEGeneric q(IP.eo var1, IEGeneric var2, IEGeneric var3, IEGeneric var4) {
      IEOperation var5 = EUtil.mul(var2.part(16).signExtend(64), var3.part(16).signExtend(64));
      IEOperation var6 = EUtil.mul(var2.slice(16, 32).signExtend(64), var3.duplicate().slice(16, 32).signExtend(64));
      switch (var1) {
         case q:
            return EUtil.add(var5, var6);
         case RF:
            return EUtil.add(EUtil.add(var5, var6), var4.signExtend(64));
         case xK:
         default:
            return null;
         case Dw:
            return EUtil.add(EUtil.sub(var5, var6), var4.signExtend(64));
         case Uv:
            return EUtil.sub(var5, var6);
      }
   }

   public static enum CU {
      q,
      RF,
      xK,
      Dw;
   }

   public static enum eo {
      q,
      RF,
      xK,
      Dw,
      Uv;
   }
}
