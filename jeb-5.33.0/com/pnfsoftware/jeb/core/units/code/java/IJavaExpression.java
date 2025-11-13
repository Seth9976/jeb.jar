package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IJavaExpression extends IJavaElement {
   IJavaExpression duplicate();
}
