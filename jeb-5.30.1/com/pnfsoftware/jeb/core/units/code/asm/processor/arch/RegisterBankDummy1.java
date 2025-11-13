package com.pnfsoftware.jeb.core.units.code.asm.processor.arch;

import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterType;
import java.util.Map;
import java.util.TreeMap;

public class RegisterBankDummy1 extends AbstractRegisterBank {
   public static final int regGrp_GP = 0;
   public static final int regGrp_SIMD = 1;
   public static final int regGrp_Special = 10;
   private static final Map entries = new TreeMap();
   private static RegisterBankDummy1 instance;

   public static synchronized RegisterBankDummy1 getInstance() {
      if (instance == null) {
         instance = new RegisterBankDummy1();
      }

      return instance;
   }

   private RegisterBankDummy1() {
   }

   @Override
   public Map getDescriptionEntryMap() {
      return entries;
   }

   static {
      for (int var0 = 0; var0 <= 7; var0++) {
         add(entries, 32, "r" + var0).grp(0, var0);
      }

      add(entries, 32, "PC").grp(10, 0).typ(RegisterType.ProgramCounter);
      add(entries, 32, "SP").grp(10, 1).typ(RegisterType.StackPointer);
      add(entries, 32, "SR").grp(10, 2).typ(RegisterType.Flags);
      add(entries, 128, "md0").sl("fp0", 64).grp(1, 0);

      for (int var1 = 1; var1 <= 15; var1++) {
         add(entries, 128, "md" + var1).sl("fp" + var1, 64).grp(1, var1);
      }
   }
}
