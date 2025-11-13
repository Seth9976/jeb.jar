package com.pnfsoftware.jeb.core.units.code.asm.processor.arch;

import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterType;
import java.util.Map;
import java.util.TreeMap;

public class RegisterBankX64 extends AbstractRegisterBank {
   private static final Map entries = new TreeMap();
   private static RegisterBankX64 instance;

   public static synchronized RegisterBankX64 getInstance() {
      if (instance == null) {
         instance = new RegisterBankX64();
      }

      return instance;
   }

   private RegisterBankX64() {
   }

   @Override
   public Map getDescriptionEntryMap() {
      return entries;
   }

   static {
      add(entries, 64, "rax").sl("eax", 32).sl("ax", 16).sl("al", 8).sl("ah", 8, 16).grp(0, 0);
      add(entries, 64, "rcx").sl("ecx", 32).sl("cx", 16).sl("cl", 8).sl("ch", 8, 16).grp(0, 1);
      add(entries, 64, "rdx").sl("edx", 32).sl("dx", 16).sl("dl", 8).sl("dh", 8, 16).grp(0, 2);
      add(entries, 64, "rbx").sl("ebx", 32).sl("bx", 16).sl("bl", 8).sl("bh", 8, 16).grp(0, 3);
      add(entries, 64, "rsp").sl("esp", 32).sl("sp", 16).sl("spl", 8).grp(0, 4).typ(RegisterType.StackPointer);
      add(entries, 64, "rbp").sl("ebp", 32).sl("bp", 16).sl("bpl", 8).grp(0, 5);
      add(entries, 64, "rsi").sl("esi", 32).sl("si", 16).sl("sil", 8).grp(0, 6);
      add(entries, 64, "rdi").sl("edi", 32).sl("di", 16).sl("dil", 8).grp(0, 7);
      add(entries, 64, "r8").sl("r8d", 32).sl("r8w", 16).sl("r8b", 8).grp(0, 8);
      add(entries, 64, "r9").sl("r9d", 32).sl("r9w", 16).sl("r9b", 8).grp(0, 9);
      add(entries, 64, "r10").sl("r10d", 32).sl("r10w", 16).sl("r10b", 8).grp(0, 10);
      add(entries, 64, "r11").sl("r11d", 32).sl("r11w", 16).sl("r11b", 8).grp(0, 11);
      add(entries, 64, "r12").sl("r12d", 32).sl("r12w", 16).sl("r12b", 8).grp(0, 12);
      add(entries, 64, "r13").sl("r13d", 32).sl("r13w", 16).sl("r13b", 8).grp(0, 13);
      add(entries, 64, "r14").sl("r14d", 32).sl("r14w", 16).sl("r14b", 8).grp(0, 14);
      add(entries, 64, "r15").sl("r15d", 32).sl("r15w", 16).sl("r15b", 8).grp(0, 15);
      add(entries, 64, "rip", "pc").sl("eip", 32).sl("ip", 16).grp(10, 0).typ(RegisterType.ProgramCounter);
      add(entries, 64, "rflags").sl("eflags", 32).sl("flags", 16).grp(11, 0).typ(RegisterType.Flags);
      add(entries, 16, "cs").grp(2, 1);
      add(entries, 16, "ss").grp(2, 2);
      add(entries, 16, "ds").grp(2, 3);
      add(entries, 16, "es").grp(2, 0);
      add(entries, 16, "fs").grp(2, 4);
      add(entries, 16, "gs").grp(2, 5);

      for (int var0 = 0; var0 <= 7; var0++) {
         add(entries, 80, "st(" + var0 + ")").grp(3, var0);
      }

      for (int var1 = 0; var1 <= 7; var1++) {
         add(entries, 64, "mm" + var1).grp(4, var1);
      }

      for (int var2 = 0; var2 <= 31; var2++) {
         add(entries, 512, "zmm" + var2).sl("ymm" + var2, 256).sl("xmm" + var2, 128).grp(5, var2);
      }
   }
}
