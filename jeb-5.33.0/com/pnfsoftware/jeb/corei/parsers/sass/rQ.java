package com.pnfsoftware.jeb.corei.parsers.sass;

import com.pnfsoftware.jeb.core.units.code.FlowInformation;
import com.pnfsoftware.jeb.core.units.code.InstructionFlags;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.AbstractConverter;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ConverterInstructionEntry;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.UnsupportedConversionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.FunctionOptype;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECompose;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEJump;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IENop;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEUntranslatedInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterUtil;
import com.pnfsoftware.jeb.util.base.Throwables;
import com.pnfsoftware.jeb.util.collect.ArrayList1;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.primitives.Floats;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Ser
public class rQ extends AbstractConverter {
   private static final ILogger eP = GlobalLog.getLogger(rQ.class);
   private static final Map UO = new HashMap();
   public static int pC;
   public static int A;
   public static int kS;
   public static int wS;
   public static int UT;
   public static int E;
   public static int sY;
   public static int ys;
   @SerId(1)
   vi ld;
   @SerId(2)
   IEVar gp;
   @SerId(3)
   IEVar oT;
   @SerId(4)
   IEVar[] fI;
   @SerId(5)
   IEVar[] WR;
   @SerId(6)
   IEVar[] NS;
   @SerId(7)
   IEVar[] vP;
   @SerId(8)
   IEVar[] xC;
   @SerId(10)
   IEVar[] ED;
   @SerId(11)
   IEVar[] Sc;
   @SerId(12)
   IEVar ah;

   public rQ(vi var1) {
      super(var1);
      this.ld = var1;
      this.gp = this.gCtx.createRegister(E, "PC", var1.getPCRegisterBitsize());
      this.oT = this.gCtx.createRegister(sY, "SP", var1.getPCRegisterBitsize());
      int var2 = 256;
      this.fI = new IEVar[var2];

      for (int var3 = 0; var3 < var2; var3++) {
         int var4 = var3 * 32 + pC;
         this.fI[var3] = this.gCtx.createRegister(var4, "R" + var3, 32);
      }

      if (var2 > 0) {
         this.fI[var2 - 1].setFlags(16);
      }

      var2 = var1.UT();
      this.WR = new IEVar[var2];

      for (int var9 = 0; var9 < var2; var9++) {
         int var14 = var9 * 32 + A;
         this.WR[var9] = this.gCtx.createRegister(var14, "UR" + var9, 32);
      }

      if (var2 > 0) {
         this.WR[var2 - 1].setFlags(16);
      }

      short var6 = 256;
      this.xC = new IEVar[var6];

      for (int var10 = 0; var10 < var6; var10++) {
         int var15 = var10 * 64 + kS;
         this.xC[var10] = this.gCtx.createRegister(var15, "SR" + var10, 64);
      }

      if (var6 > 0) {
         this.xC[var6 - 1].setFlags(16);
      }

      int var7 = 8;
      this.NS = new IEVar[var7];

      for (int var11 = 0; var11 < var7; var11++) {
         int var16 = var11 * 1 + wS;
         this.NS[var11] = this.gCtx.createRegister(var16, "P" + var11, 1);
      }

      if (var7 > 0) {
         this.NS[var7 - 1].setFlags(16);
      }

      var7 = var1.E();
      this.vP = new IEVar[var7];

      for (int var12 = 0; var12 < var7; var12++) {
         int var17 = var12 * 1 + UT;
         this.vP[var12] = this.gCtx.createRegister(var17, "UP" + var12, 1);
      }

      if (var7 > 0) {
         this.vP[var7 - 1].setFlags(16);
      }

      int var13 = ys;
      this.ED = new IEVar[32];

      for (int var18 = 0; var18 < 32; var18++) {
         this.ED[var18] = this.gCtx.createVirtualRegister(var13, "TEMP" + var18, 32);
         var13 += 32;
      }

      this.Sc = new IEVar[32];

      for (int var19 = 0; var19 < 32; var19++) {
         this.Sc[var19] = this.gCtx.createVirtualRegister(var13, "TPRED" + var19, 1);
         var13++;
      }

      this.ah = this.gCtx.createVirtualRegister(var13, "UNSUPPORTED_OPERAND", 32);
   }

   @Override
   public IEVar getProgramCounter() {
      return this.gp;
   }

   @Override
   public IEVar getStackPointer() {
      return this.oT;
   }

   @Override
   public IEVar getTempRegister(int var1) {
      return this.ED[var1];
   }

   public IEVar pC(int var1) {
      return this.Sc[var1];
   }

   @Override
   protected boolean convertInstructionFirstChance(ConverterInstructionEntry var1, String var2, boolean var3) {
      int var4 = var1.r.size();
      IEJump var5 = null;
      if (((sy)var1.insn).E != 7 || ((sy)var1.insn).wS) {
         Object var6 = ((sy)var1.insn).UT ? this.vP[((sy)var1.insn).E] : this.NS[((sy)var1.insn).E];
         if (!((sy)var1.insn).wS) {
            var6 = EUtil.notL((IEGeneric)var6);
         }

         var5 = this.ctx.createJump(-1, (IEGeneric)var6);
         var1.r.add(var5);
      }

      try {
         Method var11 = (Method)UO.get(var2);
         if (var11 == null) {
            throw new UnsupportedConversionException();
         }

         try {
            var11.invoke(this, var1);
         } catch (InvocationTargetException var8) {
            Throwables.rethrowUnchecked(var8.getTargetException());
            return false;
         } catch (ReflectiveOperationException var9) {
            return false;
         }
      } catch (UnsupportedConversionException var10) {
         this.pC(var1);
      }

      if (var5 != null) {
         var5.setBranchAddress(var1.irAddress + (var1.r.size() - var4));
      }

      return true;
   }

   @Override
   protected boolean convertInstructionLastChance(ConverterInstructionEntry var1) {
      return false;
   }

   void pC(ConverterInstructionEntry var1) {
      this.pC(var1, false);
   }

   void pC(ConverterInstructionEntry var1, boolean var2) {
      int var3 = this.ld.wS().pC(((sy)var1.insn).A).ED;
      ArrayList var4 = new ArrayList(var3);
      ArrayList var5 = new ArrayList(((sy)var1.insn).ys.length - var3);
      int var6 = 0;

      for (oP var10 : ((sy)var1.insn).ys) {
         Object var11;
         try {
            var11 = this.pC(var1.address, (sy)var1.insn, var10);
         } catch (UnsupportedConversionException var12) {
            var11 = this.ah;
         }

         if (var6 != 0 || !var2 || var3 != 0) {
            if (var6 < var3) {
               var4.add(this.gp((IEGeneric)var11));
            } else {
               var5.add(var11);
            }
         }

         var6++;
      }

      IEUntranslatedInstruction var13 = this.ctx.createUntranslatedInstruction(var1.address, ((sy)var1.insn).pC(false), var5);
      var13.setReturnExpressions(var4);
      var1.r.add(var13);
   }

   protected IEGeneric pC(long var1, sy var3, int var4) {
      return this.pC(var1, var3, var3.ys[var4]);
   }

   protected IEGeneric pC(ConverterInstructionEntry var1, int var2) {
      return this.pC(var1.address, (sy)var1.insn, ((sy)var1.insn).ys[var2]);
   }

   protected IEGeneric A(ConverterInstructionEntry var1, int var2) {
      return this.pC(var1.address, (sy)var1.insn, ((sy)var1.insn).ys[var2], 0, true);
   }

   protected IEGeneric pC(ConverterInstructionEntry var1, int var2, int var3) {
      return this.pC(var1.address, (sy)var1.insn, ((sy)var1.insn).ys[var2], var3, true);
   }

   protected IEGeneric A(ConverterInstructionEntry var1, int var2, int var3) {
      return this.pC(var1.address, (sy)var1.insn, ((sy)var1.insn).ys[var2], var3, false);
   }

   protected IEGeneric pC(long var1, sy var3, oP var4) {
      return this.pC(var1, var3, var4, 0, false);
   }

   protected IEGeneric pC(long var1, sy var3, oP var4, int var5, boolean var6) {
      byte var8 = 1;

      for (qt var10 : var4.UT) {
         String var11 = var10.pC;
         switch (var11) {
            case "REUSE":
            case "HSEL":
            case "EXTRACT_mufu":
            case "ISWZ":
            case "ISWZA":
            case "ISWZB":
               break;
            case "ONLY64":
               if (var5 == 0) {
                  String var39 = var10.A;
                  byte var42 = -1;
                  switch (var39.hashCode()) {
                     case 1726:
                        if (var39.equals("64")) {
                           var42 = 0;
                        }
                     default:
                        switch (var42) {
                           case 0:
                              var5 = 64;
                              break;
                           default:
                              throw new UnsupportedConversionException();
                        }
                  }
               }
               break;
            case "STRIDE":
               String var13 = var10.A;

               var8 = switch (var13) {
                  case "X1" -> 1;
                  case "X4" -> 4;
                  case "X8" -> 8;
                  case "X16" -> 16;
                  default -> throw new UnsupportedConversionException();
               };
               break;
            case "B3B0":
               if (var5 == 0) {
                  var5 = 8;
               } else {
                  this.pC(var5 == 8);
               }
               break;
            default:
               throw new UnsupportedConversionException(Strings.ff("TBI: modifier on operand: %s (%s)", var10.pC, var10.A));
         }
      }

      int var19 = var4.getOperandType();
      Object var7;

      var7 = switch (var19) {
         case 0 -> {
            long var27 = var4.getOperandValue();
            int var38 = RegisterUtil.getRegisterGroup(var27);
            int var41 = RegisterUtil.getRegisterIndex(var27);

            IEVar[] var45 = switch (var38) {
               case 0 -> this.fI;
               case 1 -> this.xC;
               case 2 -> this.NS;
               default -> throw new UnsupportedConversionException();
               case 4 -> this.WR;
               case 5 -> this.vP;
            };
            yield var45[var41];
            if (var38 == 0 || var38 == 4) {
               if (var41 == var45.length - 1) {
                  yield EUtil.zero(var5 == 0 ? 32 : var5);
               } else if (var5 != 0 && var5 != 32) {
                  if (var5 == 64) {
                     yield this.ctx.createCompose((IEGeneric)var7, var45[var41 + 1]);
                  } else if (var5 == 128) {
                     yield this.ctx.createCompose((IEGeneric)var7, var45[var41 + 1], var45[var41 + 2], var45[var41 + 3]);
                  } else if (var5 < 32) {
                     yield ((IEGeneric)var7).part(var5);
                  }
               }

               if (var8 != 1) {
                  yield EUtil.mul((IEGeneric)var7, EUtil.imm(var8, ((IEGeneric)var7).getBitsize()));
               }
            } else if (var38 == 1) {
               if (var41 == var45.length - 1) {
                  yield EUtil.zero(var5 == 0 ? 32 : var5);
               } else {
                  String var46 = this.ld.pC(var27, true);
                  if (var5 == 32 && var46 != null && var46.startsWith("SR_")) {
                     var46 = var46.substring(3);
                     IEVar var16 = this.gCtx.getVariableByName(var46);
                     if (var16 == null) {
                        var16 = this.gCtx.createVirtualRegister(var46, 32);
                        var16.setFlags(16);
                     }

                     yield var16;
                  }
               }
            }

            if (var4.E != null) {
               for (oP var49 : var4.E) {
                  IEGeneric var17 = this.pC(var1, var3, var49);
                  if (!EUtil.isZero(var17)) {
                     yield EUtil.add((IEGeneric)var7, EUtil.resize(var17, ((IEGeneric)var7).getBitsize(), true));
                  }
               }
            }
         }
         case 1 -> {
            yield this.ctx.createImm(var4.getOperandValue(), var4.getOperandBitsize());
            if (var5 > ((IEGeneric)var7).getBitsize()) {
               yield ((IEGeneric)var7).zeroExtend(var5);
            }
         }
         case 9 -> {
            yield this.ctx.createImm(var4.getOperandValue(), var4.getOperandBitsize());
            if (var5 > ((IEGeneric)var7).getBitsize()) {
               yield ((IEGeneric)var7).signExtend(var5);
            }
         }
         case 4096 -> throw new UnsupportedConversionException("Operand type UNKNOWN");
         case 4097 -> {
            IEImm var24 = EUtil.imm(var3.getSize(), this.getAddressBitsize());
            var24 = var24._add(this.ctx.createImm(var4.getOperandValue(), var4.getOperandBitsize()).signExtend(var24.getBitsize()));
            var24 = var24._add(EUtil.imm(var1, this.getAddressBitsize()).zeroExtend(var24.getBitsize()));
            yield var24;
         }
         case 4099 -> {
            int var23 = this.getAddressBitsize();
            int var33 = var4.pC();
            if (var33 != 0) {
               if (var33 == 1) {
                  var7 = EUtil.imm(268435456L, var23);
                  IEGeneric var35 = this.pC(var1, var3, var4.A());
                  if (!EUtil.isZero(var35)) {
                     var7 = EUtil.add((IEGeneric)var7, EUtil.mul(var35.zeroExtend(var23), EUtil.imm(16777216L, var23)));
                  }
               } else {
                  if (var33 != 2 && var33 != 3) {
                     throw new RuntimeException();
                  }

                  var7 = this.pC(var1, var3, var4.A(), var23, false);
               }
            } else {
               var7 = EUtil.imm(805306368L, var23);
            }

            for (oP var40 : var4.kS()) {
               IEGeneric var43 = this.pC(var1, var3, var40);
               var43 = var43.extend(var23, var40.getOperandType() == 9);
               if (!EUtil.isZero(var43)
                  && !this.ld(var43)
                  && !(var43 instanceof IECompose var15 && var15.getPartsCount() == 2 && this.ld(var15.getLowPart()) && EUtil.isZero(var15.getHighPart()))) {
                  var7 = EUtil.add((IEGeneric)var7, var43);
               }
            }

            int var37 = var4.getOperandBitsize() != 0 ? var4.getOperandBitsize() : 32;
            if (var5 != 0) {
               var37 = var5;
            }

            yield this.ctx.createMem((IEGeneric)var7, var37);
         }
         case 4100 -> {
            if (var4.getOperandBitsize() == 32) {
               int var20 = (int)var4.getOperandValue();
               float var31 = Float.intBitsToFloat(var20);
               yield this.ctx.createImm(var31);
            } else if (var4.getOperandBitsize() == 64) {
               long var21 = var4.getOperandValue();
               double var34 = Double.longBitsToDouble(var21);
               yield this.ctx.createImm(var34);
            } else {
               if (var4.getOperandBitsize() != 16) {
                  throw new UnsupportedConversionException();
               }

               int var22 = (int)var4.getOperandValue();
               float var32 = Floats.fromFP16Bits(var22);
               yield this.ctx.createImm(var32);
            }
         }
         default -> throw new UnsupportedConversionException("Operand type: " + var19);
      };

      if (var4.wS != 0) {
         if (var6) {
            this.pC(var19 != 1 && var19 != 9);
            if ((var4.wS & 1) != 0) {
               throw new UnsupportedConversionException();
            }

            if ((var4.wS & 2) != 0) {
               FunctionOptype var28 = this.gCtx.createFunctionType("FNEG", 0, 1, 0);
               var7 = this.ctx.createOperation(var28, (IEGeneric)var7);
            }

            if ((var4.wS & 4) != 0) {
               FunctionOptype var29 = this.gCtx.createFunctionType("FABS", 0, 1, 0);
               var7 = this.ctx.createOperation(var29, (IEGeneric)var7);
            }

            if ((var4.wS & 8) != 0) {
               FunctionOptype var30 = this.gCtx.createFunctionType("FINV", 0, 1, 0);
               var7 = this.ctx.createOperation(var30, (IEGeneric)var7);
            }
         } else {
            this.pC(var19 != 4100);
            if ((var4.wS & 1) != 0) {
               this.pC(((IEGeneric)var7).getBitsize() == 1);
               var7 = this.ctx.createOperation(OperationType.LOG_NOT, (IEGeneric)var7);
            }

            if ((var4.wS & 2) != 0) {
               var7 = this.ctx.createOperation(OperationType.SUB, EUtil.zero(((IEGeneric)var7).getBitsize()), (IEGeneric)var7);
            }

            if ((var4.wS & 4) != 0) {
               var7 = this.ctx
                  .createCond(
                     ((IEGeneric)var7).msb(),
                     this.ctx.createOperation(OperationType.SUB, EUtil.zero(((IEGeneric)var7).getBitsize()), (IEGeneric)var7),
                     (IEGeneric)var7
                  );
            }

            if ((var4.wS & 8) != 0) {
               var7 = this.ctx.createOperation(OperationType.NOT, (IEGeneric)var7);
            }
         }
      }

      return (IEGeneric)var7;
   }

   private boolean pC(ConverterInstructionEntry var1, IEGeneric var2, IEGeneric var3) {
      Object var4;
      if (!this.ld(var2) && !EUtil.isZero(var2)) {
         var4 = this.ctx.createAssign(var2, var3);
      } else {
         var4 = this.ctx.createNop();
      }

      var1.r.add(var4);
      return !((IEStatement)var4).isNop();
   }

   private IENop ET(ConverterInstructionEntry var1) {
      IENop var2 = this.ctx.createNop();
      var1.r.add(var2);
      return var2;
   }

   private boolean pC(IEGeneric var1) {
      return var1 == this.NS[7];
   }

   private boolean A(IEGeneric var1) {
      return EUtil.notL(this.NS[7]).equalsEx(var1, false);
   }

   private boolean kS(IEGeneric var1) {
      return this.vP.length > 0 && var1 == this.vP[7];
   }

   private boolean wS(IEGeneric var1) {
      return this.vP.length > 0 && EUtil.notL(this.vP[7]).equalsEx(var1, false);
   }

   private boolean UT(IEGeneric var1) {
      return this.pC(var1) || this.kS(var1);
   }

   private boolean E(IEGeneric var1) {
      return this.A(var1) || this.wS(var1);
   }

   private boolean sY(IEGeneric var1) {
      return var1 == this.fI[255];
   }

   private boolean ys(IEGeneric var1) {
      return this.WR.length > 0 && var1 == this.WR[this.WR.length - 1];
   }

   private boolean ld(IEGeneric var1) {
      return this.sY(var1) || this.ys(var1);
   }

   private int kS(ConverterInstructionEntry var1, int var2) {
      int var3 = ((sy)var1.insn).ys.length;
      this.pC(var3 == var2);
      return var3;
   }

   private int kS(ConverterInstructionEntry var1, int var2, int var3) {
      int var4 = ((sy)var1.insn).ys.length;
      this.pC(var4 == var2 || var4 == var3);
      return var4;
   }

   private int pC(ConverterInstructionEntry var1, int var2, int var3, int var4) {
      int var5 = ((sy)var1.insn).ys.length;
      this.pC(var5 == var2 || var5 == var3 || var5 == var4);
      return var5;
   }

   private int pC(ConverterInstructionEntry var1, int var2, int var3, int var4, int var5) {
      int var6 = ((sy)var1.insn).ys.length;
      this.pC(var6 == var2 || var6 == var3 || var6 == var4 || var6 == var4);
      return var6;
   }

   private String pC(ConverterInstructionEntry var1, String var2) {
      return ((sy)var1.insn).pC(var2);
   }

   private String pC(ConverterInstructionEntry var1, String var2, String var3) {
      String var4 = ((sy)var1.insn).pC(var2);
      if (var4 == null) {
         var4 = ((sy)var1.insn).pC(var3);
      }

      return var4;
   }

   private String pC(ConverterInstructionEntry var1, String var2, String var3, String var4) {
      String var5 = ((sy)var1.insn).pC(var2);
      if (var5 == null) {
         var5 = ((sy)var1.insn).pC(var3);
         if (var5 == null) {
            var5 = ((sy)var1.insn).pC(var4);
         }
      }

      return var5;
   }

   private String pC(ConverterInstructionEntry var1, String... var2) {
      for (String var6 : var2) {
         String var7 = ((sy)var1.insn).pC(var6);
         if (var7 != null) {
            return var7;
         }
      }

      return null;
   }

   private String A(ConverterInstructionEntry var1, String var2) {
      String var3 = this.pC(var1, var2);
      this.pC(var3 != null, Strings.ff("Expected non-null modifier for '%s'", var2));
      return var3;
   }

   private boolean A(ConverterInstructionEntry var1, String var2, String var3) {
      String var4 = this.pC(var1, var2);
      return var4 == null ? var3 == null : var4.equalsIgnoreCase(var3);
   }

   private void kS(ConverterInstructionEntry var1, String var2, String var3) {
      boolean var4 = this.A(var1, var2, var3);
      this.pC(var4, "Expected modifier value '%s' for '%s'", var3, var2);
   }

   private void pC(boolean var1) {
      this.pC(var1, null);
   }

   private void pC(boolean var1, String var2, Object... var3) {
      if (!var1) {
         String var4 = "";
         if (var2 != null) {
            var4 = ": " + Strings.ff(var2, var3);
         }

         throw new UnsupportedConversionException("Limitation" + var4);
      }
   }

   private IEGeneric gp(IEGeneric var1) {
      if (EUtil.isZero(var1)) {
         int var2 = var1.getBitsize();
         IEVar var3 = this.getTempRegister(4);
         if (var2 == 32) {
            return var3;
         } else if (var2 < 32) {
            return var3.part(var2);
         } else if (var2 == 64) {
            return this.ctx.createCompose(var3, this.getTempRegister(5));
         } else {
            throw new UnsupportedConversionException();
         }
      } else {
         return var1;
      }
   }

   @AbstractConverter.Mnemonic("FADD")
   void A(ConverterInstructionEntry var1) {
      this.kS(var1, 3);
      this.kS(var1, "SAT", "nosat");
      IEGeneric var2 = this.gp(this.pC(var1, 0));
      IEGeneric var3 = this.A(var1, 1);
      IEGeneric var4 = this.A(var1, 2);
      IEOperation var5 = this.ctx.createOperation(OperationType.FADD, var3, var4);
      this.pC(var1, var2, var5);
   }

   @AbstractConverter.Mnemonic("FFMA")
   void kS(ConverterInstructionEntry var1) {
      this.kS(var1, 4);
      this.kS(var1, "SAT", "nosat");
      IEGeneric var2 = this.gp(this.pC(var1, 0));
      IEGeneric var3 = this.A(var1, 1);
      IEGeneric var4 = this.A(var1, 2);
      IEGeneric var5 = this.A(var1, 3);
      IEOperation var6 = this.ctx.createOperation(OperationType.FADD, this.ctx.createOperation(OperationType.FMUL, var3, var4), var5);
      this.pC(var1, var2, var6);
   }

   @AbstractConverter.Mnemonic("FMNMX")
   void wS(ConverterInstructionEntry var1) {
      int var2 = this.kS(var1, 4, 5);
      this.pC(var2 == 4);
      String var3 = this.pC(var1, "XORSIGN");
      this.pC(var3 == null || var3.equals("noxorsign"));
      String var4 = this.pC(var1, "IS_AONLY");
      this.pC(var4 == null, "FMNMX 5-opnd version is not supported");
      IEGeneric var5 = this.A(var1, 0);
      IEGeneric var6 = this.A(var1, 1);
      IEGeneric var7 = this.A(var1, 2);
      IEGeneric var8 = this.pC(var1, 3);
      String var9;
      if (this.UT(var8)) {
         var9 = "FMIN";
      } else {
         if (!this.E(var8)) {
            throw new UnsupportedConversionException();
         }

         var9 = "FMAX";
      }

      FunctionOptype var10 = this.gCtx.createFunctionType(var9, 1, 2, 0);
      IEOperation var11 = this.ctx.createOperation(var10, var6, var7);
      this.pC(var1, var5, var11);
   }

   @AbstractConverter.Mnemonic("FMUL")
   void UT(ConverterInstructionEntry var1) {
      this.kS(var1, 3);
      String var2 = this.A(var1, "Scale");
      this.pC(var2.equals("noscale"));
      String var3 = this.A(var1, "SAT");
      this.pC(var3.equalsIgnoreCase("nosat"));
      IEGeneric var4 = this.gp(this.pC(var1, 0));
      IEGeneric var5 = this.A(var1, 1);
      IEGeneric var6 = this.A(var1, 2);
      IEOperation var7 = this.ctx.createOperation(OperationType.FMUL, var5, var6);
      this.pC(var1, var4, var7);
   }

   @AbstractConverter.Mnemonic("FSEL")
   void E(ConverterInstructionEntry var1) {
      this.kS(var1, 4);
      IEGeneric var2 = this.gp(this.pC(var1, 0));
      IEGeneric var3 = this.A(var1, 1);
      IEGeneric var4 = this.A(var1, 2);
      IEGeneric var5 = this.pC(var1, 3);
      IECond var6 = this.ctx.createCond(var5, var3, var4);
      this.pC(var1, var2, var6);
   }

   @AbstractConverter.Mnemonic("FSET")
   void sY(ConverterInstructionEntry var1) {
      this.kS(var1, 4);
      throw new UnsupportedConversionException();
   }

   @AbstractConverter.Mnemonic("FSETP")
   void ys(ConverterInstructionEntry var1) {
      this.kS(var1, 5);
      String var2 = this.A(var1, "FCMP");
      this.A(var1, "FTZ");
      String var3 = this.A(var1, "Bop");

      OperationType var4 = switch (var2) {
         case "EQ" -> OperationType.FEQ;
         case "EQU" -> OperationType.FEQ;
         case "NE" -> OperationType.FNE;
         case "NEU" -> OperationType.FNE;
         case "LT" -> OperationType.FLT;
         case "LTU" -> OperationType.FLT;
         case "LE" -> OperationType.FLE;
         case "LEU" -> OperationType.FLE;
         case "GT" -> OperationType.FGT;
         case "GTU" -> OperationType.FGT;
         case "GE" -> OperationType.FGE;
         case "GEU" -> OperationType.FGE;
         default -> throw new UnsupportedConversionException();
      };

      OperationType var5 = switch (var3) {
         case "AND" -> OperationType.AND;
         case "OR" -> OperationType.OR;
         case "XOR" -> OperationType.XOR;
         default -> throw new UnsupportedConversionException();
      };
      IEGeneric var12 = this.gp(this.pC(var1, 0));
      IEGeneric var13 = this.pC(var1, 1);
      IEGeneric var8 = this.A(var1, 2);
      IEGeneric var9 = this.A(var1, 3);
      IEGeneric var10 = this.pC(var1, 4);
      Object var11 = this.ctx.createOperation(var4, var8, var9);
      if (!this.UT(var13)) {
         this.pC(var1, var13, (IEGeneric)var11);
         var11 = var13;
      }

      if (var5 != OperationType.AND || !this.UT(var10)) {
         var11 = this.ctx.createOperation(var5, (IEGeneric)var11, var10);
      }

      this.pC(var1, var12, (IEGeneric)var11);
   }

   @AbstractConverter.Mnemonic("MUFU")
   void ld(ConverterInstructionEntry var1) {
      int var2 = this.kS(var1, 2, 3);
      this.pC(var2 == 2);
      IEGeneric var3 = this.gp(this.pC(var1, 0));
      Object var4 = this.A(var1, 1);
      String var5 = this.pC(var1, "MUFU_OP", "MUFUOP_COS_SIN_EX2_LG2_RCP_RSQ_SQRT_TANH", "OP_COS_SIN_EX2_LG2_RCP_RSQ_SQRT_TANH");
      switch (var5) {
         case "RCP":
         case "RSQ":
         case "SIN":
         case "COS":
         case "LG2":
         case "EX2":
         case "SQRT":
         case "TANH":
            this.pC(this.pC(var1, "Float16", "FMT_F16_BF16", "FMT_F16x2_BF16x2") == null);
            this.pC(this.pC(var1, "EX2ONLY") == null);
            if (var4 instanceof IEImm var6 && var6.getBitsize() == 64) {
               double var10 = var6.getValueAsDoubleFloat();
               var4 = this.ctx.createImm((float)var10);
            }

            FunctionOptype var9 = this.gCtx.createFunctionType(var5, 0, 1, 32);
            this.pC(var1, var3, this.ctx.createOperation(var9, (IEGeneric)var4));
            return;
         default:
            throw new UnsupportedConversionException();
      }
   }

   @AbstractConverter.Mnemonic("HADD2")
   void gp(ConverterInstructionEntry var1) {
      this.kS(var1, 3, 4);
      this.pC(var1);
   }

   @AbstractConverter.Mnemonic("HFMA2")
   void oT(ConverterInstructionEntry var1) {
      this.pC(var1, 4, 5, 6);
      this.pC(var1);
   }

   @AbstractConverter.Mnemonic("HFMA2.MMA")
   void fI(ConverterInstructionEntry var1) {
      this.pC(var1, 4, 5, 6);
      this.pC(var1);
   }

   @AbstractConverter.Mnemonic("HMMA")
   void WR(ConverterInstructionEntry var1) {
      this.pC(var1, 4, 5, 6, 7);
      throw new UnsupportedConversionException();
   }

   @AbstractConverter.Mnemonic("DADD")
   void NS(ConverterInstructionEntry var1) {
      this.kS(var1, 3);
      IEGeneric var2 = this.gp(this.A(var1, 0, 64));
      IEGeneric var3 = this.pC(var1, 1, 64);
      IEGeneric var4 = this.pC(var1, 2, 64);
      IEOperation var5 = this.ctx.createOperation(OperationType.FADD, var3, var4);
      this.pC(var1, var2, var5);
   }

   @AbstractConverter.Mnemonic("DFMA")
   void vP(ConverterInstructionEntry var1) {
      this.kS(var1, 4);
      IEGeneric var2 = this.gp(this.A(var1, 0, 64));
      IEGeneric var3 = this.pC(var1, 1, 64);
      IEGeneric var4 = this.pC(var1, 2, 64);
      IEGeneric var5 = this.pC(var1, 3, 64);
      IEOperation var6 = this.ctx.createOperation(OperationType.FADD, this.ctx.createOperation(OperationType.FMUL, var3, var4), var5);
      this.pC(var1, var2, var6);
   }

   @AbstractConverter.Mnemonic("DMUL")
   void xC(ConverterInstructionEntry var1) {
      this.kS(var1, 3);
      String var2 = this.A(var1, "Round1");
      this.pC(var2.equalsIgnoreCase("RN"));
      IEGeneric var3 = this.gp(this.A(var1, 0, 64));
      IEGeneric var4 = this.pC(var1, 1, 64);
      IEGeneric var5 = this.pC(var1, 2, 64);
      IEOperation var6 = this.ctx.createOperation(OperationType.FMUL, var4, var5);
      this.pC(var1, var3, var6);
   }

   @AbstractConverter.Mnemonic("DSETP")
   void ED(ConverterInstructionEntry var1) {
      this.kS(var1, 5);
      String var2 = this.A(var1, "DSETP_FCMP");
      String var3 = this.A(var1, "Bop");

      OperationType var4 = switch (var2) {
         case "EQ" -> OperationType.FEQ;
         case "EQU" -> OperationType.FEQ;
         case "NE" -> OperationType.FNE;
         case "NEU" -> OperationType.FNE;
         case "LT" -> OperationType.FLT;
         case "LTU" -> OperationType.FLT;
         case "LE" -> OperationType.FLE;
         case "LEU" -> OperationType.FLE;
         case "GT" -> OperationType.FGT;
         case "GTU" -> OperationType.FGT;
         case "GE" -> OperationType.FGE;
         case "GEU" -> OperationType.FGE;
         default -> throw new UnsupportedConversionException();
      };

      OperationType var5 = switch (var3) {
         case "AND" -> OperationType.AND;
         case "OR" -> OperationType.OR;
         case "XOR" -> OperationType.XOR;
         default -> throw new UnsupportedConversionException();
      };
      IEGeneric var12 = this.gp(this.pC(var1, 0));
      IEGeneric var13 = this.pC(var1, 1);
      IEGeneric var8 = this.pC(var1, 2, 64);
      IEGeneric var9 = this.pC(var1, 3, 64);
      IEGeneric var10 = this.pC(var1, 4);
      Object var11 = this.ctx.createOperation(var4, var8, var9);
      if (!this.UT(var13)) {
         this.pC(var1, var13, (IEGeneric)var11);
         var11 = var13;
      }

      if (var5 != OperationType.AND || !this.UT(var10)) {
         var11 = this.ctx.createOperation(var5, (IEGeneric)var11, var10);
      }

      this.pC(var1, var12, (IEGeneric)var11);
   }

   @AbstractConverter.Mnemonic("BMMA")
   void Sc(ConverterInstructionEntry var1) {
      throw new UnsupportedConversionException();
   }

   @AbstractConverter.Mnemonic("BMSK")
   void ah(ConverterInstructionEntry var1) {
      throw new UnsupportedConversionException();
   }

   @AbstractConverter.Mnemonic("BREV")
   void eP(ConverterInstructionEntry var1) {
      throw new UnsupportedConversionException();
   }

   @AbstractConverter.Mnemonic("FLO")
   void UO(ConverterInstructionEntry var1) {
      this.kS(var1, 3);
      throw new UnsupportedConversionException();
   }

   @AbstractConverter.Mnemonic("IABS")
   void Ab(ConverterInstructionEntry var1) {
      throw new UnsupportedConversionException();
   }

   @AbstractConverter.Mnemonic("IADD")
   void rl(ConverterInstructionEntry var1) {
      throw new UnsupportedConversionException();
   }

   @AbstractConverter.Mnemonic("IADD3")
   void z(ConverterInstructionEntry var1) {
      int var2 = this.kS(var1, 6, 8);
      IEGeneric var3 = this.gp(this.pC(var1, 0));
      IEGeneric var4 = this.pC(var1, 1);
      IEGeneric var5 = this.pC(var1, 2);
      IEGeneric var6 = this.pC(var1, 3);
      Object var7 = this.pC(var1, 4);
      IEGeneric var8 = this.pC(var1, 5);
      IEGeneric var9 = var2 == 8 ? this.pC(var1, 6) : null;
      IEGeneric var10 = var2 == 8 ? this.pC(var1, 7) : null;
      int var11 = var6.getBitsize();
      this.pC(((IEGeneric)var7).getBitsize() == var11);
      this.pC(var8.getBitsize() == var11);
      String var12 = this.pC(var1, "XONLY");
      this.pC(var2 == 6 || var12.equals("X"));
      IEVar var13 = this.getTempRegister(0);
      if (this.UT(var5)) {
         if (var2 == 6 && (this.ld(var8) || EUtil.isZero(var8))) {
            IEOperation var18 = EUtil.add(var6, (IEGeneric)var7);
            if (this.UT(var4)) {
               this.pC(var1, var3, var18);
            } else {
               this.pC(var1, var13, var18);
               if (!this.UT(var4)) {
                  this.pC(var1, var4, EUtil.ltU(var13, var6));
               }

               this.pC(var1, var3, var13);
            }

            return;
         }

         if (var2 == 8 && this.UT(var4)) {
            IEOperation var17 = EUtil.add(var6, (IEGeneric)var7);
            if (!this.ld(var8) && !EUtil.isZero(var8)) {
               var17 = EUtil.add(var17, var8);
            }

            if (!this.E(var9)) {
               var17 = EUtil.add(var17, var9.zeroExtend(var11));
            }

            if (!this.E(var10)) {
               var17 = EUtil.add(var17, var10.zeroExtend(var11));
            }

            this.pC(var1, var3, var17);
            return;
         }
      }

      if (var7 instanceof IEMem) {
         this.pC(var1, var13, (IEGeneric)var7);
         var7 = var13;
      }

      ArrayList var14 = new ArrayList(5);
      var14.add(var6);
      var14.add(var7);
      var14.add(var8);
      IEOperation var15 = EUtil.add(EUtil.add(var6, (IEGeneric)var7), var8);
      if (var9 != null && !this.E(var9)) {
         var15 = EUtil.add(var15, var9.zeroExtend(var15.getBitsize()));
         var14.add(var9);
      } else {
         var14.add(EUtil.zero(1));
      }

      if (var10 != null && !this.E(var10)) {
         var15 = EUtil.add(var15, var10.zeroExtend(var15.getBitsize()));
         var14.add(var10);
      } else {
         var14.add(EUtil.zero(1));
      }

      this.pC(var1, var3, var15);
      if (!this.UT(var4)) {
         FunctionOptype var16 = this.gCtx.createFunctionType("COMPUTE_ADD3_CARRY1", 0, 5, 1);
         this.pC(var1, var4, this.ctx.createOperation(var16, (IEGeneric[])var14.toArray(new IEGeneric[var14.size()])));
      }

      if (!this.UT(var5)) {
         FunctionOptype var19 = this.gCtx.createFunctionType("COMPUTE_ADD3_CARRY2", 0, 5, 1);
         this.pC(var1, var5, this.ctx.createOperation(var19, (IEGeneric[])var14.toArray(new IEGeneric[var14.size()])));
      }
   }

   @AbstractConverter.Mnemonic("IMAD")
   void Ek(ConverterInstructionEntry var1) {
      int var2 = this.pC(var1, 4, 5, 6);
      String var3 = this.pC(var1, "FMT", "REDUX_SZ");

      boolean var4 = switch (var3) {
         case "U32" -> true;
         case "S32" -> false;
         default -> throw new UnsupportedConversionException();
      };
      boolean var5 = this.A(var1, "LOOnly", "LO");
      boolean var18 = this.A(var1, "WIDEONLY", "WIDE");
      boolean var7 = this.A(var1, "HIONLY", "HI") || this.A(var1, "HIONLY_imad", "HI");
      this.pC(var5 && !var18 && !var7 || !var5 && var18 && !var7 || !var5 && !var18 && var7);
      boolean var8 = this.A(var1, "XONLY", "X");
      IEGeneric var10 = null;
      IEGeneric var11 = null;
      int var12 = 0;
      IEGeneric var13 = this.gp(this.A(var1, var12++, var18 ? 64 : 32));
      IEGeneric var14 = this.pC(var1, var12++);
      IEGeneric var9;
      if (var14.getBitsize() == 1) {
         var11 = var14;
         var9 = this.pC(var1, var12++);
      } else {
         var9 = var14;
      }

      IEGeneric var15 = this.pC(var1, var12++);
      IEGeneric var16 = this.A(var1, var12++, var13.getBitsize());
      if (var12 < var2) {
         this.pC(var8);
         var10 = this.pC(var1, var12);
         this.pC(var10.getBitsize() == 1);
      }

      this.pC(var11 == null || this.UT(var11));
      IEOperation var17;
      if (var5) {
         var17 = EUtil.add(EUtil.mul(var9, var15), var16);
      } else if (var7) {
         var17 = EUtil.add(EUtil.mul(var9.extend(64, !var4), var15.extend(64, !var4)).slice(32), var16);
      } else {
         var17 = EUtil.add(EUtil.mul(var9.extend(64, !var4), var15.extend(64, !var4)), var16);
      }

      if (var10 != null) {
         var17 = EUtil.add(var17, var10.zeroExtend(var17.getBitsize()));
      }

      this.pC(var1, var13, var17);
   }

   @AbstractConverter.Mnemonic("IMNMX")
   void hK(ConverterInstructionEntry var1) {
      int var2 = this.kS(var1, 4, 7);
      this.pC(var2 == 4);
      String var3 = this.pC(var1, "FMT", "REDUX_SZ");
      this.pC(var1, "FMT_64_DIST");

      boolean var4 = switch (var3) {
         case "U32" -> true;
         case "S32" -> false;
         default -> throw new UnsupportedConversionException();
      };
      IEGeneric var11 = this.pC(var1, 3);
      boolean var5;
      if (this.UT(var11)) {
         var5 = true;
      } else {
         if (!this.E(var11)) {
            throw new UnsupportedConversionException();
         }

         var5 = false;
      }

      IEGeneric var7 = this.gp(this.pC(var1, 0));
      IEGeneric var8 = this.pC(var1, 1);
      IEGeneric var9 = this.pC(var1, 2);
      IECond var10;
      if (var4) {
         if (var5) {
            var10 = this.ctx.createCond(EUtil.ltU(var8, var9), var8, var9);
         } else {
            var10 = this.ctx.createCond(EUtil.gtU(var8, var9), var8, var9);
         }
      } else if (var5) {
         var10 = this.ctx.createCond(EUtil.ltS(var8, var9), var8, var9);
      } else {
         var10 = this.ctx.createCond(EUtil.gtS(var8, var9), var8, var9);
      }

      this.pC(var1, var7, var10);
   }

   @AbstractConverter.Mnemonic("ISETP")
   void Er(ConverterInstructionEntry var1) {
      int var2 = this.kS(var1, 5, 6);
      String var3 = this.pC(var1, "EXONLY", "EX");
      this.pC(var3 == null ? var2 == 5 : var2 == 6);
      this.kS(var1, "FMT_64_DIST", null);
      String var4 = this.A(var1, "ICmpAll");
      String var5 = this.A(var1, "Bop");
      String var6 = this.pC(var1, "FMT", "REDUX_SZ");
      this.pC(Strings.isContainedIn(var6, "S32", "U32", "S64", "U64"));
      boolean var7 = var6.startsWith("S");

      OperationType var8 = switch (var4) {
         case "EQ" -> OperationType.LOG_EQ;
         case "NE" -> OperationType.LOG_NEQ;
         case "GT" -> var7 ? OperationType.GT_S : OperationType.GT_U;
         case "GE" -> var7 ? OperationType.GE_S : OperationType.GE_U;
         case "LT" -> var7 ? OperationType.LT_S : OperationType.LT_U;
         case "LE" -> var7 ? OperationType.LE_S : OperationType.LE_U;
         default -> throw new UnsupportedConversionException();
      };

      OperationType var9 = switch (var5) {
         case "AND" -> OperationType.AND;
         case "OR" -> OperationType.OR;
         case "XOR" -> OperationType.XOR;
         default -> throw new UnsupportedConversionException();
      };
      IEGeneric var18 = this.gp(this.pC(var1, 0));
      IEGeneric var19 = this.pC(var1, 1);
      IEGeneric var12 = this.pC(var1, 2);
      IEGeneric var13 = this.pC(var1, 3);
      IEGeneric var14 = this.pC(var1, 4);
      IEGeneric var15 = var2 < 6 ? var14 : this.pC(var1, 5);
      IEOperation var16 = this.ctx.createOperation(var8, var12, var13);
      if (!this.UT(var18) && this.UT(var19)) {
         if (var9 != OperationType.AND || !this.UT(var14)) {
            var16 = this.ctx.createOperation(var9, var16, var14);
         }

         this.pC(var1, var18, var16);
      } else {
         IEVar var17 = this.pC(0);
         this.pC(var1, var17, var16);
         if (!this.UT(var18)) {
            this.pC(var1, var18, this.ctx.createOperation(var9, var17, var14));
         }

         if (!this.UT(var19)) {
            this.pC(var1, var19, this.ctx.createOperation(var9, EUtil.notB(var17), var15));
         }
      }
   }

   @AbstractConverter.Mnemonic("LEA")
   void FE(ConverterInstructionEntry var1) {
      int var2 = this.pC(var1, 5, 6, 7);
      boolean var3 = this.A(var1, "HIONLY", "HI") || this.A(var1, "HIONLY_lea", "HI");
      boolean var4 = this.A(var1, "LOONLY", "LO");
      boolean var5 = this.A(var1, "XONLY", "X");
      boolean var6 = this.A(var1, "SX32ONLY", "SX32");
      this.pC(var3 ^ var4);
      this.pC(!var6 || var3);
      int var7 = 0;
      IEGeneric var8 = this.gp(this.pC(var1, var7++));
      IEGeneric var9 = this.pC(var1, var7++);
      IEGeneric var10 = this.pC(var1, var7++);
      IEGeneric var11 = this.pC(var1, var7++);
      IEGeneric var12 = var3 && !var6 ? this.pC(var1, var7++) : null;
      IEGeneric var13 = this.pC(var1, var7++);
      IEGeneric var14 = var5 ? this.pC(var1, var7++) : null;
      this.pC(var7 == var2);
      IEVar var15 = this.getTempRegister(0);
      this.pC(var1, var15, var11);
      IEOperation var16;
      if (var4) {
         var16 = EUtil.add(var15, EUtil.shl(var10, var13));
      } else {
         IEImm var17 = EUtil.imm(32L, 32);
         var16 = EUtil.add(var15, EUtil.sxr(EUtil.shl(var10, var13), var17, var6));
         if (var12 != null) {
            var16 = EUtil.add(var16, var12);
         }
      }

      if (var14 != null) {
         var16 = EUtil.add(var16, var14.zeroExtend(32));
      }

      this.pC(var1, var8, var16);
      if (var9 != null) {
         this.pC(var1, var9, EUtil.ltU(var8, var15));
      }
   }

   @AbstractConverter.Mnemonic("LOP3")
   void Aj(ConverterInstructionEntry var1) {
      this.kS(var1, 7);
      this.kS(var1, "LUTOnly", "LUT");
      String var2 = this.A(var1, "LOP_POP");

      boolean var3 = switch (var2) {
         case "POR" -> false;
         case "PAND" -> true;
         default -> throw new UnsupportedConversionException();
      };
      IEGeneric var4 = this.pC(var1, 0);
      IEGeneric var14 = this.gp(this.pC(var1, 1));
      IEGeneric var6 = this.pC(var1, 2);
      IEGeneric var7 = this.pC(var1, 3);
      IEGeneric var8 = this.pC(var1, 4);
      IEGeneric var9 = this.pC(var1, 5);
      IEGeneric var10 = this.pC(var1, 6);
      this.pC(!var3 && this.E(var10));
      int var11 = var6.getBitsize();
      this.pC(var7.getBitsize() == var11);
      this.pC(var8.getBitsize() == var11);
      this.pC(var9.isImm());
      int var12 = (int)(var9.asImm().getValueAsUnsignedLong() & 255L);
      Object var13;
      if (var12 == 192) {
         var13 = EUtil.andB(var6, var7);
      } else if (var12 == 252) {
         var13 = EUtil.orB(var6, var7);
      } else if (var12 == 60) {
         var13 = EUtil.xorB(var6, var7);
      } else if (var12 == 240) {
         var13 = EUtil.notB(var6);
      } else if (var12 == 51) {
         var13 = EUtil.notB(var7);
      } else if (var12 == 85) {
         var13 = EUtil.notB(var8);
      } else if (var12 == 128) {
         var13 = EUtil.andB(EUtil.andB(var6, var7), var8);
      } else if (var12 == 1) {
         var13 = EUtil.andB(EUtil.andB(EUtil.notB(var6), EUtil.notB(var7)), EUtil.notB(var8));
      } else if (var12 == 254) {
         var13 = EUtil.orB(EUtil.orB(var6, var7), var8);
      } else if (var12 == 127) {
         var13 = EUtil.orB(EUtil.orB(EUtil.notB(var6), EUtil.notB(var7)), EUtil.notB(var8));
      } else if (var12 == 248) {
         var13 = EUtil.orB(var6, EUtil.andB(var7, var8));
      } else if (var12 == 200) {
         var13 = EUtil.andB(var7, EUtil.orB(var6, var8));
      } else {
         if (var12 != 72) {
            Object[] var15 = new Object[]{var12};
            throw new UnsupportedConversionException();
         }

         var13 = EUtil.andB(var7, EUtil.xorB(var6, var8));
      }

      this.pC(var1, var14, (IEGeneric)var13);
      if (!this.UT(var4)) {
         this.pC(var1, var4, EUtil.ne(var14, EUtil.zero(var14.getBitsize())));
      }
   }

   @AbstractConverter.Mnemonic("SHF")
   void EX(ConverterInstructionEntry var1) {
      this.kS(var1, 4);
      String var2 = this.A(var1, "SDIR");
      String var3 = this.A(var1, "CWMode");
      String var4 = this.A(var1, "HILO");
      String var5 = this.pC(var1, "FMT_shf", "FMT");
      String var6 = this.pC(var1, "XOR");
      this.pC(var6 == null || var6.equals("noxor"));

      boolean var7 = switch (var2) {
         case "L" -> true;
         case "R" -> false;
         default -> throw new RuntimeException();
      };

      boolean var8 = switch (var3) {
         case "C" -> true;
         case "W" -> false;
         default -> throw new RuntimeException();
      };

      boolean var22 = switch (var5) {
         case "U32", "U64" -> true;
         case "S32", "S64" -> false;
         default -> throw new RuntimeException();
      };

      boolean var23 = switch (var5) {
         case "U32", "S32" -> true;
         case "U64", "S64" -> false;
         default -> throw new RuntimeException();
      };

      boolean var24 = switch (var4) {
         case "LO" -> true;
         case "HI" -> false;
         default -> throw new RuntimeException();
      };
      IEGeneric var25 = this.gp(this.pC(var1, 0));
      IEGeneric var26 = this.pC(var1, 1);
      IEGeneric var14 = this.pC(var1, 2);
      IEGeneric var15 = this.pC(var1, 3);
      this.pC(var25.getBitsize() == 32 && var26.getBitsize() == 32 && var14.getBitsize() == 32 && var15.getBitsize() == 32);
      boolean var16 = false;
      if (var5.equals("S32")) {
         this.pC(!var7);
         this.pC(EUtil.isZero(var26) || this.ld(var26));
         this.pC(!var24);
         var16 = true;
      }

      Object var17 = this.getTempRegister(0);
      if (var14.isImm()) {
         long var19 = var14.asImm().getValueAsLong();
         if (var8) {
            var19 = var19 < 0L ? 0L : (var19 > 32L ? 32L : var19);
         } else {
            var19 &= 31L;
         }

         var17 = EUtil.imm(var19, 32);
      } else {
         IEImm var28 = EUtil.imm(31L, var14.getBitsize());
         Object var18;
         if (var8) {
            IEImm var20 = EUtil.zero(var14.getBitsize());
            var18 = this.ctx.createCond(EUtil.ltS(var14, var20), var20, this.ctx.createCond(EUtil.gtS(var14, var28), var28, var14));
         } else {
            var18 = EUtil.andB(var14, var28);
         }

         this.pC(var1, (IEGeneric)var17, (IEGeneric)var18);
      }

      Object var29;
      if (var23) {
         IEImm var31 = EUtil.imm(32L, 32);
         if (var7) {
            if (var24) {
               var29 = EUtil.orB(EUtil.shl(var26, (IEGeneric)var17), EUtil.shr(var15, EUtil.sub(var31, (IEGeneric)var17)));
            } else {
               var29 = EUtil.orB(EUtil.shl(var15, (IEGeneric)var17), EUtil.shr(var26, EUtil.sub(var31, (IEGeneric)var17)));
            }
         } else if (var24) {
            var29 = EUtil.orB(EUtil.shr(var26, (IEGeneric)var17), EUtil.shl(var15, EUtil.sub(var31, (IEGeneric)var17)));
         } else if (var16) {
            var29 = EUtil.sar(var15, (IEGeneric)var17);
         } else {
            var29 = EUtil.orB(EUtil.shr(var15, (IEGeneric)var17), EUtil.shl(var26, EUtil.sub(var31, (IEGeneric)var17)));
         }
      } else {
         OperationType var32 = var7 ? OperationType.SHL : (var22 ? OperationType.SHR : OperationType.SAR);
         IECompose var21 = this.ctx.createCompose(var26, var15);
         var29 = this.ctx.createOperation(var32, var21, (IEGeneric)var17);
         var29 = var24 ? var29.part(32) : var29.slice(32);
      }

      this.pC(var1, var25, var29);
   }

   @AbstractConverter.Mnemonic("VIMNMX")
   void LM(ConverterInstructionEntry var1) {
      this.kS(var1, 4, 6);
      throw new UnsupportedConversionException();
   }

   @AbstractConverter.Mnemonic("F2F")
   void mv(ConverterInstructionEntry var1) {
      this.kS(var1, 2);
      this.A(var1, "FTZ");
      this.A(var1, "Round1");
      String var2 = this.pC(
         var1,
         "F2Ffmts2",
         "F2Ffmts2_16",
         "F2Ffmts2_64",
         "F2Ffmts2_64_32",
         "F2Ffmts2_64_16",
         "DSTFMT_SRCFMT_F16F32_E8M7F32_BF16F32",
         "F32.F16ONLY",
         "DSTFMT_SRCFMT_F16F64_F32F64",
         "F64.F32ONLY",
         "F64.F16ONLY",
         "DSTFMT_SRCFMT_F16F32_E8M7F32_E6M9F32_BF16F32",
         "DSTFMT_SRCFMT_F32F16_F32E6M9",
         "DSTFMT_SRCFMT_F32F16_BF16F16_F16BF16_F32BF16_F32E6M9",
         "DSTFMT_SRCFMT_F16F64_F32F64_BF16F64",
         "DSTFMT_SRCFMT_F64F16_F64BF16",
         "DSTFMT_SRCFMT_F16F32_BF16F32",
         "DSTFMT_SRCFMT_F32F16_BF16F16_F16BF16_F32BF16"
      );
      String[] var3 = var2.split("\\.");
      String var4 = var3[0];
      String var5 = var3[1];
      int var6 = 0;
      if (var4.startsWith("F")) {
         var6 = Integer.parseInt(var4.substring(1));
      }

      this.pC(var6 != 0);
      int var7 = 0;
      if (var5.startsWith("F")) {
         var7 = Integer.parseInt(var5.substring(1));
      }

      this.pC(var7 != 0);
      IEGeneric var8 = this.gp(this.A(var1, 0, var6));
      IEGeneric var9 = this.pC(var1, 1, var7);
      IEOperation var10 = this.ctx.createConversionOperation(OperationType.FP2FP, var9, var6);
      this.pC(var1, var8, var10);
   }

   @AbstractConverter.Mnemonic("F2FP")
   void sO(ConverterInstructionEntry var1) {
      int var2 = this.pC(var1, 2, 3, 4);
      this.pC(var2 == 3 && ((sy)var1.insn).A.equals("f2fp__RRR"));
      String var3 = this.pC(var1, "PACK_ABONLY");
      this.pC(var3.equals("PACK_AB"));
      String var4 = this.pC(var1, "DSTFMT");
      this.pC(var4.equals("F16") || var4.equals("BF16"));
      FunctionOptype var5 = this.gCtx.createFunctionType("CONVERT_F32_TO_" + var4, 0, 1, 16);
      IEGeneric var6 = this.pC(var1, 0);
      IEGeneric var7 = this.A(var1, 1);
      IEGeneric var8 = this.A(var1, 2);
      IEOperation var9 = this.ctx.createOperation(var5, var7);
      IEOperation var10 = this.ctx.createOperation(var5, var8);
      this.pC(var1, var6, this.ctx.createCompose(var10, var9));
   }

   @AbstractConverter.Mnemonic("F2I")
   void os(ConverterInstructionEntry var1) {
      this.kS(var1, 2);
      String var2 = this.pC(var1, "Float16", "Float32", "Float64", "BF16ONLY");
      String var3 = this.pC(var1, "DSTFMT_U8_S8_U16_S16_U32_S32", "DSTFMT_U64_S64");

      byte var4 = switch (var2) {
         case "F32" -> 32;
         case "F64" -> 64;
         default -> throw new UnsupportedConversionException();
      };

      byte var5 = switch (var3) {
         case "U8", "S8" -> 8;
         case "U16", "S16" -> 16;
         case "U32", "S32" -> 32;
         case "U64", "S64" -> 64;
         default -> throw new UnsupportedConversionException();
      };

      boolean var10 = switch (var3) {
         case "U8", "U16", "U32", "U64" -> true;
         case "S8", "S16", "S32", "S64" -> true;
         default -> throw new UnsupportedConversionException();
      };
      IEGeneric var11 = this.gp(this.A(var1, 0, var5));
      IEGeneric var12 = this.pC(var1, 1, var4);
      IEOperation var9 = this.ctx.createConversionOperation(var10 ? OperationType.FP2UINT : OperationType.FP2INT, var12, var5);
      this.pC(var1, var11, var9);
   }

   @AbstractConverter.Mnemonic("I2F")
   void Cu(ConverterInstructionEntry var1) {
      this.kS(var1, 2);
      this.pC(var1, "Round1");
      this.pC(this.pC(var1, "S32ONLY_i2fp") == null);
      this.pC(this.pC(var1, "U32ONLY_i2fp") == null);
      String var2 = this.pC(
         var1,
         "DSTFMT_U64_S64",
         "SRCFMT_U16_S16",
         "SRCFMT_U32_S32",
         "SRCFMTA",
         "SRCFMT_U8_S8",
         "SRCFMT",
         "S64ONLY",
         "U64ONLY",
         "S32ONLY",
         "U32ONLY",
         "U32ONLY_i2f"
      );
      String var3 = this.pC(var1, "Float64", "DSTFMT_F16_F32", "DSTFMT_F16_F32_BF16");

      byte var4 = switch (var2) {
         case "U8", "S8" -> 8;
         case "U16", "S16" -> 16;
         case "U32", "S32" -> 32;
         case "U64", "S64" -> 64;
         default -> throw new UnsupportedConversionException();
      };

      boolean var5 = switch (var2) {
         case "U8", "U16", "U32", "U64" -> true;
         case "S8", "S16", "S32", "S64" -> true;
         default -> throw new UnsupportedConversionException();
      };

      byte var10 = switch (var3) {
         case "F16" -> 16;
         case "F32" -> 32;
         case "F64" -> 64;
         default -> throw new UnsupportedConversionException();
      };
      this.pC(var10 != 16);
      IEGeneric var11 = this.gp(this.A(var1, 0, var10));
      IEGeneric var12 = this.A(var1, 1, var4);
      IEOperation var9 = this.ctx.createConversionOperation(var5 ? OperationType.UINT2FP : OperationType.INT2FP, var12, var10);
      this.pC(var1, var11, var9);
   }

   @AbstractConverter.Mnemonic("I2I")
   void hZ(ConverterInstructionEntry var1) {
      this.kS(var1, 2);
      throw new UnsupportedConversionException();
   }

   @AbstractConverter.Mnemonic("I2IP")
   void UW(ConverterInstructionEntry var1) {
      this.kS(var1, 4);
      throw new UnsupportedConversionException();
   }

   @AbstractConverter.Mnemonic("I2FP")
   void PR(ConverterInstructionEntry var1) {
      this.kS(var1, 2);
      this.pC(this.pC(var1, "F16X2ONLY") == null);
      this.pC(this.pC(var1, "SRCFMT_4X2") == null);
      String var2 = this.pC(var1, "SRCFMT", "S32ONLY_i2fp", "U32ONLY_i2fp", "U32ONLY_i2fp", "SRCFMT_U32_S32", "S32ONLY", "U32ONLY_i2f", "SRCFMT_i2fp");

      boolean var3 = switch (var2) {
         case "U32" -> true;
         case "S32" -> false;
         default -> throw new UnsupportedConversionException();
      };
      IEGeneric var4 = this.gp(this.pC(var1, 0));
      IEGeneric var7 = this.pC(var1, 1);
      IEOperation var6 = this.ctx.createConversionOperation(var3 ? OperationType.UINT2FP : OperationType.INT2FP, var7, var4.getBitsize());
      this.pC(var1, var4, var6);
   }

   @AbstractConverter.Mnemonic("F2IP")
   void cX(ConverterInstructionEntry var1) {
      this.kS(var1, 4);
      throw new UnsupportedConversionException();
   }

   @AbstractConverter.Mnemonic("FRND")
   void DQ(ConverterInstructionEntry var1) {
      this.kS(var1, 2);
      throw new UnsupportedConversionException();
   }

   @AbstractConverter.Mnemonic("MOV")
   void ZN(ConverterInstructionEntry var1) {
      int var2 = this.kS(var1, 2, 3);
      if (var2 == 3) {
         this.pC(this.pC(var1, 2) instanceof IEImm var4 && (var4.getValueAsLong() & 15L) == 15L);
      }

      this.kS(var1, "SPILLONLY", null);
      byte var7 = 32;
      String var8 = this.pC(var1, "ONLY64", "ONLY64_syncs");
      if (var8 != null && var8.replace("_", "").equals("64")) {
         var7 = 64;
      }

      IEGeneric var5 = this.gp(this.A(var1, 0, var7));
      IEGeneric var6 = this.A(var1, 1, var7);
      this.pC(var1, var5, var6);
   }

   @AbstractConverter.Mnemonic("PRMT")
   void OB(ConverterInstructionEntry var1) {
      this.kS(var1, 4);
      String var2 = this.pC(var1, "PMode", "IDXOnly");
      IEGeneric var3 = this.gp(this.pC(var1, 0));
      IEGeneric var4 = this.pC(var1, 1);
      IEGeneric var5 = this.pC(var1, 2);
      IEGeneric var6 = this.pC(var1, 3);
      if (var2.equalsIgnoreCase("IDX") && var5 instanceof IEImm) {
         int var7 = (int)var5.asImm().getValueAsLong();
         if ((var7 & -65536) == 0) {
            boolean var8 = (var7 & 8) != 0;
            boolean var9 = (var7 >> 4 & 8) != 0;
            boolean var10 = (var7 >> 8 & 8) != 0;
            boolean var11 = (var7 >> 12 & 8) != 0;
            int var12 = var7 & 7;
            int var13 = var7 >> 4 & 7;
            int var14 = var7 >> 8 & 7;
            int var15 = var7 >> 12 & 7;
            IEGeneric var16 = var12 < 4 ? var4.slice(var12 * 8, (var12 + 1) * 8) : var6.slice((var12 - 4) * 8, (var12 - 4 + 1) * 8);
            if (var8) {
               var16 = var16.msb().signExtend(8);
            }

            IEGeneric var17 = var13 < 4 ? var4.slice(var13 * 8, (var13 + 1) * 8) : var6.slice((var13 - 4) * 8, (var13 - 4 + 1) * 8);
            if (var9) {
               var17 = var17.msb().signExtend(8);
            }

            IEGeneric var18 = var14 < 4 ? var4.slice(var14 * 8, (var14 + 1) * 8) : var6.slice((var14 - 4) * 8, (var14 - 4 + 1) * 8);
            if (var10) {
               var18 = var18.msb().signExtend(8);
            }

            IEGeneric var19 = var15 < 4 ? var4.slice(var15 * 8, (var15 + 1) * 8) : var6.slice((var15 - 4) * 8, (var15 - 4 + 1) * 8);
            if (var11) {
               var19 = var19.msb().signExtend(8);
            }

            this.pC(var1, var3, this.ctx.createCompose(var16, var17, var18, var19));
            return;
         }
      }

      IEUntranslatedInstruction var20 = this.ctx.createUntranslatedInstruction(var1.address, ((sy)var1.insn).pC(false), var4, var5, var6);
      var20.setReturnExpression(var3);
      var1.r.add(var20);
   }

   @AbstractConverter.Mnemonic("SEL")
   void pF(ConverterInstructionEntry var1) {
      this.kS(var1, 4);
      byte var2 = 32;
      String var3 = this.pC(var1, "ONLY64");
      if (var3 != null && var3.replace("_", "").equals("64")) {
         var2 = 64;
      }

      IEGeneric var4 = this.gp(this.A(var1, 0, var2));
      IEGeneric var5 = this.A(var1, 1, var2);
      IEGeneric var6 = this.A(var1, 2, var2);
      IEGeneric var7 = this.pC(var1, 3);
      IECond var8 = this.ctx.createCond(var7, var5, var6);
      this.pC(var1, var4, var8);
   }

   @AbstractConverter.Mnemonic("SHFL")
   void Bc(ConverterInstructionEntry var1) {
      this.kS(var1, 5);
      IEGeneric var2 = this.pC(var1, 0);
      IEGeneric var3 = this.gp(this.pC(var1, 1));
      IEGeneric var4 = this.pC(var1, 2);
      IEGeneric var5 = this.pC(var1, 3);
      IEGeneric var6 = this.pC(var1, 4);
      this.pC(this.UT(var2));
      IEUntranslatedInstruction var7 = this.ctx.createUntranslatedInstruction(var1.address, ((sy)var1.insn).pC(false), var4, var5, var6);
      var7.setReturnExpression(var3);
      var1.r.add(var7);
   }

   @AbstractConverter.Mnemonic("PLOP3")
   void OI(ConverterInstructionEntry var1) {
      this.kS(var1, 7);
      this.kS(var1, "LUTOnly", "LUT");
      IEGeneric var2 = this.gp(this.pC(var1, 0));
      IEGeneric var3 = this.pC(var1, 1);
      IEGeneric var4 = this.pC(var1, 2);
      IEGeneric var5 = this.pC(var1, 3);
      IEGeneric var6 = this.pC(var1, 4);
      IEGeneric var7 = this.pC(var1, 5);
      this.pC(var1, 6);
      this.pC(var3.getBitsize() == 1 && this.UT(var3));
      if (var4.getBitsize() != 1) {
         var4 = var4.msb();
      }

      if (var5.getBitsize() != 1) {
         var5 = var6.msb();
      }

      if (var6.getBitsize() != 1) {
         var6 = var6.msb();
      }

      this.pC(var7.isImm());
      int var8 = (int)(var7.asImm().getValueAsUnsignedLong() & 255L);
      IEOperation var9;
      if (var8 == 8) {
         var9 = EUtil.andB(EUtil.andB(EUtil.notB(var4), var5), var6);
      } else if (var8 == 128) {
         var9 = EUtil.andB(EUtil.andB(var4, var5), var6);
      } else if (var8 == 168) {
         var9 = EUtil.andB(EUtil.orB(var4, var5), var6);
      } else {
         if (var8 != 40) {
            throw new UnsupportedConversionException();
         }

         var9 = EUtil.andB(EUtil.xorB(var4, var5), var6);
      }

      this.pC(var1, var2, var9);
   }

   @AbstractConverter.Mnemonic("P2R")
   void Bf(ConverterInstructionEntry var1) {
      this.kS(var1, 4);
      this.A(var1, "B3B0");
      IEGeneric var2 = this.gp(this.pC(var1, 0));
      IEGeneric var3 = this.pC(var1, 2);
      IEGeneric var4 = this.pC(var1, 3);
      IEUntranslatedInstruction var5 = this.ctx.createUntranslatedInstruction(var1.address, ((sy)var1.insn).pC(false), var3, var4);
      var5.setReturnExpression(var2);
      var1.r.add(var5);
   }

   private void kS(ConverterInstructionEntry var1, String var2) {
      this.pC(var1, var2, 0);
   }

   private void pC(ConverterInstructionEntry var1, String var2, int var3) {
      if (var2.equals("U")) {
         var2 = "32";
      } else if (var2.startsWith("U.")) {
         var2 = var2.substring(2);
      }
      short var4 = switch (var2) {
         case "U8", "S8" -> 8;
         case "U16", "S16" -> 16;
         case "_32", "32" -> 32;
         case "_64", "64" -> 64;
         case "_128", "128" -> 128;
         default -> throw new UnsupportedConversionException();
      };

      byte var5 = switch (var2) {
         case "U8", "U16" -> 1;
         case "S8", "S16" -> -1;
         default -> 0;
      };
      IEGeneric var9 = this.gp(this.A(var1, var3, var4 < 32 ? 32 : var4));
      IEGeneric var10 = this.A(var1, var3 + 1, var4);
      if (var5 == -1) {
         IEVar var8 = this.getTempRegister(0);
         this.pC(var1, var8, var10.zeroExtend(32));
         var10 = var8.part(var4).signExtend(32);
      } else if (var5 == 1) {
         var10 = var10.zeroExtend(var9.getBitsize());
      }

      this.pC(var1, var9, var10);
   }

   @AbstractConverter.Mnemonic("LD")
   void Pe(ConverterInstructionEntry var1) {
      int var2 = this.pC(var1, 2, 3, 4);
      if (this.ld.kS() >= 75) {
         IEGeneric var3 = this.pC(var1, var2 - 1);
         this.pC(this.UT(var3));
      }

      this.pC(this.pC(var1, "ZDONLY") == null);
      String var4 = this.pC(var1, "SIZE3", "SZ_U8_S8_U16_S16_32_64_128");
      this.kS(var1, var4);
   }

   @AbstractConverter.Mnemonic("LDC")
   void ck(ConverterInstructionEntry var1) {
      this.kS(var1, 2);
      String var2 = this.A(var1, "SZ_U8_S8_U16_S16_32_64");
      String var3 = this.A(var1, "AdMode");
      this.pC(var3.equals("IA"));
      this.kS(var1, var2);
   }

   @AbstractConverter.Mnemonic("LDG")
   void RW(ConverterInstructionEntry var1) {
      int var2 = this.pC(var1, 3, 4, 5);
      this.pC(var2 == 4);
      String var3 = this.pC(var1, "SIZE3", "ONLY256", "SZ_U8_S8_U16_S16_32_64_128");
      IEGeneric var4 = this.pC(var1, 0);
      IEGeneric var5 = this.pC(var1, 3);
      this.pC(this.UT(var4));
      this.pC(this.UT(var5));
      this.pC(var1, var3, 1);
   }

   @AbstractConverter.Mnemonic("LDL")
   void e(ConverterInstructionEntry var1) {
      this.kS(var1, 2);
      String var2 = this.pC(var1, "SIZE3", "ONLY256", "SZ_U8_S8_U16_S16_32_64_128");
      this.kS(var1, var2);
   }

   @AbstractConverter.Mnemonic("LDS")
   void xM(ConverterInstructionEntry var1) {
      int var2 = this.kS(var1, 2, 3);
      String var3;
      if (this.ld.kS() < 80) {
         if (this.pC(var1, "ZDONLY") != null) {
            this.pC(var2 == 3);
            throw new UnsupportedConversionException();
         }

         this.pC(var2 == 2);
         var3 = this.A(var1, "LDSSIZE_DIST");
      } else {
         this.pC(var2 == 2);
         var3 = this.A(var1, "LDSSIZE");
      }

      this.kS(var1, var3);
   }

   private void wS(ConverterInstructionEntry var1, String var2) {
      short var3 = switch (var2) {
         case "U8", "S8" -> 8;
         case "U16", "S16" -> 16;
         case "_32", "32" -> 32;
         case "_64", "64" -> 64;
         case "_128", "128" -> 128;
         default -> throw new UnsupportedConversionException();
      };
      IEGeneric var4 = this.gp(this.A(var1, 0, var3));
      IEGeneric var6 = this.A(var1, 1, var3 < 32 ? 32 : var3).part(var4.getBitsize());
      this.pC(var1, var4, var6);
   }

   @AbstractConverter.Mnemonic("ST")
   void kU(ConverterInstructionEntry var1) {
      this.kS(var1, 2);
      String var2 = this.pC(var1, "SIZE3", "SZ_U8_S8_U16_S16_32_64_128");
      this.wS(var1, var2);
   }

   @AbstractConverter.Mnemonic("STG")
   void Kq(ConverterInstructionEntry var1) {
      int var2 = this.kS(var1, 2, 4);
      this.pC(var2 == 2);
      String var3 = this.pC(var1, "SIZE3", "ONLY256", "SZ_U8_S8_U16_S16_32_64_128");
      this.wS(var1, var3);
   }

   @AbstractConverter.Mnemonic("STL")
   void go(ConverterInstructionEntry var1) {
      this.kS(var1, 2);
      String var2 = this.pC(var1, "SZ_U8_S8_U16_S16_32_64_128");
      this.wS(var1, var2);
   }

   @AbstractConverter.Mnemonic("STS")
   void JF(ConverterInstructionEntry var1) {
      this.kS(var1, 2);
      String var2 = this.A(var1, "SZ_U8_S8_U16_S16_32_64_128");
      this.wS(var1, var2);
   }

   @AbstractConverter.Mnemonic("S2UR")
   void Nq(ConverterInstructionEntry var1) {
      this.fH(var1);
   }

   @AbstractConverter.Mnemonic("UF2FP")
   void pg(ConverterInstructionEntry var1) {
      this.sO(var1);
   }

   @AbstractConverter.Mnemonic("UFLO")
   void gj(ConverterInstructionEntry var1) {
      this.UO(var1);
   }

   @AbstractConverter.Mnemonic("UIADD3")
   void ZD(ConverterInstructionEntry var1) {
      this.z(var1);
   }

   @AbstractConverter.Mnemonic("UIMAD")
   void DL(ConverterInstructionEntry var1) {
      this.Ek(var1);
   }

   @AbstractConverter.Mnemonic("UISETP")
   void UH(ConverterInstructionEntry var1) {
      this.Er(var1);
   }

   @AbstractConverter.Mnemonic("ULDC")
   void VD(ConverterInstructionEntry var1) {
      int var2 = this.kS(var1, 2, 3);
      this.pC(var2 == 2);
      String var3 = this.A(var1, "SZ_U8_S8_U16_S16_32_64");
      this.kS(var1, var3);
   }

   @AbstractConverter.Mnemonic("ULEA")
   void Xs(ConverterInstructionEntry var1) {
      this.FE(var1);
   }

   @AbstractConverter.Mnemonic("ULOP3")
   void KV(ConverterInstructionEntry var1) {
      this.Aj(var1);
   }

   @AbstractConverter.Mnemonic("UMOV")
   void FK(ConverterInstructionEntry var1) {
      this.kS(var1, 2);
      String var2 = this.pC(var1, "ONLY64", "ONLY64_syncs");
      this.pC(var2 == null || var2.replace("_", "").equals("64"));
      int var3 = var2 == null ? 32 : 64;
      IEGeneric var4 = this.gp(this.A(var1, 0, var3));
      IEGeneric var5 = this.A(var1, 1, var3);
      this.pC(var1, var4, var5);
   }

   @AbstractConverter.Mnemonic("UPLOP3")
   void Bi(ConverterInstructionEntry var1) {
      this.OI(var1);
   }

   @AbstractConverter.Mnemonic("UPRMT")
   void wQ(ConverterInstructionEntry var1) {
      this.OB(var1);
   }

   @AbstractConverter.Mnemonic("USEL")
   void PZ(ConverterInstructionEntry var1) {
      this.kS(var1, 4);
      this.pF(var1);
   }

   @AbstractConverter.Mnemonic("USHF")
   void Ip(ConverterInstructionEntry var1) {
      this.EX(var1);
   }

   @AbstractConverter.Mnemonic("CS2UR")
   void Fm(ConverterInstructionEntry var1) {
      this.ve(var1);
   }

   @AbstractConverter.Mnemonic("LDCU")
   void FM(ConverterInstructionEntry var1) {
      int var2 = this.pC(var1, 2, 3, 4, 5);
      this.pC(var2 == 2);
      String var3 = this.pC(var1, "SZ_U8_S8_U16_S16_32_64_128", "ONLY256_ldcu");
      this.kS(var1, var3);
   }

   @AbstractConverter.Mnemonic("UFADD")
   void Wn(ConverterInstructionEntry var1) {
      this.A(var1);
   }

   @AbstractConverter.Mnemonic("UF2F")
   void gy(ConverterInstructionEntry var1) {
      this.mv(var1);
   }

   @AbstractConverter.Mnemonic("UF2I")
   void pt(ConverterInstructionEntry var1) {
      this.os(var1);
   }

   @AbstractConverter.Mnemonic("UF2IP")
   void uE(ConverterInstructionEntry var1) {
      this.cX(var1);
   }

   @AbstractConverter.Mnemonic("UFFMA")
   void Um(ConverterInstructionEntry var1) {
      this.kS(var1);
   }

   @AbstractConverter.Mnemonic("UFMNMX")
   void Ta(ConverterInstructionEntry var1) {
      this.wS(var1);
   }

   @AbstractConverter.Mnemonic("UFMUL")
   void So(ConverterInstructionEntry var1) {
      this.UT(var1);
   }

   @AbstractConverter.Mnemonic("UFRND")
   void tH(ConverterInstructionEntry var1) {
      this.DQ(var1);
   }

   @AbstractConverter.Mnemonic("UFSEL")
   void Gm(ConverterInstructionEntry var1) {
      this.E(var1);
   }

   @AbstractConverter.Mnemonic("UFSET")
   void Br(ConverterInstructionEntry var1) {
      this.sY(var1);
   }

   @AbstractConverter.Mnemonic("UFSETP")
   void IE(ConverterInstructionEntry var1) {
      this.ys(var1);
   }

   @AbstractConverter.Mnemonic("UI2F")
   void AU(ConverterInstructionEntry var1) {
      this.Cu(var1);
   }

   @AbstractConverter.Mnemonic("UI2FP")
   void jS(ConverterInstructionEntry var1) {
      this.PR(var1);
   }

   @AbstractConverter.Mnemonic("UI2I")
   void KK(ConverterInstructionEntry var1) {
      this.hZ(var1);
   }

   @AbstractConverter.Mnemonic("UI2IP")
   void oB(ConverterInstructionEntry var1) {
      this.UW(var1);
   }

   @AbstractConverter.Mnemonic("UIABS")
   void Rq(ConverterInstructionEntry var1) {
      this.Ab(var1);
   }

   @AbstractConverter.Mnemonic("UIMNMX")
   void LL(ConverterInstructionEntry var1) {
      this.hK(var1);
   }

   @AbstractConverter.Mnemonic("ULEPC")
   void rC(ConverterInstructionEntry var1) {
      this.yv(var1);
   }

   @AbstractConverter.Mnemonic("BMOV")
   void be(ConverterInstructionEntry var1) {
      this.ET(var1);
   }

   @AbstractConverter.Mnemonic("BPT")
   void Xh(ConverterInstructionEntry var1) {
      this.kS(var1, 1);
      IEGeneric var2 = this.pC(var1, 0);
      IEUntranslatedInstruction var3 = this.ctx.createUntranslatedInstruction(var1.address, ((sy)var1.insn).pC(false), var2);
      var1.r.add(var3);
   }

   @AbstractConverter.Mnemonic("BRA")
   void sz(ConverterInstructionEntry var1) {
      int var2 = this.kS(var1, 2, 3);
      IEGeneric var3 = this.pC(var1, 0);
      IEGeneric var4 = var2 == 2 ? null : this.pC(var1, 1);
      Object var5 = this.pC(var1, var4 == null ? 1 : 2);
      String var6 = this.pC(var1, "COND", "UONLY", "COND_DIV_CONV", "COND__DIV_CONV");
      String var7 = this.pC(var1, "DEPTH", "DEPTH_bra");
      String var8 = this.pC(var1, "USEL");
      String var9 = this.pC(var1, "NOTTID0");
      if (var7.startsWith("nodepth") && (var8 == null || var8.equalsIgnoreCase("ALL")) && var9 != null) {
         var9.equalsIgnoreCase("nonottid0");
      }

      IEImm var10 = EUtil.imm(var1.address + ((sy)var1.insn).getSize(), this.getAddressBitsize());
      if (var6.startsWith("nocond")) {
         if (!this.UT(var3)) {
            var5 = this.ctx.createCond(var3, (IEGeneric)var5, var10);
         }

         var1.r.add(this.ctx.createBranchAssign(this.gp, (IEGeneric)var5, false));
      } else {
         String var11 = switch (var6) {
            case "DIV" -> "CHECK_DIVERGENCE";
            case "CONV" -> "CHECK_CONVERGENCE";
            case "U" -> "CHECK_UNIFORM";
            default -> throw new UnsupportedConversionException();
         };
         FunctionOptype var12 = this.gCtx.createFunctionType(var11, 0, 1, 1);
         IEOperation var15 = this.ctx.createOperation(var12, (IEGeneric)(var4 != null ? var4 : EUtil.imm(-1L, 32)));
         if (!this.UT(var3)) {
            var15 = EUtil.andL(var3, var15);
         }

         var5 = this.ctx.createCond(var15, (IEGeneric)var5, var10);
         var1.r.add(this.ctx.createBranchAssign(this.gp, (IEGeneric)var5, false));
      }
   }

   @AbstractConverter.Mnemonic("BRX")
   void QQ(ConverterInstructionEntry var1) {
      this.kS(var1, 2);
      this.pC(var1, "DEPTH", "DEPTH_bra");
      String var2 = this.pC(var1, "COND");
      IEGeneric var3 = this.pC(var1, 0);
      IEGeneric var4 = this.A(var1, 1, this.gp.getBitsize());
      IEImm var5 = EUtil.imm(var1.address + ((sy)var1.insn).getSize(), this.gp.getBitsize());
      Object var6 = var4.zeroExtend(this.gp.getBitsize());
      if (var2 != null && !var2.startsWith("nocond")) {
         String var7 = switch (var2) {
            case "DIV" -> "CHECK_DIVERGENCE";
            case "CONV" -> "CHECK_CONVERGENCE";
            case "U" -> "CHECK_UNIFORM";
            default -> throw new UnsupportedConversionException();
         };
         FunctionOptype var8 = this.gCtx.createFunctionType(var7, 0, 1, 1);
         IEOperation var11 = this.ctx.createOperation(var8, EUtil.imm(-1L, 32));
         if (!this.UT(var3)) {
            var11 = EUtil.andL(var3, var11);
         }

         var6 = this.ctx.createCond(var11, (IEGeneric)var6, var5);
         var1.r.add(this.ctx.createBranchAssign(this.gp, (IEGeneric)var6, false));
      } else {
         if (!this.UT(var3)) {
            var6 = this.ctx.createCond(var3, (IEGeneric)var6, var5);
         }

         var1.r.add(this.ctx.createBranchAssign(this.gp, (IEGeneric)var6, false));
      }
   }

   @AbstractConverter.Mnemonic("BRXU")
   void eE(ConverterInstructionEntry var1) {
      this.QQ(var1);
   }

   @AbstractConverter.Mnemonic("BSSY")
   void dM(ConverterInstructionEntry var1) {
      this.ET(var1);
   }

   @AbstractConverter.Mnemonic("BSYNC")
   void EM(ConverterInstructionEntry var1) {
      this.ET(var1);
   }

   @AbstractConverter.Mnemonic("CALL")
   void fD(ConverterInstructionEntry var1) {
      this.kS(var1, 2);
      this.kS(var1, "CALL_DEPTH", "NOINC");
      String var2 = this.pC(var1, "RelOpt", "ABSONLY");
      switch (var2) {
         case "REL":
         case "ABS":
            IEGeneric var3 = this.pC(var1, 0);
            IEGeneric var7 = this.pC(var1, 1);
            if (!this.UT(var3)) {
               var1.r.add(this.ctx.createJump(var1.irAddress + var1.r.size() + 2, EUtil.notL(var3)));
            }

            IEGeneric var5 = var7.zeroExtend(this.gp.getBitsize());
            IEAssign var6 = this.ctx.createBranchAssign(this.gp, var5, true);
            var1.r.add(var6);
            return;
         default:
            throw new UnsupportedConversionException();
      }
   }

   @AbstractConverter.Mnemonic("EXIT")
   void ii(ConverterInstructionEntry var1) {
      this.kS(var1, 1);
      IEGeneric var2 = this.pC(var1, 0);
      if (!this.UT(var2)) {
         var1.r.add(this.ctx.createJump(var1.irAddress + var1.r.size() + 2, EUtil.notL(var2)));
      }

      IEUntranslatedInstruction var3 = this.ctx.createUntranslatedInstruction(var1.address, ((sy)var1.insn).pC(false));
      var3.setBreakingFlow(FlowInformation.EMPTY);
      var3.setInstructionFlags(new ArrayList1(InstructionFlags.ROUTINE_TERMINATOR));
      var1.r.add(var3);
   }

   @AbstractConverter.Mnemonic("NANOSLEEP")
   void Gu(ConverterInstructionEntry var1) {
      int var2 = this.kS(var1, 1, 2);
      IEGeneric var3 = this.pC(var1, 0);
      this.pC(this.UT(var3));
      IEGeneric[] var4;
      if (this.A(var1, "CLEARONLY", "CLEAR")) {
         this.pC(var2 == 1);
         var4 = new IEGeneric[0];
      } else {
         this.pC(var2 == 2);
         IEGeneric var5 = this.pC(var1, 1);
         var4 = new IEGeneric[]{var5};
      }

      IEUntranslatedInstruction var6 = this.ctx.createUntranslatedInstruction(var1.address, ((sy)var1.insn).pC(false), var4);
      var1.r.add(var6);
   }

   @AbstractConverter.Mnemonic("RET")
   void hw(ConverterInstructionEntry var1) {
      this.kS(var1, 2);
      this.kS(var1, "RET_DEPTH", "NODEC");
      String var2 = this.pC(var1, "RELONLY", "ABSONLY_ret");
      switch (var2) {
         case "REL":
         case "ABS":
            IEGeneric var3 = this.pC(var1, 0);
            IEGeneric var7 = this.pC(var1, 1);
            if (!this.UT(var3)) {
               var1.r.add(this.ctx.createJump(var1.irAddress + var1.r.size() + 2, EUtil.notL(var3)));
            }

            IEGeneric var5 = var7.zeroExtend(this.gp.getBitsize());
            IEAssign var6 = this.ctx.createBranchAssign(this.gp, var5, false);
            var1.r.add(var6);
            return;
         default:
            throw new UnsupportedConversionException();
      }
   }

   @AbstractConverter.Mnemonic("WARPSYNC")
   void qG(ConverterInstructionEntry var1) {
      this.pC(var1, 1, 2, 3);
      String var2 = this.pC(var1, "EXCLUSIVE", "DIV__EXCLUSIVE");
      if (var2 != null) {
         var2.equals("EXCLUSIVE");
      }

      IEGeneric var3 = this.pC(var1, 0);
      boolean var4 = false;
      if (this.UT(var3)) {
         var4 = true;
      }

      this.pC(var1, var4);
   }

   @AbstractConverter.Mnemonic("YIELD")
   void yi(ConverterInstructionEntry var1) {
      this.kS(var1, 1);
      IEGeneric var2 = this.pC(var1, 0);
      if (this.UT(var2)) {
         this.ET(var1);
      } else {
         this.pC(var1);
      }
   }

   @AbstractConverter.Mnemonic("ENDCOLLECTIVE")
   void zK(ConverterInstructionEntry var1) {
      this.kS(var1, 1);
      IEGeneric var2 = this.pC(var1, 0);
      boolean var3 = false;
      if (this.UT(var2)) {
         var3 = true;
      }

      this.pC(var1, var3);
   }

   @AbstractConverter.Mnemonic("BAR")
   void LA(ConverterInstructionEntry var1) {
      this.ET(var1);
   }

   @AbstractConverter.Mnemonic("CS2R")
   void ve(ConverterInstructionEntry var1) {
      this.kS(var1, 2);
      String var2 = this.A(var1, "QInteger");
      String var4 = var2.replace("_", "");

      byte var3 = switch (var4) {
         case "32" -> 32;
         case "64" -> 64;
         default -> throw new UnsupportedConversionException();
      };
      IEGeneric var6 = this.gp(this.A(var1, 0, var3));
      IEGeneric var7 = this.A(var1, 1, var3).part(var3);
      this.pC(var1, var6, var7);
   }

   @AbstractConverter.Mnemonic("LEPC")
   void yv(ConverterInstructionEntry var1) {
      int var2 = this.kS(var1, 1, 2);
      IEGeneric var3 = this.gp(this.A(var1, 0, this.getAddressBitsize()));
      IEGeneric var4 = var2 == 3 ? this.pC(var1, 1) : null;
      this.pC(var4 == null || EUtil.isZero(var4));
      IEImm var5 = EUtil.imm(var1.address, this.getAddressBitsize());
      this.pC(var1, var3, var5);
   }

   @AbstractConverter.Mnemonic("NOP")
   void MZ(ConverterInstructionEntry var1) {
      this.ET(var1);
   }

   @AbstractConverter.Mnemonic("S2R")
   void fH(ConverterInstructionEntry var1) {
      this.kS(var1, 2);
      IEGeneric var2 = this.gp(this.A(var1, 0, 32));
      IEGeneric var3 = this.A(var1, 1, 32).part(32);
      this.pC(var1, var2, var3);
   }

   static {
      for (Method var3 : rQ.class.getDeclaredMethods()) {
         AbstractConverter.Mnemonic var4 = (AbstractConverter.Mnemonic)var3.getAnnotation(AbstractConverter.Mnemonic.class);
         if (var4 != null) {
            String var5 = var4.value();
            Method var10000 = (Method)UO.put(var5, var3);
         }
      }

      pC = 0;
      A = 8192;
      kS = 16384;
      wS = 61440;
      UT = 61696;
      E = 61952;
      sY = 62208;
      ys = 65536;
   }
}
