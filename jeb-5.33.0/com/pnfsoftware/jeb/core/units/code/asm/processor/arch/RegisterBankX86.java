package com.pnfsoftware.jeb.core.units.code.asm.processor.arch;

import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterType;
import java.util.Map;
import java.util.TreeMap;

public class RegisterBankX86 extends AbstractRegisterBank {
   public static final int regGrp_GP = 0;
   public static final int regGrp_Seg = 2;
   public static final int regGrp_X87 = 3;
   public static final int regGrp_MMX = 4;
   public static final int regGrp_XMM = 5;
   public static final int regGrp_MR = 6;
   public static final int regGrp_Ctl = 8;
   public static final int regGrp_Dbg = 9;
   public static final int regGrp_IP = 10;
   public static final int regGrp_Flags = 11;
   public static final int regGrp_X87_Stack = 12;
   public static final int regGrp_BND = 13;
   private static final Map entries = new TreeMap();
   private static RegisterBankX86 instance;

   public static synchronized RegisterBankX86 getInstance() {
      if (instance == null) {
         instance = new RegisterBankX86();
      }

      return instance;
   }

   private RegisterBankX86() {
   }

   @Override
   public Map getDescriptionEntryMap() {
      return entries;
   }

   static {
      add(entries, 32, "eax").sl("ax", 16).sl("al", 8).sl("ah", 8, 16).grp(0, 0);
      add(entries, 32, "ecx").sl("cx", 16).sl("cl", 8).sl("ch", 8, 16).grp(0, 1);
      add(entries, 32, "edx").sl("dx", 16).sl("dl", 8).sl("dh", 8, 16).grp(0, 2);
      add(entries, 32, "ebx").sl("bx", 16).sl("bl", 8).sl("bh", 8, 16).grp(0, 3);
      add(entries, 32, "esp").sl("sp", 16).sl("spl", 8).grp(0, 4).typ(RegisterType.StackPointer);
      add(entries, 32, "ebp").sl("bp", 16).sl("bpl", 8).grp(0, 5);
      add(entries, 32, "esi").sl("si", 16).sl("sil", 8).grp(0, 6);
      add(entries, 32, "edi").sl("di", 16).sl("dil", 8).grp(0, 7);
      add(entries, 32, "eip", "pc").sl("ip", 16).grp(10, 0).typ(RegisterType.ProgramCounter);
      add(entries, 32, "eflags").sl("flags", 16).grp(11, 0).typ(RegisterType.Flags);
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
         add(entries, 128, "xmm" + var2).grp(5, var2);
      }
   }
}
