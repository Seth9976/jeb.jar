package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IJavaLabel extends IJavaStatement {
   String getName();

   int getOffset();

   void generate(JavaOutputSink var1, boolean var2);

   IJavaLabel duplicate();
}
