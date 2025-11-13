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

public class cff {
   cfb pC;
   IERoutineContext A;
   private static final String[] wS = new String[]{"F", "UN", "EQ", "UEQ", "OLT", "ULT", "OLE", "ULE", "SF", "NGLE", "SEQ", "NGL", "LT", "NGE", "LE", "NGT"};
   private static final String[] UT = new String[]{
      "AF", "UN", "EQ", "UEQ", "LT", "ULT", "LE", "ULE", "SAF", "SUN", "SEQ", "SUEQ", "SLT", "SULT", "SLE", "SULE"
   };
   private static final String[] E = new String[]{
      "AT", "OR", "UNE", "NE", "UGE", "OGE", "UGT", "OGT", "SAT", "SOR", "SUNE", "SNE", "SUGE", "SOGE", "SUGT", "SOGT"
   };
   static boolean[][] kS = new boolean[][]{
      {false, false, false, false},
      {false, false, false, true},
      {false, false, true, false},
      {false, false, true, true},
      {false, true, false, false},
      {false, true, false, true},
      {false, true, true, false},
      {false, true, true, true}
   };

   public cff(cfb var1) {
      this.pC = var1;
   }

   public void pC(cfq var1, List var2, List var3, long var4) {
      String var6 = var1.getMnemonic();
      if (!var6.contains(".")) {
         this.A(var1, var2, var3, var4);
      } else {
         String var7 = var6.substring(0, var6.indexOf("."));
         if (var7.contains("_")) {
            var7 = var7.substring(0, var7.indexOf("_"));
         }

         switch (var7) {
            case "C":
               this.kS(var1, var2);
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
               var2.add(this.pC.pC(var1, var4));
               break;
            case "MOV":
               this.wS(var1, var2);
               break;
            case "ADD":
               this.pC(var1, var2, var4, OperationType.FADD);
               break;
            case "SUB":
               this.pC(var1, var2, var4, OperationType.FSUB);
               break;
            case "MUL":
               this.pC(var1, var2, var4, OperationType.FMUL);
               break;
            case "DIV":
               this.pC(var1, var2, var4, OperationType.FDIV);
               break;
            case "MADD":
               this.pC(var1, var2, OperationType.FADD, var4);
               break;
            case "MSUB":
               this.pC(var1, var2, OperationType.FSUB, var4);
               break;
            case "CVT":
            case "TRUNC":
               this.pC(var1, var2, var4);
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
               var2.add(this.pC.pC(var1, var4));
               break;
            case "CLASS":
               var2.add(this.pC.pC(var1, var4));
               break;
            case "CMP":
               this.kS(var1, var2);
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
               var2.add(this.pC.pC(var1, var4));
               break;
            default:
               throw new UnsupportedConversionException("Cannot convert FP instruction: " + var1);
         }
      }
   }

   private void A(cfq var1, List var2, List var3, long var4) {
      String var6 = var1.getMnemonic();
      switch (var6) {
         case "LWC1":
         case "LWXC1":
            this.A(var1, var2, "S", false);
            break;
         case "SWC1":
         case "SWXC1":
            this.pC(var1, var2, "S", false);
            break;
         case "SDC1":
         case "SDXC1":
            this.pC(var1, var2, "D", false);
            break;
         case "SUXC1":
            this.pC(var1, var2, "D", true);
            break;
         case "LDC1":
         case "LDXC1":
            this.A(var1, var2, "D", false);
            break;
         case "LUXC1":
            this.A(var1, var2, "D", true);
            break;
         case "MFC1":
            this.pC(var1, var2, var4, 32);
            break;
         case "DMFC1":
            this.pC(var1, var2, var4, 64);
            break;
         case "MFHC1":
            this.A(var1, var2);
            break;
         case "MTC1":
            this.A(var1, var2, var4, 32);
            break;
         case "DMTC1":
            this.A(var1, var2, var4, 64);
            break;
         case "MTHC1":
            this.pC(var1, var2);
            break;
         case "BC1T":
         case "BC1TL":
            if (this.pC.pC(var1, var3, var2, var4, var6)) {
               this.pC.A.pC(var1, var3, var4, true);
            }
            break;
         case "BC1F":
         case "BC1FL":
            if (this.pC.pC(var1, var3, var2, var4, var6)) {
               this.pC.A.pC(var1, var3, var4, false);
            }
            break;
         case "CFC1":
            IEVar var11 = this.pC.A(((cfj[])var1.getOperands())[0].getOperandValue());
            IEUntranslatedInstruction var10 = this.pC.pC(var1, var4, var11);
            var10.addSideEffectDefinedVariable(var11);
            var2.add(var10);
            break;
         case "CTC1":
            IEVar var9 = this.pC.A(((cfj[])var1.getOperands())[0].getOperandValue());
            var2.add(this.pC.pC(var1, var4, var9));
            break;
         case "BC1EQZ":
            if (this.pC.pC(var1, var3, var2, var4, var6)) {
               this.pC.A.A(var1, var3, var4, true);
            }
            break;
         case "BC1NEZ":
            if (this.pC.pC(var1, var3, var2, var4, var6)) {
               this.pC.A.A(var1, var3, var4, false);
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
            var2.add(this.pC.pC(var1, var4));
            break;
         default:
            this.pC.os++;
            if (this.pC.mv != null) {
               this.pC.mv.inc(var6);
            }

            throw new UnsupportedConversionException("Cannot convert FP instruction: " + var1);
      }
   }

   public void pC(cfq var1, List var2, String var3, boolean var4) {
      cff.Av var5 = this.pC(var1, var3);
      IEGeneric var6 = this.pC(((cfj[])var1.getOperands())[0], var5);
      IEMem var7 = this.pC.pC(var1, ((cfj[])var1.getOperands())[1], var5.A());
      if (var4) {
         IEGeneric var8 = var7.getReference();
         var7 = this.A.createMem(this.A.createCompose(this.pC.A(3), var8.slice(3, var8.getBitsize())), var7.getBitsize());
      }

      if (var7.getBitsize() > var6.getBitsize()) {
         var2.add(this.A.createAssign(var7, var6.zeroExtend(var7.getBitsize())));
      } else {
         var2.add(this.A.createAssign(var7, var6.part(var7.getBitsize())));
      }
   }

   public void A(cfq var1, List var2, String var3, boolean var4) {
      cff.Av var5 = this.pC(var1, var3);
      IEGeneric var6 = this.pC(((cfj[])var1.getOperands())[0], var5);
      IEMem var7 = this.pC.pC(var1, ((cfj[])var1.getOperands())[1], var5.A());
      if (var4) {
         IEGeneric var8 = var7.getReference();
         var7 = this.A.createMem(this.A.createCompose(this.pC.A(3), var8.slice(3, var8.getBitsize())), var7.getBitsize());
      }

      if (var6 instanceof IECompose) {
         int var11 = 0;

         for (IEGeneric var10 : var6.asCompose()) {
            var2.add(this.A.createAssign(var10, var7.slice(var11, var11 + var10.getBitsize())));
            var11 += var10.getBitsize();
         }
      } else if (var6.getBitsize() == var7.getBitsize()) {
         var2.add(this.A.createAssign(var6, var7));
      } else {
         var2.add(this.A.createAssign(var6.part(var7.getBitsize()), var7));
      }
   }

   private void kS(cfq var1, List var2) {
      String[] var3 = var1.getMnemonic().split("\\.");
      if (var3.length != 3) {
         throw new IllegalConversionException("Inconsistent " + var3[0] + ".cond.fmt");
      } else {
         cff.Av var4 = this.pC(var1, 1);
         int var5 = 0;
         Object var6 = this.pC.hK[0];
         if (((cfj[])var1.getOperands()).length == 3) {
            var6 = this.pC.pC(var1, var1.getOperand(0));
            var5++;
         }

         IEGeneric var7 = this.pC((cfj)var1.getOperand(var5), var4);
         IEGeneric var8 = this.pC((cfj)var1.getOperand(var5 + 1), var4);
         if (((IEGeneric)var6).getBitsize() == 1) {
            var2.add(this.A.createAssign((IEGeneric)var6, this.pC(var3[1], var7, var8)));
         } else {
            var2.add(this.A.createAssign((IEGeneric)var6, this.A.createCond(this.pC(var3[1], var7, var8), this.kS((IEGeneric)var6), this.pC((IEGeneric)var6))));
         }
      }
   }

   private IEGeneric pC(String var1, IEGeneric var2, IEGeneric var3) {
      boolean var4 = false;

      for (int var5 = 0; var5 < wS.length; var5++) {
         if (var1.equals(UT[var5])) {
            var1 = wS[var5];
            break;
         }

         if (var1.equals(E[var5])) {
            var1 = wS[var5];
            var4 = true;
         }
      }

      IEGeneric var6 = this.A(var1, var2, var3);
      return var4 ? EUtil.notL(var6) : var6;
   }

   private IEGeneric A(String var1, IEGeneric var2, IEGeneric var3) {
      for (int var4 = 0; var4 < wS.length; var4++) {
         if (var1.equals(wS[var4])) {
            boolean[] var5 = kS[var4 % 8];
            if (var5[0] && var5[1] && var5[2] && var5[3]) {
               return this.pC.Ab;
            }

            if (!var5[0] && !var5[1] && !var5[2] && !var5[3]) {
               return this.pC.UO;
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
               var10.add(this.A.createOperation(var5[2] ? OperationType.FGE : OperationType.FGT, var2, var3));
               var11 = false;
            }

            if (var5[1]) {
               var10.add(this.A.createOperation(var5[2] ? OperationType.FLE : OperationType.FLT, var2, var3));
               var11 = false;
            }

            if (var11 && var5[2]) {
               var10.add(this.A.createOperation(OperationType.FEQ, var2, var3));
            }

            if (var5[2]) {
               var10.add(
                  EUtil.orL(
                     EUtil.andL(this.A.createOperation(OperationType.FEQ, var2, this.pC(var2)), this.A.createOperation(OperationType.FEQ, var3, this.A(var2))),
                     EUtil.andL(this.A.createOperation(OperationType.FEQ, var2, this.A(var2)), this.A.createOperation(OperationType.FEQ, var3, this.pC(var2)))
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

   private IEImm pC(IEGeneric var1) {
      return var1.getBitsize() == 64 ? this.A.createImm(0.0) : this.A.createImm(0.0F).zeroExtend(var1.getBitsize());
   }

   private IEImm A(IEGeneric var1) {
      return var1.getBitsize() == 64
         ? this.A.createImm(Double.longBitsToDouble(Long.MIN_VALUE))
         : this.A.createImm(Float.intBitsToFloat(Integer.MIN_VALUE)).zeroExtend(var1.getBitsize());
   }

   private IEImm kS(IEGeneric var1) {
      return this.A.createImm(Float.intBitsToFloat(-1)).zeroExtend(var1.getBitsize());
   }

   private void wS(cfq var1, List var2) {
      cff.Av var3 = this.pC(var1);
      IEGeneric var4 = this.pC((cfj)var1.getOperand(0), var3);
      IEGeneric var5 = this.pC((cfj)var1.getOperand(1), var3);
      var2.add(this.A.createAssign(var4, var5));
   }

   public void pC(cfq var1, List var2, long var3, int var5) {
      IEGeneric var6 = this.pC.pC(var1, (IInstructionOperand)((cfj[])var1.getOperands())[0]);
      IEGeneric var7 = this.pC.pC(var1, (IInstructionOperand)((cfj[])var1.getOperands())[1], var3).part(var5);
      var2.add(this.A.createAssign(var6, var7.signExtend(var6.getBitsize())));
   }

   public void A(cfq var1, List var2, long var3, int var5) {
      IEGeneric var6 = this.pC.pC(var1, (IInstructionOperand)((cfj[])var1.getOperands())[0], var3).part(var5);
      IEGeneric var7 = this.pC.pC(var1, (IInstructionOperand)((cfj[])var1.getOperands())[1]);
      var2.add(this.A.createAssign(var7.part(var5), var6));
   }

   public void pC(cfq var1, List var2) {
      IEGeneric var3 = this.pC.pC(var1, (IInstructionOperand)((cfj[])var1.getOperands())[0]).part(32);
      long var5 = ((cfj[])var1.getOperands())[1].getOperandValue();
      Object var4;
      if (this.A(var1)) {
         if ((var5 & 1L) == 0L) {
            var5++;
         }

         var4 = this.pC.A(var5);
      } else {
         var4 = this.pC.A(var5).slice(32, 64);
      }

      var2.add(this.A.createAssign((IEGeneric)var4, var3));
   }

   public void A(cfq var1, List var2) {
      IEGeneric var3 = this.pC.pC(var1, (IInstructionOperand)((cfj[])var1.getOperands())[0]);
      long var5 = ((cfj[])var1.getOperands())[1].getOperandValue();
      Object var4;
      if (this.A(var1)) {
         if ((var5 & 1L) == 0L) {
            var5++;
         }

         var4 = this.pC.A(var5);
      } else {
         var4 = this.pC.A(var5).slice(32, 64);
      }

      var2.add(this.A.createAssign(var3, ((IEGeneric)var4).signExtend(var3.getBitsize())));
   }

   private void pC(cfq var1, List var2, long var3, OperationType var5) {
      cff.Av var6 = this.pC(var1);
      if (var6.pC()) {
         var2.add(this.pC.pC(var1, var3));
      } else {
         IEGeneric var7 = this.pC((cfj)var1.getOperand(0), var6);
         IEGeneric var8 = this.pC((cfj)var1.getOperand(1), var6);
         IEGeneric var9 = this.pC((cfj)var1.getOperand(2), var6);
         this.pC(var2, var5, var7, var8, var9, var6);
      }
   }

   private void pC(List var1, OperationType var2, IEGeneric var3, IEGeneric var4, IEGeneric var5, cff.Av var6) {
      if (var6 == cff.Av.E) {
         IEOperation var7 = this.A.createOperation(var2, var4.part(32), var5.part(32));
         IEOperation var8 = this.A.createOperation(var2, var4.slice(32, 64), var5.slice(32, 64));
         var1.add(this.A.createAssign(var3, this.A.createCompose(var7, var8)));
      } else {
         var1.add(this.A.createAssign(var3, this.A.createOperation(var2, var4, var5)));
      }
   }

   private void pC(cfq var1, List var2, OperationType var3, long var4) {
      cff.Av var6 = this.pC(var1);
      IEGeneric var7 = this.pC((cfj)var1.getOperand(0), var6);
      IEGeneric var8 = this.pC((cfj)var1.getOperand(1), var6);
      IEGeneric var9 = this.pC((cfj)var1.getOperand(2), var6);
      IEGeneric var10 = this.pC((cfj)var1.getOperand(3), var6);
      IEGeneric var11 = this.pC.Er.part(var7.getBitsize());
      this.pC(var2, OperationType.FMUL, var11, var9, var10, var6);
      this.pC(var2, var3, var7, var11, var8, var6);
   }

   private void pC(cfq var1, List var2, long var3) {
      cff.Av var5 = this.pC(var1, 0);
      cff.Av var6 = this.pC(var1, 1);
      long var7 = ((cfj[])var1.getOperands())[0].getOperandValue();
      IEVar var9 = this.pC.A(var7);
      IEGeneric var10 = this.pC(((cfj[])var1.getOperands())[1], var6);
      OperationType var11 = this.pC(var5, var6);
      if (var11 != null) {
         var2.add(this.A.createAssign(var9, this.A.createConversionOperation(var11, var10, var5.sY).zeroExtend(var9.getBitsize())));
      } else {
         ArrayList var12 = new ArrayList();
         ArrayList var13 = new ArrayList();
         var12.add(var9);
         if (var5 == cff.Av.wS) {
            var12.add(this.pC.A(var7 + 1L));
         }

         long var14 = ((cfj[])var1.getOperands())[1].getOperandValue();
         var13.add(this.pC.A(var14));
         if (var6 == cff.Av.wS) {
            var13.add(this.pC.A(var14 + 1L));
         }

         ArrayList var16 = new ArrayList(var12);
         var16.addAll(var13);
         IEUntranslatedInstruction var17 = this.pC.pC(var1, var3, (IEGeneric[])var16.toArray(new IEVar[var16.size()]));
         var17.addSideEffectDefinedVariable((IEVar[])var12.toArray(new IEVar[var12.size()]));
         var17.addSideEffectUsedVariable((IEVar[])var13.toArray(new IEVar[var13.size()]));
         var2.add(var17);
      }
   }

   private OperationType pC(cff.Av var1, cff.Av var2) {
      switch (var1) {
         case pC:
         case kS:
            switch (var2) {
               case pC:
               case kS:
               case wS:
                  return OperationType.FP2FP;
               case UT:
               case A:
                  return OperationType.INT2FP;
            }
         case wS:
         default:
            break;
         case UT:
         case A:
            switch (var2) {
               case pC:
               case kS:
               case wS:
                  return OperationType.FP2INT;
            }
      }

      return null;
   }

   cff.Av pC(cfq var1) {
      return this.pC(var1, 0);
   }

   private cff.Av pC(cfq var1, int var2) {
      String[] var3 = var1.getMnemonic().split("\\.");
      if (var3.length < 2) {
         throw new IllegalConversionException("Binary FP operation misses fmt: " + var1);
      } else {
         return this.pC(var1, var3[1 + var2]);
      }
   }

   private cff.Av pC(cfq var1, String var2) {
      switch (var2) {
         case "S":
            return cff.Av.pC;
         case "W":
            return cff.Av.A;
         case "D":
            if (this.A(var1)) {
               return cff.Av.wS;
            }

            return cff.Av.kS;
         case "L":
            if (this.A(var1)) {
               return cff.Av.wS;
            }

            return cff.Av.UT;
         case "PS":
            return cff.Av.E;
         default:
            throw new IllegalConversionException("Binary FP operation is not supported yet for " + var1);
      }
   }

   private boolean A(cfq var1) {
      return var1.getProcessorMode() != 64 && !this.pC.LM.A();
   }

   private IEGeneric pC(cfj var1, cff.Av var2) {
      long var3 = var1.getOperandValue();
      Object var5 = this.pC.A(var3);
      if (var2 == cff.Av.wS) {
         if ((var3 & 1L) != 0L) {
            var5 = this.pC.A(--var3);
         }

         var5 = this.A.createCompose((IEGeneric)var5, this.pC.A(var3 + 1L));
      }

      if (var2.A() < ((IEGeneric)var5).getBitsize()) {
         return ((IEGeneric)var5).part(var2.A());
      } else if (var2.A() > ((IEGeneric)var5).getBitsize()) {
         throw new IllegalConversionException("Invalid operand: is not a fp register " + var1);
      } else {
         return (IEGeneric)var5;
      }
   }

   public static enum Av {
      pC(32),
      A(32, false),
      kS(64),
      wS(32, 32),
      UT(64, false),
      E(-1);

      private final int sY;
      private int ys = 0;
      private boolean ld = true;

      private Av(int var3) {
         this.sY = var3;
      }

      private Av(int var3, int var4) {
         this.sY = var3;
         this.ys = var4;
      }

      private Av(int var3, boolean var4) {
         this.sY = var3;
         this.ld = var4;
      }

      boolean pC() {
         return this.sY == -1;
      }

      int A() {
         return this.sY + this.ys;
      }
   }
}
