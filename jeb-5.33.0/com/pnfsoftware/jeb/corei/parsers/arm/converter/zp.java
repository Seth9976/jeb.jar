package com.pnfsoftware.jeb.corei.parsers.arm.converter;

import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.UnsupportedConversionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterUtil;
import com.pnfsoftware.jebglobal.Gq;
import com.pnfsoftware.jebglobal.KH;
import com.pnfsoftware.jebglobal.Yg;
import com.pnfsoftware.jebglobal.hg;
import com.pnfsoftware.jebglobal.hy;
import com.pnfsoftware.jebglobal.lw;
import com.pnfsoftware.jebglobal.mN;
import com.pnfsoftware.jebglobal.pY;
import com.pnfsoftware.jebglobal.rw;
import com.pnfsoftware.jebglobal.u;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class zp {
   HE pC;
   IERoutineContext A;

   public zp(HE var1) {
      this.pC = var1;
   }

   public void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, mN var2, List var3, long var4) {
      if (var2.ED()) {
         var3.add(this.pC.pC(var1, var4, true));
      } else {
         ArrayList var6 = new ArrayList();

         for (int var7 = 0; var7 < var2.kS(); var7++) {
            var6.add(this.pC.pC(var1, var2.A(var7)));
         }

         KH var20 = var2.wS();
         Yg var8 = var20.pC(var4);
         if (var8 != null) {
            IEGeneric var21 = this.pC.pC(var1, (IInstructionOperand)var8, var4);
            this.pC(var6, var1, var2, var3, var21);
         } else {
            zp.Av var9 = new zp.Av(var20.A());
            IEGeneric var10 = var9.pC(var1, var4, var2.NS());
            Object var11 = this.pC(var1, var10, var20.sY(), var4);
            Object var12 = var20.ys() ? var11 : var10;
            zp.Sv var13 = new zp.Sv(var20, var2.ld());
            IEVar var14 = this.pC.E(((IEGeneric)var12).getBitsize());
            if (this.pC(var2, var13)) {
               var3.add(this.A.createAssign(var14, (IEGeneric)var12));
               var12 = var14;
            }

            hg.Av var15 = var2.gp();
            if (var13.pC() && var13.A() && var15 == null) {
               pY var16 = this.pC.pC(var1, var9.kS);
               this.pC.pC(var1, var3, var16, (IEGeneric)var11, true);
            }

            IEVar var22 = this.pC(var6, var1, var2, var3, (IEGeneric)var12);
            if (var13.pC() && (var15 != null || !var13.A())) {
               pY var17 = this.pC.pC(var1, var9.kS);
               if (var15 != null) {
                  int var18 = var2.kS();
                  if (var2.oT() == mN.Av.UT) {
                     var18 = 1;
                  }

                  int var19 = var15.A(var18, var2.UT() >>> 3);
                  if (var19 != 0) {
                     var11 = EUtil.add((IEGeneric)var11, this.A.createImm(var19, ((IEGeneric)var11).getBitsize()));
                  }
               }

               this.pC.pC(var1, var3, var17, (IEGeneric)var11, true);
            }

            if (var22 != null && var2.pC()) {
               if (var15 == null) {
                  this.pC.pC(var1, var22, var3);
               } else {
                  this.pC(var3, var4, var22);
               }
            }
         }
      }
   }

   private boolean pC(mN var1, zp.Sv var2) {
      if (var1.gp() != null) {
         HashSet var6 = new HashSet();
         this.pC(var6, var1.wS());

         for (int var7 = 0; var7 < var1.ld().length; var7++) {
            if (var6.contains(RegisterUtil.getPureId(var1.A(var7).getOperandValue()))) {
               if (var2.pC()) {
                  var2.pC = false;
               }

               return true;
            }
         }

         return false;
      } else {
         HashSet var3 = new HashSet();
         this.pC(var3, var1.wS());

         for (int var4 = 0; var4 < var1.kS(); var4++) {
            Yg var5 = var1.A(var4);
            if (var5.getOperandType() != 0) {
               return true;
            }

            if (var3.contains(RegisterUtil.getPureId(var5.getOperandValue()))) {
               if (var2.pC()) {
                  return true;
               }

               if (var2.wS || var4 != var1.kS() - 1) {
                  return true;
               }
            }
         }

         if (var2.pC()) {
            var2.A = false;
         }

         return false;
      }
   }

   private void pC(Set var1, Yg var2) {
      if (var2.E() != null && var2.E().length != 0) {
         for (Yg var6 : var2.E()) {
            this.pC(var1, var6);
         }
      } else {
         if (var2.getOperandType() == 0) {
            var1.add(RegisterUtil.getPureId(var2.getOperandValue()));
         }
      }
   }

   private IEVar pC(List var1, com.pnfsoftware.jeb.corei.parsers.arm.rQ var2, mN var3, List var4, IEGeneric var5) {
      hg.Av var6 = var3.gp();
      IEVar var7 = null;

      for (int var8 = 0; var8 < var3.kS(); var8++) {
         pY var9 = (pY)var1.get(var8);
         Object var10 = this.pC(var2, var3, var5, var8, var9.E(), var4);
         boolean var11 = var6 != null || var3.wS().UT() || var8 != var3.kS() - 1;
         if (var3.fI() != null) {
            IEVar var12 = this.pC.ys(((IEGeneric)var10).getBitsize());
            var4.add(this.A.createAssign(var12, (IEGeneric)var10));
            this.pC(var2, var4, (IEGeneric)var10, var12, var3);
            var10 = var12;
         }

         if (var3.A(var8).A(this.pC.fI) && var11) {
            var7 = var5 == this.pC.E(var5.getBitsize()) ? this.pC.sY(var5.getBitsize()) : this.pC.E(var5.getBitsize());
            var4.add(this.A.createAssign(var7, (IEGeneric)var10));
         } else {
            this.pC.pC(var2, var4, var9, (IEGeneric)var10, var3.pC());
         }
      }

      return var7;
   }

   private IEGeneric pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, mN var2, IEGeneric var3, int var4, boolean var5, List var6) {
      switch (var2.oT()) {
         case wS:
         case UT:
         case A:
         case kS:
            Yg var16 = var2.A(var4);
            int var17;
            int var18;
            if (var1.getProcessorMode() == 64) {
               if (var16 instanceof lw) {
                  var17 = u.wS((lw)var16);
                  var18 = u.kS((lw)var16);
               } else {
                  if (!(var16 instanceof rw)) {
                     throw new UnsupportedConversionException("Illegal Register: " + var16);
                  }

                  Yg var19 = ((rw)var16).A();
                  if (!(var19 instanceof lw)) {
                     throw new UnsupportedConversionException("Illegal Register: " + var16);
                  }

                  var17 = 1;
                  var18 = u.pC((lw)var19) >>> 3;
               }
            } else {
               if (var1.wS().UT() == null) {
                  throw new UnsupportedConversionException("Illegal Register: " + var16);
               }

               if (var16 instanceof rw) {
                  var17 = 1;
                  var18 = Gq.pC(var1.wS()) >>> 3;
               } else {
                  var18 = Gq.A(var1.wS());
                  var17 = Gq.kS(var1.wS());
               }
            }

            ArrayList var20 = new ArrayList();
            Object var21 = null;
            switch (var2.oT()) {
               case wS:
                  var21 = EUtil.add(var3, this.A.createImm(var4 * var18, var3.getBitsize()));
                  break;
               case UT:
                  var21 = var3;
            }

            if (var21 == null) {
               for (int var25 = 0; var25 < var17; var25++) {
                  IEMem var26;
                  switch (var2.oT()) {
                     case A:
                        int var27 = var4 * var18 + var25 * var2.kS() * var18;
                        var21 = EUtil.add(var3, this.A.createImm(var27, var3.getBitsize()));
                        var26 = this.A.createMem((IEGeneric)var21, var18 * 8);
                        break;
                     case kS:
                        int var15 = var4 % 2 == 0 ? (var4 >>> 1) * var18 : var17 * var18 * 2 + (var4 >>> 1) * var18;
                        var21 = EUtil.add(var3, this.A.createImm(var15 + var25 * 2 * var18, var3.getBitsize()));
                        var26 = this.A.createMem((IEGeneric)var21, var18 * 8);
                        break;
                     default:
                        throw new UnsupportedConversionException("Illegal Mode: " + var2.oT());
                  }

                  var20.add(var26);
               }

               return EUtil.compose(this.A, var20);
            }

            IEVar var24 = this.pC.ys(var18 * 8);
            var6.add(this.A.createAssign(var24, this.A.createMem((IEGeneric)var21, var18 * 8)));
            IEVar var13 = var24;

            for (int var14 = 0; var14 < var17; var14++) {
               var20.add(var13);
            }

            return EUtil.compose(this.A, var20);
         case pC:
            hg.Av var7 = var2.gp();
            int var8 = var2.UT();
            int var9 = (var7 != null ? var7 : hg.Av.pC).pC(var4, var2.kS(), var8 >>> 3);
            Object var10;
            if (var9 != 0) {
               var10 = this.A.createMem(EUtil.add(var3, this.A.createImm(var9, var3.getBitsize())), var8);
            } else {
               var10 = this.A.createMem(var3, var8);
            }

            Object var11;
            if (var5) {
               if (var2.ys() && var6 != null && var2.E() && var2.fI() == null) {
                  IEVar var12 = this.pC.ys(((IEGeneric)var10).getBitsize());
                  var6.add(this.A.createAssign(var12, (IEGeneric)var10));
                  var10 = var12;
               }

               var11 = EUtil.extend((IEGeneric)var10, var2.A(), var2.E());
            } else {
               var11 = var10;
            }

            return (IEGeneric)var11;
         default:
            throw new UnsupportedConversionException("Illegal Mode: " + var2.oT());
      }
   }

   private void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, IEGeneric var3, IEGeneric var4, mN var5) {
      IEGeneric var6 = this.pC.pC(var1, (IInstructionOperand)var5.WR()).part(var5.UT());
      var3 = var3.part(var5.UT());
      var4 = var4.part(var5.UT());
      IEGeneric var7 = this.pC(var5.fI(), var4, var6);
      var2.add(this.A.createAssign(var3, var7));
   }

   private IEGeneric pC(mN.Sv var1, IEGeneric var2, IEGeneric var3) {
      com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.K var4;
      switch (var1) {
         case pC:
            var4 = com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.K.pC;
            break;
         case A:
            var4 = com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.K.wS;
            break;
         case kS:
            var4 = com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.K.UT;
            break;
         case wS:
            var4 = com.pnfsoftware.jeb.corei.parsers.arm.converter.Sv.K.ys;
            break;
         case UT:
            return EUtil.max(this.A, var2, var3, true);
         case E:
            return EUtil.min(this.A, var2, var3, true);
         case sY:
            return EUtil.max(this.A, var2, var3, false);
         case ys:
            return EUtil.min(this.A, var2, var3, false);
         default:
            throw new UnsupportedConversionException();
      }

      return this.A.createOperation(var4.pC(), var4.pC(var2), var4.A(var3));
   }

   public void A(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, mN var2, List var3, long var4) {
      if (var2.ED()) {
         var3.add(this.pC.pC(var1, var4, true));
      } else {
         ArrayList var6 = new ArrayList();

         for (int var7 = 0; var7 < var2.kS(); var7++) {
            IEGeneric var8 = this.pC.pC(var1, (IInstructionOperand)var2.A(var7), var4);
            if (var2.UT() < this.pC.fI) {
               var8 = var8.part(var2.UT());
            }

            var6.add(var8);
         }

         KH var16 = var2.wS();
         Yg var17 = var16.pC(var4);
         if (var17 != null) {
            IEGeneric var18 = this.pC.pC(var1, (IInstructionOperand)var17, var4);
            this.A(var6, var1, var2, var3, var18);
         } else {
            zp.Av var9 = new zp.Av(var16.A());
            IEGeneric var10 = var9.pC(var1, var4, var2.NS());
            Object var11 = this.pC(var1, var10, var16.sY(), var4);
            Object var12 = var16.ys() ? var11 : var10;
            this.A(var6, var1, var2, var3, (IEGeneric)var12);
            if (var16.UT()) {
               pY var13 = this.pC.pC(var1, var9.kS);
               hg.Av var14 = var2.gp();
               if (var14 != null) {
                  int var15 = var14.A(var2.kS(), var2.UT() >>> 3);
                  if (var15 != 0) {
                     var11 = EUtil.add((IEGeneric)var11, this.A.createImm(var15, ((IEGeneric)var11).getBitsize()));
                  }
               }

               this.pC.pC(var1, var3, var13, (IEGeneric)var11, true);
            }

            if (var2.sY() != null) {
               pY var19 = this.pC.pC(var1, var2.sY());
               this.pC.pC(var1, var3, var19, this.pC.wS(var19.sY()));
            }
         }
      }
   }

   private void A(List var1, com.pnfsoftware.jeb.corei.parsers.arm.rQ var2, mN var3, List var4, IEGeneric var5) {
      for (int var6 = 0; var6 < var3.kS(); var6++) {
         IEGeneric var7 = (IEGeneric)var1.get(var6);
         IEGeneric var8 = this.pC(var2, var3, var5, var6, false, null);
         if (var3.fI() != null) {
            IEGeneric var9 = EUtil.extend(var8, var3.A(), var3.E());
            this.pC(var2, var4, var9, var9, var3);
         } else {
            this.pC.pC(var2, var4, var8, var7);
         }
      }
   }

   private void pC(List var1, long var2, IEGeneric var4) {
      this.pC.wS.pC(var1, var4);
   }

   private IEGeneric pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, IEGeneric var2, Yg var3, long var4) {
      if (var3 == null) {
         return var2;
      } else {
         IEGeneric var6 = this.pC.pC(var1, var3, var2.getBitsize(), var4);
         return var6.isOperation(OperationType.SUB) && EUtil.isZero(var6.asOperation().getOperand1())
            ? EUtil.sub(var2, var6.asOperation().getOperand2())
            : EUtil.add(var2, var6);
      }
   }

   public void pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      pY var5 = this.pC.pC(var1, var1.pC(0));
      IEGeneric var6 = this.pC.pC(var1, (IInstructionOperand)var1.pC(1), var3);
      IEMem var7 = this.pC.pC(var1, var1.pC(2), var3, var5.sY());
      IEVar var8 = this.pC.E(var6.getBitsize());
      var2.add(this.A.createAssign(var8, var7));
      var2.add(this.A.createAssign(var7, var6));
      this.pC.pC(var1, var2, var5, var8);
   }

   public void A(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, List var2, long var3) {
      pY var5 = this.pC.pC(var1, var1.pC(0));
      IEGeneric var6 = this.pC.pC(var1, (IInstructionOperand)var1.pC(1), var3);
      IEMem var7 = this.pC.pC(var1, var1.pC(2), var3, 8);
      IEVar var8 = this.pC.E(var6.getBitsize());
      var2.add(this.A.createAssign(var8, var7.zeroExtend(var5.sY())));
      var2.add(this.A.createAssign(var7, var6.part(8)));
      this.pC.pC(var1, var2, var5, var8);
   }

   private class Av {
      private final Yg kS;
      final int pC;

      public Av(Yg var2) {
         if (var2 instanceof hy) {
            this.kS = var2.E()[0];
            this.pC = (int)var2.E()[1].getOperandValue();
         } else {
            this.kS = var2;
            this.pC = 0;
         }
      }

      public IEGeneric pC(com.pnfsoftware.jeb.corei.parsers.arm.rQ var1, long var2, String var4) {
         IEGeneric var5 = zp.this.pC.pC(var1, (IInstructionOperand)this.kS, var2);
         return var4 != null ? zp.this.pC.oT.A(var5, var4.charAt(0)) : var5;
      }
   }

   private class Sv {
      boolean pC;
      boolean A;
      final boolean kS;
      final boolean wS;

      public Sv(KH var2, Yg[] var3) {
         this.pC = var2.UT();
         this.kS = var2.A().A(zp.this.pC.fI);
         this.wS = this.pC(var3);
         if (!this.kS) {
            this.A = true;
         } else if (!this.wS) {
            this.A = false;
         } else {
            this.pC = false;
         }
      }

      public boolean pC() {
         return this.pC;
      }

      public boolean A() {
         return this.A;
      }

      private boolean pC(Yg[] var1) {
         for (Yg var5 : var1) {
            if (var5.A(zp.this.pC.fI)) {
               return true;
            }
         }

         return false;
      }
   }
}
