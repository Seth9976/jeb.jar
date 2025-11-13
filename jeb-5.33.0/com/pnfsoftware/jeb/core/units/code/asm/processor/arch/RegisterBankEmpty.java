package com.pnfsoftware.jeb.core.units.code.asm.processor.arch;

import java.util.Collections;
import java.util.Map;

public class RegisterBankEmpty extends AbstractRegisterBank {
   private static RegisterBankEmpty instance;

   public static synchronized RegisterBankEmpty getInstance() {
      if (instance == null) {
         instance = new RegisterBankEmpty();
      }

      return instance;
   }

   private RegisterBankEmpty() {
   }

   @Override
   public Map getDescriptionEntryMap() {
      return Collections.emptyMap();
   }
}
