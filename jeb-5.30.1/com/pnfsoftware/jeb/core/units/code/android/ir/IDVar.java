package com.pnfsoftware.jeb.core.units.code.android.ir;

public interface IDVar extends IDExpression, Comparable {
   int getId();

   boolean usesSingleRegister();

   boolean usesPairOfRegisters();

   String getPreferredName();

   void setPreferredName(String var1);

   IDVar duplicate();
}
