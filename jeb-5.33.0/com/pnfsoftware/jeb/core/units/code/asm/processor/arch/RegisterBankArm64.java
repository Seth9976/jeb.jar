package com.pnfsoftware.jeb.core.units.code.asm.processor.arch;

import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterType;
import com.pnfsoftware.jeb.util.collect.ArrayUtil;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.Map;
import java.util.TreeMap;

public class RegisterBankArm64 extends AbstractRegisterBank {
   public static final int regGrp_GP = 0;
   public static final int regGrp_COPROC = 4;
   public static final int regGrp_SYS64 = 9;
   public static final int regGrp_SIMD = 6;
   public static final int regGrp_SIMDV = 7;
   public static final int regGrp_IP = 10;
   public static final int regGrp_Flags = 11;
   public static final int regGrp_Z = 12;
   public static final int regGrp_P = 13;
   public static final int regGrp_ZA = 14;
   public static final int regGrp_PN = 15;
   private static final Map entries = new TreeMap();
   public static final int PC_ID = 0;
   public static final int ZR_ID = 33;
   public static final int ZA_ID = 16;
   private static final String[][] _2_0_0 = new String[][]{
      {null, null, "OSDTRRX_EL1"},
      null,
      {"MDCCINT_EL1", null, "MDSCR_EL1"},
      {null, null, "OSDTRTX_EL1"},
      {null, null, "MDSELR_EL1"},
      {null, null, "MDSTEPOP_EL1"},
      {null, null, "OSECCR_EL1"}
   };
   private static final String[][] _2_0_1 = new String[][]{
      {"MDRAR_EL1", null, null, null, "OSLAR_EL1"},
      {null, null, null, null, "OSLSR_EL1"},
      null,
      {null, null, null, null, "OSDLR_EL1"},
      {null, null, null, null, "DBGPRCR_EL1"}
   };
   private static final String[][] _2_0_7 = new String[][]{
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      {null, null, null, null, null, null, "DBGCLAIMSET_EL1"},
      {null, null, null, null, null, null, "DBGCLAIMCLR_EL1"},
      null,
      null,
      null,
      null,
      {null, null, null, null, null, null, "DBGAUTHSTATUS_EL1"}
   };
   private static final String[][] _2_0_9 = new String[][]{
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      {"SPMCGCR0_EL1", "SPMCGCR1_EL1", null, "SPMACCESSR_EL1", "SPMIIDR_EL1", "SPMDEVARCH_EL1", "SPMDEVAFF_EL1", "SPMCFGR_EL1"},
      {null, "SPMINTENSET_EL1", "SPMINTENCLR_EL1"}
   };
   private static final String[][] _2_0_14 = new String[][]{
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      {"PMEVCNTSVR0_EL1", "PMEVCNTSVR1_EL1", "PMEVCNTSVR2_EL1", "PMEVCNTSVR3_EL1", "PMEVCNTSVR4_EL1", "PMEVCNTSVR5_EL1", "PMEVCNTSVR6_EL1", "PMEVCNTSVR7_EL1"},
      {
            "PMEVCNTSVR8_EL1",
            "PMEVCNTSVR9_EL1",
            "PMEVCNTSVR10_EL1",
            "PMEVCNTSVR11_EL1",
            "PMEVCNTSVR12_EL1",
            "PMEVCNTSVR13_EL1",
            "PMEVCNTSVR14_EL1",
            "PMEVCNTSVR15_EL1"
      },
      {
            "PMEVCNTSVR16_EL1",
            "PMEVCNTSVR17_EL1",
            "PMEVCNTSVR18_EL1",
            "PMEVCNTSVR19_EL1",
            "PMEVCNTSVR20_EL1",
            "PMEVCNTSVR21_EL1",
            "PMEVCNTSVR22_EL1",
            "PMEVCNTSVR23_EL1"
      },
      {
            "PMEVCNTSVR24_EL1",
            "PMEVCNTSVR25_EL1",
            "PMEVCNTSVR26_EL1",
            "PMEVCNTSVR27_EL1",
            "PMEVCNTSVR28_EL1",
            "PMEVCNTSVR29_EL1",
            "PMEVCNTSVR30_EL1",
            "PMCCNTSVR_EL1"
      },
      {"PMICNTSVR_EL1"}
   };
   private static final String[][] _2_1_0 = new String[][]{
      {null, "TRCTRACEIDR", "TRCVICTLR", null, "TRCSEQEVR0", "TRCCNTRLDVR0", "TRCIDR8", "TRCIMSPEC0"},
      {"TRCPRGCTLR", "TRCQCTLR", "TRCVIIECTLR", null, "TRCSEQEVR1", "TRCCNTRLDVR1", "TRCIDR9", "TRCIMSPEC1"},
      {"TRCPROCSELR", "TRCITEEDCR", "TRCVISSCTLR", null, "TRCSEQEVR2", "TRCCNTRLDVR2", "TRCIDR10", "TRCIMSPEC2"},
      {"TRCSTATR", null, "TRCVIPCSSCTLR", null, "TRCSEQEVR3", "TRCCNTRLDVR3", "TRCIDR11", "TRCIMSPEC3"},
      {"TRCCONFIGR", null, null, null, null, "TRCCNTCTLR0", "TRCIDR12", "TRCIMSPEC4"},
      {null, null, null, null, null, "TRCCNTCTLR1", "TRCIDR13", "TRCIMSPEC5"},
      {"TRCAUXCTLR", null, null, null, "TRCSEQRSTEVR", "TRCCNTCTLR2", null, "TRCIMSPEC6"},
      {null, null, null, null, "TRCSEQSTR", "TRCCNTCTLR3", null, "TRCIMSPEC7"},
      {"TRCEVENTCTL0R", null, "TRCVDCTLR", null, "TRCEXTINSELR", "TRCCNTVR0", null, "TRCIDR0"},
      {"TRCEVENTCTL1R", null, "TRCVDSACCTLR", null, "TRCEXTINSELR1", "TRCCNTVR1", null, "TRCIDR1"},
      {"TRCRSR", null, "TRCVDARCCTLR", null, "TRCEXTINSELR2", "TRCCNTVR2", null, "TRCIDR2"},
      {"TRCSTALLCTLR", null, null, null, "TRCEXTINSELR3", "TRCCNTVR3", null, "TRCIDR3"},
      {"TRCTSCTLR", null, null, null, null, null, null, "TRCIDR4"},
      {"TRCSYNCPR", null, null, null, null, null, null, "TRCIDR5"},
      {"TRCCCCTLR", null, null, null, null, null, null, "TRCIDR6"},
      {"TRCBBCTLR", null, null, null, null, null, null, "TRCIDR7"}
   };
   private static final String[][] _2_1_1 = new String[][]{
      {"TRCRSCTLR0", "TRCRSCTLR16", "TRCSSCCR0", "TRCSSPCICR0", "TRCOSLAR"},
      {"TRCRSCTLR1", "TRCRSCTLR17", "TRCSSCCR1", "TRCSSPCICR1", "TRCOSLSR"},
      {"TRCRSCTLR2", "TRCRSCTLR18", "TRCSSCCR2", "TRCSSPCICR2"},
      {"TRCRSCTLR3", "TRCRSCTLR19", "TRCSSCCR3", "TRCSSPCICR3"},
      {"TRCRSCTLR4", "TRCRSCTLR20", "TRCSSCCR4", "TRCSSPCICR4", "TRCPDCR"},
      {"TRCRSCTLR5", "TRCRSCTLR21", "TRCSSCCR5", "TRCSSPCICR5", "TRCPDSR"},
      {"TRCRSCTLR6", "TRCRSCTLR22", "TRCSSCCR6", "TRCSSPCICR6"},
      {"TRCRSCTLR7", "TRCRSCTLR23", "TRCSSCCR7", "TRCSSPCICR7"},
      {"TRCRSCTLR8", "TRCRSCTLR24", "TRCSSCSR0"},
      {"TRCRSCTLR9", "TRCRSCTLR25", "TRCSSCSR1"},
      {"TRCRSCTLR10", "TRCRSCTLR26", "TRCSSCSR2"},
      {"TRCRSCTLR11", "TRCRSCTLR27", "TRCSSCSR3"},
      {"TRCRSCTLR12", "TRCRSCTLR28", "TRCSSCSR4"},
      {"TRCRSCTLR13", "TRCRSCTLR29", "TRCSSCSR5"},
      {"TRCRSCTLR14", "TRCRSCTLR30", "TRCSSCSR6"},
      {"TRCRSCTLR15", "TRCRSCTLR31", "TRCSSCSR7"}
   };
   private static final String[][] _2_1_2 = new String[][]{
      {"TRCACVR0", "TRCACVR8", "TRCACATR0", "TRCACATR8", "TRCDVCVR0", "TRCDVCVR4", "TRCDVCMR0", "TRCDVCMR4"},
      null,
      {"TRCACVR1", "TRCACVR9", "TRCACATR1", "TRCACATR9"},
      null,
      {"TRCACVR2", "TRCACVR10", "TRCACATR2", "TRCACATR10", "TRCDVCVR1", "TRCDVCVR5", "TRCDVCMR1", "TRCDVCMR5"},
      null,
      {"TRCACVR3", "TRCACVR11", "TRCACATR3", "TRCACATR11"},
      null,
      {"TRCACVR4", "TRCACVR12", "TRCACATR4", "TRCACATR12", "TRCDVCVR2", "TRCDVCVR6", "TRCDVCMR2", "TRCDVCMR6"},
      null,
      {"TRCACVR5", "TRCACVR13", "TRCACATR5", "TRCACATR13"},
      null,
      {"TRCACVR6", "TRCACVR14", "TRCACATR6", "TRCACATR14", "TRCDVCVR3", "TRCDVCVR7", "TRCDVCMR3", "TRCDVCMR7"},
      null,
      {"TRCACVR7", "TRCACVR15", "TRCACATR7", "TRCACATR15"},
      null
   };
   private static final String[][] _2_1_3 = new String[][]{
      {"TRCCIDCVR0", "TRCVMIDCVR0", "TRCCIDCCTLR0"},
      {null, null, "TRCCIDCCTLR1"},
      {"TRCCIDCVR1", "TRCVMIDCVR1", "TRCVMIDCCTLR0"},
      {null, null, "TRCVMIDCCTLR1"},
      {"TRCCIDCVR2", "TRCVMIDCVR2"},
      null,
      {"TRCCIDCVR3", "TRCVMIDCVR3"},
      null,
      {"TRCCIDCVR4", "TRCVMIDCVR4"},
      null,
      {"TRCCIDCVR5", "TRCVMIDCVR5"},
      null,
      {"TRCCIDCVR6", "TRCVMIDCVR6"},
      null,
      {"TRCCIDCVR7", "TRCVMIDCVR7"}
   };
   private static final String[][] _2_1_7 = new String[][]{
      {null, null, null, null, "TRCITCTRL"},
      null,
      {null, null, null, null, null, null, null, "TRCDEVID"},
      {null, null, null, null, null, null, null, "TRCDEVTYPE"},
      {null, null, null, null, null, null, null, "TRCPIDR4"},
      {null, null, null, null, null, null, null, "TRCPIDR5"},
      {null, null, null, null, null, null, null, "TRCPIDR6"},
      {null, null, null, null, null, null, null, "TRCPIDR7"},
      {null, null, null, null, null, null, "TRCCLAIMSET", "TRCPIDR0"},
      {null, null, null, null, null, null, "TRCCLAIMCLR", "TRCPIDR1"},
      {null, null, null, null, null, null, "TRCDEVAFF0", "TRCPIDR2"},
      {null, null, null, null, null, null, "TRCDEVAFF1", "TRCPIDR3"},
      {null, null, null, null, null, null, "TRCLAR", "TRCCIDR0"},
      {null, null, null, null, null, null, "TRCLSR", "TRCCIDR1"},
      {null, null, null, null, null, null, "TRCAUTHSTATUS", "TRCCIDR2"},
      {null, null, null, null, null, null, "TRCDEVARCH", "TRCCIDR3"}
   };
   private static final String[][] _2_1_8 = new String[][]{
      {"BRBINF0_EL1", "BRBSRC0_EL1", "BRBTGT0_EL1", null, "BRBINF16_EL1", "BRBSRC16_EL1", "BRBTGT16_EL1"},
      {"BRBINF1_EL1", "BRBSRC1_EL1", "BRBTGT1_EL1", null, "BRBINF17_EL1", "BRBSRC17_EL1", "BRBTGT17_EL1"},
      {"BRBINF2_EL1", "BRBSRC2_EL1", "BRBTGT2_EL1", null, "BRBINF18_EL1", "BRBSRC18_EL1", "BRBTGT18_EL1"},
      {"BRBINF3_EL1", "BRBSRC3_EL1", "BRBTGT3_EL1", null, "BRBINF19_EL1", "BRBSRC19_EL1", "BRBTGT19_EL1"},
      {"BRBINF4_EL1", "BRBSRC4_EL1", "BRBTGT4_EL1", null, "BRBINF20_EL1", "BRBSRC20_EL1", "BRBTGT20_EL1"},
      {"BRBINF5_EL1", "BRBSRC5_EL1", "BRBTGT5_EL1", null, "BRBINF21_EL1", "BRBSRC21_EL1", "BRBTGT21_EL1"},
      {"BRBINF6_EL1", "BRBSRC6_EL1", "BRBTGT6_EL1", null, "BRBINF22_EL1", "BRBSRC22_EL1", "BRBTGT22_EL1"},
      {"BRBINF7_EL1", "BRBSRC7_EL1", "BRBTGT7_EL1", null, "BRBINF23_EL1", "BRBSRC23_EL1", "BRBTGT23_EL1"},
      {"BRBINF8_EL1", "BRBSRC8_EL1", "BRBTGT8_EL1", null, "BRBINF24_EL1", "BRBSRC24_EL1", "BRBTGT24_EL1"},
      {"BRBINF9_EL1", "BRBSRC9_EL1", "BRBTGT9_EL1", null, "BRBINF25_EL1", "BRBSRC25_EL1", "BRBTGT25_EL1"},
      {"BRBINF10_EL1", "BRBSRC10_EL1", "BRBTGT10_EL1", null, "BRBINF26_EL1", "BRBSRC26_EL1", "BRBTGT26_EL1"},
      {"BRBINF11_EL1", "BRBSRC11_EL1", "BRBTGT11_EL1", null, "BRBINF27_EL1", "BRBSRC27_EL1", "BRBTGT27_EL1"},
      {"BRBINF12_EL1", "BRBSRC12_EL1", "BRBTGT12_EL1", null, "BRBINF28_EL1", "BRBSRC28_EL1", "BRBTGT28_EL1"},
      {"BRBINF13_EL1", "BRBSRC13_EL1", "BRBTGT13_EL1", null, "BRBINF29_EL1", "BRBSRC29_EL1", "BRBTGT29_EL1"},
      {"BRBINF14_EL1", "BRBSRC14_EL1", "BRBTGT14_EL1", null, "BRBINF30_EL1", "BRBSRC30_EL1", "BRBTGT30_EL1"},
      {"BRBINF15_EL1", "BRBSRC15_EL1", "BRBTGT15_EL1", null, "BRBINF31_EL1", "BRBSRC31_EL1", "BRBTGT31_EL1"}
   };
   private static final String[][] _2_1_9 = new String[][]{
      {"BRBCR_EL1", "BRBFCR_EL1", "BRBTS_EL1"}, {"BRBINFINJ_EL1", "BRBSRCINJ_EL1", "BRBTGTINJ_EL1"}, {"BRBIDR0_EL1"}
   };
   private static final String[][] _2_3_0 = new String[][]{null, {"MDCCSR_EL0"}, null, null, {"DBGDTR_EL0"}, {"DBGDTRRX_EL0"}};
   private static final String[][] _2_3_9 = new String[][]{
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      {"SPMCR_EL0", "SPMCNTENSET_EL0", "SPMCNTENCLR_EL0", "SPMOVSCLR_EL0", "SPMZR_EL0", "SPMSELR_EL0"},
      null,
      {null, null, null, "SPMOVSSET_EL0"}
   };
   private static final String[][] _2_3_14 = new String[][]{
      {"SPMEVCNTR0_EL0", "SPMEVCNTR1_EL0", "SPMEVCNTR2_EL0", "SPMEVCNTR3_EL0", "SPMEVCNTR4_EL0", "SPMEVCNTR5_EL0", "SPMEVCNTR6_EL0", "SPMEVCNTR7_EL0"},
      {"SPMEVCNTR8_EL0", "SPMEVCNTR9_EL0", "SPMEVCNTR10_EL0", "SPMEVCNTR11_EL0", "SPMEVCNTR12_EL0", "SPMEVCNTR13_EL0", "SPMEVCNTR14_EL0", "SPMEVCNTR15_EL0"},
      {"SPMEVTYPER0_EL0", "SPMEVTYPER1_EL0", "SPMEVTYPER2_EL0", "SPMEVTYPER3_EL0", "SPMEVTYPER4_EL0", "SPMEVTYPER5_EL0", "SPMEVTYPER6_EL0", "SPMEVTYPER7_EL0"},
      {
            "SPMEVTYPER8_EL0",
            "SPMEVTYPER9_EL0",
            "SPMEVTYPER10_EL0",
            "SPMEVTYPER11_EL0",
            "SPMEVTYPER12_EL0",
            "SPMEVTYPER13_EL0",
            "SPMEVTYPER14_EL0",
            "SPMEVTYPER15_EL0"
      },
      {"SPMEVFILTR0_EL0", "SPMEVFILTR1_EL0", "SPMEVFILTR2_EL0", "SPMEVFILTR3_EL0", "SPMEVFILTR4_EL0", "SPMEVFILTR5_EL0", "SPMEVFILTR6_EL0", "SPMEVFILTR7_EL0"},
      {
            "SPMEVFILTR8_EL0",
            "SPMEVFILTR9_EL0",
            "SPMEVFILTR10_EL0",
            "SPMEVFILTR11_EL0",
            "SPMEVFILTR12_EL0",
            "SPMEVFILTR13_EL0",
            "SPMEVFILTR14_EL0",
            "SPMEVFILTR15_EL0"
      },
      {
            "SPMEVFILT2R0_EL0",
            "SPMEVFILT2R1_EL0",
            "SPMEVFILT2R2_EL0",
            "SPMEVFILT2R3_EL0",
            "SPMEVFILT2R4_EL0",
            "SPMEVFILT2R5_EL0",
            "SPMEVFILT2R6_EL0",
            "SPMEVFILT2R7_EL0"
      },
      {
            "SPMEVFILT2R8_EL0",
            "SPMEVFILT2R9_EL0",
            "SPMEVFILT2R10_EL0",
            "SPMEVFILT2R11_EL0",
            "SPMEVFILT2R12_EL0",
            "SPMEVFILT2R13_EL0",
            "SPMEVFILT2R14_EL0",
            "SPMEVFILT2R15_EL0"
      }
   };
   private static final String[][] _2_4_0 = new String[][]{null, null, null, null, null, null, null, {"DBGVCR32_EL2"}};
   private static final String[][] _2_4_9 = new String[][]{
      {"BRBCR_EL2"}, null, null, null, null, null, null, null, null, null, null, null, null, {null, null, null, "SPMACCESSR_EL2"}
   };
   private static final String[][] _2_5_9 = new String[][]{
      {"BRBCR_EL12"}, null, null, null, null, null, null, null, null, null, null, null, null, {null, null, null, "SPMACCESSR_EL12"}
   };
   private static final String[][] _2_6_9 = new String[][]{
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      {null, null, null, "SPMACCESSR_EL3"},
      {null, null, null, null, null, null, null, "SPMROOTCR_EL3"}
   };
   private static final String[][] _2_7_9 = new String[][]{
      null, null, null, null, null, null, null, null, null, null, null, null, null, null, {null, null, null, null, null, null, null, "SPMSCR_EL1"}
   };
   private static final String[][] _sysRegs_3_0_0 = new String[][]{
      {"MIDR_EL1", null, null, null, "MPUIR_EL1", "MPIDR_EL1", "REVIDR_EL1"},
      {"ID_PFR0_EL1", "ID_PFR1_EL1", "ID_DFR0_EL1", "ID_AFR0_EL1", "ID_MMFR0_EL1", "ID_MMFR1_EL1", "ID_MMFR2_EL1", "ID_MMFR3_EL1"},
      {"ID_ISAR0_EL1", "ID_ISAR1_EL1", "ID_ISAR2_EL1", "ID_ISAR3_EL1", "ID_ISAR4_EL1", "ID_ISAR5_EL1", "ID_MMFR4_EL1", "ID_ISAR6_EL1"},
      {"MVFR0_EL1", "MVFR1_EL1", "MVFR2_EL1", null, "ID_PFR2_EL1", "ID_DFR1_EL1", "ID_MMFR5_EL1"},
      {"ID_AA64PFR0_EL1", "ID_AA64PFR1_EL1", "ID_AA64PFR2_EL1", null, "ID_AA64ZFR0_EL1", "ID_AA64SMFR0_EL1", null, "ID_AA64FPFR0_EL1"},
      {"ID_AA64DFR0_EL1", "ID_AA64DFR1_EL1", "ID_AA64DFR2_EL1", null, "ID_AA64AFR0_EL1", "ID_AA64AFR1_EL1"},
      {"ID_AA64ISAR0_EL1", "ID_AA64ISAR1_EL1", "ID_AA64ISAR2_EL1", "ID_AA64ISAR3_EL1"},
      {"ID_AA64MMFR0_EL1", "ID_AA64MMFR1_EL1", "ID_AA64MMFR2_EL1", "ID_AA64MMFR3_EL1", "ID_AA64MMFR4_EL1"}
   };
   private static final String[][] _3_0_1 = new String[][]{
      {"SCTLR_EL1", "ACTLR_EL1", "CPACR_EL1", "SCTLR2_EL1", null, "RGSR_EL1", "GCR_EL1"},
      null,
      {"ZCR_EL1", "TRFCR_EL1", null, "TRCITECR_EL1", "SMPRI_EL1", null, "SMCR_EL1"},
      null,
      {"SCTLRMASK_EL1", "ACTLRMASK_EL1", "CPACRMASK_EL1", "SCTLR2MASK_EL1", "CPACRALIAS_EL1", "ACTLRALIAS_EL1", "SCTLRALIAS_EL1", "SCTLR2ALIAS_EL1"}
   };
   private static final String[][] _3_0_2 = new String[][]{
      {"TTBR0_EL1", "TTBR1_EL1", "TCR_EL1", "TCR2_EL1"},
      {"APIAKeyLo_EL1", "APIAKeyHi_EL1", "APIBKeyLo_EL1", "APIBKeyHi_EL1"},
      {"APDAKeyLo_EL1", "APDAKeyHi_EL1", "APDBKeyLo_EL1", "APDBKeyHi_EL1"},
      {"APGAKeyLo_EL1", "APGAKeyHi_EL1"},
      null,
      {"GCSCR_EL1", "GCSPR_EL1", "GCSCRE0_EL1"}
   };
   private static final String[][] _special_3_0_4 = new String[][]{
      {"SPSR_EL1", "ELR_EL1"}, {"SP_EL0"}, {"SPSel", null, "CurrentEL", "PAN", "UAO"}, {"ALLINT", "PM"}, null, null, {"ICC_PMR_EL1"}
   };
   private static final String[][] _3_0_5 = new String[][]{
      null,
      {"AFSR0_EL1", "AFSR1_EL1"},
      {"ESR_EL1"},
      {"ERRIDR_EL1", "ERRSELR_EL1", "ERXGSR_EL1"},
      {"ERXFR_EL1", "ERXCTLR_EL1", "ERXSTATUS_EL1", "ERXADDR_EL1", "ERXPFGF_EL1", "ERXPFGCTL_EL1", "ERXPFGCDN_EL1"},
      {"ERXMISC0_EL1", "ERXMISC1_EL1", "ERXMISC2_EL1", "ERXMISC3_EL1"},
      {"TFSR_EL1", "TFSRE0_EL1"}
   };
   private static final String[][] _3_0_6 = new String[][]{
      {"FAR_EL1", null, null, null, null, "PFAR_EL1"},
      {null, "PRENR_EL1"},
      {null, "PRSELR_EL1"},
      null,
      null,
      null,
      null,
      null,
      {"PRBAR_EL1", "PRLAR_EL1", null, null, "PRBAR1_EL1", "PRLAR1_EL1"},
      {"PRBAR2_EL1", "PRLAR2_EL1", null, null, "PRBAR3_EL1", "PRLAR3_EL1"},
      {"PRBAR4_EL1", "PRLAR4_EL1", null, null, "PRBAR5_EL1", "PRLAR5_EL1"},
      {"PRBAR6_EL1", "PRLAR6_EL1", null, null, "PRBAR7_EL1", "PRLAR7_EL1"},
      {"PRBAR8_EL1", "PRLAR8_EL1", null, null, "PRBAR9_EL1", "PRLAR9_EL1"},
      {"PRBAR10_EL1", "PRLAR10_EL1", null, null, "PRBAR11_EL1", "PRLAR11_EL1"},
      {"PRBAR12_EL1", "PRLAR12_EL1", null, null, "PRBAR13_EL1", "PRLAR13_EL1"},
      {"PRBAR14_EL1", "PRLAR14_EL1", null, null, "PRBAR15_EL1", "PRLAR15_EL1"}
   };
   private static final String[][] _3_0_7 = new String[][]{null, null, null, null, {"PAR_EL1"}};
   private static final String[][] _3_0_9 = new String[][]{
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      null,
      {"PMSCR_EL1", "PMSNEVFR_EL1", "PMSICR_EL1", "PMSIRR_EL1", "PMSFCR_EL1", "PMSEVFR_EL1", "PMSLATFR_EL1", "PMSIDR_EL1"},
      {"PMBLIMITR_EL1", "PMBPTR_EL1", null, "PMBSR_EL1", "PMSDSFR_EL1", "PMBMAR_EL1", null, "PMBIDR_EL1"},
      {"TRBLIMITR_EL1", "TRBPTR_EL1", "TRBBASER_EL1", "TRBSR_EL1", "TRBMAR_EL1", "TRBMPAM_EL1", "TRBTRG_EL1", "TRBIDR_EL1"},
      null,
      {null, null, null, "PMSSCR_EL1"},
      {null, "PMINTENSET_EL1", "PMINTENCLR_EL1", null, "PMUACR_EL1", "PMECR_EL1", "PMMIR_EL1", "PMIAR_EL1"}
   };
   private static final String[][] _3_0_10 = new String[][]{
      null,
      null,
      {"MAIR_EL1", "MAIR2_EL1", "PIRE0_EL1", "PIR_EL1", "POR_EL1", "S2POR_EL1"},
      {"AMAIR_EL1", "AMAIR2_EL1"},
      {"LORSA_EL1", "LOREA_EL1", "LORN_EL1", "LORC_EL1", "MPAMIDR_EL1", "MPAMBWIDR_EL1", "TLBIDIDR_EL1", "LORID_EL1"},
      {"MPAM1_EL1", "MPAM0_EL1", "MPAMCTL_EL1", "MPAMSM_EL1", "MPAMBW1_EL1", "MPAMBW0_EL1", null, "MPAMBWSM_EL1"}
   };
   private static final String[][] _3_0_12 = new String[][]{
      {"VBAR_EL1", "RVBAR_EL1", "RMR_EL1"},
      {"ISR_EL1", "DISR_EL1"},
      null,
      null,
      null,
      null,
      null,
      null,
      {"ICC_IAR0_EL1", "ICC_EOIR0_EL1", "ICC_HPPIR0_EL1", "ICC_BPR0_EL1", "ICC_AP0R0_EL1", "ICC_AP0R1_EL1", "ICC_AP0R2_EL1", "ICC_AP0R3_EL1"},
      {"ICC_AP1R0_EL1", "ICC_AP1R1_EL1", "ICC_AP1R2_EL1", "ICC_AP1R3_EL1", null, "ICC_NMIAR1_EL1"},
      {
            "ICC_PPI_HMR0_EL1",
            "ICC_PPI_HMR1_EL1",
            "ICC_IDR0_EL1",
            "ICC_HPPIR_EL1",
            "ICC_ICSR_EL1",
            "ICC_IAFFIDR_EL1",
            "ICC_PPI_ENABLER0_EL1",
            "ICC_PPI_ENABLER1_EL1"
      },
      {null, "ICC_DIR_EL1", null, "ICC_RPR_EL1", null, "ICC_SGI1R_EL1", "ICC_ASGI1R_EL1", "ICC_SGI0R_EL1"},
      {"ICC_IAR1_EL1", "ICC_EOIR1_EL1", "ICC_HPPIR1_EL1", "ICC_BPR1_EL1", "ICC_CTLR_EL1", "ICC_SRE_EL1", "ICC_IGRPEN0_EL1", "ICC_IGRPEN1_EL1"},
      {
            "ICC_PPI_CACTIVER0_EL1",
            "ICC_PPI_CACTIVER1_EL1",
            "ICC_PPI_SACTIVER0_EL1",
            "ICC_PPI_SACTIVER1_EL1",
            "ICC_PPI_CPENDR0_EL1",
            "ICC_PPI_CPENDR1_EL1",
            "ICC_PPI_SPENDR0_EL1",
            "ICC_PPI_SPENDR1_EL1"
      }
   };
   private static final String[][] _3_0_13 = new String[][]{
      {null, "CONTEXTIDR_EL1", null, "RCWSMASK_EL1", "TPIDR_EL1", "ACCDATA_EL1", "RCWMASK_EL1", "SCXTNUM_EL1"}
   };
   private static final String[][] _3_0_14 = new String[][]{null, {"CNTKCTL_EL1"}};
   private static final String[][][] _3_0 = new String[][][]{
      _sysRegs_3_0_0,
      _3_0_1,
      _3_0_2,
      new String[0][],
      _special_3_0_4,
      _3_0_5,
      _3_0_6,
      _3_0_7,
      new String[0][],
      _3_0_9,
      _3_0_10,
      new String[0][],
      _3_0_12,
      _3_0_13,
      _3_0_14,
      new String[0][]
   };
   private static final String[][] _3_1_0 = new String[][]{{"CCSIDR_EL1", "CLIDR_EL1", "CCSIDR2_EL1", null, "GMID_EL1", null, "SMIDR_EL1", "AIDR_EL1"}};
   private static final String[][] _3_1_12 = new String[][]{{"ICC_APR_EL1", "ICC_CR0_EL1", "ICC_PCR_EL1", "ICC_HAPR_EL1"}};
   private static final String[][] _3_2_0 = new String[][]{{"CSSELR_EL1"}};
   private static final String[][] _3_3_0 = new String[][]{{null, "CTR_EL0", null, null, null, null, null, "DCZID_EL0"}};
   private static final String[][] _3_3_1 = new String[][]{null, null, {null, null, null}};
   private static final String[][] _3_3_2 = new String[][]{null, null, null, null, {"RNDR", "RNDRRS"}, {null, "GCSPR_EL0"}};
   private static final String[][] _special_3_3_4 = new String[][]{
      null, null, {"NZCV", "DAIF", "SVCR", null, null, "DIT", "SSBS", "TCO"}, null, {"FPCR", "FPSR", "FPMR"}, {"DSPSR_EL0", "DLR_EL0"}
   };
   private static final String[][] _3_3_9 = new String[][]{
      null,
      null,
      null,
      null,
      {"PMICNTR_EL0"},
      null,
      {"PMICFILTR_EL0"},
      null,
      null,
      null,
      null,
      null,
      {"PMCR_EL0", "PMCNTENSET_EL0", "PMCNTENCLR_EL0", "PMOVSCLR_EL0", "PMSWINC_EL0", "PMSELR_EL0", "PMCEID0_EL0", "PMCEID1_EL0"},
      {"PMCCNTR_EL0", "PMXEVTYPER_EL0", "PMXEVCNTR_EL0", null, "PMZR_EL0"},
      {"PMUSERENR_EL0", null, null, "PMOVSSET_EL0"}
   };
   private static final String[][] _3_3_10 = new String[][]{null, null, {null, null, null, null, "POR_EL0"}};
   private static final String[][] _3_3_13 = new String[][]{
      {null, null, "TPIDR_EL0", "TPIDRRO_EL0", null, "TPIDR2_EL0", null, "SCXTNUM_EL0"},
      null,
      {"AMCR_EL0", "AMCFGR_EL0", "AMCGCR_EL0", "AMUSERENR_EL0", "AMCNTENCLR0_EL0", "AMCNTENSET0_EL0", "AMCG1IDR_EL0"},
      {"AMCNTENCLR1_EL0", "AMCNTENSET1_EL0"},
      {"AMEVCNTR00_EL0", "AMEVCNTR01_EL0", "AMEVCNTR02_EL0", "AMEVCNTR03_EL0", "AMEVCNTR04_EL0", "AMEVCNTR05_EL0", "AMEVCNTR06_EL0", "AMEVCNTR07_EL0"},
      {"AMEVCNTR08_EL0", "AMEVCNTR09_EL0", "AMEVCNTR010_EL0", "AMEVCNTR011_EL0", "AMEVCNTR012_EL0", "AMEVCNTR013_EL0", "AMEVCNTR014_EL0", "AMEVCNTR015_EL0"},
      {"AMEVTYPER00_EL0", "AMEVTYPER01_EL0", "AMEVTYPER02_EL0", "AMEVTYPER03_EL0", "AMEVTYPER04_EL0", "AMEVTYPER05_EL0", "AMEVTYPER06_EL0", "AMEVTYPER07_EL0"},
      {
            "AMEVTYPER08_EL0",
            "AMEVTYPER09_EL0",
            "AMEVTYPER010_EL0",
            "AMEVTYPER011_EL0",
            "AMEVTYPER012_EL0",
            "AMEVTYPER013_EL0",
            "AMEVTYPER014_EL0",
            "AMEVTYPER015_EL0"
      },
      null,
      null,
      null,
      null,
      {"AMEVCNTR10_EL0", "AMEVCNTR11_EL0", "AMEVCNTR12_EL0", "AMEVCNTR13_EL0", "AMEVCNTR14_EL0", "AMEVCNTR15_EL0", "AMEVCNTR16_EL0", "AMEVCNTR17_EL0"},
      {"AMEVCNTR18_EL0", "AMEVCNTR19_EL0", "AMEVCNTR110_EL0", "AMEVCNTR111_EL0", "AMEVCNTR112_EL0", "AMEVCNTR113_EL0", "AMEVCNTR114_EL0", "AMEVCNTR115_EL0"},
      {"AMEVTYPER10_EL0", "AMEVTYPER11_EL0", "AMEVTYPER12_EL0", "AMEVTYPER13_EL0", "AMEVTYPER14_EL0", "AMEVTYPER15_EL0", "AMEVTYPER16_EL0", "AMEVTYPER17_EL0"},
      {
            "AMEVTYPER18_EL0",
            "AMEVTYPER19_EL0",
            "AMEVTYPER110_EL0",
            "AMEVTYPER111_EL0",
            "AMEVTYPER112_EL0",
            "AMEVTYPER113_EL0",
            "AMEVTYPER114_EL0",
            "AMEVTYPER115_EL0"
      }
   };
   private static final String[][] _3_3_14 = new String[][]{
      {"CNTFRQ_EL0", "CNTPCT_EL0", "CNTVCT_EL0", null, null, "CNTPCTSS_EL0", "CNTVCTSS_EL0", null},
      null,
      {"CNTP_TVAL_EL0", "CNTP_CTL_EL0", "CNTP_CVAL_EL0"},
      {"CNTV_TVAL_EL0", "CNTV_CTL_EL0", "CNTV_CVAL_EL0"}
   };
   private static final String[][] _3_4_0 = new String[][]{{"VPIDR_EL2", null, null, null, "MPUIR_EL2", "VMPIDR_EL2"}};
   private static final String[][] _3_4_1 = new String[][]{
      {"SCTLR_EL2", "ACTLR_EL2", null, "SCTLR2_EL2"},
      {"HCR_EL2", "MDCR_EL2", "CPTR_EL2", "HSTR_EL2", "HFGRTR_EL2", "HFGWTR_EL2", "HFGITR_EL2", "HACR_EL2"},
      {"ZCR_EL2", "TRFCR_EL2", "HCRX_EL2", "TRCITECR_EL2", null, "SMPRIMAP_EL2", "SMCR_EL2"},
      {null, "SDER32_EL2"},
      {"SCTLRMASK_EL2", "ACTLRMASK_EL2", "CPTRMASK_EL2", "SCTLR2MASK_EL2"},
      {"NVHCR_EL2", "NVHCRX_EL2", null, null, "NVHCRMASK_EL2", "NVHCRXMASK_EL2", "HCRMASK_EL2", "HCRXMASK_EL2"}
   };
   private static final String[][] _3_4_2 = new String[][]{
      {"TTBR0_EL2", "TTBR1_EL2", "TCR_EL2", "TCR2_EL2"},
      {"VTTBR_EL2", null, "VTCR_EL2"},
      {"VNCR_EL2"},
      {null, null, "HDBSSBR_EL2", "HDBSSPROD_EL2", "HACDBSBR_EL2", "HACDBSCONS_EL2"},
      null,
      {"GCSCR_EL2", "GCSPR_EL2"},
      {"VSTTBR_EL2", null, "VSTCR_EL2"},
      {null, null, "TCRMASK_EL2", "TCR2MASK_EL2"},
      {"VTLBID0_EL2", "VTLBID1_EL2", "VTLBID2_EL2", "VTLBID3_EL2"},
      {"VTLBIDOS0_EL2", "VTLBIDOS1_EL2", "VTLBIDOS2_EL2", "VTLBIDOS3_EL2"}
   };
   private static final String[][] _3_4_3 = new String[][]{
      {"DACR32_EL2"}, {"HDFGRTR2_EL2", "HDFGWTR2_EL2", "HFGRTR2_EL2", "HFGWTR2_EL2", "HDFGRTR_EL2", "HDFGWTR_EL2", "HAFGRTR_EL2", "HFGITR2_EL2"}
   };
   private static final String[][] _special_3_4_4 = new String[][]{{"SPSR_EL2", "ELR_EL2"}, {"SP_EL1"}, null, {"SPSR_irq", "SPSR_abt", "SPSR_und", "SPSR_fiq"}};
   private static final String[][] _3_4_5 = new String[][]{
      {null, "IFSR32_EL2"}, {"AFSR0_EL2", "AFSR1_EL2"}, {"ESR_EL2", null, null, "VSESR_EL2"}, {"FPEXC32_EL2"}, null, null, {"TFSR_EL2"}
   };
   private static final String[][] _3_4_6 = new String[][]{
      {"FAR_EL2", null, null, null, "HPFAR_EL2", "PFAR_EL2"},
      {null, "PRENR_EL2"},
      {null, "PRSELR_EL2"},
      null,
      null,
      null,
      null,
      null,
      {"PRBAR_EL2", "PRLAR_EL2", null, null, "PRBAR1_EL2", "PRLAR1_EL2"},
      {"PRBAR2_EL2", "PRLAR2_EL2", null, null, "PRBAR3_EL2", "PRLAR3_EL2"},
      {"PRBAR4_EL2", "PRLAR4_EL2", null, null, "PRBAR5_EL2", "PRLAR5_EL2"},
      {"PRBAR6_EL2", "PRLAR6_EL2", null, null, "PRBAR7_EL2", "PRLAR7_EL2"},
      {"PRBAR8_EL2", "PRLAR8_EL2", null, null, "PRBAR9_EL2", "PRLAR9_EL2"},
      {"PRBAR10_EL2", "PRLAR10_EL2", null, null, "PRBAR11_EL2", "PRLAR11_EL2"},
      {"PRBAR12_EL2", "PRLAR12_EL2", null, null, "PRBAR13_EL2", "PRLAR13_EL2"},
      {"PRBAR14_EL2", "PRLAR14_EL2", null, null, "PRBAR15_EL2", "PRLAR15_EL2"}
   };
   private static final String[][] _3_4_9 = new String[][]{
      null, null, null, null, null, null, null, null, null, {"PMSCR_EL2"}, {null, null, null, "PMBSR_EL2"}, {null, null, null, "TRBSR_EL2"}
   };
   private static final String[][] _3_4_10 = new String[][]{
      null,
      {null, "MAIR2_EL2"},
      {"MAIR_EL2", null, "PIRE0_EL2", "PIR_EL2", "POR_EL2", "S2PIR_EL2"},
      {"AMAIR_EL2", "AMAIR2_EL2"},
      {"MPAMHCR_EL2", "MPAMVPMV_EL2"},
      {"MPAM2_EL2", null, "MPAMCTL_EL2", null, "MPAMBW2_EL2", null, "MPAMBWCAP_EL2"},
      {"MPAMVPM0_EL2", "MPAMVPM1_EL2", "MPAMVPM2_EL2", "MPAMVPM3_EL2", "MPAMVPM4_EL2", "MPAMVPM5_EL2", "MPAMVPM6_EL2", "MPAMVPM7_EL2"},
      {"MPAMVIDCR_EL2", "MPAMVIDSR_EL2"},
      {"MECID_P0_EL2", "MECID_A0_EL2", "MECID_P1_EL2", "MECID_A1_EL2", null, null, null, "MECIDR_EL2"},
      {"VMECID_P_EL2", "VMECID_A_EL2"}
   };
   private static final String[][] _3_4_12 = new String[][]{
      {"VBAR_EL2", "RVBAR_EL2", "RMR_EL2"},
      {null, "VDISR_EL2"},
      null,
      null,
      null,
      null,
      null,
      null,
      {"ICH_AP0R0_EL2", "ICH_AP0R1_EL2", "ICH_AP0R2_EL2", "ICH_AP0R3_EL2", "ICH_APR_EL2", "ICH_HPPIR_EL2"},
      {"ICH_AP1R0_EL2", "ICH_AP1R1_EL2", "ICH_AP1R2_EL2", "ICH_AP1R3_EL2", "ICH_HFGRTR_EL2", "ICC_SRE_EL2", "ICH_HFGWTR_EL2", "ICH_HFGITR_EL2"},
      {
            "ICH_PPI_DVIR0_EL2",
            "ICH_PPI_DVIR1_EL2",
            "ICH_PPI_ENABLER0_EL2",
            "ICH_PPI_ENABLER1_EL2",
            "ICH_PPI_PENDR0_EL2",
            "ICH_PPI_PENDR1_EL2",
            "ICH_PPI_ACTIVER0_EL2",
            "ICH_PPI_ACTIVER1_EL2"
      },
      {"ICH_HCR_EL2", "ICH_VTR_EL2", "ICH_MISR_EL2", "ICH_EISR_EL2", "ICH_VCTLR_EL2", "ICH_ELRSR_EL2", "ICH_CONTEXTR_EL2", "ICH_VMCR_EL2"}
   };
   private static final String[][] _3_4_13 = new String[][]{
      {null, "CONTEXTIDR_EL2", "TPIDR_EL2", null, null, null, null, "SCXTNUM_EL2"}, null, null, null, null, null, null, null
   };
   private static final String[][] _3_4_14 = new String[][]{
      {null, null, null, "CNTVOFF_EL2", null, null, "CNTPOFF_EL2"},
      {"CNTHCTL_EL2"},
      {"CNTHP_TVAL_EL2", "CNTHP_CTL_EL2", "CNTHP_CVAL_EL2"},
      {"CNTHV_TVAL_EL2", "CNTHV_CTL_EL2", "CNTHV_CVAL_EL2"},
      {"CNTHVS_TVAL_EL2", "CNTHVS_CTL_EL2", "CNTHVS_CVAL_EL2"},
      {"CNTHPS_TVAL_EL2", "CNTHPS_CTL_EL2", "CNTHPS_CVAL_EL2"}
   };
   private static final String[][] _system_3_5_1 = new String[][]{
      {"SCTLR_EL12", "ACTLR_EL12", "CPACR_EL12", "SCTLR2_EL12"},
      null,
      {"ZCR_EL12", "TRFCR_EL12", null, "TRCITECR_EL12", null, null, "SMCR_EL12"},
      null,
      {"SCTLRMASK_EL12", "ACTLRMASK_EL12", "CPACRMASK_EL12", "SCTLR2MASK_EL12"}
   };
   private static final String[][] _system_3_5_2 = new String[][]{
      {"TTBR0_EL12", "TTBR1_EL12", "TCR_EL12", "TCR2_EL12"},
      null,
      null,
      null,
      null,
      {"GCSCR_EL12", "GCSPR_EL12"},
      null,
      {null, null, "TCRMASK_EL12", "TCR2MASK_EL12"}
   };
   private static final String[][] _system_3_5_4 = new String[][]{{"SPSR_EL12", "ELR_EL12"}};
   private static final String[][] _system_3_5_5 = new String[][]{null, {"AFSR0_EL12", "AFSR1_EL12"}, {"ESR_EL12"}, null, null, null, {"TFSR_EL12"}};
   private static final String[][] _system_3_5_6 = new String[][]{{"FAR_EL12", null, null, null, null, "PFAR_EL12"}};
   private static final String[][] _system_3_5_9 = new String[][]{
      null, null, null, null, null, null, null, null, null, {"PMSCR_EL12"}, {null, null, null, "PMBSR_EL12"}, {null, null, null, "TRBSR_EL12"}
   };
   private static final String[][] _system_3_5_10 = new String[][]{
      null,
      null,
      {"MAIR_EL12", "MAIR2_EL12", "PIRE0_EL12", "PIR_EL12", "POR_EL12"},
      {"AMAIR_EL12", "AMAIR2_EL12"},
      null,
      {"MPAM1_EL12", null, "MPAMCTL_EL12", null, "MPAMBW1_EL12"}
   };
   private static final String[][] _system_3_5_12 = new String[][]{{"VBAR_EL12"}};
   private static final String[][] _system_3_5_13 = new String[][]{{null, "CONTEXTIDR_EL12", null, null, null, null, null, "SCXTNUM_EL12"}};
   private static final String[][] _system_3_5_14 = new String[][]{
      null, {"CNTKCTL_EL12"}, {"CNTP_TVAL_EL02", "CNTP_CTL_EL02", "CNTP_CVAL_EL02"}, {"CNTV_TVAL_EL02", "CNTV_CTL_EL02", "CNTV_CVAL_EL02"}
   };
   private static final String[][] _3_6_1 = new String[][]{
      {"SCTLR_EL3", "ACTLR_EL3", null, "SCTLR2_EL3"},
      {"SCR_EL3", "SDER32_EL3", "CPTR_EL3", null, null, "FGWTE3_EL3"},
      {"ZCR_EL3", null, "SCR2_EL3", null, null, null, "SMCR_EL3"},
      {null, "MDCR_EL3"}
   };
   private static final String[][] _3_6_2 = new String[][]{
      {"TTBR0_EL3", null, "TCR_EL3"}, {null, null, null, null, "GPTBR_EL3", "GPCBW_EL3", "GPCCR_EL3"}, null, null, null, {"GCSCR_EL3", "GCSPR_EL3"}
   };
   private static final String[][] _special_3_6_4 = new String[][]{{"SPSR_EL3", "ELR_EL3"}, {"SP_EL2"}};
   private static final String[][] _3_6_5 = new String[][]{
      null, {"AFSR0_EL3", "AFSR1_EL3"}, {"ESR_EL3", null, null, "VSESR_EL3"}, null, null, null, {"TFSR_EL3"}
   };
   private static final String[][] _3_6_6 = new String[][]{{"FAR_EL3", null, null, null, null, "MFAR_EL3"}};
   private static final String[][] _3_6_9 = new String[][]{
      null, null, null, null, null, null, null, null, null, null, {null, null, null, "PMBSR_EL3"}, {null, null, null, "TRBSR_EL3"}
   };
   private static final String[][] _3_6_10 = new String[][]{
      null,
      {null, "MAIR2_EL3"},
      {"MAIR_EL3", null, null, "PIR_EL3", "POR_EL3"},
      {"AMAIR_EL3", "AMAIR2_EL3"},
      null,
      {"MPAM3_EL3", null, "MPAMCTL_EL3", null, "MPAMBW3_EL3"},
      null,
      null,
      null,
      null,
      {null, "MECID_RL_A_EL3"}
   };
   private static final String[][] _3_6_12 = new String[][]{
      {"VBAR_EL3", "RVBAR_EL3", "RMR_EL3"},
      {null, "VDISR_EL3"},
      null,
      null,
      null,
      null,
      null,
      null,
      {"ICC_APR_EL3", "ICC_PCR_EL3", "ICC_DOMHPPIR_EL3", null, "ICC_PPI_DOMAINR0_EL3", "ICC_PPI_DOMAINR1_EL3", "ICC_PPI_DOMAINR2_EL3", "ICC_PPI_DOMAINR3_EL3"},
      {"ICC_CR0_EL3", "ICC_HPPIR_EL3"},
      null,
      null,
      {null, null, null, null, "ICC_CTLR_EL3", "ICC_SRE_EL3", null, "ICC_IGRPEN1_EL3"}
   };
   private static final String[][] _3_6_13 = new String[][]{{null, null, "TPIDR_EL3", null, null, null, null, "SCXTNUM_EL3"}};
   private static final String[][] _3_7_14 = new String[][]{null, null, {"CNTPS_TVAL_EL1", "CNTPS_CTL_EL1", "CNTPS_CVAL_EL1"}};
   private static RegisterBankArm64 instance;

   public static String getA64SystemRegister(int var0) {
      if (var0 == 32768) {
         return "DBGDTRTX_EL0";
      } else {
         int var1 = var0 & 7;
         int var2 = var0 >>> 3 & 15;
         int var3 = var0 >>> 7 & 15;
         int var4 = var0 >>> 11 & 7;
         int var5 = var0 >>> 14 & 1;
         String var6 = getUnsafeSysReg(var5, var4, var3, var2, var1);
         return var6 == null ? Strings.ff("S%d_%d_C%d_C%d_%d", var5 + 2, var4, var3, var2, var1) : var6;
      }
   }

   private static String getUnsafeSysReg(int var0, int var1, int var2, int var3, int var4) {
      return var0 == 0 ? getUnsafeOp0SysReg(var1, var2, var3, var4) : getUnsafeOp3SysReg(var1, var2, var3, var4);
   }

   private static String getUnsafeOp0SysReg(int var0, int var1, int var2, int var3) {
      if (var0 == 0) {
         if (var1 == 0) {
            if (var3 == 4) {
               return Strings.ff("DBGBVR%d_EL1", var2);
            }

            if (var3 == 5) {
               return Strings.ff("DBGBCR%d_EL1", var2);
            }

            if (var3 == 6) {
               return Strings.ff("DBGWVR%d_EL1", var2);
            }

            if (var3 == 7) {
               return Strings.ff("DBGWCR%d_EL1", var2);
            }

            return (String)ArrayUtil.getSafe2(_2_0_0, var2, var3, null);
         }

         if (var1 == 1) {
            return (String)ArrayUtil.getSafe2(_2_0_1, var2, var3, null);
         }

         if (var1 == 7) {
            return (String)ArrayUtil.getSafe2(_2_0_7, var2, var3, null);
         }

         if (var1 == 9) {
            return (String)ArrayUtil.getSafe2(_2_0_9, var2, var3, null);
         }

         if (var1 == 14) {
            return (String)ArrayUtil.getSafe2(_2_0_14, var2, var3, null);
         }
      } else if (var0 == 1) {
         if (var1 == 0) {
            return (String)ArrayUtil.getSafe2(_2_1_0, var2, var3, null);
         }

         if (var1 == 1) {
            return (String)ArrayUtil.getSafe2(_2_1_1, var2, var3, null);
         }

         if (var1 == 2) {
            return (String)ArrayUtil.getSafe2(_2_1_2, var2, var3, null);
         }

         if (var1 == 3) {
            return (String)ArrayUtil.getSafe2(_2_1_3, var2, var3, null);
         }

         if (var1 == 7) {
            return (String)ArrayUtil.getSafe2(_2_1_7, var2, var3, null);
         }

         if (var1 == 8) {
            return (String)ArrayUtil.getSafe2(_2_1_8, var2, var3, null);
         }

         if (var1 == 9) {
            return (String)ArrayUtil.getSafe2(_2_1_9, var2, var3, null);
         }
      } else if (var0 == 2) {
         if (var1 == 0 && var2 == 0 && var3 == 0) {
            return "TEECR32_EL1";
         }

         if (var1 == 1 && var2 == 0 && var3 == 0) {
            return "TEEHBR32_EL1";
         }
      } else if (var0 == 3) {
         if (var1 == 0) {
            return (String)ArrayUtil.getSafe2(_2_3_0, var2, var3, null);
         }

         if (var1 == 9) {
            return (String)ArrayUtil.getSafe2(_2_3_9, var2, var3, null);
         }

         if (var1 == 14) {
            return (String)ArrayUtil.getSafe2(_2_3_14, var2, var3, null);
         }
      } else if (var0 == 4) {
         if (var1 == 0) {
            return (String)ArrayUtil.getSafe2(_2_4_0, var2, var3, null);
         }

         if (var1 == 9) {
            return (String)ArrayUtil.getSafe2(_2_4_9, var2, var3, null);
         }
      } else if (var0 == 5) {
         if (var1 == 9) {
            return (String)ArrayUtil.getSafe2(_2_5_9, var2, var3, null);
         }
      } else if (var0 == 6) {
         if (var1 == 9) {
            return (String)ArrayUtil.getSafe2(_2_6_9, var2, var3, null);
         }
      } else if (var0 == 7 && var1 == 9) {
         return (String)ArrayUtil.getSafe2(_2_7_9, var2, var3, null);
      }

      return null;
   }

   private static String getUnsafeOp3SysReg(int var0, int var1, int var2, int var3) {
      switch (var0) {
         case 0:
            if (var1 == 12 && var2 >= 14) {
               return Strings.ff("ICC_PPI_PRIORITYR%d_EL1", (var2 & 1) << 3 | var3);
            }

            return (String)ArrayUtil.getSafe2(_3_0[var1], var2, var3, null);
         case 1:
            if (var1 == 0) {
               return (String)ArrayUtil.getSafe2(_3_1_0, var2, var3, null);
            }

            if (var1 == 12) {
               return (String)ArrayUtil.getSafe2(_3_1_12, var2, var3, null);
            }
            break;
         case 2:
            if (var1 == 0) {
               return (String)ArrayUtil.getSafe2(_3_2_0, var2, var3, null);
            }
            break;
         case 3:
            if (var1 == 0) {
               return (String)ArrayUtil.getSafe2(_3_3_0, var2, var3, null);
            }

            if (var1 == 1) {
               return (String)ArrayUtil.getSafe2(_3_3_1, var2, var3, null);
            }

            if (var1 == 2) {
               return (String)ArrayUtil.getSafe2(_3_3_2, var2, var3, null);
            }

            if (var1 == 4) {
               return (String)ArrayUtil.getSafe2(_special_3_3_4, var2, var3, null);
            }

            if (var1 == 9) {
               return (String)ArrayUtil.getSafe2(_3_3_9, var2, var3, null);
            }

            if (var1 == 10) {
               return (String)ArrayUtil.getSafe2(_3_3_10, var2, var3, null);
            }

            if (var1 == 13) {
               return (String)ArrayUtil.getSafe2(_3_3_13, var2, var3, null);
            }

            if (var1 == 14) {
               if (var2 < 8) {
                  return (String)ArrayUtil.getSafe2(_3_3_14, var2, var3, null);
               }

               if (var2 >= 8 && var2 <= 10 || var2 == 11 && var3 < 7) {
                  return Strings.ff("PMEVCNTR%d_EL0", getPMEVCounter(var2, var3));
               }

               if (var2 >= 12 && var2 <= 14 || var2 == 15 && var3 < 7) {
                  return Strings.ff("PMEVTYPER%d_EL0", getPMEVCounter(var2, var3));
               }

               if (var2 == 15 && var3 == 7) {
                  return "PMCCFILTR_EL0";
               }
            }
            break;
         case 4:
            if (var1 == 0) {
               return (String)ArrayUtil.getSafe2(_3_4_0, var2, var3, null);
            }

            if (var1 == 1) {
               return (String)ArrayUtil.getSafe2(_3_4_1, var2, var3, null);
            }

            if (var1 == 2) {
               return (String)ArrayUtil.getSafe2(_3_4_2, var2, var3, null);
            }

            if (var1 == 3) {
               return (String)ArrayUtil.getSafe2(_3_4_3, var2, var3, null);
            }

            if (var1 == 4) {
               return (String)ArrayUtil.getSafe2(_special_3_4_4, var2, var3, null);
            }

            if (var1 == 5) {
               return (String)ArrayUtil.getSafe2(_3_4_5, var2, var3, null);
            }

            if (var1 == 6) {
               return (String)ArrayUtil.getSafe2(_3_4_6, var2, var3, null);
            }

            if (var1 == 9) {
               return (String)ArrayUtil.getSafe2(_3_4_9, var2, var3, null);
            }

            if (var1 == 10) {
               return (String)ArrayUtil.getSafe2(_3_4_10, var2, var3, null);
            }

            if (var1 == 12) {
               if (var2 != 12 && var2 != 13) {
                  if (var2 >= 14) {
                     return Strings.ff("ICH_PPI_PRIORITYR%d_EL2", (var2 & 1) << 3 | var3);
                  }

                  return (String)ArrayUtil.getSafe2(_3_4_12, var2, var3, null);
               }

               return Strings.ff("ICH_LR%d_EL2", (var2 & 1) << 3 | var3);
            }

            if (var1 == 13) {
               if (var2 != 8 && var2 != 9) {
                  if (var2 != 10 && var2 != 11) {
                     return (String)ArrayUtil.getSafe2(_3_4_13, var2, var3, null);
                  }

                  return Strings.ff("AMEVCNTVOFF1%d_EL2", (var2 & 1) << 3 | var3);
               }

               return Strings.ff("AMEVCNTVOFF0%d_EL2", (var2 & 1) << 3 | var3);
            }

            if (var1 == 14) {
               return (String)ArrayUtil.getSafe2(_3_4_14, var2, var3, null);
            }
            break;
         case 5:
            if (var1 == 1) {
               return (String)ArrayUtil.getSafe2(_system_3_5_1, var2, var3, null);
            }

            if (var1 == 2) {
               return (String)ArrayUtil.getSafe2(_system_3_5_2, var2, var3, null);
            }

            if (var1 == 4) {
               return (String)ArrayUtil.getSafe2(_system_3_5_4, var2, var3, null);
            }

            if (var1 == 5) {
               return (String)ArrayUtil.getSafe2(_system_3_5_5, var2, var3, null);
            }

            if (var1 == 6) {
               return (String)ArrayUtil.getSafe2(_system_3_5_6, var2, var3, null);
            }

            if (var1 == 9) {
               return (String)ArrayUtil.getSafe2(_system_3_5_9, var2, var3, null);
            }

            if (var1 == 10) {
               return (String)ArrayUtil.getSafe2(_system_3_5_10, var2, var3, null);
            }

            if (var1 == 12) {
               return (String)ArrayUtil.getSafe2(_system_3_5_12, var2, var3, null);
            }

            if (var1 == 13) {
               return (String)ArrayUtil.getSafe2(_system_3_5_13, var2, var3, null);
            }

            if (var1 == 14) {
               return (String)ArrayUtil.getSafe2(_system_3_5_14, var2, var3, null);
            }
            break;
         case 6:
            if (var1 == 1) {
               return (String)ArrayUtil.getSafe2(_3_6_1, var2, var3, null);
            }

            if (var1 == 2) {
               return (String)ArrayUtil.getSafe2(_3_6_2, var2, var3, null);
            }

            if (var1 == 4) {
               return (String)ArrayUtil.getSafe2(_special_3_6_4, var2, var3, null);
            }

            if (var1 == 5) {
               return (String)ArrayUtil.getSafe2(_3_6_5, var2, var3, null);
            }

            if (var1 == 6) {
               return (String)ArrayUtil.getSafe2(_3_6_6, var2, var3, null);
            }

            if (var1 == 9) {
               return (String)ArrayUtil.getSafe2(_3_6_9, var2, var3, null);
            }

            if (var1 == 10) {
               return (String)ArrayUtil.getSafe2(_3_6_10, var2, var3, null);
            }

            if (var1 == 12) {
               return (String)ArrayUtil.getSafe2(_3_6_12, var2, var3, null);
            }

            if (var1 == 13) {
               return (String)ArrayUtil.getSafe2(_3_6_13, var2, var3, null);
            }
            break;
         case 7:
            if (var1 == 14) {
               return (String)ArrayUtil.getSafe2(_3_7_14, var2, var3, null);
            }

            if (var1 == 15 && var2 == 2 && var3 == 0) {
               return "CPM_IOACC_CTL_EL3";
            }
      }

      return null;
   }

   private static int getPMEVCounter(int var0, int var1) {
      if (var0 > 11) {
         var0 -= 4;
      }

      int var2 = var0 - 8;
      return var1 + var2 * 8;
   }

   public static synchronized RegisterBankArm64 getInstance() {
      if (instance == null) {
         instance = new RegisterBankArm64();
      }

      return instance;
   }

   private RegisterBankArm64() {
   }

   @Override
   public Map getDescriptionEntryMap() {
      return entries;
   }

   static {
      for (int var0 = 0; var0 <= 30; var0++) {
         add(entries, 64, "X" + var0).sl("W" + var0, 32).grp(0, var0);
      }

      add(entries, 64, "SP", "XSP").sl("WSP", 32).grp(0, 31).typ(RegisterType.StackPointer);
      add(entries, 64, "PC").sl("PC ", 32).grp(10, 0).typ(RegisterType.ProgramCounter);
      add(entries, 32, "cpsr").grp(11, 0).typ(RegisterType.Flags);
      add(entries, 64, "XZR").sl("WZR", 32).grp(0, 33);

      for (int var1 = 0; var1 < 16; var1++) {
         add(entries, 32, "c" + var1).grp(4, var1);
      }

      for (int var2 = 0; var2 < 32; var2++) {
         add(entries, 128, "Q" + var2).sl("D" + var2, 64).sl("S" + var2, 32).sl("H" + var2, 16).sl("B" + var2, 8).grp(6, var2);
      }

      for (int var3 = 0; var3 < 32; var3++) {
         add(entries, 128, "V" + var3).grp(7, var3);
      }

      for (int var4 = 0; var4 <= 32768; var4++) {
         add(entries, 64, getA64SystemRegister(var4)).grp(9, var4);
      }

      for (int var5 = 0; var5 < 32; var5++) {
         add(entries, 128, "Z" + var5).grp(12, var5);
      }

      for (int var6 = 0; var6 < 16; var6++) {
         add(entries, 16, "P" + var6).grp(13, var6);
      }

      for (int var7 = 0; var7 < 16; var7++) {
         add(entries, 16, "PN" + var7).grp(15, var7);
      }

      add(entries, 16, "FFR").grp(13, 16);

      for (int var8 = 0; var8 < 16; var8++) {
         add(entries, 64, "ZA" + var8).grp(14, var8);
      }

      add(entries, 64, "ZA").grp(14, 16);
   }
}
