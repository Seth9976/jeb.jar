package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.IllegalConversionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.UnsupportedConversionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECompose;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEUntranslatedInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import java.util.ArrayList;
import java.util.List;

public class clr {
   cln q;
   IERoutineContext RF;
   private static final String[] Dw = new String[]{"F", "UN", "EQ", "UEQ", "OLT", "ULT", "OLE", "ULE", "SF", "NGLE", "SEQ", "NGL", "LT", "NGE", "LE", "NGT"};
   private static final String[] Uv = new String[]{
      "AF", "UN", "EQ", "UEQ", "LT", "ULT", "LE", "ULE", "SAF", "SUN", "SEQ", "SUEQ", "SLT", "SULT", "SLE", "SULE"
   };
   private static final String[] oW = new String[]{
      "AT", "OR", "UNE", "NE", "UGE", "OGE", "UGT", "OGT", "SAT", "SOR", "SUNE", "SNE", "SUGE", "SOGE", "SUGT", "SOGT"
   };
   private static final int gO = 0;
   private static final int nf = 1;
   private static final int gP = 2;
   private static final int za = 3;
   static boolean[][] xK = new boolean[][]{
      {false, false, false, false},
      {false, false, false, true},
      {false, false, true, false},
      {false, false, true, true},
      {false, true, false, false},
      {false, true, false, true},
      {false, true, true, false},
      {false, true, true, true}
   };

   public clr(cln var1) {
      this.q = var1;
   }

   public void q(cmd var1, List var2, List var3, long var4) {
      String var6 = var1.getMnemonic();
      if (!var6.contains(".")) {
         this.RF(var1, var2, var3, var4);
      } else {
         String var7 = var6.substring(0, var6.indexOf("."));
         if (var7.contains("_")) {
            var7 = var7.substring(0, var7.indexOf("_"));
         }

         switch (var7) {
            case "C":
               this.xK(var1, var2);
               break;
            case "ALNV":
            case "MOVN":
            case "MOVZ":
            case "MOVF":
            case "MOVT":
            case "NMADD":
            case "NMSUB":
            case "PLL":
            case "PLU":
            case "PUL":
            case "PUU":
               var2.add(this.q.q(var1, var4));
               break;
            case "MOV":
               this.Dw(var1, var2);
               break;
            case "ADD":
               this.q(var1, var2, var4, OperationType.FADD);
               break;
            case "SUB":
               this.q(var1, var2, var4, OperationType.FSUB);
               break;
            case "MUL":
               this.q(var1, var2, var4, OperationType.FMUL);
               break;
            case "DIV":
               this.q(var1, var2, var4, OperationType.FDIV);
               break;
            case "MADD":
               this.q(var1, var2, OperationType.FADD, var4);
               break;
            case "MSUB":
               this.q(var1, var2, OperationType.FSUB, var4);
               break;
            case "CVT":
            case "TRUNC":
               this.q(var1, var2, var4);
               break;
            case "NEG":
            case "ABS":
            case "CEIL":
            case "FLOOR":
            case "JALR":
            case "JR":
            case "RECIP":
            case "ROUND":
            case "RSQRT":
            case "SQRT":
            case "ABSQ":
            case "ADDQ":
            case "ADDQH":
            case "ADDU":
            case "ADDUH":
            case "CMPGDU":
            case "CMPGU":
            case "CMPU":
            case "DEXTR":
            case "DEXTRV":
            case "DPA":
            case "DPAQ":
            case "DPAQX":
            case "DPAU":
            case "DPAX":
            case "DPS":
            case "DPSQ":
            case "DPSQX":
            case "DPSU":
            case "DPSX":
            case "EXTR":
            case "EXTRV":
            case "MAQ":
            case "MULEQ":
            case "MULEU":
            case "MULQ":
            case "MULSA":
            case "MULSAQ":
            case "PACKRL":
            case "PICK":
            case "PRECEQ":
            case "PRECEQU":
            case "PRECEU":
            case "PRECR":
            case "PRECRQ":
            case "PRECRQU":
            case "RADDU":
            case "REPL":
            case "REPLV":
            case "SHLL":
            case "SHLLV":
            case "SHRA":
            case "SHRAV":
            case "SHRL":
            case "SHRLV":
            case "SUBQ":
            case "SUBQH":
            case "SUBU":
            case "SUBUH":
               var2.add(this.q.q(var1, var4));
               break;
            case "CLASS":
               var2.add(this.q.q(var1, var4));
               break;
            case "CMP":
               this.xK(var1, var2);
               break;
            case "DVP":
            case "EVP":
            case "MADDF":
            case "MSUBF":
            case "MAX":
            case "MAXA":
            case "MIN":
            case "MINA":
            case "RINT":
            case "SEL":
            case "SELEQZ":
            case "SELNEZ":
               var2.add(this.q.q(var1, var4));
               break;
            default:
               throw new UnsupportedConversionException("Cannot convert FP instruction: " + var1);
         }
      }
   }

   private void RF(cmd var1, List var2, List var3, long var4) {
      String var6 = var1.getMnemonic();
      switch (var6) {
         case "LWC1":
         case "LWXC1":
            this.RF(var1, var2, "S", false);
            break;
         case "SWC1":
         case "SWXC1":
            this.q(var1, var2, "S", false);
            break;
         case "SDC1":
         case "SDXC1":
            this.q(var1, var2, "D", false);
            break;
         case "SUXC1":
            this.q(var1, var2, "D", true);
            break;
         case "LDC1":
         case "LDXC1":
            this.RF(var1, var2, "D", false);
            break;
         case "LUXC1":
            this.RF(var1, var2, "D", true);
            break;
         case "MFC1":
            this.q(var1, var2, var4, 32);
            break;
         case "DMFC1":
            this.q(var1, var2, var4, 64);
            break;
         case "MFHC1":
            this.RF(var1, var2);
            break;
         case "MTC1":
            this.RF(var1, var2, var4, 32);
            break;
         case "DMTC1":
            this.RF(var1, var2, var4, 64);
            break;
         case "MTHC1":
            this.q(var1, var2);
            break;
         case "BC1T":
         case "BC1TL":
            if (this.q.q(var1, var3, var2, var4, var6)) {
               this.q.es.q(var1, var3, var4, true);
            }
            break;
         case "BC1F":
         case "BC1FL":
            if (this.q.q(var1, var3, var2, var4, var6)) {
               this.q.es.q(var1, var3, var4, false);
            }
            break;
         case "CFC1":
            IEVar var11 = this.q.RF(((clv[])var1.getOperands())[0].getOperandValue());
            IEUntranslatedInstruction var10 = this.q.q(var1, var4, var11);
            var10.addSideEffectDefinedVariable(var11);
            var2.add(var10);
            break;
         case "CTC1":
            IEVar var9 = this.q.RF(((clv[])var1.getOperands())[0].getOperandValue());
            var2.add(this.q.q(var1, var4, var9));
            break;
         case "BC1EQZ":
            if (this.q.q(var1, var3, var2, var4, var6)) {
               this.q.es.RF(var1, var3, var4, true);
            }
            break;
         case "BC1NEZ":
            if (this.q.q(var1, var3, var2, var4, var6)) {
               this.q.es.RF(var1, var3, var4, false);
            }
            break;
         case "MFC0":
         case "MFHC0":
         case "MTC0":
         case "MTHC0":
         case "DMFC0":
         case "DMTC0":
         case "BC2EQZ":
         case "BC2NEZ":
         case "BC2F":
         case "BC2FL":
         case "BC2T":
         case "BC2TL":
         case "CFC2":
         case "CTC2":
         case "LDC2":
         case "LWC2":
         case "SDC2":
         case "SWC2":
         case "COP2":
         case "MFC2":
         case "MFHC2":
         case "MTC2":
         case "MTHC2":
         case "DMFC2":
         case "DMTC2":
            var2.add(this.q.q(var1, var4));
            break;
         default:
            this.q.lk++;
            if (this.q.HO != null) {
               this.q.HO.inc(var6);
            }

            throw new UnsupportedConversionException("Cannot convert FP instruction: " + var1);
      }
   }

   public void q(cmd var1, List var2, String var3, boolean var4) {
      clr.CU var5 = this.q(var1, var3);
      IEGeneric var6 = this.q(((clv[])var1.getOperands())[0], var5);
      IEMem var7 = this.q.q(var1, ((clv[])var1.getOperands())[1], var5.RF());
      if (var4) {
         IEGeneric var8 = var7.getReference();
         var7 = this.RF.createMem(this.RF.createCompose(this.q.xK(3), var8.slice(3, var8.getBitsize())), var7.getBitsize());
      }

      if (var7.getBitsize() > var6.getBitsize()) {
         var2.add(this.RF.createAssign(var7, var6.zeroExtend(var7.getBitsize())));
      } else {
         var2.add(this.RF.createAssign(var7, var6.part(var7.getBitsize())));
      }
   }

   public void RF(cmd var1, List var2, String var3, boolean var4) {
      clr.CU var5 = this.q(var1, var3);
      IEGeneric var6 = this.q(((clv[])var1.getOperands())[0], var5);
      IEMem var7 = this.q.q(var1, ((clv[])var1.getOperands())[1], var5.RF());
      if (var4) {
         IEGeneric var8 = var7.getReference();
         var7 = this.RF.createMem(this.RF.createCompose(this.q.xK(3), var8.slice(3, var8.getBitsize())), var7.getBitsize());
      }

      if (var6 instanceof IECompose) {
         int var11 = 0;

         for (IEGeneric var10 : var6.asCompose()) {
            var2.add(this.RF.createAssign(var10, var7.slice(var11, var11 + var10.getBitsize())));
            var11 += var10.getBitsize();
         }
      } else if (var6.getBitsize() == var7.getBitsize()) {
         var2.add(this.RF.createAssign(var6, var7));
      } else {
         var2.add(this.RF.createAssign(var6.part(var7.getBitsize()), var7));
      }
   }

   private void xK(cmd var1, List var2) {
      String[] var3 = var1.getMnemonic().split("\\.");
      if (var3.length != 3) {
         throw new IllegalConversionException("Inconsistent " + var3[0] + ".cond.fmt");
      } else {
         clr.CU var4 = this.q(var1, 1);
         int var5 = 0;
         Object var6 = this.q.er[0];
         if (((clv[])var1.getOperands()).length == 3) {
            var6 = this.q.q(var1, var1.getOperand(0));
            var5++;
         }

         IEGeneric var7 = this.q((clv)var1.getOperand(var5), var4);
         IEGeneric var8 = this.q((clv)var1.getOperand(var5 + 1), var4);
         if (((IEGeneric)var6).getBitsize() == 1) {
            var2.add(this.RF.createAssign((IEGeneric)var6, this.q(var3[1], var7, var8)));
         } else {
            var2.add(this.RF.createAssign((IEGeneric)var6, this.RF.createCond(this.q(var3[1], var7, var8), this.xK((IEGeneric)var6), this.q((IEGeneric)var6))));
         }
      }
   }

   private IEGeneric q(String var1, IEGeneric var2, IEGeneric var3) {
      boolean var4 = false;

      for (int var5 = 0; var5 < Dw.length; var5++) {
         if (var1.equals(Uv[var5])) {
            var1 = Dw[var5];
            break;
         }

         if (var1.equals(oW[var5])) {
            var1 = Dw[var5];
            var4 = true;
         }
      }

      IEGeneric var6 = this.RF(var1, var2, var3);
      return var4 ? EUtil.notL(var6) : var6;
   }

   private IEGeneric RF(String var1, IEGeneric var2, IEGeneric var3) {
      for (int var4 = 0; var4 < Dw.length; var4++) {
         if (var1.equals(Dw[var4])) {
            boolean[] var5 = xK[var4 % 8];
            if (var5[0] && var5[1] && var5[2] && var5[3]) {
               return this.q.fe;
            }

            if (!var5[0] && !var5[1] && !var5[2] && !var5[3]) {
               return this.q.uw;
            }

            boolean var6 = false;
            if (var5[3]) {
               boolean[] var7 = new boolean[4];

               for (int var8 = 0; var8 < var5.length; var8++) {
                  var7[var8] = !var5[var8];
               }

               var5 = var7;
               var6 = true;
            }

            ArrayList var10 = new ArrayList();
            boolean var11 = true;
            if (var5[0]) {
               var10.add(this.RF.createOperation(var5[2] ? OperationType.FGE : OperationType.FGT, var2, var3));
               var11 = false;
            }

            if (var5[1]) {
               var10.add(this.RF.createOperation(var5[2] ? OperationType.FLE : OperationType.FLT, var2, var3));
               var11 = false;
            }

            if (var11 && var5[2]) {
               var10.add(this.RF.createOperation(OperationType.FEQ, var2, var3));
            }

            if (var5[2]) {
               var10.add(
                  EUtil.orL(
                     EUtil.andL(this.RF.createOperation(OperationType.FEQ, var2, this.q(var2)), this.RF.createOperation(OperationType.FEQ, var3, this.RF(var2))),
                     EUtil.andL(this.RF.createOperation(OperationType.FEQ, var2, this.RF(var2)), this.RF.createOperation(OperationType.FEQ, var3, this.q(var2)))
                  )
               );
            }

            Object var9 = null;
            if (var10.size() == 1) {
               var9 = (IEGeneric)var10.get(0);
            } else if (var10.size() == 2) {
               var9 = EUtil.orL((IEGeneric)var10.get(0), (IEGeneric)var10.get(1));
            } else if (var10.size() == 3) {
               var9 = EUtil.orL((IEGeneric)var10.get(0), EUtil.orL((IEGeneric)var10.get(1), (IEGeneric)var10.get(2)));
            }

            if (var6) {
               return EUtil.notL((IEGeneric)var9);
            }

            return (IEGeneric)var9;
         }
      }

      throw new IllegalConversionException("Implementation missing for condition: " + var1);
   }

   private IEImm q(IEGeneric var1) {
      return var1.getBitsize() == 64 ? this.RF.createImm(0.0) : this.RF.createImm(0.0F).zeroExtend(var1.getBitsize());
   }

   private IEImm RF(IEGeneric var1) {
      return var1.getBitsize() == 64
         ? this.RF.createImm(Double.longBitsToDouble(Long.MIN_VALUE))
         : this.RF.createImm(Float.intBitsToFloat(Integer.MIN_VALUE)).zeroExtend(var1.getBitsize());
   }

   private IEImm xK(IEGeneric var1) {
      return this.RF.createImm(Float.intBitsToFloat(-1)).zeroExtend(var1.getBitsize());
   }

   private void Dw(cmd var1, List var2) {
      clr.CU var3 = this.q(var1);
      IEGeneric var4 = this.q((clv)var1.getOperand(0), var3);
      IEGeneric var5 = this.q((clv)var1.getOperand(1), var3);
      var2.add(this.RF.createAssign(var4, var5));
   }

   public void q(cmd var1, List var2, long var3, int var5) {
      IEGeneric var6 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[0]);
      IEGeneric var7 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[1], var3).part(var5);
      var2.add(this.RF.createAssign(var6, var7.signExtend(var6.getBitsize())));
   }

   public void RF(cmd var1, List var2, long var3, int var5) {
      IEGeneric var6 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[0], var3).part(var5);
      IEGeneric var7 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[1]);
      var2.add(this.RF.createAssign(var7.part(var5), var6));
   }

   public void q(cmd var1, List var2) {
      IEGeneric var3 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[0]).part(32);
      long var5 = ((clv[])var1.getOperands())[1].getOperandValue();
      Object var4;
      if (this.RF(var1)) {
         if ((var5 & 1L) == 0L) {
            var5++;
         }

         var4 = this.q.RF(var5);
      } else {
         var4 = this.q.RF(var5).slice(32, 64);
      }

      var2.add(this.RF.createAssign((IEGeneric)var4, var3));
   }

   public void RF(cmd var1, List var2) {
      IEGeneric var3 = this.q.q(var1, (IInstructionOperand)((clv[])var1.getOperands())[0]);
      long var5 = ((clv[])var1.getOperands())[1].getOperandValue();
      Object var4;
      if (this.RF(var1)) {
         if ((var5 & 1L) == 0L) {
            var5++;
         }

         var4 = this.q.RF(var5);
      } else {
         var4 = this.q.RF(var5).slice(32, 64);
      }

      var2.add(this.RF.createAssign(var3, ((IEGeneric)var4).signExtend(var3.getBitsize())));
   }

   private void q(cmd var1, List var2, long var3, OperationType var5) {
      clr.CU var6 = this.q(var1);
      if (var6.q()) {
         var2.add(this.q.q(var1, var3));
      } else {
         IEGeneric var7 = this.q((clv)var1.getOperand(0), var6);
         IEGeneric var8 = this.q((clv)var1.getOperand(1), var6);
         IEGeneric var9 = this.q((clv)var1.getOperand(2), var6);
         this.q(var2, var5, var7, var8, var9, var6);
      }
   }

   private void q(List var1, OperationType var2, IEGeneric var3, IEGeneric var4, IEGeneric var5, clr.CU var6) {
      if (var6 == clr.CU.oW) {
         IEOperation var7 = this.RF.createOperation(var2, var4.part(32), var5.part(32));
         IEOperation var8 = this.RF.createOperation(var2, var4.slice(32, 64), var5.slice(32, 64));
         var1.add(this.RF.createAssign(var3, this.RF.createCompose(var7, var8)));
      } else {
         var1.add(this.RF.createAssign(var3, this.RF.createOperation(var2, var4, var5)));
      }
   }

   private void q(cmd var1, List var2, OperationType var3, long var4) {
      clr.CU var6 = this.q(var1);
      IEGeneric var7 = this.q((clv)var1.getOperand(0), var6);
      IEGeneric var8 = this.q((clv)var1.getOperand(1), var6);
      IEGeneric var9 = this.q((clv)var1.getOperand(2), var6);
      IEGeneric var10 = this.q((clv)var1.getOperand(3), var6);
      IEGeneric var11 = this.q.SM.part(var7.getBitsize());
      this.q(var2, OperationType.FMUL, var11, var9, var10, var6);
      this.q(var2, var3, var7, var11, var8, var6);
   }

   private void q(cmd var1, List var2, long var3) {
      clr.CU var5 = this.q(var1, 0);
      clr.CU var6 = this.q(var1, 1);
      long var7 = ((clv[])var1.getOperands())[0].getOperandValue();
      IEVar var9 = this.q.RF(var7);
      IEGeneric var10 = this.q(((clv[])var1.getOperands())[1], var6);
      OperationType var11 = this.q(var5, var6);
      if (var11 != null) {
         var2.add(this.RF.createAssign(var9, this.RF.createConversionOperation(var11, var10, var5.gO).zeroExtend(var9.getBitsize())));
      } else {
         ArrayList var12 = new ArrayList();
         ArrayList var13 = new ArrayList();
         var12.add(var9);
         if (var5 == clr.CU.Dw) {
            var12.add(this.q.RF(var7 + 1L));
         }

         long var14 = ((clv[])var1.getOperands())[1].getOperandValue();
         var13.add(this.q.RF(var14));
         if (var6 == clr.CU.Dw) {
            var13.add(this.q.RF(var14 + 1L));
         }

         ArrayList var16 = new ArrayList(var12);
         var16.addAll(var13);
         IEUntranslatedInstruction var17 = this.q.q(var1, var3, (IEGeneric[])var16.toArray(new IEVar[var16.size()]));
         var17.addSideEffectDefinedVariable((IEVar[])var12.toArray(new IEVar[var12.size()]));
         var17.addSideEffectUsedVariable((IEVar[])var13.toArray(new IEVar[var13.size()]));
         var2.add(var17);
      }
   }

   private OperationType q(clr.CU var1, clr.CU var2) {
      switch (var1) {
         case q:
         case xK:
            switch (var2) {
               case q:
               case xK:
               case Dw:
                  return OperationType.FP2FP;
               case Uv:
               case RF:
                  return OperationType.INT2FP;
            }
         case Dw:
         default:
            break;
         case Uv:
         case RF:
            switch (var2) {
               case q:
               case xK:
               case Dw:
                  return OperationType.FP2INT;
            }
      }

      return null;
   }

   clr.CU q(cmd var1) {
      return this.q(var1, 0);
   }

   private clr.CU q(cmd var1, int var2) {
      String[] var3 = var1.getMnemonic().split("\\.");
      if (var3.length < 2) {
         throw new IllegalConversionException("Binary FP operation misses fmt: " + var1);
      } else {
         return this.q(var1, var3[1 + var2]);
      }
   }

   private clr.CU q(cmd var1, String var2) {
      switch (var2) {
         case "S":
            return clr.CU.q;
         case "W":
            return clr.CU.RF;
         case "D":
            if (this.RF(var1)) {
               return clr.CU.Dw;
            }

            return clr.CU.xK;
         case "L":
            if (this.RF(var1)) {
               return clr.CU.Dw;
            }

            return clr.CU.Uv;
         case "PS":
            return clr.CU.oW;
         default:
            throw new IllegalConversionException("Binary FP operation is not supported yet for " + var1);
      }
   }

   private boolean RF(cmd var1) {
      return var1.getProcessorMode() != 64 && !this.q.Up.RF();
   }

   private IEGeneric q(clv var1, clr.CU var2) {
      long var3 = var1.getOperandValue();
      Object var5 = this.q.RF(var3);
      if (var2 == clr.CU.Dw) {
         if ((var3 & 1L) != 0L) {
            var5 = this.q.RF(--var3);
         }

         var5 = this.RF.createCompose((IEGeneric)var5, this.q.RF(var3 + 1L));
      }

      if (var2.RF() < ((IEGeneric)var5).getBitsize()) {
         return ((IEGeneric)var5).part(var2.RF());
      } else if (var2.RF() > ((IEGeneric)var5).getBitsize()) {
         throw new IllegalConversionException("Invalid operand: is not a fp register " + var1);
      } else {
         return (IEGeneric)var5;
      }
   }

   public static enum CU {
      q(32),
      RF(32, false),
      xK(64),
      Dw(32, 32),
      Uv(64, false),
      oW(-1);

      private final int gO;
      private int nf = 0;
      private boolean gP = true;

      private CU(int var3) {
         this.gO = var3;
      }

      private CU(int var3, int var4) {
         this.gO = var3;
         this.nf = var4;
      }

      private CU(int var3, boolean var4) {
         this.gO = var3;
         this.gP = var4;
      }

      boolean q() {
         return this.gO == -1;
      }

      int RF() {
         return this.gO + this.nf;
      }
   }

   public static enum eo {
      q;
   }
}
