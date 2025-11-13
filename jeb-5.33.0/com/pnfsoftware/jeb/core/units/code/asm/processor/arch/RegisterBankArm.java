package com.pnfsoftware.jeb.core.units.code.asm.processor.arch;

import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterDescriptionEntry;
import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterType;
import java.util.Map;
import java.util.TreeMap;

public class RegisterBankArm extends AbstractRegisterBank {
   public static final int regGrp_GP = 0;
   public static final int regGrp_BANKED = 2;
   public static final int regGrp_COPROC = 4;
   public static final int regGrp_SIMD = 6;
   public static final int regGrp_FP_SYSTEM = 8;
   public static final int regGrp_Flags = 11;
   private static final Map entries = new TreeMap();
   private static final String UNPREDICTABLE = "UNPREDICTABLE";
   private static String[] StatusRegisters = new String[]{
      null,
      "CPSR_c",
      "CPSR_x",
      "CPSR_xc",
      "APSR_g",
      "CPSR_sc",
      "CPSR_sx",
      "CPSR_sxc",
      "APSR_nzcvq",
      "CPSR_fc",
      "CPSR_fx",
      "CPSR_fxc",
      "APSR_nzcvqg",
      "CPSR_fsc",
      "CPSR_fsx",
      "CPSR_fsxc",
      null,
      "SPSR_c",
      "SPSR_x",
      "SPSR_xc",
      "SPSR_s",
      "SPSR_sc",
      "SPSR_sx",
      "SPSR_sxc",
      "SPSR_f",
      "SPSR_fc",
      "SPSR_fx",
      "SPSR_fxc",
      "SPSR_fs",
      "SPSR_fsc",
      "SPSR_fsx",
      "SPSR_fsxc"
   };
   public static final int APSR_nzcv = StatusRegisters.length;
   public static final int APSR = StatusRegisters.length + 1;
   public static final int SPSR = StatusRegisters.length + 2;
   private static String[] BankedRegisters = new String[]{
      "R8_usr",
      "R9_usr",
      "R10_usr",
      "R11_usr",
      "R12_usr",
      "SP_usr",
      "LR_usr",
      "UNPREDICTABLE",
      "R8_fiq",
      "R9_fiq",
      "R10_fiq",
      "R11_fiq",
      "R12_fiq",
      "SP_fiq",
      "LR_fiq",
      "UNPREDICTABLE",
      "LR_irq",
      "SP_irq",
      "LR_svc",
      "SP_svc",
      "LR_abt",
      "SP_abt",
      "LR_und",
      "SP_und",
      "UNPREDICTABLE",
      "UNPREDICTABLE",
      "UNPREDICTABLE",
      "UNPREDICTABLE",
      "LR_mon",
      "SP_mon",
      "ELR_hyp",
      "SP_hyp",
      "UNPREDICTABLE",
      "UNPREDICTABLE",
      "UNPREDICTABLE",
      "UNPREDICTABLE",
      "UNPREDICTABLE",
      "UNPREDICTABLE",
      "UNPREDICTABLE",
      "UNPREDICTABLE",
      "UNPREDICTABLE",
      "UNPREDICTABLE",
      "UNPREDICTABLE",
      "UNPREDICTABLE",
      "UNPREDICTABLE",
      "UNPREDICTABLE",
      "SPSR_fiq",
      "UNPREDICTABLE",
      "SPSR_irq",
      "UNPREDICTABLE",
      "SPSR_svc",
      "UNPREDICTABLE",
      "SPSR_abt",
      "UNPREDICTABLE",
      "SPSR_und",
      "UNPREDICTABLE",
      "UNPREDICTABLE",
      "UNPREDICTABLE",
      "UNPREDICTABLE",
      "UNPREDICTABLE",
      "SPSR_mon",
      "UNPREDICTABLE",
      "SPSR_hyp",
      "UNPREDICTABLE"
   };
   private static String[] floatingPointSystemRegisters = new String[]{
      "FPSID", "FPSCR", "#2", "#3", "#4", "MVFR2", "MVFR1", "MVFR0", "FPEXC", "FPINST", "FPINST2", "#11", "#12", "#13", "#14", "#15"
   };
   private static RegisterBankArm instance;

   public static synchronized RegisterBankArm getInstance() {
      if (instance == null) {
         instance = new RegisterBankArm();
      }

      return instance;
   }

   private RegisterBankArm() {
   }

   @Override
   public Map getDescriptionEntryMap() {
      return entries;
   }

   public static long getSpId() {
      return RegisterUtil.createPureRegisterId(13, 0);
   }

   static {
      for (int var0 = 0; var0 < 13; var0++) {
         add(entries, 32, "R" + var0).grp(0, var0);
      }

      add(entries, 32, "SP").grp(0, 13).typ(RegisterType.StackPointer);
      add(entries, 32, "LR").grp(0, 14);
      add(entries, 32, "PC").grp(0, 15).typ(RegisterType.ProgramCounter);
      add(entries, 32, "CPSR").grp(11, 0).typ(RegisterType.Flags);

      for (int var4 = 0; var4 < 16; var4++) {
         if (StatusRegisters[var4] != null) {
            add(entries, 32, StatusRegisters[var4]).grp(11, var4);
         }
      }

      add(entries, 32, "APSR_nzcv").grp(11, APSR_nzcv);
      add(entries, 32, "APSR").grp(11, APSR);
      add(entries, 32, "SPSR").grp(11, SPSR);

      for (int var5 = 16; var5 < StatusRegisters.length; var5++) {
         if (StatusRegisters[var5] != null) {
            add(entries, 32, StatusRegisters[var5]).grp(11, var5);
         }
      }

      for (int var6 = 0; var6 < BankedRegisters.length; var6++) {
         add(entries, 32, BankedRegisters[var6]).grp(2, var6);
      }

      for (int var7 = 0; var7 < 16; var7++) {
         add(entries, 32, "c" + var7).grp(4, var7);
      }

      for (int var8 = 0; var8 < floatingPointSystemRegisters.length; var8++) {
         add(entries, 32, floatingPointSystemRegisters[var8]).grp(8, var8);
      }

      for (int var9 = 0; var9 < 16; var9++) {
         int var1 = var9 * 2;
         RegisterDescriptionEntry var2 = add(entries, 128, "Q" + var9).sl("D" + var1, 64).sl("D" + (var1 + 1), 64, 128).grp(6, var9);
         if (var9 < 8) {
            int var3 = var9 * 4;
            var2.sl("S" + var3, 32).sl("S" + (var3 + 1), 32, 64).sl("S" + (var3 + 2), 64, 96).sl("S" + (var3 + 3), 96, 128);
         }
      }
   }
}
