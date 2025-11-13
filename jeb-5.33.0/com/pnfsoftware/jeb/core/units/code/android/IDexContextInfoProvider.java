package com.pnfsoftware.jeb.core.units.code.android;

public interface IDexContextInfoProvider {
   boolean setMethodCAT(String var1, ContextAccessType var2);

   ContextAccessType getMethodCAT(String var1);

   boolean setFieldEFInfo(String var1, EffectiveFinalityType var2);

   EffectiveFinalityType getFieldEFInfo(String var1);
}
