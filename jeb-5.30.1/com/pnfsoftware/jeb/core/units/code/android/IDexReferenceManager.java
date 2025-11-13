package com.pnfsoftware.jeb.core.units.code.android;

import com.pnfsoftware.jeb.core.units.code.android.dex.DexPoolType;
import com.pnfsoftware.jeb.core.units.code.android.dex.DexReferenceType;
import java.util.Collection;

public interface IDexReferenceManager {
   Collection getReferences(DexPoolType var1, int var2);

   Collection getReferences(DexPoolType var1, int var2, int var3);

   boolean addStringReference(String var1, String var2, DexReferenceType var3);

   boolean addMethodReference(String var1, String var2, DexReferenceType var3);

   boolean addFieldReference(String var1, String var2, DexReferenceType var3);
}
