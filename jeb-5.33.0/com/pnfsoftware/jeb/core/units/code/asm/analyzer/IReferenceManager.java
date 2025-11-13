package com.pnfsoftware.jeb.core.units.code.asm.analyzer;

import com.pnfsoftware.jeb.core.units.code.asm.items.INativeMethodItem;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.Set;

@Ser
public interface IReferenceManager {
   boolean recordInternalReference(long var1, long var3, ReferenceType var5);

   boolean recordInternalReference(long var1, long var3, ReferenceType var5, int var6);

   boolean recordExternalReference(long var1, INativeMethodItem var3, ReferenceType var4);

   boolean recordExternalReference(long var1, INativeMethodItem var3, ReferenceType var4, int var5);

   boolean recordReference(ReferenceLocation var1, ReferenceLocation var2, ReferenceType var3);

   boolean recordReference(ReferenceLocation var1, ReferenceLocation var2, ReferenceType var3, int var4);

   Set getReferencesTo(long var1);

   Set getReferencesToExternalMethod(INativeMethodItem var1);

   Set getReferencesTo(ReferenceLocation var1);

   Set getReferencesFrom(long var1);

   Set getReferencesFrom(ReferenceLocation var1);

   boolean unrecordAllReferencesFrom(long var1);

   boolean unrecordAllReferencesFrom(ReferenceLocation var1);

   boolean unrecordAllReferencesTo(long var1);

   boolean unrecordAllReferencesTo(ReferenceLocation var1);

   boolean unrecordReference(long var1, long var3);

   boolean unrecordReference(ReferenceLocation var1, ReferenceLocation var2);
}
