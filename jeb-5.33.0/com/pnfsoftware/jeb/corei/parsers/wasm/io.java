package com.pnfsoftware.jeb.corei.parsers.wasm;

import com.pnfsoftware.jeb.core.JebCoreService;
import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.AbstractConverter;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ConverterInstructionEntry;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICCall;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElementFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLeftExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.IllegalConversionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.UnsupportedConversionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECall;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEPrototypeHandler;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEReturn;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESwitch;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEUntranslatedInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardPrototype;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardTypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodDataItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.ICallingConvention;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrototypeItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.collect.Maps;
import com.pnfsoftware.jeb.util.collect.ReferenceCounter;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import com.pnfsoftware.jebglobal.aku;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Ser
public class io extends AbstractConverter {
   private static final ILogger fI = GlobalLog.getLogger(io.class);
   @SerId(1)
   ReferenceCounter pC;
   @SerId(2)
   int A;
   @SerId(3)
   int kS;
   @SerId(4)
   int wS;
   @SerId(5)
   IEVar UT;
   @SerId(6)
   IEVar E;
   @SerId(7)
   Hv sY;
   @SerId(8)
   INativeCodeUnit ys;
   @SerTransient
   long ld;
   @SerTransient
   cq gp;
   @SerTransient
   List oT;
   @SerTransient
   private List WR = new LinkedList();
   @SerTransient
   private int NS = 0;
   @SerTransient
   private int vP = 0;

   protected io(Hv var1, INativeCodeUnit var2) {
      super(var1.pC(), 32);
      this.sY = var1;
      if (var2 == null) {
         throw new IllegalArgumentException();
      } else {
         this.ys = var2;
         this.UT = this.gCtx.createRegister(0, "pc", this.regNormalBitsize);
         this.E = this.gCtx.createRegister(256, "sp", this.regNormalBitsize);
         this.pC = new ReferenceCounter();
      }
   }

   @Override
   public IEVar getProgramCounter() {
      return this.UT;
   }

   @Override
   public IEVar getStackPointer() {
      return this.E;
   }

   private IEVar pC(String var1, int var2) {
      return this.ctx.createVirtualVar(var1, var2);
   }

   @Override
   protected void preRoutineConversion(INativeMethodItem var1, IERoutineContext var2, List var3) {
      super.preRoutineConversion(var1, var2, var3);
      this.wS();
      INativeMethodDataItem var4 = var1.getData();
      this.ld = var4.getCFG().getLastAddress();
      this.gp = this.pC(var1);
      int var5 = 0;
      this.oT = new ArrayList();

      for (int var9 : this.gp.pC.A) {
         IEVar var10 = this.pC("par" + var5, this.pC(var9));
         var5++;
         this.oT.add(var10);
      }

      var5 = 0;

      for (int var13 : this.gp.A.pC) {
         IEVar var14 = this.pC("r" + var5, this.pC(var13));
         var5++;
         this.oT.add(var14);
      }

      this.WR = new LinkedList();
      this.NS = 0;
      this.vP = 0;
   }

   @Override
   protected void postRoutineConversion(INativeMethodItem var1, IERoutineContext var2) {
      for (IEStatement var4 : var2.getStatements()) {
         if (var4 instanceof IESwitch var5) {
            long var6 = var5.getPrimaryLowerLevelAddress();
            m var8 = (m)var1.getData().getCFG().getInstruction(var6);
            int var9 = 0;

            for (int var11 : var8.oT) {
               long var12 = this.gp.kS + var11;
               int var14 = var2.convertNativeAddress(var12).intValue();
               if (var9 == var8.oT.size() - 1) {
                  var5.setDefaultAddress(var14);
               } else {
                  var5.addCase(EUtil.imm(var9, var5.getControlExpression().getBitsize()), var14);
               }

               var9++;
            }
         }
      }

      this.oT = null;
      this.gp = null;
      this.WR = null;
      this.NS = 0;
      this.vP = 0;
   }

   public List pC(IERoutineContext var1) {
      cq var2 = this.kS(var1);
      ArrayList var3 = new ArrayList();

      for (int var4 = 0; var4 < var2.pC.A.length; var4++) {
         var3.add(var1.getVariableByName("par" + var4));
      }

      return var3;
   }

   public IWildcardType A(IERoutineContext var1) {
      cq var2 = this.kS(var1);
      IWildcardTypeManager var3 = var1.getWildcardTypeManager();
      Integer var4 = var2.pC.pC;
      return var4 == null ? var3.create("void") : var3.create(this.A(var4));
   }

   int pC(int var1) {
      switch (var1) {
         case 124:
         case 126:
            return 64;
         case 125:
         case 127:
            return 32;
         default:
            throw new RuntimeException();
      }
   }

   INativeType A(int var1) {
      ITypeManager var2 = this.ys.getTypeManager();
      switch (var1) {
         case 124:
            return var2.getExactFloat(8);
         case 125:
            return var2.getExactFloat(4);
         case 126:
            return var2.getExactInteger(8, true);
         case 127:
            return var2.getExactInteger(4, true);
         default:
            throw new RuntimeException();
      }
   }

   @Override
   protected void convertBlock(BasicBlock var1, List var2) {
      long var3 = var1.getFirstAddress();
      long var5 = var3;
      ArrayList var7 = new ArrayList();
      ConverterInstructionEntry var8 = new ConverterInstructionEntry();
      var8.r = var7;
      boolean var9 = false;
      m var10 = null;

      try {
         for (int var11 = 0; var11 < var1.size(); var11++) {
            this.A++;
            var10 = (m)var1.get(var11);
            if (var11 == 0) {
               this.NS = var10.sY;
            }

            var7.clear();
            int var40 = var2.size();
            var8.insn = var10;
            var8.address = var5;
            var8.irAddress = var40;
            Integer var13 = null;
            int var14 = var10.pC();
            switch (var14) {
               case 0:
                  var8.r.add(this.ctx.createJumpFar(this.ctx.createImm(0L, this.getAddressBitsize())));
                  break;
               case 1:
                  var8.r.add(this.ctx.createNop());
                  break;
               case 2:
               case 3:
                  var8.r.add(this.ctx.createNop());
                  break;
               case 4:
                  IEVar var99 = this.A();
                  long var114 = var1.getOutputBlock(0).getFirstAddress();
                  long var139 = var1.getOutputBlock(1).getFirstAddress();
                  IEAssign var148 = this.ctx
                     .createBranchAssign(this.UT, this.ctx.createCond(var99, this.ctx.createImm(var114, 32), this.ctx.createImm(var139, 32)), false);
                  var8.r.add(var148);
                  break;
               case 5:
                  this.NS = var10.ys;
                  long var98 = var1.getOutputBlock(0).getFirstAddress();
                  IEAssign var128 = this.ctx.createBranchAssign(this.UT, this.ctx.createImm(var98, 32), false);
                  var8.r.add(var128);
                  break;
               case 6:
               case 7:
               case 8:
               case 9:
               case 10:
               case 18:
               case 19:
               case 20:
               case 21:
               case 22:
               case 23:
               case 24:
               case 25:
               case 28:
               case 29:
               case 30:
               case 31:
               case 37:
               case 38:
               case 39:
               default:
                  this.kS++;
                  if (this.pC != null) {
                     this.pC.inc(var10.getMnemonic());
                  }

                  throw new UnsupportedConversionException("Cannot convert instruction: " + var10);
               case 11:
                  if (var5 != this.ld || var9 || !this.pC()) {
                     var8.r.add(this.ctx.createNop());
                     break;
                  }
               case 15:
                  Integer var113 = this.gp.pC.pC;
                  IEVar var127 = var113 == null ? null : this.A();
                  IEReturn var97 = this.ctx.createReturn(var127);
                  var8.r.add(var97);
                  var9 = true;
                  break;
               case 12:
                  BasicBlock var96 = var1.getOutputBlock(0);
                  m var112 = var10.WR;
                  if (var112.pC() == 2 || var112.pC() == 4) {
                     int var125 = var112.ys;
                     int var138 = var112.oT();
                     if (Tb.E(var138)) {
                        var125++;
                     }

                     if (this.NS != var125) {
                        int var142 = this.NS - var125;
                        IEVar var147 = null;
                        if (Tb.E(var138)) {
                           var147 = this.A();
                        }

                        this.sY(var142);
                        if (var147 != null) {
                           this.pC(var8, var138, var147);
                        }
                     }
                  }

                  long var126 = var96.getFirstAddress();
                  IEAssign var143 = this.ctx.createBranchAssign(this.UT, this.ctx.createImm(var126, 32), false);
                  var8.r.add(var143);
                  break;
               case 13:
                  IEVar var95 = this.A();
                  long var111 = var1.getOutputBlock(0).getFirstAddress();
                  long var137 = var1.getOutputBlock(1).getFirstAddress();
                  Integer var146 = null;
                  int var152 = 0;
                  m var157 = var10.WR;
                  if (var157.pC() == 2 || var157.pC() == 4) {
                     var152 = var157.oT();
                     if (Tb.E(var152)) {
                        int var161 = var157.ys;
                        if (this.NS != var161 + 1) {
                           var146 = this.NS - (var161 + 1);
                        }
                     }
                  }

                  if (var146 != null) {
                     var8.r.add(this.ctx.createJump(var8.irAddress + 3, this.ctx.createOperation(OperationType.LOG_NOT, var95)));
                     IEVar var162 = this.A();
                     this.sY(var146);
                     this.pC(var8, var152, var162);
                     IEAssign var167 = this.ctx.createBranchAssign(this.UT, this.ctx.createImm(var137, 32), false);
                     var8.r.add(var167);
                  } else {
                     IEAssign var163 = this.ctx
                        .createBranchAssign(this.UT, this.ctx.createCond(var95, this.ctx.createImm(var137, 32), this.ctx.createImm(var111, 32)), false);
                     var8.r.add(var163);
                  }
                  break;
               case 14:
                  IEVar var94 = this.A();
                  Integer var110 = null;

                  for (m var136 : var10.NS) {
                     if (var136.pC() == 2 || var136.pC() == 4) {
                        int var141 = var136.oT();
                        if (var110 == null) {
                           var110 = var141;
                        } else if (var141 != var110) {
                           throw new IllegalConversionException("br_table labels have inconsistent types");
                        }

                        if (Tb.E(var141)) {
                           int var145 = var136.ys;
                           if (this.NS != var145 + 1) {
                              IEVar var151 = this.kS();
                              int var156 = this.NS - (var145 + 1);
                              this.NS -= var156;
                              this.pC(var8, var141, var151);
                              this.NS += var156 - 1;
                           }
                        }
                     }
                  }

                  IESwitch var124 = this.ctx.createSwitch(var94, -1);
                  var8.r.add(var124);
                  break;
               case 16:
                  int var93 = var10.gp();
                  cq var109 = this.sY.pC(var93);
                  INativeMethodItem var122 = this.getNativeContext().getRoutine(var109.kS);
                  if (var122 == null) {
                     var122 = this.getNativeContext().getRoutineByName(var109.ys());
                     if (var122 == null) {
                        throw new UnsupportedConversionException("Cannot resolve routine: " + var109.ld());
                     }
                  }

                  IPrototypeItem var135 = var122.getPrototype();
                  if (var135 == null) {
                     this.pC(var122, false);
                  }

                  ArrayList var140 = new ArrayList();
                  ArrayList var144 = new ArrayList();

                  for (int var165 : var109.pC().A()) {
                     INativeType var168 = this.A(var165);
                     var140.add(var168);
                     var144.add(this.A());
                  }

                  Integer var150 = var109.pC().pC();
                  ArrayList var155 = new ArrayList();
                  if (var150 != null) {
                     var155.add(this.E(var150));
                  }

                  IEVar var160 = this.ctx.createSymbolForRoutine(var122);
                  IECall var166 = this.ctx.createCall(var160, null, var155, var144, 0, null, null);
                  var8.r.add(var166);
                  break;
               case 17:
                  int var92 = var10.gp();
                  DH var108 = this.sY.kS(var92);
                  IPrototypeItem var121 = this.pC(var108);
                  IWildcardPrototype var134 = this.gCtx.getWildcardTypeManager().createPrototype(var121);
                  IEVar var23 = this.A();
                  ArrayList var24 = new ArrayList();
                  ArrayList var25 = new ArrayList();

                  for (int var29 : var108.A()) {
                     INativeType var30 = this.A(var29);
                     var24.add(var30);
                     var25.add(this.A());
                  }

                  Integer var153 = var108.pC();
                  ArrayList var158 = new ArrayList();
                  if (var153 != null) {
                     var158.add(this.E(var153));
                  }

                  long var164 = this.sY.kS();
                  INativeContinuousItem var169 = this.ys.getNativeItemAt(var164);
                  if (!(var169 instanceof INativeDataItem)) {
                     throw new RuntimeException();
                  }

                  IEVar var31 = this.ctx.createSymbolForGlobalVariable((INativeDataItem)var169);
                  IEOperation var32 = this.ctx
                     .createOperation(OperationType.ADD, var31, this.ctx.createOperation(OperationType.MUL, var23, this.ctx.createImm(4L, 32)));
                  IEMem var33 = this.ctx.createMem(var32, 32);
                  IECall var34 = this.ctx.createCall(var33, null, var158, var25, 0, null, var134);
                  var8.r.add(var34);
                  break;
               case 26:
                  this.A();
                  break;
               case 27:
                  IEVar var91 = this.A();
                  IEVar var107 = this.A();
                  IEVar var120 = this.A();
                  this.pC(var8, 0, this.ctx.createCond(var91, var120, var107));
                  break;
               case 32:
                  int var53 = this.pC(var10);
                  IEVar var62 = this.wS(var53);
                  this.pC(var8, 0, var62);
                  break;
               case 33:
                  int var52 = this.pC(var10);
                  IEVar var61 = this.A();
                  this.kS(var8, var52, var61);
                  break;
               case 34:
                  int var51 = this.pC(var10);
                  IEVar var60 = this.kS();
                  this.kS(var8, var51, var60);
                  break;
               case 35:
                  int var50 = this.pC(var10);
                  var13 = this.sY.A(var50).pC.pC;
                  IEVar var59 = this.kS(var50);
                  this.pC(var8, var13, var59);
                  break;
               case 36:
                  int var49 = this.pC(var10);
                  IEVar var58 = this.A();
                  this.A(var8, var49, var58);
                  break;
               case 40:
               case 44:
               case 45:
               case 46:
               case 47:
                  byte var90 = 32;
                  if (var14 == 44 || var14 == 45) {
                     var90 = 8;
                  } else if (var14 == 46 || var14 == 47) {
                     var90 = 16;
                  }

                  Object var106 = this.A();
                  int var119 = ((Long)var10.ys()[0].kS()).intValue();
                  if (var119 != 0) {
                     var106 = this.ctx.createOperation(OperationType.ADD, (IEGeneric)var106, this.ctx.createImm(var119, ((IEGeneric)var106).getBitsize()));
                  }

                  Object var133 = this.ctx.createMem((IEGeneric)var106, var90);
                  if (var14 == 44 || var14 == 46) {
                     var133 = ((IEGeneric)var133).signExtend(32);
                  } else if (var14 == 45 || var14 == 47) {
                     var133 = ((IEGeneric)var133).zeroExtend(32);
                  }

                  this.pC(var8, 127, (IEGeneric)var133);
                  break;
               case 41:
               case 48:
               case 49:
               case 50:
               case 51:
               case 52:
               case 53:
                  byte var89 = 64;
                  if (var14 == 48 || var14 == 49) {
                     var89 = 8;
                  } else if (var14 == 50 || var14 == 51) {
                     var89 = 16;
                  } else if (var14 == 52 || var14 == 53) {
                     var89 = 32;
                  }

                  Object var105 = this.A();
                  int var118 = ((Long)var10.ys()[0].kS()).intValue();
                  if (var118 != 0) {
                     var105 = this.ctx.createOperation(OperationType.ADD, (IEGeneric)var105, this.ctx.createImm(var118, ((IEGeneric)var105).getBitsize()));
                  }

                  Object var132 = this.ctx.createMem((IEGeneric)var105, var89);
                  if (var14 == 48 || var14 == 50 || var14 == 52) {
                     var132 = ((IEGeneric)var132).signExtend(64);
                  } else if (var14 == 49 || var14 == 51 || var14 == 53) {
                     var132 = ((IEGeneric)var132).zeroExtend(64);
                  }

                  this.pC(var8, 126, (IEGeneric)var132);
                  break;
               case 42:
               case 43:
                  int var88 = var14 == 42 ? 32 : 64;
                  Object var104 = this.A();
                  int var117 = ((Long)var10.ys()[0].kS()).intValue();
                  if (var117 != 0) {
                     var104 = this.ctx.createOperation(OperationType.ADD, (IEGeneric)var104, this.ctx.createImm(var117, ((IEGeneric)var104).getBitsize()));
                  }

                  IEMem var131 = this.ctx.createMem((IEGeneric)var104, var88);
                  this.pC(var8, Tb.ld(var88), var131);
                  break;
               case 54:
               case 58:
               case 59:
                  byte var87 = 32;
                  if (var14 == 58) {
                     var87 = 8;
                  } else if (var14 == 59) {
                     var87 = 16;
                  }

                  IEVar var103 = this.A();
                  Object var116 = this.A();
                  int var130 = ((Long)var10.ys()[0].kS()).intValue();
                  if (var130 != 0) {
                     var116 = this.ctx.createOperation(OperationType.ADD, (IEGeneric)var116, this.ctx.createImm(var130, ((IEGeneric)var116).getBitsize()));
                  }

                  var8.r.add(this.ctx.createAssign(this.ctx.createMem((IEGeneric)var116, var87), var103.part(var87)));
                  break;
               case 55:
               case 60:
               case 61:
               case 62:
                  byte var86 = 64;
                  if (var14 == 60) {
                     var86 = 8;
                  } else if (var14 == 61) {
                     var86 = 16;
                  } else if (var14 == 62) {
                     var86 = 32;
                  }

                  IEVar var102 = this.A();
                  Object var115 = this.A();
                  int var129 = ((Long)var10.ys()[0].kS()).intValue();
                  if (var129 != 0) {
                     var115 = this.ctx.createOperation(OperationType.ADD, (IEGeneric)var115, this.ctx.createImm(var129, ((IEGeneric)var115).getBitsize()));
                  }

                  var8.r.add(this.ctx.createAssign(this.ctx.createMem((IEGeneric)var115, var86), var102.part(var86)));
                  break;
               case 56:
               case 57:
                  int var85 = var14 == 56 ? 32 : 64;
                  IEVar var101 = this.A();
                  Object var21 = this.A();
                  int var22 = ((Long)var10.ys()[0].kS()).intValue();
                  if (var22 != 0) {
                     var21 = this.ctx.createOperation(OperationType.ADD, (IEGeneric)var21, this.ctx.createImm(var22, ((IEGeneric)var21).getBitsize()));
                  }

                  var8.r.add(this.ctx.createAssign(this.ctx.createMem((IEGeneric)var21, var85), var101.part(var85)));
                  break;
               case 63:
                  this.pC(var8, 127, this.ctx.createImm(100L, 32));
                  break;
               case 64:
                  this.sY(1);
                  this.pC(var8, 127, this.ctx.createImm(100L, 32));
                  break;
               case 65:
                  long var48 = this.A(var10);
                  this.pC(var8, 127, this.ctx.createImm(var48, 32));
                  break;
               case 66:
                  long var47 = this.kS(var10);
                  this.pC(var8, 126, this.ctx.createImm(var47, 64));
                  break;
               case 67:
                  float var84 = this.wS(var10);
                  this.pC(var8, 125, this.ctx.createImm(var84));
                  break;
               case 68:
                  double var83 = this.UT(var10);
                  this.pC(var8, 124, this.ctx.createImm(var83));
                  break;
               case 69:
               case 80:
                  var13 = 127;
                  IEVar var46 = this.A();
                  IEImm var57 = EUtil.zero(var46.getBitsize());
                  IEOperation var67 = this.ctx.createOperation(OperationType.LOG_EQ, var46, var57);
                  IEGeneric var68 = var67.zeroExtend(32);
                  this.pC(var8, var13, var68);
                  break;
               case 70:
               case 71:
               case 72:
               case 73:
               case 74:
               case 75:
               case 76:
               case 77:
               case 78:
               case 79:
               case 81:
               case 82:
               case 83:
               case 84:
               case 85:
               case 86:
               case 87:
               case 88:
               case 89:
               case 90:
               case 106:
               case 107:
               case 108:
               case 109:
               case 110:
               case 111:
               case 112:
               case 113:
               case 114:
               case 115:
               case 116:
               case 117:
               case 118:
               case 119:
               case 120:
                  var13 = 127;
               case 124:
               case 125:
               case 126:
               case 127:
               case 128:
               case 129:
               case 130:
               case 131:
               case 132:
               case 133:
               case 134:
               case 135:
               case 136:
               case 137:
               case 138:
                  if (var13 == null) {
                     var13 = 126;
                  }

                  OperationType var45 = this.ys(var14);
                  if (var45 == null) {
                     throw new RuntimeException("TBI: Wasm operator not converted");
                  }

                  IEVar var56 = this.A();
                  IEVar var66 = this.A();
                  IEOperation var72 = this.ctx.createOperation(var45, var66, var56);
                  IEGeneric var73;
                  if (var13 == 127) {
                     var73 = var72.zeroExtend(32);
                  } else {
                     if (var13 != 126) {
                        throw new RuntimeException();
                     }

                     var73 = var72.zeroExtend(64);
                  }

                  this.pC(var8, var13, var73);
                  break;
               case 91:
               case 92:
               case 93:
               case 94:
               case 95:
               case 96:
               case 97:
               case 98:
               case 99:
               case 100:
               case 101:
               case 102:
                  OperationType var44 = this.ys(var14);
                  if (var44 == null) {
                     throw new RuntimeException("TBI: Wasm operator not converted");
                  }

                  IEVar var55 = this.A();
                  IEVar var65 = this.A();
                  IEOperation var70 = this.ctx.createOperation(var44, var65, var55);
                  IEGeneric var71 = var70.zeroExtend(32);
                  this.pC(var8, 127, var71);
                  break;
               case 103:
               case 104:
               case 105:
                  if (var13 == null) {
                     var13 = 127;
                  }
               case 121:
               case 122:
               case 123:
                  if (var13 == null) {
                     var13 = 126;
                  }
               case 150:
               case 151:
               case 152:
                  if (var13 == null) {
                     var13 = 125;
                  }
               case 164:
               case 165:
               case 166:
                  if (var13 == null) {
                     var13 = 124;
                  }
               case 139:
               case 140:
               case 141:
               case 142:
               case 143:
               case 144:
               case 145:
                  if (var13 == null) {
                     var13 = 125;
                  }
               case 153:
               case 154:
               case 155:
               case 156:
               case 157:
               case 158:
               case 159:
                  if (var13 == null) {
                     var13 = 124;
                  }
               case 188:
                  if (var13 == null) {
                     var13 = 127;
                  }
               case 189:
                  if (var13 == null) {
                     var13 = 126;
                  }
               case 190:
                  if (var13 == null) {
                     var13 = 125;
                  }
               case 191:
                  if (var13 == null) {
                     var13 = 124;
                  }

                  int var43 = var10.pC.pC();
                  IEGeneric[] var54 = new IEGeneric[var43];

                  for (int var63 = 0; var63 < var43; var63++) {
                     var54[var43 - 1 - var63] = this.A();
                  }

                  IEVar var64 = this.E(var13);
                  IEUntranslatedInstruction var69 = pC(this.ctx, var5, var10, var54);
                  var69.setReturnExpression(var64);
                  var8.r.add(var69);
                  break;
               case 146:
               case 147:
               case 148:
               case 149:
                  var13 = 125;
               case 160:
               case 161:
               case 162:
               case 163:
                  if (var13 == null) {
                     var13 = 124;
                  }

                  OperationType var15 = this.ys(var14);
                  if (var15 == null) {
                     throw new RuntimeException("TBI: Wasm operator not converted");
                  }

                  IEVar var16 = this.A();
                  IEVar var17 = this.A();
                  IEOperation var18 = this.ctx.createOperation(var15, var17, var16);
                  this.pC(var8, var13, var18);
                  break;
               case 167:
                  IEVar var82 = this.A();
                  this.pC(var8, 127, this.ctx.createConversionOperation(OperationType.CAST, var82, 32));
                  break;
               case 168:
               case 169:
               case 170:
               case 171:
                  IEVar var81 = this.A();
                  this.pC(var8, 127, this.ctx.createConversionOperation(OperationType.FP2INT, var81, 32));
                  break;
               case 172:
               case 173:
                  IEVar var80 = this.A();
                  this.pC(var8, 126, this.ctx.createResizeOperation(var80, 64, var14 == 172));
                  break;
               case 174:
               case 175:
               case 176:
               case 177:
                  IEVar var79 = this.A();
                  this.pC(var8, 126, this.ctx.createConversionOperation(OperationType.FP2INT, var79, 64));
                  break;
               case 178:
               case 179:
               case 180:
               case 181:
                  IEVar var78 = this.A();
                  this.pC(var8, 125, this.ctx.createConversionOperation(OperationType.INT2FP, var78, 32));
                  break;
               case 182:
                  IEVar var77 = this.A();
                  this.pC(var8, 125, this.ctx.createConversionOperation(OperationType.FP2FP, var77, 32));
                  break;
               case 183:
               case 184:
               case 185:
               case 186:
                  IEVar var76 = this.A();
                  this.pC(var8, 124, this.ctx.createConversionOperation(OperationType.INT2FP, var76, 64));
                  break;
               case 187:
                  IEVar var75 = this.A();
                  this.pC(var8, 124, this.ctx.createConversionOperation(OperationType.FP2FP, var75, 64));
                  break;
               case 192:
               case 193:
                  IEVar var74 = this.A();
                  int var100 = var14 == 192 ? 8 : 16;
                  this.pC(var8, 127, this.ctx.createResizeOperation(var74.part(var100), 32, true));
                  break;
               case 194:
               case 195:
               case 196:
                  IEVar var19 = this.A();
                  int var20 = var14 == 194 ? 8 : (var14 == 195 ? 16 : 32);
                  this.pC(var8, 126, this.ctx.createResizeOperation(var19.part(var20), 64, true));
            }

            if (var8.r.isEmpty()) {
               var8.r.add(this.ctx.createNop());
            }

            EUtil.setLowerLevelAddress(var5, var7);
            var2.addAll(var7);
            var5 += var10.getSize();
         }
      } catch (Throwable var38) {
         String var12 = Strings.ff("%s @ 0x%X", var10.getMnemonic(), var5);
         JebCoreService.notifySilentExceptionToClient(var38, Maps.toMap("Instruction", var12));
         fI.error("Error: Instruction cannot be converted: %Xh: %s: %s", var5, Formatter.byteArrayToHex(var10.getCode()), var10.format(var5));
         fI.catchingSilent(var38);
         throw var38;
      } finally {
         ;
      }
   }

   @Override
   public String formatStatistics() {
      StringBuilder var1 = new StringBuilder(super.formatStatistics());
      var1.append("\n\n");
      Strings.ff(var1, "Converted instructions: %d (incl. failed=%d, untranslated=%d)\n", this.A, this.kS, this.wS);
      if (this.pC != null && !this.pC.isEmpty()) {
         var1.append("Failed instructions: ").append(this.pC.formatTopReferences(-1));
      }

      return var1.toString();
   }

   IEVar kS(int var1) {
      rQ var3 = this.sY.A(var1);
      INativeContinuousItem var4 = this.ys.getNativeItemAt(var3.kS);
      if (var4 instanceof INativeContinuousItem) {
         IEVar var2 = this.gCtx.createGlobalVariable(var4.getMemoryAddress(), (int)var4.getMemorySize() * 8);
         ((aku)var2).pC(var4.getName(true));
         return var2;
      } else {
         throw new RuntimeException("Cannot retrieve native data item for global: " + var3);
      }
   }

   IEVar wS(int var1) {
      return (IEVar)this.oT.get(var1);
   }

   void pC(ConverterInstructionEntry var1, int var2, IEGeneric var3) {
      IEVar var4;
      if (var2 == 0) {
         var4 = this.UT(var3.getBitsize());
      } else {
         var4 = this.E(var2);
      }

      var1.r.add(this.ctx.createAssign(var4, var3));
   }

   IEVar UT(int var1) {
      IEVar var2;
      if (this.NS < this.WR.size()) {
         var2 = this.pC("var" + this.vP, var1);
         this.WR.set(this.NS, new io.Av(var2, 0));
         this.vP++;
      } else {
         var2 = this.pC("var" + this.vP, var1);
         this.WR.add(new io.Av(var2, 0));
         this.vP++;
      }

      this.NS++;
      return var2;
   }

   IEVar E(int var1) {
      Assert.a(var1 != 0);
      IEVar var2;
      if (this.NS < this.WR.size()) {
         io.Av var3 = (io.Av)this.WR.get(this.NS);
         if (var3.A == var1) {
            var2 = var3.pC;
         } else {
            int var4 = Tb.sY(var1) * 8;
            var2 = this.pC("var" + this.vP, var4);
            this.WR.set(this.NS, new io.Av(var2, var1));
            this.vP++;
         }
      } else {
         int var5 = Tb.sY(var1) * 8;
         var2 = this.pC("var" + this.vP, var5);
         this.WR.add(new io.Av(var2, var1));
         this.vP++;
      }

      this.NS++;
      return var2;
   }

   boolean pC() {
      return this.NS > 0;
   }

   IEVar A() {
      Assert.a(this.NS > 0);
      this.NS--;
      return ((io.Av)this.WR.get(this.NS)).pC;
   }

   IEVar kS() {
      Assert.a(this.NS > 0);
      return ((io.Av)this.WR.get(this.NS - 1)).pC;
   }

   void sY(int var1) {
      while (var1-- > 0) {
         this.A();
      }
   }

   void A(ConverterInstructionEntry var1, int var2, IEGeneric var3) {
      IEVar var4 = this.kS(var2);
      var1.r.add(this.ctx.createAssign(var4, var3.zeroExtend(var4.getBitsize())));
   }

   void kS(ConverterInstructionEntry var1, int var2, IEGeneric var3) {
      IEVar var4 = this.wS(var2);
      var1.r.add(this.ctx.createAssign(var4, var3.zeroExtend(var4.getBitsize())));
   }

   int pC(m var1) {
      long var2 = (Long)var1.ys()[0].kS();
      if (var2 >= 2147483648L) {
         throw new RuntimeException();
      } else {
         return (int)var2;
      }
   }

   long A(m var1) {
      return (Long)var1.ys()[0].kS() & 4294967295L;
   }

   long kS(m var1) {
      return (Long)var1.ys()[0].kS();
   }

   float wS(m var1) {
      return Float.intBitsToFloat(((Long)var1.ys()[0].kS()).intValue());
   }

   double UT(m var1) {
      return Double.longBitsToDouble((Long)var1.ys()[0].kS());
   }

   OperationType ys(int var1) {
      switch (var1) {
         case 70:
         case 81:
            return OperationType.LOG_EQ;
         case 71:
         case 82:
            return OperationType.LOG_NEQ;
         case 72:
         case 83:
            return OperationType.LT_S;
         case 73:
         case 84:
            return OperationType.LT_U;
         case 74:
         case 85:
            return OperationType.GT_S;
         case 75:
         case 86:
            return OperationType.GT_U;
         case 76:
         case 87:
            return OperationType.LE_S;
         case 77:
         case 88:
            return OperationType.LE_U;
         case 78:
         case 89:
            return OperationType.GE_S;
         case 79:
         case 90:
            return OperationType.GE_U;
         case 80:
         case 103:
         case 104:
         case 105:
         case 121:
         case 122:
         case 123:
         case 139:
         case 140:
         case 141:
         case 142:
         case 143:
         case 144:
         case 145:
         case 150:
         case 151:
         case 152:
         case 153:
         case 154:
         case 155:
         case 156:
         case 157:
         case 158:
         case 159:
         default:
            return null;
         case 91:
         case 97:
            return OperationType.FEQ;
         case 92:
         case 98:
            return OperationType.FNE;
         case 93:
         case 99:
            return OperationType.FLT;
         case 94:
         case 100:
            return OperationType.FGT;
         case 95:
         case 101:
            return OperationType.FLE;
         case 96:
         case 102:
            return OperationType.FGE;
         case 106:
         case 124:
            return OperationType.ADD;
         case 107:
         case 125:
            return OperationType.SUB;
         case 108:
         case 126:
            return OperationType.MUL;
         case 109:
         case 127:
            return OperationType.DIV_S;
         case 110:
         case 128:
            return OperationType.DIV_U;
         case 111:
         case 129:
            return OperationType.REM_S;
         case 112:
         case 130:
            return OperationType.REM_U;
         case 113:
         case 131:
            return OperationType.AND;
         case 114:
         case 132:
            return OperationType.OR;
         case 115:
         case 133:
            return OperationType.XOR;
         case 116:
         case 134:
            return OperationType.SHL;
         case 117:
         case 135:
            return OperationType.SAR;
         case 118:
         case 136:
            return OperationType.SHR;
         case 119:
         case 137:
            return OperationType.ROL;
         case 120:
         case 138:
            return OperationType.ROR;
         case 146:
         case 160:
            return OperationType.FADD;
         case 147:
         case 161:
            return OperationType.FSUB;
         case 148:
         case 162:
            return OperationType.FMUL;
         case 149:
         case 163:
            return OperationType.FDIV;
      }
   }

   @Override
   public boolean canCreateCalls() {
      return false;
   }

   @Override
   public List convertParameterExpressions(IERoutineContext var1, IWildcardPrototype var2, INativeMethodItem var3, List var4) {
      throw new RuntimeException("Do not use");
   }

   @Override
   public IEGeneric convertReturnLocation(IERoutineContext var1, IWildcardPrototype var2) {
      throw new RuntimeException("Do not use");
   }

   @Override
   public List convertReturnExpressions(IERoutineContext var1, IWildcardPrototype var2, INativeMethodItem var3, List var4, List var5) {
      throw new RuntimeException("Do not use");
   }

   @Override
   public Integer determineStackPointerDeltaAfterIRCall(IWildcardPrototype var1, List var2) {
      return null;
   }

   @Override
   public int insertReturns(IERoutineContext var1) {
      return 0;
   }

   public void wS() {
      for (INativeMethodItem var2 : this.getNativeContext().getRoutines()) {
         this.pC(var2, false);
      }
   }

   public IPrototypeItem pC(INativeMethodItem var1, boolean var2) {
      IPrototypeItem var3 = var1.getPrototype();
      if (var3 == null || var2) {
         cq var4 = this.sY.wS(var1.getName());
         var3 = this.pC(var4.pC());
         var1.setPrototype(var3);
      }

      return var3;
   }

   IPrototypeItem pC(DH var1) {
      ICallingConvention var2 = this.ys.getTypeManager().getCallingConventionManager().getDefaultConvention();
      ArrayList var3 = new ArrayList();

      for (int var7 : var1.A()) {
         INativeType var8 = this.A(var7);
         var3.add(var8);
      }

      Integer var9 = var1.pC();
      INativeType var10 = null;
      if (var9 != null) {
         var10 = this.A(var9);
      }

      return this.ys.getTypeManager().createPrototype(var2, var10, var3, null);
   }

   @Override
   public IEPrototypeHandler getPrototypeHandler(IERoutineContext var1) {
      return new Sb(var1);
   }

   private cq pC(INativeMethodItem var1) {
      return this.sY.wS(var1.getName());
   }

   private cq kS(IERoutineContext var1) {
      return this.pC(var1.getRoutine());
   }

   public static IEUntranslatedInstruction pC(IERoutineContext var0, long var1, m var3, IEGeneric... var4) {
      IEUntranslatedInstruction var5 = var0.createUntranslatedInstruction(var1, var3.getMnemonic(), var4);
      var5.setTag(var3.pC());
      return var5;
   }

   @Override
   public ICStatement generateASTForUntranslatedIR(IEUntranslatedInstruction var1, IERoutineContext var2, ICMethod var3) {
      String var4 = var1.getNativeMnemonic();
      if (var4 == null) {
         return null;
      } else {
         String var5 = var4.toLowerCase();
         if (Strings.startsWith(var5, "i32.", "i64.", "f32.", "f64.")) {
            var5 = var5.substring(4);
         }

         ICElementFactory var6 = var3.getElementFactory();
         ICMethod var7 = var3.getMethodFactory().createSynthetic(var5);
         ArrayList var8 = new ArrayList();

         for (IEGeneric var10 : var1.getParameterExpressions()) {
            ICExpression var11 = (ICExpression)var10.generateC(var2, var3);
            var8.add(var11);
         }

         ICLeftExpression var13 = null;
         IEGeneric var14 = var1.getReturnExpression();
         if (var14 != null) {
            var13 = (ICLeftExpression)var14.generateC(var2, var3);
         }

         ICCall var12 = var6.createCall(var7, var8);
         Object var15;
         if (var13 != null) {
            var15 = var6.createAssignment(var13, var12);
         } else {
            var15 = var12;
         }

         return (ICStatement)var15;
      }
   }

   static class Av {
      IEVar pC;
      int A;

      Av(IEVar var1, int var2) {
         this.pC = var1;
         this.A = var2;
      }

      @Override
      public String toString() {
         return this.pC == null ? "null" : this.pC.toString();
      }
   }
}
