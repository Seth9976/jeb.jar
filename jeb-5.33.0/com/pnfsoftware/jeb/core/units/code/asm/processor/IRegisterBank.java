package com.pnfsoftware.jeb.core.units.code.asm.processor;

import java.util.Collection;

public interface IRegisterBank {
   int getCountOfDescriptionEntries();

   Collection getDescriptionEntries();

   RegisterDescriptionEntry getDescriptionEntry(int var1);

   RegisterDescriptionEntry getDescriptionEntryByType(RegisterType var1);

   Collection getAllDescriptionEntries();

   RegisterDescriptionEntry getDescriptionEntryByName(String var1);

   RegisterDescriptionEntry getDescriptionEntryByName(Collection var1);

   RegisterDescriptionEntry getDescriptionEntryById(long var1);

   String format(int var1);
}
