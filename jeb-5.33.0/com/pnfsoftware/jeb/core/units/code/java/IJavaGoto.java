package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IJavaGoto extends IJavaStatement {
   IJavaLabel getLabel();

   void setLabel(IJavaLabel var1);

   IJavaGoto duplicate();
}
