package com.pnfsoftware.jeb.corei.parsers.ethereum;

import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.FlowInformation;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.AbstractConverter;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ConverterInstructionEntry;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CIdentifierClass;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COperatorType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICCall;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICCustomStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElementFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIdentifier;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLeftExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICTypeFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.UnsupportedConversionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECall;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEPrototypeHandler;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEUntranslatedInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardTypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.ICallingConvention;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrimitiveType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrototypeItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeUtil;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.collect.ReferenceCounter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInit;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import com.pnfsoftware.jebglobal.C;
import com.pnfsoftware.jebglobal.ayr;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Ser
public class oP extends AbstractConverter {
   private static final ILogger mv = GlobalLog.getLogger(oP.class);
   @SerId(1)
   ReferenceCounter pC;
   @SerId(2)
   int A;
   @SerId(3)
   int kS;
   @SerId(4)
   int wS;
   @SerId(11)
   yt UT;
   @SerId(12)
   C E;
   @SerId(13)
   int sY;
   public static BigInteger ys = BigInteger.ZERO;
   public static BigInteger ld = BigInteger.ONE;
   public static BigInteger gp = BigInteger.valueOf(2L);
   public static BigInteger oT = BigInteger.valueOf(3L);
   public static BigInteger fI = BigInteger.valueOf(4L);
   public static BigInteger WR = BigInteger.valueOf(5L);
   public static BigInteger NS = BigInteger.valueOf(6L);
   public static BigInteger vP = BigInteger.valueOf(7L);
   public static BigInteger xC = BigInteger.valueOf(8L);
   public static BigInteger ED = BigInteger.valueOf(9L);
   public static BigInteger Sc = BigInteger.ONE.shiftLeft(160).subtract(BigInteger.ONE);
   @SerId(20)
   IEVar ah;
   @SerId(21)
   IEVar eP;
   @SerId(22)
   IEVar UO;
   @SerId(23)
   IEVar Ab;
   @SerId(24)
   IEVar rl;
   @SerId(25)
   IEVar z;
   @SerId(26)
   IEVar Ek;
   @SerId(27)
   IEImm hK;
   @SerId(28)
   IEImm Er;
   @SerId(29)
   IEImm FE;
   @SerId(30)
   IEImm Aj;
   @SerId(31)
   IEImm EX;
   @SerId(32)
   IEVar[] LM;
   @SerTransient
   private List sO;
   @SerTransient
   private int os;
   @SerTransient
   private int Cu;
   @SerTransient
   private Map hZ;

   @SerCustomInit
   private void UT() {
      if (this.LM == null) {
         this.LM = new IEVar[16];

         for (int var1 = 0; var1 < 16; var1++) {
            this.LM[var1] = this.gCtx.createVirtualRegister("r" + var1, this.regNormalBitsize);
         }
      }
   }

   public oP(yt var1, INativeCodeUnit var2) {
      super(var1.pC(), 256);
      this.UT = var1;
      if (var2 == null) {
         throw new IllegalArgumentException();
      } else {
         this.E = (C)var2;
         this.ah = this.gCtx.createRegister(0, "pc", this.regNormalBitsize);
         this.eP = this.gCtx.createRegister(256, "sp", this.regNormalBitsize);
         this.UO = this.gCtx.createVirtualRegister("tmp", this.regNormalBitsize);
         this.Ab = this.gCtx.createVirtualRegister("m0", this.regNormalBitsize);
         this.rl = this.gCtx.createVirtualRegister("m1", this.regNormalBitsize);
         this.UT();
         this.hK = this.gCtx.createImm(0L, 256);
         this.Er = this.gCtx.createImm(1L, 256);
         this.FE = this.gCtx.createImm(255L, 256);
         this.Aj = this.gCtx.createImm(256L, 256);
         this.EX = this.gCtx.createImm("0xFFFFFFFF_FFFFFFFF_FFFFFFFF_FFFFFFFF_FFFFFFFF_FFFFFFFF_FFFFFFFF_FFFFFFE0", 256);
         this.Ek = this.gCtx.createVirtualRegister("msg.value", 256);
         this.Ek.setFlags(16);
         this.z = this.gCtx.createVirtualRegister("msg.sig", 32);
         this.z.setFlags(16);
         this.pC = new ReferenceCounter();
      }
   }

   public yt pC() {
      return this.UT;
   }

   @Override
   public void initialize() {
   }

   @Override
   public IEVar getProgramCounter() {
      return this.ah;
   }

   @Override
   public IEVar getStackPointer() {
      return this.eP;
   }

   @Override
   public IWildcardType.Group getWildcardTypeManagerDefaultResolutionGroup() {
      return IWildcardType.Group.UINT;
   }

   @Override
   public Boolean isSegmentEMemReferencingPrimaryMemory(IEMem var1) {
      return false;
   }

   private IEVar pC(String var1, int var2) {
      return this.ctx.createVirtualVar(var1, var2);
   }

   private IEVar pC(String var1) {
      IEVar var2 = this.pC(var1 != null ? var1 : "var" + this.sO.size(), this.regNormalBitsize);
      this.sO.add(var2);
      return var2;
   }

   @Override
   protected void preRoutineConversion(INativeMethodItem var1, IERoutineContext var2, List var3) {
      super.preRoutineConversion(var1, var2, var3);
      this.wS();
      CFG var4 = var1.getData().getCFG();
      int var5 = 1;
      eW var6 = this.UT.kS(var1.getMemoryAddress());
      if (var6 instanceof nA) {
         var5 += ((nA)var6).kS();
      }

      Sb var7 = new Sb(this.UT, var4);
      boolean var8 = var7.pC(var5);
      if (!var8) {
         Object[] var10000 = new Object[]{var1};
      } else {
         this.sO = new ArrayList();
         this.hZ = var7.pC;
         if (var6 instanceof nA) {
            this.pC("par0");

            for (int var9 = 0; var9 < ((nA)var6).kS(); var9++) {
               this.pC("par" + (var9 + 1));
            }

            this.os = this.sO.size();

            for (int var10 = 0; var10 < var7.pC(); var10++) {
               this.pC("var" + var10);
            }
         } else if (var6 instanceof m) {
            this.pC("par0");
            this.os = this.sO.size();

            for (int var11 = 0; var11 < var7.pC(); var11++) {
               this.pC("var" + var11);
            }
         }
      }
   }

   @Override
   protected void postRoutineConversion(INativeMethodItem var1, IERoutineContext var2) {
      this.sO = null;
      this.os = 0;
      this.Cu = 0;
      this.hZ = null;
   }

   @Override
   protected void convertBlock(BasicBlock var1, List var2) {
      long var3 = var1.getFirstAddress();
      long var5 = var3;
      ArrayList var7 = new ArrayList();
      ConverterInstructionEntry var8 = new ConverterInstructionEntry();
      var8.r = var7;

      try {
         if (this.sO != null) {
            Integer var10 = (Integer)this.hZ.get(var3);
            if (var10 == null) {
               var10 = 0;
            }

            this.Cu = this.os + var10;
         }

         for (int var29 = 0; var29 < var1.size(); var29++) {
            this.A++;
            Mh var9 = (Mh)var1.get(var29);
            var7.clear();
            int var11 = var2.size();
            var8.insn = var9;
            var8.address = var5;
            var8.irAddress = var11;
            Integer var12 = this.UT.pC(var5);
            if (var12 == null) {
               throw new RuntimeException();
            }

            int var13 = var9.pC();
            switch (var13) {
               case 0:
               case 8:
               case 9:
               case 11:
               case 32:
               case 48:
               case 49:
               case 50:
               case 51:
               case 53:
               case 54:
               case 55:
               case 56:
               case 57:
               case 58:
               case 59:
               case 60:
               case 61:
               case 62:
               case 63:
               case 64:
               case 65:
               case 66:
               case 67:
               case 68:
               case 69:
               case 70:
               case 71:
               case 72:
               case 84:
               case 85:
               case 89:
               case 90:
               case 160:
               case 161:
               case 162:
               case 163:
               case 164:
               case 240:
               case 241:
               case 242:
               case 243:
               case 244:
               case 245:
               case 250:
               case 253:
               case 254:
               case 255:
                  int var41 = var9.pC.wS;
                  Assert.a(var41 <= 16);
                  int var56 = var9.pC.UT;
                  Assert.a(var56 <= 1);
                  IEGeneric[] var62 = new IEGeneric[var41];

                  for (int var67 = 0; var67 < var41; var67++) {
                     var62[var67] = this.pC(var8, var67);
                  }

                  IEVar var68 = null;
                  if (this.sO == null) {
                     if (var56 == 1) {
                        var68 = this.UO;
                     }

                     IEUntranslatedInstruction var78 = pC(this.ctx, var5, var9, var62);
                     var78.setReturnExpression(var68);
                     if (var9.sY()) {
                        var78.setBreakingFlow(new FlowInformation());
                     }

                     var8.r.add(var78);
                     if (var56 == 1) {
                        this.pC(var8, this.UO);
                     }
                  } else {
                     if (var56 == 1) {
                        var68 = this.A();
                     }

                     IEUntranslatedInstruction var79 = pC(this.ctx, var5, var9, var62);
                     var79.setReturnExpression(var68);
                     if (var9.sY()) {
                        var79.setBreakingFlow(new FlowInformation());
                     }

                     var8.r.add(var79);
                  }
                  break;
               case 1:
               case 2:
               case 3:
               case 4:
               case 5:
               case 6:
               case 7:
               case 10:
               case 16:
               case 17:
               case 18:
               case 19:
               case 20:
               case 22:
               case 23:
               case 24:
                  OperationType var40 = this.pC(var13);
                  IEVar var55 = this.pC(var8, 0);
                  IEVar var61 = this.pC(var8, 1);
                  IEOperation var66 = this.ctx.createOperation(var40, var55, var61);
                  this.pC(var8, var66);
                  break;
               case 12:
               case 13:
               case 14:
               case 15:
               case 30:
               case 31:
               case 33:
               case 34:
               case 35:
               case 36:
               case 37:
               case 38:
               case 39:
               case 40:
               case 41:
               case 42:
               case 43:
               case 44:
               case 45:
               case 46:
               case 47:
               case 73:
               case 74:
               case 75:
               case 76:
               case 77:
               case 78:
               case 79:
               case 92:
               case 93:
               case 94:
               case 95:
               case 165:
               case 166:
               case 167:
               case 168:
               case 169:
               case 170:
               case 171:
               case 172:
               case 173:
               case 174:
               case 175:
               case 176:
               case 177:
               case 178:
               case 179:
               case 180:
               case 181:
               case 182:
               case 183:
               case 184:
               case 185:
               case 186:
               case 187:
               case 188:
               case 189:
               case 190:
               case 191:
               case 192:
               case 193:
               case 194:
               case 195:
               case 196:
               case 197:
               case 198:
               case 199:
               case 200:
               case 201:
               case 202:
               case 203:
               case 204:
               case 205:
               case 206:
               case 207:
               case 208:
               case 209:
               case 210:
               case 211:
               case 212:
               case 213:
               case 214:
               case 215:
               case 216:
               case 217:
               case 218:
               case 219:
               case 220:
               case 221:
               case 222:
               case 223:
               case 224:
               case 225:
               case 226:
               case 227:
               case 228:
               case 229:
               case 230:
               case 231:
               case 232:
               case 233:
               case 234:
               case 235:
               case 236:
               case 237:
               case 238:
               case 239:
               case 246:
               case 247:
               case 248:
               case 249:
               case 251:
               case 252:
               default:
                  this.kS++;
                  if (this.pC != null) {
                     this.pC.inc(var9.getMnemonic());
                  }

                  throw new UnsupportedConversionException("Cannot convert instruction: " + var9);
               case 21:
                  IEVar var39 = this.pC(var8, 0);
                  IEOperation var54 = this.ctx.createOperation(OperationType.LOG_EQ, var39, this.gCtx.createImm(0L, 256));
                  this.pC(var8, var54);
                  break;
               case 25:
                  IEVar var38 = this.pC(var8, 0);
                  IEOperation var53 = this.ctx.createOperation(OperationType.NOT, var38);
                  this.pC(var8, var53);
                  break;
               case 26:
                  IEVar var37 = this.pC(var8, 0);
                  IEVar var52 = this.pC(var8, 1);
                  IEOperation var60 = this.ctx.createOperation(OperationType.AND, var37, this.EX);
                  IEImm var65 = this.hK;
                  IEGeneric var74 = this.ctx.createOperation(OperationType.ADD, var37, this.Er).leftShift(3);
                  IEOperation var75 = this.ctx.createOperation(OperationType.SUB, this.Aj, var74);
                  IEOperation var76 = this.ctx.createOperation(OperationType.SHR, var52, var75);
                  IEOperation var77 = this.ctx.createOperation(OperationType.AND, var76, this.FE);
                  this.pC(var8, this.ctx.createCond(var60, var65, var77));
                  break;
               case 27:
               case 28:
               case 29:
                  OperationType var36 = this.pC(var13);
                  IEVar var51 = this.pC(var8, 0);
                  IEVar var59 = this.pC(var8, 1);
                  IEOperation var64 = this.ctx.createOperation(var36, var59, var51);
                  this.pC(var8, var64);
                  break;
               case 52:
                  this.pC(var8, this.Ek);
                  break;
               case 80:
                  this.pC(var8, 0);
                  break;
               case 81:
                  IEVar var35 = this.pC(var8, 0);
                  IEMem var50 = this.ctx.createMem(var35, 256);
                  this.pC(var8, var50);
                  break;
               case 82:
                  IEVar var34 = this.pC(var8, 0);
                  IEVar var49 = this.pC(var8, 1);
                  var8.r.add(this.ctx.createAssign(this.ctx.createMem(var34, 256), var49));
                  break;
               case 83:
                  IEVar var33 = this.pC(var8, 0);
                  IEVar var48 = this.pC(var8, 1);
                  var8.r.add(this.ctx.createAssign(this.ctx.createMem(var33, 8), var48.part(8)));
                  break;
               case 86:
                  IEVar var32 = this.pC(var8, 0);
                  if (this.sO == null) {
                     Object var43 = var32;
                     if (var9.sY != null) {
                        var43 = this.gCtx.createImm(var9.sY.intValue(), this.regNormalBitsize);
                     }

                     var8.r.add(this.ctx.createBranchAssign(this.ah, (IEGeneric)var43, (var9.E & 1) != 0));
                  } else if ((var9.E & 2) != 0) {
                     IEVar var44 = null;
                     if (!TypeUtil.isVoid(this.ctx.getRoutine().getPrototype().getReturnType())) {
                        var44 = this.pC(var8, 0);
                     }

                     var8.r.add(this.ctx.createReturn(var44));
                  } else if ((var9.E & 1) != 0) {
                     eW var45 = this.UT.kS(var9.sY.intValue());
                     INativeMethodItem var58 = this.getNativeContext().getRoutine(var9.sY.intValue());
                     IEVar var63 = this.ctx.createSymbolForRoutine(var58);
                     Object var72 = null;
                     ArrayList var81 = new ArrayList();
                     ArrayList var82 = new ArrayList();
                     if (var45 instanceof m) {
                        IECall var83 = this.ctx.createCall(var63, (IEGeneric)var72, var82, var81, 0, null, null);
                        var8.r.add(var83);
                     } else {
                        if (!(var45 instanceof nA var84)) {
                           throw new RuntimeException();
                        }

                        for (int var85 = 0; var85 < var84.kS(); var85++) {
                           var81.add(this.kS());
                        }

                        var72 = this.kS();

                        for (int var86 = 0; var86 < var84.wS(); var86++) {
                           var82.add(this.A());
                        }

                        IECall var87 = this.ctx.createCall(var63, (IEGeneric)var72, var82, var81, 0, null, null);
                        var8.r.add(var87);
                     }
                  } else if ((var9.E & 4) != 0) {
                     IEUntranslatedInstruction var46 = pC(this.ctx, var5, Integer.MIN_VALUE);
                     var46.setBreakingFlow(new FlowInformation());
                     var8.r.add(var46);
                  } else {
                     Object var47;
                     if (var9.sY != null) {
                        var47 = this.gCtx.createImm(var9.sY.intValue(), this.regNormalBitsize);
                     } else {
                        var47 = var32.part(this.regNormalBitsize);
                     }

                     var8.r.add(this.ctx.createBranchAssign(this.ah, (IEGeneric)var47, false));
                  }
                  break;
               case 87:
                  IEVar var31 = this.pC(var8, 0);
                  IEVar var42 = this.pC(var8, 1);
                  int var57 = var12 + 1;
                  IEImm var17 = this.gCtx.createImm(var57, this.regNormalBitsize);
                  if (this.sO == null) {
                     Object var18 = var31;
                     if (var9.sY != null) {
                        var18 = this.gCtx.createImm(var9.sY.intValue(), this.regNormalBitsize);
                     }

                     var8.r.add(this.ctx.createBranchAssign(this.ah, this.gCtx.createCond(var42, (IEGeneric)var18, var17), (var9.E & 1) != 0));
                  } else {
                     if ((var9.E & 2) != 0) {
                        throw new RuntimeException("Illegal flag on JUMPI");
                     }

                     if ((var9.E & 1) != 0) {
                        eW var69 = this.UT.kS(var9.sY.intValue());
                        var8.r.add(this.ctx.createJump(var8.irAddress + 2, EUtil.notL(var42)));
                        INativeMethodItem var19 = this.getNativeContext().getRoutine(var9.sY.intValue());
                        IEVar var20 = this.ctx.createSymbolForRoutine(var19);
                        ArrayList var21 = new ArrayList();
                        ArrayList var22 = new ArrayList();
                        if (!(var69 instanceof m)) {
                           throw new RuntimeException("TBI: JUMPI to INTERNAL_ROUTINE");
                        }

                        IECall var23 = this.ctx.createCall(var20, null, var22, var21, 0, null, null);
                        var8.r.add(var23);
                     } else if ((var9.E & 4) != 0) {
                        var8.r.add(this.ctx.createJump(var8.irAddress + 2, EUtil.notL(var42)));
                        IEUntranslatedInstruction var70 = pC(this.ctx, var5, Integer.MIN_VALUE);
                        var70.setBreakingFlow(new FlowInformation());
                        var8.r.add(var70);
                     } else {
                        Object var71;
                        if (var9.sY != null) {
                           var71 = this.gCtx.createImm(var9.sY.intValue(), this.regNormalBitsize);
                        } else {
                           var71 = var31.part(this.regNormalBitsize);
                        }

                        IECond var80 = this.ctx.createCond(var42, (IEGeneric)var71, var17);
                        var8.r.add(this.ctx.createBranchAssign(this.ah, var80, false));
                     }
                  }
                  break;
               case 88:
                  this.pC(var8, this.gCtx.createImm(var12.intValue(), this.regNormalBitsize));
                  break;
               case 91:
                  var8.r.add(this.ctx.createNop());
                  break;
               case 96:
               case 97:
               case 98:
               case 99:
               case 100:
               case 101:
               case 102:
               case 103:
               case 104:
               case 105:
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
               case 121:
               case 122:
               case 123:
               case 124:
               case 125:
               case 126:
               case 127:
                  this.pC(var8, this.gCtx.createImm(var9.wS, 256));
                  break;
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
               case 139:
               case 140:
               case 141:
               case 142:
               case 143:
                  IEVar var30 = this.pC(var9.kS(), var8, 0);
                  this.pC(var8, var30);
                  break;
               case 144:
               case 145:
               case 146:
               case 147:
               case 148:
               case 149:
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
                  int var14 = 1 + var9.A();
                  IEVar var15 = this.pC(1, var8, 0);
                  IEVar var16 = this.pC(var14, var8, 1);
                  if (this.sO == null) {
                     this.pC(1, var8, var16);
                     this.pC(var14, var8, var15);
                  } else {
                     var8.r.add(this.ctx.createAssign(this.UO, var15));
                     var8.r.add(this.ctx.createAssign(var15, var16));
                     var8.r.add(this.ctx.createAssign(var16, this.UO));
                  }
            }

            if (var8.r.isEmpty()) {
               var8.r.add(this.ctx.createNop());
            }

            EUtil.setLowerLevelAddress(var12.intValue(), var7);
            var2.addAll(var7);
            var5 += var9.getSize();
         }
      } catch (Throwable var27) {
         mv.catchingSilent(var27);
         throw var27;
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

   void pC(ConverterInstructionEntry var1, IEGeneric var2) {
      if (this.sO == null) {
         IEAssign var5 = this.ctx.createAssign(this.eP, EUtil.sub(this.eP, EUtil.imm(32L, 256)));
         IEAssign var4 = this.ctx.createAssign(this.createStackMemoryAccess(this.eP, 256), var2.zeroExtend(256));
         var1.r.add(var5);
         var1.r.add(var4);
      } else {
         IEVar var3 = this.A();
         var1.r.add(this.ctx.createAssign(var3, var2.zeroExtend(var3.getBitsize())));
      }
   }

   IEVar A() {
      if (this.sO == null) {
         throw new IllegalStateException();
      } else {
         if (this.Cu < 0 || this.Cu >= this.sO.size()) {
            Object[] var10000 = new Object[0];
         }

         IEVar var1 = (IEVar)this.sO.get(this.Cu);
         this.Cu++;
         return var1;
      }
   }

   IEVar kS() {
      return this.pC(null, -1);
   }

   IEVar pC(ConverterInstructionEntry var1, int var2) {
      if (this.sO == null) {
         IEVar var3 = var2 < 0 ? this.UO : this.LM[var2];
         IEAssign var4 = this.ctx.createAssign(var3, this.createStackMemoryAccess(this.eP, 256));
         var1.r.add(var4);
         IEAssign var5 = this.ctx.createAssign(this.eP, EUtil.add(this.eP, EUtil.imm(32L, 256)));
         var1.r.add(var5);
         return var3;
      } else {
         Assert.a(this.Cu > 0, "Empty evm operand stack");
         this.Cu--;
         if (this.Cu < 0 || this.Cu >= this.sO.size()) {
            Object[] var10000 = new Object[0];
         }

         return (IEVar)this.sO.get(this.Cu);
      }
   }

   IEVar pC(int var1, ConverterInstructionEntry var2, int var3) {
      Assert.a(var1 >= 1, "Illegal evm operand stack position");
      if (this.sO == null) {
         IEVar var7 = var3 < 0 ? this.UO : this.LM[var3];
         Object var5 = this.eP;
         if (var1 >= 2) {
            var5 = EUtil.add((IEGeneric)var5, EUtil.mul(EUtil.imm(var1 - 1, 256), EUtil.imm(32L, 256)));
         }

         IEAssign var6 = this.ctx.createAssign(var7, this.createStackMemoryAccess((IEGeneric)var5, 256));
         var2.r.add(var6);
         return var7;
      } else {
         int var4 = this.Cu - var1;
         if (var4 < 0 || var4 >= this.sO.size()) {
            Object[] var10000 = new Object[0];
         }

         return (IEVar)this.sO.get(var4);
      }
   }

   private void pC(int var1, ConverterInstructionEntry var2, IEVar var3) {
      Object var4 = this.eP;
      if (var1 >= 2) {
         var4 = EUtil.add((IEGeneric)var4, EUtil.mul(EUtil.imm(var1 - 1, 256), EUtil.imm(32L, 256)));
      }

      var2.r.add(this.ctx.createAssign(this.createStackMemoryAccess((IEGeneric)var4, 256), var3));
   }

   OperationType pC(int var1) {
      switch (var1) {
         case 1:
            return OperationType.ADD;
         case 2:
            return OperationType.MUL;
         case 3:
            return OperationType.SUB;
         case 4:
            return OperationType.DIV_U;
         case 5:
            return OperationType.DIV_S;
         case 6:
            return OperationType.REM_U;
         case 7:
            return OperationType.REM_S;
         case 8:
         case 9:
         case 11:
         case 12:
         case 13:
         case 14:
         case 15:
         case 21:
         case 26:
         default:
            return null;
         case 10:
            return OperationType.POW;
         case 16:
            return OperationType.LT_U;
         case 17:
            return OperationType.GT_U;
         case 18:
            return OperationType.LT_S;
         case 19:
            return OperationType.GT_S;
         case 20:
            return OperationType.LOG_EQ;
         case 22:
            return OperationType.AND;
         case 23:
            return OperationType.OR;
         case 24:
            return OperationType.XOR;
         case 25:
            return OperationType.NOT;
         case 27:
            return OperationType.SHL;
         case 28:
            return OperationType.SHR;
         case 29:
            return OperationType.SAR;
      }
   }

   public void wS() {
      for (INativeMethodItem var2 : this.E.getMethods()) {
         this.pC(var2, false);
      }
   }

   public IPrototypeItem pC(INativeMethodItem var1, boolean var2) {
      IPrototypeItem var3 = var1.getPrototype();
      if (var3 == null || var2) {
         eW var4 = this.UT.kS(var1.getMemoryAddress());
         if (var4 != null) {
            var3 = this.pC(var4);
            var1.setPrototype(var3);
         }
      }

      return var3;
   }

   IPrototypeItem pC(eW var1) {
      ayr var2 = this.E.A().pC();
      IPrimitiveType var3 = var2.getType("uint256");
      if (var3 == null) {
         throw new RuntimeException("Missing primitive type: uint256");
      } else {
         ICallingConvention var4 = this.E.A().A().getDefaultConvention();
         IPrimitiveType var5 = null;
         ArrayList var6 = new ArrayList();
         if (!(var1 instanceof m)) {
            if (!(var1 instanceof nA)) {
               throw new RuntimeException("Unknown EVM routine type: " + var1);
            }

            for (int var7 = 0; var7 < ((nA)var1).kS(); var7++) {
               var6.add(var3);
            }

            int var8 = ((nA)var1).wS();
            if (var8 == 1) {
               var5 = var3;
            } else if (var8 >= 2) {
               var5 = var3;
            }
         }

         return this.E.A().pC(var4, var5, var6, null);
      }
   }

   @Override
   public IEPrototypeHandler getPrototypeHandler(IERoutineContext var1) {
      return new gJ(var1);
   }

   @Override
   public IEVar getInputVariableByIndex(IERoutineContext var1, int var2) {
      return var1.getVariableByName("par" + (1 + var2));
   }

   @Override
   public ICStatement generateASTForUntranslatedIR(IEUntranslatedInstruction var1, IERoutineContext var2, ICMethod var3) {
      String var4 = var1.getNativeMnemonic();
      if (var4 == null) {
         return null;
      } else {
         int var5 = (Integer)var1.getTag();
         String var6 = var4.toLowerCase();
         if (var5 == 90) {
            var6 = "gasleft";
         } else if (var5 == 243) {
            var6 = "__evm_return";
         }

         ICElementFactory var7 = var3.getElementFactory();
         ICTypeFactory var8 = var3.getTypeFactory();
         IWildcardTypeManager var9 = var2.getWildcardTypeManager();
         ICType var10 = var8.create(var9.create("uint256"));
         switch (var5) {
            case Integer.MIN_VALUE:
               ICCustomStatement var27 = var7.createNativeStatement(var1.getNativeAddress());
               var27.setCommandName("throw()");
               return var27;
            case 48:
               IEGeneric var26 = var1.getReturnExpression();
               ICLeftExpression var38 = (ICLeftExpression)var26.generateC(var2, var3);
               ICIdentifier var50 = var3.getIdentifierManager().create(65537, var10, "address(this)", CIdentifierClass.BUILTIN).getIdentifier();
               return var7.createAssignment(var38, var50);
            case 51:
               IEGeneric var25 = var1.getReturnExpression();
               ICLeftExpression var37 = (ICLeftExpression)var25.generateC(var2, var3);
               ICIdentifier var49 = var3.getIdentifierManager().create(65536, var10, "msg.sender", CIdentifierClass.BUILTIN).getIdentifier();
               return var7.createAssignment(var37, var49);
            case 52:
               IEGeneric var24 = var1.getReturnExpression();
               ICLeftExpression var36 = (ICLeftExpression)var24.generateC(var2, var3);
               ICIdentifier var48 = var3.getIdentifierManager().create(65538, var10, "msg.value", CIdentifierClass.BUILTIN).getIdentifier();
               return var7.createAssignment(var36, var48);
            case 53:
               ArrayList var23 = new ArrayList();

               for (IEGeneric var62 : (IEGeneric[])var1.getOperands()) {
                  ICExpression var65 = (ICExpression)var62.generateC(var2, var3);
                  var23.add(var65);
               }

               IEGeneric var35 = var1.getReturnExpression();
               ICLeftExpression var47 = (ICLeftExpression)var35.generateC(var2, var3);
               ICMethod var57 = var3.getMethodFactory().createSynthetic(var4.toLowerCase());
               return var7.createAssignment(var47, var7.createCall(var57, var23));
            case 54:
               IEGeneric var22 = var1.getReturnExpression();
               ICLeftExpression var33 = (ICLeftExpression)var22.generateC(var2, var3);
               ICIdentifier var45 = var3.getIdentifierManager().create(65539, var10, "msg.data", CIdentifierClass.BUILTIN).getIdentifier();
               ICOperation var55 = var7.createOperation(var3.getOperatorFactory().get(COperatorType.SIZEOF), var45);
               return var7.createAssignment(var33, var55);
            case 84:
            case 85:
               ICCustomStatement var21 = var7.createNativeStatement(var1.getNativeAddress());
               var21.setCommandName(var6);
               ArrayList var32 = new ArrayList();

               for (IEGeneric var54 : var1.getParameterExpressions()) {
                  ICExpression var61 = (ICExpression)var54.generateC(var2, var3);
                  var32.add(var61);
               }

               var21.setInputElements(var32);
               if (var5 == 84) {
                  ArrayList var44 = new ArrayList();
                  var44.add(var1.getReturnExpression().generateC(var2, var3));
                  var21.setOutputElements(var44);
               }

               return var21;
            case 161:
            case 162:
            case 163:
            case 164:
               String var18 = (String)var1.getTag("eventName");
               if (var18 != null && !var18.startsWith("unknown_")) {
                  ICCustomStatement var31 = var7.createNativeStatement(var1.getNativeAddress());
                  ArrayList var42 = new ArrayList();
                  int var53 = 0;

                  for (IEGeneric var64 : var1.getParameterExpressions()) {
                     if (var53 != 2) {
                        ICExpression var17 = (ICExpression)var64.generateC(var2, var3);
                        var42.add(var17);
                        var53++;
                     }
                  }

                  var31.setCommandName("emit " + var18);
                  var31.setInputElements(var42);
                  return var31;
               }
            default:
               if (var5 == 241) {
                  IEGeneric var19 = var1.getParameterExpression(1);
                  if (var19 instanceof IEImm) {
                     BigInteger var29 = ((IEImm)var19).getValue();
                     Integer var39 = null;
                     if (var29.equals(ld)) {
                        var39 = 1073741825;
                     } else if (var29.equals(gp)) {
                        var39 = 1073741826;
                     } else if (var29.equals(oT)) {
                        var39 = 1073741827;
                     } else if (var29.equals(fI)) {
                        var39 = 1073741828;
                     } else if (var29.equals(WR)) {
                        var39 = 1073741829;
                     } else if (var29.equals(NS)) {
                        var39 = 1073741830;
                     } else if (var29.equals(vP)) {
                        var39 = 1073741831;
                     } else if (var29.equals(xC)) {
                        var39 = 1073741832;
                     } else if (var29.equals(ED)) {
                        var39 = 1073741833;
                     }

                     if (var39 != null) {
                        var6 = var6 + "_" + uX.pC(var39).A().toLowerCase();
                     }
                  }
               }

               ICMethod var20 = var3.getMethodFactory().createSynthetic(var6);
               ArrayList var30 = new ArrayList();

               for (IEGeneric var51 : var1.getParameterExpressions()) {
                  ICExpression var58 = (ICExpression)var51.generateC(var2, var3);
                  var30.add(var58);
               }

               ICLeftExpression var41 = null;
               IEGeneric var52 = var1.getReturnExpression();
               if (var52 != null) {
                  var41 = (ICLeftExpression)var52.generateC(var2, var3);
               }

               ICCall var63 = var7.createCall(var20, var30);
               Object var59;
               if (var41 != null) {
                  var59 = var7.createAssignment(var41, var63);
               } else {
                  var59 = var63;
               }

               return (ICStatement)var59;
            case 243:
            case 253:
               ArrayList var11 = new ArrayList();

               for (IEGeneric var15 : (IEGeneric[])var1.getOperands()) {
                  ICExpression var16 = (ICExpression)var15.generateC(var2, var3);
                  var11.add(var16);
               }

               ICMethod var28 = var3.getMethodFactory().createSynthetic(var4.toLowerCase());
               return var7.createCall(var28, var11);
         }
      }
   }

   public static Integer pC(IEStatement var0, int... var1) {
      if (!(var0 instanceof IEUntranslatedInstruction)) {
         return null;
      } else {
         Object var2 = ((IEUntranslatedInstruction)var0).getTag();
         if (!(var2 instanceof Integer)) {
            return null;
         } else {
            int var3 = (Integer)var2;

            for (int var7 : var1) {
               if (var7 == var3) {
                  return var3;
               }
            }

            return null;
         }
      }
   }

   public static boolean pC(IEStatement var0, int var1) {
      if (!(var0 instanceof IEUntranslatedInstruction)) {
         return false;
      } else {
         Object var2 = ((IEUntranslatedInstruction)var0).getTag();
         return var2 instanceof Integer && (Integer)var2 == var1;
      }
   }

   public static boolean pC(IEStatement var0) {
      return pC(var0, 254);
   }

   public static boolean A(IEStatement var0) {
      return !pC(var0, 253) ? false : EUtil.isZero(((IEUntranslatedInstruction)var0).getParameterExpression(0));
   }

   public static boolean kS(IEStatement var0) {
      return !pC(var0, 253)
         ? false
         : EUtil.isZero(((IEUntranslatedInstruction)var0).getParameterExpression(0))
            && EUtil.isZero(((IEUntranslatedInstruction)var0).getParameterExpression(1));
   }

   public static boolean wS(IEStatement var0) {
      return pC(var0, Integer.MIN_VALUE);
   }

   public static IEUntranslatedInstruction pC(IERoutineContext var0, long var1, int var3, IEGeneric... var4) {
      GK var5 = uX.pC(var3);
      IEUntranslatedInstruction var6 = var0.createUntranslatedInstruction(var1, var5.A(), var4);
      var6.setTag(var3);
      return var6;
   }

   public static IEUntranslatedInstruction pC(IERoutineContext var0, long var1, Mh var3, IEGeneric... var4) {
      IEUntranslatedInstruction var5 = var0.createUntranslatedInstruction(var1, var3.getMnemonic(), var4);
      var5.setTag(var3.pC());
      return var5;
   }
}
