package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.units.INativeCodeUnit;
import com.pnfsoftware.jeb.core.units.code.AddressableInstruction;
import com.pnfsoftware.jeb.core.units.code.FlowInformation;
import com.pnfsoftware.jeb.core.units.code.IFlowInformation;
import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.IInstructionOperand;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.BranchTarget;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.AbstractConverter;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.IllegalConversionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.InstructionConversionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.exceptions.UnsupportedConversionException;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EState;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEBranchDetails;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECond;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEJumpFar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEUntranslatedInstruction;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeContinuousItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeInstructionItem;
import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.core.units.code.asm.processor.IRegisterBank;
import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterDescriptionEntry;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterUtil;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrototypeItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
import com.pnfsoftware.jeb.core.units.codeobject.IELFUnit;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.collect.CollectionUtil;
import com.pnfsoftware.jeb.util.collect.Lists;
import com.pnfsoftware.jeb.util.collect.ReferenceCounter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import com.pnfsoftware.jeb.util.serialization.annotations.SerTransient;
import java.util.ArrayList;
import java.util.List;

@Ser
public class cfb extends AbstractConverter {
   private static final ILogger UW = GlobalLog.getLogger(cfb.class);
   private static final Object[] PR = new Object[]{
      "$zero",
      0,
      64,
      "$zero",
      0,
      32,
      "$at",
      64,
      64,
      "$at",
      64,
      32,
      "$v0",
      128,
      64,
      "$v0",
      128,
      32,
      "$v1",
      192,
      64,
      "$v1",
      192,
      32,
      "$a0",
      256,
      64,
      "$a0",
      256,
      32,
      "$a1",
      320,
      64,
      "$a1",
      320,
      32,
      "$a2",
      384,
      64,
      "$a2",
      384,
      32,
      "$a3",
      448,
      64,
      "$a3",
      448,
      32,
      "$a4",
      512,
      64,
      "$t0",
      512,
      32,
      "$a5",
      576,
      64,
      "$t1",
      576,
      32,
      "$a6",
      640,
      64,
      "$t2",
      640,
      32,
      "$a7",
      704,
      64,
      "$t3",
      704,
      32,
      "$t4",
      768,
      64,
      "$t4",
      768,
      32,
      "$t5",
      832,
      64,
      "$t5",
      832,
      32,
      "$t6",
      896,
      64,
      "$t6",
      896,
      32,
      "$t7",
      960,
      64,
      "$t7",
      960,
      32,
      "$s0",
      1024,
      64,
      "$s0",
      1024,
      32,
      "$s1",
      1088,
      64,
      "$s1",
      1088,
      32,
      "$s2",
      1152,
      64,
      "$s2",
      1152,
      32,
      "$s3",
      1216,
      64,
      "$s3",
      1216,
      32,
      "$s4",
      1280,
      64,
      "$s4",
      1280,
      32,
      "$s5",
      1344,
      64,
      "$s5",
      1344,
      32,
      "$s6",
      1408,
      64,
      "$s6",
      1408,
      32,
      "$s7",
      1472,
      64,
      "$s7",
      1472,
      32,
      "$t8",
      1536,
      64,
      "$t8",
      1536,
      32,
      "$t9",
      1600,
      64,
      "$t9",
      1600,
      32,
      "$k0",
      1664,
      64,
      "$k0",
      1664,
      32,
      "$k1",
      1728,
      64,
      "$k1",
      1728,
      32,
      "$gp",
      1792,
      64,
      "$gp",
      1792,
      32,
      "$sp",
      1856,
      64,
      "$sp",
      1856,
      32,
      "$fp",
      1920,
      64,
      "$fp",
      1920,
      32,
      "$ra",
      1984,
      64,
      "$ra",
      1984,
      32,
      "lo",
      2048,
      64,
      "lo",
      2048,
      32,
      "hi",
      2112,
      64,
      "hi",
      2112,
      32,
      "PC",
      2176,
      64,
      "PC",
      2176,
      32,
      "LLbit",
      2240,
      1,
      "ISAMode",
      2241,
      1,
      "cc0",
      2288,
      1,
      "cc1",
      2289,
      1,
      "cc2",
      2290,
      1,
      "cc3",
      2291,
      1,
      "cc4",
      2292,
      1,
      "cc5",
      2293,
      1,
      "cc6",
      2294,
      1,
      "cc7",
      2295,
      1,
      "$f0",
      2304,
      64,
      "$f0",
      2304,
      32,
      "$f1",
      2368,
      64,
      "$f1",
      2368,
      32,
      "$f2",
      2432,
      64,
      "$f2",
      2432,
      32,
      "$f3",
      2496,
      64,
      "$f3",
      2496,
      32,
      "$f4",
      2560,
      64,
      "$f4",
      2560,
      32,
      "$f5",
      2624,
      64,
      "$f5",
      2624,
      32,
      "$f6",
      2688,
      64,
      "$f6",
      2688,
      32,
      "$f7",
      2752,
      64,
      "$f7",
      2752,
      32,
      "$f8",
      2816,
      64,
      "$f8",
      2816,
      32,
      "$f9",
      2880,
      64,
      "$f9",
      2880,
      32,
      "$f10",
      2944,
      64,
      "$f10",
      2944,
      32,
      "$f11",
      3008,
      64,
      "$f11",
      3008,
      32,
      "$f12",
      3072,
      64,
      "$f12",
      3072,
      32,
      "$f13",
      3136,
      64,
      "$f13",
      3136,
      32,
      "$f14",
      3200,
      64,
      "$f14",
      3200,
      32,
      "$f15",
      3264,
      64,
      "$f15",
      3264,
      32,
      "$f16",
      3328,
      64,
      "$f16",
      3328,
      32,
      "$f17",
      3392,
      64,
      "$f17",
      3392,
      32,
      "$f18",
      3456,
      64,
      "$f18",
      3456,
      32,
      "$f19",
      3520,
      64,
      "$f19",
      3520,
      32,
      "$f20",
      3584,
      64,
      "$f20",
      3584,
      32,
      "$f21",
      3648,
      64,
      "$f21",
      3648,
      32,
      "$f22",
      3712,
      64,
      "$f22",
      3712,
      32,
      "$f23",
      3776,
      64,
      "$f23",
      3776,
      32,
      "$f24",
      3840,
      64,
      "$f24",
      3840,
      32,
      "$f25",
      3904,
      64,
      "$f25",
      3904,
      32,
      "$f26",
      3968,
      64,
      "$f26",
      3968,
      32,
      "$f27",
      4032,
      64,
      "$f27",
      4032,
      32,
      "$f28",
      4096,
      64,
      "$f28",
      4096,
      32,
      "$f29",
      4160,
      64,
      "$f29",
      4160,
      32,
      "$f30",
      4224,
      64,
      "$f30",
      4224,
      32,
      "$f31",
      4288,
      64,
      "$f31",
      4288,
      32,
      "$cop0",
      4352,
      64,
      "$cop0",
      4352,
      32,
      "$cop1",
      4416,
      64,
      "$cop1",
      4416,
      32,
      "$cop2",
      4480,
      64,
      "$cop2",
      4480,
      32,
      "$cop3",
      4544,
      64,
      "$cop3",
      4544,
      32,
      "$cop4",
      4608,
      64,
      "$cop4",
      4608,
      32,
      "$cop5",
      4672,
      64,
      "$cop5",
      4672,
      32,
      "$cop6",
      4736,
      64,
      "$cop6",
      4736,
      32,
      "$cop7",
      4800,
      64,
      "$cop7",
      4800,
      32,
      "$cop8",
      4864,
      64,
      "$cop8",
      4864,
      32,
      "$cop9",
      4928,
      64,
      "$cop9",
      4928,
      32,
      "$cop10",
      4992,
      64,
      "$cop10",
      4992,
      32,
      "$cop11",
      5056,
      64,
      "$cop11",
      5056,
      32,
      "$cop12",
      5120,
      64,
      "$cop12",
      5120,
      32,
      "$cop13",
      5184,
      64,
      "$cop13",
      5184,
      32,
      "$cop13",
      5248,
      64,
      "$cop14",
      5248,
      32,
      "$cop15",
      5312,
      64,
      "$cop15",
      5312,
      32,
      "$cop16",
      5376,
      64,
      "$cop16",
      5376,
      32,
      "$cop17",
      5440,
      64,
      "$cop17",
      5440,
      32,
      "$cop18",
      5504,
      64,
      "$cop18",
      5504,
      32,
      "$cop19",
      5568,
      64,
      "$cop19",
      5568,
      32,
      "$cop20",
      5632,
      64,
      "$cop20",
      5632,
      32,
      "$cop21",
      5696,
      64,
      "$cop21",
      5696,
      32,
      "$cop22",
      5760,
      64,
      "$cop22",
      5760,
      32,
      "$cop23",
      5824,
      64,
      "$cop23",
      5824,
      32,
      "$cop24",
      5888,
      64,
      "$cop24",
      5888,
      32,
      "$cop25",
      5952,
      64,
      "$cop25",
      5952,
      32,
      "$cop26",
      6016,
      64,
      "$cop26",
      6016,
      32,
      "$cop27",
      6080,
      64,
      "$cop27",
      6080,
      32,
      "$cop28",
      6144,
      64,
      "$cop28",
      6144,
      32,
      "$cop29",
      6208,
      64,
      "$cop29",
      6208,
      32,
      "$cop30",
      6272,
      64,
      "$cop30",
      6272,
      32,
      "$cop31",
      6336,
      64,
      "$cop31",
      6336,
      32,
      "CPUNum",
      6400,
      64,
      "CPUNum",
      6400,
      32,
      "SYNCI_Step",
      6464,
      64,
      "SYNCI_Step",
      6464,
      32,
      "CC",
      6528,
      64,
      "CC",
      6528,
      32,
      "CCRes",
      6592,
      64,
      "CCRes",
      6592,
      32,
      "PerfCtr",
      6656,
      64,
      "PerfCtr",
      6656,
      32,
      "XNP",
      6720,
      64,
      "XNP",
      6720,
      32,
      "$hw6",
      6784,
      64,
      "$hw6",
      6784,
      32,
      "$hw7",
      6848,
      64,
      "$hw7",
      6848,
      32,
      "$hw8",
      6912,
      64,
      "$hw8",
      6912,
      32,
      "$hw9",
      6976,
      64,
      "$hw9",
      6976,
      32,
      "$hw10",
      7040,
      64,
      "$hw10",
      7040,
      32,
      "$hw11",
      7104,
      64,
      "$hw11",
      7104,
      32,
      "$hw12",
      7168,
      64,
      "$hw12",
      7168,
      32,
      "$hw13",
      7232,
      64,
      "$hw13",
      7232,
      32,
      "$hw13",
      7296,
      64,
      "$hw14",
      7296,
      32,
      "$hw15",
      7360,
      64,
      "$hw15",
      7360,
      32,
      "$hw16",
      7424,
      64,
      "$hw16",
      7424,
      32,
      "$hw17",
      7488,
      64,
      "$hw17",
      7488,
      32,
      "$hw18",
      7552,
      64,
      "$hw18",
      7552,
      32,
      "$hw19",
      7616,
      64,
      "$hw19",
      7616,
      32,
      "$hw20",
      7680,
      64,
      "$hw20",
      7680,
      32,
      "$hw21",
      7744,
      64,
      "$hw21",
      7744,
      32,
      "$hw22",
      7808,
      64,
      "$hw22",
      7808,
      32,
      "$hw23",
      7872,
      64,
      "$hw23",
      7872,
      32,
      "$hw24",
      7936,
      64,
      "$hw24",
      7936,
      32,
      "$hw25",
      8000,
      64,
      "$hw25",
      8000,
      32,
      "$hw26",
      8064,
      64,
      "$hw26",
      8064,
      32,
      "$hw27",
      8128,
      64,
      "$hw27",
      8128,
      32,
      "$hw28",
      8192,
      64,
      "$hw28",
      8192,
      32,
      "ULR",
      8256,
      64,
      "ULR",
      8256,
      32,
      "$hw30",
      8320,
      64,
      "$hw30",
      8320,
      32,
      "$hw31",
      8384,
      64,
      "$hw31",
      8384,
      32,
      "$lo1",
      8448,
      64,
      "$lo1",
      8448,
      32,
      "$lo2",
      8512,
      64,
      "$lo2",
      8512,
      32,
      "$lo3",
      8576,
      64,
      "$lo3",
      8576,
      32,
      "$hi1",
      8640,
      64,
      "$hi1",
      8640,
      32,
      "$hi2",
      8704,
      64,
      "$hi2",
      8704,
      32,
      "$hi3",
      8768,
      64,
      "$hi3",
      8768,
      32
   };
   @SerTransient
   cew pC;
   @SerTransient
   cex A;
   @SerTransient
   cez kS;
   @SerTransient
   cfa wS;
   @SerTransient
   cff UT;
   @SerId(6)
   int E;
   @SerId(7)
   IEVar sY;
   @SerId(8)
   IEVar ys;
   @SerId(9)
   IEVar ld;
   @SerId(10)
   IEVar gp;
   @SerId(11)
   IEVar oT;
   @SerId(12)
   IEVar fI;
   @SerId(13)
   IEVar WR;
   @SerId(14)
   IEVar NS;
   @SerId(15)
   IEVar vP;
   @SerId(16)
   IEVar xC;
   @SerId(17)
   IEVar ED;
   @SerId(18)
   IEVar Sc;
   @SerId(19)
   IEVar ah;
   @SerId(20)
   IEVar eP;
   @SerId(21)
   IEImm UO;
   @SerId(22)
   IEImm Ab;
   @SerId(23)
   IEImm rl;
   @SerId(26)
   IEVar z;
   @SerId(27)
   IEVar Ek;
   @SerId(28)
   IEVar[] hK = new IEVar[8];
   @SerId(29)
   IEVar Er;
   @SerId(30)
   IEVar FE;
   @SerId(31)
   IEVar Aj;
   @SerId(32)
   int EX;
   @SerId(33)
   com.pnfsoftware.jeb.corei.parsers.mips.Tb LM;
   @SerId(200)
   ReferenceCounter mv;
   @SerId(201)
   int sO;
   @SerId(202)
   int os;
   @SerId(203)
   int Cu;
   @SerId(204)
   ReferenceCounter hZ;

   public cfb(com.pnfsoftware.jeb.corei.parsers.mips.p var1) {
      super(var1, var1.getMode());
      this.LM = var1.pC();
      if (var1.getMode() != 64 && var1.pC() != com.pnfsoftware.jeb.corei.parsers.mips.Tb.gp) {
         this.EX = 32;
      } else {
         this.EX = 64;
      }

      for (byte var2 = 0; var2 < PR.length; var2 += 3) {
         String var3 = (String)PR[var2];
         int var4 = (Integer)PR[var2 + 1];
         int var5 = (Integer)PR[var2 + 2];
         if ((var3.startsWith("$f") && !var3.equals("$fp") ? this.EX == var5 : var5 <= this.regNormalBitsize) && this.gCtx.canCreateVariable(var4, var5)) {
            this.gCtx.createRegister(var4, var3, var5);
         }
      }

      this.E = this.regNormalBitsize;
      this.sY = this.gCtx.getVar(128);
      this.ys = this.gCtx.getVar(192);
      this.ld = this.gCtx.getVar(256);
      this.gp = this.gCtx.getVar(320);
      this.oT = this.gCtx.getVar(384);
      this.fI = this.gCtx.getVar(448);
      this.vP = this.gCtx.getVar(1856);
      this.NS = this.gCtx.getVar(1920);
      this.xC = this.gCtx.getVar(1984);
      this.ED = this.gCtx.getVar(2048);
      this.Sc = this.gCtx.getVar(2112);
      this.WR = this.gCtx.getVar(2176);

      for (int var6 = 0; var6 < 8; var6++) {
         this.hK[var6] = this.gCtx.getVar(2288 + var6 * 1);
      }

      this.ah = this.gCtx.getVar(2240);
      this.Aj = this.gCtx.getVar(2241);
      this.eP = this.gCtx.createVirtualRegister(65536, "tmp", 32);
      this.z = this.gCtx.createVirtualRegister(65568, "tmpJump", this.E);
      this.Er = this.gCtx.createVirtualRegister(65664, "tmp", 64);
      this.FE = this.gCtx.createVirtualRegister(65728, "tmp2", 64);
      this.Ek = this.gCtx.createVirtualRegister(65792, "tmpJumpPredicate", 1);
      this.UO = this.gCtx.createImm(0L, 1);
      this.Ab = this.gCtx.createImm(1L, 1);
      this.rl = this.gCtx.createImm(0L, 32);
      this.pC();
      this.mv = new ReferenceCounter();
      this.hZ = new ReferenceCounter();
   }

   @SerCustomInitPostGraph
   private void pC() {
      this.pC = new cew(this);
      this.A = new cex(this);
      this.kS = new cez(this);
      this.wS = new cfa(this);
      this.UT = new cff(this);
      if (this.EX == 0) {
         this.EX = this.E;
      }

      if (this.LM == null) {
         this.LM = ((com.pnfsoftware.jeb.corei.parsers.mips.p)this.proc).pC();
      }
   }

   @Override
   public IEVar getProgramCounter() {
      return this.WR;
   }

   @Override
   public IEVar getReturnAddressRegister() {
      return this.xC;
   }

   @Override
   public IEVar getStackPointer() {
      return this.vP;
   }

   @Override
   public void setCurrentContext(IERoutineContext var1) {
      super.setCurrentContext(var1);
      this.pC.A = var1;
      this.A.A = var1;
      this.kS.A = var1;
      this.wS.A = var1;
      this.UT.A = var1;
   }

   @Override
   protected BasicBlock preBlockConversion(CFG var1, BasicBlock var2, List var3) {
      if (var2.size() > 1
         && ((com.pnfsoftware.jeb.corei.parsers.mips.Sv)var2.get(var2.size() - 2)).A().pC()
         && ((com.pnfsoftware.jeb.corei.parsers.mips.Sv)var2.get(var2.size() - 1)).wS()) {
         throw new IllegalConversionException(
            Strings.ff("CTI instruction %s is not managed in delay slot for routine at %xh", var2.get(var2.size() - 1), var1.getEntryAddress())
         );
      } else {
         boolean var4 = true;
         if (((com.pnfsoftware.jeb.corei.parsers.mips.Sv)var2.get(var2.size() - 1)).A().pC()) {
            var2 = var2.shallowCopy(true);
            var4 = false;
            com.pnfsoftware.jeb.corei.parsers.mips.Sv var5 = (com.pnfsoftware.jeb.corei.parsers.mips.Sv)var1.getInstruction(var2.getEndAddress());
            if (var5 == null) {
               throw new IllegalConversionException(
                  Strings.ff("Can not process routine at %xh because the last delay slot instruction can not be retrieved.", var1.getEntryAddress())
               );
            }

            var2.add(var5);
         }

         for (int var25 = 0; var25 < var2.size(); var25++) {
            cfq var6 = (cfq)var2.get(var25);
            String var7 = var6.getMnemonic();
            switch (var7) {
               case "SWL":
               case "SWR":
               case "LWL":
               case "LWR":
               case "LDL":
               case "LDR":
               case "SDL":
               case "SDR":
                  int var11 = var25 + 1;
                  cfq var12 = null;

                  cfq var13;
                  for (var13 = null; var11 < var2.size(); var11++) {
                     var13 = (cfq)var2.get(var11);
                     var12 = this.kS.pC(var6, var13, this.proc.getEndianness().isBig());
                     if (var12 != null) {
                        break;
                     }
                  }

                  if (var12 != null) {
                     if (var25 + 1 != var11) {
                        boolean var14 = false;
                        ArrayList var15 = new ArrayList();
                        ArrayList var16 = new ArrayList();
                        var6.getDefUse(var15, var16, null);

                        for (int var17 = var25 + 1; var17 < var11; var17++) {
                           cfq var18 = (cfq)var2.get(var17);
                           if (var18.A().isPCUpdated()) {
                              if (var17 != var11 - 1 || !var18.A().pC() || var18.A().kS()) {
                                 var14 = true;
                                 break;
                              }
                           } else {
                              ArrayList var19 = new ArrayList();
                              ArrayList var20 = new ArrayList();

                              try {
                                 var18.getDefUse(var19, var20, null);
                              } catch (Exception var24) {
                                 try {
                                    cfj[] var21 = (cfj[])var18.getOperands();

                                    for (int var22 = 0; var22 < var21.length; var22++) {
                                       var21[var22].pC(var20);
                                    }

                                    if (!CollectionUtil.intersect(var20, var16).isEmpty() || !CollectionUtil.intersect(var20, var15).isEmpty()) {
                                       var14 = true;
                                       break;
                                    }
                                 } catch (Exception var23) {
                                    var14 = true;
                                    break;
                                 }
                              }

                              if (!CollectionUtil.intersect(var19, var16).isEmpty() || !CollectionUtil.intersect(var19, var15).isEmpty()) {
                                 var14 = true;
                                 break;
                              }

                              if (!CollectionUtil.intersect(var20, var15).isEmpty()) {
                                 var14 = true;
                                 break;
                              }

                              if (!CollectionUtil.intersect(var20, var16).isEmpty() && this.pC(var6, var13)) {
                                 var14 = true;
                                 break;
                              }
                           }
                        }

                        if (var14) {
                           UW.catchingSilent(new JebRuntimeException(Strings.f("Can not merge Mips xWx at %xh", var2.getFirstAddress())));
                           continue;
                        }
                     }

                     if (var4) {
                        var2 = var2.shallowCopy(true);
                        var4 = false;
                     }

                     var2.set(var25, var12);
                     var2.set(var11, new cft(var13, "NOP"));
                  }
            }
         }

         return var2;
      }
   }

   private boolean pC(cfq var1, cfq var2) {
      if (!var2.sY() || ((cfj[])var1.getOperands()).length != 2 || ((cfj[])var2.getOperands()).length != 2) {
         return false;
      } else if (!this.pC(var1) && !this.pC(var2)) {
         cfj var3 = ((cfj[])var1.getOperands())[1];
         cfj var4 = ((cfj[])var2.getOperands())[1];
         if (var3.getOperandType() == 7 && var4.getOperandType() == 7) {
            if (var3.getOperands().length >= 2 && var4.getOperands().length >= 2 && var3.getOperands()[1].equals(var4.getOperands()[1])) {
               cfj var5 = (cfj)var3.getOperands()[0];
               cfj var6 = (cfj)var4.getOperands()[0];
               if (var3.getOperandType() == 1 && var4.getOperandType() == 1) {
                  String var7 = var2.getMnemonic();
                  int var8 = !var7.startsWith("SW") && !var7.startsWith("SH") && !var7.startsWith("SEH") && !var7.startsWith("SB") && !var7.startsWith("SEB")
                     ? 8
                     : 4;
                  return Math.abs(var5.getOperandValue() - var6.getOperandValue()) < var8;
               } else {
                  return false;
               }
            } else {
               return false;
            }
         } else {
            return false;
         }
      } else {
         return false;
      }
   }

   private boolean pC(cfq var1) {
      return ((cfj[])var1.getOperands())[1].getOperandType() == 7 && ((cfj[])var1.getOperands())[1].getOperands().length == 2
         ? ((cfj)((cfj[])var1.getOperands())[1].getOperands()[0]).getOperandValue() == 0L
            && ((cfj[])var1.getOperands())[0].getOperandValue() == ((cfj)((cfj[])var1.getOperands())[1].getOperands()[1]).getOperandValue()
         : true;
   }

   @Override
   protected void convertBlock(BasicBlock var1, List var2) {
      long var3 = var1.getFirstAddress();
      long var5 = var3;
      ArrayList var7 = new ArrayList();
      ArrayList var8 = new ArrayList();
      int var9 = 0;
      Long var10 = null;

      for (boolean var11 = false; var9 < var1.size(); var9++) {
         this.sO++;
         cfq var12 = (cfq)var1.get(var9);
         String var13 = var12.getMnemonic();
         var7.clear();
         if (cfq.pC(var13)) {
            this.UT.pC(var12, var7, var8, var5);
            if (var13.equals("BC1TL") || var13.equals("BC1FL")) {
               var11 = true;
            }
         } else {
            if (var13.startsWith("B") && var13.endsWith("C") && var13.length() > 3 && !var13.equals("BALC")) {
               var13 = "B**C";
            }

            switch (var13) {
               case "ADD":
               case "ADDI":
               case "ADDIU":
               case "ADDU":
                  this.pC.pC(var12, var7, OperationType.ADD, true);
                  break;
               case "AND":
               case "ANDI":
                  this.pC.pC(var12, var7, OperationType.AND, false, this.E);
                  break;
               case "CLO":
                  this.pC.pC(var12, var7);
                  break;
               case "CLZ":
                  this.pC.A(var12, var7);
                  break;
               case "DIV":
                  this.pC.pC(var12, var7, true);
                  break;
               case "DIVU":
                  this.pC.pC(var12, var7, false);
                  break;
               case "EXT":
                  this.pC.pC(var12, var7, 32);
                  break;
               case "INS":
                  this.pC.kS(var12, var7);
                  break;
               case "LUI":
                  this.pC.wS(var12, var7);
                  break;
               case "MOD":
                  this.pC.A(var12, var7, true);
                  break;
               case "MODU":
                  this.pC.A(var12, var7, false);
                  break;
               case "MOVN":
                  this.pC.A(var12, var7, OperationType.LOG_NEQ);
                  break;
               case "MOVZ":
                  this.pC.A(var12, var7, OperationType.LOG_EQ);
                  break;
               case "MUL":
                  this.pC.wS(var12, var7, true);
                  break;
               case "MULU":
                  this.pC.wS(var12, var7, false);
                  break;
               case "MULT":
                  this.pC.kS(var12, var7, true);
                  break;
               case "MULTU":
                  this.pC.kS(var12, var7, false);
                  break;
               case "MUH":
                  this.pC.UT(var12, var7, true);
                  break;
               case "MUHU":
                  this.pC.UT(var12, var7, false);
                  break;
               case "MADD":
                  this.pC.E(var12, var7, true);
                  break;
               case "MADDU":
                  this.pC.E(var12, var7, false);
                  break;
               case "MSUB":
                  this.pC.sY(var12, var7, true);
                  break;
               case "MSUBU":
                  this.pC.sY(var12, var7, false);
                  break;
               case "NOR":
                  this.pC.UT(var12, var7);
                  break;
               case "OR":
               case "ORI":
                  this.pC.pC(var12, var7, OperationType.OR, false, this.E);
                  break;
               case "SEB":
                  this.pC.A(var12, var7, 8);
                  break;
               case "SEH":
                  this.pC.A(var12, var7, 16);
                  break;
               case "SUB":
               case "SUBU":
                  this.pC.pC(var12, var7, OperationType.SUB, false);
                  break;
               case "XOR":
               case "XORI":
                  this.pC.pC(var12, var7, OperationType.XOR, false, this.E);
                  break;
               case "WSBH":
                  this.pC.sY(var12, var7);
                  break;
               case "ROTR":
               case "ROTRV":
                  this.pC.pC(var12, var7, OperationType.ROR, 32);
                  break;
               case "SLL":
               case "SLLV":
                  this.pC.pC(var12, var7, OperationType.SHL, 32);
                  break;
               case "SRL":
               case "SRLV":
                  this.pC.pC(var12, var7, OperationType.SHR, 32);
                  break;
               case "SRA":
               case "SRAV":
                  this.pC.pC(var12, var7, OperationType.SAR, 32);
                  break;
               case "LI":
               case "MOVE":
                  this.pC.pC(var12, var7, var5);
                  break;
               case "NEG":
               case "NEGU":
                  this.pC.E(var12, var7);
                  break;
               case "NOT":
                  this.pC.pC(var12, var7, OperationType.NOT);
                  break;
               case "B":
               case "BAL":
               case "J":
               case "JR":
               case "JAL":
                  if (this.pC(var12, var8, var7, var5, var13)) {
                     this.A.pC(var12, var8, var5);
                  }
                  break;
               case "JALR":
                  if (this.pC(var12, var8, var7, var5, var13)) {
                     this.A.A(var12, var8, var5);
                  }
                  break;
               case "JIALC":
               case "JALRC":
               case "JIC":
               case "JRC":
                  if (this.pC(var12, var8, var7, var5, var13)) {
                     this.A.kS(var12, var7, var5);
                  }
                  break;
               case "NAL":
                  this.A.wS(var12, var7, var5);
                  break;
               case "JALX":
                  if (this.pC(var12, var8, var7, var5, var13)) {
                     this.A.UT(var12, var8, var5);
                  }
                  break;
               case "BEQ":
               case "BEQZ":
               case "BGEZ":
               case "BGEZAL":
               case "BGTZ":
               case "BLEZ":
               case "BLTZ":
               case "BLTZAL":
               case "BNE":
               case "BNEZ":
                  if (this.pC(var12, var8, var7, var5, var13)) {
                     this.A.pC(var12, var8, var5, 8);
                  }
                  break;
               case "B**C":
                  if (this.pC(var12, var8, var7, var5, var13)) {
                     this.A.pC(var12, var7, var5, 4);
                  }
                  break;
               case "BEQL":
               case "BGEZALL":
               case "BGEZL":
               case "BGTZL":
               case "BLEZL":
               case "BLTZL":
               case "BLTZALL":
               case "BNEL":
                  if (this.pC(var12, var8, var7, var5, var13)) {
                     this.A.pC(var12, var8, var5, 8);
                  }

                  var11 = true;
                  break;
               case "LB":
                  this.kS.A(var12, var7, 8);
                  break;
               case "LBU":
                  this.kS.kS(var12, var7, 8);
                  break;
               case "LH":
                  this.kS.A(var12, var7, 16);
                  break;
               case "LHU":
                  this.kS.kS(var12, var7, 16);
                  break;
               case "LW":
                  this.kS.A(var12, var7, 32);
                  break;
               case "LD":
                  this.kS.kS(var12, var7, 64);
                  break;
               case "LWL":
                  this.kS.pC(var12, var7, 4, this.proc.getEndianness().isBig(), var2.size());
                  break;
               case "LWR":
                  this.kS.A(var12, var7, 4, this.proc.getEndianness().isBig(), var2.size());
                  break;
               case "LWPC":
                  this.kS.pC(var12, var7, var5, true);
                  break;
               case "SB":
                  this.kS.pC(var12, var7, 8);
                  break;
               case "SH":
                  this.kS.pC(var12, var7, 16);
                  break;
               case "SW":
                  this.kS.pC(var12, var7, 32);
                  break;
               case "SD":
                  this.kS.pC(var12, var7, 64);
                  break;
               case "SWL":
                  this.kS.kS(var12, var7, 4, this.proc.getEndianness().isBig(), var2.size());
                  break;
               case "SWR":
                  this.kS.wS(var12, var7, 4, this.proc.getEndianness().isBig(), var2.size());
                  break;
               case "SC":
                  this.kS.pC(var12, var7);
                  break;
               case "LL":
                  this.kS.A(var12, var7);
                  break;
               case "MFHI":
                  this.kS.pC(var12, var7, false);
                  break;
               case "MFLO":
                  this.kS.pC(var12, var7, true);
                  break;
               case "MTHI":
                  this.kS.A(var12, var7, false);
                  break;
               case "MTLO":
                  this.kS.A(var12, var7, true);
                  break;
               case "SELEQZ":
                  this.wS.pC(var12, var7);
                  break;
               case "SELNEZ":
                  this.wS.A(var12, var7);
                  break;
               case "SLT":
                  this.wS.pC(var12, var7, true);
                  break;
               case "SLTI":
                  this.wS.A(var12, var7, true);
                  break;
               case "SLTIU":
                  this.wS.A(var12, var7, false);
                  break;
               case "SLTU":
                  this.wS.pC(var12, var7, false);
                  break;
               case "PREF":
               case "NOP":
               case "SSNOP":
               case "SYNC":
                  if (!this.doNotGenerateNops) {
                     var7.add(this.ctx.createNop());
                  }
                  break;
               case "TEQ":
                  if ((var9 <= 0 || !this.A(var12, (cfq)var1.get(var9 - 1))) && (var9 + 1 >= var1.size() || !this.A(var12, (cfq)var1.get(var9 + 1)))) {
                     var7.add(this.pC(var12, var5));
                  }
                  break;
               case "TEQI":
               case "TGE":
               case "TGEI":
               case "TGEIU":
               case "TGEU":
               case "TLT":
               case "TLTI":
               case "TLTIU":
               case "TLTU":
               case "TNE":
               case "TNEI":
                  var7.add(this.pC(var12, var5));
                  break;
               case "AUI":
                  this.pC.ld(var12, var7);
                  break;
               case "AUIPC":
                  this.pC.kS(var12, var7, var5);
                  break;
               case "LAPC":
               case "ADDIUPC":
                  this.pC.pC(var12, var7, var5);
                  break;
               case "ALUIPC":
                  this.pC.wS(var12, var7, var5);
                  break;
               case "BC":
               case "BALC":
                  this.A.pC(var12, var7, var5);
                  break;
               case "LSA":
                  this.pC.ys(var12, var7);
                  break;
               case "ALIGN":
                  this.pC.UT(var12, var7, var5);
                  break;
               case "BITSWAP":
                  this.pC.gp(var12, var7);
                  break;
               case "BREAK":
               case "RDHWR":
               case "UDI":
                  var7.add(this.pC(var12, var5));
                  break;
               case "SYSCALL":
                  IEUntranslatedInstruction var22 = this.pC(var12, var5, this.sY);
                  var22.addSideEffectUsedVariable(this.ld, this.gp, this.oT, this.fI);
                  var22.addSideEffectDefinedVariable(this.sY, this.fI);
                  var7.add(var22);
                  break;
               case "DADD":
               case "DADDI":
               case "DADDIU":
               case "DADDU":
                  this.pC.pC(var12, var7, OperationType.ADD, true, var5);
                  break;
               case "DSLL":
                  this.pC.pC(var12, var7, var5, OperationType.SHL, 0);
                  break;
               case "DSLL32":
                  this.pC.pC(var12, var7, var5, OperationType.SHL, 32);
                  break;
               case "DSRL":
                  this.pC.pC(var12, var7, var5, OperationType.SHR, 0);
                  break;
               case "DSRL32":
                  this.pC.pC(var12, var7, var5, OperationType.SHR, 32);
                  break;
               case "DEXT":
                  this.pC.A(var12, var7, var5);
                  break;
               case "LDL":
                  this.kS.pC(var12, var7, 8, this.proc.getEndianness().isBig(), var2.size());
                  break;
               case "LDR":
                  this.kS.A(var12, var7, 8, this.proc.getEndianness().isBig(), var2.size());
                  break;
               case "SDL":
                  this.kS.kS(var12, var7, 8, this.proc.getEndianness().isBig(), var2.size());
                  break;
               case "SDR":
                  this.kS.wS(var12, var7, 8, this.proc.getEndianness().isBig(), var2.size());
                  break;
               case "LWU":
                  this.kS.kS(var12, var7, 32);
                  break;
               case "LWUPC":
                  this.kS.pC(var12, var7, var5, false);
                  break;
               case "MOVF":
               case "MOVT":
               case "DALIGN":
               case "DAUI":
               case "DAHI":
               case "DATI":
               case "DBITSWAP":
               case "DCLZ":
               case "DCLO":
               case "DSUB":
               case "DSUBU":
               case "DNEG":
               case "DNEGU":
               case "DSLLV":
               case "DSRLV":
               case "DSRAV":
               case "DLSA":
               case "DSRA":
               case "DSRA32":
               case "DROTR":
               case "DROTR32":
               case "DROTRV":
               case "DMULT":
               case "DMULTU":
               case "DDIV":
               case "DDIVU":
               case "DMOD":
               case "DMODU":
               case "DMUH":
               case "DMUL":
               case "DMUHU":
               case "DMULU":
               case "LDPC":
               case "LLD":
               case "LLDX":
               case "SCD":
               case "SCDX":
               case "DEXTM":
               case "DEXTU":
               case "DINS":
               case "DINSM":
               case "DINSU":
               case "DSBH":
               case "DSHD":
               case "LBE":
               case "LBUE":
               case "LHE":
               case "LHUE":
               case "LLE":
               case "LLX":
               case "LLXE":
               case "LWE":
               case "LWLE":
               case "LWRE":
               case "PREFE":
               case "SBE":
               case "SCE":
               case "SCXE":
               case "SHE":
               case "SWE":
               case "SWLE":
               case "SWRE":
               case "PREFX":
               case "SCX":
               case "CACHE":
               case "CACHEE":
               case "PAUSE":
               case "SDBBP":
               case "SIGRIE":
               case "SYNCI":
               case "TLBINV":
               case "TLBINVF":
               case "TLBP":
               case "TLBR":
               case "TLBWI":
               case "TLBWR":
               case "WAIT":
               case "ADDSC":
               case "ADDWC":
               case "APPEND":
               case "BALIGN":
               case "BITREV":
               case "BPOSGE32":
               case "BPOSGE32C":
               case "BPOSGE64":
               case "DAPPEND":
               case "DBALIGN":
               case "DEXTP":
               case "DEXTPDP":
               case "DEXTPDPV":
               case "DEXTPV":
               case "DINSV":
               case "DMADD":
               case "DMADDU":
               case "DMSUB":
               case "DMSUBU":
               case "DMTHLIP":
               case "DSHILO":
               case "DSHILOV":
               case "EXTP":
               case "EXTPDP":
               case "EXTPDPV":
               case "EXTPV":
               case "INSV":
               case "LDX":
               case "MODSUB":
               case "MTHLIP":
               case "PREPEND":
               case "PREPENDD":
               case "PREPENDW":
               case "SHILO":
               case "SHILOV":
                  var7.add(this.pC(var12, var5));
                  break;
               case "RDDSP":
               case "RDPGPR":
                  IEVar var21 = this.A(((cfj[])var12.getOperands())[0].getOperandValue());
                  IEUntranslatedInstruction var28 = this.pC(var12, var5, var21);
                  var28.addSideEffectDefinedVariable(var21);
                  var7.add(var28);
                  break;
               case "WRDSP":
               case "WRPGPR":
                  IEVar var20 = this.A(((cfj[])var12.getOperands())[var13.equals("WRPGPR") ? 1 : 0].getOperandValue());
                  IEUntranslatedInstruction var27 = this.pC(var12, var5, var20);
                  var27.addSideEffectDefinedVariable(var20);
                  var7.add(var27);
                  break;
               case "DERET":
                  var7.add(this.ctx.createReturn());
                  break;
               case "ERET":
                  var7.add(this.ctx.createAssign(this.ah, this.UO));
                  var7.add(this.ctx.createReturn());
                  break;
               case "ERETNC":
                  var7.add(this.ctx.createReturn());
                  break;
               case "EHB":
                  var7.add(this.pC(var12, var5));
                  break;
               case "DI":
               case "EI":
                  IEUntranslatedInstruction var16;
                  if (var12.getOperands() != null && ((cfj[])var12.getOperands()).length != 0) {
                     if (((cfj[])var12.getOperands())[0].getOperandType() != 0 || ((cfj[])var12.getOperands()).length > 1) {
                        throw new IllegalConversionException("Invalid DI/EI instruction: " + var12);
                     }

                     IEVar[] var17 = new IEVar[]{this.A(((cfj[])var12.getOperands())[0].getOperandValue())};
                     var16 = this.pC(var12, var5, var17);
                     var16.addSideEffectDefinedVariable(var17);
                  } else {
                     var16 = this.pC(var12, var5);
                  }

                  var7.add(var16);
                  break;
               case "LBUX":
                  this.kS.kS(var12, var7, 8);
                  break;
               case "LHX":
                  this.kS.kS(var12, var7, 16);
                  break;
               case "LWX":
                  this.kS.kS(var12, var7, 32);
                  break;
               default:
                  this.os++;
                  if (this.mv != null) {
                     this.mv.inc(var13);
                  }

                  throw new UnsupportedConversionException("Cannot convert instruction: " + var12);
            }
         }

         this.pC(var8, var7, var12, var5);
         if (!var8.isEmpty() && var10 == null) {
            var10 = var5;
         }

         if (!var8.isEmpty() && var10 != null && var10 != var5) {
            IEStatement var14 = (IEStatement)var8.get(var8.size() - 1);
            IEAssign var19 = null;
            if (var14 instanceof IEAssign) {
               var19 = (IEAssign)var14;
            }

            if (var11) {
               EUtil.setLowerLevelAddress(var10, var8);
            } else {
               EUtil.setLowerLevelAddress(var10, var7);
               EUtil.setLowerLevelAddress(var10, var8);
            }

            for (int var23 = 0; var23 < var8.size() - 1; var23++) {
               var2.add((IEStatement)var8.get(var23));
            }

            if (var19 != null && var11) {
               if (!(var19.getSrcOperand() instanceof IECond)) {
                  throw new IllegalConversionException("Likely branch must be conditional");
               }

               IECond var24 = (IECond)var19.getSrcOperand();
               IEAssign var29 = this.ctx
                  .createBranchAssign(this.WR, this.ctx.createCond(var24.getCondition(), this.pC(var10, 1), var24.getExpressionFalse()), false);
               var2.add(var29);
               var29.copyLowerLevelAddresses(var19);
               IEAssign var18 = this.ctx.createBranchAssign(this.WR, var24.getExpressionTrue(), var19.isRoutineCall());
               var18.copyLowerLevelAddresses(var19);
               var8.set(var8.size() - 1, var18);
               EUtil.setLowerLevelAddress(var10 + 1L, var7);
            }

            if (!var7.isEmpty()) {
               for (IEStatement var30 : var7) {
                  if (this.pC(var30)) {
                     var2.add(this.pC(var12, var13, var5));
                  } else {
                     var2.add(var30);
                  }
               }
            }

            if (var19 != null && !var11 && var19.getSrcOperand() instanceof IECond) {
               IECond var26 = (IECond)var19.getSrcOperand();
               IEAssign var31 = this.ctx
                  .createBranchAssign(this.WR, this.ctx.createCond(var26.getCondition(), var26.getExpressionTrue(), this.pC(var10, 1)), false);
               var31.copyLowerLevelAddresses(var19);
               var2.add(var31);
               var8.set(
                  var8.size() - 1, this.ctx.createBranchAssign(this.WR, var26.getExpressionFalse(), var19.isRoutineCall()).withLowerLevelAddress(var10 + 1L)
               );
            }

            var11 = false;
            var2.add((IEStatement)var8.get(var8.size() - 1));
            var8.clear();
         }

         if (var8.isEmpty()) {
            if (var10 == null) {
               EUtil.setLowerLevelAddress(var5, var7);
               var2.addAll(var7);
            }

            var10 = null;
         }

         var5 += var12.getSize();
      }
   }

   private boolean A(cfq var1, cfq var2) {
      return var2.getMnemonic().startsWith("DIV")
         && ((cfj[])var1.getOperands())[0] == ((cfj[])var2.getOperands())[1]
         && this.pC(((cfj[])var1.getOperands())[1]);
   }

   private boolean pC(cfj var1) {
      return var1.getOperandType() == 0
         && RegisterUtil.getRegisterGroup(var1.getOperandValue()) == 0
         && RegisterUtil.getRegisterIndex(var1.getOperandValue()) == 0;
   }

   boolean pC(cfq var1, List var2, List var3, long var4, String var6) {
      if (!var2.isEmpty()) {
         var3.add(this.pC(var1, var6, var4));
         return false;
      } else {
         return true;
      }
   }

   private IEStatement pC(cfq var1, String var2, long var3) {
      return this.ctx.createUntranslatedInstruction(var3, "SIGILL: " + var2, this.pC(var1, var3, false));
   }

   IEUntranslatedInstruction pC(cfq var1, long var2) {
      String var4 = var1.getMnemonic();
      this.Cu++;
      if (this.hZ != null) {
         this.hZ.inc(var4);
      }

      aks var5 = (aks)this.ctx.createUntranslatedInstruction(var2, var4);
      var5.setBreakingFlow(var1.getBreakingFlow(var2));
      var5.setRoutineCall(var1.getRoutineCall(var2));
      return var5;
   }

   private void pC(List var1, List var2, cfq var3, long var4) {
      if (var2.size() == 1 && var2.get(0) instanceof IEUntranslatedInstruction && var3.A().pC()) {
         IEUntranslatedInstruction var6 = (IEUntranslatedInstruction)var2.remove(0);
         if (this.pC(var3, var1, var2, var4, var3.getMnemonic())) {
            var1.add(var6);
            if (var6.getBreakingFlow(var4) != FlowInformation.NONE) {
               var6.setBreakingFlow(this.pC(var6.getBreakingFlow(var4)));
            }

            if (var6.getRoutineCall(var4) != FlowInformation.NONE) {
               var6.setRoutineCall(this.pC(var6.getRoutineCall(var4)));
            }
         }
      }
   }

   private IFlowInformation pC(IFlowInformation var1) {
      FlowInformation var2 = new FlowInformation(var1.mustComputeFallThrough(), 0);
      var2.addTargets(var1.getTargets());
      return var2;
   }

   IEUntranslatedInstruction pC(cfq var1, long var2, IEGeneric... var4) {
      String var5 = var1.getMnemonic();
      if (!var5.equals("SYSCALL")) {
         this.Cu++;
         if (this.hZ != null) {
            this.hZ.inc(var5);
         }
      }

      aks var6 = (aks)this.ctx.createUntranslatedInstruction(var2, var5, var4 == null ? this.pC(var1, var2, false) : var4);
      var6.setBreakingFlow(var1.getBreakingFlow(var2));
      var6.setRoutineCall(var1.getRoutineCall(var2));
      return var6;
   }

   IEGeneric[] pC(cfq var1, long var2, boolean var4) {
      if (var1.getOperands() == null) {
         return null;
      } else {
         ArrayList var5 = new ArrayList();

         for (int var6 = 0; var6 < ((cfj[])var1.getOperands()).length; var6++) {
            cfj var7 = ((cfj[])var1.getOperands())[var6];
            if (var7.getOperandType() == 0 && RegisterUtil.getRegisterGroup(var7.getOperandValue()) == 6) {
               int var10 = RegisterUtil.getRegisterIndex(var7.getOperandValue());
               var5.add(this.kS(var10));
               var5.add(this.wS(var10));
            } else {
               try {
                  IEGeneric var8 = this.pC(var1, (IInstructionOperand)var7, var2);
                  if (var8 != null) {
                     var5.add(var8);
                  }
               } catch (InstructionConversionException var9) {
                  if (var4) {
                     throw var9;
                  }

                  UW.catchingSilent(var9);
               }
            }
         }

         return (IEGeneric[])var5.toArray(new IEGeneric[var5.size()]);
      }
   }

   private boolean pC(IEStatement var1) {
      if (!(var1 instanceof IEAssign)) {
         return false;
      } else {
         IEGeneric var2 = ((IEAssign)var1).getDstOperand();
         return var2 == this.WR;
      }
   }

   @Override
   public IEBranchDetails getDefaultBranchToRoutineSideEffects(INativeMethodItem var1) {
      ArrayList var2 = Lists.createArrayList();
      Lists.addNonNulls(var2, this.ld, this.gp, this.oT, this.fI, this.vP);
      ArrayList var3 = Lists.createArrayList();
      Lists.addNonNulls(var3, this.sY);
      ArrayList var4 = Lists.createArrayList();
      Lists.addNonNulls(var4, this.ys);
      return this.gCtx.createBranchDetails(var3, var2, var4, 0);
   }

   public IEGeneric pC(long var1) {
      return this.pC(var1, 0);
   }

   public IEGeneric pC(long var1, int var3) {
      return this.ctx.createImm(var1 + var3, this.E);
   }

   public int pC(cfq var1, cfj var2, long var3) {
      return (int)this.A(var1, var2, var3);
   }

   public long A(cfq var1, cfj var2, long var3) {
      switch (var2.getOperandType()) {
         case 1:
         case 2:
         case 3:
            return var2.pC(var3, var1.getProcessorMode(), null);
         default:
            throw new IllegalConversionException("NaN parameter");
      }
   }

   public IEGeneric pC(cfq var1, IInstructionOperand var2) {
      return this.pC(var1, (cfj)var2, 0L, 0);
   }

   public IEGeneric pC(cfq var1, IInstructionOperand var2, long var3) {
      return this.pC(var1, (cfj)var2, var3, 0);
   }

   public IEGeneric pC(cfq var1, cfj var2, long var3, int var5) {
      switch (var2.getOperandType()) {
         case 0:
            return this.A(var2.getOperandValue());
         case 1:
            return this.ctx.createImm(var2.getOperandValue(), var5 == 0 ? var2.getOperandBitsize() : var5);
         case 2:
         case 3:
            return this.ctx.createImm(var2.getOperandValue(var3), this.E);
         case 4:
         case 5:
         case 6:
         default:
            return null;
         case 7:
            return var2.getOperands().length == 1 ? this.pC(var1, (cfj)var2.getOperands()[0], var3, var5) : this.pC(var1, var2);
      }
   }

   public IEGeneric pC(cfq var1, cfj var2) {
      return this.kS(var1, var2, 0L);
   }

   public IEGeneric kS(cfq var1, cfj var2, long var3) {
      if (var2.getOperandType() == 7) {
         IInstructionOperand[] var5 = var2.getOperands();
         IEGeneric var6 = this.pC(var1, var5[1], var3);
         IEGeneric var7 = this.pC(var1, (cfj)var5[0], var3, this.E);
         return this.ctx.createOperation(OperationType.ADD, var7, var6);
      } else {
         throw new IllegalConversionException("Expected an operand list");
      }
   }

   public IEMem pC(cfq var1, cfj var2, int var3) {
      IEGeneric var4 = this.kS(var1, var2, 0L);
      return this.gCtx.createMem(var4, var3);
   }

   private IRegisterBank A() {
      return RegisterUtil.getBank(this.regNormalBitsize == 64 ? ProcessorType.MIPS64 : ProcessorType.MIPS);
   }

   IEVar A(long var1) {
      int var3 = RegisterUtil.getRegisterGroup(var1);
      int var4 = RegisterUtil.getRegisterIndex(var1);
      RegisterDescriptionEntry var5 = this.A().getDescriptionEntryById(var1);
      String var6 = var5.getName();
      switch (var3) {
         case 0:
            return this.gCtx.createRegister(0 + var4 * 64, var6, this.regNormalBitsize);
         case 1:
         case 6:
         case 7:
         case 8:
         case 9:
         default:
            throw new UnsupportedConversionException("Register type not converted yet: " + var1);
         case 2:
            return this.gCtx.createRegister(2288 + var4 * 1, var6, 1);
         case 3:
            return this.gCtx.createRegister(2304 + var4 * 64, var6, this.EX);
         case 4:
            return this.gCtx.createRegister(4352 + var4 * 64, var6, this.regNormalBitsize);
         case 5:
            return this.gCtx.createRegister(6400 + var4 * 64, var6, this.regNormalBitsize);
         case 10:
            if (var4 == 0) {
               return this.WR;
            } else {
               throw new IllegalConversionException("Can not access lo and hi filter");
            }
      }
   }

   @Override
   public boolean resolveCustomCalls(IERoutineContext var1) {
      if (!(var1.getNativeContext() instanceof INativeCodeUnit var3)) {
         return false;
      } else if (!(var3.getCodeObjectContainer() instanceof IELFUnit)) {
         return false;
      } else {
         ITypeManager var4 = var3.getTypeManager();
         int var5 = 0;

         for (BasicBlock var7 : var1.getCfg()) {
            for (AddressableInstruction var9 : var7.addressableInstructions()) {
               IEStatement var10 = (IEStatement)var9.getInstruction();
               if (var10 instanceof IEUntranslatedInstruction var11) {
                  INativeContinuousItem var12 = var3.getNativeItemAt(var11.getNativeAddress());
                  if (var12 instanceof INativeInstructionItem) {
                     IInstruction var13 = ((INativeInstructionItem)var12).getInstruction();
                     if (var13 instanceof com.pnfsoftware.jeb.corei.parsers.mips.Sv && Strings.equalsIgnoreCase(var13.getMnemonic(), "syscall")) {
                        IEGeneric var14 = (IEGeneric)var11.getOperands()[0];
                        if (var14 instanceof IEImm && ((IEImm)var14).canReadAsLong()) {
                           int var15 = (int)((IEImm)var14).getValueAsLong();
                           Object[] var10000 = new Object[]{var15};
                           Long var16 = var10.getPrimaryLowerLevelAddress();
                           if (var16 != null) {
                              com.pnfsoftware.jeb.corei.parsers.mips.Mh var17 = com.pnfsoftware.jeb.corei.parsers.mips.Mh.pC();
                              String var18 = var17.pC(var15);
                              if (var18 != null) {
                                 var10000 = new Object[]{var18};
                                 IPrototypeItem var19 = var17.pC(var15, var4, true);
                                 if (var19 != null) {
                                    String var20 = "sys_" + var18;
                                    INativeMethodItem var21 = var3.getMethod(var20);
                                    if (var21 == null) {
                                       var21 = var3.createMethodReference(var20, var19, null);
                                    }

                                    var3.getCodeAnalyzer().recordDynamicBranchTarget(var16, true, new BranchTarget(var21), false);
                                    var5++;
                                 }
                              }
                           }
                        }
                     }
                  }
               }
            }
         }

         return var5 > 0;
      }
   }

   @Override
   protected void postBlockConversion(CFG var1, BasicBlock var2, List var3, int var4) {
      super.postBlockConversion(var1, var2, var3, var4);
      if (var2.outsize() == 0 && !var2.isEmpty()) {
         AddressableInstruction var5 = var2.getLast2();
         com.pnfsoftware.jeb.corei.parsers.mips.Sv var6 = (com.pnfsoftware.jeb.corei.parsers.mips.Sv)var5.getInstruction();
         long var7 = var5.getOffset();
         boolean var9 = !var6.getBreakingFlow(var7).isBroken() || var6.getRoutineCall(var7).isBroken();
         if (var9) {
            IEJumpFar var10 = this.ctx.createJumpFar(this.ctx.createImm(var7 + var6.getSize(), this.getAddressBitsize()));
            var3.add(var10);
            if (!var6.A().isPCUpdated() && var2.size() >= 2) {
               AddressableInstruction var11 = var2.get2(var2.size() - 2);
               if (((com.pnfsoftware.jeb.corei.parsers.mips.Sv)var11.getInstruction()).A().isPCUpdated()) {
                  var10.setLowerLevelAddress(var11.getOffset());
               } else {
                  var10.setLowerLevelAddress(var7);
               }
            } else {
               var10.setLowerLevelAddress(var7);
            }
         }
      }
   }

   @Override
   public void customInitStateRegisters(EState var1, Long var2) {
      var1.removeValue(1792);
      if (var2 != null) {
         var1.setValue(1600, var2);
      }
   }

   public static int pC(int var0) {
      return 0 + var0 * 64;
   }

   IEImm A(int var1) {
      return EUtil.zero(var1);
   }

   public IEVar kS(int var1) {
      if (var1 == 0) {
         return this.ED;
      } else {
         IEVar var2 = this.gCtx.getVar(8448 + (var1 - 1) * 64);
         return var2 != null ? var2 : null;
      }
   }

   public IEVar wS(int var1) {
      if (var1 == 0) {
         return this.Sc;
      } else {
         IEVar var2 = this.gCtx.getVar(8640 + (var1 - 1) * 64);
         return var2 != null ? var2 : null;
      }
   }

   @Override
   public String formatStatistics() {
      StringBuilder var1 = new StringBuilder(super.formatStatistics());
      var1.append("\n\n");
      Strings.ff(var1, "Converted instructions: %d (incl. failed=%d, untranslated=%d)\n", this.sO, this.os, this.Cu);
      if (this.mv != null && !this.mv.isEmpty()) {
         var1.append("Failed instructions: ").append(this.mv.formatTopReferences(-1));
      }

      if (this.hZ != null && !this.hZ.isEmpty()) {
         var1.append("Untranslated instructions: ").append(this.hZ.formatTopReferences(-1));
      }

      return var1.toString();
   }
}
