package com.pnfsoftware.jeb.core.units.code.asm.type;

import com.pnfsoftware.jeb.client.Licensing;
import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterBankArm;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterBankArm64;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterBankMips;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterBankMips64;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterBankX64;
import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterBankX86;
import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.core.units.codeobject.SubsystemType;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CallingConventionService {
   private static final ILogger logger = GlobalLog.getLogger(CallingConventionService.class);
   private static volatile CallingConventionService instance;
   private List cclist = new ArrayList();

   public static CallingConventionService getInstance() {
      if (instance == null) {
         synchronized (CallingConventionService.class) {
            if (instance == null) {
               CallingConventionService var1 = new CallingConventionService();
               instance = var1;
            }
         }
      }

      return instance;
   }

   private CallingConventionService() {
      this.createStandardConventions();
   }

   private void createStandardConventions() {
      long var2 = RegisterBankX86.getInstance().getDescriptionEntryByName("eax").getId();
      long var4 = RegisterBankX86.getInstance().getDescriptionEntryByName("ecx").getId();
      long var6 = RegisterBankX86.getInstance().getDescriptionEntryByName("edx").getId();
      long var8 = RegisterBankX86.getInstance().getDescriptionEntryByName("esp").getId();
      long var10 = RegisterBankX86.getInstance().getDescriptionEntryByName("st(0)").getId();
      CallingConventionBuilder var1 = new CallingConventionBuilder(CallingConventionName.CDECL, ProcessorType.X86)
         .addSpoiledRegisters(var2, var4, var6)
         .setReturnAddressSlot(StorageEntry.createStackSlot(0L))
         .addOutputSlot(StorageEntry.createRegister(var2))
         .addOutputRegisterPair(StorageEntry.createRegisterPair(var2, var6))
         .setOutputFpSlot(StorageEntry.createRegister(var10))
         .addInputSlot(StorageEntry.createStackSlot(1L))
         .addFlags(64)
         .addFlags(4096)
         .addFlags(256)
         .setIPRD(3, null, null);
      this.add(var1.build());
      var1 = new CallingConventionBuilder(CallingConventionName.STDCALL, ProcessorType.X86)
         .addFlags(1)
         .addSpoiledRegisters(var2, var4, var6, var8)
         .setReturnAddressSlot(StorageEntry.createStackSlot(0L))
         .addOutputSlot(StorageEntry.createRegister(var2))
         .addOutputRegisterPair(StorageEntry.createRegisterPair(var2, var6))
         .setOutputFpSlot(StorageEntry.createRegister(var10))
         .addInputSlot(StorageEntry.createStackSlot(1L))
         .addFlags(64)
         .addFlags(4096)
         .addFlags(256)
         .setIPRD(3, null, null);
      this.add(var1.build());
      var1 = new CallingConventionBuilder(CallingConventionName.THISCALL, ProcessorType.X86)
         .addFlags(1)
         .addSpoiledRegisters(var2, var4, var6, var8)
         .setReturnAddressSlot(StorageEntry.createStackSlot(0L))
         .addOutputSlot(StorageEntry.createRegister(var2))
         .addOutputRegisterPair(StorageEntry.createRegisterPair(var2, var6))
         .setOutputFpSlot(StorageEntry.createRegister(var10))
         .addInputSlot(StorageEntry.createRegister(var4))
         .addInputSlot(StorageEntry.createStackSlot(1L))
         .addFlags(64)
         .addFlags(4096)
         .addFlags(2048);
      this.add(var1.build());
      var1 = new CallingConventionBuilder(CallingConventionName.FASTCALL, ProcessorType.X86)
         .addSubsystemType(SubsystemType.WINDOWS)
         .addFlags(1)
         .addSpoiledRegisters(var2, var4, var6)
         .setReturnAddressSlot(StorageEntry.createStackSlot(0L))
         .addOutputSlot(StorageEntry.createRegister(var2))
         .addOutputRegisterPair(StorageEntry.createRegisterPair(var2, var6))
         .setOutputFpSlot(StorageEntry.createRegister(var10))
         .addInputSlot(StorageEntry.createRegister(var4))
         .addInputSlot(StorageEntry.createRegister(var6))
         .addInputSlot(StorageEntry.createStackSlot(1L))
         .addFlags(64)
         .addFlags(4096)
         .addFlags(256)
         .setIPRD(3, StorageEntry.createStackSlot(1L), null);
      this.add(var1.build());
      var1 = new CallingConventionBuilder(CallingConventionName.FASTCALL, ProcessorType.X86)
         .addSubsystemType(SubsystemType.LINUX)
         .addFlags(1)
         .addSpoiledRegisters(var2, var4, var6)
         .setReturnAddressSlot(StorageEntry.createStackSlot(0L))
         .addOutputSlot(StorageEntry.createRegister(var2))
         .addOutputRegisterPair(StorageEntry.createRegisterPair(var2, var6))
         .setOutputFpSlot(StorageEntry.createRegister(var10))
         .addInputSlot(StorageEntry.createRegister(var4))
         .addInputSlot(StorageEntry.createRegister(var6))
         .addInputSlot(StorageEntry.createStackSlot(1L))
         .addFlags(64)
         .addFlags(4096)
         .addFlags(32768)
         .addFlags(256)
         .setIPRD(3, StorageEntry.createStackSlot(1L), null);
      this.add(var1.build());
      var1 = new CallingConventionBuilder("__passthru", ProcessorType.X86).setReturnAddressSlot(StorageEntry.createStackSlot(0L));
      this.add(var1.build());
      long var12 = RegisterBankX64.getInstance().getDescriptionEntryByName("rax").getId();
      long var14 = RegisterBankX64.getInstance().getDescriptionEntryByName("rcx").getId();
      long var16 = RegisterBankX64.getInstance().getDescriptionEntryByName("rdx").getId();
      long var18 = RegisterBankX64.getInstance().getDescriptionEntryByName("r8").getId();
      long var20 = RegisterBankX64.getInstance().getDescriptionEntryByName("r9").getId();
      long var22 = RegisterBankX64.getInstance().getDescriptionEntryByName("r10").getId();
      long var24 = RegisterBankX64.getInstance().getDescriptionEntryByName("r11").getId();
      long var26 = RegisterBankX64.getInstance().getDescriptionEntryByName("xmm0").getId();
      long var28 = RegisterBankX64.getInstance().getDescriptionEntryByName("xmm1").getId();
      long var30 = RegisterBankX64.getInstance().getDescriptionEntryByName("xmm2").getId();
      long var32 = RegisterBankX64.getInstance().getDescriptionEntryByName("xmm3").getId();
      long var34 = RegisterBankX64.getInstance().getDescriptionEntryByName("xmm4").getId();
      long var36 = RegisterBankX64.getInstance().getDescriptionEntryByName("xmm5").getId();
      long var38 = RegisterBankX64.getInstance().getDescriptionEntryByName("xmm6").getId();
      long var40 = RegisterBankX64.getInstance().getDescriptionEntryByName("xmm7").getId();
      long[] var42 = new long[8];

      for (int var43 = 0; var43 < var42.length; var43++) {
         var42[var43] = RegisterBankX64.getInstance().getDescriptionEntryByName("xmm" + var43).getId();
      }

      var1 = new CallingConventionBuilder(CallingConventionName.STDCALL, ProcessorType.X86_64)
         .addAlternateName(CallingConventionName.CDECL.toString())
         .addAlternateName(CallingConventionName.FASTCALL.toString())
         .addAlternateName(CallingConventionName.THISCALL.toString())
         .addSubsystemType(SubsystemType.WINDOWS)
         .addSpoiledRegisters(var12, var14, var16, var18, var20, var22, var24)
         .addSpoiledRegisters(var42)
         .setReturnAddressSlot(StorageEntry.createStackSlot(0L))
         .addOutputSlot(StorageEntry.createRegister(var12))
         .setOutputFpSlot(StorageEntry.createRegister(var26))
         .addInputSlot(StorageEntry.createRegister(var14))
         .addInputSlot(StorageEntry.createRegister(var16))
         .addInputSlot(StorageEntry.createRegister(var18))
         .addInputSlot(StorageEntry.createRegister(var20))
         .addInputSlot(StorageEntry.createStackSlot(5L))
         .addInputFpSlot(StorageEntry.createRegister(var26))
         .addInputFpSlot(StorageEntry.createRegister(var28))
         .addInputFpSlot(StorageEntry.createRegister(var30))
         .addInputFpSlot(StorageEntry.createRegister(var32))
         .addFlags(1024)
         .addFlags(8192)
         .addFlags(256)
         .setIPRD(2, null, null);
      this.add(var1.build());
      long var284 = RegisterBankX64.getInstance().getDescriptionEntryByName("rsi").getId();
      long var45 = RegisterBankX64.getInstance().getDescriptionEntryByName("rdi").getId();
      var1 = new CallingConventionBuilder(CallingConventionName.CDECL, ProcessorType.X86_64)
         .addAlternateName(CallingConventionName.SYSVAMD64.toString())
         .addSubsystemType(SubsystemType.LINUX)
         .addSpoiledRegisters(var12, var14, var16, var284, var45, var18, var20, var22, var24)
         .addSpoiledRegisters(var42)
         .setReturnAddressSlot(StorageEntry.createStackSlot(0L))
         .addOutputSlot(StorageEntry.createRegister(var12))
         .addOutputRegisterPair(StorageEntry.createRegisterPair(var12, var16))
         .setOutputFpSlot(StorageEntry.createRegister(var26))
         .addInputSlot(StorageEntry.createRegister(var45))
         .addInputSlot(StorageEntry.createRegister(var284))
         .addInputSlot(StorageEntry.createRegister(var16))
         .addInputSlot(StorageEntry.createRegister(var14))
         .addInputSlot(StorageEntry.createRegister(var18))
         .addInputSlot(StorageEntry.createRegister(var20))
         .addInputFpSlot(StorageEntry.createRegister(var26))
         .addInputFpSlot(StorageEntry.createRegister(var28))
         .addInputFpSlot(StorageEntry.createRegister(var30))
         .addInputFpSlot(StorageEntry.createRegister(var32))
         .addInputFpSlot(StorageEntry.createRegister(var34))
         .addInputFpSlot(StorageEntry.createRegister(var36))
         .addInputFpSlot(StorageEntry.createRegister(var38))
         .addInputFpSlot(StorageEntry.createRegister(var40))
         .addInputSlot(StorageEntry.createStackSlot(1L))
         .addInputRegisterPair(StorageEntry.createRegisterPair(var45, var284))
         .addInputRegisterPair(StorageEntry.createRegisterPair(var284, var16))
         .addInputRegisterPair(StorageEntry.createRegisterPair(var16, var14))
         .addInputRegisterPair(StorageEntry.createRegisterPair(var14, var18))
         .addInputRegisterPair(StorageEntry.createRegisterPair(var18, var20))
         .addFlags(256)
         .setIPRD(3, null, null);
      this.add(var1.build());
      long var47 = RegisterBankArm.getInstance().getDescriptionEntryByName("R0").getId();
      long var49 = RegisterBankArm.getInstance().getDescriptionEntryByName("R1").getId();
      long var51 = RegisterBankArm.getInstance().getDescriptionEntryByName("R2").getId();
      long var53 = RegisterBankArm.getInstance().getDescriptionEntryByName("R3").getId();
      long var55 = RegisterBankArm.getInstance().getDescriptionEntryByName("LR").getId();
      var1 = new CallingConventionBuilder(CallingConventionName.CDECL, ProcessorType.ARM)
         .addAlternateName(CallingConventionName.ARM_A32.toString())
         .addSpoiledRegisters(var47, var49, var51, var53)
         .setReturnAddressSlot(StorageEntry.createRegister(var55))
         .addOutputSlot(StorageEntry.createRegister(var47))
         .addOutputRegisterPair(StorageEntry.createRegisterPairEndianDep(var47, var49))
         .addInputSlot(StorageEntry.createRegister(var47))
         .addInputSlot(StorageEntry.createRegister(var49))
         .addInputSlot(StorageEntry.createRegister(var51))
         .addInputSlot(StorageEntry.createRegister(var53))
         .addAlignementRequirement(2, 2)
         .addInputRegisterPair(StorageEntry.createRegisterPairEndianDep(var47, var49))
         .addInputRegisterPair(StorageEntry.createRegisterPairEndianDep(var51, var53))
         .addInputSlot(StorageEntry.createStackSlot(0L));
      this.add(var1.build());
      long var57 = RegisterBankArm.getInstance().getDescriptionEntryByName("S0").getId();
      long var59 = RegisterBankArm.getInstance().getDescriptionEntryByName("S1").getId();
      long var61 = RegisterBankArm.getInstance().getDescriptionEntryByName("S2").getId();
      long var63 = RegisterBankArm.getInstance().getDescriptionEntryByName("S3").getId();
      var1 = var1.duplicate()
         .setName(CallingConventionName.ARM_A32_HF.name(), true)
         .addInputFpSlot(StorageEntry.createRegister(var57))
         .addInputFpSlot(StorageEntry.createRegister(var59))
         .addInputFpSlot(StorageEntry.createRegister(var61))
         .addInputFpSlot(StorageEntry.createRegister(var63))
         .setOutputFpSlot(StorageEntry.createRegister(var57));
      this.add(var1.build());
      var1 = new CallingConventionBuilder(CallingConventionName.CDECL, ProcessorType.ARM)
         .addAlternateName(CallingConventionName.STDCALL.toString())
         .addAlternateName(CallingConventionName.FASTCALL.toString())
         .addAlternateName(CallingConventionName.VECTORCALL.toString())
         .addSubsystemType(SubsystemType.WINDOWS)
         .addSubsystemType(SubsystemType.WINDOWS_USER)
         .addSubsystemType(SubsystemType.WINDOWS_KERNEL)
         .addSpoiledRegisters(var47, var49, var51, var53)
         .setReturnAddressSlot(StorageEntry.createRegister(var55))
         .addOutputSlot(StorageEntry.createRegister(var47))
         .addOutputRegisterPair(StorageEntry.createRegisterPairEndianDep(var47, var49))
         .addInputSlot(StorageEntry.createRegister(var47))
         .addInputSlot(StorageEntry.createRegister(var49))
         .addInputSlot(StorageEntry.createRegister(var51))
         .addInputSlot(StorageEntry.createRegister(var53))
         .addAlignementRequirement(2, 2)
         .addInputRegisterPair(StorageEntry.createRegisterPairEndianDep(var47, var49))
         .addInputRegisterPair(StorageEntry.createRegisterPairEndianDep(var51, var53))
         .addInputSlot(StorageEntry.createStackSlot(0L));
      this.add(var1.build());
      var1 = var1.duplicate()
         .setName(CallingConventionName.ARM_A32_HF.name(), true)
         .addInputFpSlot(StorageEntry.createRegister(var57))
         .addInputFpSlot(StorageEntry.createRegister(var59))
         .addInputFpSlot(StorageEntry.createRegister(var61))
         .addInputFpSlot(StorageEntry.createRegister(var63))
         .setOutputFpSlot(StorageEntry.createRegister(var57));
      this.add(var1.build());
      long var65 = RegisterBankArm64.getInstance().getDescriptionEntryByName("X0").getId();
      long var67 = RegisterBankArm64.getInstance().getDescriptionEntryByName("X1").getId();
      long var69 = RegisterBankArm64.getInstance().getDescriptionEntryByName("X2").getId();
      long var71 = RegisterBankArm64.getInstance().getDescriptionEntryByName("X3").getId();
      long var73 = RegisterBankArm64.getInstance().getDescriptionEntryByName("X4").getId();
      long var75 = RegisterBankArm64.getInstance().getDescriptionEntryByName("X5").getId();
      long var77 = RegisterBankArm64.getInstance().getDescriptionEntryByName("X6").getId();
      long var79 = RegisterBankArm64.getInstance().getDescriptionEntryByName("X7").getId();
      long var81 = RegisterBankArm64.getInstance().getDescriptionEntryByName("X8").getId();
      long var83 = RegisterBankArm64.getInstance().getDescriptionEntryByName("X30").getId();
      long var85 = RegisterBankArm64.getInstance().getDescriptionEntryByName("V0").getId();
      long var87 = RegisterBankArm64.getInstance().getDescriptionEntryByName("V1").getId();
      long var89 = RegisterBankArm64.getInstance().getDescriptionEntryByName("V2").getId();
      long var91 = RegisterBankArm64.getInstance().getDescriptionEntryByName("V3").getId();
      long var93 = RegisterBankArm64.getInstance().getDescriptionEntryByName("V4").getId();
      long var95 = RegisterBankArm64.getInstance().getDescriptionEntryByName("V5").getId();
      long var97 = RegisterBankArm64.getInstance().getDescriptionEntryByName("V6").getId();
      long var99 = RegisterBankArm64.getInstance().getDescriptionEntryByName("V7").getId();
      var1 = new CallingConventionBuilder(CallingConventionName.CDECL, ProcessorType.ARM64)
         .addAlternateName(CallingConventionName.ARM_A64.toString())
         .addSpoiledRegisters(var65, var67, var69, var71, var73, var75, var77, var79)
         .setReturnAddressSlot(StorageEntry.createRegister(var83))
         .addOutputSlot(StorageEntry.createRegister(var65))
         .addOutputRegisterPair(StorageEntry.createRegisterPairEndianDep(var65, var67))
         .addInputSlot(StorageEntry.createRegister(var65))
         .addInputSlot(StorageEntry.createRegister(var67))
         .addInputSlot(StorageEntry.createRegister(var69))
         .addInputSlot(StorageEntry.createRegister(var71))
         .addInputSlot(StorageEntry.createRegister(var73))
         .addInputSlot(StorageEntry.createRegister(var75))
         .addInputSlot(StorageEntry.createRegister(var77))
         .addInputSlot(StorageEntry.createRegister(var79))
         .addInputRegisterPair(StorageEntry.createRegisterPairEndianDep(var65, var67))
         .addInputRegisterPair(StorageEntry.createRegisterPairEndianDep(var67, var69))
         .addInputRegisterPair(StorageEntry.createRegisterPairEndianDep(var69, var71))
         .addInputRegisterPair(StorageEntry.createRegisterPairEndianDep(var71, var73))
         .addInputRegisterPair(StorageEntry.createRegisterPairEndianDep(var73, var75))
         .addInputRegisterPair(StorageEntry.createRegisterPairEndianDep(var75, var77))
         .addInputRegisterPair(StorageEntry.createRegisterPairEndianDep(var77, var79))
         .addInputSlot(StorageEntry.createStackSlot(0L))
         .addInputFpSlot(StorageEntry.createRegister(var85))
         .addInputFpSlot(StorageEntry.createRegister(var87))
         .addInputFpSlot(StorageEntry.createRegister(var89))
         .addInputFpSlot(StorageEntry.createRegister(var91))
         .addInputFpSlot(StorageEntry.createRegister(var93))
         .addInputFpSlot(StorageEntry.createRegister(var95))
         .addInputFpSlot(StorageEntry.createRegister(var97))
         .addInputFpSlot(StorageEntry.createRegister(var99))
         .setOutputFpSlot(StorageEntry.createRegister(var85))
         .addFlags(16384)
         .addFlags(256)
         .setIPRD(3, StorageEntry.createRegister(var81), null);
      this.add(var1.build());
      var1 = new CallingConventionBuilder(CallingConventionName.CDECL, ProcessorType.ARM64)
         .addAlternateName(CallingConventionName.STDCALL.toString())
         .addAlternateName(CallingConventionName.FASTCALL.toString())
         .addAlternateName(CallingConventionName.VECTORCALL.toString())
         .addSubsystemType(SubsystemType.WINDOWS)
         .addSubsystemType(SubsystemType.WINDOWS_USER)
         .addSubsystemType(SubsystemType.WINDOWS_KERNEL)
         .addSpoiledRegisters(var65, var67, var69, var71, var73, var75, var77, var79)
         .setReturnAddressSlot(StorageEntry.createRegister(var83))
         .addOutputSlot(StorageEntry.createRegister(var65))
         .addOutputRegisterPair(StorageEntry.createRegisterPairEndianDep(var65, var67))
         .addInputSlot(StorageEntry.createRegister(var65))
         .addInputSlot(StorageEntry.createRegister(var67))
         .addInputSlot(StorageEntry.createRegister(var69))
         .addInputSlot(StorageEntry.createRegister(var71))
         .addInputSlot(StorageEntry.createRegister(var73))
         .addInputSlot(StorageEntry.createRegister(var75))
         .addInputSlot(StorageEntry.createRegister(var77))
         .addInputSlot(StorageEntry.createRegister(var79))
         .addInputRegisterPair(StorageEntry.createRegisterPairEndianDep(var65, var67))
         .addInputRegisterPair(StorageEntry.createRegisterPairEndianDep(var67, var69))
         .addInputRegisterPair(StorageEntry.createRegisterPairEndianDep(var69, var71))
         .addInputRegisterPair(StorageEntry.createRegisterPairEndianDep(var71, var73))
         .addInputRegisterPair(StorageEntry.createRegisterPairEndianDep(var73, var75))
         .addInputRegisterPair(StorageEntry.createRegisterPairEndianDep(var75, var77))
         .addInputRegisterPair(StorageEntry.createRegisterPairEndianDep(var77, var79))
         .addInputSlot(StorageEntry.createStackSlot(0L))
         .addInputFpSlot(StorageEntry.createRegister(var85))
         .addInputFpSlot(StorageEntry.createRegister(var87))
         .addInputFpSlot(StorageEntry.createRegister(var89))
         .addInputFpSlot(StorageEntry.createRegister(var91))
         .addInputFpSlot(StorageEntry.createRegister(var93))
         .addInputFpSlot(StorageEntry.createRegister(var95))
         .addInputFpSlot(StorageEntry.createRegister(var97))
         .addInputFpSlot(StorageEntry.createRegister(var99))
         .setOutputFpSlot(StorageEntry.createRegister(var85))
         .addFlags(16384)
         .addFlags(256)
         .setIPRD(3, StorageEntry.createRegister(var81), null);
      this.add(var1.build());
      long var101 = RegisterBankMips.getInstance().getDescriptionEntryByName("$v0").getId();
      long var103 = RegisterBankMips.getInstance().getDescriptionEntryByName("$v1").getId();
      long var105 = RegisterBankMips.getInstance().getDescriptionEntryByName("$a0").getId();
      long var107 = RegisterBankMips.getInstance().getDescriptionEntryByName("$a1").getId();
      long var109 = RegisterBankMips.getInstance().getDescriptionEntryByName("$a2").getId();
      long var111 = RegisterBankMips.getInstance().getDescriptionEntryByName("$a3").getId();
      long var113 = RegisterBankMips.getInstance().getDescriptionEntryByName("$t0").getId();
      long var115 = RegisterBankMips.getInstance().getDescriptionEntryByName("$t1").getId();
      long var117 = RegisterBankMips.getInstance().getDescriptionEntryByName("$t2").getId();
      long var119 = RegisterBankMips.getInstance().getDescriptionEntryByName("$t3").getId();
      long var121 = RegisterBankMips.getInstance().getDescriptionEntryByName("$t4").getId();
      long var123 = RegisterBankMips.getInstance().getDescriptionEntryByName("$t5").getId();
      long var125 = RegisterBankMips.getInstance().getDescriptionEntryByName("$t6").getId();
      long var127 = RegisterBankMips.getInstance().getDescriptionEntryByName("$t7").getId();
      long var129 = RegisterBankMips.getInstance().getDescriptionEntryByName("$t8").getId();
      long var131 = RegisterBankMips.getInstance().getDescriptionEntryByName("$t9").getId();
      long var133 = RegisterBankMips.getInstance().getDescriptionEntryByName("$ra").getId();
      long var135 = RegisterBankMips.getInstance().getDescriptionEntryByName("$f0").getId();
      long var137 = RegisterBankMips.getInstance().getDescriptionEntryByName("$f1").getId();
      long var139 = RegisterBankMips.getInstance().getDescriptionEntryByName("$f2").getId();
      long var141 = RegisterBankMips.getInstance().getDescriptionEntryByName("$f3").getId();
      long var143 = RegisterBankMips.getInstance().getDescriptionEntryByName("$f4").getId();
      long var145 = RegisterBankMips.getInstance().getDescriptionEntryByName("$f5").getId();
      long var147 = RegisterBankMips.getInstance().getDescriptionEntryByName("$f6").getId();
      long var149 = RegisterBankMips.getInstance().getDescriptionEntryByName("$f7").getId();
      long var151 = RegisterBankMips.getInstance().getDescriptionEntryByName("$f8").getId();
      long var153 = RegisterBankMips.getInstance().getDescriptionEntryByName("$f9").getId();
      long var155 = RegisterBankMips.getInstance().getDescriptionEntryByName("$f10").getId();
      long var157 = RegisterBankMips.getInstance().getDescriptionEntryByName("$f11").getId();
      long var159 = RegisterBankMips.getInstance().getDescriptionEntryByName("$f12").getId();
      long var161 = RegisterBankMips.getInstance().getDescriptionEntryByName("$f13").getId();
      long var163 = RegisterBankMips.getInstance().getDescriptionEntryByName("$f14").getId();
      long var165 = RegisterBankMips.getInstance().getDescriptionEntryByName("$f15").getId();
      long var167 = RegisterBankMips.getInstance().getDescriptionEntryByName("$f16").getId();
      long var169 = RegisterBankMips.getInstance().getDescriptionEntryByName("$f17").getId();
      long var171 = RegisterBankMips.getInstance().getDescriptionEntryByName("$f18").getId();
      long var173 = RegisterBankMips.getInstance().getDescriptionEntryByName("$f19").getId();
      var1 = new CallingConventionBuilder(CallingConventionName.MIPS_O32, ProcessorType.MIPS)
         .addSpoiledRegisters(var101, var103, var105, var107, var109, var111, var113, var115, var117, var119, var121, var123, var125, var127, var129, var131)
         .addSpoiledRegisters(
            var135,
            var137,
            var139,
            var141,
            var143,
            var145,
            var147,
            var149,
            var151,
            var153,
            var155,
            var157,
            var159,
            var161,
            var163,
            var165,
            var167,
            var169,
            var171,
            var173
         )
         .setReturnAddressSlot(StorageEntry.createRegister(var133))
         .addOutputSlot(StorageEntry.createRegister(var101))
         .addOutputRegisterPair(StorageEntry.createRegisterPairEndianDep(var101, var103))
         .addInputSlot(StorageEntry.createRegister(var105))
         .addInputSlot(StorageEntry.createRegister(var107))
         .addInputSlot(StorageEntry.createRegister(var109))
         .addInputSlot(StorageEntry.createRegister(var111))
         .setOutputFpSlot(StorageEntry.createRegister(var135))
         .addInputFpSlot(StorageEntry.createRegister(var159))
         .addInputFpSlot(StorageEntry.createRegister(var163))
         .addInputFpSlot(StorageEntry.createRegister(var109))
         .addInputFpSlot(StorageEntry.createRegister(var111))
         .addInputRegisterPair(StorageEntry.createRegisterPairEndianDep(var105, var107))
         .addInputRegisterPair(StorageEntry.createRegisterPairEndianDep(var109, var111))
         .addInputSlot(StorageEntry.createStackSlot(4L));
      this.add(var1.build());
      var1 = new CallingConventionBuilder(CallingConventionName.MIPS_N32, ProcessorType.MIPS)
         .addSpoiledRegisters(var101, var103, var105, var107, var109, var111, var113, var115, var117, var119, var121, var123, var125, var127, var129, var131)
         .addSpoiledRegisters(
            var135,
            var137,
            var139,
            var141,
            var143,
            var145,
            var147,
            var149,
            var151,
            var153,
            var155,
            var157,
            var159,
            var161,
            var163,
            var165,
            var167,
            var169,
            var171,
            var173
         )
         .setReturnAddressSlot(StorageEntry.createRegister(var133))
         .addOutputSlot(StorageEntry.createRegister(var101))
         .addOutputRegisterPair(StorageEntry.createRegisterPairEndianDep(var101, var103))
         .addInputSlot(StorageEntry.createRegister(var105))
         .addInputSlot(StorageEntry.createRegister(var107))
         .addInputSlot(StorageEntry.createRegister(var109))
         .addInputSlot(StorageEntry.createRegister(var111))
         .addInputSlot(StorageEntry.createRegister(var113))
         .addInputSlot(StorageEntry.createRegister(var115))
         .addInputSlot(StorageEntry.createRegister(var117))
         .addInputSlot(StorageEntry.createRegister(var119))
         .setOutputFpSlot(StorageEntry.createRegister(var135))
         .addInputFpSlot(StorageEntry.createRegister(var159))
         .addInputFpSlot(StorageEntry.createRegister(var161))
         .addInputFpSlot(StorageEntry.createRegister(var163))
         .addInputFpSlot(StorageEntry.createRegister(var165))
         .addInputFpSlot(StorageEntry.createRegister(var167))
         .addInputFpSlot(StorageEntry.createRegister(var169))
         .addInputFpSlot(StorageEntry.createRegister(var171))
         .addInputFpSlot(StorageEntry.createRegister(var173))
         .addInputRegisterPair(StorageEntry.createRegisterPairEndianDep(var105, var107))
         .addInputRegisterPair(StorageEntry.createRegisterPairEndianDep(var109, var111))
         .addInputSlot(StorageEntry.createStackSlot(0L));
      this.add(var1.build());
      long var183 = RegisterBankMips64.getInstance().getDescriptionEntryByName("$v0").getId();
      long var185 = RegisterBankMips64.getInstance().getDescriptionEntryByName("$v1").getId();
      long var187 = RegisterBankMips64.getInstance().getDescriptionEntryByName("$a0").getId();
      long var189 = RegisterBankMips64.getInstance().getDescriptionEntryByName("$a1").getId();
      long var191 = RegisterBankMips64.getInstance().getDescriptionEntryByName("$a2").getId();
      long var193 = RegisterBankMips64.getInstance().getDescriptionEntryByName("$a3").getId();
      long var195 = RegisterBankMips64.getInstance().getDescriptionEntryByName("$a4").getId();
      long var197 = RegisterBankMips64.getInstance().getDescriptionEntryByName("$a5").getId();
      long var199 = RegisterBankMips64.getInstance().getDescriptionEntryByName("$a6").getId();
      long var201 = RegisterBankMips64.getInstance().getDescriptionEntryByName("$a7").getId();
      long var203 = RegisterBankMips64.getInstance().getDescriptionEntryByName("$t4").getId();
      long var205 = RegisterBankMips64.getInstance().getDescriptionEntryByName("$t5").getId();
      long var207 = RegisterBankMips64.getInstance().getDescriptionEntryByName("$t6").getId();
      long var209 = RegisterBankMips64.getInstance().getDescriptionEntryByName("$t7").getId();
      long var211 = RegisterBankMips64.getInstance().getDescriptionEntryByName("$t8").getId();
      long var213 = RegisterBankMips64.getInstance().getDescriptionEntryByName("$t9").getId();
      long var215 = RegisterBankMips64.getInstance().getDescriptionEntryByName("$ra").getId();
      long var217 = RegisterBankMips64.getInstance().getDescriptionEntryByName("$f0").getId();
      long var219 = RegisterBankMips64.getInstance().getDescriptionEntryByName("$f1").getId();
      long var221 = RegisterBankMips64.getInstance().getDescriptionEntryByName("$f2").getId();
      long var223 = RegisterBankMips64.getInstance().getDescriptionEntryByName("$f3").getId();
      long var225 = RegisterBankMips64.getInstance().getDescriptionEntryByName("$f4").getId();
      long var227 = RegisterBankMips64.getInstance().getDescriptionEntryByName("$f5").getId();
      long var229 = RegisterBankMips64.getInstance().getDescriptionEntryByName("$f6").getId();
      long var231 = RegisterBankMips64.getInstance().getDescriptionEntryByName("$f7").getId();
      long var233 = RegisterBankMips64.getInstance().getDescriptionEntryByName("$f8").getId();
      long var235 = RegisterBankMips64.getInstance().getDescriptionEntryByName("$f9").getId();
      long var237 = RegisterBankMips64.getInstance().getDescriptionEntryByName("$f10").getId();
      long var239 = RegisterBankMips64.getInstance().getDescriptionEntryByName("$f11").getId();
      long var241 = RegisterBankMips64.getInstance().getDescriptionEntryByName("$f12").getId();
      long var243 = RegisterBankMips64.getInstance().getDescriptionEntryByName("$f13").getId();
      long var245 = RegisterBankMips64.getInstance().getDescriptionEntryByName("$f14").getId();
      long var247 = RegisterBankMips64.getInstance().getDescriptionEntryByName("$f15").getId();
      long var249 = RegisterBankMips64.getInstance().getDescriptionEntryByName("$f16").getId();
      long var251 = RegisterBankMips64.getInstance().getDescriptionEntryByName("$f17").getId();
      long var253 = RegisterBankMips64.getInstance().getDescriptionEntryByName("$f18").getId();
      long var255 = RegisterBankMips64.getInstance().getDescriptionEntryByName("$f19").getId();
      long var257 = RegisterBankMips64.getInstance().getDescriptionEntryByName("$f20").getId();
      long var259 = RegisterBankMips64.getInstance().getDescriptionEntryByName("$f21").getId();
      long var261 = RegisterBankMips64.getInstance().getDescriptionEntryByName("$f22").getId();
      long var263 = RegisterBankMips64.getInstance().getDescriptionEntryByName("$f23").getId();
      var1 = new CallingConventionBuilder(CallingConventionName.MIPS_N64, ProcessorType.MIPS64)
         .addSpoiledRegisters(var183, var185, var187, var189, var191, var193, var195, var197, var199, var201, var203, var205, var207, var209, var211, var213)
         .addSpoiledRegisters(
            var217,
            var219,
            var221,
            var223,
            var225,
            var227,
            var229,
            var231,
            var233,
            var235,
            var237,
            var239,
            var241,
            var243,
            var245,
            var247,
            var249,
            var251,
            var253,
            var255,
            var257,
            var259,
            var261,
            var263
         )
         .setReturnAddressSlot(StorageEntry.createRegister(var215))
         .addOutputSlot(StorageEntry.createRegister(var183))
         .addOutputRegisterPair(StorageEntry.createRegisterPairEndianDep(var183, var185))
         .addInputSlot(StorageEntry.createRegister(var187))
         .addInputSlot(StorageEntry.createRegister(var189))
         .addInputSlot(StorageEntry.createRegister(var191))
         .addInputSlot(StorageEntry.createRegister(var193))
         .addInputSlot(StorageEntry.createRegister(var195))
         .addInputSlot(StorageEntry.createRegister(var197))
         .addInputSlot(StorageEntry.createRegister(var199))
         .addInputSlot(StorageEntry.createRegister(var201))
         .setOutputFpSlot(StorageEntry.createRegister(var217))
         .addInputFpSlot(StorageEntry.createRegister(var241))
         .addInputFpSlot(StorageEntry.createRegister(var243))
         .addInputFpSlot(StorageEntry.createRegister(var245))
         .addInputFpSlot(StorageEntry.createRegister(var247))
         .addInputFpSlot(StorageEntry.createRegister(var249))
         .addInputFpSlot(StorageEntry.createRegister(var251))
         .addInputFpSlot(StorageEntry.createRegister(var253))
         .addInputFpSlot(StorageEntry.createRegister(var255))
         .addInputRegisterPair(StorageEntry.createRegisterPairEndianDep(var187, var189))
         .addInputRegisterPair(StorageEntry.createRegisterPairEndianDep(var191, var193))
         .addInputSlot(StorageEntry.createStackSlot(0L));
      this.add(var1.build());
      var1 = new CallingConventionBuilder(CallingConventionName.CDECL, ProcessorType.AVR)
         .setReturnAddressSlot(StorageEntry.createStackSlot(0L))
         .addInputSlot(StorageEntry.createRegister(24L))
         .addInputRegisterPair(StorageEntry.createRegisterPair(24L, 25L))
         .addInputSlot(StorageEntry.createRegister(22L))
         .addInputRegisterPair(StorageEntry.createRegisterPair(22L, 23L))
         .addInputSlot(StorageEntry.createRegister(20L))
         .addInputRegisterPair(StorageEntry.createRegisterPair(20L, 21L))
         .addInputSlot(StorageEntry.createRegister(18L))
         .addInputRegisterPair(StorageEntry.createRegisterPair(18L, 19L))
         .addInputSlot(StorageEntry.createStackSlot(1L))
         .addOutputSlot(StorageEntry.createRegister(24L))
         .addOutputRegisterPair(StorageEntry.createRegisterPair(24L, 25L));
      this.add(var1.build());
      var1 = new CallingConventionBuilder("__godecl", ProcessorType.UNKNOWN)
         .setReturnAddressSlot(StorageEntry.createStackSlot(0L))
         .addInputSlot(StorageEntry.createStackSlot(1L))
         .addOutputSlot(StorageEntry.createStackSlot(1L))
         .addFlags(32);
      this.add(var1.build());
      var1 = new CallingConventionBuilder(CallingConventionName.UNKNOWN, ProcessorType.UNKNOWN).addFlags(1);
      this.add(var1.build());
      if (!Licensing.isReleaseBuild()) {
         ;
      }
   }

   private void add(ICallingConvention var1) {
      if (!this.addConvention(var1)) {
         throw new RuntimeException("CC collision!");
      } else {
         ICallingConvention var2 = var1.getIPRDConvention();
         if (var2 != null) {
            this.addConvention(var2);
         }
      }
   }

   public List getConventions() {
      return Collections.unmodifiableList(this.cclist);
   }

   public boolean addConvention(ICallingConvention var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         long var2 = var1.getIdentifierKey();

         for (ICallingConvention var5 : this.cclist) {
            if (var5.getIdentifierKey() == var2) {
               logger.error(S.L("Calling convention cannot be registered: %s. It collides with: %s."), var1, var5);
               return false;
            }
         }

         this.cclist.add(var1);
         return true;
      }
   }

   public boolean removeConvention(ICallingConvention var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         return this.cclist.remove(var1);
      }
   }

   @Override
   public String toString() {
      return Strings.ff("ccsvc(%d)", this.cclist.size());
   }
}
