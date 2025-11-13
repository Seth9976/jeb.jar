package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IJavaStatement extends IJavaExpression {
   int getIntermediateOffset();

   void setIntermediateOffset(int var1);

   IJavaStatement duplicate();
}
