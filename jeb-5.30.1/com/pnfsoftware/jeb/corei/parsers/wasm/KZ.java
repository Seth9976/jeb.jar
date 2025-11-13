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
import com.pnfsoftware.jebglobal.amy;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Ser
public class KZ extends AbstractConverter {
   private static final ILogger qa = GlobalLog.getLogger(KZ.class);
   @SerId(1)
   ReferenceCounter q;
   @SerId(2)
   int RF;
   @SerId(3)
   int xK;
   @SerId(4)
   int Dw;
   public static final int Uv = 0;
   public static final int oW = 256;
   public static final String gO = "par";
   public static final String nf = "r";
   public static final String gP = "var";
   @SerId(5)
   IEVar za;
   @SerId(6)
   IEVar lm;
   @SerId(7)
   qx zz;
   @SerId(8)
   INativeCodeUnit JY;
   @SerTransient
   long HF;
   @SerTransient
   Nt LK;
   @SerTransient
   List io;
   @SerTransient
   private List Hk = new LinkedList();
   @SerTransient
   private int Me = 0;
   @SerTransient
   private int PV = 0;

   String q() {
      StringBuilder var1 = new StringBuilder();
      int var2 = 0;

      for (KZ.eo var4 : this.Hk) {
         if (var2 == this.Me) {
            var1.append("^ ");
         } else {
            var1.append("  ");
         }

         var1.append(var4);
         var2++;
      }

      if (this.Me == this.Hk.size()) {
         var1.append("^");
      }

      return var1.toString();
   }

   protected KZ(qx var1, INativeCodeUnit var2) {
      super(var1.q(), 32);
      this.zz = var1;
      if (var2 == null) {
         throw new IllegalArgumentException();
      } else {
         this.JY = var2;
         this.za = this.gCtx.createRegister(0, "pc", this.regNormalBitsize);
         this.lm = this.gCtx.createRegister(256, "sp", this.regNormalBitsize);
         this.q = new ReferenceCounter();
      }
   }

   @Override
   public IEVar getProgramCounter() {
      return this.za;
   }

   @Override
   public IEVar getStackPointer() {
      return this.lm;
   }

   private IEVar q(String var1, int var2) {
      return this.ctx.createVirtualVar(var1, var2);
   }

   @Override
   protected void preRoutineConversion(INativeMethodItem var1, IERoutineContext var2, List var3) {
      super.preRoutineConversion(var1, var2, var3);
      this.Uv();
      INativeMethodDataItem var4 = var1.getData();
      this.HF = var4.getCFG().getLastAddress();
      this.LK = this.q(var1);
      int var5 = 0;
      this.io = new ArrayList();

      for (int var9 : this.LK.q.RF) {
         IEVar var10 = this.q("par" + var5, this.q(var9));
         var5++;
         this.io.add(var10);
      }

      var5 = 0;

      for (int var13 : this.LK.RF.q) {
         IEVar var14 = this.q("r" + var5, this.q(var13));
         var5++;
         this.io.add(var14);
      }

      this.Hk = new LinkedList();
      this.Me = 0;
      this.PV = 0;
   }

   @Override
   protected void postRoutineConversion(INativeMethodItem var1, IERoutineContext var2) {
      for (IEStatement var4 : var2.getStatements()) {
         if (var4 instanceof IESwitch var5) {
            long var6 = var5.getPrimaryLowerLevelAddress();
            SG var8 = (SG)var1.getData().getCFG().getInstruction(var6);
            int var9 = 0;

            for (int var11 : var8.zz) {
               long var12 = this.LK.xK + var11;
               int var14 = var2.convertNativeAddress(var12).intValue();
               if (var9 == var8.zz.size() - 1) {
                  var5.setDefaultAddress(var14);
               } else {
                  var5.addCase(EUtil.imm(var9, var5.getControlExpression().getBitsize()), var14);
               }

               var9++;
            }
         }
      }

      this.io = null;
      this.LK = null;
      this.Hk = null;
      this.Me = 0;
      this.PV = 0;
   }

   public List q(IERoutineContext var1) {
      Nt var2 = this.Dw(var1);
      ArrayList var3 = new ArrayList();

      for (int var4 = 0; var4 < var2.q.RF.length; var4++) {
         var3.add(var1.getVariableByName("par" + var4));
      }

      return var3;
   }

   public List RF(IERoutineContext var1) {
      Nt var2 = this.Dw(var1);
      IWildcardTypeManager var3 = var1.getWildcardTypeManager();
      ArrayList var4 = new ArrayList();

      for (int var8 : var2.q.RF) {
         var4.add(var3.create(this.RF(var8)));
      }

      return var4;
   }

   public IWildcardType xK(IERoutineContext var1) {
      Nt var2 = this.Dw(var1);
      IWildcardTypeManager var3 = var1.getWildcardTypeManager();
      Integer var4 = var2.q.q;
      return var4 == null ? var3.create("void") : var3.create(this.RF(var4));
   }

   int q(int var1) {
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

   INativeType RF(int var1) {
      ITypeManager var2 = this.JY.getTypeManager();
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
      SG var10 = null;

      try {
         for (int var11 = 0; var11 < var1.size(); var11++) {
            this.RF++;
            var10 = (SG)var1.get(var11);
            if (var11 == 0) {
               this.Me = var10.nf;
            }

            var7.clear();
            int var40 = var2.size();
            var8.insn = var10;
            var8.address = var5;
            var8.irAddress = var40;
            Integer var13 = null;
            int var14 = var10.q();
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
                  IEVar var99 = this.xK();
                  long var114 = var1.getOutputBlock(0).getFirstAddress();
                  long var139 = var1.getOutputBlock(1).getFirstAddress();
                  IEAssign var148 = this.ctx
                     .createBranchAssign(this.za, this.ctx.createCond(var99, this.ctx.createImm(var114, 32), this.ctx.createImm(var139, 32)), false);
                  var8.r.add(var148);
                  break;
               case 5:
                  this.Me = var10.gP;
                  long var98 = var1.getOutputBlock(0).getFirstAddress();
                  IEAssign var128 = this.ctx.createBranchAssign(this.za, this.ctx.createImm(var98, 32), false);
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
                  this.xK++;
                  if (this.q != null) {
                     this.q.inc(var10.getMnemonic());
                  }

                  throw new UnsupportedConversionException("Cannot convert instruction: " + var10);
               case 11:
                  if (var5 != this.HF || var9 || !this.RF()) {
                     var8.r.add(this.ctx.createNop());
                     break;
                  }
               case 15:
                  Integer var113 = this.LK.q.q;
                  IEVar var127 = var113 == null ? null : this.xK();
                  IEReturn var97 = this.ctx.createReturn(var127);
                  var8.r.add(var97);
                  var9 = true;
                  break;
               case 12:
                  BasicBlock var96 = var1.getOutputBlock(0);
                  SG var112 = var10.HF;
                  if (var112.q() == 2 || var112.q() == 4) {
                     int var125 = var112.gP;
                     int var138 = var112.lm();
                     if (Xa.oW(var138)) {
                        var125++;
                     }

                     if (this.Me != var125) {
                        int var142 = this.Me - var125;
                        IEVar var147 = null;
                        if (Xa.oW(var138)) {
                           var147 = this.xK();
                        }

                        this.gO(var142);
                        if (var147 != null) {
                           this.q(var8, var138, var147);
                        }
                     }
                  }

                  long var126 = var96.getFirstAddress();
                  IEAssign var143 = this.ctx.createBranchAssign(this.za, this.ctx.createImm(var126, 32), false);
                  var8.r.add(var143);
                  break;
               case 13:
                  IEVar var95 = this.xK();
                  long var111 = var1.getOutputBlock(0).getFirstAddress();
                  long var137 = var1.getOutputBlock(1).getFirstAddress();
                  Integer var146 = null;
                  int var152 = 0;
                  SG var157 = var10.HF;
                  if (var157.q() == 2 || var157.q() == 4) {
                     var152 = var157.lm();
                     if (Xa.oW(var152)) {
                        int var161 = var157.gP;
                        if (this.Me != var161 + 1) {
                           var146 = this.Me - (var161 + 1);
                        }
                     }
                  }

                  if (var146 != null) {
                     var8.r.add(this.ctx.createJump(var8.irAddress + 3, this.ctx.createOperation(OperationType.LOG_NOT, var95)));
                     IEVar var162 = this.xK();
                     this.gO(var146);
                     this.q(var8, var152, var162);
                     IEAssign var167 = this.ctx.createBranchAssign(this.za, this.ctx.createImm(var137, 32), false);
                     var8.r.add(var167);
                  } else {
                     IEAssign var163 = this.ctx
                        .createBranchAssign(this.za, this.ctx.createCond(var95, this.ctx.createImm(var137, 32), this.ctx.createImm(var111, 32)), false);
                     var8.r.add(var163);
                  }
                  break;
               case 14:
                  IEVar var94 = this.xK();
                  Integer var110 = null;

                  for (SG var136 : var10.LK) {
                     if (var136.q() == 2 || var136.q() == 4) {
                        int var141 = var136.lm();
                        if (var110 == null) {
                           var110 = var141;
                        } else if (var141 != var110) {
                           throw new IllegalConversionException("br_table labels have inconsistent types");
                        }

                        if (Xa.oW(var141)) {
                           int var145 = var136.gP;
                           if (this.Me != var145 + 1) {
                              IEVar var151 = this.Dw();
                              int var156 = this.Me - (var145 + 1);
                              this.Me -= var156;
                              this.q(var8, var141, var151);
                              this.Me += var156 - 1;
                           }
                        }
                     }
                  }

                  IESwitch var124 = this.ctx.createSwitch(var94, -1);
                  var8.r.add(var124);
                  break;
               case 16:
                  int var93 = var10.za();
                  Nt var109 = this.zz.RF(var93);
                  INativeMethodItem var122 = this.getNativeContext().getRoutine(var109.xK);
                  if (var122 == null) {
                     var122 = this.getNativeContext().getRoutineByName(var109.zz());
                     if (var122 == null) {
                        throw new UnsupportedConversionException("Cannot resolve routine: " + var109.JY());
                     }
                  }

                  IPrototypeItem var135 = var122.getPrototype();
                  if (var135 == null) {
                     this.q(var122, false);
                  }

                  ArrayList var140 = new ArrayList();
                  ArrayList var144 = new ArrayList();

                  for (int var165 : var109.q().RF()) {
                     INativeType var168 = this.RF(var165);
                     var140.add(var168);
                     var144.add(this.xK());
                  }

                  Integer var150 = var109.q().q();
                  ArrayList var155 = new ArrayList();
                  if (var150 != null) {
                     var155.add(this.oW(var150));
                  }

                  IEVar var160 = this.ctx.createSymbolForRoutine(var122);
                  IECall var166 = this.ctx.createCall(var160, null, var155, var144, 0, null, null);
                  var8.r.add(var166);
                  break;
               case 17:
                  int var92 = var10.za();
                  iZ var108 = this.zz.oW(var92);
                  IPrototypeItem var121 = this.q(var108);
                  IWildcardPrototype var134 = this.gCtx.getWildcardTypeManager().createPrototype(var121);
                  IEVar var23 = this.xK();
                  ArrayList var24 = new ArrayList();
                  ArrayList var25 = new ArrayList();

                  for (int var29 : var108.RF()) {
                     INativeType var30 = this.RF(var29);
                     var24.add(var30);
                     var25.add(this.xK());
                  }

                  Integer var153 = var108.q();
                  ArrayList var158 = new ArrayList();
                  if (var153 != null) {
                     var158.add(this.oW(var153));
                  }

                  long var164 = this.zz.nf();
                  INativeContinuousItem var169 = this.JY.getNativeItemAt(var164);
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
                  this.xK();
                  break;
               case 27:
                  IEVar var91 = this.xK();
                  IEVar var107 = this.xK();
                  IEVar var120 = this.xK();
                  this.q(var8, 0, this.ctx.createCond(var91, var120, var107));
                  break;
               case 32:
                  int var53 = this.q(var10);
                  IEVar var62 = this.Dw(var53);
                  this.q(var8, 0, var62);
                  break;
               case 33:
                  int var52 = this.q(var10);
                  IEVar var61 = this.xK();
                  this.xK(var8, var52, var61);
                  break;
               case 34:
                  int var51 = this.q(var10);
                  IEVar var60 = this.Dw();
                  this.xK(var8, var51, var60);
                  break;
               case 35:
                  int var50 = this.q(var10);
                  var13 = this.zz.xK(var50).q.q;
                  IEVar var59 = this.xK(var50);
                  this.q(var8, var13, var59);
                  break;
               case 36:
                  int var49 = this.q(var10);
                  IEVar var58 = this.xK();
                  this.RF(var8, var49, var58);
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

                  Object var106 = this.xK();
                  int var119 = ((Long)var10.nf()[0].Dw()).intValue();
                  if (var119 != 0) {
                     var106 = this.ctx.createOperation(OperationType.ADD, (IEGeneric)var106, this.ctx.createImm(var119, ((IEGeneric)var106).getBitsize()));
                  }

                  Object var133 = this.ctx.createMem((IEGeneric)var106, var90);
                  if (var14 == 44 || var14 == 46) {
                     var133 = ((IEGeneric)var133).signExtend(32);
                  } else if (var14 == 45 || var14 == 47) {
                     var133 = ((IEGeneric)var133).zeroExtend(32);
                  }

                  this.q(var8, 127, (IEGeneric)var133);
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

                  Object var105 = this.xK();
                  int var118 = ((Long)var10.nf()[0].Dw()).intValue();
                  if (var118 != 0) {
                     var105 = this.ctx.createOperation(OperationType.ADD, (IEGeneric)var105, this.ctx.createImm(var118, ((IEGeneric)var105).getBitsize()));
                  }

                  Object var132 = this.ctx.createMem((IEGeneric)var105, var89);
                  if (var14 == 48 || var14 == 50 || var14 == 52) {
                     var132 = ((IEGeneric)var132).signExtend(64);
                  } else if (var14 == 49 || var14 == 51 || var14 == 53) {
                     var132 = ((IEGeneric)var132).zeroExtend(64);
                  }

                  this.q(var8, 126, (IEGeneric)var132);
                  break;
               case 42:
               case 43:
                  int var88 = var14 == 42 ? 32 : 64;
                  Object var104 = this.xK();
                  int var117 = ((Long)var10.nf()[0].Dw()).intValue();
                  if (var117 != 0) {
                     var104 = this.ctx.createOperation(OperationType.ADD, (IEGeneric)var104, this.ctx.createImm(var117, ((IEGeneric)var104).getBitsize()));
                  }

                  IEMem var131 = this.ctx.createMem((IEGeneric)var104, var88);
                  this.q(var8, Xa.gP(var88), var131);
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

                  IEVar var103 = this.xK();
                  Object var116 = this.xK();
                  int var130 = ((Long)var10.nf()[0].Dw()).intValue();
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

                  IEVar var102 = this.xK();
                  Object var115 = this.xK();
                  int var129 = ((Long)var10.nf()[0].Dw()).intValue();
                  if (var129 != 0) {
                     var115 = this.ctx.createOperation(OperationType.ADD, (IEGeneric)var115, this.ctx.createImm(var129, ((IEGeneric)var115).getBitsize()));
                  }

                  var8.r.add(this.ctx.createAssign(this.ctx.createMem((IEGeneric)var115, var86), var102.part(var86)));
                  break;
               case 56:
               case 57:
                  int var85 = var14 == 56 ? 32 : 64;
                  IEVar var101 = this.xK();
                  Object var21 = this.xK();
                  int var22 = ((Long)var10.nf()[0].Dw()).intValue();
                  if (var22 != 0) {
                     var21 = this.ctx.createOperation(OperationType.ADD, (IEGeneric)var21, this.ctx.createImm(var22, ((IEGeneric)var21).getBitsize()));
                  }

                  var8.r.add(this.ctx.createAssign(this.ctx.createMem((IEGeneric)var21, var85), var101.part(var85)));
                  break;
               case 63:
                  this.q(var8, 127, this.ctx.createImm(100L, 32));
                  break;
               case 64:
                  this.gO(1);
                  this.q(var8, 127, this.ctx.createImm(100L, 32));
                  break;
               case 65:
                  long var48 = this.RF(var10);
                  this.q(var8, 127, this.ctx.createImm(var48, 32));
                  break;
               case 66:
                  long var47 = this.xK(var10);
                  this.q(var8, 126, this.ctx.createImm(var47, 64));
                  break;
               case 67:
                  float var84 = this.Dw(var10);
                  this.q(var8, 125, this.ctx.createImm(var84));
                  break;
               case 68:
                  double var83 = this.Uv(var10);
                  this.q(var8, 124, this.ctx.createImm(var83));
                  break;
               case 69:
               case 80:
                  var13 = 127;
                  IEVar var46 = this.xK();
                  IEImm var57 = EUtil.zero(var46.getBitsize());
                  IEOperation var67 = this.ctx.createOperation(OperationType.LOG_EQ, var46, var57);
                  IEGeneric var68 = var67.zeroExtend(32);
                  this.q(var8, var13, var68);
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

                  OperationType var45 = this.nf(var14);
                  if (var45 == null) {
                     throw new RuntimeException("TBI: Wasm operator not converted");
                  }

                  IEVar var56 = this.xK();
                  IEVar var66 = this.xK();
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

                  this.q(var8, var13, var73);
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
                  OperationType var44 = this.nf(var14);
                  if (var44 == null) {
                     throw new RuntimeException("TBI: Wasm operator not converted");
                  }

                  IEVar var55 = this.xK();
                  IEVar var65 = this.xK();
                  IEOperation var70 = this.ctx.createOperation(var44, var65, var55);
                  IEGeneric var71 = var70.zeroExtend(32);
                  this.q(var8, 127, var71);
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

                  int var43 = var10.RF.xK();
                  IEGeneric[] var54 = new IEGeneric[var43];

                  for (int var63 = 0; var63 < var43; var63++) {
                     var54[var43 - 1 - var63] = this.xK();
                  }

                  IEVar var64 = this.oW(var13);
                  IEUntranslatedInstruction var69 = q(this.ctx, var5, var10, var54);
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

                  OperationType var15 = this.nf(var14);
                  if (var15 == null) {
                     throw new RuntimeException("TBI: Wasm operator not converted");
                  }

                  IEVar var16 = this.xK();
                  IEVar var17 = this.xK();
                  IEOperation var18 = this.ctx.createOperation(var15, var17, var16);
                  this.q(var8, var13, var18);
                  break;
               case 167:
                  IEVar var82 = this.xK();
                  this.q(var8, 127, this.ctx.createConversionOperation(OperationType.CAST, var82, 32));
                  break;
               case 168:
               case 169:
               case 170:
               case 171:
                  IEVar var81 = this.xK();
                  this.q(var8, 127, this.ctx.createConversionOperation(OperationType.FP2INT, var81, 32));
                  break;
               case 172:
               case 173:
                  IEVar var80 = this.xK();
                  this.q(var8, 126, this.ctx.createResizeOperation(var80, 64, var14 == 172));
                  break;
               case 174:
               case 175:
               case 176:
               case 177:
                  IEVar var79 = this.xK();
                  this.q(var8, 126, this.ctx.createConversionOperation(OperationType.FP2INT, var79, 64));
                  break;
               case 178:
               case 179:
               case 180:
               case 181:
                  IEVar var78 = this.xK();
                  this.q(var8, 125, this.ctx.createConversionOperation(OperationType.INT2FP, var78, 32));
                  break;
               case 182:
                  IEVar var77 = this.xK();
                  this.q(var8, 125, this.ctx.createConversionOperation(OperationType.FP2FP, var77, 32));
                  break;
               case 183:
               case 184:
               case 185:
               case 186:
                  IEVar var76 = this.xK();
                  this.q(var8, 124, this.ctx.createConversionOperation(OperationType.INT2FP, var76, 64));
                  break;
               case 187:
                  IEVar var75 = this.xK();
                  this.q(var8, 124, this.ctx.createConversionOperation(OperationType.FP2FP, var75, 64));
                  break;
               case 192:
               case 193:
                  IEVar var74 = this.xK();
                  int var100 = var14 == 192 ? 8 : 16;
                  this.q(var8, 127, this.ctx.createResizeOperation(var74.part(var100), 32, true));
                  break;
               case 194:
               case 195:
               case 196:
                  IEVar var19 = this.xK();
                  int var20 = var14 == 194 ? 8 : (var14 == 195 ? 16 : 32);
                  this.q(var8, 126, this.ctx.createResizeOperation(var19.part(var20), 64, true));
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
         qa.error("Error: Instruction cannot be converted: %Xh: %s: %s", var5, Formatter.byteArrayToHex(var10.getCode()), var10.format(var5));
         qa.catchingSilent(var38);
         throw var38;
      } finally {
         ;
      }
   }

   @Override
   public String formatStatistics() {
      StringBuilder var1 = new StringBuilder(super.formatStatistics());
      var1.append("\n\n");
      Strings.ff(var1, "Converted instructions: %d (incl. failed=%d, untranslated=%d)\n", this.RF, this.xK, this.Dw);
      if (this.q != null && !this.q.isEmpty()) {
         var1.append("Failed instructions: ").append(this.q.formatTopReferences(-1));
      }

      return var1.toString();
   }

   IEVar xK(int var1) {
      tw var3 = this.zz.xK(var1);
      INativeContinuousItem var4 = this.JY.getNativeItemAt(var3.xK);
      if (var4 instanceof INativeContinuousItem) {
         IEVar var2 = this.gCtx.createGlobalVariable(var4.getMemoryAddress(), (int)var4.getMemorySize() * 8);
         ((amy)var2).q(var4.getName(true));
         return var2;
      } else {
         throw new RuntimeException("Cannot retrieve native data item for global: " + var3);
      }
   }

   IEVar Dw(int var1) {
      return (IEVar)this.io.get(var1);
   }

   void q(ConverterInstructionEntry var1, int var2, IEGeneric var3) {
      IEVar var4;
      if (var2 == 0) {
         var4 = this.Uv(var3.getBitsize());
      } else {
         var4 = this.oW(var2);
      }

      var1.r.add(this.ctx.createAssign(var4, var3));
   }

   IEVar Uv(int var1) {
      IEVar var2;
      if (this.Me < this.Hk.size()) {
         var2 = this.q("var" + this.PV, var1);
         this.Hk.set(this.Me, new KZ.eo(var2, 0));
         this.PV++;
      } else {
         var2 = this.q("var" + this.PV, var1);
         this.Hk.add(new KZ.eo(var2, 0));
         this.PV++;
      }

      this.Me++;
      return var2;
   }

   IEVar oW(int var1) {
      Assert.a(var1 != 0);
      IEVar var2;
      if (this.Me < this.Hk.size()) {
         KZ.eo var3 = (KZ.eo)this.Hk.get(this.Me);
         if (var3.RF == var1) {
            var2 = var3.q;
         } else {
            int var4 = Xa.gO(var1) * 8;
            var2 = this.q("var" + this.PV, var4);
            this.Hk.set(this.Me, new KZ.eo(var2, var1));
            this.PV++;
         }
      } else {
         int var5 = Xa.gO(var1) * 8;
         var2 = this.q("var" + this.PV, var5);
         this.Hk.add(new KZ.eo(var2, var1));
         this.PV++;
      }

      this.Me++;
      return var2;
   }

   boolean RF() {
      return this.Me > 0;
   }

   IEVar xK() {
      Assert.a(this.Me > 0);
      this.Me--;
      return ((KZ.eo)this.Hk.get(this.Me)).q;
   }

   IEVar Dw() {
      Assert.a(this.Me > 0);
      return ((KZ.eo)this.Hk.get(this.Me - 1)).q;
   }

   void gO(int var1) {
      while (var1-- > 0) {
         this.xK();
      }
   }

   void RF(ConverterInstructionEntry var1, int var2, IEGeneric var3) {
      IEVar var4 = this.xK(var2);
      var1.r.add(this.ctx.createAssign(var4, var3.zeroExtend(var4.getBitsize())));
   }

   void xK(ConverterInstructionEntry var1, int var2, IEGeneric var3) {
      IEVar var4 = this.Dw(var2);
      var1.r.add(this.ctx.createAssign(var4, var3.zeroExtend(var4.getBitsize())));
   }

   int q(SG var1) {
      long var2 = (Long)var1.nf()[0].Dw();
      if (var2 >= 2147483648L) {
         throw new RuntimeException();
      } else {
         return (int)var2;
      }
   }

   long RF(SG var1) {
      return (Long)var1.nf()[0].Dw() & 4294967295L;
   }

   long xK(SG var1) {
      return (Long)var1.nf()[0].Dw();
   }

   float Dw(SG var1) {
      return Float.intBitsToFloat(((Long)var1.nf()[0].Dw()).intValue());
   }

   double Uv(SG var1) {
      return Double.longBitsToDouble((Long)var1.nf()[0].Dw());
   }

   OperationType nf(int var1) {
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

   public void Uv() {
      for (INativeMethodItem var2 : this.getNativeContext().getRoutines()) {
         this.q(var2, false);
      }
   }

   public IPrototypeItem q(INativeMethodItem var1, boolean var2) {
      IPrototypeItem var3 = var1.getPrototype();
      if (var3 == null || var2) {
         Nt var4 = this.zz.Dw(var1.getName());
         var3 = this.q(var4.q());
         var1.setPrototype(var3);
      }

      return var3;
   }

   IPrototypeItem q(iZ var1) {
      ICallingConvention var2 = this.JY.getTypeManager().getCallingConventionManager().getDefaultConvention();
      ArrayList var3 = new ArrayList();

      for (int var7 : var1.RF()) {
         INativeType var8 = this.RF(var7);
         var3.add(var8);
      }

      Integer var9 = var1.q();
      INativeType var10 = null;
      if (var9 != null) {
         var10 = this.RF(var9);
      }

      return this.JY.getTypeManager().createPrototype(var2, var10, var3, null);
   }

   @Override
   public IEPrototypeHandler getPrototypeHandler(IERoutineContext var1) {
      return new GA(var1);
   }

   private Nt q(INativeMethodItem var1) {
      return this.zz.Dw(var1.getName());
   }

   private Nt Dw(IERoutineContext var1) {
      return this.q(var1.getRoutine());
   }

   public static IEUntranslatedInstruction q(IERoutineContext var0, long var1, SG var3, IEGeneric... var4) {
      IEUntranslatedInstruction var5 = var0.createUntranslatedInstruction(var1, var3.getMnemonic(), var4);
      var5.setTag(var3.q());
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

   static class eo {
      IEVar q;
      int RF;

      eo(IEVar var1, int var2) {
         this.q = var1;
         this.RF = var2;
      }

      @Override
      public String toString() {
         return this.q == null ? "null" : this.q.toString();
      }
   }
}
