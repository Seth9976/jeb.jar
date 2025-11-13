package com.pnfsoftware.jeb.core.units.code.asm.processor;

import com.pnfsoftware.jeb.core.units.codeobject.ProcessorType;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class RegisterBankService {
   private static final ILogger logger = GlobalLog.getLogger(RegisterBankService.class);
   private static volatile RegisterBankService instance;
   private Map map = new LinkedHashMap();

   public static RegisterBankService getInstance() {
      if (instance == null) {
         synchronized (RegisterBankService.class) {
            if (instance == null) {
               RegisterBankService var1 = new RegisterBankService();
               instance = var1;
            }
         }
      }

      return instance;
   }

   private RegisterBankService() {
   }

   public IRegisterBank add(ProcessorType var1, IRegisterBank var2) {
      if (var1 != null && var2 != null) {
         synchronized (this.map) {
            return (IRegisterBank)this.map.put(var1, var2);
         }
      } else {
         throw new IllegalArgumentException();
      }
   }

   public boolean remove(ProcessorType var1) {
      synchronized (this.map) {
         return this.map.remove(var1) != null;
      }
   }

   public IRegisterBank get(ProcessorType var1) {
      synchronized (this.map) {
         return (IRegisterBank)this.map.get(var1);
      }
   }

   public Collection getSupportedProcessors() {
      synchronized (this.map) {
         return Collections.unmodifiableSet(this.map.keySet());
      }
   }
}
