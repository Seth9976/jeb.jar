package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IJavaBreak extends IJavaStatement {
   IJavaLabel getLabel();

   void setLabel(IJavaLabel var1);

   IJavaBreak duplicate();
}
