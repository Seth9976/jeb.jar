package com.pnfsoftware.jeb.core.units.code.asm.processor.arch;

import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterType;
import java.util.Map;
import java.util.TreeMap;

public class RegisterBankMips extends AbstractRegisterBank {
   public static final int regGrp_GP = 0;
   public static final int regGrp_FCC = 2;
   public static final int regGrp_F = 3;
   public static final int regGrp_COPROC = 4;
   public static final int regGrp_HW = 5;
   public static final int regGrp_DSP_AC = 6;
   public static final int regGrp_IP = 10;
   public static final int regGrp_LOHI = 12;
   private static final Map entries = new TreeMap();
   private static RegisterBankMips instance;

   static void buildRegisters(Map var0, int var1) {
      add(var0, var1, "$zero", "$0").grp(0, 0);
      add(var0, var1, "$at", "$1").grp(0, 1);
      add(var0, var1, "$v0", "$2").grp(0, 2);
      add(var0, var1, "$v1", "$3").grp(0, 3);
      add(var0, var1, "$a0", "$4").grp(0, 4);
      add(var0, var1, "$a1", "$5").grp(0, 5);
      add(var0, var1, "$a2", "$6").grp(0, 6);
      add(var0, var1, "$a3", "$7").grp(0, 7);

      for (int var2 = 0; var2 < 4; var2++) {
         add(var0, var1, "$t" + var2, "$" + (8 + var2)).grp(0, 8 + var2).addName("$a" + (4 + var2));
      }

      for (int var3 = 0; var3 < 4; var3++) {
         add(var0, var1, "$t" + (4 + var3), "$" + (12 + var3)).grp(0, 12 + var3);
      }

      for (int var4 = 0; var4 < 8; var4++) {
         add(var0, var1, "$s" + var4, "$" + (16 + var4)).grp(0, 16 + var4);
      }

      add(var0, var1, "$t8", "$24").grp(0, 24);
      add(var0, var1, "$t9", "$25").grp(0, 25);
      add(var0, var1, "$k0", "$26").grp(0, 26);
      add(var0, var1, "$k1", "$27").grp(0, 27);
      add(var0, var1, "$gp").grp(0, 28);
      add(var0, var1, "$sp").grp(0, 29).typ(RegisterType.StackPointer);
      add(var0, var1, "$fp", "$s8").grp(0, 30);
      add(var0, var1, "$ra").grp(0, 31);
      add(var0, var1, "lo").grp(12, 0);
      add(var0, var1, "hi").grp(12, 1);
      add(var0, var1, "pc").grp(10, 0).typ(RegisterType.ProgramCounter);

      for (int var5 = 0; var5 < 8; var5++) {
         add(var0, 1, "$fcc" + var5).grp(2, var5);
      }

      for (int var6 = 0; var6 < 32; var6++) {
         add(var0, var1, "$f" + var6).grp(3, var6);
      }

      for (int var7 = 0; var7 < 32; var7++) {
         add(var0, var1, "$" + var7).grp(4, var7);
      }

      add(var0, var1, "CPUNum").grp(5, 0);
      add(var0, var1, "SYNCI_Step").grp(5, 1);
      add(var0, var1, "CC").grp(5, 2);
      add(var0, var1, "CCRes").grp(5, 3);
      add(var0, var1, "PerfCtr").grp(5, 4);
      add(var0, var1, "XNP").grp(5, 5);

      for (int var8 = 6; var8 < 29; var8++) {
         add(var0, var1, "$" + var8).grp(5, var8);
      }

      add(var0, var1, "ULR").grp(5, 29);
      add(var0, var1, "$30").grp(5, 30);
      add(var0, var1, "$31").grp(5, 31);

      for (int var9 = 0; var9 < 4; var9++) {
         add(var0, var1, "$ac" + var9).grp(6, var9);
      }
   }

   public static synchronized RegisterBankMips getInstance() {
      if (instance == null) {
         instance = new RegisterBankMips();
      }

      return instance;
   }

   private RegisterBankMips() {
   }

   @Override
   public Map getDescriptionEntryMap() {
      return entries;
   }

   static {
      buildRegisters(entries, 32);
   }
}
