package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.core.units.code.IdRanges;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.IllegalConversionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.List;

@Ser
public class ym {
   private static final ILogger gP = GlobalLog.getLogger(ym.class);
   @SerId(21)
   IEVar q;
   @SerId(22)
   IEVar RF;
   @SerId(23)
   IEVar xK;
   @SerId(24)
   IEVar Dw;
   @SerId(33)
   IEImm Uv;
   @SerId(34)
   IEImm oW;
   @SerId(38)
   private IEVar za;
   @SerId(39)
   private IEVar lm;
   @SerId(40)
   private IEVar zz;
   @SerTransient
   IERoutineContext gO;
   @SerTransient
   boolean nf = true;
   private static ym.eo JY = new ym.eo(null);

   public ym(IEGlobalContext var1) {
      this(var1, true);
   }

   public ym(IEGlobalContext var1, boolean var2) {
      this.q = var1.getVar(543);
      this.RF = var1.getVar(542);
      this.xK = var1.getVar(541);
      this.Dw = var1.getVar(540);
      this.za = var1.createVirtualRegister(66080, "cmp64", 64);
      this.lm = var1.createVirtualRegister(66144, "cmpOp0", 32);
      this.zz = var1.createVirtualRegister(66176, "cmpOp1", 32);
      this.nf = var2;
      this.Uv = var1.createImm(0L, 1);
      this.oW = var1.createImm(1L, 1);
   }

   void q(int var1, List var2, IEGeneric var3, List var4, List var5, long var6, boolean var8, IEVar var9) {
      var2.add(this.gO.createJump(var1 + 2 + var4.size(), EUtil.notL(var3)));
      var2.addAll(var4);
      var2.add(this.gO.createJump(var1 + 2 + var4.size() + var5.size(), null));
      var2.addAll(var5);
      IEStatement var10 = (IEStatement)var5.get(var5.size() - 1);
      this.q(var2, var8, var10, var6, var9);
   }

   void q(int var1, List var2, IEGeneric var3, List var4, long var5, boolean var7, IEVar var8) {
      var2.add(this.gO.createJump(var1 + 1 + var4.size(), EUtil.notL(var3)));
      var2.addAll(var4);
      IEStatement var9 = (IEStatement)var4.get(var4.size() - 1);
      this.q(var2, var7, var9, var5, var8);
   }

   private void q(List var1, boolean var2, IEStatement var3, long var4, IEVar var6) {
      if (var2 && var3 instanceof IEAssign && ((IEAssign)var3).getLeftOperand().equals(var6)) {
         var1.add(this.gO.createBranchAssign(var6, this.gO.createImm(var4, var6.getBitsize()), false));
      }
   }

   public ym.eo q(uq var1, fA var2, List var3, long var4, int var6, ym.eo var7) {
      if (var2.Dw().xK()) {
         if (var2.Uv().gO()) {
            return JY;
         } else if (var2.gO()) {
            return JY;
         } else {
            String var10 = var2.Dw().q();
            IEGeneric var14;
            IEGeneric var15;
            switch (var10) {
               case "CMP":
               case "SUB":
                  var14 = this.q(var1, var2, 0, var4);
                  var15 = this.RF(var1, var2, var14.getBitsize(), var4);
                  break;
               case "RSB":
                  var15 = this.q(var1, var2, 0, var4);
                  var14 = this.RF(var1, var2, var15.getBitsize(), var4);
                  break;
               case "SBC":
                  if (var7 == null || var7.RF == null || var7.xK == null) {
                     return JY;
                  }

                  var14 = this.q(var1, var2, 0, var4);
                  var15 = this.RF(var1, var2, var14.getBitsize(), var4);
                  if (var14.getBitsize() != 32 || var7.RF.getBitsize() != 32 || var15 == null) {
                     return JY;
                  }

                  var14 = EUtil.compose(this.gO, var7.RF, var14);
                  var15 = EUtil.compose(this.gO, var7.xK, var15);
                  break;
               default:
                  Object[] var10000 = new Object[]{var10};
                  return JY;
            }

            if (var14 == null || var15 == null) {
               return JY;
            } else if (var14.getBitsize() == 64) {
               var3.add(0, this.gO.createAssign(this.za, EUtil.sub(var14, var15)));
               return new ym.eo(this.za);
            } else {
               var3.add(0, this.gO.createAssign(this.lm, var14));
               var3.add(1, this.gO.createAssign(this.zz, var15));
               var7 = new ym.eo(var6, var4, this.lm, this.zz, var14, var15);
               if (var10.equals("SUB") || var10.equals("RSB")) {
                  var7.q(var1.q(var2, (IInstructionOperand)var2.q(0), var4));
               }

               return var7;
            }
         }
      } else {
         return var7;
      }
   }

   private IEGeneric q(uq var1, fA var2, int var3, long var4) {
      return var1.q(var2, (IInstructionOperand)var2.q(var2.RF().length == 2 ? 0 : 1), var4);
   }

   private IEGeneric RF(uq var1, fA var2, int var3, long var4) {
      int var6 = var2.RF().length == 2 ? 1 : 2;
      switch (var2.q(var6).getOperandType()) {
         case 0:
         case 1:
         case 2:
         case 3:
            return var1.q(var2, var2.q(var6), var3, var4);
         default:
            return null;
      }
   }

   public IEGeneric q(CW var1, IEGeneric var2) {
      return var1.getOperandType() != 1 && var1.getOperandType() != 6 ? null : this.q(Integer.valueOf((int)var1.getOperandValue()), var2);
   }

   public IEGeneric q(Integer var1, IEGeneric var2) {
      if (this.nf && var2 != null) {
         IEGeneric var3 = this.q(var1 >>> 1, var2);
         if (var3 != null) {
            return (var1 & 1) == 0 ? var3 : EUtil.notL(var3, true);
         }
      }

      return this.q(var1);
   }

   private IEGeneric q(int var1, IEGeneric var2) {
      switch (var1) {
         case 0:
            return EUtil.eq(var2, this.gO.createImm(0L, var2.getBitsize()));
         case 1:
         case 2:
         case 3:
         case 4:
            return null;
         case 5:
            return EUtil.geS(var2, this.gO.createImm(0L, var2.getBitsize()));
         case 6:
            return EUtil.gtS(var2, this.gO.createImm(0L, var2.getBitsize()));
         case 7:
            return this.oW;
         default:
            return null;
      }
   }

   public IEGeneric q(Integer var1) {
      IEGeneric var2 = this.q(var1 >>> 1);
      return (IEGeneric)((var1 & 1) == 0 ? var2 : this.gO.createOperation(OperationType.LOG_NOT, var2));
   }

   private IEGeneric q(int var1) {
      switch (var1) {
         case 0:
            return this.gO.createOperation(OperationType.LOG_NEQ, this.RF, this.Uv);
         case 1:
            return this.gO.createOperation(OperationType.LOG_NEQ, this.xK, this.Uv);
         case 2:
            return this.gO.createOperation(OperationType.LOG_NEQ, this.q, this.Uv);
         case 3:
            return this.gO.createOperation(OperationType.LOG_NEQ, this.Dw, this.Uv);
         case 4:
            return this.gO
               .createOperation(
                  OperationType.LOG_AND,
                  this.gO.createOperation(OperationType.LOG_NEQ, this.xK, this.Uv),
                  this.gO.createOperation(OperationType.LOG_EQ, this.RF, this.Uv)
               );
         case 5:
            return this.gO.createOperation(OperationType.LOG_EQ, this.q, this.Dw);
         case 6:
            return this.gO
               .createOperation(
                  OperationType.LOG_AND,
                  this.gO.createOperation(OperationType.LOG_EQ, this.RF, this.Uv),
                  this.gO.createOperation(OperationType.LOG_EQ, this.q, this.Dw)
               );
         case 7:
            return this.oW;
         default:
            return null;
      }
   }

   void q(IEGeneric var1, List var2) {
      var2.add(this.q(var1));
      var2.add(this.RF(var1));
   }

   private IEAssign q(IEGeneric var1) {
      IEGeneric var2 = var1.msb();
      return this.gO.createAssign(this.q, var2);
   }

   private IEAssign RF(IEGeneric var1) {
      IECond var2 = this.gO.createCond(var1, this.Uv, this.oW);
      return this.gO.createAssign(this.RF, var2);
   }

   public IEGeneric q(uq var1, List var2, CZ var3, YO var4) {
      IEVar var5 = null;
      if (var4 == null) {
         return var5;
      } else {
         if (var3 != null && var4.gO()) {
            if (!var3.gP()) {
               throw new IllegalConversionException(Strings.f("Destination register is not a register %s in %s", var3, var4.q()));
            }

            var5 = var1.gO(var3.q().getBitsize());
            int var6 = var4.q((IEVar)var3.q(), var5);
            if (var6 == 0) {
               var5 = null;
            }
         }

         if (var4.RF() != null) {
            var2.add(this.gO.createAssign(this.q, var4.RF()));
         }

         if (var4.xK() != null) {
            var2.add(this.gO.createAssign(this.RF, var4.xK()));
         }

         if (var4.Uv() != null) {
            var2.add(this.gO.createAssign(this.Dw, var4.Uv()));
         }

         if (var4.Dw() != null) {
            var2.add(this.gO.createAssign(this.xK, var4.Dw()));
         }

         return var5;
      }
   }

   static class eo {
      private IEVar q;
      private IEGeneric RF;
      private IEGeneric xK;
      private int Dw;
      private long Uv;
      private IEGeneric oW;
      private IEGeneric gO;
      private IEGeneric nf;

      public eo(IEVar var1) {
         this.q = var1;
      }

      public eo(int var1, long var2, IEGeneric var4, IEGeneric var5, IEGeneric var6, IEGeneric var7) {
         this.Dw = var1;
         this.Uv = var2;
         this.RF = var4;
         this.xK = var5;
         this.oW = var6;
         this.gO = var7;
      }

      private void q(IEGeneric var1) {
         this.nf = var1;
      }

      public IEGeneric q(BasicBlock var1, int var2, List var3, int var4, boolean var5) {
         if (this.RF != null) {
            if (this.Dw + 1 == var2) {
               return (IEGeneric)(this.nf != null ? this.nf : EUtil.sub(this.oW, this.gO));
            }

            if (var5) {
               for (int var6 = var4; var6 < var3.size(); var6++) {
                  IEStatement var7 = (IEStatement)var3.get(var6);
                  if (var7.getPrimaryLowerLevelAddress() == null || var7.getPrimaryLowerLevelAddress() <= this.Uv || !(var7 instanceof IEAssign)) {
                     break;
                  }

                  IEGeneric var8 = ((IEAssign)var7).getLeftOperand();
                  IdRanges var9 = var8.getUsed();
                  if (this.nf != null && var9.hasIntersection(this.nf.getUsed())) {
                     this.nf = null;
                  }

                  if (var9.hasIntersection(this.oW.getUsed()) || var9.hasIntersection(this.gO.getUsed())) {
                     break;
                  }
               }
            }
         }

         return (IEGeneric)(this.RF != null && this.xK != null ? EUtil.sub(this.RF, this.xK) : this.q);
      }
   }
}
