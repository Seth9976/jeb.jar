package com.pnfsoftware.jeb.core.units.code.asm.processor.arch;

import java.util.Map;
import java.util.TreeMap;

public class RegisterBankMips64 extends AbstractRegisterBank {
   private static final Map entries = new TreeMap();
   private static RegisterBankMips64 instance;

   public static synchronized RegisterBankMips64 getInstance() {
      if (instance == null) {
         instance = new RegisterBankMips64();
      }

      return instance;
   }

   private RegisterBankMips64() {
   }

   @Override
   public Map getDescriptionEntryMap() {
      return entries;
   }

   static {
      RegisterBankMips.buildRegisters(entries, 64);
   }
}
