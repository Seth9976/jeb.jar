package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.UnsupportedConversionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterUtil;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class mV {
   uq q;
   IERoutineContext RF;

   public mV(uq var1) {
      this.q = var1;
   }

   public void q(fA var1, fV var2, List var3, long var4) {
      if (var2.qa()) {
         var3.add(this.q.q(var1, var4, true));
      } else {
         ArrayList var6 = new ArrayList();

         for (int var7 = 0; var7 < var2.xK(); var7++) {
            var6.add(this.q.q(var1, var2.RF(var7)));
         }

         wh var20 = var2.Dw();
         CW var8 = var20.q(var4);
         if (var8 != null) {
            IEGeneric var21 = this.q.q(var1, (IInstructionOperand)var8, var4);
            this.q(var6, var1, var2, var3, var21);
         } else {
            mV.eo var9 = new mV.eo(var20.RF());
            IEGeneric var10 = var9.q(var1, var4, var2.HF());
            Object var11 = this.q(var1, var10, var20.gO(), var4);
            Object var12 = var20.nf() ? var11 : var10;
            mV.CU var13 = new mV.CU(var20, var2.gP());
            IEVar var14 = this.q.gO(((IEGeneric)var12).getBitsize());
            if (this.q(var2, var13)) {
               var3.add(this.RF.createAssign(var14, (IEGeneric)var12));
               var12 = var14;
            }

            OQ.eo var15 = var2.za();
            if (var13.q() && var13.RF() && var15 == null) {
               CZ var16 = this.q.q(var1, var9.xK);
               this.q.q(var1, var3, var16, (IEGeneric)var11, true);
            }

            IEVar var22 = this.q(var6, var1, var2, var3, (IEGeneric)var12);
            if (var13.q() && (var15 != null || !var13.RF())) {
               CZ var17 = this.q.q(var1, var9.xK);
               if (var15 != null) {
                  int var18 = var2.xK();
                  if (var2.lm() == fV.eo.Uv) {
                     var18 = 1;
                  }

                  int var19 = var15.RF(var18, var2.Uv() >>> 3);
                  if (var19 != 0) {
                     var11 = EUtil.add((IEGeneric)var11, this.RF.createImm(var19, ((IEGeneric)var11).getBitsize()));
                  }
               }

               this.q.q(var1, var3, var17, (IEGeneric)var11, true);
            }

            if (var22 != null && var2.q()) {
               if (var15 == null) {
                  this.q.q(var1, var22, var3);
               } else {
                  this.q(var3, var4, var22);
               }
            }
         }
      }
   }

   private boolean q(fV var1, mV.CU var2) {
      if (var1.za() != null) {
         HashSet var6 = new HashSet();
         this.q(var6, var1.Dw());

         for (int var7 = 0; var7 < var1.gP().length; var7++) {
            if (var6.contains(RegisterUtil.getPureId(var1.RF(var7).getOperandValue()))) {
               if (var2.q()) {
                  var2.q = false;
               }

               return true;
            }
         }

         return false;
      } else {
         HashSet var3 = new HashSet();
         this.q(var3, var1.Dw());

         for (int var4 = 0; var4 < var1.xK(); var4++) {
            CW var5 = var1.RF(var4);
            if (var5.getOperandType() != 0) {
               return true;
            }

            if (var3.contains(RegisterUtil.getPureId(var5.getOperandValue()))) {
               if (var2.q()) {
                  return true;
               }

               if (var2.Dw || var4 != var1.xK() - 1) {
                  return true;
               }
            }
         }

         if (var2.q()) {
            var2.RF = false;
         }

         return false;
      }
   }

   private void q(Set var1, CW var2) {
      if (var2.oW() != null && var2.oW().length != 0) {
         for (CW var6 : var2.oW()) {
            this.q(var1, var6);
         }
      } else {
         if (var2.getOperandType() == 0) {
            var1.add(RegisterUtil.getPureId(var2.getOperandValue()));
         }
      }
   }

   private IEVar q(List var1, fA var2, fV var3, List var4, IEGeneric var5) {
      OQ.eo var6 = var3.za();
      IEVar var7 = null;

      for (int var8 = 0; var8 < var3.xK(); var8++) {
         CZ var9 = (CZ)var1.get(var8);
         IEGeneric var10 = this.q(var2, var3, var5, var8, var9.oW());
         boolean var11 = var6 != null || var3.Dw().Uv() || var8 != var3.xK() - 1;
         if (var3.RF(var8).RF(this.q.JF) && var11) {
            var7 = var5 == this.q.gO(var5.getBitsize()) ? this.q.nf(var5.getBitsize()) : this.q.gO(var5.getBitsize());
            var4.add(this.RF.createAssign(var7, var10));
         } else {
            this.q.q(var2, var4, var9, var10, var3.q());
         }

         if (var3.zz() != null) {
            this.q(var2, var4, var10, var3);
         }
      }

      return var7;
   }

   private IEGeneric q(fA var1, fV var2, IEGeneric var3, int var4, boolean var5) {
      switch (var2.lm()) {
         case RF:
         case xK:
         case Dw:
         case Uv:
            CW var13 = var2.RF(var4);
            int var14;
            int var15;
            if (var1.getProcessorMode() == 64) {
               if (var13 instanceof RI) {
                  var14 = xB.Dw((RI)var13);
                  var15 = xB.xK((RI)var13);
               } else {
                  if (!(var13 instanceof BY)) {
                     throw new UnsupportedConversionException("Illegal Register: " + var13);
                  }

                  CW var16 = ((BY)var13).RF();
                  if (!(var16 instanceof RI)) {
                     throw new UnsupportedConversionException("Illegal Register: " + var13);
                  }

                  var14 = 1;
                  var15 = xB.q((RI)var16) >>> 3;
               }
            } else {
               if (var1.Dw().oW() == null) {
                  throw new UnsupportedConversionException("Illegal Register: " + var13);
               }

               if (var13 instanceof BY) {
                  var14 = 1;
                  var15 = k.q(var1.Dw()) >>> 3;
               } else {
                  var15 = k.RF(var1.Dw());
                  var14 = k.xK(var1.Dw());
               }
            }

            ArrayList var17 = new ArrayList();

            for (int var18 = 0; var18 < var14; var18++) {
               var17.add(this.RF.createMem((IEGeneric)(switch (var2.lm()) {
                  case RF -> EUtil.add(var3, this.RF.createImm(var4 * var15 + var18 * var2.xK() * var15, var3.getBitsize()));
                  case xK -> {
                     int var12 = var4 % 2 == 0 ? (var4 >>> 1) * var15 : var14 * var15 * 2 + (var4 >>> 1) * var15;
                     yield EUtil.add(var3, this.RF.createImm(var12 + var18 * 2 * var15, var3.getBitsize()));
                  }
                  case Dw -> EUtil.add(var3, this.RF.createImm(var4 * var15, var3.getBitsize()));
                  case Uv -> var3;
                  default -> throw new UnsupportedConversionException("Illegal Mode: " + var2.lm());
               }), var15 * 8));
            }

            return EUtil.compose(this.RF, var17);
         case q:
            OQ.eo var6 = var2.za();
            int var7 = var2.Uv();
            int var8 = (var6 != null ? var6 : OQ.eo.q).q(var4, var2.xK(), var7 >>> 3);
            IEMem var9;
            if (var8 != 0) {
               var9 = this.RF.createMem(EUtil.add(var3, this.RF.createImm(var8, var3.getBitsize())), var7);
            } else {
               var9 = this.RF.createMem(var3, var7);
            }

            Object var10;
            if (var5) {
               var10 = EUtil.extend(var9, var2.RF(), var2.oW());
            } else {
               var10 = var9;
            }

            return (IEGeneric)var10;
         default:
            throw new UnsupportedConversionException("Illegal Mode: " + var2.lm());
      }
   }

   private void q(fA var1, List var2, IEGeneric var3, fV var4) {
      IEGeneric var5 = this.q.q(var1, (IInstructionOperand)var4.JY()).part(var4.Uv());
      var3 = var3.part(var4.Uv());
      IEGeneric var6 = this.q(var4.zz(), var3, var5);
      var2.add(this.RF.createAssign(var3, var6));
   }

   private IEGeneric q(fV.CU var1, IEGeneric var2, IEGeneric var3) {
      Lb.nI var4;
      switch (var1) {
         case q:
            var4 = Lb.nI.q;
            break;
         case RF:
            var4 = Lb.nI.Dw;
            break;
         case xK:
            var4 = Lb.nI.Uv;
            break;
         case Dw:
            var4 = Lb.nI.nf;
            break;
         case Uv:
            return EUtil.max(this.RF, var2, var3, true);
         case oW:
            return EUtil.min(this.RF, var2, var3, true);
         case gO:
            return EUtil.max(this.RF, var2, var3, false);
         case nf:
            return EUtil.min(this.RF, var2, var3, false);
         default:
            throw new UnsupportedConversionException();
      }

      return this.RF.createOperation(var4.q(), var4.q(var2), var4.RF(var3));
   }

   public void RF(fA var1, fV var2, List var3, long var4) {
      if (var2.qa()) {
         var3.add(this.q.q(var1, var4, true));
      } else {
         ArrayList var6 = new ArrayList();

         for (int var7 = 0; var7 < var2.xK(); var7++) {
            IEGeneric var8 = this.q.q(var1, (IInstructionOperand)var2.RF(var7), var4);
            if (var2.Uv() < this.q.JF) {
               var8 = var8.part(var2.Uv());
            }

            var6.add(var8);
         }

         wh var16 = var2.Dw();
         CW var17 = var16.q(var4);
         if (var17 != null) {
            IEGeneric var18 = this.q.q(var1, (IInstructionOperand)var17, var4);
            this.RF(var6, var1, var2, var3, var18);
         } else {
            mV.eo var9 = new mV.eo(var16.RF());
            IEGeneric var10 = var9.q(var1, var4, var2.HF());
            Object var11 = this.q(var1, var10, var16.gO(), var4);
            Object var12 = var16.nf() ? var11 : var10;
            this.RF(var6, var1, var2, var3, (IEGeneric)var12);
            if (var16.Uv()) {
               CZ var13 = this.q.q(var1, var9.xK);
               OQ.eo var14 = var2.za();
               if (var14 != null) {
                  int var15 = var14.RF(var2.xK(), var2.Uv() >>> 3);
                  if (var15 != 0) {
                     var11 = EUtil.add((IEGeneric)var11, this.RF.createImm(var15, ((IEGeneric)var11).getBitsize()));
                  }
               }

               this.q.q(var1, var3, var13, (IEGeneric)var11, true);
            }

            if (var2.gO() != null) {
               CZ var19 = this.q.q(var1, var2.gO());
               this.q.q(var1, var3, var19, this.q.Uv(var19.gO()));
            }
         }
      }
   }

   private void RF(List var1, fA var2, fV var3, List var4, IEGeneric var5) {
      for (int var6 = 0; var6 < var3.xK(); var6++) {
         IEGeneric var7 = (IEGeneric)var1.get(var6);
         IEGeneric var8 = this.q(var2, var3, var5, var6, false);
         if (var3.zz() != null) {
            IEGeneric var9 = EUtil.extend(var8, var3.RF(), var3.oW());
            this.q(var2, var4, var9, var3);
         } else {
            this.q.q(var2, var4, var8, var7);
         }
      }
   }

   private void q(List var1, long var2, IEGeneric var4) {
      this.q.es.q(var1, var4);
   }

   private IEGeneric q(fA var1, IEGeneric var2, CW var3, long var4) {
      if (var3 == null) {
         return var2;
      } else {
         IEGeneric var6 = this.q.q(var1, var3, var2.getBitsize(), var4);
         return var6.isOperation(OperationType.SUB) && EUtil.isZero(var6.asOperation().getOperand1())
            ? EUtil.sub(var2, var6.asOperation().getOperand2())
            : EUtil.add(var2, var6);
      }
   }

   public void q(fA var1, List var2, long var3) {
      CZ var5 = this.q.q(var1, var1.q(0));
      IEGeneric var6 = this.q.q(var1, (IInstructionOperand)var1.q(1), var3);
      IEMem var7 = this.q.q(var1, var1.q(2), var3, var5.gO());
      IEVar var8 = this.q.gO(var6.getBitsize());
      var2.add(this.RF.createAssign(var8, var7));
      var2.add(this.RF.createAssign(var7, var6));
      this.q.q(var1, var2, var5, var8);
   }

   public void RF(fA var1, List var2, long var3) {
      CZ var5 = this.q.q(var1, var1.q(0));
      IEGeneric var6 = this.q.q(var1, (IInstructionOperand)var1.q(1), var3);
      IEMem var7 = this.q.q(var1, var1.q(2), var3, 8);
      IEVar var8 = this.q.gO(var6.getBitsize());
      var2.add(this.RF.createAssign(var8, var7.zeroExtend(var5.gO())));
      var2.add(this.RF.createAssign(var7, var6.part(8)));
      this.q.q(var1, var2, var5, var8);
   }

   private class CU {
      boolean q;
      boolean RF;
      final boolean xK;
      final boolean Dw;

      public CU(wh var2, CW[] var3) {
         this.q = var2.Uv();
         this.xK = var2.RF().RF(mV.this.q.JF);
         this.Dw = this.q(var3);
         if (!this.xK) {
            this.RF = true;
         } else if (!this.Dw) {
            this.RF = false;
         } else {
            this.q = false;
         }
      }

      public boolean q() {
         return this.q;
      }

      public boolean RF() {
         return this.RF;
      }

      private boolean q(CW[] var1) {
         for (CW var5 : var1) {
            if (var5.RF(mV.this.q.JF)) {
               return true;
            }
         }

         return false;
      }
   }

   private class eo {
      private final CW xK;
      final int q;

      public eo(CW var2) {
         if (var2 instanceof JK) {
            this.xK = var2.oW()[0];
            this.q = (int)var2.oW()[1].getOperandValue();
         } else {
            this.xK = var2;
            this.q = 0;
         }
      }

      public IEGeneric q(fA var1, long var2, String var4) {
         IEGeneric var5 = mV.this.q.q(var1, (IInstructionOperand)this.xK, var2);
         return var4 != null ? mV.this.q.tb.RF(var5, var4.charAt(0)) : var5;
      }

      public void q(List var1, IEGeneric var2) {
      }
   }
}
