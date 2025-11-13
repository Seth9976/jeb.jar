package com.pnfsoftware.jeb.core.units.code.asm.processor.arch;

import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterDescriptionEntry;
import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterEncoding;
import com.pnfsoftware.jeb.core.units.code.asm.processor.RegisterType;
import java.util.Map;
import java.util.TreeMap;

public class AbstractRegisterBank2 extends AbstractRegisterBank {
   protected final Map entries = new TreeMap();

   @Override
   public Map getDescriptionEntryMap() {
      return this.entries;
   }

   protected RegisterDescriptionEntry add(int var1, String var2, String var3, RegisterEncoding var4, RegisterType var5) {
      return add(this.entries, var1, var2, var3, var4, var5);
   }

   protected RegisterDescriptionEntry add(int var1, String var2, String var3) {
      return add(this.entries, var1, var2, var3);
   }

   protected RegisterDescriptionEntry add(int var1, String var2) {
      return add(this.entries, var1, var2);
   }
}
